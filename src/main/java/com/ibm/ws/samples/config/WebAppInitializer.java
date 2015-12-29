/**
 * 
 */
package com.ibm.ws.samples.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.ibm.ws.samples.config.data.DataAccessConfiguration;
import com.ibm.ws.samples.config.filter.CustomConfigurableSiteMeshFilter;
import com.ibm.ws.samples.config.security.SecurityConfiguration;
import com.ibm.ws.samples.config.util.UtilsConfiguration;
import com.ibm.ws.samples.config.web.MvcConfiguration;

/**
 * @author lentiummmx
 *
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#customizeRegistration(javax.servlet.ServletRegistration.Dynamic)
	 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setInitParameter("defaultHtmlEscape", "true");
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses()
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { RootAppConfiguration.class, DataAccessConfiguration.class, SecurityConfiguration.class, UtilsConfiguration.class };
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses()
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { MvcConfiguration.class };
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletFilters()
	 */
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		
		CustomConfigurableSiteMeshFilter configurableSiteMeshFilter = new CustomConfigurableSiteMeshFilter();
		MultipartFilter multipartFilter = new MultipartFilter();
		
		return new Filter[]{characterEncodingFilter, configurableSiteMeshFilter, multipartFilter};
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()
	 */
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletName()
	 */
	@Override
	protected String getServletName() {
		// TODO Auto-generated method stub
		return new String("SpringDispatcherAnnotated");
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.context.AbstractContextLoaderInitializer#registerContextLoaderListener(javax.servlet.ServletContext)
	 */
	@Override
	protected void registerContextLoaderListener(ServletContext servletContext) {
		// TODO Auto-generated method stub
		super.registerContextLoaderListener(servletContext);
	}

}
