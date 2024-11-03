package monitoramento.inventario.service

import monitoramento.inventario.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthorizationService(
    private val repository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        return username?.let { repository.findByLogin(it) }
            ?: throw UsernameNotFoundException("User not found with username: $username")
    }
}
