package cigma.pfe.Service.Company;

import cigma.pfe.Dto.ReservationDTO;
import cigma.pfe.Dto.ServiceDto;
import cigma.pfe.Entity.Reservation;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

public interface ICompanyService {
    Boolean PostService(Long userId, ServiceDto serviceDto) throws IOException;
    List<ServiceDto> listService(Long userId);
    ServiceDto getServiceById(Long userId);
    Boolean UpdateService(Long serviceId, ServiceDto serviceDto)throws IOException;
    Boolean deleteService(Long serviceId) throws IOException;
    List<ReservationDTO>  ReservationByCompanyID(Long companyId);
    boolean changeReservationStatus(@PathVariable Long reservationId, String status);


}
