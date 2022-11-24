<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html> 
    <head>
        <meta charset='utf-8'>
        <meta http-equiv='X-UA-Compatible' content='IE=edge'>
        <meta name='viewport' content='width=device-width, initial-scale=1'>
        <link rel='stylesheet' type='text/css' media='screen' href='css/general.css'>
        <title>Login</title>
    </head>

    <body>
        <section id="form-login">
            <h5>Login</h5>
            <form action="logServlet" method="POST">
                <input class="controls" type="text" name="correo" value="" placeholder="Correo">
                <input class="controls" type="password" name="contrasenia" value="" placeholder="Contraseña">
                <input class="buttons" type="submit" name ="boton" value="login">
            </form>
            <p><a href="#">¿Olvidastes tu Contraseña?</a></p>
        </section>
        
        <c:if test="${not empty mensaje}">
            <script>
                window.addEventListener("load", function(){
                    alert("${mensaje}");
                });
            </script>
        </c:if>
    </body>

</html>
