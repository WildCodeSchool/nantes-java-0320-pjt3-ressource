package com.wildcodeschool.ressource.service;

import com.wildcodeschool.ressource.entity.Admin;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface UserService {

    void autoLogin(HttpServletRequest request, String username, String password);

    Admin getLoggedUsername();
}
