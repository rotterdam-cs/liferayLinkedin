/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeysExt;

/**
 * @author V. Koshelenko
 */
public class PropsValuesExt {

    public static final String LINKEDIN_CONNECT_APP_ID = PropsUtil.get(PropsKeysExt.LINKEDIN_CONNECT_APP_ID);

    public static final String LINKEDIN_CONNECT_APP_SECRET = PropsUtil.get(PropsKeysExt.LINKEDIN_CONNECT_APP_SECRET);

    public static final String LINKEDIN_REDIRECT_URL = PropsUtil.get(PropsKeysExt.LINKEDIN_REDIRECT_URL);

    public static final boolean LINKEDIN_CONNECT_AUTH_ENABLED = GetterUtil.getBoolean(PropsUtil.get(PropsKeysExt.LINKEDIN_CONNECT_AUTH_ENABLED));


    public static final String TWITTER_CONNECT_APP_ID = PropsUtil.get(PropsKeysExt.TWITTER_CONNECT_APP_ID);

    public static final String TWITTER_CONNECT_APP_SECRET = PropsUtil.get(PropsKeysExt.TWITTER_CONNECT_APP_SECRET);

    public static final String TWITTER_REDIRECT_URL = PropsUtil.get(PropsKeysExt.TWITTER_REDIRECT_URL);

    public static final boolean TWITTER_CONNECT_AUTH_ENABLED = GetterUtil.getBoolean(PropsUtil.get(PropsKeysExt.TWITTER_CONNECT_AUTH_ENABLED));
}