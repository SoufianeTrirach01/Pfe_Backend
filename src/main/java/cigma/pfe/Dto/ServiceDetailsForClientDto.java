package cigma.pfe.Dto;

import lombok.Data;

import java.util.List;

@Data
public class ServiceDetailsForClientDto {
    private  ServiceDto serviceDto;
    List<ReviewDto> reviewDtoList;
}
