
package entidades;

import java.io.Serializable;
import java.sql.Time;
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
public class PrestacaoServico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataPrestacao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date horarioInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date horarioTermino;
    @OneToOne
    private Tecnico tecnico;    
    @OneToOne
    private Pessoa cliente;
    @OneToMany
    private List<Produto> produtos;
    @OneToOne
    private Venda venda;

    public PrestacaoServico() {
    }

    public PrestacaoServico(Date dataPrestacao, Date horarioInicio, Date horarioTermino, Tecnico tecnico, Pessoa cliente, ArrayList<Produto> produtos, Venda venda) {
        this.dataPrestacao = dataPrestacao;
        this.horarioInicio = horarioInicio;
        this.horarioTermino = horarioTermino;
        this.tecnico = tecnico;
        this.cliente = cliente;
        this.produtos = produtos;
        this.venda = venda;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    
    public Date getDataPrestacao() {
        return dataPrestacao;
    }

    public void setDataPrestacao(Date dataPrestacao) {
        this.dataPrestacao = dataPrestacao;
    }

    public Date getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Date getHorarioTermino() {
        return horarioTermino;
    }

    public void setHorarioTermino(Date horarioTermino) {
        this.horarioTermino = horarioTermino;
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

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
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
        if (!(object instanceof PrestacaoServico)) {
            return false;
        }
        PrestacaoServico other = (PrestacaoServico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.PrestacaoServico[ id=" + id + " ]";
    }
    
}
