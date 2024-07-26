<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online FIR System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }

        .header {
            background-color: #333;
            color: #fff;
            padding: 15px 0;
            text-align: center;
            position: relative;
        }

        .header h1 {
            margin: 0;
            font-size: 1.8em;
        }

        .header nav {
            margin-top: 10px;
        }

        .header a {
            color: #fff;
            text-decoration: none;
            padding: 10px 15px;
            display: inline-block;
            transition: background-color 0.3s;
        }

        .header a:hover {
            background-color: #555;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        .intro {
            text-align: center;
            margin: 20px 0;
        }

        .intro h2 {
            color: #333;
        }

        .intro p {
            color: #666;
            font-size: 1.1em;
        }

        .features {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            margin: 20px 0;
        }

        .feature {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin: 10px;
            padding: 20px;
            width: 30%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s, box-shadow 0.3s;
            cursor: pointer;
        }

        .feature:hover {
            transform: translateY(-10px);
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
        }

        .feature h3 {
            color: #333;
            margin-top: 0;
        }

        .feature p {
            color: #666;
            font-size: 1em;
        }

        .footer {
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 10px 0;
            position: fixed;
            bottom: 0;
            width: 100%;
        }

        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
            padding-top: 60px;
        }

        .modal-content {
            background-color: #fefefe;
            margin: 5% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 600px;
            border-radius: 10px;
            text-align: center;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }

        @media (max-width: 768px) {
            .feature {
                width: 45%;
            }
        }

        @media (max-width: 480px) {
            .feature {
                width: 90%;
            }
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Online FIR System</h1>
        <nav>
            <a href="<c:url value='/login' />">Login</a>
            <a href="<c:url value='/signup' />">Sign Up</a>
            <a href="<c:url value='/track-complaint' />">Track Complaint</a>
            <a href="<c:url value='/add-complaint' />">Add Complaint</a>
        </nav>
    </div>

    <div class="container">
        <div class="intro">
            <h2>Welcome to the Online FIR System</h2>
            <p>This system allows users to file FIRs easily and quickly online.</p>
        </div>

        <div class="features">
            <div class="feature" onclick="openModal('Quick FIR Filing', 'File your FIRs quickly with our easy-to-use online system.')">
                <h3>Quick FIR Filing</h3>
                <p>File your FIRs quickly with our easy-to-use online system.</p>
            </div>
            <div class="feature" onclick="openModal('Secure and Confidential', 'Your data is secure with us. We ensure confidentiality of all FIRs filed.')">
                <h3>Secure and Confidential</h3>
                <p>Your data is secure with us. We ensure confidentiality of all FIRs filed.</p>
            </div>
            <div class="feature" onclick="openModal('Track FIR Status', 'Track the status of your FIR in real-time from anywhere.')">
                <h3>Track FIR Status</h3>
                <p>Track the status of your FIR in real-time from anywhere.</p>
            </div>
        </div>
    </div>

    <div class="footer">
        <p>&copy; 2024 Online FIR System. All rights reserved.</p>
    </div>

    <div id="featureModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h2 id="modalTitle"></h2>
            <p id="modalDescription"></p>
        </div>
    </div>

    <script>
        function openModal(title, description) {
            document.getElementById('modalTitle').innerText = title;
            document.getElementById('modalDescription').innerText = description;
            document.getElementById('featureModal').style.display = "block";
        }

        function closeModal() {
            document.getElementById('featureModal').style.display = "none";
        }

        window.onclick = function(event) {
            if (event.target == document.getElementById('featureModal')) {
                closeModal();
            }
        }
    </script>
</body>
</html>
