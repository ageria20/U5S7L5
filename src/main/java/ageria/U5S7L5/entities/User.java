package ageria.U5S7L5.entities;


import ageria.U5S7L5.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"password", "role", "authorities", "enabled", "accountNonLocked", "credentialsNonExpired", "accountNonExpired"})
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String surname;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = Role.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
