package com.edutech.msforos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.msforos.model.MensajeForo;

@RestController
@RequestMapping("api/v1/mensaje")
public class MensajeController {
    @Autowired
    MensajeForo mensajeForo;

    @PostMapping("/{id}")

    @GetMapping

    @GetMapping("{/id}")

    @PutMapping

    @DeleteMapping("{/id}")
    public ResponseEntity<MensajeForo> delMensaje(@PathVariable Integer id) {
        try {

        } catch (Exception e) {
            // TODO: handle exception
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
