package com.github.andreldsr.librarymanager.modules.user.data

import com.github.andreldsr.librarymanager.modules.role.Role
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.proxy.HibernateProxy
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "\"user\"")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val login: String,
    @Column(name = "password")
    val userPassword: String,
    @ManyToMany
    val roles: List<Role> = emptyList(),
    val active: Boolean = true,
    @ManyToOne(cascade = [CascadeType.PERSIST])
    val profile: Profile? = null
) : UserDetails {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || HibernateProxy::class.java.isInstance(other)) return false
        other as User
        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString() = this::class.simpleName + "(id = $id )"

    override fun getAuthorities() = roles

    override fun getPassword() = userPassword

    override fun getUsername() = login

    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}
