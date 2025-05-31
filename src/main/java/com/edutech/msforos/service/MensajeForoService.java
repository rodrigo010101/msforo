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

    public void deleteById(Integer id) {
        mForoRepository.deleteById(id);
    }

    // public boolean existsByMensaje(String mensaje){
    // try {
    // MensajeForo foro = mForoRepository.existsByMensaje(mensaje)
    // } catch (Exception e) {
    // // TODO: handle exception
    // }
    // }
}
