package cigma.pfe.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDto {
    private Long id;
    private Date reviewDate;
    private String review;
    private Long note;
    private Long userId;
    private Long  serviceId;
    private String clientName;
    private String  serviceName;
    private Long reservationId;
}
