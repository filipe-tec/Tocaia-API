package br.com.edu.ifsp.ads.tocaiaapi.dto;

import br.com.edu.ifsp.ads.tocaiaapi.domain.TipoOcorrencia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosRegistroOcorrencia(
        @NotNull TipoOcorrencia tipo,
        @NotBlank String endereco,
        String descricao
) {}