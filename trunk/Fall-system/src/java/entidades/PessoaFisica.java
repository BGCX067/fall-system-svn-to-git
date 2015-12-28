/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import javax.persistence.Entity;

/**
 *
 * @author Alisson Renan
 */
@Entity
public abstract class PessoaFisica extends Pessoa {
    
    private String nome;
    private String cpf;

    public PessoaFisica() {
       
    }

    public PessoaFisica(String nome, String cpf, String cep, String estado, String logradouro, String numero, String complemento, String bairro, String telefone, String email) {
        super(cep, estado, logradouro, numero, complemento, bairro, telefone, email);
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
    
    

    
    
    
    
    
    
}
