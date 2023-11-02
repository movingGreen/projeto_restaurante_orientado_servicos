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
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "custo_unidade")
    private float custoUnidade;

    @Column(name = "valor_energetico")
    private int valorEnergetico;

    @Column(name = "estoque")
    private int estoque;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grupo")
    private GrupoAlimentar grupoAlimentar;

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

    public GrupoAlimentar getGrupoAlimentar() {
        return grupoAlimentar;
    }

    public void setGrupoAlimentar(GrupoAlimentar grupoAlimentar) {
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
        Produto other = (Produto) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }

    public String validar() {
        String retorno = "";

        if (this.nome == null || this.nome.length() < 3) {
            retorno += "Nome invalido";
        }

        if (this.custoUnidade <= 0) {
            retorno += "Custo por unidade invalido";
        }

        if (this.valorEnergetico < 0) {
            retorno += "Valor energetico invalido";
        }

        if (estoque < 0) {
            retorno += "Estoque invalido";
        }

        if (this.grupoAlimentar == null) {
            retorno += "Grupo alimentar nao pode ser nulo";
        }

        return retorno;
    }
}
