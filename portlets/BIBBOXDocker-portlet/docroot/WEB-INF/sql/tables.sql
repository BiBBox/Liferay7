create table bibboxdocker_ApplicationInstance (
	applicationInstanceId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	instanceId TEXT null,
	name TEXT null,
	shortName TEXT null,
	baseurl VARCHAR(75) null,
	folderName TEXT null,
	application TEXT null,
	version TEXT null,
	status BOOLEAN,
	deleted BOOLEAN,
	shortdescription TEXT null,
	description TEXT null,
	adminnode VARCHAR(75) null,
	maintenance TEXT null,
	ismaintenance BOOLEAN,
	installlog TEXT null
);

create table bibboxdocker_ApplicationInstanceContainer (
	applicationInstanceContainerId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	containerName VARCHAR(75) null,
	needrunning BOOLEAN,
	applicationInstanceId LONG
);

create table bibboxdocker_ApplicationInstancePort (
	applicationInstancePortId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	port LONG,
	primary_ BOOLEAN,
	applicationInstanceId LONG
);