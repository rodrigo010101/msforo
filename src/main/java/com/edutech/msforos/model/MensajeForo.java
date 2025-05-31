package com.edutech.msforos.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class MensajeForo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Integer idMensaje;

    @Column(length = 100, nullable = false)
    private String titulo;

    @Column(length = 100, nullable = false)
    private String autor;

    @Column(length = 100, nullable = false)
    private String contenido;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "foro_curso_id")
    @JsonBackReference
    private ForoCurso foroCurso;

}
