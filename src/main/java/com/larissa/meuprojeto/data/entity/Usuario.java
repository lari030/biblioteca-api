package com.larissa.meuprojeto.data.entity;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table (name = "usuario")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "id")
public class Usuario implements UserDetails{

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
    private Long id;
   @Column(nullable = false, length = 100)
    private String login;
   @Column(nullable = false, length = 100)
    private String password;
   @Column(nullable = false, length = 5)
    private UsuarioRole role;

    public Usuario (String login, String password, UsuarioRole role){
        this.login = login;
        this.password= password;
        this.role = role;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       if(this.role == UsuarioRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getUsername() {
      return login;
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
