package com.edutech.msforos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.edutech.msforos.model.ForoCurso;
import com.edutech.msforos.model.MensajeForo;
import com.edutech.msforos.model.TemaForo;
import com.edutech.msforos.repository.MensajeForoRepository;

@ExtendWith(MockitoExtension.class)

public class MensajeForoServiceTest {

    @Mock
    private MensajeForoRepository mensajeForoRepository;

    @InjectMocks
    private MensajeForoService mensajeForoService;

    @Captor
    ArgumentCaptor<MensajeForo> mensajeForoCaptor;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        // Inicializa los mocks anotados en esta clase
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void guargarMensajeForoYretornarMensajeForo() {

        ForoCurso foroCurso = new ForoCurso();
        TemaForo temaforo = new TemaForo();

        LocalDate fechaCreacion = LocalDate.now();
        MensajeForo mensajeForo = new MensajeForo(1, "Linux", "Linux tolvar", "Aprende linux", fechaCreacion, foroCurso,
                temaforo);

        when(mensajeForoRepository.save(any(MensajeForo.class))).thenReturn(mensajeForo);

        MensajeForo resultado = mensajeForoService.save(mensajeForo);
        assertNotNull(resultado);
        assertEquals(mensajeForo, resultado);
        assertEquals("Linux", resultado.getTitulo());
        verify(mensajeForoRepository, times(1)).save(mensajeForoCaptor.capture());

        // validar los argumentos capturados
        MensajeForo capturado = mensajeForoCaptor.getValue();
        assertEquals(1, capturado.getIdMensaje());
        assertEquals("Aprende linux", capturado.getContenido());
        assertEquals("Linux", capturado.getTitulo());

    }

    @Test
    void listarMensajeForoyRetornarLista() {

        LocalDate fechaCreacion = LocalDate.now();
        ForoCurso foroCurso = new ForoCurso();
        TemaForo temaForo = new TemaForo();

        List<MensajeForo> listMensajeForo = List.of(
                new MensajeForo(1, "Base de datos", "base", "base de datos 2", fechaCreacion, foroCurso, temaForo),
                new MensajeForo(2, "Programacion", "program", "Orientado a objetos", fechaCreacion, foroCurso,
                        temaForo));

        when(mensajeForoRepository.findAll()).thenReturn(listMensajeForo);

        List<MensajeForo> resultado = mensajeForoService.findListAll();

        assertEquals(listMensajeForo, resultado);
        assertEquals(listMensajeForo.size(), resultado.size());
        verify(mensajeForoRepository).findAll();
    }

    @Test
    void buscarMensajeForoporIdYDebeRetornarelObjeto() {
        LocalDate fechaCreacion = LocalDate.now();
        ForoCurso foroCurso = new ForoCurso();
        TemaForo temaForo = new TemaForo();

        MensajeForo mensajeForo = new MensajeForo(0, "Marvel", "wi", "poderes", fechaCreacion, foroCurso,
                temaForo);

        // lenient().doReturn(Optional.of(mensajeForo)).when(mensajeForoRepository).findById(0);
        doReturn(Optional.of(mensajeForo)).when(mensajeForoRepository).findById(Integer.valueOf(0));
        Optional<MensajeForo> resultado = mensajeForoService.findById(0);
        assertTrue(resultado.isPresent(), "Resultado debe estar presente");
        assertEquals(0, resultado.get().getIdMensaje());
        assertEquals("Marvel", resultado.get().getTitulo());

        verify(mensajeForoRepository, times(1)).findById(Integer.valueOf(0));
    }

    @Test
    void eliminarMensajeForoYdebeLlamaralMetodoDelete() {

        MensajeForo mensajeForoSiexiste = new MensajeForo();

        when(mensajeForoRepository.findById(1)).thenReturn(Optional.of(mensajeForoSiexiste));

        doNothing().when(mensajeForoRepository).deleteById(1);

        mensajeForoService.findById(Integer.valueOf(1));
        mensajeForoService.deleteById(Integer.valueOf(1));

        verify(mensajeForoRepository).findById(Integer.valueOf(1));
        verify(mensajeForoRepository).deleteById(Integer.valueOf(1));

    }

    @Test
    void existeTituloEnForoYRetornarTrue() {

        MensajeForo tituloExistente = new MensajeForo();

        MensajeForo tituloNuevo = new MensajeForo();

        tituloExistente.setTitulo("Programacion");

        tituloNuevo.setTitulo("Programacion");

        ForoCurso foro = new ForoCurso();

        foro.setMensajes(List.of(tituloExistente));
        tituloNuevo.setForoCurso(foro);

        boolean resultado = mensajeForoService.exitsTituloEnForo(tituloNuevo);

        assertTrue(resultado);
    }

