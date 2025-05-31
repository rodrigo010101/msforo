package com.edutech.msforos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.msforos.model.MensajeForo;

@Repository
public interface MensajeForoRepository extends JpaRepository<MensajeForo, Integer> {

    MensajeForo findById(int id);

    boolean existsMensaje(String mensaje);

    Optional<MensajeForo> existsListMensaje(String mensaje);

    Optional<MensajeForo> findByTitulo(String titulo);

    Optional<MensajeForo> findByAutor(String autor);

}