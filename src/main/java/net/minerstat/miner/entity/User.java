package net.minerstat.miner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "users",
       uniqueConstraints=@UniqueConstraint(columnNames={"email"}),
       indexes = { @Index(name = "IDX_MYIDX1", columnList = "uid,username,email")}
)
public class User implements UserDetails {

    private static final long serialVersionUID = 2353528370345499815L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", updatable=false, nullable=false)
    @SequenceGenerator(
            name="USERS_SEQUENCE_GENERATOR",
            sequenceName="USERS_ID_SEQ"
    )
    private Long uid;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "last_login")
    private Date last_login;

    @Column(name = "updated")
    private Date updated;

    @Column(name = "created")
    private Date created;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    private Set<UsersRoles> authorities = new HashSet<>();

    public User() {}

    public User(String username, String email, String password) {
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(Set<UsersRoles> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Set<UsersRoles> getAuthorities() {
        return this.authorities;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public Long getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return "";
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
