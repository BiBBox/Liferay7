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

package at.graz.meduni.bibbox.liferay.portlet.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.json.JSONFactoryUtil;

import at.graz.meduni.bibbox.helper.ActivitiesProtocol;
import at.graz.meduni.bibbox.helper.BibboxConfigReader;
import at.graz.meduni.bibbox.helper.FormatExceptionMessage;
import at.graz.meduni.bibbox.helper.UpdateGitRepositoriesBackgroundTask;
import at.graz.meduni.bibbox.helper.UpdateGitRepository;
import at.graz.meduni.bibbox.liferay.backgroundtasks.BibboxBackgroundTaskExecutorNames;
import at.graz.meduni.bibbox.liferay.backgroundtasks.ControleApplication;
import at.graz.meduni.bibbox.liferay.backgroundtasks.DeleteApplication;
import at.graz.meduni.bibbox.liferay.backgroundtasks.InstallApplicationBG;
import at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstance;
import at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstanceContainer;
import at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstancePort;
import at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstanceStatus;
import at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceContainerLocalServiceUtil;
import at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceLocalServiceUtil;
import at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstancePortLocalServiceUtil;
import at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceStatusLocalServiceUtil;
import at.graz.meduni.bibbox.liferay.portlet.service.base.ApplicationInstanceServiceBaseImpl;

/**
 * The implementation of the application instance remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Robert Reihs
 * @see ApplicationInstanceServiceBaseImpl
 * @see at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceServiceUtil
 */
