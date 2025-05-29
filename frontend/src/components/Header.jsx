// src/components/Header.jsx
import { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/global.css'

function Header() {
    const [dropdownOpen, setDropdownOpen] = useState(false);

    return (
        <header className="header">
            <h1>JusticeNet</h1>
            <nav>
                <Link to="/track-complaint"><strong>Track Complaint</strong></Link>
                <Link to="/add-complaint"><strong>Add Complaint</strong></Link>

                <div
                    className="dropdown"
                    onMouseEnter={() => setDropdownOpen(true)}
                    onMouseLeave={() => setDropdownOpen(false)}
                >
                    <span><strong>Username</strong></span>
                    {dropdownOpen && (
                        <div className="dropdown-content">
                            <Link to="/logout"><strong>Logout</strong></Link>
                        </div>
                    )}
                </div>
            </nav>
        </header>
    );
}

export default Header;
