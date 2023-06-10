package neordinary.oteam.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import neordinary.oteam.domain.diary.Diary;
import neordinary.oteam.domain.record.Record;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name = "DD_USER")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name", unique = true, nullable = false)
    private String name; // 사용자 이름

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Record> records = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diary> diaries = new ArrayList<>();


}
