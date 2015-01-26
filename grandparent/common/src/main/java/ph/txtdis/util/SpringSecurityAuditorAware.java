package ph.txtdis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Users;
import ph.txtdis.repository.UserRepository;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<Users> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated())
            return userRepository.findOne("SYSGEN");
        return (Users) authentication.getPrincipal();
    }
}
