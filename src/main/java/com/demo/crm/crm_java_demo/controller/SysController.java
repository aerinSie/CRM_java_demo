package com.demo.crm.crm_java_demo.controller;

import com.demo.crm.crm_java_demo.req.LoginReq;
import com.demo.crm.crm_java_demo.res.ApiResponse;
import com.demo.crm.crm_java_demo.res.LoginRes;
import com.demo.crm.crm_java_demo.service.SysUserDetailsService;
import com.demo.crm.crm_java_demo.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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
    private SysUserDetailsService sysUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginRes>> login(@RequestBody @Valid LoginReq loginReq, HttpSession session) {
        try {
            UserDetails userDetails = sysUserDetailsService.loadUserByUsername(loginReq.getUsername());

            if (!sysUserDetailsService.checkPassword(loginReq.getPassword(), userDetails.getPassword())) {
                ApiResponse<LoginRes> response = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"錯誤使用者");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
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

            return new ResponseEntity<>(new ApiResponse<>(loginRes), HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<LoginRes> response = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ResponseEntity<>(new ApiResponse<>("ok"), HttpStatus.OK);
    }
}
