package com.edutech.msforos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edutech.msforos.model.MensajeForo;
import com.edutech.msforos.repository.MensajeForoRepository;

@Service
public class MensajeForoService {
    @Autowired
    private MensajeForoRepository mForoRepository;

    public MensajeForo save(MensajeForo msforo) {
        return mForoRepository.save(msforo);
    }

    public List<MensajeForo> findListAll() {
        return mForoRepository.findAll();
    }

    public Optional<MensajeForo> findById(Integer id) {
        return mForoRepository.findById(id);
    }

    public void deleteById(int id) {
        mForoRepository.deleteById(id);
    }

    public boolean exitsTituloEnForo(MensajeForo titulo) {
        return titulo.getForoCurso().getMensajes().stream()
                .anyMatch(t -> t.getTitulo().equalsIgnoreCase(titulo.getTitulo()));
    }

    public boolean exitsAutorEnForo(MensajeForo autor) {
        return autor.getForoCurso().getMensajes().stream()
                .anyMatch(a -> a.getAutor().equalsIgnoreCase(autor.getAutor()));
    }

    public boolean update(int id, MensajeForo mensajeForo) {
        MensajeForo udp = mForoRepository.findById(id);
        udp.setIdMensaje(id);
        udp.setAutor(mensajeForo.getAutor());
        udp.setContenido(mensajeForo.getContenido());
        udp.setTitulo(mensajeForo.getTitulo());
        udp.setForoCurso(mensajeForo.getForoCurso());

        mForoRepository.save(udp);
        return true;
    }
}