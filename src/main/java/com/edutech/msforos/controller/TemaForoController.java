package com.edutech.msforos.controller;

import java.util.List;
import java.util.Optional;

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

import com.edutech.msforos.model.TemaForo;
import com.edutech.msforos.service.TemaForoService;

@RestController
@RequestMapping("api/v1/temaforo")
public class TemaForoController {

    @Autowired
    private TemaForoService temaForoService;

    @PostMapping()
    public ResponseEntity<TemaForo> postTema(@RequestBody TemaForo temaForo) {
        try {
            if (temaForoService.existsByAutor(temaForo.getAutor())) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            if (temaForoService.existsByTitulo(temaForo.getTitulo())) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            var newtema = temaForoService.save(temaForo);
            return new ResponseEntity<>(newtema, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<TemaForo> getId(@PathVariable int id) {
        try {
            Optional<TemaForo> linkId = temaForoService.findById(id);
            if (!linkId.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return temaForoService.findById(id).map(f -> new ResponseEntity<>(f, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<List<TemaForo>> getList() {
        try {
            var listforo = temaForoService.findListAll();
            if (listforo.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(listforo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<TemaForo> delForo(@PathVariable int id) {
        try {
            Optional<TemaForo> idtema = temaForoService.findById(id);
            if (!idtema.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return temaForoService.findById(id).map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<TemaForo> updateTemaForo(@PathVariable int id, @RequestBody TemaForo temaForo) {
        try {
            Optional<TemaForo> linkId = temaForoService.findById(id);
            if (!linkId.isPresent()) {
                throw new IllegalArgumentException("Campo ID no puede estar vacio");
            }
            temaForoService.update(id, temaForo);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
