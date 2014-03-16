<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
        <title>Grails Sparklr</title>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'style.css')}" type="text/css"/>
    </head>
    <body>
        <h1>Grails Sparklr</h1>

        <div id="content">

            <g:if test="${params.authentication_error == 'true'}">
                <h1>Woops!</h1>
                <p class="error">Your login attempt was not successful.</p>
            </g:if>

            <g:if test="${params.authorization_error == 'true'}">
                <h1>Woops!</h1>
                <p class="error">You are not permitted to access that resource.</p>
            </g:if>

            <h2>Login</h2>

            <p>
                We've got a grand total of 2 users: marissa and paul. Go ahead and log in.
                Marrisa's password is "koala" and Paul's password is "emu".
            </p>

            <form id="loginForm" name="loginForm" action="${request.contextPath}/j_spring_security_check" method="post">
                <p><label>Username: <input type='text' name='j_username' value="marissa"></label></p>
                <p><label>Password: <input type='text' name='j_password' value="koala"></label></p>

                <p><input name="login" value="Login" type="submit"></p>
            </form>
        </div>

    <div id="footer">
        Sample application for <a href="http://grails.org/plugin/spring-security-oauth2-provider" target="_blank">
        Grails Spring Security OAuth2 Provider Plugin</a>. Based on the Sparklr sample application from
        <a href="http://github.com/SpringSource/spring-security-oauth" target="_blank">Spring Security OAuth</a>
    </div>

    </body>
</html>
