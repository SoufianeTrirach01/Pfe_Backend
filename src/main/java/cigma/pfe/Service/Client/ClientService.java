package cigma.pfe.Service.Client;

import cigma.pfe.Dto.ReservationDTO;
import cigma.pfe.Dto.ReviewDto;
import cigma.pfe.Dto.ServiceDetailsForClientDto;
import cigma.pfe.Dto.ServiceDto;

import java.util.List;

public interface ClientService {
    List<ServiceDto> getAllServices();
    List<ServiceDto> getServiceByName(String  name);

    boolean createReservation(ReservationDTO reservationDTO);
    ServiceDetailsForClientDto getServiceDetailsById(Long service_id);
    List<ReservationDTO> GetAllReservationByUserId(Long userId);
    Boolean saveReview(ReviewDto reviewDto);
    List<ReviewDto> getAllReviews();
}
