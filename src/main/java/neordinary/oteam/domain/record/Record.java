package neordinary.oteam.domain.record;

import lombok.Getter;
import lombok.NoArgsConstructor;
import neordinary.oteam.domain.diary.Diary;
import neordinary.oteam.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name = "DD_RECORD")
@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long recordId;

    private Long userId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // 기록 일시

    @Column(name = "contents", nullable = false)
    private String contents; // 기록 내용

    @Column(name = "answer", nullable = false)
    private String answer; // gpt 답변

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;


    public void updateUserId(Long userId) {
        this.userId = userId;
    }

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
}
