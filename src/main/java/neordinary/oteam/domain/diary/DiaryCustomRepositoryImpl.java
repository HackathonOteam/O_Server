package neordinary.oteam.domain.diary;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

import static neordinary.oteam.domain.diary.QDiary.diary;
import static neordinary.oteam.domain.user.QUser.user;

@Repository
@RequiredArgsConstructor
@Slf4j
public class DiaryCustomRepositoryImpl implements DiaryCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Diary> findMonthlyDiary(String userName, YearMonth month) {

        LocalDate startTimeOfMonth = LocalDate.from(month.atDay(1) .atStartOfDay());
        LocalDate endTimeOfMonth = month.atEndOfMonth();

        return jpaQueryFactory.select(diary)
                .from(diary)
                .leftJoin(user).on(user.userId.eq(diary.user.userId))
                .where(user.name.eq(userName),
                        diary.diaryDate.between(startTimeOfMonth, endTimeOfMonth))
                .fetch();

    }
}
