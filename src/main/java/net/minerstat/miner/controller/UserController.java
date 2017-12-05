package net.minerstat.miner.controller;

import net.minerstat.miner.dao.RoleDao;
import net.minerstat.miner.dao.UserRepository;
import net.minerstat.miner.entity.User;
import net.minerstat.miner.entity.UsersRoles;
import net.minerstat.miner.model.json.request.AuthenticationRequest;
import net.minerstat.miner.model.json.response.AuthenticationResponse;
import net.minerstat.miner.security.TokenUtils;
import net.minerstat.miner.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping(value = {"/api/v1/user"})
public class UserController {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    // Create new user.
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<User>  createUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        // New User object for save to db.
        User newUser = new User(user.getUsername(), user.getEmail(), hashedPassword);
        Date currDate = new Date();
        newUser.setCreated(currDate);
        newUser.setUpdated(currDate);
        newUser.setEnabled(true);

        // New created user.
        User createdUser = userService.add(newUser);

        // Added role to new created user.
        HashSet<UsersRoles> roles = new HashSet<>();
        Long ridAuth = roleDao.findRidByName("Authenticated");
        roles.add(new UsersRoles(createdUser.getUid(), ridAuth));
        createdUser.setAuthorities(roles);
        userService.update(createdUser);

        response.setHeader("Location", request.getRequestURL().append("/").append(createdUser.getUid()).toString());
        return ResponseEntity.ok(createdUser);
    }

    // Update user.
    @RequestMapping(value = "{uid}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("(hasAuthority('Authenticated') AND isAuthenticated() AND @UP.uidIsCurrentUser(#uid)) OR hasAuthority('Administrator')")
    public @ResponseBody boolean updateUser(@RequestBody User user, @PathVariable String uid) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = user.getPassword();
        String hashedPassword = "";
        if (password != null &&  !password.isEmpty()) {
            hashedPassword = passwordEncoder.encode(password);
        }

        String name = user.getUsername();
        String email = user.getEmail();
        Set<UsersRoles> authorities = user.getAuthorities();

        User updatedUser = userService.get(Long.valueOf(uid));
        updatedUser.setUpdated(new Date());
        if (hashedPassword != null &&  !hashedPassword.isEmpty()) {
            updatedUser.setPassword(hashedPassword);
        }

        if (name != null && !name.isEmpty()) {
            updatedUser.setUsername(name);
        }

        if (email != null && !email.isEmpty()) {
            updatedUser.setEmail(email);
        }

        if (authorities != null && !authorities.isEmpty()) {
            updatedUser.setAuthorities(authorities);
        }

        userService.update(updatedUser);
        return true;
    }

    // Retrieve user.
    @RequestMapping(value = "{uid}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("(hasAuthority('Authenticated') AND isAuthenticated() AND @UP.uidIsCurrentUser(#uid)) OR hasAuthority('Administrator')")
    public ResponseEntity<User> getUser(@PathVariable String uid) {
        User user = userService.get(Long.valueOf(uid));
        return ResponseEntity.ok(user);
    }

    // Delete user.
    @RequestMapping(value="{uid}", method=RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody boolean removeEmp(@PathVariable String uid) {
        return userService.delete(Long.valueOf(uid));
    }

    // User authorization.
    @RequestMapping(value="login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {
        // Perform the authentication
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getName(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-authentication so we can generate token
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getName());
        String token = tokenUtils.generateToken(userDetails);

        // Return the token
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

}
