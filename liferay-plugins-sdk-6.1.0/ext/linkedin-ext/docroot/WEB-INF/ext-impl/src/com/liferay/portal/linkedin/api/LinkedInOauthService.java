/**
 * Copyright (c) 2012 Rotterdam CS, Inc. All rights reserved.
 */

package com.liferay.portal.linkedin.api;

import java.util.Map;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthConstants;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 * OAuth 1.0a implementation of {@link OAuthService}
 * 
 * @author Rajesh
 */
public class LinkedInOauthService {

	private static final String VERSION = "1.0";

	private OAuthConfig config;
	private DefaultApi10a api;

	public LinkedInOauthService(DefaultApi10a api, OAuthConfig config) {
		this.api = api;
		this.config = config;
	}

	private void addOAuthParams(LinkedInOAuthRequest request, Token token) {
		request.addOAuthParameter(OAuthConstants.TIMESTAMP, api
				.getTimestampService().getTimestampInSeconds());
		request.addOAuthParameter(OAuthConstants.NONCE, api
				.getTimestampService().getNonce());
		request.addOAuthParameter(OAuthConstants.CONSUMER_KEY,
				config.getApiKey());
		request.addOAuthParameter(OAuthConstants.SIGN_METHOD, api
				.getSignatureService().getSignatureMethod());
		request.addOAuthParameter(OAuthConstants.VERSION, getVersion());
		request.addOAuthParameter(OAuthConstants.SIGNATURE,
				getSignature(request, token));
	}

	public Token getAccessToken(String xoauth_oauth2_access_token) {
		
		LinkedInOAuthRequest request = new LinkedInOAuthRequest(api.getAccessTokenVerb(),
				api.getAccessTokenEndpoint());
		request.addOAuthParameter("xoauth_oauth2_access_token",
				xoauth_oauth2_access_token);
		addOAuthParams(request, OAuthConstants.EMPTY_TOKEN);
		appendSignature(request);

		Response response = request.send();
		System.out.println("LinkedInUtil getAccessToken=" + response.getBody());
		
		return api.getAccessTokenExtractor().extract(response.getBody());
	}

	public void signRequest(Token token, LinkedInOAuthRequest request) {
		request.addOAuthParameter(OAuthConstants.TOKEN, token.getToken());
		addOAuthParams(request, token);
		appendSignature(request);
	}

	public String getVersion() {
		return VERSION;
	}

	private String getSignature(LinkedInOAuthRequest request, Token token) {
		String baseString = api.getBaseStringExtractor().extract(request);
		String signature = api.getSignatureService().getSignature(baseString,
				config.getApiSecret(), token.getSecret());
		return signature;
	}

	private void appendSignature(LinkedInOAuthRequest request) {
		switch (config.getSignatureType()) {
		case Header:
			String oauthHeader = api.getHeaderExtractor().extract(request);
			request.addHeader(OAuthConstants.HEADER, oauthHeader);
			break;
		case QueryString:
			for (Map.Entry<String, String> entry : request.getOauthParameters()
					.entrySet()) {
				request.addQuerystringParameter(entry.getKey(),
						entry.getValue());
			}
			break;
		}
	}
}
