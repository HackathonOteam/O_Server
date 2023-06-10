package neordinary.oteam.domain.diary;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.NoArgsConstructor;
import neordinary.oteam.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name = "DD_DIARY")
@Entity
public class Diary{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long diary_id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // 등록 일자

    @Column(name = "summary", nullable = false)
    private String summary; // 요약 내용

    @Column(name = "emotion", nullable = false)
    private String emotion; // 감정

    @Column(name = "is_anniversary")
    private Boolean isAnniversary; // 기념일 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
