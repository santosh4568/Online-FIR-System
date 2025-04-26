// src/pages/Home.jsx

import Header from '../components/Header';
import FeatureCard from '../components/FeatureCard';
import Footer from '../components/Footer';
import Modal from '../components/Modal';
import { useState } from 'react';

function Home() {
    const [modalData, setModalData] = useState({ isOpen: false, title: '', description: '' });

    const openModal = (title, description) => {
        setModalData({ isOpen: true, title, description });
    };

    const closeModal = () => {
        setModalData({ ...modalData, isOpen: false });
    };

    return (
        <>
            <Header />
            <main className="container">
                <section className="intro">
                    <h2>Welcome to JusticeNet</h2>
                    <p>Empowering citizens to file FIRs easily, securely, and stay updated in real time.</p>
                </section>

                <section className="features">
                    <FeatureCard
                        title="Quick FIR Filing"
                        description="JusticeNet streamlines the FIR process online, making it fast, secure, and user-friendly."
                        onClick={openModal}
                    />
                    <FeatureCard
                        title="Secure and Confidential"
                        description="We ensure your sensitive information is protected with highest standards of security."
                        onClick={openModal}
                    />
                    <FeatureCard
                        title="Track FIR Status"
                        description="Stay updated with real-time tracking of your filed FIRs from submission to resolution."
                        onClick={openModal}
                    />
                    <FeatureCard
                        title="24/7 Support"
                        description="Get assistance anytime you need with our round-the-clock support team available for you."
                        onClick={openModal}
                    />
                    <FeatureCard
                        title="Multi-Device Access"
                        description="File and track complaints easily from mobile, tablet, or desktop anytime, anywhere."
                        onClick={openModal}
                    />
                    <FeatureCard
                        title="Transparency & Trust"
                        description="We maintain full transparency in the filing process ensuring your trust and confidence."
                        onClick={openModal}
                    />
                </section>
            </main>

            <Footer />

            {modalData.isOpen && (
                <Modal
                    title={modalData.title}
                    description={modalData.description}
                    onClose={closeModal}
                />
            )}
        </>
    );
}

export default Home;
