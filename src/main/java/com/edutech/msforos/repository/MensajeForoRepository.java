package com.edutech.msforos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.msforos.model.MensajeForo;

@Repository
public interface MensajeForoRepository extends JpaRepository<MensajeForo, Integer> {
    Optional<MensajeForo> findById(Integer id);
}
