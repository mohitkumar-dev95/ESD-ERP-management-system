import React, { useState , useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import logo from '../logo.png'; 
import { Link } from 'react-router-dom';

function Navbar() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const navigate = useNavigate();

    // Check login status on component mount
    useEffect(() => {
        const loggedInStatus = localStorage.getItem('isLoggedIn') === 'true';
        setIsLoggedIn(loggedInStatus);
    }, []);

    // Logout function
    const handleLogout = () => {
      // Clear login-related data from localStorage
      localStorage.clear();

      // Update state to reflect logout
      setIsLoggedIn(false);
      navigate('/');
  };

  return (
    <>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark fs-5">
        <div className="container-fluid">
        <Link className="navbar-brand" to="#">
          <img
            src={logo}
            alt="Academia Logo"
            style={{ height: '30px', marginRight: '10px' }}
          />
          Academia
        </Link>

          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav">
              <li className="nav-item">
                <Link className="nav-link active" aria-current="page" to="/">
                  Home
                </Link>
              </li>
              {isLoggedIn && (
                <li className="nav-item">
                  <Link className="nav-link" to="/enroll">
                    Enroll
                  </Link>
                </li>
              )}
               {isLoggedIn && (
                <li className="nav-item">
                  <Link className="nav-link" to="/enrolled_courses">
                    Enrolled Courses
                  </Link>
                </li>
              )}
            </ul>
            <ul className="navbar-nav ms-auto">
              {!isLoggedIn && (
                <li className="nav-item">
                  <Link className="nav-link" to="/signin">
                    Log in
                  </Link>
                </li>
              )}
              {isLoggedIn && (
                <li className="nav-item">
                  <button className="btn btn-link nav-link" onClick={handleLogout}>
                    Logout
                  </button>
                </li>
              )}
            </ul>
          </div>

        </div>
      </nav>
    </>
  );
}

export default Navbar;
