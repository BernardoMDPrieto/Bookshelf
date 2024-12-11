package br.com.magementms.controller;

import br.com.magementms.dto.LoginRequest;
import br.com.magementms.service.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private AuthenticationManager authenticationManager() {
        return authenticationManager;
    }

    @PostMapping("/login")
    public DeferredResult<ResponseEntity<String>> login(@RequestBody @Valid LoginRequest loginRequest) {
        DeferredResult<ResponseEntity<String>> dr = new DeferredResult<>();
        String token = authorizationService.login(loginRequest,authenticationManager());
        dr.setResult(ResponseEntity.ok(token));
        return dr;
    }
}

























