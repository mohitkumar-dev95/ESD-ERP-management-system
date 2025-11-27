-- Insert Domains
INSERT INTO Domain (domain_id) VALUES
(1),  -- Technology
(2);  -- Business

-- Insert Departments
INSERT INTO Departments (department_id) VALUES
(1),  -- Department of Computer Science
(2),
(3);  -- Department of Information Technology

-- Insert Grades
INSERT INTO Grades (grade_id) VALUES
(1),  -- Grade A
(2),  -- Grade B
(3);  -- Grade C

INSERT INTO Placements (placement_id) VALUES
(1),
(2),
(3);

INSERT INTO Specialisation (specialisation_id) VALUES
(1),
(2),
(3);

-- Insert Courses
INSERT INTO Courses (course_code, name, description, year, term, faculty, credits, capacity) VALUES
('CS101', 'Introduction to Programming', 'Basics of programming in C language', 2024, 1, 'Prof. John Smith', 3, 50),
('CS102', 'Data Structures', 'Introduction to data structures', 2024, 1, 'Prof. Alice Johnson', 4, 50),
('CS103', 'Algorithms', 'Introduction to algorithms', 2024, 1, 'Prof. Sarah Lee', 3, 50),
('CS104', 'Database Systems', 'Introduction to database systems', 2024, 2, 'Dr. Michael Clark', 4, 50),
('CS105', 'Operating Systems', 'Introduction to operating systems', 2024, 2, 'Dr. Karen White', 4, 50),
('CS106', 'Software Engineering', 'Introduction to software engineering', 2024, 1, 'Prof. Emily Davis', 3, 50);

-- Insert Student Course Prerequisites
INSERT INTO Course_Prerequisite (course_id, prerequisite_id, description) VALUES
(2, 1, 'CS101 is a prerequisite for CS102'),
(3, 2, 'CS102 is a prerequisite for CS103'),
(4, 2, 'CS102 is a prerequisite for CS104'),
(5, 3, 'CS103 is a prerequisite for CS105');

-- Insert Students
INSERT INTO Students (roll_number, first_name, last_name, email, photograph_path, cgpa, total_credits, graduation_year, domain_id, specialisation_id, placement_id, password) VALUES
(101, 'John', 'Doe', 'john.doe@example.com', '/images/john.jpg', 3.8, 120, 2024, 1, 1, 1, 'hashed_password_1'),
(102, 'Jane', 'Smith', 'jane.smith@example.com', '/images/jane.jpg', 3.6, 110, 2025, 2, 2, 2, 'hashed_password_2');

-- Insert Student Course Selections
INSERT INTO Student_Courses (student_id, course_id, grade_id, comments) VALUES
(1, 1, 1, 'Excellent performance in CS101'),
(1, 2, 2, 'Good performance in CS102'),
(1, 3, 1, 'Excellent performance in CS103'),
(1, 4, 2, 'Good performance in CS104'),
(2, 1, 1, 'Excellent performance in CS101'),
(2, 2, 2, 'Good performance in CS102'),
(2, 3, 1, 'Excellent performance in CS103'),
(2, 5, 2, 'Good performance in CS105');

-- Insert Employees (Faculty)
INSERT INTO Employees (first_name, last_name, email, title, photograph_path, department_id) VALUES
('John', 'Smith', 'john.smith@university.edu', 'Professor', '/images/john_smith.jpg', 1),
('Alice', 'Johnson', 'alice.johnson@university.edu', 'Professor', '/images/alice_johnson.jpg', 2),
('Sarah', 'Lee', 'sarah.lee@university.edu', 'Professor', '/images/sarah_lee.jpg', 1),
('Michael', 'Clark', 'michael.clark@university.edu', 'Dr.', '/images/michael_clark.jpg', 3),
('Karen', 'White', 'karen.white@university.edu', 'Dr.', '/images/karen_white.jpg', 3),
('Emily', 'Davis', 'emily.davis@university.edu', 'Professor', '/images/emily_davis.jpg', 1);

-- Insert Faculty Courses
INSERT INTO Faculty_Courses (faculty, course_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6);

-- Insert Extra Courses
INSERT INTO courses (course_code, name, description, year, term, faculty, credits, capacity) VALUES
('CS111', 'Cloud Computing', 'Introduction to cloud computing concepts', 2024, 1, 'Dr. Steven Grant', 3, 50),
('CS112', 'Cybersecurity', 'Fundamentals of cybersecurity and data protection', 2024, 2, 'Prof. Linda Green', 4, 50),
('CS113', 'Data Science', 'Introduction to data science and data analysis', 2024, 1, 'Dr. Alan Watson', 3, 50),
('CS114', 'Mobile Application Development', 'Building mobile applications for Android and iOS', 2024, 2, 'Dr. Olivia Black', 3, 50),
('CS115', 'Digital Signal Processing', 'Fundamentals of digital signal processing', 2024, 1, 'Prof. Victor Hughes', 3, 50),
('CS116', 'Game Development', 'Introduction to game development and design principles', 2024, 2, 'Prof. Mark Walker', 3, 50);

-- Insert Student Course Prerequisites
INSERT INTO course_prerequisite (course_id, prerequisite_id, description) VALUES
(11, 2, 'CS102 is a prerequisite for CS111'),
(12, 6, 'CS106 is a prerequisite for CS112'),
(13, 2, 'CS102 is a prerequisite for CS113'),
(14, 2, 'CS107 is a prerequisite for CS114'),
(15, 3, 'CS103 is a prerequisite for CS115'),
(16, 12, 'CS112 is a prerequisite for CS116');

-- Insert Faculty Courses for new courses
INSERT INTO faculty_courses (faculty, course_id) VALUES
(1, 11),  -- Assigning 'CS111: Cloud Computing' to 'Prof. John Smith'
(2, 12),  -- Assigning 'CS112: Cybersecurity' to 'Prof. Alice Johnson'
(3, 13),  -- Assigning 'CS113: Data Science' to 'Dr. Michael Clark'
(1, 14),  -- Assigning 'CS114: Mobile Application Development' to 'Prof. Sarah Lee'
(2, 15),  -- Assigning 'CS115: Digital Signal Processing' to 'Prof. Emily Davis'
(3, 16);  -- Assigning 'CS116: Game Development' to 'Dr. Karen White'




