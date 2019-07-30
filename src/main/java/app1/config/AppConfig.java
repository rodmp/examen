package app1.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"app1.dao", "app1.service", "app1"})
@PropertySource("classpath:configuration.properties")
public class AppConfig {

}
