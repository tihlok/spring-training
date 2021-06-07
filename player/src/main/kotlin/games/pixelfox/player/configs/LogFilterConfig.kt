package games.pixelfox.player.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Configuration
class LogFilterConfig {
    class LogFilter : OncePerRequestFilter() {
        private val now: Long
            get() = System.currentTimeMillis()

        override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
            val start = now
            val wrappedRequest = ContentCachingRequestWrapper(request)
            val wrappedResponse = ContentCachingResponseWrapper(response)
            filterChain.doFilter(wrappedRequest, wrappedResponse)

            logger.debug("[${response.status}] [${now - start}ms] ${request.method} ${request.requestURI}")
            wrappedResponse.copyBodyToResponse()
        }
    }

    @Bean
    fun logFilter(): OncePerRequestFilter = LogFilter()
}
