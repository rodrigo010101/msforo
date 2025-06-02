package com.edutech.msforos.model;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class TemaForo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTema;

    @Column(length = 100, nullable = false)
    private String titulo;
    @Column(length = 100, nullable = false)
    private String autor;
    @Column(nullable = false)
    private LocalDate fechaCreacion;
    @Column(nullable = false)
    private LocalDate fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "foro_curso_id")
    private ForoCurso foroCurso;

    @OneToMany(mappedBy = "temaForo", cascade = CascadeType.ALL)
    private List<MensajeForo> mensajes;
}