package br.com.edu.ifsp.ads.tocaiaapi.repository;

import br.com.edu.ifsp.ads.tocaiaapi.domain.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
}