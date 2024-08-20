package cigma.pfe.Entity;

import cigma.pfe.Dto.ServiceDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;
    @Column(length = 65555)
    private String description;
    private Double price;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public ServiceDto getServiceDto(){
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setId(id);
        serviceDto.setServiceName(serviceName);
        serviceDto.setDescription(description);
        serviceDto.setPrice(price);
        serviceDto.setCompanyName(user.getName());
        serviceDto.setReturnedImg(img);  // Ensure this is set correctly

        return serviceDto;
    }
}
