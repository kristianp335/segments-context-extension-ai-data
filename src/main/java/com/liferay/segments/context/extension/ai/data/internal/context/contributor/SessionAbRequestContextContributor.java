/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.segments.context.extension.ai.data.internal.context.contributor;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.segments.context.Context;
import com.liferay.segments.context.contributor.RequestContextContributor;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.impl.provider.entity.StringProvider;

import javax.servlet.http.HttpServletRequest;


import org.osgi.service.component.annotations.Component;

/**
 * @author Kris
 */
@Component(
	immediate = true,
	property = {
		"request.context.contributor.key=" + SessionAbRequestContextContributor.KEY,
		"request.context.contributor.type=boolean"
	},
	service = RequestContextContributor.class
)
public class SessionAbRequestContextContributor
	implements RequestContextContributor {

	public static final String KEY = "sessionab";

	@Override
	public void contribute(
		Context context, HttpServletRequest httpServletRequest) {

		
		boolean actualStateOfAb = true;
		
		String sessionId = httpServletRequest.getSession().getId();
		System.out.println("The session id = " + sessionId);		
		Character lastChar = sessionId.charAt(sessionId.length() -1);
		int charNumericValue = Character.getNumericValue(lastChar);	
		//checks if the interger is divisible by 2, if it can be then it is even
		if (charNumericValue % 2 == 0)
		{
			actualStateOfAb = true;
		}
			else
			{
				actualStateOfAb = false;
			}
		
		System.out.println("The AB session rule is running and the state of ab being even is " + actualStateOfAb);	
		context.put(KEY, actualStateOfAb);
	    					
		
	}
}