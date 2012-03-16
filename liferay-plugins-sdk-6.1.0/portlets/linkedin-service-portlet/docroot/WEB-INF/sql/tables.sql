create table Portal_UserLinkedIn (
	id_ LONG not null primary key,
	companyId LONG,
	userId LONG,
	linkedId VARCHAR(75) null
);