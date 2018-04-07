package pl.serkus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class AppConfig implements WebMvcConfigurer {
 
   @Bean
   public LocaleResolver localeResolver() {
       SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
       sessionLocaleResolver.setDefaultLocale(new Locale("PL"));
       return sessionLocaleResolver;
   }
 
   @Bean
   public LocaleChangeInterceptor localeChangeInterceptor() {
       LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
       lci.setParamName("lang");
       return lci;
   }
 
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(localeChangeInterceptor());
   }
}