package spring.devrep.sante.entities;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ProDetails implements UserDetails {
    private Pro pro;

    public ProDetails(Pro pro) {
        this.pro = pro;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority;
        if (pro.visitor) {
            authority = new SimpleGrantedAuthority((String) "VISITOR");
        } else {
            authority = new SimpleGrantedAuthority((String) "PRO");
        }
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return pro.getMdp();
    }

    @Override
    public String getUsername() {
        return pro.getMail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
