package br.com.edu.ifsp.ads.tocaiaapi.dto;

import br.com.edu.ifsp.ads.tocaiaapi.domain.Ocorrencia;
import br.com.edu.ifsp.ads.tocaiaapi.domain.StatusOcorrencia;
import br.com.edu.ifsp.ads.tocaiaapi.domain.TipoOcorrencia;
import java.time.LocalDateTime;

public record DadosDetalhamentoOcorrencia(
        Long id, TipoOcorrencia tipo, String endereco, String descricao,
        StatusOcorrencia status, LocalDateTime dataRegistro, String cpfAutor) {

    public DadosDetalhamentoOcorrencia(Ocorrencia ocorrencia) {
        this(ocorrencia.getId(), ocorrencia.getTipo(), ocorrencia.getEndereco(),
                ocorrencia.getDescricao(), ocorrencia.getStatus(),
                ocorrencia.getDataRegistro(), ocorrencia.getUsuario().getCpf());
    }
}
