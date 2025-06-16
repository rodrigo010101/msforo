package com.edutech.msforos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.msforos.model.MensajeForo;
import com.edutech.msforos.repository.MensajeForoRepository;

@Service
public class MensajeForoService {
    @Autowired
    private MensajeForoRepository mensajeForoRepository;

    public MensajeForo save(MensajeForo msforo) {
        return mensajeForoRepository.save(msforo);
    }

    public List<MensajeForo> findListAll() {
        return mensajeForoRepository.findAll();
    }

    public Optional<MensajeForo> findById(Integer id) {
        return mensajeForoRepository.findById(id);
    }

    public void deleteById(Integer id) {
        mensajeForoRepository.deleteById(id);
    }

    public boolean exitsTituloEnForo(MensajeForo titulo) {
        return titulo.getForoCurso().getMensajes().stream()
                .anyMatch(t -> t.getTitulo().equalsIgnoreCase(titulo.getTitulo()));
    }

    public boolean exitsAutorEnForo(MensajeForo autor) {
        return autor.getForoCurso().getMensajes().stream()
                .anyMatch(a -> a.getAutor().equalsIgnoreCase(autor.getAutor()));
    }

    public boolean existsByContenido(MensajeForo contenido) {
        return contenido.getForoCurso().getMensajes().stream()
                .anyMatch(c -> c.getContenido().equalsIgnoreCase(contenido.getContenido()));
    }

    public boolean update(Integer id, MensajeForo mensajeForo) {

        Optional<MensajeForo> oPudp = mensajeForoRepository.findById(id);
        if (oPudp.isPresent()) {
            MensajeForo udp = oPudp.get();

            udp.setIdMensaje(id);
            udp.setAutor(mensajeForo.getAutor());
            udp.setContenido(mensajeForo.getContenido());
            udp.setTitulo(mensajeForo.getTitulo());
            udp.setForoCurso(mensajeForo.getForoCurso());
            mensajeForoRepository.save(udp);
            return true;
        }
        return false;
    }
}