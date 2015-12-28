
package entidades;

import java.util.List;
import javax.persistence.Entity;import javax.persistence.ManyToMany;
;

/**
 *
 * @author Alisson Renan
 */
@Entity
public class ClientePessoaFisica extends PessoaFisica {
    @ManyToMany
    private List<Empresa> empresas;

    public ClientePessoaFisica() {
        super();
    }

    public ClientePessoaFisica(String nome, String cpf, String cep, String estado, String logradouro, String numero, String complemento, String bairro, String telefone, String email) {
        super(nome, cpf, cep, estado, logradouro, numero, complemento, bairro, telefone, email);
    }
    
    
    
}
