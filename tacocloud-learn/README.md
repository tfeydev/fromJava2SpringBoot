# 🌮 TacoCloud-Learn Projekt

**Dieses Projekt basiert auf den ersten drei Kapiteln des Buches _Spring in Action (6. Auflage)_ von Craig Walls.**  
Es setzt die dort beschriebenen Backend-Konzepte mit **Spring Boot**, **Spring Data JPA** und einer **H2-In-Memory-Datenbank** um.  
Das ursprünglich verwendete Template-System **Thymeleaf** wird in diesem Projekt durch ein modernes **React + Vite Frontend** ersetzt (wird noch ergänzt).

---

## 📚 Bezug zum Buch: *Spring in Action (6th Edition)*

Die Kapitel 1–3 des Buches behandeln u. a.:

- Spring Boot Grundkonfiguration
- Entitäten und Datenmodellierung mit JPA
- Controller-Design und REST-Endpunkte
- Erste Weboberfläche mit Thymeleaf

🧠 **In diesem Projekt wird der Backend-Teil vollständig nachgebaut**, das Frontend jedoch modernisiert (React statt Thymeleaf).

---

## 🛠️ Einrichtung

### 🔙 Backend

Das Backend basiert auf Spring Boot mit H2-Datenbank.

#### 📦 Wichtige Maven-Abhängigkeit (`pom.xml`):

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

#### ⚙️ Konfiguration (`src/main/resources/application.properties`):

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

### 🧪 Starten der Anwendung:

```bash
./mvnw spring-boot:run
```

🖥 Zugriff auf H2-Konsole (optional):  
[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

### ⚛️ Frontend (React + Vite)

Das Frontend wird aktuell mit **React** und **Vite** neu aufgebaut.  
Weitere Details zur Integration folgen nach Abschluss der Backend-Entwicklung.

---

## 📦 Projektstruktur (Backend)

```
tacocloud-learn/
├── model/
│   ├── Taco.java
│   ├── TacoOrder.java
│   └── Ingredient.java
├── controller/
│   └── OrderController.java
├── repository/
│   └── OrderRepository.java
├── application.properties
└── TacocloudApplication.java
```

---

## ✍️ Anmerkungen

- Dieses Projekt wurde **neu gestartet**, um die Komplexität zu reduzieren und sich **strikt an die Spring-in-Action-Vorgaben** zu halten.
- Ziel ist es, die Kernkonzepte von Spring Boot und JPA sauber nachzuvollziehen.
- Die Frontend-Modernisierung folgt im zweiten Schritt.

---

## 👨‍💻 Autor

**Thorsten Fey**  
🔗 [github.com/tfeydev](https://github.com/tfeydev)

---

## 📜 Lizenz

MIT – frei nutzbar für Lern- und Ausbildungszwecke.
