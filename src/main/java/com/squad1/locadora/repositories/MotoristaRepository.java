
package com.squad1.locadora.repositories;

import com.squad1.locadora.entities.pessoa.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    
}
