// src/components/Modal.jsx
import '../styles/global.css'

function Modal({ title, description, onClose }) {
    return (
        <div className="modal" onClick={onClose}>
            <div className="modal-content" onClick={(e) => e.stopPropagation()}>
                <span className="close" onClick={onClose}>&times;</span>
                <h2>{title}</h2>
                <p>{description}</p>
            </div>
        </div>
    );
}

export default Modal;
