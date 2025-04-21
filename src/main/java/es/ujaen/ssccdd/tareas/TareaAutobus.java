package es.ujaen.ssccdd.tareas;

import es.ujaen.ssccdd.Constantes;

import java.util.List;

import static es.ujaen.ssccdd.Constantes.*;

public class TareaAutobus implements Runnable {
    private String idAutobus;
    private String idLinea;

    // Estado interno del autobús
    private String paradaActual;
    private int capacidad;
    private int ocupacion;
    private List<String> ruta;
    private int indiceRuta;
    private EstadoAutobus estado;

    /**
     * Constructor de la tarea del autobús.
     * @param idAutobus Identificador único del autobús
     * @param idLinea Identificador de la línea a la que pertenece
     * @param capacidad Capacidad máxima de pasajeros
     * @param ruta Lista de paradas que conforman la ruta
     */
    public TareaAutobus(String idAutobus, String idLinea, int capacidad, List<String> ruta) {
        // Inicialización de atributos
    }

    @Override
    public void run() {
    }
}