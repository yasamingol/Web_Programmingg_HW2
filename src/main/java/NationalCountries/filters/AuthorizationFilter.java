//package NationalCountries.filters;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.client.WebClientResponseException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class AuthorizationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private WebClient.Builder webClientBuilder;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null || !authorizeRequest(authHeader)) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//            return;
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private boolean authorizeRequest(String authHeader) {
//        try {
//            webClientBuilder.build()
//                    .get()
//                    .uri("http://user-management-service:8082/users/meow")
//                    .header("Authorization", authHeader)
//                    .retrieve()
//                    .bodyToMono(Void.class)
//                    .block();
//            return true;
//        } catch (WebClientResponseException ex) {
//            return false;
//        }
//    }
//}
