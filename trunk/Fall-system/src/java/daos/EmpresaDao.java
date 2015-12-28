
package daos;

import entidades.ClientePessoaJuridica;
import entidades.Empresa;

/**
 *
 * @author Alisson Renan
 */
public class EmpresaDao extends PessoaJuridicaDao<Empresa> {

    public EmpresaDao() {
        super(Empresa.class);
    }
    
    
}
