
package daos;

import entidades.Administrador;
import entidades.Tecnico;
import entidades.Usuario;

/**
 *
 * @author Alisson Renan
 */
public class TecnicoDao extends UsuarioDao<Tecnico> {

    public TecnicoDao() {
        super(Tecnico.class);
    }
    
    
}
