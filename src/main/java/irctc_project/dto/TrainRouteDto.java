package irctc_project.dto;

import irctc_project.entity.Station;
import irctc_project.entity.Train;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainRouteDto
{
    private Long id;

    @NotNull(message = "Train id is required")
    private Long train_id;

    @NotNull(message = "Station id is required")
    private Long station_id;

    @NotNull(message = "Station order is required")
    @Min(value = 1, message = "Station order must be at least 1")
    private Integer stationorder;

    @NotNull(message = "Arrival time is required")
    private LocalTime arrivalTime;

    @NotNull(message = "Departure time is required")
    private LocalTime departureTime;

    @NotNull(message = "Halt minutes is required")
    @Min(value = 0, message = "Halt minutes must be 0 or greater")
    private Integer haltMinutes;

    @NotNull(message = "Distance from source is required")
    @Min(value = 0, message = "Distance must be 0 or greater")
    private Integer distanceFromSource;

}
