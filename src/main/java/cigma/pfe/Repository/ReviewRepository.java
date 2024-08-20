package cigma.pfe.Repository;

import cigma.pfe.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByServiceId(Long serviceId);
}
