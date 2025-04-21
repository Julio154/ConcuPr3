package es.ujaen.ssccdd.datos;

import java.io.Serializable;
import java.time.Instant;

public record SegmentoRuta(
        String idLinea,
        String idParadaOrigen,
        String idParadaDestino,
        Instant horaSalida,
        Instant horaLlegada,
        int duracion
) implements Serializable {}