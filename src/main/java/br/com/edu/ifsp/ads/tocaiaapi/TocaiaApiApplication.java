package br.com.edu.ifsp.ads.tocaiaapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TocaiaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TocaiaApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner injetarUsuarioDeTeste(JdbcTemplate jdbcTemplate, PasswordEncoder encoder) {
        return args -> {
            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM usuarios", Integer.class);

            if (count != null && count == 0) {
                // Criptografa a senha na hora, eliminando falhas de cópia do hash
                String senhaCriptografada = encoder.encode("123456");

                String sql = "INSERT INTO usuarios (cpf, senha, papel) VALUES ('11122233344', '" + senhaCriptografada + "', 'ROLE_POLICIAL');";
                jdbcTemplate.execute(sql);
                System.out.println("🚨 SISTEMA: Usuário Policial (CPF: 11122233344, Senha: 123456) injetado com sucesso para testes!");
            }
        };
    }
}