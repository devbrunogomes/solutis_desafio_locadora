package com.squad1.locadora.entities.aluguel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "apolices")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApoliceSeguro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float valorFranquia;
    private boolean protecaoTerceiro;
    private boolean protecaoCausasNaturais;
    private boolean protecaoRoubo;

    @OneToOne(mappedBy = "apolice")
    private Aluguel aluguel;
}
