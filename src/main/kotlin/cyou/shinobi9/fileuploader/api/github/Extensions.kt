package cyou.shinobi9.fileuploader.api.github

import io.github.rybalkinsd.kohttp.dsl.context.HeaderContext
import io.github.rybalkinsd.kohttp.dsl.context.HttpContext
import io.github.rybalkinsd.kohttp.ext.url

const val GITHUB_API_HOST = "https://api.github.com"

fun String.withGithubHost(): String = "$GITHUB_API_HOST$this"

fun HttpContext.githubAPI(uri: String) {
    url("$GITHUB_API_HOST$uri")
}

fun HeaderContext.recommendedAccept() {
    "Accept" to "application/vnd.github.v3+json"
}

fun HeaderContext.personalAccessToken(token: String) {
    "Authorization" to "token $token"
}
