package com.example.Aula.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Aula.entidades.Local;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long>{

}

