package cigma.pfe.Dto;

import cigma.pfe.Enums.UserRole;
import lombok.Data;

@Data
public class UserDto{
    private long id;
        private String email;
        private String name;
        private String lastname;
        private String phone;
        private UserRole role;


}
