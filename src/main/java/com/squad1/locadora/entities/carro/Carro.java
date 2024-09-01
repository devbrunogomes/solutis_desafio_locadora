package com.squad1.locadora.entities.carro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.squad1.locadora.entities.aluguel.Aluguel;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    private boolean reserva ;

    @ManyToOne
    @JoinColumn(name = "modelo_id")  // Nome da coluna que armazenará o ID do modelo  
    private ModeloCarro modeloCarro;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "carro_acessorio", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "carro_id"), // FK para Carro
            inverseJoinColumns = @JoinColumn(name = "acessorio_id") // FK para Acessorio
    )
    private Set<Acessorio> acessorios;

    @OneToMany(mappedBy = "carro")
    @JsonIgnore
    private Set<Aluguel> aluguel;

    private String urlImagem;

    public Long getId() {
        return id;
    }

    public ModeloCarro getModeloCarro() {
        return modeloCarro;
    }

    public Set<Acessorio> getAcessorios() {
        return acessorios;
    }

    public Set<Aluguel> getAluguel() {
        return aluguel;
    }
    
    
}