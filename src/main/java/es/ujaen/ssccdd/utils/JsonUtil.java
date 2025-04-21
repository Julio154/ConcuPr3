package es.ujaen.ssccdd.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;


import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.List;

public class JsonUtil {
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
            .create();

    /**
     * Crea la representación Json desde un obejto dado
     * @param objeto el objeto a transformar
     * @return el String en formato Json que lo representa
     */
    public static String toJson(Object objeto) {
        return gson.toJson(objeto);
    }

    /**
     * Obtiene el objeto que está representado en el String con formato Json
     * @param json el String desde donde se quiere reconstruir el objeto
     * @param clase la clase a la que pertenece el objeto
     * @return el objeto reconstruido
     * @param <T> la clase del objeto
     */
    public static <T> T fromJson(String json, Class<T> clase) {
        return gson.fromJson(json, clase);
    }

    /**
     * Obtiene una lista de objetos representada por el String en formato Json de la lista
     * @param json el String que representa la lista que se quiere reconstruir
     * @param clase la clase de los objetos que conforman la lista
     * @return la lista reconstruida
     * @param <T> la clase de los objetos de la lista
     */
    public static <T> List<T> fromJsonToList(String json, Class<T> clase) {
        Type tipoLista = TypeToken.getParameterized(List.class, clase).getType();
        return gson.fromJson(json, tipoLista);
    }

    /**
     * Transforma un objeto dado a una representación intermedia JsonElement
     * @param objeto el objeto que se desea transformar
     * @return la transformación a JsonElement
     */
    public static JsonElement toJsonElement(Object objeto) {
        return gson.toJsonTree(objeto);
    }

    /**
     * Reconstruye un objeto desde su representación JsonElement
     * @param jsonElement la representación del objeto en JsonElement
     * @param clase la clase a la que pertenece el objeto
     * @return el objeto reconstruido
     * @param <T> la clase que representa en el objeto
     */
    public static <T> T fromJsonElement(JsonElement jsonElement, Class<T> clase) {
        return gson.fromJson(jsonElement, clase);
    }

    /**
     * TypeAdapter para serializar y deserializar objetos Instant
     */
    private static class InstantTypeAdapter extends TypeAdapter<Instant> {
        @Override
        public void write(JsonWriter out, Instant value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value.toString()); // Serializa como cadena ISO-8601
            }
        }

        @Override
        public Instant read(JsonReader in) throws IOException {
            if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            String instantStr = in.nextString();
            return Instant.parse(instantStr); // Deserializa desde cadena ISO-8601
        }
    }
}
