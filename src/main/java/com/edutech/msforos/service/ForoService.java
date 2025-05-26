package com.edutech.msforos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.msforos.model.ForoCurso;
import com.edutech.msforos.repository.ForoRepository;

@Service
public class ForoService {
    @Autowired
    private ForoRepository fRepository;

    public ForoCurso save(ForoCurso foroCurso) {
        return fRepository.save(foroCurso);
    }

    public List<ForoCurso> findListAll() {
        return fRepository.findAll();
    }

    public Optional<ForoCurso> findById(Integer idforo) {
        return fRepository.findById(idforo);
    }

    public void deleteById(Integer idForo) {
        fRepository.findById(idForo);
    }

    public void activado(Integer idforo) {
        var foro = fRepository.findById(idforo)
                .orElseThrow(() -> new RuntimeException("El idForo" + idforo + "no existe"));
        foro.habilitadoCurso();
    }

    public void desactivado(Integer idforo) {
        var foro = fRepository.findById(idforo)
                .orElseThrow(() -> new RuntimeException("El idForo " + idforo + " no existe"));
        foro.deshabilitadoCurso();
    }

}
