/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_ppi.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Seven
 */
@Entity
public class Calendario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String referencia;
    private String descricao;
    private String maquina;
    private String chegada;
    private String usinagem;
    private String entrega;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calendario)) {
            return false;
        }
        Calendario other = (Calendario) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "projeto_ppi.pojo.Calendario[ id=" + getId() + " ]";
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the maquina
     */
    public String getMaquina() {
        return maquina;
    }

    /**
     * @param maquina the maquina to set
     */
    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    /**
     * @return the chegada
     */
    public String getChegada() {
        return chegada;
    }

    

    /**
     * @return the usinagem
     */
    public String getUsinagem() {
        return usinagem;
    }


    /**
     * @return the entrega
     */
    public String getEntrega() {
        return entrega;
    }

    /**
     * @param chegada the chegada to set
     */
    public void setChegada(String chegada) {
        this.chegada = chegada;
    }

    /**
     * @param usinagem the usinagem to set
     */
    public void setUsinagem(String usinagem) {
        this.usinagem = usinagem;
    }

    /**
     * @param entrega the entrega to set
     */
    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }
    
}
