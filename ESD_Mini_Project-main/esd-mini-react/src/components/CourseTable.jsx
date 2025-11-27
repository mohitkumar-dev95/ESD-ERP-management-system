import React, { useState , useEffect } from "react";
import { enrollCourses } from '../utils/enrollCourses';
import useCourses  from '../hooks/useCourses';
import {deenrollCourse} from '../utils/deenrollCourse';
import { useNavigate } from 'react-router-dom';


function CourseTable({ whichCourses }) {
  const token = localStorage.getItem("token"); // Get JWT token from localStorage
  const studentId = localStorage.getItem("studentId");
  const [selectedCourses, setSelectedCourses] = useState([]);
  const [courseIdRemove, setCourseId1] = useState("");
  const [courseIdAdd, setCourseId2] = useState("");
  const navigate = useNavigate();

  // Use custom hook to fetch courses
  const { courses, error, refetchCourses, fetchData } = useCourses(token, studentId, whichCourses);

  useEffect(() => {
    const loggedInStatus = localStorage.getItem('isLoggedIn');
    if (loggedInStatus !== 'true') {
      navigate("/");
    }
}, [navigate]);

  // Handle course selection
  const handleSelect = (courseId) => {
    setSelectedCourses((prevSelected) =>
      prevSelected.includes(courseId)
        ? prevSelected.filter((id) => id !== courseId)
        : [...prevSelected, courseId]
    );
  };


  // Handle course enrollment
  const handleEnroll = async () => {
    const currentCount = courses[courses.length - 1]?.count || 0;
    if (currentCount + selectedCourses.length < 4 || currentCount + selectedCourses.length > 6) {
      alert("You must select between 4 and 6 courses.");
      return;
    }

    try {
      const response = await enrollCourses(token, studentId, selectedCourses);
      if (response && response.error) {
        console.error(response.error);
        alert(response.error || "Error enrolling in courses.");
      } else {
        console.log("Enrollment response:", response);
        alert(typeof response === 'string' ? response : "Enrollment completed.");
      }
    } catch (error) {
      console.error("Unexpected error during enrollment:", error.message);
    }
    await refetchCourses();
    setSelectedCourses([]);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await deenrollCourse(token, studentId, courseIdRemove, courseIdAdd);
      // alert(response.data);
      refetchCourses(); // Reload the table after successful deenrollment
    } catch (err) {
      console.error("Deenrollment failed:", err.message);
    }
  };
  return (
    <>
      <h2 className="text-center container-header rounded bg-primary text-white p-1 bg-dark">
        {whichCourses === "enrolled_courses" ? "Selected Courses" : "Select Courses"}
      </h2>

      {error && <p className="text-danger text-center">{error}</p>}

      <table className="table table-striped table-hover">
        <thead>
          <tr>
            <th>#</th>
            <th>Course Name</th>
            <th>Course Description</th>
            <th>Credits</th>
            <th>Course ID</th>
            <th>Faculty</th>
            <th>Prerequisite</th>
            {whichCourses !== "enrolled_courses" && <th>Select</th>}
          </tr>
        </thead>
        <tbody>
          {Array.isArray(courses) && courses.length > 0 ? (
            courses.map((course, index) => {
              if (index === courses.length - 1) {
                return null;
              }

              return (
                <tr key={course.course_id || course.courseId}>
                  <td>{index + 1}</td>
                  <td>{course.courseName}</td>
                  <td>{course.description}</td>
                  <td>{course.credits}</td>
                  <td>{course.course_id || course.courseId}</td>
                  <td>{course.faculty}</td>
                  <td>
                    {course.prerequisites && course.prerequisites.length > 0
                      ? course.prerequisites.map((prerequisite, i) => (
                          <span key={prerequisite.id}>
                            {prerequisite.name}
                            {i !== course.prerequisites.length - 1 && ", "}
                          </span>
                        ))
                      : "None"}
                  </td>
                  {whichCourses !== "enrolled_courses" && (
                    <td>
                      <input
                        type="checkbox"
                        checked={selectedCourses.includes(course.course_id)}
                        onChange={() => handleSelect(course.course_id)}
                        disabled={course.disabled === 1}
                      />
                    </td>
                  )}
                </tr>
              );
            })
          ) : (
            <tr>
              <td colSpan="8" className="text-center">
                No Courses Available
              </td>
            </tr>
          )}
        </tbody>
      </table>

      {whichCourses !== "enrolled_courses" ? (
        <div className="text-center">
          <button
            className="btn btn-primary"
            onClick={handleEnroll}
            disabled={
              (selectedCourses.length < 4 ||
              selectedCourses.length > 6) &&
              ((courses[courses.length - 1]?.count || 0) + selectedCourses.length > 6) ||
              (selectedCourses.length === 0 )
            }
          >
            Submit
          </button>
          <p className="text-muted">
            {selectedCourses.length < 4 ? "Please select at least 4 courses." : ""}
            {selectedCourses.length > 6 ? "You can select a maximum of 6 courses." : ""}
          </p>
        </div>
      ) : (
        <div className="form-container">
  <h2 className="rounded bg-primary text-white p-1 fs-5 bg-dark text-center">Enter Course ID To Remove </h2>
  <form onSubmit={handleSubmit}>
    <div className="form-group">
      <label htmlFor="courseIdRemove">Course ID to Remove:&nbsp;&nbsp;&nbsp;</label>
      <input
        type="text"
        id="courseId1"
        value={courseIdRemove}
        onChange={(e) => setCourseId1(e.target.value)}
        required
        placeholder="Enter course ID to remove"
      />
    </div>

    {courses[courses.length - 1]?.count === 4 && (
      <div className="form-group">
        <label htmlFor="courseIdAdd">Course ID to Add: &nbsp;&nbsp;&nbsp;</label>
        <input
          type="text"
          id="courseId2"
          value={courseIdAdd}
          onChange={(e) => setCourseId2(e.target.value)}
          required
          placeholder="Enter course ID to add"
        />
      </div>
    )}

    <button
      type="submit"
      className="btn btn-primary"
      disabled={
        !courseIdRemove || // No course to remove
        (courses[courses.length - 1]?.count === 4 && !courseIdAdd) // No course to add when exactly 4 courses are enrolled
      }
    >
      Submit
    </button>
  </form>
</div>
      )}
    </>
  );
}

export default CourseTable;
