package cigma.pfe.Controller;

import cigma.pfe.Dto.ReservationDTO;
import cigma.pfe.Dto.ServiceDto;
import cigma.pfe.Repository.ReservationRepository;
import cigma.pfe.Service.Company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/company")

public class CompanyController {
    @Autowired
    private ICompanyService companyService;
    private ReservationRepository reservationRepository;

    public CompanyController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @PostMapping("/service/{userId}")
    public ResponseEntity<?> postService(@PathVariable Long userId, @ModelAttribute ServiceDto serviceDto) throws IOException {
        boolean success = companyService.PostService(userId, serviceDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
    @GetMapping("/services/{userId}")
    public ResponseEntity<?> getAllService(@PathVariable Long userId){
        List<ServiceDto> services = companyService.listService(userId);
            return ResponseEntity.ok(services);


    }
    @GetMapping("/service/{userId}")
    public ResponseEntity<?> getServiceById(@PathVariable Long userId){
        ServiceDto services = companyService.getServiceById(userId);
       if(services!=null) {
            return ResponseEntity.ok(services);
        }
       else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }


    }
    @PutMapping("/service/{serviceId}")
    public ResponseEntity<?> updateService(@PathVariable Long serviceId, @ModelAttribute ServiceDto serviceDto) throws IOException {
        boolean success = companyService.UpdateService(serviceId, serviceDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/service/{serviceId}")
    public ResponseEntity<?> deleteService(@PathVariable Long serviceId) throws IOException {
        boolean success = companyService.deleteService(serviceId);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/reservation/{companyId}")
    public ResponseEntity<List<ReservationDTO>> ReservationByCompanyId(@PathVariable Long companyId) {
        List<ReservationDTO> reservationDTO = companyService.ReservationByCompanyID(companyId);
        if (reservationDTO != null) {
            return ResponseEntity.ok(reservationDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("/reservation/{reservationId}/{status}")
    public ResponseEntity<?> changeReservationStatus(@PathVariable Long reservationId,@PathVariable String status) {
        boolean   reservationStatus = companyService.changeReservationStatus(reservationId,status);
        if (reservationStatus)
            return ResponseEntity.status(HttpStatus.OK).build();

        return ResponseEntity.notFound().build();
    }

}
