# ✈️ AirConnect Hub – Java Swing + MySQL Airways Management System

## 🔍 Project Overview

**AirConnect Hub** is a comprehensive Airways Management System developed using **Java Swing** and **MySQL**. It supports multiple user roles like **Admin** and **Agent**, and handles essential airline operations including flight booking, flight management, passenger handling, and billing.

The system includes strong validation, real-time data updates, and secure role-based access.



## 💼 Key Features

### 👥 User Login & Roles
- Login/Register system with roles: **Admin** / **Agent**
- Role-based access to respective modules

### 🧾 Flight Booking
- Book tickets by selecting source, destination, and travel date
- Auto-fetch passenger details using Aadhaar
- Real-time seat availability
- Generates unique **PNR** and **Ticket Number**
- **Rate Limiting:** One booking per Aadhaar every **2 minutes**
- Strong input validation (e.g., name format, Aadhaar number)

### ✈️ Flight Management (Admin Only)
- Add, update, or delete flights
- Manage source, destination, timing, price, and available seats

### 📊 Billing & Reports
- View all bookings
- Calculate total revenue
- Filter by travel date (Admin only)

### 📋 Booking Logs
- Every booking attempt is logged
- Used for rate-limiting to prevent spam or abuse



## 🛠️ Tech Stack

| Layer            | Technology                  |
|------------------|------------------------------|
| Frontend         | Java Swing (JFrame, JPanel)  |
| Backend          | MySQL                        |
| DB Connection    | JDBC (custom `conn` class)   |
| Date Picker      | JDateChooser (Toedter library) |
| Icons/Images     | Local image resources        |

---

## 🧱 Database Tables

### `passenger`
Stores passenger details like Aadhaar, name, nationality, address, and gender.

### `flight`
Stores flight details such as name, code, source, destination, departure time, ticket price, and available seats.

### `reservation`
Stores booking records with PNR, ticket number, flight and passenger details, travel date, time, and price.

### `booking_log`
Logs every booking attempt with Aadhaar and timestamp. Used to implement booking rate-limiting per user.

### `login`
Contains login credentials and user roles (Admin or Agent) for role-based access control.

### `cancel`
Logs cancelled bookings with PNR, cancellation date, and optional reason for cancellation.

### `payment`
Stores payment-related details including payment ID, associated PNR, amount paid, method (UPI/Card/etc.), and date.



##  Optional: Setup Instructions
1. Clone the repository
2. Create the MySQL database: `airwaysdb`
3. Import the SQL schema (provided separately)
4. Update database credentials in `conn.java`
5. Run the project using any Java IDE (e.g., NetBeans or IntelliJ)
