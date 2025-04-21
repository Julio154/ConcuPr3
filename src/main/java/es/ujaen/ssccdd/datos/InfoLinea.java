package es.ujaen.ssccdd.datos;

import java.time.Instant;
import java.util.List;

public class InfoLinea {
    private String idLinea;
    private String nombre;
    private List<String> paradas;
    private int frecuencia; // en minutos
    private Instant primeraSalida;
    private Instant ultimaSalida;

    // Implementar por el alumno
}