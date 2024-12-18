package com.example.developTodo.filter;

import com.example.developTodo.exception.AuthorizeException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
@WebFilter("/*") // 모든 요청에 필터를 적용
@RequiredArgsConstructor
public class LoginFilter implements Filter {

    private static final String [] WHITE_LIST = {"/auth/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();

        log.info("로그인 필터 로직 실행");

        // 로그인 및 회원가입 URL은 인증 처리에서 제외하는 로직
        if (!isWhiteListed(path)) {
            HttpSession session = httpRequest.getSession(false);

            if (session == null || session.getAttribute("loginStatus") == null) {
                throw new AuthorizeException("로그인 해주세요.");
            }

        }

        log.info("성공 확인");
        // 로그인 성공 시 요청 진행
        chain.doFilter(request, response);
    }
    private boolean isWhiteListed(String path) {
        // URL이 화이트 리스트에 포함되면 true 반환
        return PatternMatchUtils.simpleMatch(WHITE_LIST,path);
    }
}

