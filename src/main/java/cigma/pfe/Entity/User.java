package cigma.pfe.Entity;

import cigma.pfe.Dto.UserDto;
import cigma.pfe.Enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;

    @Enumerated(EnumType.ORDINAL)
    private UserRole role;


    public UserDto getDto() {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName(name);
        userDto.setLastname(lastname);
        userDto.setEmail(email);
        userDto.setPhone(phone);
        userDto.setRole(role);
        // userDto.setPassword(password); // Do not set password in DTO for security:
        return userDto;
    }
}