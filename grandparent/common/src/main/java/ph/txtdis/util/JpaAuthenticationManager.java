package ph.txtdis.util;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Users;
import ph.txtdis.repository.RoleRepository;
import ph.txtdis.repository.UserRepository;

@NoArgsConstructor
@AllArgsConstructor
@Component
public class JpaAuthenticationManager implements AuthenticationManager {

    private ConfigurableApplicationContext context;

    private static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        UserRepository userRepository = context.getBean(UserRepository.class);
        Users user = userRepository.findOne(auth.getName());
        if (user != null && isValidated(auth.getCredentials(), user.getPassword()))
            return new UsernamePasswordAuthenticationToken(user, auth.getCredentials(), getRoles(user));
        throw new BadCredentialsException("Incorrect Username and/or Password");
    }

    private boolean isValidated(Object submitted, String retrieved) {
        return new BCryptPasswordEncoder().matches(submitted.toString(), retrieved);
    }

    private List<GrantedAuthority> getRoles(Users user) {
        RoleRepository roleRepository = context.getBean(RoleRepository.class);
        List<String> roles = roleRepository.getRoles(user);
        roles.forEach(role -> AUTHORITIES.add(new SimpleGrantedAuthority(role)));
        return AUTHORITIES;
    }
}
