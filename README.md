# Sistema de Transporte Urbano

## Paso de Mensajes Asíncronos [![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

## Descripción del Problema

Se desea simular un sistema de transporte urbano para gestionar la movilidad de una ciudad mediante líneas de autobuses urbanos. El sistema permitirá a los usuarios planificar sus viajes desde un origen hasta un destino, incluso cuando estos puntos no se encuentren en la misma línea de transporte, requiriendo transbordos. Para resolver este problema, utilizaremos el paradigma de paso de mensajes asíncronos mediante JMS (Java Message Service) con ActiveMQ como broker de mensajes.

El sistema estará compuesto por tres tipos principales de actores que se comunicarán mediante intercambio de mensajes asíncronos:

-   **Autobuses**: Vehículos asociados a una línea específica que realizan un recorrido predefinido entre paradas.
-   **Usuarios**: Pasajeros que desean desplazarse desde un origen hasta un destino.
-   **Controlador del Sistema**: Entidad central que coordina todo el sistema, calcula rutas y gestiona incidencias.

La comunicación entre estos actores se realizará de forma asíncrona, permitiendo que el sistema sea altamente escalable y pueda manejar un gran número de solicitudes concurrentes.

## Objetivos

-   Implementar un sistema distribuido basado en el paradigma de paso de mensajes asíncronos.
-   Utilizar JMS como tecnología de comunicación y ActiveMQ como broker de mensajes.
-   Aplicar patrones de diseño adecuados para sistemas distribuidos.
-   Desarrollar un mecanismo eficiente para el cálculo de rutas que pueden implicar transbordos entre líneas.
-   Gestionar la concurrencia y sincronización entre los diferentes actores del sistema.
-   Implementar un sistema de seguimiento que permita a los usuarios conocer el estado de los autobuses en tiempo real.
-   Desarrollar un mecanismo de notificaciones para informar a los usuarios sobre incidencias, retrasos o cambios en el servicio.

## Estructura del Sistema

### 1. Componentes Principales

#### 1.1. Autobuses (`TareaAutobus`)

-   Cada autobús estará asociado a una única línea de transporte.
-   Informará periódicamente su posición actual (parada actual o tramo entre paradas).
-   Notificará su capacidad disponible y nivel de ocupación.
-   Responderá a eventos como averías, retrasos o desvíos.
-   Gestionará el embarque y desembarque de pasajeros.

#### 1.2. Usuarios (`TareaUsuario`)

-   Solicitarán rutas desde un origen hasta un destino.
-   Podrán consultar el estado actual de los autobuses y las líneas.
-   Recibirán notificaciones sobre sus viajes planificados.
-   Informarán al sistema cuando inicien y finalicen un viaje.
-   Podrán comunicar incidencias observadas durante el viaje.

#### 1.3. Controlador del Sistema (`TareaControlador`)

-   Gestionará la información global del sistema.
-   Calculará rutas óptimas entre puntos de la ciudad, incluyendo transbordos si son necesarios.
-   Monitorizará el estado de todos los autobuses y líneas.
-   Gestionará incidencias y proporcionará soluciones alternativas.
-   Generará estadísticas sobre el funcionamiento del sistema.

### 2. Definición de Destinos JMS
Para los destinos hay que incluir el nombre del grupo para que no se mezclen los mensajes entre diferentes grupos de prácticas.

Para implementar la comunicación asíncrona, utilizaremos los siguientes destinos JMS:

#### 2.1. Colas (Queue) - Comunicación punto a punto

-   **`autobus.{idLinea}.{idAutobus}.comando`**: Cola para enviar comandos específicos a un autobús (por ejemplo, desvíos o paradas especiales).
-   **`autobus.estado`**: Cola donde todos los autobuses publican su estado actual (posición, capacidad, etc.).
-   **`usuario.{idUsuario}.notificacion`**: Cola para enviar notificaciones personalizadas a un usuario específico.
-   **`usuario.{idUsuario}.respuestaRuta`**: Cola donde el controlador envía respuestas a solicitudes de ruta de un usuario concreto.
-   **`controlador.solicitudRuta`**: Cola donde los usuarios envían solicitudes de cálculo de ruta.
-   **`controlador.incidencia`**: Cola para informar incidencias al controlador (desde autobuses o usuarios).

#### 2.2. Tópicos (Topic) - Publicación/Suscripción

-   **`topico.linea.{idLinea}.estado`**: Tópico donde se publica información actualizada sobre el estado de una línea específica.
-   **`topico.sistema.alertas`**: Tópico para alertas generales del sistema que puedan afectar a múltiples usuarios o líneas.

### 3. Clases de Datos para Mensajes

Todos los mensajes intercambiados en el sistema serán registros Java convertidos a formato JSON para su transmisión. Se incluyen en el proyecto las siguientes clases:

#### 3.1. Mensajes de Estado

Mensaje del estado de cada autobús en un momento específico.

#### 3.2. Mensajes de Solicitud/Respuesta de Ruta

Mensajes del usuario para su tránsito en el sistema de transporte.

#### 3.3. Mensajes de Incidencias

Mensajes para comunicar incidencias en el sistema.

#### 3.4. Mensajes de Comandos

Mensajes de órdenes que pueden recibir los autobuses.

## Flujo Principal del Sistema

1.  **Inicialización**:    
    -   El controlador se inicia y carga la información de todas las líneas, paradas y conexiones.
    -   Los autobuses se registran en el sistema indicando su línea asignada.
    -   Los usuarios se conectan al sistema desde sus dispositivos.

