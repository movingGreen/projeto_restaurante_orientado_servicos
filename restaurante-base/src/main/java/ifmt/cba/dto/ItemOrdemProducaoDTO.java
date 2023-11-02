package ifmt.cba.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ItemOrdemProducaoDTO {
    
    private int codigo;
    private PreparoProdutoDTO preparoProdutoDTO;
    private int quantidadePorcao;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public PreparoProdutoDTO getPreparoProdutoDTO() {
        return preparoProdutoDTO;
    }

    public void setPreparoProdutoDTO(PreparoProdutoDTO preparoProdutoDTO) {
        this.preparoProdutoDTO = preparoProdutoDTO;
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
}
