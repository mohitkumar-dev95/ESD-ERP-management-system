-- Add foreign key for Students table
ALTER TABLE Students
    ADD CONSTRAINT fk_students_domain
        FOREIGN KEY (domain_id) REFERENCES Domain(domain_id);

ALTER TABLE Students
    ADD CONSTRAINT fk_students_specialisation
        FOREIGN KEY (specialisation_id) REFERENCES Specialisation(specialisation_id);

ALTER TABLE Students
    ADD CONSTRAINT fk_students_placement
        FOREIGN KEY (placement_id) REFERENCES Placements(placement_id);

-- Add foreign key for Course_Prerequisite table
ALTER TABLE Course_Prerequisite
    ADD CONSTRAINT fk_course_prerequisite_course
        FOREIGN KEY (course_id) REFERENCES Courses(course_id);

ALTER TABLE Course_Prerequisite
    ADD CONSTRAINT fk_course_prerequisite_prerequisite
        FOREIGN KEY (prerequisite_id) REFERENCES Courses(course_id);

-- Add foreign key for Student_Courses table
ALTER TABLE Student_Courses
    ADD CONSTRAINT fk_student_courses_student
        FOREIGN KEY (student_id) REFERENCES Students(student_id);

ALTER TABLE Student_Courses
    ADD CONSTRAINT fk_student_courses_course
        FOREIGN KEY (course_id) REFERENCES Courses(course_id);

ALTER TABLE Student_Courses
    ADD CONSTRAINT fk_student_courses_grade
        FOREIGN KEY (grade_id) REFERENCES Grades(grade_id);

-- Add foreign key for Faculty_Courses table
ALTER TABLE Faculty_Courses
    ADD CONSTRAINT fk_faculty_courses_faculty
        FOREIGN KEY (faculty) REFERENCES Employees(employee_id);

ALTER TABLE Faculty_Courses
    ADD CONSTRAINT fk_faculty_courses_course
        FOREIGN KEY (course_id) REFERENCES Courses(course_id);

-- Add foreign key for Employees table
ALTER TABLE Employees
    ADD CONSTRAINT fk_employees_department
        FOREIGN KEY (department_id) REFERENCES Departments(department_id);
