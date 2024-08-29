
package com.squad1.locadora.repositories;

import com.squad1.locadora.entities.aluguel.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    
}
