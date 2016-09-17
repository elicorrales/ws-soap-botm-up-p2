package com.eli.calc.shape.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.eli.calc.shape.service.ws.ShapeCalculatorWebService;
import com.eli.calc.shape.service.ws.impl.ShapeCalculatorWebServiceImpl;

@Configuration
@ComponentScan(basePackages="com.eli.calc.shape") //this finds base and persist config
//@Import({ShapeCalcBaseContext.class,PersistContext.class})
//@ImportResource({"classpath:META-INF/cxf/cxf.xml"})//required - or use @Bean(Bus.DEF...blah)
public class WebServiceContext {

	private static final Logger logger = LoggerFactory.getLogger(WebServiceContext.class);


    public WebServiceContext() {
    	logger.debug("\n\n\nConstructor\n\n\n");
    }


	//@Bean
    @Bean(name = Bus.DEFAULT_BUS_ID) //this is REQUIRED or you get "No bean named 'cxf' is defined"
	public SpringBus springBus() {
		logger.debug("\n\n\n\nELI: WebServiceContext springBus \n\n\n\n");
	    return new SpringBus();
	}

    @Bean
    public ShapeCalculatorWebService shapeCalculatorWebServiceImpl() {
    	return new ShapeCalculatorWebServiceImpl();
    }

	@Bean
	public Endpoint endpoint() {
		logger.debug("\n\n\n\nELI: WebServiceContext endpoint \n\n\n\n");
	    EndpointImpl endpoint = new EndpointImpl(springBus(), shapeCalculatorWebServiceImpl());
	    
	    //NOT WORK NOT WORK NOT WORK
	    //must be a relative path
	    //endpoint.publish("http://localhost:8080/web-service-soap-bottom-up-p1/services/ws/ShapeCalculatorWebService");
	    
	    //WORKS
	    //endpoint.publish("/web-service-soap-bottom-up-p1/services/ws/ShapeCalculatorWebService");

	    //WORKS
	    endpoint.publish("/ShapeCalculatorWebService");

	    //NOT WORK - it does a clean start up but when entering this at browser:
	    // http://localhost:8080/web-service-soap-bottom-up-p1/services  ,
	    // and clicking on WSDL link, we get
	    // http://localhost:8080/web-service-soap-bottom-up-p1/servicesShapeCalculatorWebService?wsdl
	    // it missing the "/" between services and ShapeCalculat....
	    //endpoint.publish("ShapeCalculatorWebService");

	    return endpoint;
	}

}
