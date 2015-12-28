
package daos;

import entidades.ClientePessoaJuridica;
import entidades.Fornecedor;

/**
 *
 * @author Alisson Renan
 */
public class FornecedorDao extends PessoaFisicaDao<Fornecedor> {

    public FornecedorDao() {
        super(Fornecedor.class);
    }
    
    
}
