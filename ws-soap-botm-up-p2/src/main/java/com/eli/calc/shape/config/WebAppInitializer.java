package com.eli.calc.shape.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


public class WebAppInitializer implements WebApplicationInitializer {

	private static final Logger logger = LoggerFactory.getLogger(WebAppInitializer.class);
	
    public WebAppInitializer() {
    	logger.debug("\n\n\nConstructor\n\n\n");
    }


	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		logger.debug("\n\n\n\nELI: Web AppInitializer onStartup() \n\n\n\n");

		AnnotationConfigWebApplicationContext rootCtx = new AnnotationConfigWebApplicationContext();
		rootCtx.register(WebServiceContext.class);
		
		logger.debug("\n\n\n\nELI: Web AppInitializer root context loader listener.. \n\n\n\n");
		ContextLoaderListener loaderListener = new ContextLoaderListener(rootCtx);

		logger.debug("\n\n\n\nELI: Web AppInitializer add root context loader listener to servletcontext..... \n\n\n\n");
		servletContext.addListener(loaderListener);

		//logger.debug("\n\n\n\nELI: Web AppInitializer add shape calc servlet context listener to servletcontext..... \n\n\n\n");
		//ShapeCalcServletContextListener myServletContextListener = new ShapeCalcServletContextListener(rootCtx);
		//servletContext.addListener(myServletContextListener);

		//logger.debug("\n\n\n\nELI: Web AppInitializer created dispatcher servlet passing root ctx..... \n\n\n\n");
		//DispatcherServlet dispatcherServlet = new DispatcherServlet(rootCtx);
		logger.debug("\n\n\n\nELI: Web AppInitializer dispatcherServlet CXF servlet ..... \n\n\n\n");
		CXFServlet dispatcherServlet = new CXFServlet();
		
		//logger.debug("\n\n\n\nELI: Web AppInitializer register dispatcher servlet with servlet context..... \n\n\n\n");
		//ServletRegistration.Dynamic registration = servletContext.addServlet("cxfServlet",cxfServlet);
		logger.debug("\n\n\n\nELI: Web AppInitializer register CXF servlet with servlet context..... \n\n\n\n");
		ServletRegistration.Dynamic registration = servletContext.addServlet("cxfServlet",dispatcherServlet);
	
		registration.setLoadOnStartup(1);
		//registration.addMapping("/");
		registration.addMapping("/services/*");

		logger.debug("\n\n\n\nELI: Web AppInitializer onStartup() done\n\n\n\n");

	}

}
