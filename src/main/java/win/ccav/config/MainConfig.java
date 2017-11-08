package win.ccav.config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import win.ccav.filter.UserAccessFilter;

/**
 * Created by paul on 2017/4/6.
 */
@SpringBootApplication
@ComponentScan(basePackages = "win.ccav")
public class MainConfig {
    public static void main(String[] args) {
        SpringApplication.run(MainConfig.class,args);
    }
    @Bean
    public FilterRegistrationBean testFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new UserAccessFilter());
        registration.addUrlPatterns("/*");
        //registration.addInitParameter("paramName", "paramValue");
        registration.setName("userAccessFilter");
        registration.setOrder(1);
        return registration;
    }
}
