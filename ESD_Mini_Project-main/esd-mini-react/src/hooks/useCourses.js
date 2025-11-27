import { useState, useEffect } from "react";
import { fetchCourses } from "../utils/fetchCourses";
import { useNavigate } from 'react-router-dom';

const useCourses = (token, studentId, whichCourses) => {
  const [courses, setCourses] = useState([]);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  // Function to fetch courses and update state
  const fetchData = async () => {
    if (localStorage.length === 0) {
      console.log("localStorage is empty");
      navigate("/");
      alert("Session Out");
    } else {
    if (token && studentId) {
      try {
        const response = await fetchCourses(token, studentId, whichCourses, navigate); // Call the API function to fetch courses
        if (Array.isArray(response)) {
          setCourses(response); // Set the received courses to state
        } else {
          console.error("Unexpected response format:", response);
          setCourses([]); // Fallback to empty array
        }
      } catch (error) {
        console.error("Error during fetchCourses execution:", error.message);
        setError(error.message); // Set the error state
        setCourses([]); // Fallback to empty array
      }
    } else {
      console.error("Missing token or studentId");
      setError("Missing token or studentId");
    }
  }
  };

  const refetchCourses = async () => {
    if (localStorage.length === 0) {
      console.log("localStorage is empty");
      navigate("/");
    } else {
    try {
      const response = await fetchCourses(token, studentId, whichCourses, navigate);
      if (Array.isArray(response)) {
        setCourses(response);
      } else {
        setCourses([]);
        setError("Unexpected response format");
      }
    } catch (err) {
      setError(err.message || "Failed to fetch courses");
      setCourses([]);
    }
  }
  };

  // Initial fetch of courses on component mount or dependency change
  useEffect(() => {
    fetchData();
  }, [token, studentId, whichCourses]);

  useEffect(() => {
    refetchCourses();
  }, [token, studentId, whichCourses]);

  return { courses, error, refetchCourses, fetchData };
};

export default useCourses;
