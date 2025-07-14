# 🌮 TacoCloud Learn – Spring Boot 3 & React

This project documents my personal learning journey **from traditional Java to modern Spring Boot fullstack development** – based on the book *Spring in Action* (6th Edition, Craig Walls), extended with modern web development using **React**.

---

## ✅ Chapters 1–2: Spring Boot Foundation

### 📌 Completed Topics

- ✅ Spring Boot 3 with Java 21
- ✅ Thymeleaf for server-side views
- ✅ REST controller and form processing
- ✅ Validation using `@Valid` and Bean Validation (Hibernate Validator)
- ✅ Session management via `@SessionAttributes`
- ✅ Lombok usage: `@Data`, `@Slf4j`, etc.
- ✅ First CRUD structure: `TacoOrder`
- ✅ HTML form with error feedback
- ✅ Package structure: `br.com.techthor.tacocloud`

📁 Project: [`tacocloud-learn`](./)

📚 Source: *Spring in Action*, Chapters 1–2

---

## ⚛️ Chapter 3: Introducing React

### Why React?

Thymeleaf is great for simple server-rendered pages, but:

- Reactive UI behavior is limited
- Dynamic frontends and SPA architecture are hard to achieve
- Real-world Spring Boot fullstack projects commonly use React
- Clean separation between frontend and backend

### Goal

- Replace order and taco design pages with **React components**
- Connect to the Spring Boot backend via **REST APIs**
- Improve UI/UX with modern frontend technology

---

## 🔧 Technologies Used

| Layer      | Technology              |
| ---------- | ----------------------- |
| Backend    | Spring Boot 3 (Java 21) |
| Templates  | Thymeleaf               |
| Validation | Hibernate Validator     |
| Build Tool | Maven                   |
| Frontend   | upcoming: React         |
| Logging    | Lombok + SLF4J          |

---

## 🔄 Should Chapters 1–2 be Replaced?

**No.** The contents from chapters 1 and 2 are:

- **Up-to-date** with Spring Boot 3
- A great introduction to MVC and server-side rendering
- Helpful for understanding backend fundamentals

> Keep chapters 1 and 2 as a reference and foundation.

---

## 🛠️ Next Steps

-

---

## 📂 Project Structure

```bash
tacocloud-learn/
├── src/
│   ├── main/
│   │   ├── java/br/com/techthor/tacocloud/
│   │   └── resources/
│   │           └──static
│   │                └── images
│   │                styles.css
│   │           └──templates/
│   │                └── .html
│   └── test/
├── pom.xml
└── README.md
```

---

## 📩 Contact

Thorsten Fey – [GitHub @tfeydev](https://github.com/tfeydev)

---

## 📘 License

MIT License

