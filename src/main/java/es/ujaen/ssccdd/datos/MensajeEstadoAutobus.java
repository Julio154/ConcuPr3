package es.ujaen.ssccdd.datos;

import es.ujaen.ssccdd.Constantes;

import java.io.Serializable;
import java.time.Instant;

import static es.ujaen.ssccdd.Constantes.*;

public record MensajeEstadoAutobus(
        String idAutobus,
        String idLinea,
        String paradaActual,
        String paradaSiguiente,
        int capacidadDisponible,
        int ocupacion,
        Instant marcaTiempo,
        EstadoAutobus estado
) implements Serializable {}