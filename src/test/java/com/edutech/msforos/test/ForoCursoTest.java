package com.edutech.msforos.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.edutech.msforos.model.ForoCurso;
import com.edutech.msforos.model.MensajeForo;
import com.edutech.msforos.model.TemaForo;
import com.edutech.msforos.repository.ForoRepository;
import com.edutech.msforos.service.ForoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

// los assert nos ayuda a la confirmacion de dicha operacion

@ExtendWith(MockitoExtension.class)

public class ForoCursoTest {

    @Mock
    private ForoRepository foroRepository;

    @InjectMocks
    private ForoService foroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void agregarForo_debeGuardarForoyLuegoRetornarForo() {

        LocalDate fechaCreacion = LocalDate.now();
        LocalDate fechaModificacion = LocalDate.now();

        ForoCurso foroCurso = new ForoCurso(0, 1, true, "Programacion", "booleanos", new ArrayList<>());

        TemaForo temaforo = new TemaForo(0, "Programacion", "Geoger", fechaCreacion, fechaModificacion, foroCurso,
                new ArrayList<>());

        MensajeForo mensajeForo = new MensajeForo(0, "La programacion", "georger", "Programacion y base de datos",
                fechaCreacion, foroCurso, temaforo);

        foroCurso.addMensajes(mensajeForo);

        when(foroRepository.save(any(ForoCurso.class))).thenReturn(foroCurso);

        ForoCurso resultado = foroService.save(foroCurso);

        assertNotNull(resultado);
        assertEquals("Programacion", resultado.getTitulo());
        assertEquals(1, resultado.getMensajes().size());
        verify(foroRepository).save(resultado);
    }

    @Test
    void listarForo_debeDevolverlaListaForo() {

        ForoCurso foro = new ForoCurso(1, 1, true, "Programacion", "sobre Programacion", new ArrayList<>());

        List<ForoCurso> listaForo = Arrays.asList(foro);
        when(foroRepository.findAll()).thenReturn(listaForo);

        List<ForoCurso> resultado = foroService.findListAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Programacion", resultado.get(0).getTitulo());
        verify(foroRepository).findAll();
    }

    @Test
    void eliminarUnforoYdebellamaralRepository() {
        foroService.deleteById(1);
        verify(foroRepository).findById(1);
    }

    @Test
    void actualizarforo_debeactualizarYretornarElnuevoForo() {

        ForoCurso foroExistente = new ForoCurso(0, 1, true, "Programacion", "Materia java", new ArrayList<>());
        ForoCurso nuevoForo = new ForoCurso(0, 1, true, "Programacion orientada a objetos", "Programacion con java",
                new ArrayList<>());

        when(foroRepository.findById(0)).thenReturn(Optional.of(foroExistente));
        when(foroRepository.save(any(ForoCurso.class))).thenReturn(foroExistente);

        boolean resultado = foroService.update(0, nuevoForo);

        verify(foroRepository).findById(0);
        assertTrue(resultado);
        verify(foroRepository).save(any(ForoCurso.class));
    }

    @Test
    void ActuaizarForoCurso_cuandoNoExiste() {

        int idInexistente = 100;
        ForoCurso nuevoCurso = new ForoCurso(idInexistente, 1, true, "base de datos", "mysql", new ArrayList<>());

        when(foroRepository.findById(idInexistente)).thenReturn(Optional.empty());

        boolean resultado = foroService.update(idInexistente, nuevoCurso);
        assertFalse(resultado);
        verify(foroRepository, never()).save(any());
    }

}
