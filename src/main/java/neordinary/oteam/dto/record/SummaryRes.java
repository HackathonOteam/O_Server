package neordinary.oteam.dto.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryRes {
    String date; // ex. 2023.06.11
    String emotion; // 감정
    String summary; // 요약

}
