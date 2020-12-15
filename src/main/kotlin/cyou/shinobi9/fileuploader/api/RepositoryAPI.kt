package cyou.shinobi9.fileuploader.api

interface RepositoryAPI {
    fun fetchRepositories(token: String): List<Repository>
}
