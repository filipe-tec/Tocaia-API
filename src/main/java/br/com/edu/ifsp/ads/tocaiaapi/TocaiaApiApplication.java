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
    public CommandLineRunner injetarUsuariosPoliciais(JdbcTemplate jdbcTemplate, PasswordEncoder encoder) {
        return args -> {
            // Verifica se já existem policiais para evitar erros de duplicidade no banco
            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM usuarios WHERE papel = 'ROLE_POLICIAL'", Integer.class);

            if (count != null && count < 2) {
                String senhaCriptografada = encoder.encode("123456");

                // Injeta dois policiais distintos
                injetarPolicial(jdbcTemplate, "11122233344", senhaCriptografada);
                injetarPolicial(jdbcTemplate, "55566677788", senhaCriptografada);

                System.out.println("🚨 SISTEMA: Dois usuários Policiais foram injetados com sucesso!");
            }
        };
    }

    private void injetarPolicial(JdbcTemplate jdbcTemplate, String cpf, String senha) {
        String sql = "INSERT INTO usuarios (cpf, senha, papel) VALUES (?, ?, 'ROLE_POLICIAL')";
        jdbcTemplate.update(sql, cpf, senha);
    }
}