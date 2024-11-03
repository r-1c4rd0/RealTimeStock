
package monitoramento.inventario.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

class CustomAuthenticationProvider : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication?
    {
        val username = authentication.name
        val password = authentication.credentials as String


        if (username == "user" && password == "password") {
            return UsernamePasswordAuthenticationToken(username, password, emptyList())
        } else {
            return null
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
}
}
