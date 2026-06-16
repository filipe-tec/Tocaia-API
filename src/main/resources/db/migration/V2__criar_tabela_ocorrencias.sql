CREATE TABLE ocorrencias (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             tipo VARCHAR(50) NOT NULL,
                             endereco VARCHAR(255) NOT NULL,
                             descricao TEXT,
                             status VARCHAR(50) NOT NULL,
                             data_registro DATETIME NOT NULL,
                             usuario_id BIGINT NOT NULL,
                             CONSTRAINT fk_ocorrencia_usuario FOREIGN KEY(usuario_id) REFERENCES usuarios(id)
);