package com.everyme.global.security.auth.handler;

import com.everyme.domain.user.repository.UserRepository;
import com.everyme.domain.user.service.UserService;
import com.everyme.global.security.auth.model.DetailsUser;
import com.everyme.global.security.auth.model.dto.TokenDTO;
import com.everyme.global.security.common.AuthConstants;
import com.everyme.global.security.common.utils.ConvertUtil;
import com.everyme.global.security.common.utils.TokenUtils;
import com.everyme.domain.user.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Configuration
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    public UserService userService;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        User user = ((DetailsUser) authentication.getPrincipal()).getUser();
        JSONObject jsonValue = (JSONObject) ConvertUtil.convertObjectToJsonObject(user);
        HashMap<String, Object> responseMap = new HashMap<>();
        JSONObject jsonObject;

        if (user.getUserState().equals("N")) {
            responseMap.put("userInfo", jsonValue);
            responseMap.put("message", "휴면 상태인 계정입니다.");
        } else {
            responseMap.put("userInfo", jsonValue);
            responseMap.put("message", "로그인 성공!");

            String existedToken = user.getUserToken();

            // 기존에 토큰이 있는 경우
            if (existedToken != null && !existedToken.isEmpty()) {
                response.addHeader(AuthConstants.AUTH_HEADER, AuthConstants.TOKEN_TYPE + " " + existedToken);
                System.out.println(existedToken);
            } else {
                // 기존에 토큰이 없는 경우, 새 토큰 생성
                String newToken = TokenUtils.generateJwtToken(user);
                user.setUserToken(newToken);
                userService.login(user);
                response.addHeader(AuthConstants.AUTH_HEADER, AuthConstants.TOKEN_TYPE + " " + newToken);
            }
        }

        jsonObject = new JSONObject(responseMap);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonObject);
        printWriter.flush();
        printWriter.close();
    }
}
