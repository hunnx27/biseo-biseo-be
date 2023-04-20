package com.onz.modules.auth.web;

//import com.google.gson.JsonObject;
//import java.io.IOException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response,
//        AuthenticationException authException) throws IOException {
//        JsonObject jsonObject = new JsonObject();
//
//        jsonObject.addProperty("code", (String) request.getAttribute("exception"));
//        jsonObject.addProperty("message", authException.getMessage());
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.getWriter().print(jsonObject);
//    }
//}
@Deprecated
public class AuthenticationEntryPointImpl{

}