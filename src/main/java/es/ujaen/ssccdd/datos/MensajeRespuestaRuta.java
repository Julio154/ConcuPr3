package es.ujaen.ssccdd.datos;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public record MensajeRespuestaRuta(
        String idSolicitud,
        List<SegmentoRuta> segmentos,
        int duracionTotal,
        int contadorTransbordos,
        Instant llegadaEstimada
) implements Serializable {}