package com.edutech.msforos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.msforos.model.TemaForo;

@Repository
public interface TemaForoRepository extends JpaRepository<TemaForo, Integer> {

    TemaForo findById(int idforo);

    TemaForo findByTituloyAutor(String titulo, String Autor);

    boolean findByTema(String tema);

    boolean finByTitulo(String titulo);

    boolean finByAutor(String autor);

}
