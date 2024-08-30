package com.squad1.locadora.entities.pessoa;

import com.squad1.locadora.entities.aluguel.Aluguel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "motorista")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Motorista extends Pessoa {

    @Column(unique = true, nullable = false)
    private String numeroCNH;

    @OneToMany(mappedBy = "motorista")
    private Set<Aluguel> aluguel;

    @Column(unique = true, nullable = false)
    private String email;
}
