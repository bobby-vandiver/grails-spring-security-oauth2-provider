import grails.plugin.springsecurity.oauthprovider.GormOAuth2Client
import test.oauth2.provider.Role
import test.oauth2.provider.User
import test.oauth2.provider.UserRole

class BootStrap {

    def init = { servletContext ->
        Role role = new Role(authority: 'ROLE_USER').save(flush: true)
        User user = new User(
                username: 'bob',
                password: 'pass',
                enabled: true,
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false
        ).save(flush:true)
        UserRole.create(user, role, true)

        new GormOAuth2Client(
                clientId: 'my-trusted-client',
                authorizedGrantTypes: ['password', 'authorization_code', 'refresh_token', 'implicit'],
                authorities: ['ROLE_CLIENT', 'ROLE_TRUSTED_CLIENT'],
                scopes: ['read', 'write', 'trust'],
                accessTokenValiditySeconds: 60
        ).save(flush: true)

        new GormOAuth2Client(
                clientId: 'my-trusted-client-with-secret',
                clientSecret: 'somesecret',
                authorizedGrantTypes: ['password', 'authorization_code', 'refresh_token', 'implicit'],
                authorities: ['ROLE_CLIENT', 'ROLE_TRUSTED_CLIENT'],
        ).save(flush: true)

        new GormOAuth2Client(
                clientId: 'my-client-with-secret',
                clientSecret: 'secret',
                authorizedGrantTypes: ['client_credentials'],
                authorities: ['ROLE_CLIENT'],
                scopes: ['read']
        ).save(flush: true)

        new GormOAuth2Client(
                clientId: 'my-less-trusted-client',
                authorizedGrantTypes: ['authorization_code', 'implicit'],
                authorities: ['ROLE_CLIENT']
        ).save(flush: true)

        new GormOAuth2Client(
                clientId: 'my-less-trusted-autoapprove-client',
                authorizedGrantTypes: ['implicit'],
                authorities: ['ROLE_CLIENT'],
                scopes: ['read', 'write', 'trust']
        ).save(flush: true)

        new GormOAuth2Client(
                clientId: 'my-client-with-registered-redirect',
                authorizedGrantTypes: ['authorization_code', 'client_credentials'],
                authorities: ['ROLE_CLIENT'],
                redirectUris: ['http://anywhere?key=value'],
                scopes: ['read', 'trust']
        ).save(flush: true)

        new GormOAuth2Client(
                clientId: 'my-untrusted-client-with-registered-redirect',
                authorizedGrantTypes: ['authorization_code'],
                authorities: ['ROLE_CLIENT'],
                redirectUris: ['http://anywhere'],
                scopes: ['read']
        ).save(flush: true)

        new GormOAuth2Client(
                clientId: 'tonr',
                clientSecret: 'secret',
                resourceIds: ['sparklr'],
                authorizedGrantTypes: ['authorization_code', 'implicit'],
                authorities: ['ROLE_CLIENT'],
                scopes: ['read', 'write']
        ).save(flush: true)
    }

    def destroy = {
    }
}
