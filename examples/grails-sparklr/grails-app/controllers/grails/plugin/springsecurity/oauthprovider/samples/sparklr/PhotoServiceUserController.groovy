package grails.plugin.springsecurity.oauthprovider.samples.sparklr

import grails.plugin.springsecurity.annotation.Secured

@Secured(["hasRole('ROLE_USER') and #oauth2.hasScope('read')"])
class PhotoServiceUserController {

    def springSecurityService

    def getPhotoServiceUser() {
        def username = springSecurityService.currentUser.username
        render(status: 200, contentType: 'application/json', encoding: 'UTF-8') {
            [name: username, username: username]
        }
    }
}
