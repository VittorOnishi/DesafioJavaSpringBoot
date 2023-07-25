package br.com.crud.controle;

import br.com.crud.dominio.entidade.Cliente;
import br.com.crud.dominio.entidade.Contato;
import br.com.crud.dominio.record.DadosAlteracaoCliente;
import br.com.crud.dominio.record.DadosAlteracaoContato;
import br.com.crud.dominio.record.DadosCadastroClientes;
import br.com.crud.dominio.record.DadosCadastroContato;
import br.com.crud.dominio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository repository;
    @GetMapping("/formulario")
    public String carregaPaginaFormulario(Long id, Model model){
        if(id != null){
            var cliente = repository.getReferenceById(id);
            model.addAttribute("cliente", cliente);
        }
        return "clientes/formulario";
    }

    @GetMapping()
    public String carregaPaginaListagem(Model model, String nome){
        if(nome != null){
            model.addAttribute("listaClientes", repository.findByNome(nome));
            return "clientes/listagem";
        }
        model.addAttribute("listaClientes", repository.findAll());
        return "clientes/listagem";
    }
    @Transactional
    @PostMapping()
    public String cadastraCliente(DadosCadastroClientes dadosCliente, DadosCadastroContato dadosContato){
        var contato = new Contato(dadosContato);
        var cliente = new Cliente(dadosCliente, contato);
        contato.setCliente(cliente);
        repository.save(cliente);
        return "redirect:/clientes";
    }
    @DeleteMapping
    @Transactional
    public String removeCliente(Long id){
        repository.deleteById(id);
        return "redirect:/clientes";
    }
    @PutMapping
    @Transactional
    public String alteraCliente(DadosAlteracaoCliente dadosCliente, DadosAlteracaoContato dadosContato){
        var cliente = repository.getReferenceById(dadosCliente.id());
        cliente.atualizaDados(dadosCliente);
        cliente.getContato().atualizaDados(dadosContato);
        return "redirect:/clientes";
    }

}
