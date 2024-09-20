package ageria.U5S7L5.controllers;

import ageria.U5S7L5.dto.NewUserDTO;
import ageria.U5S7L5.dto.UserDTO;
import ageria.U5S7L5.dto.UserLoginDTO;
import ageria.U5S7L5.dto.UserRespDTO;
import ageria.U5S7L5.exceptions.BadRequestException;
import ageria.U5S7L5.service.AuthService;
import ageria.U5S7L5.service.EventService;
import ageria.U5S7L5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    // POST login
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserRespDTO loginUser(@RequestBody UserLoginDTO body) {
        return new UserRespDTO(this.authService.checkCredentialAndGenerateToken(body));
    }

    // POST registration
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserDTO save(@RequestBody @Validated UserDTO body, BindingResult resultValidation) {
        if (resultValidation.hasErrors()) {
            String msg = resultValidation.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining());
            throw new BadRequestException(msg);
        } else {
            return new NewUserDTO(this.userService.saveUser(body).getId());
        }

    }


}
