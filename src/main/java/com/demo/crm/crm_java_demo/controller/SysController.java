package com.demo.crm.crm_java_demo.controller;

import com.demo.crm.crm_java_demo.req.LoginReq;
import com.demo.crm.crm_java_demo.res.LoginRes;
import com.demo.crm.crm_java_demo.service.CustomUserDetailsService;
import com.demo.crm.crm_java_demo.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginReq loginReq, HttpSession session) {
        try {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginReq.getUsername());

            if (!customUserDetailsService.checkPassword(loginReq.getPassword(), userDetails.getPassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("錯誤使用者");
            }
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword());
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

            String token = jwtUtil.generateToken(loginReq.getUsername());
            LoginRes loginRes = new LoginRes(loginReq.getUsername(), token);
            return ResponseEntity.ok(loginRes);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/api/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok().build();
    }
}
