package ageria.U5S7L5.service;


import ageria.U5S7L5.dto.UserDTO;
import ageria.U5S7L5.entities.User;
import ageria.U5S7L5.exceptions.BadRequestException;
import ageria.U5S7L5.exceptions.NotFoundException;
import ageria.U5S7L5.repositories.UserRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    @Autowired
    UserRepositoriy userRepositoriy;

    @Autowired
    PasswordEncoder bcrypt;

    public User findById(Long id) {
        return this.userRepositoriy.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    // Saving user POST
    public User saveUser(@RequestBody UserDTO userDTO) {
        if (this.userRepositoriy.existsByEmail(userDTO.email())) {
            throw new BadRequestException("This email already exists");
        }
        User user = new User(
                userDTO.name(),
                userDTO.surname(),
                userDTO.email(),
                bcrypt.encode(userDTO.password())
        );

        return this.userRepositoriy.save(user);
    }
}
