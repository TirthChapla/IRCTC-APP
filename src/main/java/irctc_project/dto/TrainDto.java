package irctc_project.dto;

import irctc_project.entity.Station;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainDto
{
    private Long id;

    @NotBlank(message = "Train number is required")
    private String number;

    @NotBlank(message = "Train name is required")
    private String name;

    @NotNull(message = "Total distance is required")
    @Min(value = 1, message = "Distance must be greater than 0")
    private Integer totalDistance;


    @NotNull(message = "Source station is required")
    private Station sourceStation;


    @NotNull(message = "Destination station is required")
    private Station destinationStation;

}
