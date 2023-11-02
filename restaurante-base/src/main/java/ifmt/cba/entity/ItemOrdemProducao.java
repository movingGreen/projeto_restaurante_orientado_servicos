package ifmt.cba.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_ordem_producao")
public class ItemOrdemProducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_preparo")
    private PreparoProduto preparoProduto;

    @Column(name = "qtde_porcao")
    private int quantidadePorcao;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public PreparoProduto getPreparoProduto() {
        return preparoProduto;
    }

    public void setPreparoProduto(PreparoProduto preparoProduto) {
        this.preparoProduto = preparoProduto;
    }

    public int getQuantidadePorcao() {
        return quantidadePorcao;
    }

    public void setQuantidadePorcao(int quantidadePorcao) {
        this.quantidadePorcao = quantidadePorcao;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigo;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemOrdemProducao other = (ItemOrdemProducao) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }

    public String validar(){
        String retorno = "";

        if(this.preparoProduto == null){
            retorno += "Item de Ordem de Producao nao relacionado a um preparo de produto";
        }

        if (this.quantidadePorcao <= 0){
            retorno += "Quantidade de porcoes deve ser maior que zero";
        }

        return retorno;
    }
}
