package br.com.edu.ifsp.ads.tocaiaapi.controller;

import br.com.edu.ifsp.ads.tocaiaapi.domain.Usuario;
import br.com.edu.ifsp.ads.tocaiaapi.dto.DadosCadastroUsuario;
import br.com.edu.ifsp.ads.tocaiaapi.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DadosCadastroUsuario dados) {
        // Verifica se o CPF já existe antes de tentar salvar
        if (repository.findByCpf(dados.cpf()) != null) {
            return ResponseEntity.badRequest().body("Erro: CPF já cadastrado no sistema.");
        }

        var senhaCriptografada = passwordEncoder.encode(dados.senha());
        var usuario = new Usuario(dados.cpf(), senhaCriptografada, "ROLE_CIDADAO");
        repository.save(usuario);

        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }

    @PostMapping("/policial")
    @Transactional
    @PreAuthorize("hasRole('ROLE_POLICIAL')") // Apenas policiais podem executar isso
    public ResponseEntity registrarPolicial(@RequestBody @Valid DadosCadastroUsuario dados) {
        if (repository.findByCpf(dados.cpf()) != null) {
            return ResponseEntity.badRequest().body("Erro: CPF já cadastrado.");
        }

        var senhaCriptografada = passwordEncoder.encode(dados.senha());
        // Aqui forçamos a ROLE_POLICIAL
        var policial = new Usuario(dados.cpf(), senhaCriptografada, "ROLE_POLICIAL");

        repository.save(policial);

        return ResponseEntity.ok("Policial cadastrado com sucesso!");
    }
}