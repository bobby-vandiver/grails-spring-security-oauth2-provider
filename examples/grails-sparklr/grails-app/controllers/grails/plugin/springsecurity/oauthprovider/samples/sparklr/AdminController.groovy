package grails.plugin.springsecurity.oauthprovider.samples.sparklr

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication

import java.security.Principal

class AdminController {

    static allowedMethods = [listTokensForUser: 'GET', revokeToken: 'DELETE', listTokensForClient: 'GET']

    def springSecurityService
    def tokenServices

    @Secured(["#oauth2.clientHasRole('ROLE_CLIENT') and (hasRole('ROLE_USER') or #oauth2.isClient()) and #oauth2.hasScope('read')"])
    def listTokensForUser(String user) {
        log.debug "Listing tokens for user [$user]"
        checkResourceOwner(user)
        renderAccessTokens(tokenServices.findTokensByUserName(user))
    }

    @Secured(["#oauth2.clientHasRole('ROLE_CLIENT') and (hasRole('ROLE_USER') or #oauth2.isClient()) and #oauth2.hasScope('write')"])
    def revokeToken(String user, String token) {
        log.debug "Revoking token [$token] for user [$user]"
        checkResourceOwner(user)
        tokenServices.revokeToken(token) ? render(status: 204) : render(status: 404)
    }

    @Secured(["#oauth2.clientHasRole('ROLE_CLIENT') and #oauth2.isClient() and #oauth2.hasScope('read')"])
    def listTokensForClient(String clientId) {
        log.debug "Listing tokens for client [$clientId]"
        renderAccessTokens(tokenServices.findTokensByClientId(clientId))
    }

    private void renderAccessTokens(Collection<OAuth2AccessToken> tokens) {
        def result = []
        tokens.each { prototype ->

            def token = new DefaultOAuth2AccessToken(prototype)
            def clientId = tokenServices.getClientId(token.value)

            if(clientId != null) {
                def map = token.additionalInformation
                map.put('client_id', clientId)
                token.additionalInformation = map
                result << createMapFromAccessToken(token, clientId)
            }
        }

        render(status: 200, contentType: 'application/json', encoding: 'UTF-8') {
            result
        }
    }

    private Map createMapFromAccessToken(OAuth2AccessToken token, String clientId) {
        [
                access_token: token.value,
                token_type: token.tokenType,
                refresh_token: token.refreshToken.value,
                expires_in: token.expiresIn,
                scope: token.scope.join(' '),
                client_id: clientId
        ]
    }

    private void checkResourceOwner(String user) {
        def principal = springSecurityService.principal as Principal
        if(principal instanceof OAuth2Authentication) {
            def authentication = principal as OAuth2Authentication
            if(!authentication.clientOnly && user != principal.name) {
                throw new AccessDeniedException("User [${principal.name}] cannot obtain tokens for user [$user]")
            }
        }
    }
}
