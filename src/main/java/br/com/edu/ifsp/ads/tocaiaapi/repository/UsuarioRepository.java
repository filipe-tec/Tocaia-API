package br.com.edu.ifsp.ads.tocaiaapi.repository;

import br.com.edu.ifsp.ads.tocaiaapi.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByCpf(String cpf);
}