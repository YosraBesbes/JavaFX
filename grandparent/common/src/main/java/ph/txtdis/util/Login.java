package ph.txtdis.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ph.txtdis.model.Users;

@NoArgsConstructor
@AllArgsConstructor
public class Login {

    private ConfigurableApplicationContext context;

    public void validate(String username, String password) {
        AuthenticationManager authenticationManager = new JpaAuthenticationManager(context);
        Authentication authToken = new UsernamePasswordAuthenticationToken(username, password);
        authToken = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    public static Users user() {
        return (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
