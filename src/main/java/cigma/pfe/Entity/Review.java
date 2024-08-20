package cigma.pfe.Entity;

import cigma.pfe.Dto.ReviewDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Review {
    @Id @GeneratedValue
private Long id;
private Date reviewDate;
private String review;
private Long note;
    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Service service;

    public ReviewDto getReviewDto(){
        ReviewDto reviewDto=new ReviewDto();
        reviewDto.setId(id);
        reviewDto.setReview(review);
        reviewDto.setNote(note);
        reviewDto.setReviewDate(reviewDate);
        reviewDto.setUserId(user.getId());
        reviewDto.setClientName(user.getName());
        reviewDto.setServiceId(service.getId());
        reviewDto.setServiceName(service.getServiceName());

return reviewDto;
    }
}
