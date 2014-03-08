package grails.plugin.springsecurity.oauthprovider.samples.sparklr

class PhotoInfo {

    String name
    String userId
    String resourceURL

    static mapping = {
        id generator: 'assigned'
    }
}
