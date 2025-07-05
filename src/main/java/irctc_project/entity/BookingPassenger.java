package irctc_project.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booking_passenger")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingPassenger
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private String name;

    private Integer age;

    private String seatNumber;

}
