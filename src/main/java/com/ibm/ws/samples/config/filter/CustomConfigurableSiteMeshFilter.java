/**
 * 
 */
package com.ibm.ws.samples.config.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * @author lentiummmx
 *
 */
public class CustomConfigurableSiteMeshFilter extends ConfigurableSiteMeshFilter {

	/* (non-Javadoc)
	 * @see org.sitemesh.config.ConfigurableSiteMeshFilter#applyCustomConfiguration(org.sitemesh.builder.SiteMeshFilterBuilder)
	 */
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/login", "/WEB-INF/decorators/guestLayout.jsp")
				.addDecoratorPath("/register", "/WEB-INF/decorators/guestLayout.jsp")
				.addDecoratorPath("/", "/WEB-INF/decorators/layout.jsp");
	}

}
