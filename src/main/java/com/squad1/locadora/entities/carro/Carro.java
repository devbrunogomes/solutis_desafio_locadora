package com.squad1.locadora.entities.carro;

import com.squad1.locadora.entities.aluguel.Aluguel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carros")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placa;
    private String chassi;
    private String cor;
    private float valorDiaria;

    @ManyToOne
    @JoinColumn(name = "modelo_id")  // Nome da coluna que armazenará o ID do modelo  
    private ModeloCarro modeloCarro;

    @ManyToMany
    @JoinTable(
            name = "carro_acessorio", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "carro_id"), // FK para Carro
            inverseJoinColumns = @JoinColumn(name = "acessorio_id") // FK para Acessorio
    )
    private Set<Acessorio> acessorios;

    @OneToMany(mappedBy = "carro")
    private Set<Aluguel> aluguel;
}
