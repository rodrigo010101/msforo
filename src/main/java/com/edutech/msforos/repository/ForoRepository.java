package com.edutech.msforos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.msforos.model.ForoCurso;

@Repository
public interface ForoRepository extends JpaRepository<ForoCurso, Integer> {

    Optional<ForoCurso> findById(int idForo);

    ForoCurso findBytituloYAutor(String mensaje);

    Optional<ForoCurso> findByTitulo(String titulo);

}