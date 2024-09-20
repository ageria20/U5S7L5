package ageria.U5S7L5.controllers;


import ageria.U5S7L5.entities.User;
import ageria.U5S7L5.service.BookingService;
import ageria.U5S7L5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BookingService bookingService;


    // 1. GET access only the ADMIN
    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> findAll(@RequestParam(defaultValue = "0") int pages,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String sortBy) {
        return this.userService.getAllUsers(pages, size, sortBy);
    }

    // GET ME endpoint
    @GetMapping("/me")
    public User updateEmployeeProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        return (User) this.bookingService.getBookings((currentAuthenticatedUser.getId()));
    }


    @DeleteMapping("/me")
    public void deleteEmployeeProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        this.userService.deleteUser(currentAuthenticatedUser.getId());
    }
}
