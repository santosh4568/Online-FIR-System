// src/components/Footer.jsx
import { FaEnvelope, FaPhone, FaInstagram, FaFacebook } from 'react-icons/fa';
import '../styles/global.css'

function Footer() {
    return (
        <footer className="footer">
            <div className="contact-info">
                <a href="mailto:s6400192@gmail.com"><FaEnvelope /></a>
                <a href="tel:+919431679589"><FaPhone /></a>
                <a href="https://www.instagram.com/shiv.alax108" target="_blank" rel="noopener noreferrer"><FaInstagram /></a>
                <a href="https://www.facebook.com/justicenet" target="_blank" rel="noopener noreferrer"><FaFacebook /></a>
            </div>
            <p>&copy; 2024 JusticeNet. All rights reserved.</p>
        </footer>
    );
}

export default Footer;
