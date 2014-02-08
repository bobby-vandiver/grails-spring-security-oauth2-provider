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

        GormOAuth2Client client = new GormOAuth2Client(
                clientId: 'clientId',
                clientSecret: 'clientSecret',
                authorizedGrantTypes: ['password']
        ).save(flush: true)
    }

    def destroy = {
    }
}
