package com.example.Aula.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Aula.entidades.Contato;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{

}

