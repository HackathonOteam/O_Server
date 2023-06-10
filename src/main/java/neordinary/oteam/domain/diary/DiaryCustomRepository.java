package neordinary.oteam.domain.diary;

import java.time.YearMonth;
import java.util.List;

public interface DiaryCustomRepository {

    List<Diary> findMonthlyDiary(String userName, YearMonth date);
}