@ProviderType
public class ApplicationInstanceServiceImpl
	extends ApplicationInstanceServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceServiceUtil} to access the application instance remote service.
	 */
	
	/**
	 * Error Format for date
	 */
	private String log_portlet_ = "BIBBOXDocker";
	private String log_classname_ = "at.graz.meduni.bibbox.liferay.portlet.service.impl.ApplicationInstanceServiceImpl";
	
	private static SimpleDateFormat format_date = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat format_time = new SimpleDateFormat("HH:mm:ss.SSS");
	public static String newline_ = System.getProperty("line.separator");
	
	@JSONWebService("/version")
	public JSONObject getVersionAPI() {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		returnobject.put("BIBBOX Version", "v1.0");
		return returnobject;
	}
	
	@JSONWebService("/get-application-store-list")
	public JSONObject getApplicationStoreListAPI() {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		returnobject.put("status", "local");
		returnobject.put("groups", getApplicationStoreList());
		returnobject.put("user", getUserObject());
		return returnobject;
	}
	
	@JSONWebService("/get-updated-application-store-list")
	public JSONObject getUpdatedApplicationStoreListAPI() {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		UpdateGitRepository updategitrepository = new UpdateGitRepository();
		String gitstatus = updategitrepository.updateLocalGitRepository(BibboxConfigReader.getApplicationStorePWD() + "/application-store");
		returnobject.put("status", gitstatus);
		returnobject.put("groups", getApplicationStoreList());
		returnobject.put("user", getUserObject());
		UpdateGitRepositoriesBackgroundTask updategitrepositoriesbackgroundtask = new UpdateGitRepositoriesBackgroundTask("Update Application Store");
		updategitrepositoriesbackgroundtask.start();
		updateMachinePerformanceMetricData();
		return returnobject;
	}
	
	@JSONWebService("/get-application-store-item")
	public JSONObject getApplicationStoreItemAPI(String applicationname, String version) {
		JSONObject returnobject = getApplicationStoreItem(applicationname, version);
		returnobject.put("user", getUserObject());
		return returnobject;
	}
	
	@JSONWebService(value = "/install-application", method = "POST")
	public JSONObject installApplicationAPI(String applicationname, String version, String instanceid, String instancename, String data) {
		if(!checkPermission(instanceid, "edit")) {
			JSONObject returnobject = JSONFactoryUtil.createJSONObject();
			returnobject.put("status", "error");
			returnobject.put("error", "permission denied");
			returnobject.put("user", getUserObject());
			return returnobject;
		}
		JSONObject returnobject = installApplication(applicationname, version, instanceid, instancename, data);
		returnobject.put("user", getUserObject());
		return returnobject;
	}
	
	@JSONWebService(value = "/get-instance-list")
	public JSONObject getInstanceListAPI() {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		returnobject.put("instances", getInstanceList());
		returnobject.put("user", getUserObject());
		return returnobject;
	}
	
	@JSONWebService(value = "/get-instance-info")
	public JSONObject getInstanceInfoAPI(String instanceId) {
		if(!checkPermission(instanceId, "view")) {
			JSONObject returnobject = JSONFactoryUtil.createJSONObject();
			returnobject.put("status", "error");
			returnobject.put("error", "permission denied");
			returnobject.put("user", getUserObject());
			return returnobject;
		}
		JSONObject returnobject = getInstanceDashboard(instanceId);
		returnobject.put("user", getUserObject());
		return returnobject;
	}
	
	@JSONWebService(value = "/get-instance-dashboard")
	public JSONObject getInstanceDashboardAPI(String instanceId) {
		if(!checkPermission(instanceId, "view")) {
			JSONObject returnobject = JSONFactoryUtil.createJSONObject();
			returnobject.put("status", "error");
			returnobject.put("error", "permission denied");
			returnobject.put("user", getUserObject());
			return returnobject;
		}
		JSONObject returnobject = getInstanceDashboard(instanceId);
		returnobject.put("user", getUserObject());
		return returnobject;
	}
	
	@JSONWebService(value = "/get-instance-log")
	public JSONObject getInstanceLogdAPI(String instanceId) {
		if(!checkPermission(instanceId, "view")) {
			JSONObject returnobject = JSONFactoryUtil.createJSONObject();
			returnobject.put("status", "error");
			returnobject.put("error", "permission denied");
			returnobject.put("user", getUserObject());
			return returnobject;
		}
		JSONObject returnobject = getInstanceLogd(instanceId, "200");
		returnobject.put("user", getUserObject());
		return returnobject;
	}
	
	@JSONWebService(value = "/get-instance-log")
	public JSONObject getInstanceLogdAPI(String instanceId, String lines) {
		if(!checkPermission(instanceId, "view")) {
			JSONObject returnobject = JSONFactoryUtil.createJSONObject();
			returnobject.put("status", "error");
			returnobject.put("error", "permission denied");
			returnobject.put("user", getUserObject());
			return returnobject;
		}
		JSONObject returnobject = getInstanceLogd(instanceId, lines);
		returnobject.put("user", getUserObject());
		return returnobject;
	}
	
	@JSONWebService(value = "/get-instance-maintenance")
	public JSONObject getInstanceMaintenanceAPI(String instanceId) {
		JSONObject returnobject = getInstanceMaintenance(instanceId);
		returnobject.put("user", getUserObject());
		return returnobject;
	}
	
	@JSONWebService(value = "/update-instance-info", method = "POST")
	public JSONObject updateInstanceInfoAPI(String instanceId, String instancename, String instanceshortname, String description, String shortdescription, String adminnode, String maintenance) {
		if(!checkPermission(instanceId, "edit")) {
			JSONObject returnobject = JSONFactoryUtil.createJSONObject();
			returnobject.put("status", "error");
			returnobject.put("error", "permission denied");
			returnobject.put("user", getUserObject());
			return returnobject;
		}
		JSONObject returnobject = updateInstanceInfo(instanceId, instancename, instanceshortname, description, shortdescription, adminnode, maintenance);
		returnobject.put("user", getUserObject());
		return returnobject;
	}
	
	@JSONWebService(value = "/delete-instance-status")
	public JSONObject deleteInstanceStatusAPI(String instanceId) {
		if(!checkPermission(instanceId, "edit")) {
			JSONObject returnobject = JSONFactoryUtil.createJSONObject();
			returnobject.put("status", "error");
			returnobject.put("error", "permission denied");
			returnobject.put("user", getUserObject());
			return returnobject;
		}
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		deleteInstance(instanceId);
		returnobject.put("user", getUserObject());
		return returnobject;
	}
	
	@JSONWebService(value = "/set-instance-status")
	public JSONObject setInstanceStatusAPI(String instanceId, String status) {
		if(!checkPermission(instanceId, "edit")) {
			JSONObject returnobject = JSONFactoryUtil.createJSONObject();
			returnobject.put("status", "error");
			returnobject.put("error", "permission denied");
			returnobject.put("user", getUserObject());
			return returnobject;
		}
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		if(status.equalsIgnoreCase("start")) {
			returnobject = startInstance(instanceId);
		} else if(status.equalsIgnoreCase("stop")) {
			returnobject = stopInstance(instanceId);
		} else if(status.equalsIgnoreCase("restart")) {
			returnobject = restartInstance(instanceId);
		} else {
			returnobject.put("status", "error");
			returnobject.put("error", "Status not know!");
		}
		returnobject.put("user", getUserObject());
		return returnobject;
	}
	
	@JSONWebService(value = "/toggle-instance-maintenance-status")
	public JSONObject toggleInstanceMaintenanceStatusAPI(String instanceId) {
		if(!checkPermission(instanceId, "edit")) {
			JSONObject returnobject = JSONFactoryUtil.createJSONObject();
			returnobject.put("status", "error");
			returnobject.put("error", "permission denied");
			returnobject.put("user", getUserObject());
			return returnobject;
		}
		JSONObject returnobject = toggleInstanceMaintenanceStatus(instanceId);
		returnobject.put("user", getUserObject());
		return returnobject;
	}
	
	@JSONWebService(value = "/get-id-mapping-info")
	public JSONObject getIdMappingInfoAPI(String instanceId) {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		ApplicationInstance applicationinstance = ApplicationInstanceLocalServiceUtil.getApplicationInstance(instanceId);
		if(applicationinstance == null) {
			returnobject.put("status", "error");
			returnobject.put("error", "InstanceId dose not exist!");
		} else {
			try {
				String jsonstring = BibboxConfigReader.readApplicationsStoreJsonFile(BibboxConfigReader.getApplicationFolder(applicationinstance.getApplication(), applicationinstance.getVersion()) + "/id-mapping-info.json");
				return JSONFactoryUtil.createJSONObject(jsonstring);
			} catch (JSONException e) {
				returnobject.put("status", "error");
				returnobject.put("error", "Parsing JSON file: " + e.getMessage());
				System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "getIdMappingInfoAPI(String instanceId)", "Error parsing id-mapping-info.json for instance: " + instanceId));
				e.printStackTrace();
			}
		}
		return returnobject;
	}
	
	@JSONWebService(value = "/test")
	public void getTestAPI(String string) {
		try {
			JSONFactoryUtil.createJSONObject(string);
		} catch (JSONException e) {
			System.out.println("ERROR creating Json Object");
			e.printStackTrace();
		}
		try {
			String string2 = "{MYSQL_PASSWORD=, INSTITUTE_NAME=BIBBOX Demo Biobank, MYSQL_USER=openspecimen, TOMCAT_MANAGER_USER=admin, TOMCAT_MANAGER_PASSWORD=}";
			JSONFactoryUtil.createJSONObject(string2);
		} catch (JSONException e) {
			System.out.println("ERROR creating Json Object 2");
			e.printStackTrace();
		}
	}
	
	@JSONWebService(value = "/set-portlet-configuration")
	public JSONObject setPortletConfiguration(long companyId, long plid, String portletId, String preferences) {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		System.out.println("-------------------------");
		System.out.println("PortletConfiguration");
		System.out.println("companyId:" + companyId + " plid:" + plid + " portletId:" + portletId + " preferences:" + preferences);
		returnobject.put("companyId", companyId);
		returnobject.put("plid", plid);
		returnobject.put("portletId", portletId);
		returnobject.put("preferences", preferences);
		Layout layout;
		try {
			layout = LayoutLocalServiceUtil.getLayout(plid);
			System.out.println("ModelClassName" + layout.getModelClassName());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<PortletPreferences> portletpreferences = PortletPreferencesLocalServiceUtil.getPortletPreferences(plid, portletId);
		System.out.println(portletpreferences.size());
		if(portletpreferences.size() == 0) {
			long ownerId = 0;
			Portlet portlet = PortletLocalServiceUtil.getPortletById(portletId);
			PortletPreferencesLocalServiceUtil.addPortletPreferences(companyId, ownerId, PortletKeys.PREFS_OWNER_TYPE_LAYOUT, plid, portletId, portlet, preferences);
		} else {
			for(PortletPreferences portletpreference : portletpreferences) {
				portletpreference.setPreferences(preferences);
				PortletPreferencesLocalServiceUtil.updatePortletPreferences(portletpreference);
				
			}
		}
		System.out.println("PortletConfiguration ... end");
		return returnobject;
	}
	
	@JSONWebService(value = "/get-meta-data-information-app")
	public JSONObject getMetaDataInformationAppAPI(String instanceId) {
		//UpdateGitRepository updategitrepository = new UpdateGitRepository();
		//String gitstatus = updategitrepository.updateLocalGitRepository(BibboxConfigReader.getApplicationStorePWD() + "/application-store");
		
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		ApplicationInstance applicationinstance = ApplicationInstanceLocalServiceUtil.getApplicationInstance(instanceId);
		if(applicationinstance == null) {
			returnobject.put("status", "error");
			returnobject.put("error", "InstanceId dose not exist!");
		} else {
			return applicationinstance.getMetadataInformationAPP();
		}
		return returnobject;
	}
	
	@JSONWebService(value = "/get-meta-data-information-machine")
	public JSONObject getMetaDataInformationMachineAPI() {
		//UpdateGitRepository updategitrepository = new UpdateGitRepository();
		//String gitstatus = updategitrepository.updateLocalGitRepository(BibboxConfigReader.getApplicationStorePWD() + "/application-store");
		
		return getMetaDataInformationMachine();
	}
	
	@JSONWebService(value = "/get-meta-data-information-domain")
	public JSONObject getMetaDataInformationDomainAPI() {
		//UpdateGitRepository updategitrepository = new UpdateGitRepository();
		//String gitstatus = updategitrepository.updateLocalGitRepository(BibboxConfigReader.getApplicationStorePWD() + "/application-store");
		
		return getMetaDataInformationDomain();
	}
	
	@JSONWebService(value = "/update-metadata-info-app", method = "POST")
	public void updateMetadataInfoAppAPI(String instanceId, String data) {
		updateMetadataInfoApp(instanceId, data);
	}
	
	@JSONWebService(value = "/update-metadata-info-machine", method = "POST")
	public void updateMetadataInfoMachineAPI(String data) {
		updateMetadataInfoMachine(data);
	}
	
	@JSONWebService(value = "/update-metadata-info-domain", method = "POST")
	public void updateMetadataInfoDomainAPI(String data) {
		updateMetadataInfoDomain(data);
	}
	
	@AccessControlled(guestAccessEnabled=true)
	@JSONWebService(value = "/get-open-application-list")
	public JSONObject getOpenApplicationListAPI() {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		returnobject.put("instances", getInstanceList());
		returnobject.put("user", getUserObject());
		return returnobject;
	}
	
	@AccessControlled(guestAccessEnabled=true)
	@JSONWebService(value = "/get-open-application-info")
	public JSONObject getOpenApplicationInfo(String instanceId) {
		JSONObject returnobject = getInstanceDashboard(instanceId);
		return returnobject;
	}
	
	private JSONArray getApplicationStoreList() {
		try {
			String applicationstore = BibboxConfigReader.getApplicationStorePWD();
			String jsonstring = BibboxConfigReader.readApplicationsStoreJsonFile(applicationstore + "/application-store/" + BibboxConfigReader.getBibboxKit() + ".json");
			return JSONFactoryUtil.createJSONArray(jsonstring);
		} catch (Exception e) {
			System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "getApplicationStoreList()", "Error reading application store list."));
			e.printStackTrace();
		}
		return JSONFactoryUtil.createJSONArray();
	}
	
	private JSONObject getApplicationStoreItem(String applicationname, String version) {
		JSONObject retrunobject = null;
		if(version.equalsIgnoreCase("development")) {
			retrunobject = getApplicationStoreItemDeveloperVersion(applicationname);
		} else {
			retrunobject = getApplicationStoreItemVersion(applicationname, version);
		}
		retrunobject.put("install", getApplicationStoreItemInstallParameters(applicationname, version));
		retrunobject.put("usedinstanceids", ApplicationInstanceLocalServiceUtil.getUsedInstanceIds());
		return retrunobject;
	}
	
	private JSONObject getApplicationStoreItemDeveloperVersion(String applicationname) {
		try {
			UpdateGitRepository updategitrepository = new UpdateGitRepository();
			String jsonstring = "";
			String applicationfolder = BibboxConfigReader.getApplicationFolder(applicationname, "development");
			if(updategitrepository.checkIfLocalRepositoryExists(applicationfolder)) {
				updategitrepository.updateLocalGitRepository(applicationfolder);
				jsonstring = BibboxConfigReader.readApplicationsStoreJsonFile(applicationfolder + "/appinfo.json");
			} else {
				updategitrepository.cloneRepositoryToFolder(applicationfolder, applicationname);
				jsonstring = BibboxConfigReader.readApplicationsStoreJsonFile(applicationfolder + "/appinfo.json");
			}
			return JSONFactoryUtil.createJSONObject(jsonstring);
		} catch (JSONException e) {
			System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "getApplicationStoreItemDeveloperVersion(String applicationname)", "Error reading application store jason file. Applicationname:" + applicationname));
			e.printStackTrace();
		}
		return JSONFactoryUtil.createJSONObject();
	}
	
	private JSONObject getApplicationStoreItemVersion(String applicationname, String version) {
		try {
			UpdateGitRepository updategitrepository = new UpdateGitRepository();
			String jsonstring = "";
			String applicationfolder = BibboxConfigReader.getApplicationFolder(applicationname, version);
			String applicationfolderdevelopment = BibboxConfigReader.getApplicationFolder(applicationname, "development");
			if(updategitrepository.checkIfLocalRepositoryExists(applicationfolder)) {
				jsonstring = BibboxConfigReader.readApplicationsStoreJsonFile(applicationfolder + "/appinfo.json");
			} else {
				if(!updategitrepository.checkIfLocalRepositoryExists(applicationfolderdevelopment)) {
					updategitrepository.cloneRepositoryToFolder(applicationfolderdevelopment, applicationname);
				} else {
					updategitrepository.updateLocalGitRepository(applicationfolderdevelopment);
				}
				updategitrepository.createTagFodler(applicationname, version);
				jsonstring = BibboxConfigReader.readApplicationsStoreJsonFile(applicationfolder + "/appinfo.json");
			}
			return JSONFactoryUtil.createJSONObject(jsonstring);
		} catch (JSONException e) {
			System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "getApplicationStoreItemVersion(String applicationname, String version)", "Error reading application store json file. Applicationname:" + applicationname + " Version: " + version));
			e.printStackTrace();
		}
		return JSONFactoryUtil.createJSONObject();
	}
	
	private JSONArray getApplicationStoreItemInstallParameters(String applicationname, String version) {
		try {
			String jsonstring = "";
			String applicationfolder = BibboxConfigReader.getApplicationFolder(applicationname, version);
			jsonstring = BibboxConfigReader.readApplicationsStoreJsonFile(applicationfolder + "/environment-parameters.json");
			return JSONFactoryUtil.createJSONArray(jsonstring);
		} catch (JSONException e) {
			System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "getApplicationStoreItemInstallParameters(String applicationname, String version)", "Error reading application store environment-parameters.json file. Applicationname:" + applicationname + " Version: " + version));
			e.printStackTrace();
		}
		return JSONFactoryUtil.createJSONArray();
	}
	
	private JSONObject installApplication(String applicationname, String version, String instanceid, String instancename, String data) {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		
		if(ApplicationInstanceLocalServiceUtil.checkInstanceNameAvailable(instanceid)) {
			returnobject.put("status", "error");
			returnobject.put("error", "InstanceId alredy exists!");
			return returnobject;
		}
		
		ApplicationInstanceLocalServiceUtil.registerApplication(applicationname, version, instanceid, instancename);
		
		long userId = 0;
		long groupId = 0;
		try {
			User user = this.getGuestOrUser();
			Company company = CompanyLocalServiceUtil.getCompany(user.getCompanyId());
			groupId = company.getGroupId();
			userId = user.getUserId();
		} catch (Exception e) {
			System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "installApplication(String applicationname, String version, String instanceid, String instancename, String data)", "Error getting user from api call."));
			e.printStackTrace();
		}
		
		Map<String, Serializable> taskContextMap = new HashMap<>();

		taskContextMap.put("instanceId", instanceid);
		taskContextMap.put("data", data);
		
		try {
			BackgroundTaskManagerUtil.addBackgroundTask(userId, groupId, BibboxBackgroundTaskExecutorNames.BIBBOX_INSTANCE_INSTALLER_BACKGROUND_TASK_EXECUTOR, new String[]{"BIBBOXDocker-portlet"}, InstallApplicationBG.class, taskContextMap, new ServiceContext());
		} catch (PortalException e) {
			System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "installApplication(String applicationname, String version, String instanceid, String instancename, String data)", "Error starting Background Task. For instance: " + instanceid));
			e.printStackTrace();
		}
		
		returnobject.put("status", "installing");
		returnobject.put("instanceid", instanceid);
		
		return returnobject;
	}
	
	private JSONArray getInstanceList() {
		JSONArray returnobject = JSONFactoryUtil.createJSONArray();
		List<ApplicationInstance> applicationinstances = ApplicationInstanceLocalServiceUtil.getActiveApplicationInstances();
		for(ApplicationInstance applicationinstance : applicationinstances) {
			JSONObject instanceobject = applicationinstance.getInstanceJSONObject();
			instanceobject.put("description", applicationinstance.getShortdescription());
			instanceobject.put("application", applicationinstance.getApplication());
			instanceobject.put("tags", applicationinstance.getApplicationTags());
			returnobject.put(instanceobject);
		}
		return returnobject;
	}
	
	private JSONObject getInstanceInfo(String instanceId) {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		ApplicationInstance applicationinstance = ApplicationInstanceLocalServiceUtil.getApplicationInstance(instanceId);
		if(applicationinstance == null) {
			returnobject.put("status", "error");
			returnobject.put("error", "InstanceId dose not exist!");
		} else {
			returnobject = applicationinstance.getInstanceJSONObject();
			returnobject.put("application", getApplicationStoreItem(applicationinstance.getApplication(), applicationinstance.getVersion()));
			return returnobject;
		}
		applicationinstance = null;
		return returnobject;
	}
	
	private JSONObject getInstanceDashboard(String instanceId) {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		ApplicationInstance applicationinstance = ApplicationInstanceLocalServiceUtil.getApplicationInstance(instanceId);
		if(applicationinstance == null) {
			returnobject.put("status", "error");
			returnobject.put("error", "InstanceId dose not exist!");
		} else {
			returnobject = applicationinstance.getInstanceJSONObject();
			returnobject.put("maintenance", applicationinstance.getMaintenance());
			returnobject.put("adminnode", applicationinstance.getAdminnode());
			returnobject.put("application", getApplicationStoreItem(applicationinstance.getApplication(), applicationinstance.getVersion()));
			return returnobject;
		}
		applicationinstance = null;
		return returnobject;
	}
	
	private JSONObject getInstanceMaintenance(String instanceId) {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		ApplicationInstance applicationinstance = ApplicationInstanceLocalServiceUtil.getApplicationInstance(instanceId);
		if(applicationinstance == null) {
			returnobject.put("status", "error");
			returnobject.put("error", "InstanceId dose not exist!");
		} else {
			returnobject = applicationinstance.getInstanceJSONObject();
			returnobject.put("maintenance", applicationinstance.getMaintenance());
			returnobject.put("ismaintenance", applicationinstance.getIsmaintenance());
			return returnobject;
		}
		applicationinstance = null;
		return returnobject;
	}
	
	private JSONObject updateInstanceInfo(String instanceId, String instancename, String instanceshortname, String description, String shortdescription, String adminnode, String maintenance) {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		ApplicationInstance applicationinstance = ApplicationInstanceLocalServiceUtil.getApplicationInstance(instanceId);
		if(applicationinstance == null) {
			returnobject.put("status", "error");
			returnobject.put("error", "InstanceId dose not exist!");
		} else {
			applicationinstance.setName(instancename);
			applicationinstance.setShortName(instanceshortname);
			applicationinstance.setDescription(description);
			applicationinstance.setShortdescription(shortdescription);
			applicationinstance.setMaintenance(maintenance);
			applicationinstance.setAdminnode(adminnode);
			ApplicationInstanceLocalServiceUtil.updateApplicationInstance(applicationinstance);
			returnobject = applicationinstance.getInstanceJSONObject();
			returnobject.put("maintenance", applicationinstance.getMaintenance());
			returnobject.put("ismaintenance", applicationinstance.getIsmaintenance());
			return returnobject;
		}
		return returnobject;
	}
	
	private JSONObject getInstanceLogd(String instanceId, String lines) {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		ApplicationInstance applicationinstance = ApplicationInstanceLocalServiceUtil.getApplicationInstance(instanceId);
		if(applicationinstance == null) {
			returnobject.put("status", "error");
			returnobject.put("error", "InstanceId dose not exist!");
		} else {
			returnobject = applicationinstance.getInstanceJSONObject();
			returnobject.put("maintenance", applicationinstance.getMaintenance());
			returnobject.put("application", applicationinstance.getApplication());
			returnobject.put("longname", applicationinstance.getName());
			returnobject.put("logs", getComposeLog(applicationinstance, lines));
			returnobject.put("log", "");
			return returnobject;
		}
		return returnobject;
	}
	
	private JSONArray getComposeLog(ApplicationInstance applicationinstance, String lines) {
		return applicationinstance.getComposeLog(lines);
	}
	
	private JSONObject deleteInstance(String instanceId) {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		ApplicationInstance applicationinstance = ApplicationInstanceLocalServiceUtil.getApplicationInstance(instanceId);
		if(applicationinstance == null) {
			returnobject.put("status", "error");
			returnobject.put("error", "InstanceId dose not exist!");
		} else {
			applicationinstance.setDeleted(true);
			applicationinstance.setStatus("deleting");
			ApplicationInstanceLocalServiceUtil.updateApplicationInstance(applicationinstance);
			
			long userId = 0;
			long groupId = 0;
			
			try {
				User user = this.getGuestOrUser();
				Company company = CompanyLocalServiceUtil.getCompany(user.getCompanyId());
				groupId = company.getGroupId();
				userId = user.getUserId();
			} catch (Exception e) {
				System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "getUserObject()", "Error getting user from api call"));
				e.printStackTrace();
			}
			
			Map<String, Serializable> taskContextMap = new HashMap<>();
			taskContextMap.put("instanceId", instanceId);
			
			try {
				BackgroundTaskManagerUtil.addBackgroundTask(userId, groupId, BibboxBackgroundTaskExecutorNames.BIBBOX_INSTANCE_DELETE_BACKGROUND_TASK_EXECUTOR, new String[]{"BIBBOXDocker-portlet"}, DeleteApplication.class, taskContextMap, new ServiceContext());
			} catch (PortalException e) {
				System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "getUserObject()", "Error starting delete Task."));
				e.printStackTrace();
			}
			
			returnobject.put("status", "installing");
			returnobject.put("instanceid", instanceId);
		}
		return returnobject;
	}
	
	private JSONObject startInstance(String instanceId) {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		ApplicationInstance applicationinstance = ApplicationInstanceLocalServiceUtil.getApplicationInstance(instanceId);
		if(applicationinstance == null) {
			returnobject.put("status", "error");
			returnobject.put("error", "InstanceId dose not exist!");
			String activityId = addMessageActivity("Starting Instance " + instanceId, "STARTAPP", "RUNNING", "UNKNOWN");
			ActivitiesProtocol.addActivityLogEntry(activityId, "ERROR", "InstanceId dose not exist!");
			finishActivity(activityId, "FINISHED", "ERROR");
		} else {
			ApplicationInstanceStatus applicationinstancestatus = ApplicationInstanceStatusLocalServiceUtil.getApplicationInstanceStatusByInstanceId(applicationinstance.getApplicationInstanceId());
			applicationinstancestatus.setStatus("starting");
			applicationinstancestatus = ApplicationInstanceStatusLocalServiceUtil.updateApplicationInstanceStatus(applicationinstancestatus);
			
			long userId = 0;
			long groupId = 0;
			try {
				User user = this.getGuestOrUser();
				Company company = CompanyLocalServiceUtil.getCompany(user.getCompanyId());
				groupId = company.getGroupId();
				userId = user.getUserId();
			} catch (Exception e) {
				System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "startInstance(String instanceId)", "Error getting user from api call."));
				e.printStackTrace();
			}
			
			Map<String, Serializable> taskContextMap = new HashMap<>();

			taskContextMap.put("instanceId", instanceId);
			taskContextMap.put("command", "start");
			
			try {
				BackgroundTaskManagerUtil.addBackgroundTask(userId, groupId, BibboxBackgroundTaskExecutorNames.BIBBOX_INSTANCE_CONTROLE_BACKGROUND_TASK_EXECUTOR, new String[]{"BIBBOXDocker-portlet"}, ControleApplication.class, taskContextMap, new ServiceContext());
			} catch (PortalException e) {
				System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "startInstance(String instanceId)", "Error starting Background Task. For instance: " + instanceId));
				e.printStackTrace();
			}
		}
		applicationinstance = null;
		returnobject.put("status", "starting");
		return returnobject;
	}
	
	private JSONObject stopInstance(String instanceId) {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		ApplicationInstance applicationinstance = ApplicationInstanceLocalServiceUtil.getApplicationInstance(instanceId);
		if(applicationinstance == null) {
			returnobject.put("status", "error");
			returnobject.put("error", "InstanceId dose not exist!");
			String activityId = addMessageActivity("Stopping Instance " + instanceId, "STOPAPP", "RUNNING", "UNKNOWN");
			ActivitiesProtocol.addActivityLogEntry(activityId, "ERROR", "InstanceId dose not exist!");
			finishActivity(activityId, "FINISHED", "ERROR");
		} else {
			ApplicationInstanceStatus applicationinstancestatus = ApplicationInstanceStatusLocalServiceUtil.getApplicationInstanceStatusByInstanceId(applicationinstance.getApplicationInstanceId());
			applicationinstancestatus.setStatus("stopping");
			applicationinstancestatus = ApplicationInstanceStatusLocalServiceUtil.updateApplicationInstanceStatus(applicationinstancestatus);
			
			long userId = 0;
			long groupId = 0;
			try {
				User user = this.getGuestOrUser();
				Company company = CompanyLocalServiceUtil.getCompany(user.getCompanyId());
				groupId = company.getGroupId();
				userId = user.getUserId();
			} catch (Exception e) {
				System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "stopInstance(String instanceId)", "Error getting user from api call."));
				e.printStackTrace();
			}
			
			Map<String, Serializable> taskContextMap = new HashMap<>();

			taskContextMap.put("instanceId", instanceId);
			taskContextMap.put("command", "stop");
			
			try {
				BackgroundTaskManagerUtil.addBackgroundTask(userId, groupId, BibboxBackgroundTaskExecutorNames.BIBBOX_INSTANCE_CONTROLE_BACKGROUND_TASK_EXECUTOR, new String[]{"BIBBOXDocker-portlet"}, ControleApplication.class, taskContextMap, new ServiceContext());
			} catch (PortalException e) {
				System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "stopInstance(String instanceId)", "Error starting Background Task. For instance: " + instanceId));
				e.printStackTrace();
			}
		}
		returnobject.put("status", "stopping");
		return returnobject;
	}
	
	private JSONObject restartInstance(String instanceId) {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		ApplicationInstance applicationinstance = ApplicationInstanceLocalServiceUtil.getApplicationInstance(instanceId);
		if(applicationinstance == null) {
			returnobject.put("status", "error");
			returnobject.put("error", "InstanceId dose not exist!");
			String activityId = addMessageActivity("Restart Instance " + instanceId, "RESTARTAPP", "RUNNING", "UNKNOWN");
			ActivitiesProtocol.addActivityLogEntry(activityId, "ERROR", "InstanceId dose not exist!");
			finishActivity(activityId, "FINISHED", "ERROR");
		} else {
			ApplicationInstanceStatus applicationinstancestatus = ApplicationInstanceStatusLocalServiceUtil.getApplicationInstanceStatusByInstanceId(applicationinstance.getApplicationInstanceId());
			applicationinstancestatus.setStatus("restarting");
			applicationinstancestatus = ApplicationInstanceStatusLocalServiceUtil.updateApplicationInstanceStatus(applicationinstancestatus);
			
			long userId = 0;
			long groupId = 0;
			try {
				User user = this.getGuestOrUser();
				Company company = CompanyLocalServiceUtil.getCompany(user.getCompanyId());
				groupId = company.getGroupId();
				userId = user.getUserId();
			} catch (Exception e) {
				System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "restartInstance(String instanceId)", "Error getting user from api call."));
				e.printStackTrace();
			}
			
			Map<String, Serializable> taskContextMap = new HashMap<>();

			taskContextMap.put("instanceId", instanceId);
			taskContextMap.put("command", "restart");
			
			try {
				BackgroundTaskManagerUtil.addBackgroundTask(userId, groupId, BibboxBackgroundTaskExecutorNames.BIBBOX_INSTANCE_CONTROLE_BACKGROUND_TASK_EXECUTOR, new String[]{"BIBBOXDocker-portlet"}, ControleApplication.class, taskContextMap, new ServiceContext());
			} catch (PortalException e) {
				System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "restartInstance(String instanceId)", "Error starting Background Task. For instance: " + instanceId));
				e.printStackTrace();
			}
		}
		returnobject.put("status", "restarting");
		return returnobject;
	}
	
	private JSONObject toggleInstanceMaintenanceStatus(String instanceId) {
		String activityId = addMessageActivity("Switch maintenance mode " + instanceId, "MAINTENANCEAPP", "RUNNING", "UNKNOWN");
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		ApplicationInstance applicationinstance = ApplicationInstanceLocalServiceUtil.getApplicationInstance(instanceId);
		if(applicationinstance == null) {
			returnobject.put("status", "error");
			returnobject.put("error", "InstanceId dose not exist!");
			ActivitiesProtocol.addActivityLogEntry(activityId, "ERROR", "InstanceId dose not exist!");
			finishActivity(activityId, "FINISHED", "ERROR");
		} else {
			applicationinstance.setIsmaintenance(!applicationinstance.getIsmaintenance());
			applicationinstance = ApplicationInstanceLocalServiceUtil.updateApplicationInstance(applicationinstance);
			returnobject = getInstanceDashboard(instanceId);
			if(applicationinstance.getIsmaintenance()) {
				ActivitiesProtocol.addActivityLogEntry(activityId, "INFO", "Instance " + applicationinstance.getInstanceId() + " set to maintenance mode");
				finishActivity(activityId, "FINISHED", "SUCCESS");
			} else {
				ActivitiesProtocol.addActivityLogEntry(activityId, "INFO", "Instance " + applicationinstance.getInstanceId() + " set online again");
				finishActivity(activityId, "FINISHED", "SUCCESS");
			}
		}
		return returnobject;
	}
	
	private JSONObject getUserObject() {
		JSONObject returnobject = JSONFactoryUtil.createJSONObject();
		try {
			User user = this.getGuestOrUser();
			returnobject.put("username", user.getFullName());
			returnobject.put("role", getFrontendRole());
		} catch (Exception e) {
			System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "getUserObject()", "Error getting user from api call"));
			e.printStackTrace();
		}
		return returnobject;
	}
	
	private String getFrontendRole() {
		try {
			User user = this.getGuestOrUser();
			List<Role> roles = user.getRoles();
			BibboxConfigReader.getAdminRoles();
			ArrayList<String> adminroles = new ArrayList<String>();
			String adminrolesstring = BibboxConfigReader.getAdminRoles();
			for(String rolename : adminrolesstring.split(";")) {
				adminroles.add(rolename);
			}
			ArrayList<String> vmadminroles = new ArrayList<String>();
			String vmadminrolesstring = BibboxConfigReader.getVMAdminRoles(); 
			for(String rolename : vmadminrolesstring.split(";")) {
				vmadminroles.add(rolename);
			}
			boolean admin = false;
			for(Role role : roles) {
				if(adminroles.contains(role.getName())) {
					admin = true;
				}
				if(vmadminroles.contains(role.getName())) {
					return "vmadmin";
				}
			}
			if(admin) {
				return "admin";
			}
		} catch (Exception e) {
			System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "checkPermission()", "Error getting user permission."));
			e.printStackTrace();
		}
		return "user";
	}
	
	// Umbauen
	private boolean checkPermission(String instanceid, String actions) {
		try {
			User user = this.getGuestOrUser();
			List<Role> roles = user.getRoles();
			BibboxConfigReader.getAdminRoles();
			ArrayList<String> adminroles = new ArrayList<String>();
			String adminrolesstring = BibboxConfigReader.getAdminRoles();
			for(String rolename : adminrolesstring.split(";")) {
				adminroles.add(rolename);
			}
			ArrayList<String> vmadminroles = new ArrayList<String>();
			String vmadminrolesstring = BibboxConfigReader.getVMAdminRoles(); 
			for(String rolename : vmadminrolesstring.split(";")) {
				vmadminroles.add(rolename);
			}
			boolean admin = false;
			for(Role role : roles) {
				if(adminroles.contains(role.getName())) {
					admin = true;
				}
				if(vmadminroles.contains(role.getName())) {
					return true;
				}
			}
			if(admin) {
				String lockedids = BibboxConfigReader.getBibboxLockedAppsInstanceIds() + ";";
				if(lockedids.contains(instanceid + ";") && actions.equals("edit")) {
					return false;
				}
				return true;
			}
		} catch (Exception e) {
			System.err.println(FormatExceptionMessage.formatExceptionMessage("error", log_portlet_, log_classname_, "checkPermission()", "Error getting user permission."));
			e.printStackTrace();
		}
		return false;
	}
	
	private String addMessageActivity(String name, String type, String state, String result) {
		JSONObject activity = JSONFactoryUtil.createJSONObject();
		Date curDate = new Date();
		activity.put("name", name);
		activity.put("type", type);
		activity.put("state", state);
		activity.put("result", result);
		activity.put("start_time", format_date.format(curDate) + "T" + format_time.format(curDate) + "Z");
		if(state.equals("FINISHED")) {
			activity.put("finished_time", format_date.format(curDate) + "T" + format_time.format(curDate) + "Z");
		}
		JSONObject activityresult = ActivitiesProtocol.createActivity(activity.toJSONString());
		return activityresult.getString("activitId");
	}
	
	private void finishActivity(String activityId, String state, String result) {
		JSONObject activity = JSONFactoryUtil.createJSONObject();
		activity.put("state", state);
		activity.put("result", result);
		Date curDate = new Date();
		activity.put("finished_time", format_date.format(curDate) + "T" + format_time.format(curDate) + "Z");
		ActivitiesProtocol.updateActivity(activityId, activity.toJSONString());
	}
	
	private void updateMetadataInfoApp(String instanceId, String data) {
		try{
			
			boolean success = (new File("/opt/bibbox/sys-bibbox-sync/data/sync/" + BibboxConfigReader.getBibboxSyncIndexMachine() + "/general")).mkdirs();
			PrintWriter out = new PrintWriter(new FileWriter("/opt/bibbox/sys-bibbox-sync/data/sync/" + BibboxConfigReader.getBibboxSyncIndexMachine() + "/general/" + instanceId  + "." + BibboxConfigReader.getBaseURL() + ".json", false));
			
			JSONObject app_data = JSONFactoryUtil.createJSONObject();
			try {
				app_data = JSONFactoryUtil.createJSONObject(data);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println(app_data.toJSONString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		updateMachinePerformanceMetricData();
	}
	
	private void updateMetadataInfoMachine(String data) {
		try{
			boolean success = (new File("/opt/bibbox/sys-bibbox-sync/data/sync/" + BibboxConfigReader.getBibboxSyncIndexMachine() + "/general-machine")).mkdirs();
			PrintWriter out = new PrintWriter(new FileWriter("/opt/bibbox/sys-bibbox-sync/data/sync/" + BibboxConfigReader.getBibboxSyncIndexMachine() + "/general-machine/" + BibboxConfigReader.getBaseURL() + ".json", false));
			
			JSONObject app_data = JSONFactoryUtil.createJSONObject();
			try {
				app_data = JSONFactoryUtil.createJSONObject(data);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("1 Medatadat: " + app_data.toJSONString());
			out.println(app_data.toJSONString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		updateMachinePerformanceMetricData();
	}
	
	private void updateMetadataInfoDomain(String data) {
		try{
			boolean success = (new File("/opt/bibbox/sys-bibbox-sync/data/sync-biobank/" + BibboxConfigReader.getBibboxSyncIndexDomain() + "/general-domain")).mkdirs();
			PrintWriter out = new PrintWriter(new FileWriter("/opt/bibbox/sys-bibbox-sync/data/sync-biobank/" + BibboxConfigReader.getBibboxSyncIndexDomain() + "/general-domain/" + BibboxConfigReader.getBaseURL() + ".json", false));
			
			JSONObject app_data = JSONFactoryUtil.createJSONObject();
			try {
				app_data = JSONFactoryUtil.createJSONObject(data);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println(app_data.toJSONString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		updateMachinePerformanceMetricData();
	}
	
	private JSONObject getMetaDataInformationMachine() {
		JSONObject jsonobject = JSONFactoryUtil.createJSONObject();
		JSONObject forms = JSONFactoryUtil.createJSONObject();
		
		boolean set_general = false;
		JSONObject general = JSONFactoryUtil.createJSONObject();
		JSONObject general_schema = getSchemaJson("/opt/bibbox/metadata/general-machine/schema.json");
		if(general_schema != null) {
			general.put("schema.json", general_schema);
			set_general = true;
		}
		JSONObject general_ui_schema = getSchemaJson("/opt/bibbox/metadata/general-machine/ui-schema.json");
		if(general_ui_schema != null) {
			general.put("ui_schema.json", general_ui_schema);
			set_general = true;
		}
		File test = new File("/opt/bibbox/sys-bibbox-sync/data/sync/" + BibboxConfigReader.getBibboxSyncIndexMachine() + "/general-machine/" + BibboxConfigReader.getBaseURL() + ".json");
		if(!test.exists()) {
			String jsonstring = BibboxConfigReader.readApplicationsStoreJsonFile("/opt/bibbox/metadata/general-machine/form_data.json");
			jsonstring = replaceParameters(jsonstring);
			try{
				boolean success = (new File("/opt/bibbox/sys-bibbox-sync/data/sync/" + BibboxConfigReader.getBibboxSyncIndexMachine() + "/general-machine")).mkdirs();
				PrintWriter out = new PrintWriter(new FileWriter("/opt/bibbox/sys-bibbox-sync/data/sync/" + BibboxConfigReader.getBibboxSyncIndexMachine() + "/general-machine/" + BibboxConfigReader.getBaseURL() + ".json", false));
				
				out.println(jsonstring);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		JSONObject general_form_data = getSchemaJson("/opt/bibbox/sys-bibbox-sync/data/sync/" + BibboxConfigReader.getBibboxSyncIndexMachine() + "/general-machine/" + BibboxConfigReader.getBaseURL() + ".json");
		if(general_form_data != null) {
			general.put("form_data.json", general_form_data);
			set_general = true;
		}
		if(set_general) {
			forms.put("general", general);
		}
		
		jsonobject.put("form", forms);
		jsonobject.put("machine_id", BibboxConfigReader.getBaseURL());
		return jsonobject;
	}
	
	private String replaceParameters(String jsonstring) {
		jsonstring = jsonstring.replaceAll("\"§§MACHINE_ID\"", "\"" + BibboxConfigReader.getBaseURL() + "\"");
		jsonstring = jsonstring.replaceAll("\"§§CPUS\"", BibboxConfigReader.getMachineCPUs());
		jsonstring = jsonstring.replaceAll("\"§§MEMORY\"", BibboxConfigReader.getTotalMemory());
		jsonstring = jsonstring.replaceAll("\"§§STORAGE\"", BibboxConfigReader.getMachineMemoryUsed());
		return jsonstring;
	}
	
	private JSONObject getMetaDataInformationDomain() {
		JSONObject jsonobject = JSONFactoryUtil.createJSONObject();
		JSONObject forms = JSONFactoryUtil.createJSONObject();
		
		boolean set_general = false;
		JSONObject general = JSONFactoryUtil.createJSONObject();
		JSONObject general_schema = getSchemaJson("/opt/bibbox/metadata/general-domain/schema.json");
		if(general_schema != null) {
			general.put("schema.json", general_schema);
			set_general = true;
		}
		JSONObject general_ui_schema = getSchemaJson("/opt/bibbox/metadata/general-domain/ui-schema.json");
		if(general_ui_schema != null) {
			general.put("ui_schema.json", general_ui_schema);
			set_general = true;
		}
		File test = new File("/opt/bibbox/sys-bibbox-sync/data/sync-biobank/" + BibboxConfigReader.getBibboxSyncIndexDomain() + "/general-domain/" + BibboxConfigReader.getBaseURL() + ".json");
		if(!test.exists()) {
			String jsonstring = BibboxConfigReader.readApplicationsStoreJsonFile("/opt/bibbox/metadata/general-domain/form_data.json");
			jsonstring = replaceParameters(jsonstring);
			try{
				boolean success = (new File("/opt/bibbox/sys-bibbox-sync/data/sync-biobank/" + BibboxConfigReader.getBibboxSyncIndexDomain() + "/general-domain")).mkdirs();
				PrintWriter out = new PrintWriter(new FileWriter("/opt/bibbox/sys-bibbox-sync/data/sync-biobank/" + BibboxConfigReader.getBibboxSyncIndexDomain() + "/general-domain/" + BibboxConfigReader.getBaseURL() + ".json", false));
				
				out.println(jsonstring);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		JSONObject general_form_data = getSchemaJson("/opt/bibbox/sys-bibbox-sync/data/sync-biobank/" + BibboxConfigReader.getBibboxSyncIndexDomain() + "/general-domain/" + BibboxConfigReader.getBaseURL() + ".json");
		if(general_form_data != null) {
			general.put("form_data.json", general_form_data);
			set_general = true;
		}
		if(set_general) {
			forms.put("general", general);
		}
		
		jsonobject.put("form", forms);
		jsonobject.put("machine_id", BibboxConfigReader.getBaseURL());
		return jsonobject;
	}
	
	private JSONObject getSchemaJson(String path) {
		JSONObject general_schema = JSONFactoryUtil.createJSONObject();
		File f = new File(path);
		if(!f.exists()) { 
		    return null;
		}
		String jsonstring = BibboxConfigReader.readApplicationsStoreJsonFile(path);
		try {
			general_schema = JSONFactoryUtil.createJSONObject(jsonstring);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return general_schema;
	}
	
	private void updateMachinePerformanceMetricData() {
		File test = new File("/opt/bibbox/sys-bibbox-sync/data/sync/" + BibboxConfigReader.getBibboxSyncIndexMachine() + "/general-machine/" + BibboxConfigReader.getBaseURL() + ".json");
		if(!test.exists()) {
			String jsonstring = BibboxConfigReader.readApplicationsStoreJsonFile("/opt/bibbox/metadata/general-machine/form_data.json");
			jsonstring = replaceParameters(jsonstring);
			try{
				boolean success = (new File("/opt/bibbox/sys-bibbox-sync/data/sync/" + BibboxConfigReader.getBibboxSyncIndexMachine() + "/general-machine")).mkdirs();
				PrintWriter out = new PrintWriter(new FileWriter("/opt/bibbox/sys-bibbox-sync/data/sync/" + BibboxConfigReader.getBibboxSyncIndexMachine() + "/general-machine/" + BibboxConfigReader.getBaseURL() + ".json", false));
					
				out.println(jsonstring);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String jsonstring = BibboxConfigReader.readApplicationsStoreJsonFile("/opt/bibbox/sys-bibbox-sync/data/sync/" + BibboxConfigReader.getBibboxSyncIndexMachine() + "/general-machine/" + BibboxConfigReader.getBaseURL() + ".json");
			jsonstring = updateParameters(jsonstring);
			try{
				boolean success = (new File("/opt/bibbox/sys-bibbox-sync/data/sync/" + BibboxConfigReader.getBibboxSyncIndexMachine() + "/general-machine")).mkdirs();
				PrintWriter out = new PrintWriter(new FileWriter("/opt/bibbox/sys-bibbox-sync/data/sync/" + BibboxConfigReader.getBibboxSyncIndexMachine() + "/general-machine/" + BibboxConfigReader.getBaseURL() + ".json", false));
					
				out.println(jsonstring);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String updateParameters(String jsonstring) {
		jsonstring = jsonstring.replaceAll("\"cpus\":\"?\\d*\"?,", "\"cpus\":" + BibboxConfigReader.getMachineCPUs()+ ",");
		jsonstring = jsonstring.replaceAll("\"memory\":\"?\\d*\"?,",  "\"memory\":" + BibboxConfigReader.getTotalMemory()+ ",");
		jsonstring = jsonstring.replaceAll("\"storage\":\"?\\d*\"?",  "\"storage\":" + BibboxConfigReader.getMachineMemoryUsed()+ "");
		return jsonstring;
	}
}