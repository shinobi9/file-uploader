@file:Suppress("ClassName")

package cyou.shinobi9.fileuploader.api.github

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import cyou.shinobi9.fileuploader.LOG
import cyou.shinobi9.fileuploader.api.User
import cyou.shinobi9.fileuploader.api.UserAPI
import cyou.shinobi9.fileuploader.api.github.data.GithubUser
import io.github.rybalkinsd.kohttp.dsl.httpGet
import io.github.rybalkinsd.kohttp.jackson.ext.toJson

object githubUserAPI : UserAPI {
    override fun fetchCurrentUser(token: String): User {
        val response = httpGet {
            header {
                recommendedAccept()
                personalAccessToken(token)
            }
            githubAPI("/user")
        }
        val currentUser = with(response) {
            val root = this.toJson()
                .also { node ->
                    ObjectMapper()
                        .configure(SerializationFeature.INDENT_OUTPUT, true)
                        .writeValueAsString(node).also { println(it) }
                }
            GithubUser(root["login"].asText(), root["avatar_url"].asText())
        }
        LOG.info { "fetched user ${currentUser.name}" }
        return currentUser
    }
}
