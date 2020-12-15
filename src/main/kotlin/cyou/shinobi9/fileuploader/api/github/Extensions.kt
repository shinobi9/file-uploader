package cyou.shinobi9.fileuploader.api.github

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import cyou.shinobi9.fileuploader.LOG
import io.github.rybalkinsd.kohttp.client.client
import io.github.rybalkinsd.kohttp.dsl.context.HeaderContext
import io.github.rybalkinsd.kohttp.dsl.context.HttpContext
import io.github.rybalkinsd.kohttp.ext.url
import io.github.rybalkinsd.kohttp.interceptors.logging.HttpLoggingInterceptor
import java.net.InetSocketAddress
import java.net.Proxy

const val GITHUB_API_HOST = "https://api.github.com"
val defaultObjectMapper = ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true)

val proxyClient = client {
    interceptors { +HttpLoggingInterceptor() }
    proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("127.0.0.1", 7890))
}

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

fun JsonNode.alsoLogInfo(): JsonNode {
    LOG.info { defaultObjectMapper.writeValueAsString(this) }
    return this
}
