package cigma.pfe.Service.Company;

import cigma.pfe.Dto.ReservationDTO;
import cigma.pfe.Dto.ServiceDto;
import cigma.pfe.Entity.Reservation;
import cigma.pfe.Entity.Service;
import cigma.pfe.Entity.User;
import cigma.pfe.Enums.ReservationStatus;
import cigma.pfe.Repository.ReservationRepository;
import cigma.pfe.Repository.ServiceRepository;
import cigma.pfe.Repository.UserRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@org.springframework.stereotype.Service
public class CompanyServiceImpl implements ICompanyService {
    private UserRepository userRepository;
    private ServiceRepository serviceRepository;
    private ReservationRepository reservationRepository;

    public CompanyServiceImpl(UserRepository userRepository, ServiceRepository serviceRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.reservationRepository = reservationRepository;
    }
    public Boolean PostService(Long userId, ServiceDto serviceDto) throws IOException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            Service service = new Service();
            service.setDescription(serviceDto.getDescription());
           service.setServiceName(serviceDto.getServiceName());
            if (serviceDto.getImg() != null && !serviceDto.getImg().isEmpty()) {
                service.setImg(serviceDto.getImg().getBytes());  // Check if this is correctly set

            }
            service.setPrice(serviceDto.getPrice());
            service.setUser(userOptional.get());
            serviceRepository.save(service);


            return true;
        }
        return false;
    }
    public List<ServiceDto>listService(Long userId){
        return serviceRepository.findAllByUserId(userId).stream().map(Service::getServiceDto).collect(Collectors.toList());
    }
   /* public ServiceDto getServiceById(Long userId){
        Optional<Service> service=  serviceRepository.findById(userId);
        if (service.isPresent()){
            return service.get().getServiceDto();
        }
        return null;
    }*/
    public ServiceDto getServiceById(Long userId){
        Optional<Service> service=  serviceRepository.findById(userId);
        if (service.isPresent()){
            ServiceDto serviceDto = service.get().getServiceDto();
            System.out.println("Returned image bytes length: " + (serviceDto.getReturnedImg() != null ? serviceDto.getReturnedImg().length : "null"));
            return serviceDto;
        }
        return null;
    }
    public Boolean UpdateService(Long serviceId, ServiceDto serviceDto) throws IOException {
        Optional<Service> serviceOptional = serviceRepository.findById(serviceId);
        if (serviceOptional.isPresent()) {
            Service service =serviceOptional.get();
            service.setServiceName(serviceDto.getServiceName());
            service.setDescription(serviceDto.getDescription());
            if(serviceDto.getImg()!=null){
                service.setImg(serviceDto.getImg().getBytes());
            }
            service.setPrice(serviceDto.getPrice());
            serviceRepository.save(service);

            return true;
        }else {
            return false;
        }
    }
    public Boolean deleteService(Long serviceId) throws IOException {
        Optional<Service> serviceOptional = serviceRepository.findById(serviceId);
        if (serviceOptional.isPresent()) {
            serviceRepository.delete(serviceOptional.get());
            return true;
        }else {
            return false;
        }
    }
    public List<ReservationDTO>  ReservationByCompanyID(Long companyId){
        return reservationRepository.findAllByCompanyId(companyId).
                stream().
                map(Reservation::getReservationDto)
                .collect(Collectors.toList());
    }
    public boolean changeReservationStatus(@PathVariable Long reservationId, String status) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation.isPresent() ) {
            Reservation existinReservation=reservation.get();
            if(Objects.equals(status,"APPROUVER")){
                existinReservation.setReservationStatus(ReservationStatus.APPROUVER);
            }else {
                existinReservation.setReservationStatus(ReservationStatus.REJETER);
            }
            reservationRepository.save(existinReservation);
            return true;
        }
        return false;
    }


}

