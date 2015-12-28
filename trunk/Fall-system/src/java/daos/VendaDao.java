/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entidades.Venda;
import java.util.List;

/**
 *
 * @author Alisson Renan
 */
public class VendaDao<T> extends Dao<T> {

    public VendaDao(Class classe) {
        super(classe);
    }

    public List<Venda> listByNome(String id) {
        return em.createQuery("SELECT e FROM " + Venda.class.getSimpleName() + " e WHERE e.id LIKE \"" + id + "%\"").getResultList();

    }
}
