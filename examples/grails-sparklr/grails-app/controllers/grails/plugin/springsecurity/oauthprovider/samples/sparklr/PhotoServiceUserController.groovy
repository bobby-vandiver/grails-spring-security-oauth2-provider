package grails.plugin.springsecurity.oauthprovider.samples.sparklr

class PhotoServiceUserController {

    def springSecurityService

    def getPhotoServiceUser() {
        def username = springSecurityService.currentUser.username
        render(status: 200, contentType: 'application/json', encoding: 'UTF-8') {
            [name: username, username: username]
        }
    }
}
