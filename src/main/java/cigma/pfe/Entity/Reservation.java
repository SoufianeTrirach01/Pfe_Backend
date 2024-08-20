package cigma.pfe.Entity;

import cigma.pfe.Dto.ReservationDTO;
import cigma.pfe.Enums.ReservationStatus;
import cigma.pfe.Enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ReservationStatus reservationStatus;
    private ReviewStatus reviewStatus;
    private Date dateReservation;

    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Service service ;

     public ReservationDTO getReservationDto() {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(id);
        dto.setServiceName(service.getServiceName());
        dto.setDateReservation(dateReservation);
        dto.setReservationStatus(reservationStatus);
        dto.setReviewStatus(reviewStatus);
        dto.setService_id(service.getId());
        dto.setCompanyId(company.getId());
        dto.setUsername(user.getName());

        return dto;
    }
}
