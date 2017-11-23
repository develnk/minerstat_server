package net.minerstat.miner.model.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SecurityUser implements UserDetails {

  private Long id;
  private String username;
  private String password;
  private String email;
  private Collection<? extends GrantedAuthority> authorities;
  private Boolean accountNonExpired = true;
  private Boolean accountNonLocked = true;
  private Boolean credentialsNonExpired = true;
  private Boolean enabled = true;

  public SecurityUser() {
    super();
  }

  public SecurityUser(Long id, String username, String password, String email, Collection<? extends GrantedAuthority> authorities) {
    this.setId(id);
    this.setUsername(username);
    this.setPassword(password);
    this.setEmail(email);
    this.setAuthorities(authorities);
  }

  public Long getId() {
    return this.id;
  }

  private void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return this.username;
  }

  private void setUsername(String username) {
    this.username = username;
  }

  @JsonIgnore
  public String getPassword() {
    return this.password;
  }

  private void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  private void setEmail(String email) {
    this.email = email;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  private void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

  @JsonIgnore
  private Boolean getAccountNonExpired() {
    return this.accountNonExpired;
  }

  public void setAccountNonExpired(Boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.getAccountNonExpired();
  }

  @JsonIgnore
  private Boolean getAccountNonLocked() {
    return this.accountNonLocked;
  }

  public void setAccountNonLocked(Boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.getAccountNonLocked();
  }

  @JsonIgnore
  private Boolean getCredentialsNonExpired() {
    return this.credentialsNonExpired;
  }

  public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.getCredentialsNonExpired();
  }

  @JsonIgnore
  private Boolean getEnabled() {
    return this.enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  @Override
  public boolean isEnabled() {
    return this.getEnabled();
  }

}
