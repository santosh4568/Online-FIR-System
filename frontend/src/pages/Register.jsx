import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/global.css';

function Register() {
    const [userType, setUserType] = useState('');
    const [formData, setFormData] = useState({
        // Common fields
        password: '',
        confirmPassword: '',

        // Client fields
        clientName: '',
        clientPhone: '',
        clientAddress: '',
        clientEmail: '',

        // Officer fields
        officerName: '',
        officerPoliceStationState: '',
        officerPoliceStationDistrict: '',
        officerPoliceStationCity: '',
        officerPoliceStationName: '',
        officerServiceNumber: '',

        // Admin fields
        adminUsername: '',
        adminEmail: '',
    });
    const [errors, setErrors] = useState({});
    const [states, setStates] = useState([]);
    const [districts, setDistricts] = useState([]);
    const [cities, setCities] = useState([]);
    const [policeStations, setPoliceStations] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        // Fetch states from API or local data
        setStates(['State1', 'State2', 'State3']); // Example states
    }, []);

    useEffect(() => {
        // Fetch districts based on selected state
        if (formData.officerPoliceStationState) {
            setDistricts(['District1', 'District2']); // Example districts for State1
        } else {
            setDistricts([]);
        }
    }, [formData.officerPoliceStationState]);

    useEffect(() => {
        // Fetch cities based on selected district
        if (formData.officerPoliceStationDistrict) {
            setCities(['City1', 'City2']); // Example cities for District1
        } else {
            setCities([]);
        }
    }, [formData.officerPoliceStationDistrict]);

    useEffect(() => {
        // Fetch police stations based on selected city
        if (formData.officerPoliceStationCity) {
            setPoliceStations(['Station1', 'Station2']); // Example stations for City1
        } else {
            setPoliceStations([]);
        }
    }, [formData.officerPoliceStationCity]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const validateForm = () => {
        const newErrors = {};
        if (!formData.password || formData.password !== formData.confirmPassword) {
            newErrors.password = 'Passwords do not match.';
        }
        if (userType === 'client' && !/\S+@\S+\.\S+/.test(formData.clientEmail)) {
            newErrors.clientEmail = 'Invalid email format.';
        }
        if (userType === 'officer' && !formData.officerServiceNumber) {
            newErrors.officerServiceNumber = 'Service number is required.';
        }
        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (validateForm()) {
            console.log('Registering:', { userType, ...formData });
            navigate('/login'); // Redirect to login page after successful registration
        }
    };

    return (
        <div className="container">
            <h2>Register</h2>
            <form onSubmit={handleSubmit}>
                <select
                    name="userType"
                    value={userType}
                    onChange={(e) => setUserType(e.target.value)}
                    required
                >
                    <option value="" selected={true}>Select User Type</option>
                    <option value="client">Client</option>
                    <option value="officer">Officer</option>
                    <option value="admin">Admin</option>
                </select>

                {userType === 'client' && (
                    <>
                        <input
                            type="text"
                            name="clientName"
                            placeholder="Enter Name"
                            value={formData.clientName}
                            onChange={handleChange}
                            required
                        />
                        <input
                            type="text"
                            name="clientPhone"
                            placeholder="Enter Phone"
                            value={formData.clientPhone}
                            onChange={handleChange}
                            required
                        />
                        <input
                            type="text"
                            name="clientAddress"
                            placeholder="Enter Address"
                            value={formData.clientAddress}
                            onChange={handleChange}
                            required
                        />
                        <input
                            type="email"
                            name="clientEmail"
                            placeholder="Enter Email"
                            value={formData.clientEmail}
                            onChange={handleChange}
                            required
                        />
                    </>
                )}

                {userType === 'officer' && (
                    <>
                        <input
                            type="text"
                            name="officerName"
                            placeholder="Enter Name"
                            value={formData.officerName}
                            onChange={handleChange}
                            required
                        />
                        <select
                            name="officerPoliceStationState"
                            value={formData.officerPoliceStationState}
                            onChange={handleChange}
                            required
                        >
                            <option value="">Select State</option>
                            {states.map((state) => (
                                <option key={state} value={state}>{state}</option>
                            ))}
                        </select>
                        <select
                            name="officerPoliceStationDistrict"
                            value={formData.officerPoliceStationDistrict}
                            onChange={handleChange}
                            required
                        >
                            <option value="">Select District</option>
                            {districts.map((district) => (
                                <option key={district} value={district}>{district}</option>
                            ))}
                        </select>
                        <select
                            name="officerPoliceStationCity"
                            value={formData.officerPoliceStationCity}
                            onChange={handleChange}
                            required
                        >
                            <option value="">Select City</option>
                            {cities.map((city) => (
                                <option key={city} value={city}>{city}</option>
                            ))}
                        </select>
                        <select
                            name="officerPoliceStationName"
                            value={formData.officerPoliceStationName}
                            onChange={handleChange}
                            required
                        >
                            <option value="">Select Police Station</option>
                            {policeStations.map((station) => (
                                <option key={station} value={station}>{station}</option>
                            ))}
                        </select>
                        <input
                            type="text"
                            name="officerServiceNumber"
                            placeholder="Enter Service Number"
                            value={formData.officerServiceNumber}
                            onChange={handleChange}
                            required
                        />
                    </>
                )}

                {userType === 'admin' && (
                    <>
                        <input
                            type="text"
                            name="adminUsername"
                            placeholder="Enter Username"
                            value={formData.adminUsername}
                            onChange={handleChange}
                            required
                        />
                        <input
                            type="email"
                            name="adminEmail"
                            placeholder="Enter Email"
                            value={formData.adminEmail}
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
                        <input
                            type="password"
                            name="confirmPassword"
                            placeholder="Confirm Password"
                            value={formData.confirmPassword}
                            onChange={handleChange}
                            required
                        />
                        {errors.password && <p className="error">{errors.password}</p>}
                        {errors.clientEmail && <p className="error">{errors.clientEmail}</p>}
                        {errors.officerServiceNumber && <p className="error">{errors.officerServiceNumber}</p>}
                        <button type="submit">Register</button>
                    </>
                )}
            </form>
        </div>
    );
}

export default Register;