package ifmt.cba.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ifmt.cba.dto.EstadoPedidoDTO;
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
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_pedido")
    private LocalTime horaPedido;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_producao")
    private LocalTime horaProducao;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_pronto")
    private LocalTime horaPronto;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_entrega")
    private LocalTime horaEntrega;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_finalizado")
    private LocalTime horaFinalizado;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoPedidoDTO estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entregador")
    private Entregador entregador;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pedido")
    private List<ItemPedido> listaItens;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public LocalTime getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(LocalTime horaPedido) {
        this.horaPedido = horaPedido;
    }

    public LocalTime getHoraProducao() {
        return horaProducao;
    }

    public void setHoraProducao(LocalTime horaProducao) {
        this.horaProducao = horaProducao;
    }

    public LocalTime getHoraPronto() {
        return horaPronto;
    }

    public void setHoraPronto(LocalTime horaPronto) {
        this.horaPronto = horaPronto;
    }

    public LocalTime getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(LocalTime horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public LocalTime getHoraFinalizado() {
        return horaFinalizado;
    }

    public void setHoraFinalizado(LocalTime horaFinalizado) {
        this.horaFinalizado = horaFinalizado;
    }

    public List<ItemPedido> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<ItemPedido> listaItens) {
        this.listaItens = listaItens;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public EstadoPedidoDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedidoDTO estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public String validar() {
        String retorno = "";

        if (this.dataPedido == null || !this.dataPedido.equals(LocalDate.now())) {
            retorno += "Data do pedido invalida";
        }

        if (this.horaPedido == null || !this.horaPedido.isBefore(LocalTime.now())) {
            retorno += "Hora do pedido invalida";
        }

        if (this.cliente == null) {
            retorno += "Cliente do pedido invalido";
        }

        if (this.horaProducao != null && this.horaProducao.isBefore(this.horaPedido)) {
            retorno += "Hora de producao invalida";
        }

        if (this.horaPronto != null && this.horaPronto.isBefore(this.horaProducao)) {
            retorno += "Hora de conclusao da producao invalida";
        }

        if (this.horaEntrega != null && this.horaEntrega.isBefore(this.horaPronto)) {
            retorno += "Hora de inicio de entrega invalida";
        }

        if (this.horaFinalizado != null && this.horaFinalizado.isBefore(this.horaEntrega)) {
            retorno += "Hora de conclusao entrega invalida";
        }

        if (this.estado == null) {
            retorno += "Estado do pedido invalido";
        } else {
            if ((this.estado == EstadoPedidoDTO.ENTREGA || this.estado == EstadoPedidoDTO.CONCLUIDO)
                    && this.entregador == null) {
                retorno += "Quando o pedido estiver nos estados de ENTREGA OU CONCLUIDO, deve existir um entregador associado";
            }
        }

        if (this.listaItens == null || this.listaItens.isEmpty()) {
            retorno += "Pedido sem itens";
        }

        return retorno;
    }
}
