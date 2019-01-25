package jk.config.security;

import jk.model.po.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private String username;
    private  String password;
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthoritys = new ArrayList<SimpleGrantedAuthority>();
        if(roles != null) {
            for(Role role : roles) {
                simpleGrantedAuthoritys.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return simpleGrantedAuthoritys;
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
