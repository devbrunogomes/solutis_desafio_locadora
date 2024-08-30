package com.squad1.locadora.entities.pessoa;

import com.squad1.locadora.entities.aluguel.Aluguel;
import jakarta.persistence.*;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

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
    @Email(message = "O email deve ser válido")
    @NotEmpty(message = "O email é obrigatório")
    private String email;
}
