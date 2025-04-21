package es.ujaen.ssccdd;

import java.util.Random;

public interface Constantes {
    // Generador aleatorio
    Random aleatorio = new Random();

    enum EstadoAutobus {
        EN_SERVICIO, EN_PARADA, EN_MOVIMIENTO, FUERA_DE_SERVICIO, RETRASADO
    }

    enum CriterioRuta {
        MAS_RAPIDO, MENOS_TRANSBORDOS, MENOS_CAMINATA
    }

    enum TipoIncidencia {
        RETRASO, AVERIA, SOBRECARGA, VIA_BLOQUEADA, ACCIDENTE, OTRO
    }

    enum TipoComando {
        SIGUIENTE_PARADA, SALTAR_PARADA, FINALIZAR_SERVICIO, PARADA_EMERGENCIA
    }

    String BROKER_URL = "tcp://suleiman.ujaen.es:8018";
}
