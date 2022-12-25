package ua.com.owu.lessonsspring.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.com.owu.lessonsspring.dao.CustomerDAO;
import ua.com.owu.lessonsspring.models.Customer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomerDAO customerDAO;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {

            @Bean
            public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
            }
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Customer customer = customerDAO.findCustomerByLogin(username);
                List<SimpleGrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority(customer.getRole()));
                User user = new User(
                        customer.getLogin(),
                        customer.getPassword(),
                        Arrays.asList(new SimpleGrantedAuthority(customer.getRole()))
                );
                return user;
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().disable()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/", "/open").permitAll()
                .antMatchers(HttpMethod.POST,"/save","/open").permitAll()
                .antMatchers(HttpMethod.GET,"/secure").hasAnyRole("ADMIN","CLIENT")
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
}
