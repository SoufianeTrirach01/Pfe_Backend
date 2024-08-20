package cigma.pfe.Controller;


import cigma.pfe.Dto.AuthenticationRequest;
import cigma.pfe.Dto.SignUpRequestDto;
import cigma.pfe.Dto.UserDto;
import cigma.pfe.Entity.User;
import cigma.pfe.Repository.UserRepository;
import cigma.pfe.Service.Authentication.AuthService;
import cigma.pfe.Service.Authentication.Jwt.UserDetailServiceImpl;
import cigma.pfe.Util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));


            final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getEmail());
            final String jwt = jwtUtil.generateJwtToken(userDetails.getUsername());
            User user = userRepository.findFirstByEmail(authenticationRequest.getEmail());

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("userId", user.getId());
            jsonResponse.put("role", user.getRole());

            response.addHeader("Access-Control-Expose-Headers", "Authorization");
            response.addHeader("Authorization", "Bearer " + jwt);

            return ResponseEntity.ok().body(jsonResponse.toString());
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect email or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }
    @PostMapping("/client/sign-up")
    public ResponseEntity<?> saveClient(@RequestBody SignUpRequestDto signUpRequestDto) {
        if (authService.presentByEmail(signUpRequestDto.getEmail())) {
            return new ResponseEntity<>("Client already exists with this email", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdUser = authService.SignUpClient(signUpRequestDto);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

        @PostMapping("/company/sign-up")
    public ResponseEntity<?> saveCompany(@RequestBody SignUpRequestDto signUpRequestDto) {
       if (authService.presentByEmail(signUpRequestDto.getEmail())) {
           return new ResponseEntity<>("Entreprise deja exist avec ce mail", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdUser = authService.SignUpCompany(signUpRequestDto);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }
  /*  @PostMapping("/authenticate")
    public void createAuthToken(@RequestBody AuthenticationRequest authenticationRequest,
                                HttpServletResponse response) throws IOException, JSONException {
      try{  authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken
                (authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));
      }catch (BadCredentialsException e){
          throw new BadCredentialsException("Incorrect username and password ",e);

      }
      // get user details
      final UserDetails userDetails=userDetailService.loadUserByUsername(authenticationRequest.getUsername());

      final String jwt=jwtUtil.generateJwtToken(userDetails.getUsername());
      // return role & id
      User user=userRepository.findFirstByEmail(authenticationRequest.getUsername());
        response.getWriter().write(
                new JSONObject()
                        .put("userId", user.getId())
                        .put("role", user.getRole())
                        .toString()
        );

        //export header for we can angular access to header
        response.addHeader("Access-Control-Expose-Headers", HEADER_STRING);
        response.addHeader("Access-Control-Allow-Headers", "Authorization,"
                + "X-PINGOTHER,Origin,X-Requested-With,Content-Type,X-Custom-header");
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
    }
*/
    }