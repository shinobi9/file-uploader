package cyou.shinobi9.fileuploader.controller

import cyou.shinobi9.fileuploader.LOG
import cyou.shinobi9.fileuploader.api.RepositoryAPI
import cyou.shinobi9.fileuploader.api.UserAPI
import cyou.shinobi9.fileuploader.api.github.githubRepositoryAPI
import cyou.shinobi9.fileuploader.api.github.githubUserAPI
import cyou.shinobi9.fileuploader.model.RepositoryModel
import cyou.shinobi9.fileuploader.model.UserModel
import cyou.shinobi9.fileuploader.model.UserModel.User.Companion.defaultImageUrl
import javafx.collections.ObservableList
import kotlinx.coroutines.*
import kotlinx.coroutines.javafx.JavaFx
import tornadofx.Controller
import kotlin.coroutines.CoroutineContext

class GithubController : Controller(), CoroutineScope {
    private val userAPI: UserAPI = githubUserAPI
    private val repositoryAPI: RepositoryAPI = githubRepositoryAPI
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job
    private val job: Job = SupervisorJob()

    fun user(userModel: UserModel) {
        launch {
            LOG.info { "${Thread.currentThread().name} request for user info...." }
            val user = userAPI.fetchCurrentUser(userModel.personalAccessToken)
            withContext(Dispatchers.JavaFx) {
                LOG.info { "${Thread.currentThread().name} render" }
                userModel.name = user.name
                userModel.avatarUrl = user.avatarUrl
            }
        }
    }

    fun clearAll(userModel: UserModel, repos: ObservableList<RepositoryModel>) {
        userModel.name = ""
        userModel.avatarUrl = defaultImageUrl
        userModel.personalAccessToken = ""
        if (repos.isNotEmpty()) repos.clear()
    }

    fun repositories(userModel: UserModel, repos: ObservableList<RepositoryModel>) {
        launch {
            val repositories = repositoryAPI.fetchRepositories(userModel.personalAccessToken)
            withContext(Dispatchers.JavaFx) {
                LOG.info { "${Thread.currentThread().name} render" }
//                RepositoryModel(
                val list = repositories.map {
                    RepositoryModel().apply {
                        url = it.url
                        name = it.name
                        description = it.description
                    }
                }
                if (repos.isNotEmpty()) repos.clear()
                repos.addAll(list)
            }
        }
    }
}
