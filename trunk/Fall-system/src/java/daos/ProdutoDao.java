/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entidades.Produto;
import java.util.List;

/**
 *
 * @author Alisson Renan
 */
public class ProdutoDao<T> extends Dao<T> {

    public ProdutoDao(Class classe) {
        super(classe);
    }

    public List<Produto> listByNome(String nome) {
            return em.createQuery("SELECT e FROM " + Produto.class.getSimpleName() + " e WHERE e.nome LIKE \""+nome+"%\"").getResultList();

    }
}
