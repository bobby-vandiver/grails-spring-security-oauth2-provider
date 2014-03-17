import grails.plugin.springsecurity.oauthprovider.exceptions.OAuth2AuthorizationEndpointException

class UrlMappings {

	static mappings = {

        "/login/$action?"(controller: 'login')
        "/logout/$action?"(controller: 'logout')

        "500"(controller: 'accessConfirmation', action: 'handleError', exception: OAuth2AuthorizationEndpointException)

        "/oauth/users/$user/tokens"(controller: 'admin', action: 'listTokensForUser')
        "/oauth/users/$user/tokens/$token"(controller: 'admin', action: 'revokeToken')

        "/oauth/clients/$client/tokens"(controller: 'admin', action: 'listTokensForClient')

        "/me"(controller: 'photoServiceUser', action: 'getPhotoServiceUser')

        "/photos/$photoId"(controller: 'photo', action: 'getPhoto')
        "/photos"(controller: 'photo', action: 'getPhotos')

        "/photos/trusted/message"(controller: 'photo', action: 'getTrustedClientMessage')
        "/photos/user/message"(controller: 'photo', action: 'getTrustedUserMessage')

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
