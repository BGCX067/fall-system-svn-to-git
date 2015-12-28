
package daos;

import entidades.ClientePessoaFisica;
import entidades.Produto;
import java.util.List;

/**
 *
 * @author Alisson Renan
 */
public class ClientePessoaFisicaDao extends PessoaFisicaDao<ClientePessoaFisica> {

    public ClientePessoaFisicaDao() {
        super(ClientePessoaFisica.class);
    }
    
    public List<ClientePessoaFisica> listByNome(String nome) {
            return em.createQuery("SELECT e FROM " + ClientePessoaFisica.class.getSimpleName() + " e WHERE e.nome LIKE \""+nome+"%\"").getResultList();

    }
    
}
