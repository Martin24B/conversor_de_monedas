# Conversor de Monedas

## Índice
1. [Estado del Proyecto](#estado-del-proyecto)
2. [Descripción](#descripción)
3. [Funcionalidades](#funcionalidades)
4. [Cómo Usarlo](#cómo-usarlo)
5. [Requisitos](#requisitos)
6. [Tecnologías Utilizadas](#tecnologías-utilizadas)
7. [Ayuda y Documentación](#ayuda-y-documentación)
8. [Autor](#autor)

---

## Estado del Proyecto
**Back-end finalizado**, con posibilidades de futuras actualizaciones.

---

## Descripción
Este proyecto es un desafío propuesto por **Oracle Education** en asociación con **Alura Latam**. Consiste en un conversor de monedas que consume una API para consultar las tasas de cambio actuales del mercado y las equivalencias monetarias entre diferentes monedas.

El programa permite a los usuarios obtener información en tiempo real sobre el mercado financiero global de forma sencilla y eficiente.

---

## Funcionalidades
La API posee 4 endpoints principales, cada uno con funcionalidades específicas:

1. **`latest`**  
   - Permite conocer las tasas de cambio actuales para cada moneda del mercado en relación a una moneda asignada.

2. **`codes`**  
   - Devuelve una lista completa de todas las monedas activas actualmente en el mercado.

3. **`pair`**  
   - **Función 1**: Consulta la tasa de cambio actual entre dos monedas asignadas.  
   - **Función 2**: Calcula la equivalencia monetaria entre dos monedas asignadas basado en un monto específico.

4. **`quota`**  
   - Permite conocer la cuota actual de solicitudes disponibles para el usuario.

---

## Cómo Usarlo
### Acceso al Proyecto
1. Clonar o descargar este repositorio desde GitHub.
2. Descargar e instalar un editor de texto o IDE, como **Eclipse**.
3. Abrir el proyecto en el IDE.
4. Ejecutar el programa desde la consola, interactuando con las opciones ofrecidas.
5. Mantener abierta la carpeta `userData` para visualizar los datos generados por las consultas.

---

## Requisitos
- **Java** instalado en el sistema (JDK 11 o superior recomendado).
- Conexión activa a internet para realizar las consultas a la API.

---

## Tecnologías Utilizadas
- **JDK (Java Development Kit)**: Herramienta principal para ejecutar programas en Java.
- **Gson**: Librería para la manipulación y deserialización de datos en formato JSON.
- **Java HttpClient**: Realización de solicitudes HTTP modernas y eficientes.
- **Eclipse IDE**: Ambiente de desarrollo integrado utilizado para programar el proyecto.

---

## Ayuda y Documentación
Para más información sobre el funcionamiento de la API utilizada en este proyecto, consulta:  
[Documentación oficial de Exchangerate-API](https://www.exchangerate-api.com/docs/overview)

---

## Autor
Proyecto desarrollado por **Martin24B**.  
Contacto: [alexmartin9c@gmail.com](mailto:alexmartin9c@gmail.com)

---

