package irctc_project.entity;

import irctc_project.enums.CoachType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "train_seat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainSeat
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "trainSchedule_id")
    private TrainSchedule trainSchedule;

    @Enumerated(EnumType.STRING)
    private CoachType coachType;

    private Integer totalSeats;

    private Integer avaiableSeats;

    // nextToAssign + no of bookedSeat <= total seats
    private Integer nextToAssign=1;

    private BigDecimal price;
}
