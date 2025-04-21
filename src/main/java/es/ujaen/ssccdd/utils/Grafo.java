package es.ujaen.ssccdd.utils;

import es.ujaen.ssccdd.datos.InfoLinea;
import es.ujaen.ssccdd.datos.SegmentoRuta;

import java.util.List;
import java.util.Map;

public class Grafo {
    private Map<String, Map<String, Integer>> listaAdyacencia; // Grafo ponderado para las conexiones entre paradas

    /**
     * Añade una conexión directa entre dos paradas.
     * @param origen Parada de origen
     * @param destino Parada de destino
     * @param peso Peso de la conexión (tiempo en minutos)
     */
    public void agregarArista(String origen, String destino, int peso) {
        // Implementar adición de arista al grafo
    }

    /**
     * Encuentra la ruta más corta entre dos paradas.
     * @param origen Parada de origen
     * @param destino Parada de destino
     * @return Lista de paradas que forman la ruta
     */
    public List<String> rutaMasCorta(String origen, String destino) {
        // Implementar algoritmo de Dijkstra para encontrar la ruta más corta
        return null; // Reemplazar con implementación real
    }

    /**
     * Encuentra la ruta con menos transbordos entre dos paradas.
     * @param origen Parada de origen
     * @param destino Parada de destino
     * @param lineas Mapa de líneas con sus paradas
     * @return Lista de segmentos que forman la ruta
     */
    public List<SegmentoRuta> rutaMenosTransbordos(String origen, String destino, Map<String, InfoLinea> lineas) {
        // Implementar algoritmo para minimizar transbordos
        return null; // Reemplazar con implementación real
    }
}