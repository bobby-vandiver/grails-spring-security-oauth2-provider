package grails.plugin.springsecurity.oauthprovider.samples.sparklr.oauth2

class Client {

    String clientId
    String clientSecret

    Integer accessTokenValiditySeconds
    Integer refreshTokenValiditySeconds

    static hasMany = [
            authorities: String,
            authorizedGrantTypes: String,
            resourceIds: String,
            scopes: String,
            redirectUris: String
    ]

    static constraints = {
        clientId blank: false, unique: true
        clientSecret nullable: true

        accessTokenValiditySeconds nullable: true
        refreshTokenValiditySeconds nullable: true

        authorities nullable: true
        authorizedGrantTypes nullable: true

        resourceIds nullable: true
        scopes nullable: true

        redirectUris nullable: true
    }

    static mapping = {
        version false
    }
}
