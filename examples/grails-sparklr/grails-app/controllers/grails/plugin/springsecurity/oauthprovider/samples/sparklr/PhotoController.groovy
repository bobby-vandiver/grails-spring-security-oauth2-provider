package grails.plugin.springsecurity.oauthprovider.samples.sparklr

import grails.plugin.springsecurity.annotation.Secured

class PhotoController {

    def springSecurityService
    def photoService

    @Secured(["hasRole('ROLE_USER') or #oauth2.hasScope('read')"])
    def getPhoto(Long photoId) {
        def photo = photoService.loadPhoto(photoId)

        if(photo == null) {
            response.status = 404
            response.contentLength = 0
        }
        else {
            def content = photo.bytes
            response.status = 200
            response.contentType = 'image/jpeg'
            response.contentLength = content.size()
            response.outputStream << content
        }
    }

    @Secured(["hasRole('ROLE_USER') or #oauth2.hasScope('read')"])
    def getPhotos(String format, String callback) {
        def username = springSecurityService.currentUser.username
        def photos = photoService.getPhotosForCurrentUser(username)

        if(format == 'xml') {
            getXmlPhotos(photos)
        }
        else if(format == 'json') {
            getJsonPhotos(photos, callback)
        }
        else {
            render(status: 400)
        }
    }

    private void getJsonPhotos(Collection<PhotoInfo> photos, String callback) {
        def out = new StringBuilder()
        if(callback != null) {
            out.append(callback).append("( ")
        }
        out.append("{ \"photos\" : [ ")

        def photosList = []
        photos.each { photo -> photosList << "{ \"id\" : \"${photo.id}\" , \"name\" : \"${photo.name}\" }" }

        def joinedList = photosList.join(',')
        out.append(joinedList)

        out.append("] }")
        if(callback != null) {
            out.append(" )")
        }

        render(status: 200, contentType: 'application/json', text: out)
    }

    private void getXmlPhotos(Collection<PhotoInfo> photos) {
        def out = new StringBuilder()
        out.append("<photos>")

        photos.each { photo ->
            out.append("<photo id=\"${photo.id}\" name=\"${photo.name}\"/>")
        }
        out.append("</photos>")
        render(status: 200, contentType: 'application/xml', text: out)
    }

    @Secured(["#oauth2.clientHasRole('ROLE_CLIENT') and #oauth2.isClient() and #oauth2.hasScope('trust')"])
    def getTrustedClientMessage() {
        render "Hello, Trusted Client"
    }

    @Secured(["hasRole('ROLE_USER') and #oauth2.hasScope('trust')"])
    def getTrustedUserMessage() {
        def username = springSecurityService.loggedIn ? springSecurityService.currentUser.username : ''
        render "Hello, Trusted User ${username}"
    }
}
