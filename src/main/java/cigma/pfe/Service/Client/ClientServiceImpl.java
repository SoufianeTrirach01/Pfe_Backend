package cigma.pfe.Service.Client;

import cigma.pfe.Dto.ReservationDTO;
import cigma.pfe.Dto.ReviewDto;
import cigma.pfe.Dto.ServiceDetailsForClientDto;
import cigma.pfe.Dto.ServiceDto;
import cigma.pfe.Entity.Reservation;
import cigma.pfe.Entity.Review;
import cigma.pfe.Entity.Service;
import cigma.pfe.Entity.User;
import cigma.pfe.Enums.ReservationStatus;
import cigma.pfe.Enums.ReviewStatus;
import cigma.pfe.Repository.ReservationRepository;
import cigma.pfe.Repository.ReviewRepository;
import cigma.pfe.Repository.ServiceRepository;
import cigma.pfe.Repository.UserRepository;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ClientServiceImpl implements ClientService {
    // Add a logger

    private ServiceRepository serviceRepository;
    private UserRepository userRepository;
    private ReservationRepository reservationRepository;
    private ReviewRepository reviewRepository;

    public ClientServiceImpl(ServiceRepository serviceRepository, UserRepository userRepository, ReservationRepository reservationRepository, ReviewRepository reviewRepository) {
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<ServiceDto> getAllServices() {
        return serviceRepository.findAll().stream().map(cigma.pfe.Entity.Service::getServiceDto).collect(Collectors.toList());
    }

    @Override
    public List<ServiceDto> getServiceByName(String name) {
        return serviceRepository.findAllByServiceNameContaining(name).stream().map(Service::getServiceDto).collect(Collectors.toList());
    }
/*
    public Boolean ReservationService(ReservationDTO reservationDTO) {
        Optional<Service> serviceOptional = serviceRepository.findById(reservationDTO.getService_id());
        Optional<User> optionalUser = userRepository.findById(reservationDTO.getUserId());
        if (optionalUser.isPresent() && serviceOptional.isPresent()) {
            // CREATE RESERVATION
            Reservation reservation = new Reservation();
            reservation.setDateReservation(reservationDTO.getDateReservation());
            reservation.setReservationStatus(ReservationStatus.ENATTENTE);
            reservation.setDateReservation(reservationDTO.getDateReservation());
            reservation.setUser(optionalUser.get());
            reservation.setService(serviceOptional.get());
            // set the company of this service
            reservation.setCompany(serviceOptional.get().getUser());
            reservation.setReviewStatus(ReviewStatus.FAUX);
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }*/
/*public boolean createReservation(ReservationDTO reservationDTO) {
    Optional<Service> serviceOptional = serviceRepository.findById(reservationDTO.getServiceId());
    Optional<User> optionalUser = userRepository.findById(reservationDTO.getUserId());



    if (serviceOptional.isPresent() && optionalUser.isPresent()) {
        // CREATE RESERVATION
        Reservation reservation = new Reservation();
        reservation.setDateReservation(reservationDTO.getDateReservation());
        reservation.setReservationStatus(ReservationStatus.ENATTENTE);
        reservation.setUser(optionalUser.get());
        reservation.setService(serviceOptional.get());

        // Set the company of this service
        reservation.setCompany(serviceOptional.get().getUser());

        reservation.setReviewStatus(ReviewStatus.FAUX);
        reservationRepository.save(reservation);
        return true;
    }
    return false;
}*/

    public ServiceDetailsForClientDto getServiceDetailsById(Long service_id) {
        Optional<Service> ServiceById = serviceRepository.findById(service_id);
        ServiceDetailsForClientDto serviceDetailsForClientDto = new ServiceDetailsForClientDto();

        if (ServiceById.isPresent()) {
            serviceDetailsForClientDto.setServiceDto(ServiceById.get().getServiceDto());
            List<Review> reviews=reviewRepository.findAllByServiceId(service_id);
            serviceDetailsForClientDto.setReviewDtoList(reviews.stream().map(Review::getReviewDto).collect(Collectors.toList()));
        }
        return serviceDetailsForClientDto;
    }

    @Override
    public List<ReservationDTO> GetAllReservationByUserId(Long userId) {
        return reservationRepository.findAllByUserId(userId).
                stream().
                map(Reservation::getReservationDto)
                .collect(Collectors.toList());
    }
    public boolean createReservation(ReservationDTO reservationDTO) {
        if (reservationDTO.getService_id() == null || reservationDTO.getUserId() == null) {
            throw new IllegalArgumentException("Service ID and User ID must not be null");
        }

        Optional<Service> serviceOptional = serviceRepository.findById(reservationDTO.getService_id());
        Optional<User> optionalUser = userRepository.findById(reservationDTO.getUserId());

        if (serviceOptional.isPresent() && optionalUser.isPresent()) {
            Reservation reservation = new Reservation();
            reservation.setDateReservation(reservationDTO.getDateReservation());
            reservation.setReservationStatus(ReservationStatus.ENATTENTE);
            reservation.setUser(optionalUser.get());
            reservation.setService(serviceOptional.get());
            reservation.setCompany(serviceOptional.get().getUser());
            reservation.setReviewStatus(ReviewStatus.FAUX);
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }
    public Boolean saveReview(ReviewDto reviewDto){
        Optional<User> optionalUser= userRepository.findById(reviewDto.getUserId());
        Optional<Reservation> reservation= reservationRepository.findById(reviewDto.getReservationId());
        if(optionalUser.isPresent() && reservation.isPresent()){
            Review review=new Review();
            review.setReviewDate(new Date());
            review.setReview(reviewDto.getReview());
            review.setNote(reviewDto.getNote());

            review.setUser(optionalUser.get());
            review.setService(reservation.get().getService());

            reviewRepository.save(review);
            //update review Status
            Reservation reservation1=reservation.get();
            reservation1.setReviewStatus(ReviewStatus.VRAI);
            reservationRepository.save(reservation1);
            return true;
        }return false;
    }
    @Override
   public List<ReviewDto> getAllReviews(){
        return reviewRepository.findAll().stream().map(Review::getReviewDto).collect(Collectors.toList());
    }
}


