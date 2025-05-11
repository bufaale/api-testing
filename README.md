# 🧪 JSON-Driven API Test Automation Framework

Este proyecto demuestra cómo diseñar un framework de automatización de pruebas de API profesional utilizando:

- **Serenity BDD + Screenplay Pattern**
- **RestAssured**
- **Estrategia Data-Driven basada en archivos JSON**
- **Validación de responses con reglas flexibles**
- **Manejo de variables dinámicas (como IDs) entre pasos**

---

## 🎯 Objetivos

- Aplicar principios **SOLID**, diseño limpio y desacoplado.
- Ejecutar flujos completos de prueba usando únicamente datos de archivos `.json`.
- Validar `statusCode`, `body`, `headers`, `schemas` (futuro).
- Recordar valores de respuesta (`remember`) para utilizarlos en steps posteriores.
- Loguear claramente cada `request`, `response` y variables recordadas.

---

## 🧱 Arquitectura del Framework

```
src/
├── main/
│   └── java/
│       └── starter/
│           ├── engine/             # Núcleo del framework: ejecución, validaciones, memoria
│           ├── tasks/              # Tasks que ejecutan los pasos del feature
│           ├── model/              # Modelos como TestRequestDefinition
│           ├── utils/              # Utilidades (ej: DeepJsonComparator)
├── test/
│   ├── java/
│   │   └── starter/
│   │       ├── stepdefinitions/    # Glue code BDD
│   │       └── TestRunner.java     # Runner de Cucumber + Serenity
│   └── resources/
│       ├── features/               # Archivos .feature
│       └── data/                   # Test cases en formato JSON
```

---

## 📄 Estructura de los test cases en JSON

```json
{
  "preconditions": [
    {
      "order": 0,
      "request": { ... },
      "response": { ... },
      "remember": {
        "fromResponse": [
          { "jsonPath": "$.id", "saveAs": "userId" }
        ]
      }
    }
  ],
  "test": {
    "request": {
      "url": "https://.../users/{userId}",
      "pathParams": {
        "userId": "{{userId}}"
      }
    },
    "response": {
      "statusCode": 200,
      "body": { ... }
    }
  }
}
```

---

## ✅ Ejemplo de Feature

```gherkin
Feature: Validate user creation using JSON-driven strategy

  Scenario: Successful user creation and retrieval
    Given the actor named "api-user"
    And the user is created with
      | testDefinitionPath    | section            | saveRequestAs   | saveResponseAs  |
      | data/create-user.json | FIRST_PRECONDITION | CREATE_USER_REQ | CREATE_USER_RES |
    Then the user is retrieved with
      | testDefinitionPath    | section | saveRequestAs | saveResponseAs |
      | data/create-user.json | TEST    | GET_USER_REQ  | GET_USER_RES   |
```

---

## 🛠 Tecnologías

- Java 17+
- Gradle
- Serenity BDD 4.x
- Cucumber 7.x
- RestAssured
- org.json
- SLF4J (opcional para logging)

---

## 📌 Mejoras futuras

- Validación contra JSON Schemas
- Soporte de múltiples tipos de validación (regex, contains, etc.)
- Export de reportes con resumen de ejecución
- Integración con Jenkins, GitHub Actions o GitLab CI

---

## 👨‍💻 Autor

**Alejandro Bufarini**  
Senior QA Automation Engineer  
🔗 [linkedin.com/in/alejandro-bufarini-113060129](https://linkedin.com/in/alejandro-bufarini-113060129)