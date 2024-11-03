package monitoramento.inventario.model.user

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.EqualsAndHashCode
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

enum class UserRole {
    ADMIN,
    USER
}

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@EqualsAndHashCode
class User(
    @Id
    val id: String,

    val login: String,

    val password: String,

    @Enumerated(EnumType.STRING)
    val role: UserRole
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        when (role) {
            UserRole.ADMIN -> mutableListOf(SimpleGrantedAuthority("ROLE_ADMIN"), SimpleGrantedAuthority("ROLE_USER"))
            UserRole.USER -> mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
        }

    override fun getUsername(): String = login

    override fun getPassword(): String = password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
