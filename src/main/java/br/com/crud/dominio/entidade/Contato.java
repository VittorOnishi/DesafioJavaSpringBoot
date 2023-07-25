package br.com.crud.dominio.entidade;

import br.com.crud.dominio.entidade.Cliente;
import br.com.crud.dominio.record.DadosAlteracaoContato;
import br.com.crud.dominio.record.DadosCadastroContato;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "contatos")
public class Contato{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cnt_id")
    private Long id;
    @Column(name = "cnt_tipo")
    private String tipo;
    @Column(name = "cnt_texto")
    private String texto;
    @OneToOne
    @JoinColumn(name = "clientes_cli_id", referencedColumnName = "cli_id")
    Cliente cliente;
    public Contato(DadosCadastroContato dados) {
        setTipo(dados.tipo());
        setTexto(dados.texto());
    }

    public Contato() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        Objects.requireNonNull(tipo, "O campo de tipo é obrigatorio");
        if(tipo.isEmpty()) {
            throw new IllegalArgumentException("O campo nome não pode ser vazio.");
        }
        this.tipo = tipo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        Objects.requireNonNull(texto, "O campo contato é obrigatorio");
        if(texto.isEmpty()) {
            throw new IllegalArgumentException("O campo nome não pode ser vazio.");
        }
        this.texto = texto;
    }

    public Cliente getCliente() {return cliente;}

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void atualizaDados(DadosAlteracaoContato dados) {
        setTipo(dados.tipo());
        setTexto(dados.texto());
    }
}
