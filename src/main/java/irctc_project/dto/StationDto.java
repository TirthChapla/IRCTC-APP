package irctc_project.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StationDto {

    private Long id;

    @NotBlank(message = "Station code must not be blank")
    @Size(max = 10, message = "Station code must be at most 10 characters")
    private String code;

    @NotBlank(message = "Station name must not be blank")
    @Size(max = 100, message = "Station name must be at most 100 characters")
    private String name;

    @NotBlank(message = "City must not be blank")
    @Size(max = 50, message = "City must be at most 50 characters")
    private String city;

    @NotBlank(message = "State must not be blank")
    @Size(max = 50, message = "State must be at most 50 characters")
    private String state;

}
