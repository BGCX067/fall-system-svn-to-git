/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Alisson Renan
 */
@Entity
public abstract class Usuario extends PessoaFisica {
    
    private String rg;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;
    private String cargo;
    private float salario;
    private String login;
    private String senha;
    @ManyToOne
    private Empresa empresa;

    public Usuario() {
        super();
    }

       

    
    

    public Usuario( String rg, Date dataNascimento, String cargo, float salario, String login, String senha, String nome, String cpf, String cep, String estado, String logradouro, String numero, String complemento, String bairro, String telefone, String email) {
        super(nome, cpf, cep, estado, logradouro, numero, complemento, bairro, telefone, email);
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
        this.salario = salario;
        this.login = login;
        this.senha = senha;
    }

    

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
 }
