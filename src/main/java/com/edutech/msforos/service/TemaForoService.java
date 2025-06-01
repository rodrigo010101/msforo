package com.edutech.msforos.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.msforos.model.TemaForo;
import com.edutech.msforos.repository.TemaForoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TemaForoService {

    @Autowired
    private TemaForoRepository tmpRepository;

    public TemaForo save(TemaForo temaForo) {
        return tmpRepository.save(temaForo);
    }

    public List<TemaForo> findListAll() {
        return tmpRepository.findAll();
    }

    public Optional<TemaForo> findById(Integer idforo) {
        return tmpRepository.findById(idforo);
    }

    public void deleteById(int idforo) {
        tmpRepository.deleteById(idforo);
    }

    public boolean existsByTitulo(String titulo) {
        return tmpRepository.finByTitulo(titulo);
    }

    public boolean existsByAutor(String autor) {
        return tmpRepository.finByAutor(autor);
    }

    public boolean update(int idtema, TemaForo temaForo) {
        TemaForo linkTema = tmpRepository.findById(idtema);
        if (temaForo.getTitulo() == null || temaForo.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El campo titulo no puede ser vacio");
        }
        if (temaForo.getAutor() == null || temaForo.getAutor().trim().isEmpty()) {
            throw new IllegalArgumentException("El campo autor no puede ser vacio");
        }
        if (linkTema == null) {
            throw new EntityNotFoundException("No se encontro el tema con el ID " + idtema);
        } else {
            linkTema.setAutor(temaForo.getAutor());
            linkTema.setTitulo(temaForo.getTitulo());
            linkTema.setFechaModificacion(LocalDate.now());
            tmpRepository.save(linkTema);
            return true;
        }
    }
}
