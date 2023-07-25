package br.com.crud.dominio.repository;


import br.com.crud.dominio.entidade.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM clientes cli INNER JOIN contatos con ON cli.cli_id = con.clientes_cli_id WHERE cli.cli_nome LIKE (:nome)")
    List<Cliente> findByNome(String nome);
}
