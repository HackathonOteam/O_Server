package neordinary.oteam.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import neordinary.oteam.domain.diary.Diary;
import neordinary.oteam.domain.record.Record;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "DD_USER")
@Entity
@Builder
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // 기록 일시

    @Column(name = "name", unique = true, nullable = false)
    private String name; // 사용자 이름

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diary> diaries = new ArrayList<>();

    public static User create(String name) {

        return User.builder()
                .name(name)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
