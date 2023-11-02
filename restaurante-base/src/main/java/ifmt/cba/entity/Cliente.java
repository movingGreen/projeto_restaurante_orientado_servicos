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
@Table(name = "cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "rg", nullable = false)
    private String RG;

    @Column(name = "cpf", nullable = false)
    private String CPF;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "referencia", nullable = false)
    private String pontoReferencia;

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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
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
        Cliente other = (Cliente) obj;
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

        if(this.logradouro == null || this.logradouro.length() == 0){
            retorno += "Logradouro invalido";
        }
     
        if(this.numero == null || this.numero.length() == 0){
            retorno += "Numero invalido";
        }

        if(this.bairro == null || this.bairro.length() == 0){
            retorno += "Bairro invalido";
        }
        
        if(this.pontoReferencia == null){
            retorno += "Ponto de referencia invalido";
        }
   
        return retorno;
    }
}
