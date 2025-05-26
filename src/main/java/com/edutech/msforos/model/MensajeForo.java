package com.edutech.msforos.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
    @JoinColumn(name = "foro_curso_id", nullable = false)
    private ForoCurso foroCurso;

    @ElementCollection
    @CollectionTable(name = "mensaje_usuario", joinColumns = @JoinColumn(name = "mensaje_id"))
    @Column(name = "usuario_id")
    private List<Integer> usuariosIds = new ArrayList<>();

}
