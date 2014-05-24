package grails.plugin.springsecurity.oauthprovider.samples.sparklr.oauth2

class AuthorizationCode {

    byte[] authentication
    String code

    static constraints = {
        code nullable: false, blank: false, unique: true
        authentication nullable: false, maxSize: 1024 * 4, validator: { val, obj -> val.size() > 0 }
    }
}
