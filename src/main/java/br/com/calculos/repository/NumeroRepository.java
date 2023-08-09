package br.com.calculos.repository;

import br.com.calculos.entity.Numero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NumeroRepository extends JpaRepository<Numero,Long> {
}
