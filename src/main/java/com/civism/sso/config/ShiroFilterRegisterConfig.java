package com.civism.sso.config;


import com.civism.sso.filter.CheckLoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
@Configuration
public class ShiroFilterRegisterConfig {

    @Bean
    public FilterRegistrationBean permissionFilterRegistration(CheckLoginFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
//
//    @Bean
//    public FilterRegistrationBean refreshFilterRegistration(RefreshFilter filter) {
//        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
//        registration.setEnabled(false);
//        return registration;
//    }
//
//    @Bean
//    public FilterRegistrationBean infoPowerRegistration(InfoPowerFilter filter){
//        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
//        registration.setEnabled(false);
//        return registration;
//    }
}
