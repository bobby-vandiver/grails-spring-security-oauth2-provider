package grails.plugin.springsecurity.oauthprovider

import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken
import org.springframework.security.oauth2.common.ExpiringOAuth2RefreshToken

class GormOAuth2RefreshToken {

    byte[] authentication

    String value
    Date expiration

    Date dateCreated

    static constraints = {
        value blank: false, unique: true
    }

    static mapping = {
        version false
    }

    ExpiringOAuth2RefreshToken toRefreshToken() {
        new DefaultExpiringOAuth2RefreshToken(value, expiration)
    }
}