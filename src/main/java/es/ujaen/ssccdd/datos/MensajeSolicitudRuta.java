package es.ujaen.ssccdd.datos;

import es.ujaen.ssccdd.Constantes;

import java.io.Serializable;
import java.time.Instant;

import static es.ujaen.ssccdd.Constantes.*;

public record MensajeSolicitudRuta(
        String idUsuario,
        String idParadaOrigen,
        String idParadaDestino,
        Instant horaSalida,
        CriterioRuta criterio
) implements Serializable {}