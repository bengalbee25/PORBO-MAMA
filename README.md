
## 📚 PORBO MAMA - Book Management System

### 🔍 Overview

**PORBO MAMA** is a Java-based desktop application that allows users to sign up, log in, and manage their book collection. It features image previews, PDF access, and secure user-specific data using a MySQL database.

---

### 📌 Features

* User Sign-Up and Login
* Upload book covers and PDF files
* View and manage personal book collection
* GUI built using Java Swing
* Secure and user-specific data handling with MySQL
* Responsive design using FlatLaf theme

---

### 🛠 Technologies Used

| Tool    | Purpose                             |
| ------- | ----------------------------------- |
| Java    | Core programming language           |
| Swing   | GUI components                      |
| MySQL   | Database for storing user/book data |
| FlatLaf | Modern look and feel for UI         |
| JDBC    | Database connectivity               |

---

### 🖥️ Installation and Setup

1. **Clone the repository**

   ```bash
   git clone https://github.com/your-username/PORBO-MAMA.git
   ```

2. **Set up the database**

   * Open MySQL and run the SQL script from `/database/porbo_mama.sql`
   * Create a user and update credentials in the source code if needed.

3. **Run the application**

   * Open the project in NetBeans/IntelliJ/Eclipse
   * Build and run `loginsignup.java`

---

### 📂 Project Structure

```
porbo-mama/
│
├── src/
│   └── PORBO_MAMA/
│       ├── loginsignup.java
│       ├── login.java
│       ├── signup.java
│       ├── viewbooks1.java
│       ├── about.java
│       └── ...
│
├── resources/
│   ├── logo1.png
│   └── library.jpg
│
├── database/
│   └── porbo_mama.sql
│
├── README.md
└── LICENSE
```

---

### ✅ Requirements

* Java 8 or later
* MySQL 5.7 or later
* IDE (e.g., NetBeans, IntelliJ)
* JDBC Driver (MySQL Connector/J)

---

### 🗃️ Database Schema

Includes tables:

* `users`
* `user_books`

> Full schema in [`/database/porbo_mama.sql`](./database/porbo_mama.sql)

---

### 📈 Future Enhancements

* Add user profile and avatar
* Role-based admin panel
* Advanced search and filtering
* Export book list to Excel/PDF
* Online cloud sync for books

---

### 🙏 Acknowledgements

* FlatLaf for UI themes
* MySQL for backend data storage
* Java Swing for rich GUI components
* Inspiration from online Java tutorials and courses

---

### 📝 License

This project is licensed under Apache License 2.0. See the [LICENSE](./LICENSE) file for details.

