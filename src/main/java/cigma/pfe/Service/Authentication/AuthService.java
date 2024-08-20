package cigma.pfe.Service.Authentication;

import cigma.pfe.Dto.SignUpRequestDto;
import cigma.pfe.Dto.UserDto;

public interface AuthService {
    public UserDto SignUpClient(SignUpRequestDto userDto);
    public UserDto SignUpCompany(SignUpRequestDto userDto);
    Boolean presentByEmail(String email);
}
