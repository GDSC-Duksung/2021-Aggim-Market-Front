package com.example.aggim.api

import com.example.aggim.api.response.ApiResponse
import com.example.aggim.common.Prefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.error

class TokenAuthenticator : Authenticator, AnkoLogger {
    override fun authenticate(
            route: Route?,
            response: Response
    ): Request? {
        if (response.code == 401) {
            debug("Token renewal required")
            return runBlocking {
                val tokenResponse = refreshToken()

                if (tokenResponse.success) {
                    debug("Token renewal successful")
                    Prefs.token = tokenResponse.data
                } else {
                    error("Token renewal failed")
                    Prefs.token = null
                    Prefs.refreshToken = null
                }

                Prefs.token?.let { token ->
                    debug("토큰 = $token")
                    response.request
                            .newBuilder()
                            .header("Authorization", token)
                            .build()
                }
            }
        }
        return null
    }

    private suspend fun refreshToken() =
            withContext(Dispatchers.IO) {
                try {
                    AggimRefreshApi.instance.refreshToken()
                } catch (e: Exception) {
                    ApiResponse.error<String>("Authentication failed")
                }
            }

}
