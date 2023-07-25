package br.com.crud.dominio.entidade;

import br.com.crud.dominio.record.DadosAlteracaoCliente;
import br.com.crud.dominio.record.DadosCadastroClientes;
import jakarta.persistence.*;
import java.util.Objects;
import org.springframework.util.StringUtils;

import java.time.LocalDate;


@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_id")
    private Long id;
    @Column(name = "cli_nome")
    private String nome;
    @Column(name = "cli_datacad")
    private LocalDate dataCadastro;
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Contato contato;

    public Cliente(DadosCadastroClientes dados, Contato contato) {
        setNome(dados.nome());
        setDataCadastro();
        setContato(contato);
    }


    public Cliente() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        Objects.requireNonNull(nome, "O campo nome é obrigatório");
        if(nome.isEmpty()) {
            throw new IllegalArgumentException("O campo nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro() {this.dataCadastro = LocalDate.now();}

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public void atualizaDados(DadosAlteracaoCliente dados) {
        setNome(dados.nome());
        setDataCadastro();
    }

}
