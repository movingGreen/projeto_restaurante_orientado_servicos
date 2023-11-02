package ifmt.cba.entity;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ifmt.cba.dto.PreparoProdutoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cardapio")
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(fetch = FetchType.EAGER)
    private List<PreparoProduto> listaPreparoProduto;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<PreparoProduto> getListaPreparoProduto() {
        return listaPreparoProduto;
    }

    public void setListaPreparoProduto(List<PreparoProduto> listaPreparoProduto) {
        this.listaPreparoProduto = listaPreparoProduto;
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
        Cardapio other = (Cardapio) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public String validar(){
        String retorno = "";

        if (this.nome == null || this.nome.length() < 3){
            retorno += "Nome invalido";
        }

        if (this.descricao == null){
            retorno += "Descricao invalida";
        }

        if (this.listaPreparoProduto == null || this.listaPreparoProduto.size() == 0){
            retorno += "Cardapio sem itens";
        }

        return retorno;
    }
}
