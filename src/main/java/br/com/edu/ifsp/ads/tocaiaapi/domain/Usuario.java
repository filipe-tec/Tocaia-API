package br.com.edu.ifsp.ads.tocaiaapi.domain;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String senha;
    private String papel; // Ex: ROLE_CIDADAO, ROLE_POLICIAL

    // Construtores, Getters e Setters omitidos para brevidade (Gere-os na IDE)

    public Long getId() { return id; }
    public String getCpf() { return cpf; }
    public String getPapel() { return papel; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.papel));
    }

    @Override
    public String getPassword() { return senha; }

    @Override
    public String getUsername() { return cpf; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}