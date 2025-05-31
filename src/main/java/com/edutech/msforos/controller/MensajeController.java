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

import com.edutech.msforos.model.MensajeForo;
import com.edutech.msforos.service.MensajeForoService;

@RestController
@RequestMapping("api/v1/mensaje")
public class MensajeController {

    @Autowired
    MensajeForoService mensajeForoService;

    @PostMapping()
    public ResponseEntity<MensajeForo> createMensajeForo(@RequestBody MensajeForo mensajeForo) {
        if (mensajeForoService.exitsAutorEnForo(mensajeForo)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if (mensajeForoService.exitsTituloEnForo(mensajeForo)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        var newMensajeForo = mensajeForoService.save(mensajeForo);
        return new ResponseEntity<>(newMensajeForo, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<MensajeForo>> listForo() {
        try {
            var list = mensajeForoService.findListAll();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<MensajeForo> readId(@PathVariable int id) {
        var linkId = mensajeForoService.findById(id);
        if (!linkId.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return mensajeForoService.findById(id).map(mensaje -> new ResponseEntity<>(mensaje, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MensajeForo> updateMenForo(@PathVariable int id, @RequestBody MensajeForo mensForo) {
        var linkId = mensajeForoService.findById(id);
        if (!linkId.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (mensajeForoService.update(id, mensForo)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delMensaje(@PathVariable int id) {
        return mensajeForoService.findById(id).map(mensaje -> {
            mensajeForoService.deleteById(mensaje.getIdMensaje());
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}