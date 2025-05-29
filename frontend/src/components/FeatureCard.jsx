// src/components/FeatureCard.jsx
import '../styles/global.css'

function FeatureCard({ title, description, onClick }) {
    return (
        <div className="feature" onClick={() => onClick(title, description)}>
            <h3>{title}</h3>
            <p>{description.substring(0, 80)}...</p>
        </div>
    );
}

export default FeatureCard;
