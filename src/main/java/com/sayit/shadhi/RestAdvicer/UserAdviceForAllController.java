package com.sayit.shadhi.RestAdvicer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayit.shadhi.Contollers.AstrologyController;
import com.sayit.shadhi.Contollers.RequestController;
import com.sayit.shadhi.Contollers.SearchController;
import com.sayit.shadhi.Security.Authentication.SecurityAuthentication;
import com.sayit.shadhi.Security.Pojo.UserPOJO;
import com.sayit.shadhi.Security.UserDetails.UserDetailImp;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = { AstrologyController.class , SearchController.class , RequestController.class })
@AllArgsConstructor
public class UserAdviceForAllController {

    private final ObjectMapper objectMapper;

    @ModelAttribute("userDetails")
    public UserDetails getUserId(HttpServletRequest request){
        SecurityAuthentication authentication =(SecurityAuthentication) SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails =(UserDetailImp) authentication.getUserDetails();
        request.setAttribute("userDetails", userDetails);
        return userDetails;
    }

}
