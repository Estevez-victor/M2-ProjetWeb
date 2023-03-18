package spring.devrep.sante.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfiguration {

        private final JPAUserDetailsService jpaUDS;

        public AppSecurityConfiguration(JPAUserDetailsService jpaUserDetailsService) {
                this.jpaUDS = jpaUserDetailsService;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests((requests) -> requests
                                .antMatchers("/sante", "/sante/lp", "/sante/lp2", "/", "/sante/addpro",
                                                "/sante/newpro", "/sante/addvisitor",
                                                "/sante/newvisitor")
                                .permitAll()
                                .anyRequest().authenticated())
                                .formLogin((form) -> form
                                                .loginPage("/login")
                                                .permitAll())
                                .userDetailsService(jpaUDS)
                                .logout((logout) -> logout.permitAll())
                                .csrf().disable();

                return http.build();
        }

        @Bean
        PasswordEncoder passwordEncoder() {
                return NoOpPasswordEncoder.getInstance();
        }

}
