package neordinary.oteam.domain.diary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long>, DiaryCustomRepository{

    @Query("select d from Diary d where d.diaryDate =:date and d.user.name =:userName")
    Diary findYearAgo(@Param(value = "userName")String userName, @Param(value = "date") LocalDate date);

    @Query("select d from Diary d where d.user.name =:userName")
    List<Diary> findByUserName(@Param(value = "userName") String userName);

    @Query("select d from Diary d where d.user.userId =:userId")
    Diary findByUserId(@Param(value = "userId") Long userId);

    @Query("select d from Diary d where d.diaryDate =:now and d.user.userId =:userId")
    Diary findByUserIdAndDate(@Param(value = "userId") Long userId, @Param(value = "now") LocalDate now);

    // findMonthlyDiary(String , YearMonth );
}
