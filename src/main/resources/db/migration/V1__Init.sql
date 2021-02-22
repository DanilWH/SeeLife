create table audios (note_id bigint not null, audios varchar(255)) engine=InnoDB;
create table day (id bigint not null, local_date date, notes_number integer, title varchar(255), user_id bigint, primary key (id)) engine=InnoDB;
create table documents (note_id bigint not null, documents varchar(255)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;

insert into hibernate_sequence values ( 1 );

create table images (note_id bigint not null, images varchar(255)) engine=InnoDB;
create table note (id bigint not null, local_time time, text TEXT, day_id bigint, primary key (id)) engine=InnoDB;
create table user (id bigint not null, active bit not null, password varchar(255), username varchar(255), primary key (id)) engine=InnoDB;
create table user_role (user_id bigint not null, roles varchar(255)) engine=InnoDB;
create table videos (note_id bigint not null, videos varchar(255)) engine=InnoDB;

alter table audios add constraint audios_note_fk foreign key (note_id) references note (id) on delete cascade;
alter table day add constraint day_user_fk foreign key (user_id) references user (id) on delete cascade;
alter table documents add constraint documents_note_fk foreign key (note_id) references note (id) on delete cascade;
alter table images add constraint images_note_fk foreign key (note_id) references note (id) on delete cascade;
alter table note add constraint note_day_fk foreign key (day_id) references day (id) on delete cascade;
alter table user_role add constraint user_role_user_fk foreign key (user_id) references user (id);
alter table videos add constraint videos_note_fk foreign key (note_id) references note (id) on delete cascade;