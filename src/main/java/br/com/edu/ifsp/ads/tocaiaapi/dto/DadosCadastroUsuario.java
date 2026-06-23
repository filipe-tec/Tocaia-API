package br.com.edu.ifsp.ads.tocaiaapi.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank String cpf,
        @NotBlank String senha
) {
}