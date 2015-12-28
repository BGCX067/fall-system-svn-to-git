
package daos;

import entidades.ClientePessoaJuridica;
import entidades.Produto;
import java.util.List;

/**
 *
 * @author Alisson Renan
 */
public class ClientePessoaJuridicaDao extends PessoaFisicaDao<ClientePessoaJuridica> {

    public ClientePessoaJuridicaDao() {
        super(ClientePessoaJuridica.class);
    }
    
    public List<ClientePessoaJuridica> listByRazaoSocial(String nome) {
            return em.createQuery("SELECT e FROM " + ClientePessoaJuridica.class.getSimpleName() + " e WHERE e.razaoSocial LIKE \""+nome+"%\"").getResultList();

    }
    
}
