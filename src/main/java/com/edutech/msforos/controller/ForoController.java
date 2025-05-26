package com.edutech.msforos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.msforos.model.ForoCurso;
import com.edutech.msforos.service.ForoService;

@RestController
@RequestMapping("/api/v1/foro")
public class ForoController {

    @Autowired
    private ForoService foroService;

    @PostMapping
    public ResponseEntity<ForoCurso> createForo(@RequestBody ForoCurso foro) {
        try {
            if (foro.getIdForo() != null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            var newforo = foroService.save(foro);
            return new ResponseEntity<>(newforo, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<List<ForoCurso>> getlistForo() {
        try {
            var listForo = foroService.findListAll();
            if (!listForo.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForoCurso> getForoId(@PathVariable Integer idforo) {
        try {
            var foro = foroService.findById(idforo);
            if (!foro.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ForoCurso> deleteForo(@PathVariable Integer idforo) {
        try {
            var foro = foroService.findById(idforo);
            if (!foro.isPresent()) {
                // ResponseEntity.notFound().build();
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            foroService.deleteById(idforo);
            // ResponseEntity.ok(foro.get());
            return new ResponseEntity<>(foro.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<ForoCurso> putForo(@RequestBody ForoCurso fC,
    // @PathVariable Integer idFc) {
    // try {
    // var foro = foroService.findById(idFc);
    // if (!foro.isPresent()) {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    // fC.setIdCurso(idFc);

    // var newforo = foro.get();

    // } catch (Exception e) {
    // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

}
