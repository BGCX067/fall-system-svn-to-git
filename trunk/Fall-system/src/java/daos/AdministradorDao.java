
package daos;

import entidades.Administrador;
import entidades.Usuario;

/**
 *
 * @author Alisson Renan
 */
public class AdministradorDao extends UsuarioDao<Administrador> {

    public AdministradorDao() {
        super(Administrador.class);
    }
    
    
}
