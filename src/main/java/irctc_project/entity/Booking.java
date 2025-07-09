package irctc_project.entity;

import irctc_project.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "booking")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "trainSchedule_id")
    private TrainSchedule trainSchedule;

    @ManyToOne
    @JoinColumn(name = "sourceStation_id")
    private Station sourceStation;

    @ManyToOne
    @JoinColumn(name = "destinationStation_id")
    private Station destinationStation;

    private LocalDate journeyDate;

    private Double totalFare;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "booking")
    private List<BookingPassenger> passengers;

    @OneToOne(mappedBy = "booking" , cascade = CascadeType.ALL)
    private Payment payment;
}
