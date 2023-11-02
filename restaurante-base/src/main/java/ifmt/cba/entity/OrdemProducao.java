package ifmt.cba.entity;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ifmt.cba.dto.EstadoOrdemProducaoDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "ordem_producao")
public class OrdemProducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Temporal(TemporalType.DATE)
    private LocalDate dataProducao;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cardapio cardapio;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoOrdemProducaoDTO estado;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ordem")
    private List<ItemOrdemProducao> listaItens;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDataProducao() {
        return dataProducao;
    }

    public void setDataProducao(LocalDate dataProducao) {
        this.dataProducao = dataProducao;
    }

    public List<ItemOrdemProducao> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<ItemOrdemProducao> listaItens) {
        this.listaItens = listaItens;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    public EstadoOrdemProducaoDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrdemProducaoDTO estado) {
        this.estado = estado;
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
        OrdemProducao other = (OrdemProducao) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }

    public String validar() {

        String retorno = "";

        if (this.listaItens == null || this.listaItens.isEmpty()) {
            retorno += "Ordem de Producao deve ter pelo menos um item";
        }

        if (this.dataProducao == null || this.dataProducao.isEqual(LocalDate.now())) {
            retorno += "Data de producao invalida";
        }

        if (this.cardapio == null) {
            retorno += "Cardapio invalido";
        }

        if (this.estado == null) {
            retorno += "Estado da ordem invalido";
        }

        return retorno;

    }
}
