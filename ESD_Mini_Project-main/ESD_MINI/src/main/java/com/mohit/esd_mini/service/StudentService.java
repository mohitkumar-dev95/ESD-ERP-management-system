package com.mohit.esd_mini.service;

import com.mohit.esd_mini.dto.EnrollStudentRequest;
import com.mohit.esd_mini.dto.StudentRequest;
import com.mohit.esd_mini.entity.*;
import com.mohit.esd_mini.helper.EncryptionService;
import com.mohit.esd_mini.mapper.StudentMapper;
import com.mohit.esd_mini.repo.*;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class StudentService {

    // To store entity into sql database
    @Autowired
    private StudentRepo repo;
    @Autowired
    private DomainRepo domainRepository;
    @Autowired
    private SpecialisationRepo specialisationRepository;
    @Autowired
    private PlacementRepo placementRepository;
    @Autowired
    private CoursesRepo coursesRepo;
    @Autowired
    private StudentRepo studentsRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private FacultyCoursesRepo facultyCoursesRepo;

    // To convert dto to entity
    @Autowired
    private StudentMapper map;
    @Autowired
    private StudentMapper createMapper;
//    private final CustomerDeleteMapper deleteMapper;

    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private Student_CoursesRepository studentCourseRepository;
    @Autowired
    private CoursePrerequisiteRepository coursePrerequisiteRepository;
    private Student_Courses studentCourse;
    private Students student;


    private boolean checkPrerequisitesMet(int studentId, List<Courses> prerequisites) {
        for (Courses prerequisite : prerequisites) {
            System.out.println(prerequisite.getCourse_id()+" MMMM "+ studentId + " nnn "+ studentCourseRepository.existsByStudentAndCourse(studentId, prerequisite.getCourse_id()));
            int prerequisiteId = prerequisite.getCourse_id();
            // Check if the prerequisite exists in the student_courses repository
            if (!studentCourseRepository.existsByStudentAndCourse(studentId, prerequisiteId)) {
                return false; // Prerequisite not met, exit early
            }
        }
        return true; // All prerequisites are met
    }

    public List<Map<String, Object>> showAllowedCourses(String student_id) {
        System.out.println("==================== create service");
        int studentId = Integer.parseInt(student_id);

        int count = 0;
        List<Courses> all_courses = coursesRepo.findAll();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Courses course : all_courses) {
            Map<String, Object> courseMap = new HashMap<>();
            courseMap.put("courseId", course.getCourse_id());
            courseMap.put("courseName", course.getCourseCode());
            courseMap.put("credits", course.getCredits());
            courseMap.put("description", course.getDescription());
            courseMap.put("course_id", course.getCourse_id());
            Integer facultyId = facultyCoursesRepo.findFacultyIdByCourse(course);
            Optional<Employees> faculty = employeeRepo.findByEmployeeId(facultyId);
            courseMap.put("faculty", "Prof. "+faculty.get().getFirstName()+" "+faculty.get().getLastName());
            List<Courses> prerequisites = coursePrerequisiteRepository.findPrerequisitesByCourseId(course.getCourse_id());
            List<Map<String, Object>> prerequisiteList = new ArrayList<>();
            for (Courses prereq : prerequisites) {
                Map<String, Object> prereqMap = new HashMap<>();
                prereqMap.put("id", prereq.getCourse_id());
                prereqMap.put("name", prereq.getCourseCode());
                prerequisiteList.add(prereqMap);
            }
            courseMap.put("prerequisites", prerequisiteList);
            if(!studentCourseRepository.existsByStudentAndCourse(studentId, course.getCourse_id())){
//            && (coursePrerequisiteRepository.findUnmetPrerequisites(studentId, course.getCourse_id()).isEmpty())){
                courseMap.put("disabled", 0);
            }
            else if(studentCourseRepository.existsByStudentAndCourse(studentId, course.getCourse_id())){
                courseMap.put("disabled", 1);
                count = count + 1;
            }
            System.out.println("===== PRE "+ course.getCourse_id()  + " " + (coursePrerequisiteRepository.findUnmetPrerequisites(studentId, course.getCourse_id()).isEmpty()));
//            if((coursePrerequisiteRepository.findUnmetPrerequisites(studentId, course.getCourse_id()).isEmpty())){
//                courseMap.put("disabled", 0);
//            }
            result.add(courseMap);
        }
        Map<String, Object> countMap = new HashMap<>();
        countMap.put("count", count);
        result.add(countMap);
        return result;
    }

    public List<Map<String, Object>> showEnrolledCourses(String student_id) {
        System.out.println("==================== create service");
        int studentId = Integer.parseInt(student_id);

        List<Courses> all_courses = coursesRepo.findAll();
        List<Map<String, Object>> result = new ArrayList<>();
        int count = 0;
        for (Courses course : all_courses) {
            if(studentCourseRepository.existsByStudentAndCourse(studentId, course.getCourse_id())){
                Map<String, Object> courseMap = new HashMap<>();
                courseMap.put("courseId", course.getCourse_id());
                courseMap.put("courseName", course.getCourseCode());
                courseMap.put("credits", course.getCredits());
                courseMap.put("description", course.getDescription());
                courseMap.put("course_id", course.getCourse_id());

                Integer facultyId = facultyCoursesRepo.findFacultyIdByCourse(course);
                Optional<Employees> faculty = employeeRepo.findByEmployeeId(facultyId);
                courseMap.put("faculty", "Prof. "+faculty.get().getFirstName()+" "+faculty.get().getLastName());
                result.add(courseMap);
                count ++;
            }
        }
        Map<String, Object> countMap = new HashMap<>();
        countMap.put("count", count);
        result.add(countMap);
        return result;
    }

    public String createStudent(StudentRequest request) {
        System.out.println("==================== create service");
        try{
        // This will convert our dto to entity using Mapper
        Students student = createMapper.toEntity(request);
        System.out.println("==================== 2create service");
        student.setPassword(encryptionService.encode(student.getPassword()));
        System.out.println("==================== 3create service");
        System.out.println(student);
        System.out.println(request);
        Domain domain = new Domain();
        domainRepository.save(domain); // Save new Domain

        Specialisation specialisation = new Specialisation();
        specialisationRepository.save(specialisation); // Save new Specialisation

        Placements placement = new Placements();
        placementRepository.save(placement); // Save new Placement

        // Students student = new Students();
        student.setDomain(domain);

        student.setSpecialisation(specialisation);
        student.setPlacement(placement);
        // Stores entity into database using Repo
        repo.save(student);
        System.out.println("==================== 4create service");
        return "Account Created Successfully";
        }
        catch(Exception e){
            return "Account Creation Failed due to : " + e.getMessage();
        }
    }

    public String enrollStudent(EnrollStudentRequest req){//long studentId, long courseId) {
        // Enforce prerequisites server-side; allow partial enrollment with a clear summary
        try {
            int studentId = req.studentId();
            List<Integer> courseIds = req.courses();
            System.out.println("==================== enroll service" + req);

            if (courseIds == null || courseIds.isEmpty()) {
                throw new BadRequestException("No courses selected for enrollment");
            }

            Students st = studentsRepo.findById(studentId).orElse(null);
            if (st == null) {
                throw new BadRequestException("Student not found: " + studentId);
            }

            List<Integer> enrolled = new ArrayList<>();
            Map<Integer, String> failed = new LinkedHashMap<>();

            for (Integer courseId : courseIds) {
                if (courseId == null || courseId < 0) {
                    failed.put(courseId, "Invalid course ID");
                    continue;
                }

                // Already enrolled check
                if (studentCourseRepository.existsByStudentAndCourse(studentId, courseId)) {
                    failed.put(courseId, "Already enrolled");
                    continue;
                }

                // Prerequisites check
                List<Integer> unmetPrerequisites = coursePrerequisiteRepository.findUnmetPrerequisites(studentId, courseId);
                if (!unmetPrerequisites.isEmpty()) {
                    failed.put(courseId, "Unmet prerequisites: " + unmetPrerequisites);
                    continue;
                }

                Courses cs = coursesRepo.findById(courseId).orElse(null);
                if (cs == null) {
                    failed.put(courseId, "Course not found");
                    continue;
                }

                Student_Courses studentCourse = new Student_Courses();
                studentCourse.setStudent(st);
                studentCourse.setCourse(cs);
                studentCourseRepository.save(studentCourse);
                enrolled.add(courseId);
            }

            if (!enrolled.isEmpty() && failed.isEmpty()) {
                return "Student successfully enrolled in selected courses: " + enrolled;
            } else if (!enrolled.isEmpty()) {
                return "Enrolled in courses: " + enrolled + ". Failed: " + failed;
            } else {
                return "No courses enrolled. Failures: " + failed;
            }
        } catch (Exception e) {
            return "Enrollment is unsuccessfull due to : " + e.getMessage();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public String deenrollStudent(EnrollStudentRequest req) throws BadRequestException {
        int studentId = req.studentId();
        Students st = studentsRepo.findById(studentId).orElse(null);
        List<Integer> courseIds = req.courses();

        System.out.println("==================== deenroll service " + req);

        // Validate courseIds
        if (courseIds == null || courseIds.isEmpty()) {
            throw new BadRequestException("No course IDs provided.");
        }
        Integer courseIdToRemove = courseIds.get(0);
        Integer courseIdToEnroll = courseIds.size() > 1 ? courseIds.get(1) : null;

        if (courseIdToRemove == null || courseIdToRemove < 0) {
            throw new BadRequestException("Invalid Course ID provided: " + courseIdToRemove);
        }

        // Check if the student is enrolled in the course to be removed
        if (!studentCourseRepository.existsByStudentAndCourse(studentId, courseIdToRemove)) {
            throw new BadRequestException("Student is not enrolled in the course to be removed. Course ID: " + courseIdToRemove);
        }

        // If a second course ID is provided, PRE-VALIDATE before any removal
        if (courseIdToEnroll != null) {
            // Already enrolled check
            if (studentCourseRepository.existsByStudentAndCourse(studentId, courseIdToEnroll)) {
                throw new BadRequestException("Already enrolled in course " + courseIdToEnroll);
            }

            // Course existence check
            Courses csToAdd = coursesRepo.findById(courseIdToEnroll).orElse(null);
            if (csToAdd == null) {
                throw new BadRequestException("Course not found for Course ID: " + courseIdToEnroll);
            }

            // Prerequisite check against CURRENT enrollment (before removal)
            List<Integer> unmetBefore = coursePrerequisiteRepository.findUnmetPrerequisites(studentId, courseIdToEnroll);
            if (unmetBefore != null && !unmetBefore.isEmpty()) {
                throw new BadRequestException("Swap failed — missing prerequisites: " + unmetBefore);
            }

            // All validations passed: proceed to remove then add
            Optional<Student_Courses> studentCourseRemove = studentCourseRepository.findByStudentAndCourse(studentId, courseIdToRemove);
            if (studentCourseRemove.isPresent()) {
                studentCourseRepository.delete(studentCourseRemove.get());
            } else {
                throw new BadRequestException("Failed to find enrollment record for Course ID: " + courseIdToRemove);
            }

            Student_Courses scAdd = new Student_Courses();
            scAdd.setStudent(st);
            scAdd.setCourse(csToAdd);
            studentCourseRepository.save(scAdd);
            return "Deenrolled from Course ID: " + courseIdToRemove + ", enrolled in Course ID: " + courseIdToEnroll;
        } else {
            // No add requested: just remove
            Optional<Student_Courses> studentCourseRemove = studentCourseRepository.findByStudentAndCourse(studentId, courseIdToRemove);
            if (studentCourseRemove.isPresent()) {
                studentCourseRepository.delete(studentCourseRemove.get());
            } else {
                throw new BadRequestException("Failed to find enrollment record for Course ID: " + courseIdToRemove);
            }
            return "Student successfully deenrolled from Course ID: " + courseIdToRemove;
        }
    }

}
