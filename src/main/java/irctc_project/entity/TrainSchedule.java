package irctc_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "train_schedule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainSchedule
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private LocalDateTime runDate;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    private Integer avaiableSeats;

    //✅ we will mention future :

    // which types of seat are there :General / AC

    @OneToMany(mappedBy = "trainSchedule")
    private List<TrainSeat> trainSeats;

    //✅ Booking Handling :

    @OneToMany(mappedBy = "trainSchedule")
    private List<Booking> bookings;




}
