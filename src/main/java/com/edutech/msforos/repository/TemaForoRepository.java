package com.edutech.msforos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.msforos.model.TemaForo;

@Repository
public interface TemaForoRepository extends JpaRepository<TemaForo, Integer> {

    TemaForo findById(int idforo);

    boolean findByTitulo(String titulo);

    boolean findByAutor(String autor);

}
