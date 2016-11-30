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

package at.graz.meduni.bibbox.liferay.portlet.service.http;

import aQute.bnd.annotation.ProviderType;

import at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link ApplicationInstanceServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Robert Reihs
 * @see ApplicationInstanceServiceSoap
 * @see HttpPrincipal
 * @see ApplicationInstanceServiceUtil
 * @generated
 */
@ProviderType
public class ApplicationInstanceServiceHttp {
	public static com.liferay.portal.kernel.json.JSONObject getApplicationStoreListAPI(
		HttpPrincipal httpPrincipal) {
		try {
			MethodKey methodKey = new MethodKey(ApplicationInstanceServiceUtil.class,
					"getApplicationStoreListAPI",
					_getApplicationStoreListAPIParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getUpdatedApplicationStoreListAPI(
		HttpPrincipal httpPrincipal) {
		try {
			MethodKey methodKey = new MethodKey(ApplicationInstanceServiceUtil.class,
					"getUpdatedApplicationStoreListAPI",
					_getUpdatedApplicationStoreListAPIParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getApplicationStoreItemAPI(
		HttpPrincipal httpPrincipal, java.lang.String applicationname,
		java.lang.String version) {
		try {
			MethodKey methodKey = new MethodKey(ApplicationInstanceServiceUtil.class,
					"getApplicationStoreItemAPI",
					_getApplicationStoreItemAPIParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					applicationname, version);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject installApplicationAPI(
		HttpPrincipal httpPrincipal, java.lang.String applicationname,
		java.lang.String version, java.lang.String instanceid,
		java.lang.String instancename, java.lang.String data) {
		try {
			MethodKey methodKey = new MethodKey(ApplicationInstanceServiceUtil.class,
					"installApplicationAPI",
					_installApplicationAPIParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					applicationname, version, instanceid, instancename, data);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getInstanceListAPI(
		HttpPrincipal httpPrincipal) {
		try {
			MethodKey methodKey = new MethodKey(ApplicationInstanceServiceUtil.class,
					"getInstanceListAPI", _getInstanceListAPIParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getInstanceInfoAPI(
		HttpPrincipal httpPrincipal, java.lang.String instanceId) {
		try {
			MethodKey methodKey = new MethodKey(ApplicationInstanceServiceUtil.class,
					"getInstanceInfoAPI", _getInstanceInfoAPIParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					instanceId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getInstanceDashboardAPI(
		HttpPrincipal httpPrincipal, java.lang.String instanceId) {
		try {
			MethodKey methodKey = new MethodKey(ApplicationInstanceServiceUtil.class,
					"getInstanceDashboardAPI",
					_getInstanceDashboardAPIParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					instanceId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getInstanceLogdAPI(
		HttpPrincipal httpPrincipal, java.lang.String instanceId,
		java.lang.String logtype) {
		try {
			MethodKey methodKey = new MethodKey(ApplicationInstanceServiceUtil.class,
					"getInstanceLogdAPI", _getInstanceLogdAPIParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					instanceId, logtype);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getInstanceLogdAPI(
		HttpPrincipal httpPrincipal, java.lang.String instanceId,
		java.lang.String logtype, java.lang.String lines) {
		try {
			MethodKey methodKey = new MethodKey(ApplicationInstanceServiceUtil.class,
					"getInstanceLogdAPI", _getInstanceLogdAPIParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					instanceId, logtype, lines);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getInstanceMaintenanceAPI(
		HttpPrincipal httpPrincipal, java.lang.String instanceId) {
		try {
			MethodKey methodKey = new MethodKey(ApplicationInstanceServiceUtil.class,
					"getInstanceMaintenanceAPI",
					_getInstanceMaintenanceAPIParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					instanceId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject updateInstanceInfoAPI(
		HttpPrincipal httpPrincipal, java.lang.String instanceId,
		java.lang.String instancename, java.lang.String instanceshortname,
		java.lang.String description, java.lang.String shortdescription,
		java.lang.String adminnode, java.lang.String maintenance) {
		try {
			MethodKey methodKey = new MethodKey(ApplicationInstanceServiceUtil.class,
					"updateInstanceInfoAPI",
					_updateInstanceInfoAPIParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					instanceId, instancename, instanceshortname, description,
					shortdescription, adminnode, maintenance);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject deleteInstanceStatusAPI(
		HttpPrincipal httpPrincipal, java.lang.String instanceId) {
		try {
			MethodKey methodKey = new MethodKey(ApplicationInstanceServiceUtil.class,
					"deleteInstanceStatusAPI",
					_deleteInstanceStatusAPIParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					instanceId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject setInstanceStatusAPI(
		HttpPrincipal httpPrincipal, java.lang.String instanceId,
		java.lang.String status) {
		try {
			MethodKey methodKey = new MethodKey(ApplicationInstanceServiceUtil.class,
					"setInstanceStatusAPI",
					_setInstanceStatusAPIParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					instanceId, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject toggleInstanceMaintenanceStatusAPI(
		HttpPrincipal httpPrincipal, java.lang.String instanceId) {
		try {
			MethodKey methodKey = new MethodKey(ApplicationInstanceServiceUtil.class,
					"toggleInstanceMaintenanceStatusAPI",
					_toggleInstanceMaintenanceStatusAPIParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					instanceId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getIdMappingInfoAPI(
		HttpPrincipal httpPrincipal, java.lang.String instanceId) {
		try {
			MethodKey methodKey = new MethodKey(ApplicationInstanceServiceUtil.class,
					"getIdMappingInfoAPI", _getIdMappingInfoAPIParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					instanceId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void getTestAPI(HttpPrincipal httpPrincipal,
		java.lang.String applicationname, java.lang.String version,
		java.lang.String instanceid, java.lang.String instancename,
		java.lang.String data) {
		try {
			MethodKey methodKey = new MethodKey(ApplicationInstanceServiceUtil.class,
					"getTestAPI", _getTestAPIParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					applicationname, version, instanceid, instancename, data);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ApplicationInstanceServiceHttp.class);
	private static final Class<?>[] _getApplicationStoreListAPIParameterTypes0 = new Class[] {
			
		};
	private static final Class<?>[] _getUpdatedApplicationStoreListAPIParameterTypes1 =
		new Class[] {  };
	private static final Class<?>[] _getApplicationStoreItemAPIParameterTypes2 = new Class[] {
			java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _installApplicationAPIParameterTypes3 = new Class[] {
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class
		};
	private static final Class<?>[] _getInstanceListAPIParameterTypes4 = new Class[] {
			
		};
	private static final Class<?>[] _getInstanceInfoAPIParameterTypes5 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _getInstanceDashboardAPIParameterTypes6 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _getInstanceLogdAPIParameterTypes7 = new Class[] {
			java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _getInstanceLogdAPIParameterTypes8 = new Class[] {
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class
		};
	private static final Class<?>[] _getInstanceMaintenanceAPIParameterTypes9 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _updateInstanceInfoAPIParameterTypes10 = new Class[] {
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class
		};
	private static final Class<?>[] _deleteInstanceStatusAPIParameterTypes11 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _setInstanceStatusAPIParameterTypes12 = new Class[] {
			java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _toggleInstanceMaintenanceStatusAPIParameterTypes13 =
		new Class[] { java.lang.String.class };
	private static final Class<?>[] _getIdMappingInfoAPIParameterTypes14 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _getTestAPIParameterTypes15 = new Class[] {
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class
		};
}