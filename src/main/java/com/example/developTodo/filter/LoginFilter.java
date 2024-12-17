package com.example.developTodo.filter;

import com.example.developTodo.entity.User;
import com.example.developTodo.exception.AuthorizeException;
import com.example.developTodo.repository.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*") // 모든 요청에 필터를 적용
@RequiredArgsConstructor
public class LoginFilter implements Filter {
    private final UserRepository userRepository;

    private static final List<String> WHITE_LIST = Arrays.asList("/login", "/signup");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();

        // 로그인 및 회원가입 URL은 인증 처리에서 제외하는 로직
        if (isWhiteListed(path)) {
            chain.doFilter(request, response); // 화이트 리스트에 포함된 URL은 인증 없이 요청을 계속 처리
            return;
        }

        // 세션에서 로그인한 사용자의 이메일 확인
        String email = (String) httpRequest.getSession().getAttribute("userEmail");

        // 이메일이 없거나 세션이 만료되었을 경우 인증 실패. 403 코드는 관리자 권한 요청 실패로 401과 쓰임이 다르다
        if (email == null) {
            throw new AuthorizeException("이메일이 없거나 세션이 만료되었습니다. 다시 로그인 해주세요");
        }

        // 세션에 저장된 이메일로 사용자 정보 확인
        User user = userRepository.findUserByUsername(email)
                .orElseThrow(() -> new AuthorizeException("이메일 또는 비밀번호가 일치하지 않습니다."));

        // 로그인 성공 시 요청 진행
        chain.doFilter(request, response);
    }

    private boolean isWhiteListed(String path) {
        return WHITE_LIST.stream().anyMatch(path::equals); // URL이 화이트 리스트에 포함되면 true 반환
    }
}

