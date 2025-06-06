package com.edutech.msforos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.msforos.model.MensajeForo;

@Repository
public interface MensajeForoRepository extends JpaRepository<MensajeForo, Integer> {

    MensajeForo findById(int id);

    boolean existsByTitulo(String titulo);

    boolean existsByAutor(String autor);

    boolean existsByContenido(String autor);

}