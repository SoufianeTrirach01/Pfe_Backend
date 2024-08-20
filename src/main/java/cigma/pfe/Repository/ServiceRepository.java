package cigma.pfe.Repository;

import cigma.pfe.Entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
    List<Service> findAllByUserId(Long userId);
    List<Service> findAllByServiceNameContaining(String name);
}
