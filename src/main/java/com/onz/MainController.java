package com.onz;

import com.onz.modules.auth.web.dto.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/hello")
    public HttpStatus hello() {
        return HttpStatus.OK;
    }

    @GetMapping("/")
    public void main(HttpServletRequest req, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userPrincipal = ((UserPrincipal) authentication.getPrincipal());
            log.info(String.valueOf(userPrincipal));
        }
        log.info(String.valueOf(req));
        log.info(String.valueOf(model));
    }

}
