package cloud.devyard.rbapi.security;

import cloud.devyard.rbapi.exception.NotFoundException;
import cloud.devyard.rbapi.repository.UserRepository;
import cloud.devyard.rbapi.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;

        String userId = null;
        if (authHeader != null  && authHeader.startsWith("Bearer "))
        {
            token = authHeader.substring(7);

            try {
                userId = jwtUtil.getUserIdFromToken(token);
            } catch (Exception e) {
                log.error("Authorization is not valid/available");
            }
        }

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            try {
                if (jwtUtil.validateToken(token) && !jwtUtil.iSTokenExpired(token))
                {
                    userRepository.findByEmail(userId).orElseThrow(()->{
                        return new NotFoundException("User not found");
                    });

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId , null , new ArrayList<>());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                log.error("Exception occured while validating token");
            }
        }

        filterChain.doFilter(request,response);
    }
}

//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtUtil jwtUtil;
//    private final UserDetailsService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String header = request.getHeader("Authorization");
//        String token = null;
//        String username = null;
//
//        // 1. Check Authorization header
//        if (header != null && header.startsWith("Bearer ")) {
//            token = header.substring(7);
//            try {
//                username = jwtUtil.extractUsername(token);
//            } catch (Exception e) {
//                log.error("Failed to extract username from token: {}", e.getMessage());
//            }
//        }
//
//        // 2. If user not authenticated yet
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            try {
//                // 3. Load user details
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//                // 4. Validate token
//                if (jwtUtil.isTokenValid(token, userDetails)) {
//                    UsernamePasswordAuthenticationToken authToken =
//                            new UsernamePasswordAuthenticationToken(
//                                    userDetails,
//                                    null,
//                                    userDetails.getAuthorities()
//                            );
//
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                    // 5. Set authentication
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                }
//
//            } catch (Exception e) {
//                log.error("Token validation failed: {}", e.getMessage());
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
