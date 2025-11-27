import React from "react";
import Navbar from "../components/Navbar";
import "../styles/home.css"; // Import CSS styles
import logo from '../logo.png'; 

function Home() {
  return (
    <>
      <Navbar />
      <div className="home-container">
        <div className="home-box">
        <img id="logo-img"
            src={logo}
            alt="Academia Logo"
            // style={{ height: '30px', marginRight: '10px' }}
          />
          <h1>Welcome to ACADEMIA</h1>
          <p>Your gateway to quality education and seamless learning management. Explore courses, manage enrollments, and much more!</p>
        </div>
      </div>
    </>
  );
}

export default Home;
