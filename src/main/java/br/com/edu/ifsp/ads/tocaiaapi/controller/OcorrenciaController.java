package br.com.edu.ifsp.ads.tocaiaapi.controller;

import br.com.edu.ifsp.ads.tocaiaapi.domain.Usuario;
import br.com.edu.ifsp.ads.tocaiaapi.dto.DadosDetalhamentoOcorrencia;
import br.com.edu.ifsp.ads.tocaiaapi.dto.DadosRegistroOcorrencia;
import br.com.edu.ifsp.ads.tocaiaapi.repository.OcorrenciaRepository;
import br.com.edu.ifsp.ads.tocaiaapi.service.OcorrenciaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService service;

    @Autowired
    private OcorrenciaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoOcorrencia> registrar(
            @RequestBody @Valid DadosRegistroOcorrencia dados,
            @AuthenticationPrincipal Usuario usuarioLogado,
            UriComponentsBuilder uriBuilder) {

        var detalhamento = service.registrar(dados, usuarioLogado);
        var uri = uriBuilder.path("/ocorrencias/{id}").buildAndExpand(detalhamento.id()).toUri();

        return ResponseEntity.created(uri).body(detalhamento);
    }

    @PutMapping("/{id}/resolucao")
    @Transactional
    @PreAuthorize("hasRole('ROLE_POLICIAL')") // Garante que só um Policial acesse esta rota
    public ResponseEntity<DadosDetalhamentoOcorrencia> darBaixa(@PathVariable Long id) {
        var detalhamento = service.darBaixa(id);
        return ResponseEntity.ok(detalhamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoOcorrencia> detalhar(@PathVariable Long id) {
        var ocorrencia = repository.findById(id);

        // Verifica se a ocorrência existe no banco de dados
        if (ocorrencia.isPresent()) {
            return ResponseEntity.ok(new DadosDetalhamentoOcorrencia(ocorrencia.get()));
        }

        // Se digitarem um ID que não existe, devolve erro 404
        return ResponseEntity.notFound().build();
    }
}