package org.superbiz.struts;

import com.opensymphony.module.sitemesh.filter.PageFilter;
import org.apache.struts2.dispatcher.ActionContextCleanUp;
import org.apache.struts2.dispatcher.FilterDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;
import static java.util.Collections.singletonList;

@SpringBootApplication
public class Application
{
    public static void main(String... args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FilterRegistrationBean filterDispatcher()
    {
        return buildFilter(0, new FilterDispatcher());
    }

    @Bean
    public FilterRegistrationBean actionContextCleanup()
    {
        return buildFilter(1, new ActionContextCleanUp());
    }

    @Bean
    public FilterRegistrationBean pageFilter()
    {
        return buildFilter(2, new PageFilter());
    }

    private FilterRegistrationBean buildFilter(int order, Filter filter)
    {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setUrlPatterns(singletonList("/*"));
        filterRegistrationBean.setOrder(order);

        return filterRegistrationBean;
    }

}
