package es.ujaen.ssccdd.tareas;

import es.ujaen.ssccdd.datos.InfoAutobus;
import es.ujaen.ssccdd.datos.InfoLinea;
import es.ujaen.ssccdd.datos.InfoParada;
import es.ujaen.ssccdd.utils.Grafo;

import java.util.List;
import java.util.Map;

public class TareaControlador implements Runnable {
    // Estado interno del controlador
    private Map<String, InfoLinea> lineas;
    private Map<String, InfoAutobus> autobuses;
    private Map<String, List<InfoParada>> paradas;
    private Grafo grafoRutas;

    /**
     * Constructor de la tarea del controlador.
     */
    public TareaControlador() {
        // Inicialización de atributos
    }

    @Override
    public void run() {

    }
    
}