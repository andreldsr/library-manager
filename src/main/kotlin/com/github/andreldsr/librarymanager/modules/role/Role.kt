package com.github.andreldsr.librarymanager.modules.role

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.proxy.HibernateProxy
import org.springframework.security.core.GrantedAuthority

@Entity
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String
) : GrantedAuthority {
    override fun getAuthority() = name

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || HibernateProxy::class.java.isInstance(other)) return false
        other as Role

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
