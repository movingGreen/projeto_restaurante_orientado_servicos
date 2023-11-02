package ifmt.cba.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CardapioDTO {

    private int codigo;
    private String nome;
    private String descricao;
    private List<PreparoProdutoDTO> listaPreparoProdutoDTO;

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

    public List<PreparoProdutoDTO> getListaPreparoProdutoDTO() {
        return listaPreparoProdutoDTO;
    }

    public void setListaPreparoProdutoDTO(List<PreparoProdutoDTO> listaPreparoProdutoDTO) {
        this.listaPreparoProdutoDTO = listaPreparoProdutoDTO;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
