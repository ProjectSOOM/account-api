package com.soom.account_api.global.security.filter;

import com.soom.account_api.domain.sign.service.LoginTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticateFilter extends OncePerRequestFilter {
    private static final String TOKEN_PREFIX = "Bearer "; //TODO 띄어쓰기떄문이아니라 index 떄문에 substring에 +1 한거아닐까?

    private final UserDetailsService userDetailsService;
    private final LoginTokenService loginTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");
        if(!(header != null && header.startsWith(TOKEN_PREFIX))) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = getToken(header); //token을 초기화한다 (logic 상의 Null-safe 보장)
        final String id = getId(token); //id를 초기화한다 (logic 상의 Null-safe 보장)

        System.out.println(token);
        System.out.println(id);

        //Security COntext 에 인증된 객체가 없을경우
        if(SecurityContextHolder.getContext().getAuthentication() == null)
            //인증객체를 생성하여 Context에 저장한다
            saveToContext(userDetailsService.loadUserByUsername(id), request);

    }

    private void saveToContext(UserDetails userDetails, HttpServletRequest request) {
        final UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private String getId(String token) {
        return String.valueOf(loginTokenService.id(token));
    }

    private String getToken(String header) {
        return header.substring(TOKEN_PREFIX.length());
    }
}
