package neordinary.oteam.domain.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    @Query(value = "insert into dd_record(created_at, contents, answer, diary_id) values(:createdAt, :contents, :answer,  :diaryId)", nativeQuery = true)
    void addUserRecord(@Param("createdAt") LocalDateTime createdAt, @Param("contents") String contents, @Param("answer") String answer, @Param("diaryId") Long diaryId);

    // 요약 생성 (오늘 기록한 레코드 데이터 모두 반환)
    @Query(value = "select contents from dd_record where diary_id=:diaryId and DATE(created_at)=:today", nativeQuery = true)
    List<String> findTodayContents(@Param("diaryId") Long diaryId, @Param("today") LocalDate today);

    @Modifying(clearAutomatically = true)
    @Query(value = "update dd_diary set summary = :summary, emotion = :emotion where diary_id=:diaryId", nativeQuery = true)
    void updateTodayDiary(@Param("diaryId") Long diaryId, @Param("emotion") String emotion, @Param("summary") String summary);
}
