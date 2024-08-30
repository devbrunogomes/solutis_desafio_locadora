package com.squad1.locadora.dto;

import java.util.Calendar;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
}
