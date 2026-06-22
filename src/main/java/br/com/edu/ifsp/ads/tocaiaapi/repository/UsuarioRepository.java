package br.com.edu.ifsp.ads.tocaiaapi.repository;

import br.com.edu.ifsp.ads.tocaiaapi.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCpf(String cpf);
}