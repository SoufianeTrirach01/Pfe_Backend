    package cigma.pfe.Dto;

    import cigma.pfe.Enums.ReservationStatus;
    import cigma.pfe.Enums.ReviewStatus;
    import lombok.Data;

    import java.util.Date;
    @Data

    public class ReservationDTO {
        private Long id;
        private ReservationStatus reservationStatus;
        private ReviewStatus reviewStatus;
        private Date dateReservation;
        private String serviceName;
        private Long userId;
        private String username;
        private Long companyId;

        private Long service_id;
    }