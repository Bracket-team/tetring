package bracket.tetring.domain.game.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RelicNumberDto {
    @Min(value = 1, message = "유물 번호는 1 이상이어야 합니다.")
    @Max(value = 30, message = "유물 번호는 30 이하여야 합니다.")
    private Integer relicNumber;
}
