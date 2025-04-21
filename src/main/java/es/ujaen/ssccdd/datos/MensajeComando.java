package es.ujaen.ssccdd.datos;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

import static es.ujaen.ssccdd.Constantes.TipoComando;

public record MensajeComando(
        String idDestino,
        TipoComando tipo,
        Map<String, String> parametros,
        Instant marcaTiempo,
        String emisor
) implements Serializable {}