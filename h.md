# SwiftSTS - Swift Student Transport Service

SwiftSTS is a layered Java application designed to simplify and digitize the management of school transport services. It handles student attendance, monthly payment calculations, driver and vehicle management, and includes user authentication with email verification.

---

## 🚀 Features

* 🧑‍🎓 Student Management (CRUD)

* 🚌 Driver & Vehicle Management(CRUD)

* 📅 Attendance Tracking (Day/Month Based)

* 💰 Monthly Payment Calculation based on attendance

* 🔐 Role-based Login System (Admin & Driver)

* 📩 Password Recovery via Email

* 🧾 **JasperReports Integration** for professional **Payment Receipt Generation** in PDF

* 📊 Dashboard Overview

---

## 📁 Project Structure (Layered Architecture)

```
SwiftSTS-Layered/
├── controller/
├── dao/
├── db/
├── dto/
├── entity/
├── service/
├── util/
├── view/
└── resources/
```

---

## 🛠 Technologies Used

* Java 21
* JavaFX
* MySQL
* JDBC
* JasperReports
* Maven 
* SceneBuilder (for UI design)

---

## 📷 Screenshots

*(Include screenshots of Login, Dashboard, Payment Table, and Generated Receipt)*

---

## 🔧 How to Run

1. Clone the repository
2. Set up the MySQL database using provided scripts
3. Import the project into IntelliJ IDEA or NetBeans
4. Build and run the project
5. Use SceneBuilder to modify UI (if needed)

---

## 📄 Database Setup

* MySQL with tables:

    * `user`
    * `student`
    * `driver`
    * `vehicle`
    * `attendance`
    * `payment`

Import the SQL file from `db/` directory to set up schema.

---

## 📬 Contributors

* Lithira Jayanaka
  [LinkedIn](https://www.linkedin.com/in/yourprofile) | [GitHub](https://github.com/yourusername)

---

## 📃 License

This project is for educational purposes. Licensing terms may be added later.
