@file:Suppress("ClassName")
package cyou.shinobi9.fileuploader.api.github

import cyou.shinobi9.fileuploader.api.Repository
import cyou.shinobi9.fileuploader.api.RepositoryAPI
import io.github.rybalkinsd.kohttp.dsl.httpGet

object githubRepositoryAPI : RepositoryAPI {
    override fun fetchRepositories(): List<Repository> {
        httpGet {
            githubAPI("/users/{username}/repos")
        }
        return emptyList()
    }
}
