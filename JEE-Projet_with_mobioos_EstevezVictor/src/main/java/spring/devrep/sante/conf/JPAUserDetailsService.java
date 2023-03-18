package spring.devrep.sante.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import spring.devrep.sante.entities.Pro;
import spring.devrep.sante.entities.ProDetails;
import spring.devrep.sante.repository.ProRepository;

@Service
public class JPAUserDetailsService implements UserDetailsService {

    @Autowired
    private ProRepository prorep;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        final Pro pro = prorep.findByMail(mail);
        if (pro == null) {
            throw new UsernameNotFoundException(mail);
        }
        return new ProDetails(pro);
    }

}
