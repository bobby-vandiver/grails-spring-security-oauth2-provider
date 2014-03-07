package grails.plugin.springsecurity.oauthprovider.samples.sparklr

class Role {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
