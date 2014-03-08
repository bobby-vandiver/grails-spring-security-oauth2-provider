class UrlMappings {

	static mappings = {

        "/oauth/users/$user/tokens"(controller: 'admin', action: 'listTokensForUser')
        "/oauth/users/$user/tokens/$token"(controller: 'admin', action: 'revokeToken')

        "/oauth/clients/$client/tokens"(controller: 'admin', action: 'listTokensForClient')

        "/me"(controller: 'photoServiceUser', action: 'getPhotoServiceUser')

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
