import React from 'react';
import { GoogleLogin } from '@react-oauth/google';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const GoogleLoginButton = () => {
    const navigate = useNavigate();

    const handleSuccess = async (credentialResponse) => {
        try {
            const response = await axios.post('http://localhost:8080/auth/google', {
                token: credentialResponse.credential,
            });

            const { jwt, studentId } = response.data;

            localStorage.setItem('token', jwt);
            localStorage.setItem('studentId', studentId);
            localStorage.setItem('isLoggedIn', 'true');

            navigate('/');
        } catch (error) {
            console.error('Google Login Failed:', error);
            alert('Google Login Failed. Please try again.');
        }
    };

    const handleError = () => {
        console.log('Google Login Failed');
        alert('Google Login Failed. Please try again.');
    };

    return (
        <div style={{ marginTop: '1rem', display: 'flex', justifyContent: 'center' }}>
            <GoogleLogin
                onSuccess={handleSuccess}
                onError={handleError}
                useOneTap
            />
        </div>
    );
};

export default GoogleLoginButton;
