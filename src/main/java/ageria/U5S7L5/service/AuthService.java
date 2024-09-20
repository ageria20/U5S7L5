package ageria.U5S7L5.service;

import ageria.U5S7L5.dto.UserLoginDTO;
import ageria.U5S7L5.entities.User;
import ageria.U5S7L5.exceptions.UnauthorizedException;
import ageria.U5S7L5.repositories.UserRepositoriy;
import ageria.U5S7L5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    JWTTools jwtTools;

    @Autowired
    UserRepositoriy userRepositoriy;

    @Autowired
    PasswordEncoder bcrypt;


    public String checkCredentialAndGenerateToken(UserLoginDTO body) {
        User userfromDB = this.userRepositoriy.findByEmail(body.email());
        if (bcrypt.matches(body.password(), userfromDB.getPassword())) {
            return jwtTools.createToken(userfromDB);
        } else {
            throw new UnauthorizedException("CREDENTIAL ARE NOT VALID");
        }
    }
}
