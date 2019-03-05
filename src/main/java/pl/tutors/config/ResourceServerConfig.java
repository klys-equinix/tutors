package pl.tutors.config;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    public interface AntMatcherBundle {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry set(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry);
    }

    private final OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler;

    @Autowired(required = false)
    private List<AntMatcherBundle> antMatchers;

    @Value("${dev.hax.enabled:false}")
    private boolean haxEnabled;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/api/agreement").permitAll()
                .antMatchers("/api/business/{businessId:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}/**")
                    .access("@businessAccessGuard.access(authentication, #businessId)")
                .antMatchers(HttpMethod.POST, "/api/user/account/reset").permitAll()
                .antMatchers("/api/user/account/reset/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                .antMatchers("/api/user/**").authenticated()
                .antMatchers("/api/note").authenticated()
                .antMatchers("/api/note/**").authenticated()
                .antMatchers("/api/static-docs/**").permitAll();

        if (antMatchers != null) {
            for (val ant : antMatchers) {
                ant.set(http.authorizeRequests());
            }
        }

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.expressionHandler(oAuth2WebSecurityExpressionHandler);
    }
}
