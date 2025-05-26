package com.edutech.msforos.model;

import java.util.ArrayList;
import java.util.List;

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
    private Integer idForo;

    @Column(nullable = false)
    private Integer idCurso;

    @Column(nullable = false)
    private boolean habilitado = true;

    @OneToMany(mappedBy = "foroCurso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MensajeForo> mensajes = new ArrayList<>();

    public void habilitadoCurso() {
        this.habilitado = true;
    }

    public void deshabilitadoCurso() {
        this.habilitado = false;
    }

}
