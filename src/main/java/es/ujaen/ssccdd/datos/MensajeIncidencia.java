package es.ujaen.ssccdd.datos;

import java.io.Serializable;
import java.time.Instant;

import static es.ujaen.ssccdd.Constantes.TipoIncidencia;

public record MensajeIncidencia(
        String idReportador,
        TipoIncidencia tipo,
        String idLinea,
        String idAutobus,
        String ubicacion,
        String descripcion,
        Instant marcaTiempo,
        int nivelGravedad
) implements Serializable {}