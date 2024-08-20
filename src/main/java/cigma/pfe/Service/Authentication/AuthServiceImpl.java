package cigma.pfe.Service.Authentication;

import cigma.pfe.Dto.SignUpRequestDto;
import cigma.pfe.Dto.UserDto;
import cigma.pfe.Entity.User;
import cigma.pfe.Enums.UserRole;
import cigma.pfe.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserDto SignUpClient(SignUpRequestDto signUpRequestDto){
        User user = new User();
        user.setName(signUpRequestDto.getName());
        user.setLastname(signUpRequestDto.getLastname());
        user.setEmail(signUpRequestDto.getEmail());
        user.setPhone(signUpRequestDto.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequestDto.getPassword()));
        user.setRole(UserRole.CLIENT);
        return userRepository.save(user).getDto();

    }
    @Override
    public UserDto SignUpCompany(SignUpRequestDto signUpRequestDto) {
        User user=new User();
        user.setName(signUpRequestDto.getName());
        user.setEmail(signUpRequestDto.getEmail());
        user.setPhone(signUpRequestDto.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequestDto.getPassword()));
        user.setRole(UserRole.COMPANY);
        return  userRepository.save(user).getDto();    }

    public  Boolean presentByEmail(String email){
        return userRepository.findFirstByEmail(email)!=null;
    }


}
