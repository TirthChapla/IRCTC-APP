package irctc_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "train_route")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainRoute
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    private Integer stationorder;

    private LocalTime arrivalTime;

    private LocalTime departureTime;

    private Integer haltMinutes;

    private  Integer distanceFromSource;
}
