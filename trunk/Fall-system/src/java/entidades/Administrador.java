/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;
import javax.persistence.Entity;
/**
 *
 * @author Alisson Renan
 */
@Entity
public class Administrador  extends Usuario{

    public Administrador() {
        super();
    }

    public Administrador(String rg, Date dataNascimento, String cargo, float salario, String login, String senha, String nome, String cpf, String cep, String estado, String logradouro, String numero, String complemento, String bairro, String telefone, String email) {
        super(rg, dataNascimento, cargo, salario, login, senha, nome, cpf, cep, estado, logradouro, numero, complemento, bairro, telefone, email);
    }
    
    
    
}
