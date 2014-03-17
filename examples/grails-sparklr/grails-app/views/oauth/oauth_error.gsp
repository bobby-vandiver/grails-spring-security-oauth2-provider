<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
        <title>Grails Sparklr</title>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'style.css')}" type="text/css"/>
    </head>
    <body>
        <h1>Grails Sparklr OAuth2 Error</h1>

        <div id="content">
            <p>${message} (${error.summary})</p>
            <p>Please go back to your client application and try again, or contact the owner and ask for support</p>
        </div>

        <div id="footer">
            Sample application for <a href="http://grails.org/plugin/spring-security-oauth2-provider" target="_blank">
            Grails Spring Security OAuth2 Provider Plugin</a>. Based on the Sparklr sample application from
            <a href="http://github.com/SpringSource/spring-security-oauth" target="_blank">Spring Security OAuth</a>
        </div>

    </body>
</html>
