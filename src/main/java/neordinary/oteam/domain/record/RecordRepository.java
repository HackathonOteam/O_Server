package neordinary.oteam.domain.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query(value = "select user_id from dd_user where name=:name", nativeQuery = true)
    Long findUsername(@Param("name") String name);

    // 다이어리 레코드 생성
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into dd_diary(created_at, user_id) values(:createdAt, :userId)", nativeQuery = true)
    void addUserTodayDiary(@Param("createdAt") LocalDateTime createdAt, @Param("userId") Long userId);

    @Query(value = "select count(*) from dd_diary where user_id=:userId and DATE(created_at)=:today", nativeQuery = true) // 오늘 조건 추가해야지 바보야~
    Integer findUserTodayDiary(@Param("userId") Long userId, @Param("today") LocalDate today);

    @Query(value = "select diary_id from dd_diary where user_id=:userId", nativeQuery = true)
    Long findDiaryId(@Param("userId") Long userId);

    @Modifying(clearAutomatically = true)
    @Query(value = "insert into dd_record(created_at, contents, diary_id) values(:createdAt, :contents, :diaryId)", nativeQuery = true)
    void addUserRecord(@Param("createdAt") LocalDateTime createdAt, @Param("contents") String contents, @Param("diaryId") Long diaryId);

}
