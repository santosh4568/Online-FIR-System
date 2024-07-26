<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online FIR System - Login</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
    <script src="<c:url value='/js/script.js'/>"></script>
</head>
<body>
    <div class="login-container">
        <div class="login-header">
            <h1>Login to Online FIR System</h1>
        </div>
        <div class="tab">
            <button class="tablinks active" onclick="openLoginForm(event, 'client')">Client Login</button>
            <button class="tablinks" onclick="openLoginForm(event, 'officer')">Officer Login</button>
        </div>
        <div id="client" class="login-form active">
            <form action="/login/client" method="post">
                <input type="text" name="username" placeholder="Username" required>
                <input type="password" name="password" placeholder="Password" required>
                <button type="submit">Login</button>
                <div class="error-message"><!-- Error messages will appear here --></div>
            </form>
        </div>
        <div id="officer" class="login-form">
            <form action="/login/officer" method="post">
                <input type="text" name="policeServiceNumber" placeholder="Police Service Number" required>
                <input type="password" name="password" placeholder="Password" required>
                <button type="submit">Login</button>
                <div class="error-message"><!-- Error messages will appear here --></div>
            </form>
        </div>
    </div>
    <script>
        function openLoginForm(evt, formType) {
            var i, form, tablinks;
            form = document.getElementsByClassName("login-form");
            for (i = 0; i < form.length; i++) {
                form[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            document.getElementById(formType).style.display = "block";
            evt.currentTarget.className += " active";
        }
    </script>
</body>
</html>
