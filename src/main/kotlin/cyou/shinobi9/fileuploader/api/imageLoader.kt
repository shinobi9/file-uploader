@file:Suppress("ClassName")

package cyou.shinobi9.fileuploader.api

import io.github.rybalkinsd.kohttp.client.client
import io.github.rybalkinsd.kohttp.dsl.httpGet
import io.github.rybalkinsd.kohttp.ext.asStream
import io.github.rybalkinsd.kohttp.ext.url
import java.net.InetSocketAddress
import java.net.Proxy

object imageLoader {

    val client = client {
        proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("127.0.0.1", 7890))
    }

    fun loadImageIfExistProxySettings(url: String) = httpGet(client) {
        url(url)
    }.asStream().let { requireNotNull(it) }
}
