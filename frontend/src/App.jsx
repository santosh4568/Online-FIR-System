// src/App.jsx
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import './App.css';
import Login from "./pages/Login.jsx";
import Register from "./pages/Register.jsx"; // We'll add some global styles here

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                {/* Future routes can be added here */}
            </Routes>
        </Router>
    );
}

export default App;
