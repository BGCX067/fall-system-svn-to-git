package daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * @version 1.0;
 * Description: Esse DAO (Data Acess Object) é a
 * forma pelo qual acessa-se os dados do Banco de Dados. O DAO aqui apresentado
 * é genérico e funciona para qualquer objeto que possua um Entity. O DAO tem um
 * EntityManager, por padrão chamado de "UP". As operações CRUD são
 * implementadas aqui.
 *
 * Para se instanciar um Dao de um Objeto é preciso da classe do objeto. Ex:
 *
 * Dao<Objeto> = new Dao<Objeto>(Objeto.class);
 *
 * ATENÇÃO: Recomenda-se criar um DAO específico para cada entidade, para poder
 * fazer pesquisas e métodos de acesso ao dados específicos para cada entidade.
 *
 */
public class Dao<T> {

    protected EntityManager em = Persistence.createEntityManagerFactory("UP").createEntityManager();
    private Class classe;

    public Class getClasse() {
        return classe;
    }

    public Dao(Class classe) {
        this.classe = classe;
    }

    /* Description:
     * O método insert() serve para inserir um objeto no banco de dados
     */
    public void insert(T entidade) {
        em.getTransaction().begin();
        em.persist(entidade);
        em.flush();
        em.getTransaction().commit();
    }

    /* Description:
     * O método update() serve para editar um objeto no banco de dados
     */
    public void update(T entidade) {
        em.getTransaction().begin();
        em.merge(entidade);
        em.getTransaction().commit();
    }

    /* Description:
     * O método remove() serve para remover um objeto no banco de dados
     */
    public void remove(long id) {
        T entidade = get(id);
        if (entidade == null) {
            return;
        }
        em.getTransaction().begin();
        em.remove(entidade);
        em.getTransaction().commit();
        
    }

    /* Description:
     * O método get() serve para ler um objeto no banco de dados
     */
    public T get(long id) {
        return (T) em.find(classe, id);
        
    }


    /* Description:
     * O método list() serve para listar todos os elementos dessa entendida
     * no banco de dados
     */
    public List<T> list() {
        return em.createQuery("SELECT e FROM " + classe.getSimpleName() + " e ").getResultList();
        
    }
}
