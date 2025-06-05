-- Create the database
CREATE DATABASE IF NOT EXISTS airwaysdb;
USE airwaysdb;

-- Table 1: passenger
CREATE TABLE IF NOT EXISTS passenger (
    aadhar VARCHAR(12) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    nationality VARCHAR(50),
    address VARCHAR(200),
    gender VARCHAR(10),
    phone VARCHAR(10)
);

-- Table 2: flight
CREATE TABLE IF NOT EXISTS flight (
    f_code VARCHAR(10) PRIMARY KEY,
    f_name VARCHAR(100) NOT NULL,
    source VARCHAR(50) NOT NULL,
    destination VARCHAR(50) NOT NULL,
    time VARCHAR(20),
    price DECIMAL(10, 2),
    seats_available INT DEFAULT 0
);

-- Table 3: reservation
CREATE TABLE IF NOT EXISTS reservation (
    pnr VARCHAR(20) PRIMARY KEY,
    ticket VARCHAR(20),
    aadhar VARCHAR(12),
    name VARCHAR(100),
    nationality VARCHAR(50),
    f_name VARCHAR(50),
    f_code VARCHAR(20),
    source VARCHAR(50),
    destination VARCHAR(50),
    travel_date DATE,
    time VARCHAR(20),
    price DECIMAL(10,2),
    booking_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (aadhar) REFERENCES passenger(aadhar),
    FOREIGN KEY (f_code) REFERENCES flight(f_code)
);



-- Table 4: booking_log (for rate limiting)
CREATE TABLE IF NOT EXISTS booking_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aadhar VARCHAR(12),
    booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (aadhar) REFERENCES passenger(aadhar)
);

-- Table 5: login
CREATE TABLE IF NOT EXISTS login (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role ENUM('Admin', 'Agent') NOT NULL
);

    
-- Table 6: cancel
CREATE TABLE IF NOT EXISTS cancel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pnr VARCHAR(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    cancellationno VARCHAR(20) NOT NULL,
    flightcode VARCHAR(20) NOT NULL,
    ddate DATE NOT NULL,
    cancelled_on DATETIME DEFAULT CURRENT_TIMESTAMP
);


-- Table 7: payment
CREATE TABLE IF NOT EXISTS payment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pnr VARCHAR(20) NOT NULL,
    flightcode VARCHAR(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    status VARCHAR(20) DEFAULT 'Paid',
    payment_date DATE DEFAULT CURRENT_DATE
);

