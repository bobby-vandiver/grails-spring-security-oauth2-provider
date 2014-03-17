package grails.plugin.springsecurity.oauthprovider.samples.sparklr

import org.springframework.security.oauth2.provider.AuthorizationRequest
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator

class AccessConfirmationController {

    ClientDetailsService clientDetailsService
    private static final String AUTHORIZATION_REQUEST_KEY = 'authorizationRequest'

    WebResponseExceptionTranslator webResponseExceptionTranslator

    def getAccessConfirmation() {
        def clientAuth = session.getAttribute(AUTHORIZATION_REQUEST_KEY) as AuthorizationRequest
        session.removeAttribute(AUTHORIZATION_REQUEST_KEY)

        def client = clientDetailsService.loadClientByClientId(clientAuth.clientId)
        render(view: 'access_confirmation', model: [auth_request: clientAuth, client: client])
    }

    def handleError() {
        def e = webResponseExceptionTranslator.translate(request.exception).body
        render(view: '/oauth/oauth_error', model: [message: 'There was a problem with the OAuth2 protocol', error: e])
    }
}
