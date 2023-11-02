package ifmt.cba.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "entregador")
public class Entregador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "rg")
    private String RG;

    @Column(name = "cpf")
    private String CPF;

    @Column(name = "telefone")
    private String telefone;

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

    public String getRG() {
        return RG;
    }

    public void setRG(String rG) {
        RG = rG;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
        Entregador other = (Entregador) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }

    public String validar(){
        String retorno = "";

        if(this.nome == null || this.nome.length() < 3){
            retorno += "Nome invalido";
        }

        if(this.RG == null || this.RG.length() == 0){
            retorno += "RG invalido";
        }

        //falta validar CPF
        if(this.CPF == null || this.CPF.length() < 11){
            retorno += "CPF invalido";
        }

        if(this.telefone == null || this.telefone.length() < 8){
            retorno += "Telefone invalido";
        }

        return retorno;
    }
}
