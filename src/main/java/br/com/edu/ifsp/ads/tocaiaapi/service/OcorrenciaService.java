package br.com.edu.ifsp.ads.tocaiaapi.service;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import br.com.edu.ifsp.ads.tocaiaapi.domain.Ocorrencia;
import br.com.edu.ifsp.ads.tocaiaapi.domain.StatusOcorrencia;
import br.com.edu.ifsp.ads.tocaiaapi.domain.Usuario;
import br.com.edu.ifsp.ads.tocaiaapi.dto.DadosDetalhamentoOcorrencia;
import br.com.edu.ifsp.ads.tocaiaapi.dto.DadosRegistroOcorrencia;
import br.com.edu.ifsp.ads.tocaiaapi.repository.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository repository;

    public DadosDetalhamentoOcorrencia registrar(DadosRegistroOcorrencia dados, Usuario autorLogado) {
        var ocorrencia = new Ocorrencia(dados.tipo(), dados.endereco(), dados.descricao(), autorLogado);
        repository.save(ocorrencia);
        return new DadosDetalhamentoOcorrencia(ocorrencia);
    }

    public DadosDetalhamentoOcorrencia darBaixa(Long id) {
        var ocorrencia = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada!"));

        if (ocorrencia.getStatus() == StatusOcorrencia.RESOLVIDA) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocorrência já foi encerrada anteriormente.");
        }

        ocorrencia.resolver();
        repository.save(ocorrencia);
        return new DadosDetalhamentoOcorrencia(ocorrencia);
    }
}
