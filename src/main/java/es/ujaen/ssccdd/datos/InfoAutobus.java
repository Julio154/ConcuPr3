package es.ujaen.ssccdd.datos;

import java.time.Instant;

import static es.ujaen.ssccdd.Constantes.EstadoAutobus;

public class InfoAutobus {
    private String idAutobus;
    private String idLinea;
    private String paradaActual;
    private String paradaSiguiente;
    private Instant horaSalida;
    private Instant horaLlegada;
    private EstadoAutobus estado;
    private int ocupacion;
    private int capacidad;

    // Implementar por el alumno
}