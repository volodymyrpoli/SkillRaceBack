create schema public;

comment on schema public is 'standard public schema';

alter schema public owner to postgres;

create table if not exists domain
(
	id serial not null
		constraint domain_pk
			primary key,
	name varchar(256) not null,
	rank integer
);

alter table domain owner to postgres;

create table if not exists topic
(
	id serial not null
		constraint topics_pk
			primary key,
	name varchar(256) not null,
	domain_id integer
		constraint topics_domain_id_fk
			references domain
				on update cascade on delete cascade
);

alter table topic owner to postgres;

create table if not exists level
(
	id serial not null
		constraint level_pk
			primary key,
	name varchar(256) not null,
	rank integer,
	color varchar(256)
);

alter table level owner to postgres;

create table if not exists subtopic
(
	id serial not null
		constraint subtopic_pk
			primary key,
	name varchar(256) not null,
	topic_id integer
		constraint subtopic_topic_id_fk
			references topic
				on update cascade on delete cascade,
	level_id integer
		constraint subtopic_level_id_fk
			references level
				on update cascade on delete cascade
);

alter table subtopic owner to postgres;

create table if not exists application_user
(
	id serial not null
		constraint application_user_pk
			primary key,
	username varchar(256) not null,
	email varchar(256),
	password varchar(256) not null
);

alter table application_user owner to postgres;

create unique index if not exists application_user_email_uindex
	on application_user (email);

create unique index if not exists application_user_username_uindex
	on application_user (username);

