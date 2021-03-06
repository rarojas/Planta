package com.selmec.plantaselmec.Models;
// Generated Oct 20, 2014 5:05:41 PM by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Usuarios generated by hbm2java
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "usuarios", catalog = "test"
)
public class Usuarios implements java.io.Serializable, UserDetails {

    private Integer id;
    private String nombres;
    private String apellidos;
    private String email;
    private String rol;
    private String password;
    private Set ensamblebases = new HashSet(0);
    private Set roles = new HashSet(0);

    public Usuarios() {
    }

    public Usuarios(String nombres, String apellidos, String email, String rol, String password) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.rol = rol;
        this.password = password;
    }

    public Usuarios(String nombres, String apellidos, String email, String rol, String password, Set ensamblebases) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.rol = rol;
        this.password = password;
        this.ensamblebases = ensamblebases;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "nombres", nullable = false, length = 50)
    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Column(name = "apellidos", nullable = false, length = 50)
    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "rol", nullable = false, length = 50)
    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarios")
    public Set getEnsamblebases() {
        return this.ensamblebases;
    }

    public void setEnsamblebases(Set ensamblebases) {
        this.ensamblebases = ensamblebases;
    }

    @Column(name = "password", nullable = false, length = 50)
    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null) {
            return Collections.emptyList();
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Object role : roles) {
            authorities.add(new SimpleGrantedAuthority(((Rol) role).getNbRol()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * @return the roles
     */
    public Set getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(Set roles) {
        this.roles = roles;
    }

}