2.  **Operación Normal**:
    -   Los autobuses envían periódicamente actualizaciones de su posición y estado.
    -   El controlador mantiene una visión global del sistema.
    -   Los usuarios pueden consultar el estado actual del sistema y planificar viajes.

3.  **Planificación de Viaje**:    
    -   Un usuario solicita una ruta desde un origen hasta un destino.
    -   El controlador calcula la ruta óptima, que puede incluir transbordos entre líneas.
    -   El controlador envía la ruta calculada al usuario.

4.  **Realización del Viaje**:    
    -   El usuario notifica al sistema que inicia su viaje.
    -   El sistema monitoriza el progreso del usuario y le envía notificaciones relevantes (próxima parada, transbordo, etc.).
    -   Los autobuses gestionan el embarque y desembarque de pasajeros.
    -   El usuario notifica al sistema cuando finaliza su viaje.

5.  **Gestión de Incidencias**:    
    -   Si ocurre una incidencia (retraso, avería, etc.), puede ser informada por un autobús o un usuario.
    -   El controlador procesa la incidencia y toma las medidas necesarias (recálculo de rutas, desvíos, servicios alternativos, etc.).
    -   Se notifica a todos los usuarios afectados sobre la incidencia y las alternativas disponibles.

## Implementación del Sistema

### 1. Conexión a ActiveMQ

Todas las tareas deben establecer una conexión con el broker ActiveMQ utilizando la definición de la `URL` proporcionada en la interface `Constantes`:

```
String  BROKER_URL  =  "tcp://suleiman.ujaen.es:8018"
```

### 2. Clases de Tareas Principales

#### 2.1. TareaAutobus

Esta es la tarea simula las operaciones que debe completar un autobús en su línea de transporte asignada.

#### 2.2. TareaUsuario

Esta tarea simula el comportamiento de un usuario dentro del sistema de transporte. Cada usuario deberá completar un viaje dentro del sistema.

#### 2.3. TareaControlador

Esta tarea controla todo el sistema de transporte y coordinará los usuarios con los autobuses presentes en el sistema. 


## Requisitos de Implementación

1.  **JMS y ActiveMQ**:
    -   Utilizar JMS como API para el paso de mensajes.
    -   Implementar la comunicación usando ActiveMQ como broker de mensajes.
    
2.  **Formato de Mensajes**:
    -   Todos los mensajes deben ser registros Java convertidos a formato JSON.
    -   Utilizar la biblioteca adecuada para la serialización/deserialización JSON.

3.  **Destinos**:    
    -   Implementar correctamente todos los destinos (colas y tópicos) definidos.
    -   Utilizar una convención de nombres clara para los destinos.
    -   Añadir como prefijo el nombre del grupo de la práctica para el tratamiento de los mensajes que cada grupo genera y que no se mezclen con el resto de grupos.
  
4.  **Clases**:  
    -   Implementar todas las clases y métodos definidos en la propuesta.
    -   Añadir comentarios detallados que expliquen el propósito de cada método.
    -   Mantener todos los atributos de las clases como privados.
    -   Se debe añadir todo lo que sea necesario para la solución del problema

5.  **Concurrencia**:
    -   Manejar adecuadamente los aspectos de concurrencia en el sistema.
    -   Prevenir condiciones de carrera y problemas de sincronización.

6.  **Pruebas**:    
    -   Implementar pruebas unitarias para todas las clases del proyecto.
    -   Desarrollar pruebas de integración para verificar el funcionamiento del sistema completo.

## Criterios de Evaluación

### Para obtener APROBADO (5-6)
-   Implementación básica de la comunicación entre autobuses, usuarios y controlador mediante JMS.
-   Correcto uso de colas y tópicos para los escenarios básicos.
-   Manejo básico de rutas sin transbordos entre origen y destino.
-   Código documentado con comentarios básicos.

### Para obtener NOTABLE (7-8)
-   Implementación completa de todos los componentes descritos.
-   Gestión correcta de transbordos entre líneas para alcanzar el destino.
-   Sistema de notificaciones a usuarios funcionando correctamente.
-   Manejo adecuado de errores y excepciones.
-   Código bien documentado con comentarios descriptivos.

### Para obtener SOBRESALIENTE (9-10)
-   Implementación avanzada con optimizaciones de rendimiento.
-   Sistema completo sin fallos bajo condiciones de alta carga.
-   Gestión avanzada de incidencias con recálculo de rutas y soluciones alternativas.
-   Estadísticas detalladas del funcionamiento del sistema.
-   Implementación de características opcionales como:
    -   Sistema de predicción de tiempos de llegada basado en datos históricos.
    -   Optimización de rutas en tiempo real según condiciones de tráfico.
    -   Gestión de usuarios con perfiles y preferencias.

## Recomendaciones para la Implementación

1.  **Configuración del entorno**: 
    -   Configurar ActiveMQ antes de comenzar con la implementación.
    -   Verificar la correcta conexión con el broker de mensajes.
    
2.  **Modularización**:
    -   Dividir el problema en módulos más pequeños y desarrollarlos incrementalmente.
    -   Probar cada módulo por separado antes de integrarlos.

3.  **Manejo de errores**:
    -   Implementar un sistema robusto de manejo de errores para evitar fallos del sistema.
    -   Considerar estrategias de recuperación ante fallos del broker de mensajes.

4.  **Pruebas**:
    -   Realizar pruebas unitarias de cada componente.
    -   Probar el sistema completo con diferentes escenarios.
    -   Realizar pruebas de carga para verificar el rendimiento del sistema.

5.  **Documentación**:
    -   Documentar adecuadamente el código y las decisiones de diseño.
    -   Mantener un registro de los problemas encontrados y sus soluciones.
