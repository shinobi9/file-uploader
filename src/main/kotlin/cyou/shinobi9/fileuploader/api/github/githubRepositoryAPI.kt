@file:Suppress("ClassName", "UnnecessaryVariable")

package cyou.shinobi9.fileuploader.api.github

import cyou.shinobi9.fileuploader.api.Repository
import cyou.shinobi9.fileuploader.api.RepositoryAPI
import cyou.shinobi9.fileuploader.api.github.data.GithubRepository
import io.github.rybalkinsd.kohttp.dsl.httpGet
import io.github.rybalkinsd.kohttp.jackson.ext.toJson

object githubRepositoryAPI : RepositoryAPI {
    override fun fetchRepositories(token: String): List<Repository> {
        val response = httpGet(proxyClient) {
            githubAPI("/user/repos")
            header {
                recommendedAccept()
                personalAccessToken(token)
            }
            param {
                "visibility" to "all"
            }
        }
        val json = response.toJson().alsoLogInfo()
        val result = json.map {
            GithubRepository(it["full_name"].asText(), it["html_url"].asText(), it["description"].asText())
        }
        return result
    }
}
