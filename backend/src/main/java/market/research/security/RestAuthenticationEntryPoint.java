package market.research.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException) throws IOException {
	System.out.println("REQUEST"+ request.getContextPath()); 
	System.out.println("RESPONSE"+ response);  
	System.out.println("authException"+ authException);  
	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
	}
}
