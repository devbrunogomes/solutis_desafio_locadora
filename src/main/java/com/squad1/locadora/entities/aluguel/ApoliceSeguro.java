package com.squad1.locadora.entities.aluguel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "apolices")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = "aluguel")
public class ApoliceSeguro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float valorFranquia;
    private boolean protecaoTerceiro;
    private boolean protecaoCausasNaturais;
    private boolean protecaoRoubo;

    @OneToOne(mappedBy = "apolice")
    @JsonIgnore
    private Aluguel aluguel;
}
