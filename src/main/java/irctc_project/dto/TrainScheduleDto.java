package irctc_project.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainScheduleDto
{
    private Long id;

    @NotNull(message = "Run date is required")
    private LocalDateTime runDate;

    @NotNull(message = "Train Id cannot be null")
    private Long trainid;

    @NotNull(message = "Available seats must be specified")
    @Positive(message = "Available seats must be positive")
    private Integer avaiableSeats;


}
