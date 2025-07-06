package irctc_project.entity;


import irctc_project.enums.CoachType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "train_seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//dibba
public class TrainSeat
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "train_schedule_id")
    private TrainSchedule trainSchedule;

    @Enumerated(EnumType.STRING)
    private CoachType coachType;

    private Integer totalSeats;

    //42-2=40=0
    private Integer availableSeats;

    private Double price;

    private Integer trainSeatOrder;

    //1+2=3.... >42
    private Integer seatNumberToAssign;

    public boolean isChochFull() {
        return availableSeats <= 0;
    }

    public boolean isSeatAvailable(int seatToBook) {
        return seatToBook <= availableSeats;
    }





}
