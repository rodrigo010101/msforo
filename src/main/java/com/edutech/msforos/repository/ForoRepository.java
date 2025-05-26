package com.edutech.msforos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.msforos.model.ForoCurso;
import java.util.Optional;

@Repository
public interface ForoRepository extends JpaRepository<ForoCurso, Integer> {

    Optional<ForoCurso> findById(Integer idForo);

}
