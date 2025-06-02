package com.edutech.msforos.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class ForoCurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private int idForo;

    @Column(nullable = false)
    private int idCurso;

    @Column(nullable = false)
    private boolean habilitado = true;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String mensaje;

    @OneToMany(mappedBy = "foroCurso", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<MensajeForo> mensajes = new ArrayList<>();

    public void habilitadoCurso() {
        this.habilitado = true;
    }

    public void deshabilitadoCurso() {
        this.habilitado = false;
    }

    public void addMensajes(MensajeForo nuevo) {
        mensajes.add(nuevo);
    }

    public void removeMensaje(MensajeForo mensaje) {
        mensajes.remove(mensaje);
        mensaje.setForoCurso(null);
    }
}
