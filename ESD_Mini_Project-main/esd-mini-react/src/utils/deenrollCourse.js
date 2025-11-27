// enrollCourses function in utils/enrollCourses.js
import axios from 'axios';
import config from '../config';

// Function to enroll in courses
export const deenrollCourse = async (token, studentId, courseIdRemove, courseIdAdd) => {
  // console.log(`Enrolling courses for studentId: ${studentId} at: ${config}/api/v1/student/enroll ${courseIds}`);
  
  try {
    const response = await axios.post(
      `${config}/api/v1/student/deenroll`,
      {
        studentId,
        courses: [courseIdRemove, courseIdAdd], // Pass the array of course IDs
      },
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    alert(response.data);
    return response.data;
  } catch (error) {
    if (error.response && error.response.status === 401) {
      alert(error.response.data.message);
      console.log('Token expired or unauthorized');
      return { error: 'Token expired or unauthorized' };
    } else if (error.response && error.response.status === 400) {
      alert(error.response.data.message);
      console.log(error.response);
      return { error: 'Token expired or unauthorized' };
    } 
    else {
      alert(error.response.data.message);
      console.error('Error enrolling in courses:', error);
      return { error: 'Error enrolling in courses' };
    }
  }
};
