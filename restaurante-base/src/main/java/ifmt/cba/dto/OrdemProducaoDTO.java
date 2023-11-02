package ifmt.cba.dto;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OrdemProducaoDTO {

    private int codigo;
    private LocalDate dataProducao;
    private CardapioDTO cardapio;
    private EstadoOrdemProducaoDTO estado;
    private List<ItemOrdemProducaoDTO> listaItens;

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

    public List<ItemOrdemProducaoDTO> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<ItemOrdemProducaoDTO> listaItens) {
        this.listaItens = listaItens;
    }

    public CardapioDTO getCardapio() {
        return cardapio;
    }

    public void setCardapio(CardapioDTO cardapio) {
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

}
