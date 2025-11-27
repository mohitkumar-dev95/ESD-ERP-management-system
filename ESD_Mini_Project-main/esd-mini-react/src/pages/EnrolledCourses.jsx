import Navbar from '../components/Navbar'
import CourseTable from '../components/CourseTable'
import React, { useState , useEffect} from 'react';
import { useNavigate } from 'react-router-dom';

function EnrolledCourses() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const loggedInStatus = localStorage.getItem('isLoggedIn');
    if (loggedInStatus !== 'true') {
      navigate("/");
    }
}, [navigate]);

  return (
    <>
    <Navbar />
    <div className='container'>
      <CourseTable  whichCourses={"enrolled_courses"} />
    </div>
    </>
  )
}

export default EnrolledCourses