package cyou.shinobi9.fileuploader.api

interface UserAPI {
    fun fetchCurrentUser(token: String): User
}
