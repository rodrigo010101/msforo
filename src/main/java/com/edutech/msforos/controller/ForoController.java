package com.edutech.msforos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.msforos.model.ForoCurso;
import com.edutech.msforos.model.MensajeForo;
import com.edutech.msforos.service.ForoService;

@RestController
@RequestMapping("/api/v1/foro")
public class ForoController {

    @Autowired
    private ForoService foroService;

    @PostMapping
    public ResponseEntity<ForoCurso> createForo(@RequestBody ForoCurso foro) {

        MensajeForo mensajeForo = new MensajeForo();

        if (mensajeForo.getTitulo() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (mensajeForo.getAutor() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (foroService.existsByTitulo(mensajeForo.getTitulo()) || foroService.existsByTitulo(mensajeForo.getAutor())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        ForoCurso newForo = foroService.save(foro);
        return new ResponseEntity<>(newForo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ForoCurso>> getlistForo() {
        try {
            var listForo = foroService.findListAll();
            if (!listForo.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(listForo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{idforo}")
    public ResponseEntity<ForoCurso> getForoId(@PathVariable Integer idforo) {
        try {
            var foro = foroService.findById(idforo);
            if (!foro.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(foro.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/del/{idforo}")
    public ResponseEntity<ForoCurso> deleteForo(@PathVariable Integer idforo) {
        try {
            var foro = foroService.findById(idforo);
            if (!foro.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            foroService.deleteById(idforo);
            // ResponseEntity.ok(foro.get());
            return new ResponseEntity<>(foro.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/upd/{idforo}")
    public ResponseEntity<ForoCurso> updateForo(@PathVariable int id, @RequestBody ForoCurso foroCurso) {
        var linkId = foroService.findById(id);
        if (!linkId.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (foroService.update(id, foroCurso)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}