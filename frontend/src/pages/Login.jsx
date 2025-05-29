import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/global.css';

function Login() {
    const [userType, setUserType] = useState(''); // Default user type
    const [formData, setFormData] = useState({ username: '', password: '', serviceNumber: '' });
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Simulate API call for login
        console.log('Logging in:', { userType, ...formData });
        navigate('/landing'); // Redirect to landing page after login
    };

    return (
        <div className="container">
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <select name="userType" value={userType} onChange={(e) => setUserType(e.target.value)} required>
                    <option value="" selected={true}>Select User Type</option>
                    <option value="client">Client</option>
                    <option value="officer">Officer</option>
                    <option value="admin">Admin</option>
                </select>

                {userType === 'client' && (
                    <>
                        <input
                            type="text"
                            name="username"
                            placeholder="Enter Username/Email"
                            value={formData.username}
                            onChange={handleChange}
                            required
                        />
                    </>
                )}

                {userType === 'officer' && (
                    <>
                        <input
                            type="text"
                            name="serviceNumber"
                            placeholder="Enter Service Number"
                            value={formData.serviceNumber}
                            onChange={handleChange}
                            required
                        />
                    </>
                )}

                {userType === 'admin' && (
                    <>
                        <input
                            type="text"
                            name="username"
                            placeholder="Enter Username"
                            value={formData.username}
                            onChange={handleChange}
                            required
                        />
                    </>
                )}
                {userType && (
                    <>
                        <input
                            type="password"
                            name="password"
                            placeholder="Enter Password"
                            value={formData.password}
                            onChange={handleChange}
                            required
                        />
                        <button type="submit">Login</button>
                    </>
                )}



            </form>
        </div>
    );
}

export default Login;