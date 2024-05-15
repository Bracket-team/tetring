package bracket.tetring.domain.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SlotNumberDto {

    @JsonProperty("slot_number")
    @Min(value = 1, message = "슬롯 넘버는 1 이상이어야 합니다.")
    @Max(value = 5, message = "슬롯 넘버는 5 이하여야 합니다.")
    private Integer slotNumber;
}
