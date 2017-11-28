package net.minerstat.miner.configuration;

import net.minerstat.miner.security.auth.AuthenticationTokenFilter;
import net.minerstat.miner.security.auth.EntryPointUnauthorizedHandler;
import net.minerstat.miner.security.TokenUtils;
import net.minerstat.miner.security.auth.RestAuthenticationEntryPoint;
import net.minerstat.miner.security.auth.TokenAuthenticationFilter;
import net.minerstat.miner.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private EntryPointUnauthorizedHandler unauthorizedHandler;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private SecurityService securityService;

  @Autowired
  private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

  @Autowired
  public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder
      .userDetailsService(this.userDetailsService)
        .passwordEncoder(passwordEncoder());
  }

  @Autowired
  TokenUtils tokenHelper;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
    AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
    authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
    return authenticationTokenFilter;
  }

  @Bean
  public SecurityService securityService() {
    return this.securityService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    List<RequestMatcher> csrfMethods = new ArrayList<>();
    Arrays.asList( "POST", "PUT", "PATCH", "DELETE" )
            .forEach( method -> csrfMethods.add( new AntPathRequestMatcher( "/**", method ) ) );


    http
      .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ).and()
      .exceptionHandling().authenticationEntryPoint( restAuthenticationEntryPoint ).and()
      .authorizeRequests()
      .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
      .antMatchers(HttpMethod.POST, "/api/v1/user").permitAll()
      .antMatchers("/v1/worker/**").permitAll()
      .antMatchers( "/v1/user/**").authenticated()
      .anyRequest().authenticated().and()
      .addFilterBefore(new TokenAuthenticationFilter(tokenHelper, userDetailsService), BasicAuthenticationFilter.class);

    // Custom JWT based authentication
    http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    http.csrf().disable();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    // TokenAuthenticationFilter will ignore the below paths
    web.ignoring().antMatchers(
            HttpMethod.POST,
            "/api/v1/user/login",
            "/api/v1/worker/**"
    );
    web.ignoring().antMatchers(
            HttpMethod.GET,
            "/"
    );

    web.expressionHandler(new DefaultWebSecurityExpressionHandler() {
      @Override
      protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {
        WebSecurityExpressionRoot root = (WebSecurityExpressionRoot) super.createSecurityExpressionRoot(authentication, fi);
        root.setDefaultRolePrefix(""); //remove the prefix ROLE_
        return root;
      }
    });
  }

}
