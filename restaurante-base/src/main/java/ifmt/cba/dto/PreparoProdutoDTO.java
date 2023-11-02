package ifmt.cba.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PreparoProdutoDTO {

    private int codigo;
    private ProdutoDTO produtoDTO;
    private TipoPreparoDTO tipoPreparoDTO;
    private int tempoPreparo;
    private float valorPreparo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ProdutoDTO getProdutoDTO() {
        return produtoDTO;
    }

    public void setProdutoDTO(ProdutoDTO produtoDTO) {
        this.produtoDTO = produtoDTO;
    }

    public TipoPreparoDTO getTipoPreparoDTO() {
        return tipoPreparoDTO;
    }

    public void setTipoPreparoDTO(TipoPreparoDTO tipoPreparoDTO) {
        this.tipoPreparoDTO = tipoPreparoDTO;
    }

    public int getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(int tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public float getValorPreparo() {
        return valorPreparo;
    }

    public void setValorPreparo(float valorPreparo) {
        this.valorPreparo = valorPreparo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
