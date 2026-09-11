package com.example.wanch.controllers;
import com.example.wanch.repositories.*;
import com.example.wanch.services.CompanyService;
import com.example.wanch.services.CustomCompanyDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class registerController {
    private final companyRepositories compRepo;
    private final CustomCompanyDetailsService companyDetailsService;
    private final CompanyService companyService;

    public registerController(companyRepositories compRepo, PasswordEncoder passwordEncoder, CustomCompanyDetailsService companyDetailsService, CompanyService companyService) {
        this.compRepo = compRepo;
        this.companyDetailsService = companyDetailsService;
        this.companyService = companyService;
    }
    @GetMapping("/")
    public String welcomePage(){
        return "welcome";
    }
    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }
    @PostMapping("/register")
    public String createCompany(@RequestParam("companyName") String companyName,
                                @RequestParam("password") String password,
                                HttpServletRequest request){
        if (compRepo.findByCompanyName(companyName) != null){
            return "redirect:/register?exists";
        }
        companyService.registerNewCompany(password,companyName);
        UserDetails companyDetails = companyDetailsService.loadUserByUsername(companyName);
        SecurityContext sc = SecurityContextHolder.createEmptyContext();
        Authentication auth = new UsernamePasswordAuthenticationToken(companyDetails,null,companyDetails.getAuthorities());
        sc.setAuthentication(auth);
        SecurityContextHolder.setContext(sc);

        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,sc);
        return "redirect:/home";


    }
}
