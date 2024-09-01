package com.squad1.locadora.DTO;

import com.squad1.locadora.entities.aluguel.MetodoPagamento;
import java.util.Calendar;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
@NoArgsConstructor
@AllArgsConstructor
public class AluguelDTO {
    private Long id;
    private Calendar dataPedido;
    private Date dataEntrega;
    private Date dataDevolucao;
    private float valorTotal;
    private Long apoliceId; // ID da ApoliceSeguro, se necessário
    private Long carroId; // ID do Carro
    private Long motoristaId; // ID do Motorista
    private MetodoPagamento metodoPagamento;

    public AluguelDTO(Calendar dataPedido, Date dataEntrega, Date dataDevolucao, Long apoliceId, Long carroId, Long motoristaId, MetodoPagamento metodoPagamento) {
        this.dataPedido = dataPedido;
        this.dataEntrega = dataEntrega;
        this.dataDevolucao = dataDevolucao;
        this.apoliceId = apoliceId;
        this.carroId = carroId;
        this.motoristaId = motoristaId;
        this.metodoPagamento = metodoPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Calendar dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getApoliceId() {
        return apoliceId;
    }

    public void setApoliceId(Long apoliceId) {
        this.apoliceId = apoliceId;
    }

    public Long getCarroId() {
        return carroId;
    }

    public void setCarroId(Long carroId) {
        this.carroId = carroId;
    }

    public Long getMotoristaId() {
        return motoristaId;
    }

    public void setMotoristaId(Long motoristaId) {
        this.motoristaId = motoristaId;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    
    
    
    
    
    
}
