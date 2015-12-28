
package entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author Alisson Renan
 */
@Entity
public class ClientePessoaJuridica extends PessoaJuridica {
    @ManyToMany
    private List<Empresa> empresas;

    public ClientePessoaJuridica() {
        super();
    }

    public ClientePessoaJuridica(String razaoSocial, String cnpj, String cep, String estado, String logradouro, String numero, String complemento, String bairro, String telefone, String email) {
        super(razaoSocial, cnpj, cep, estado, logradouro, numero, complemento, bairro, telefone, email);
    }
    
    
    
}
