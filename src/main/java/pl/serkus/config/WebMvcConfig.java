package pl.serkus.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Bean
	public BCryptPasswordEncoder pwdEnc() {
		BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
		return bcp;
	}
	
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {

      // Register resource handler for images
      registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/")
            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
   }
}
