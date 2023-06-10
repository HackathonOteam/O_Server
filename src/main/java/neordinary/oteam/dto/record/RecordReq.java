package neordinary.oteam.dto.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import neordinary.oteam.dto.chatGpt.ChatGptReq;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordReq {
    String name;
    String content;
}