    @Test
    void noExisteTituloYdebeRetornarFalse() {
        MensajeForo existente = new MensajeForo();
        MensajeForo nuevo = new MensajeForo();

        existente.setTitulo("Matematica");
        nuevo.setTitulo("Programacion");

        ForoCurso foro = new ForoCurso();
        foro.setMensajes(List.of(existente));
        nuevo.setForoCurso(foro);
        boolean resultado = mensajeForoService.exitsTituloEnForo(nuevo);
        assertFalse(resultado);
    }

    @Test
    void existeAutorEnForoYdebeRetornarTrue() {
        MensajeForo existeAutor = new MensajeForo();
        MensajeForo nuevoAutor = new MensajeForo();

        existeAutor.setAutor("sharma");
        nuevoAutor.setAutor("sharma");

        ForoCurso foro = new ForoCurso();
        foro.setMensajes(List.of(existeAutor));
        nuevoAutor.setForoCurso(foro);

        boolean resultado = mensajeForoService.exitsAutorEnForo(nuevoAutor);
        assertTrue(resultado);
    }

    @Test
    void noExisteAutorYdebeRetonarFalse() {
        MensajeForo existeAutor = new MensajeForo();
        MensajeForo nuevoAutor = new MensajeForo();

        existeAutor.setAutor("bose");
        nuevoAutor.setAutor("sourabh");

        ForoCurso foro = new ForoCurso();
        foro.setMensajes(List.of(existeAutor));
        nuevoAutor.setForoCurso(foro);

        boolean resultado = mensajeForoService.exitsAutorEnForo(nuevoAutor);
        assertFalse(resultado);
    }

    @Test
    void existeContenidoydebeRetornarTrue() {
        MensajeForo contenidoExistenete = new MensajeForo();
        MensajeForo contenidoNuevo = new MensajeForo();

        contenidoExistenete.setContenido("La programacion");
        contenidoNuevo.setContenido("La programacion");

        ForoCurso foro = new ForoCurso();

        foro.setMensajes(List.of(contenidoExistenete));
        contenidoNuevo.setForoCurso(foro);

        boolean resultado = mensajeForoService.existsByContenido(contenidoNuevo);
        assertTrue(resultado);
    }

    @Test
    void noExisteContenidoYdebeRetornaFalse() {
        MensajeForo contendioExistente = new MensajeForo();

        MensajeForo contenidoNuevo = new MensajeForo();

        contendioExistente.setContenido("Python");
        contenidoNuevo.setContenido("Java");

        ForoCurso foro = new ForoCurso();

        foro.setMensajes(List.of(contendioExistente));
        contenidoNuevo.setForoCurso(foro);

        boolean resultado = mensajeForoService.existsByContenido(contenidoNuevo);

        assertFalse(resultado);
    }

    @Test
    void actualizarPorIdYdebeRetornaTrueSisencontro() {

        LocalDate fechaCreacion = LocalDate.now();

        ForoCurso foroCurso = new ForoCurso();
        TemaForo temaForo = new TemaForo();

        MensajeForo mensajeForoExistente = new MensajeForo(1, "osho", "Isabel", "perrito", fechaCreacion, foroCurso,
                temaForo);

        MensajeForo nuevoMensajeForo = new MensajeForo(1, "violeta", "Isabel allende", "amor", fechaCreacion, foroCurso,
                temaForo);

        when(mensajeForoRepository.findById(eq(1))).thenReturn(Optional.of(mensajeForoExistente));
        when(mensajeForoRepository.save(any(MensajeForo.class))).thenReturn(nuevoMensajeForo);

        boolean resultado = mensajeForoService.update(1, nuevoMensajeForo);

        verify(mensajeForoRepository).findById(Integer.valueOf(1));
        verify(mensajeForoRepository).save(any(MensajeForo.class));
        assertTrue(resultado);
    }

    @Test
    void NoActualizarSiNoseEncontroElIdDebeRetornarFalse() {

        MensajeForo idExistente = new MensajeForo();
        idExistente.setIdMensaje(1);

        when(mensajeForoRepository.findById(1)).thenReturn(Optional.empty());

        boolean resultado = mensajeForoService.update(1, idExistente);

        verify(mensajeForoRepository).findById(Integer.valueOf(1));

        assertFalse(resultado);
    }

}
