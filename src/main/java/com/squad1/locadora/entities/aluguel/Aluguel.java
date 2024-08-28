package com.squad1.locadora.entities.aluguel;

import com.squad1.locadora.entities.carro.Carro;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Calendar;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alugueis")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Calendar dataPedido;
    private Date dataEntrega;
    private Date dataDevolucao;
    private float valorTotal;

    @OneToOne
    @JoinColumn(name = "apolice_id")
    private ApoliceSeguro apolice;
    
    @ManyToOne
    @JoinColumn(name = "carro_id")
    private Carro carro;

}
