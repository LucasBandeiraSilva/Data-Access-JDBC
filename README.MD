## Java JDBC Project: Mastering Database Access

This Java project showcases best practices for database interaction using JDBC (Java Database Connectivity), focusing on robust data access and manipulation within a MySQL database. The project demonstrates CRUD (Create, Read, Update, Delete) operations on a sample database, emphasizing clean code structure, object-oriented design, and efficient exception handling.

**Key Features:**

*   **JDBC Implementation:** Leverages the JDBC API for seamless communication with a MySQL database.
*   **DAO Pattern:**  Implements the Data Access Object (DAO) design pattern to decouple database interaction logic from business logic.
*   **Organized Structure:**  Utilizes packages to neatly organize classes and improve code maintainability (e.g., `model`, `dao`, `db`).
*   **Exception Handling:** Incorporates custom exceptions (`DbException`) to provide informative error messages and facilitate debugging.
*   **Connection Management:**  The `DB` class manages database connections efficiently, including opening, closing, and handling potential connection errors.
*   **Factory Design Pattern:** Employs a `DaoFactory` to abstract the instantiation of DAO objects, promoting loose coupling and easier maintenance.

**Project Structure:**

*   **`model.entities`:** Contains entity classes representing database tables (e.g., `Seller`, `Department`).
*   **`model.dao`:**  Houses interfaces (`SellerDao`, `DepartmentDao`) defining data access methods, and a factory class (`DaoFactory`) for creating DAO instances.
*   **`model.dao.impl`:** Contains concrete implementations of the DAO interfaces using JDBC (e.g., `SellerDaoJDBC`, `DepartmentDaoJDBC`).
*   **`db`:** Holds database utility classes for managing connections and handling exceptions (`DB`, `DbException`).
*   **`application`:** Contains the main application classes demonstrating usage of the DAO pattern to interact with the database.

**DaoFactory Explanation:**

The `DaoFactory` class plays a crucial role in decoupling the application from concrete DAO implementations. Instead of directly creating DAO objects, the application requests them from the factory. This approach offers several advantages:

*   **Loose Coupling:** Changes in DAO implementations won't affect the application, promoting maintainability and flexibility.
*   **Centralized Configuration:**  Simplifies switching between different database implementations (e.g., MySQL, PostgreSQL) by modifying the factory logic.
*   **Testability:**  Facilitates unit testing by easily substituting real DAO implementations with mocks.

**Requirements:**

*   MySQL Workbench (or a similar MySQL client)
*   Java Development Kit (JDK) 8 or above
*   MySQL Java Connector (Download and add to your project's classpath)

**Technologies:**

*   Java
*   JDBC
*   MySQL

**Getting Started:**

1.  **Database Setup:**
    *   Create a MySQL database named `courseJDBC` (or choose a different name).
    *   Execute the following SQL script in your MySQL client to create the necessary tables and sample data:

```sql
CREATE TABLE department (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(60) DEFAULT NULL,
  PRIMARY KEY (Id)
);

CREATE TABLE seller (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(60) NOT NULL,
  Email varchar(100) NOT NULL,
  BirthDate datetime NOT NULL,
  BaseSalary double NOT NULL,
  DepartmentId int(11) NOT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (DepartmentId) REFERENCES department (id)
);

INSERT INTO department (Name) VALUES 
  ('Computers'),
  ('Electronics'),
  ('Fashion'),
  ('Books');

INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES 
  ('Bob Brown','bob@gmail.com','1998-04-21 00:00:00',1000,1),
  ('Maria Green','maria@gmail.com','1979-12-31 00:00:00',3500,2),
  ('Alex Grey','alex@gmail.com','1988-01-15 00:00:00',2200,1),
  ('Martha Red','martha@gmail.com','1993-11-30 00:00:00',3000,4),
  ('Donald Blue','donald@gmail.com','2000-01-09 00:00:00',4000,3),
  ('Alex Pink','bob@gmail.com','1997-03-04 00:00:00',3000,2);
```

2.  **Configuration:** Update the `db.properties` file with your database credentials:

```bash
user=your-userName
password=passwordd-here
dburl=jdbc:mysql://localhost:3306/your-dataBase
useSSL=false
```

3.  **Compile and Run:**  Compile and run the Java application within your IDE.

This project serves as a foundational example of how to build a well-structured and maintainable application with database interaction capabilities using JDBC.

**Developed by:** Lucas Bandeira da Silva - [GitHub](https://github.com/LucasBandeiraSilva) 
