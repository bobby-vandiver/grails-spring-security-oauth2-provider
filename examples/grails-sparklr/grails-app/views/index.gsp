<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<title>Grails Sparklr</title>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'style.css')}" type="text/css"/>

        <sec:ifAllGranted roles="ROLE_USER">
            <script type="text/javascript">
                function pictureDisplay(json) {
                    for (var i = 0; i < json.photos.length; i++) {
                        var photo = json.photos[i];
                        document.write('<img src="photos/' + photo.id + '" alt="' + photo.name + '">');
                    }
                }
            </script>
        </sec:ifAllGranted>
	</head>
	<body>

        <h1>Grails Sparklr</h1>

        <div id="content">
            <h2>Home</h2>

            <p>This is a great site to store and view your photos. Unfortunately, we don't have any services
            for printing your photos.  For that, you'll have to go to Tonr.</p>

            <sec:ifNotGranted roles="ROLE_USER">
                <h2>Login</h2>

                <form id="loginForm" name="loginForm" action="${request.contextPath}/j_spring_security_check" method="post">
                    <p><label>Username: <input type='text' name='j_username' value="marissa"></label></p>
                    <p><label>Password: <input type='text' name='j_password' value="koala"></label></p>

                    <p><input name="login" value="Login" type="submit"></p>
                </form>

            </sec:ifNotGranted>

            <sec:ifAllGranted roles="ROLE_USER">

                <div style="text-align: center">
                    <form action="${request.contextPath}/j_spring_security_logout" method="post">
                        <input type="submit" value="Logout">
                    </form>
                </div>

                <h2>Your Photos</h2>

                <p>
                    <script type="text/javascript" src="photos?callback=pictureDisplay&format=json"></script>
                </p>
            </sec:ifAllGranted>
        </div>

        <div id="footer">
            Sample application for <a href="http://grails.org/plugin/spring-security-oauth2-provider" target="_blank">
            Grails Spring Security OAuth2 Provider Plugin</a>. Based on the Sparklr sample application from
            <a href="http://github.com/SpringSource/spring-security-oauth" target="_blank">Spring Security OAuth</a>
        </div>

    </body>
</html>
