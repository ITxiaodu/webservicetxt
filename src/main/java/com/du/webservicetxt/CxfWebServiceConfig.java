package com.du.webservicetxt;


import com.du.webservicetxt.service.TxtService;
import com.du.webservicetxt.service.TxtServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;

import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfWebServiceConfig {

    @Bean("cxfServletRegistration")
    public ServletRegistrationBean dispatcherServlet(){
        return new ServletRegistrationBean(new CXFServlet(),"/ws/*");
    }

    @Bean
    public TxtService txtService(){
        return new TxtServiceImpl();
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus(){
        SpringBus springBus = new SpringBus();
        return springBus;
    }

    @Bean
    public Endpoint endpoint(TxtService txtService){
        EndpointImpl endpoint = new EndpointImpl(springBus(),txtService);
        endpoint.publish("txtTest");
        return endpoint;
    }
}
