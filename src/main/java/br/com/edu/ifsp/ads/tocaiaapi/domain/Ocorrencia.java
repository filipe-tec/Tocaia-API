package br.com.edu.ifsp.ads.tocaiaapi.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Ocorrencia")
@Table(name = "ocorrencias")
public class Ocorrencia {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoOcorrencia tipo;

    private String endereco;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusOcorrencia status;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Construtor vazio exigido pela JPA
    public Ocorrencia() {}

    // Construtor para registrar
    public Ocorrencia(TipoOcorrencia tipo, String endereco, String descricao, Usuario usuario) {
        this.tipo = tipo;
        this.endereco = endereco;
        this.descricao = descricao;
        this.status = StatusOcorrencia.ABERTA; // Toda ocorrência nasce aberta
        this.dataRegistro = LocalDateTime.now();
        this.usuario = usuario;
    }

    public void resolver() {
        this.status = StatusOcorrencia.RESOLVIDA;
    }

    // Getters
    public Long getId() { return id; }
    public TipoOcorrencia getTipo() { return tipo; }
    public String getEndereco() { return endereco; }
    public String getDescricao() { return descricao; }
    public StatusOcorrencia getStatus() { return status; }
    public LocalDateTime getDataRegistro() { return dataRegistro; }
    public Usuario getUsuario() { return usuario; }
}
