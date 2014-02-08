package test.oauth2.provider

class SecuredController {

    def client() {
        render(contentType: 'application/json') {
            [message: 'hello client']
        }
    }

    def user() {
        render(contentType: 'application/json') {
            [message: 'hello user']
        }
    }
}
