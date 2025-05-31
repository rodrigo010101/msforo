package com.edutech.msforos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.MvcNamespaceHandler;

import com.edutech.msforos.model.ForoCurso;
import com.edutech.msforos.model.MensajeForo;
import com.edutech.msforos.repository.ForoRepository;

import jakarta.persistence.EntityNotFoundException;

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

    public Optional<ForoCurso> findById(int idforo) {
        return fRepository.findById(idforo);
    }

    public void deleteById(int idForo) {
        fRepository.findById(idForo);
    }

    public void activado(int idforo) {
        Optional<ForoCurso> foro = fRepository.findById(idforo);
        if (foro.isPresent()) {
            ForoCurso fo = foro.get();
            fo.setHabilitado(true);
            fRepository.save(fo);
        }
    }

    public void desactivado(int idforo) {
        Optional<ForoCurso> foro = fRepository.findById(idforo);
        if (foro.isPresent()) {
            ForoCurso fo = foro.get();
            fo.setHabilitado(false);
            fRepository.save(fo);
        } else {
            throw new EntityNotFoundException("foro curso no encontrado " + idforo);
        }
    }

    public boolean update(int id, ForoCurso foroCurso) {
        Optional<ForoCurso> fCurso = fRepository.findById(id);

        if (fCurso.isPresent()) {
            ForoCurso fC = fCurso.get();
            fC.setIdForo(id);
            fC.setMensajes(foroCurso.getMensajes());
            fRepository.save(fC);
            return true;
        } else {
            return false;
        }
    }

    public boolean existsMensaje(List<MensajeForo> mensajes) {
        return mensajes.stream()
                .anyMatch(m -> fRepository.findByMensajes(m.getContenido()).isPresent());
    }

}