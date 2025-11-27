CREATE TABLE Students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    roll_number INT UNIQUE NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100) UNIQUE NOT NULL,
    photograph_path VARCHAR(255),
    cgpa DECIMAL(3, 2),
    total_credits INT,
    graduation_year year,
    domain_id INT,
    specialisation_id INT,
    placement_id INT
);

CREATE TABLE Courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_code VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    year INT NOT NULL,
    term INT NOT NULL,
    faculty VARCHAR(500),
    credits INT NOT NULL,
    capacity INT NOT NULL
);

CREATE TABLE Employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    email VARCHAR(100) UNIQUE NOT NULL,
    title VARCHAR(50),
    photograph_path VARCHAR(255),
    department_id INT
);

CREATE TABLE course_prerequisite (
    course_prerequisite_id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT,
    prerequisite_id INT,
    description TEXT
);

CREATE TABLE Student_Courses (
    student_course_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    grade_id INT,
    comments VARCHAR(500)
);

CREATE TABLE Faculty_Courses (
    faculty_course_id INT AUTO_INCREMENT PRIMARY KEY,
    faculty INT,
    course_id INT
);

-- SUPPORTING TABLES TO IMPOSE FOREIGN KEY CONSTRAINTS
CREATE TABLE Domain(
    domain_id INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Specialisation(
    specialisation_id INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Grades(
    grade_id INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Departments(
    department_id INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Placements(
    placement_id INT AUTO_INCREMENT PRIMARY KEY
);

