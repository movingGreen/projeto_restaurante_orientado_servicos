package ifmt.cba.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ProdutoDTO {
    
    private int codigo;
    private String nome;
    private float custoUnidade;
    private int valorEnergetico;
    private int estoque;
    private GrupoAlimentarDTO grupoAlimentar;

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

    public float getCustoUnidade() {
        return custoUnidade;
    }

    public void setCustoUnidade(float custoUnidade) {
        this.custoUnidade = custoUnidade;
    }

    public int getValorEnergetico() {
        return valorEnergetico;
    }

    public void setValorEnergetico(int valorEnergetico) {
        this.valorEnergetico = valorEnergetico;
    }

    public GrupoAlimentarDTO getGrupoAlimentar() {
        return grupoAlimentar;
    }

    public void setGrupoAlimentar(GrupoAlimentarDTO grupoAlimentar) {
        this.grupoAlimentar = grupoAlimentar;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }    

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
