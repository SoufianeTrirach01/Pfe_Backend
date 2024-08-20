package cigma.pfe.Controller;

import cigma.pfe.Dto.ReservationDTO;
import cigma.pfe.Dto.ReviewDto;
import cigma.pfe.Service.Client.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/services")
    public ResponseEntity<?> getAllServices() {
        return ResponseEntity.ok(clientService.getAllServices());
    }
    @GetMapping("/reviews")
    public ResponseEntity<?> getAllReviews() {
        return ResponseEntity.ok(clientService.getAllReviews());
    }

    @GetMapping("/services/{name}")
    public ResponseEntity<?> getAllServicesByName(@PathVariable String name) {
        return ResponseEntity.ok(clientService.getServiceByName(name));
    }


    @GetMapping("/service/{service_id}")
    public ResponseEntity<?> getServicesDetailsById(@PathVariable Long service_id) {
        return ResponseEntity.ok(clientService.getServiceDetailsById(service_id));

    }
    @PostMapping("/reserver")
    public ResponseEntity<?> ReserveServices(@RequestBody ReservationDTO reservationDTO) {
        // Log the received ReservationDTO
        System.out.println("Received ReservationDTO: " + reservationDTO);

        boolean success = clientService.createReservation(reservationDTO);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/reservation/{userId}")
    public ResponseEntity<List<ReservationDTO>> ReservationByCompanyId(@PathVariable Long userId) {
        List<ReservationDTO> reservationDTO = clientService.GetAllReservationByUserId(userId);
        if (reservationDTO != null) {
            return ResponseEntity.ok(reservationDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
    @PostMapping("/saveReview")
    public ResponseEntity<?> saveReview(@RequestBody ReviewDto reviewDto) {
        boolean success = clientService.saveReview(reviewDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
