package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Alisson Renan
 */
@Entity
public class Venda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataVenda;
    @OneToOne
    private Usuario vendedor;
    @OneToOne
    private Pessoa cliente;

    
    @OneToMany
    private List<Produto> produtos;
    private boolean temPrestacaoServico;
    private double valorTotal;
    @OneToOne
    private FormaPagamento formaPagamento;
    

    public Venda() {
    }

    public Venda(Date dataVenda, Usuario vendedor, Pessoa cliente, ArrayList<Produto> produtos, boolean temPrestacaoServico, double valorTotal, FormaPagamento formaPagamento) {
        this.dataVenda = dataVenda;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.produtos = produtos;
        this.temPrestacaoServico = temPrestacaoServico;
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public boolean isTemPrestacaoServico() {
        return temPrestacaoServico;
    }

    public void setTemPrestacaoServico(boolean temPrestacaoServico) {
        this.temPrestacaoServico = temPrestacaoServico;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Venda[ id=" + id + " ]";
    }
    
}
