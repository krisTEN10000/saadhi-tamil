package com.sayit.shadhi.Interfaces;

import com.sayit.shadhi.Enums.AstrologyStatus;
import com.sayit.shadhi.Enums.GeneralStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface RequestInterface {
    public AstrologyStatus getStatusOFaPair(long chartId);
    public GeneralStatus giveRequestToPair(long pairId , @ModelAttribute("userId") UserDetails userDetails);

}
