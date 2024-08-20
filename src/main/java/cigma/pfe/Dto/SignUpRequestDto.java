package cigma.pfe.Dto;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private long id;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;

}
