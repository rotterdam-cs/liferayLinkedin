/**
 * Copyright (c) 2012 Rotterdam CS, Inc. All rights reserved.
 */

package com.liferay.portal.linkedin.api;

import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Verb;

/**
 * The representation of an OAuth HttpRequest.
 * 
 * Adds OAuth-related functionality to the {@link Request}
 * 
 * @author Rajesh
 */
public class LinkedInOAuthRequest extends OAuthRequest {

	private static final String OAUTH_PREFIX = "oauth_";

	private static final String OAUTH_2_PREFIX = "xoauth_";

	public LinkedInOAuthRequest(Verb verb, String url) {
		super(verb, url);
	}

	public void addOAuthParameter(String key, String value) {
		getOauthParameters().put(checkKey(key), value);
	}

	private String checkKey(String key) {
		if (key.startsWith(OAUTH_PREFIX) || key.equals(OAuthConstants.SCOPE)
				|| key.startsWith(OAUTH_2_PREFIX)) {
			return key;
		} else {
			throw new IllegalArgumentException(String.format(
					"OAuth parameters must either be '%s' or start with '%s'",
					OAuthConstants.SCOPE, OAUTH_PREFIX));
		}
	}

}
