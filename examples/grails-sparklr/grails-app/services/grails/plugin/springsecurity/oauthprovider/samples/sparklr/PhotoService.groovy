package grails.plugin.springsecurity.oauthprovider.samples.sparklr

class PhotoService {

    def springSecurityService

    Collection<PhotoInfo> getPhotosForCurrentUser(String user) {
        PhotoInfo.findAllByUserId(user)
    }

    InputStream loadPhoto(Long id) {
        def username = springSecurityService.currentUser.username
        def photoInfo = PhotoInfo.findByIdAndUserId(id, username)
        photoInfo != null ? this.class.classLoader.getResourceAsStream(photoInfo.resourceURL) : null
    }
}
