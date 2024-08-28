package com.squad1.locadora.entities.carro;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "modelos_carro")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModeloCarro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "fabricante_id")
    private Fabricante nomeFabricante;
    
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}
