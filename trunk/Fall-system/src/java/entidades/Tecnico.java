/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
/**
 *
 * @author Alisson Renan
 */
@Entity
public class Tecnico  extends Usuario{
    
    private List<String> especialidades;

    public Tecnico() {
        super();
    }

    public Tecnico(List<String> especialidades, String rg, Date dataNascimento, String cargo, float salario, String login, String senha, String nome, String cpf, String cep, String estado, String logradouro, String numero, String complemento, String bairro, String telefone, String email) {
        super(rg, dataNascimento, cargo, salario, login, senha, nome, cpf, cep, estado, logradouro, numero, complemento, bairro, telefone, email);
        this.especialidades = especialidades;
    }

    public List<String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<String> especialidades) {
        this.especialidades = especialidades;
    }
    
    
    
    
    
}
