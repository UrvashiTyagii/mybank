# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

-- init script create procs
-- Inital script to create stored procedures etc for mysql platform
DROP PROCEDURE IF EXISTS usp_ebean_drop_foreign_keys;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_foreign_keys TABLE, COLUMN
-- deletes all constraints and foreign keys referring to TABLE.COLUMN
--
CREATE PROCEDURE usp_ebean_drop_foreign_keys(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE c_fk_name CHAR(255);
  DECLARE curs CURSOR FOR SELECT CONSTRAINT_NAME from information_schema.KEY_COLUMN_USAGE
    WHERE TABLE_SCHEMA = DATABASE() and TABLE_NAME = p_table_name and COLUMN_NAME = p_column_name
      AND REFERENCED_TABLE_NAME IS NOT NULL;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  OPEN curs;

  read_loop: LOOP
    FETCH curs INTO c_fk_name;
    IF done THEN
      LEAVE read_loop;
    END IF;
    SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP FOREIGN KEY ', c_fk_name);
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
  END LOOP;

  CLOSE curs;
END
$$

DROP PROCEDURE IF EXISTS usp_ebean_drop_column;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_column TABLE, COLUMN
-- deletes the column and ensures that all indices and constraints are dropped first
--
CREATE PROCEDURE usp_ebean_drop_column(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
  CALL usp_ebean_drop_foreign_keys(p_table_name, p_column_name);
  SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP COLUMN ', p_column_name);
  PREPARE stmt FROM @sql;
  EXECUTE stmt;
END
$$
create table user (
  id                            integer auto_increment not null,
  fname                         varchar(255),
  lname                         varchar(255),
  dob                           datetime(6),
  email                         varchar(255),
  adhar_no                      varchar(255),
  status_id                     integer,
  role_id                       integer,
  created_on                    datetime(6),
  phone_no                      varchar(255),
  constraint pk_user primary key (id)
);

create table bank (
  id                            integer auto_increment not null,
  name                          varchar(255),
  ifsc                          integer not null,
  account_no                    varchar(255),
  amount_to_transact            varchar(255),
  tt_id                         integer,
  description                   varchar(255),
  user_id                       integer,
  constraint pk_bank primary key (id)
);

create table password (
  id                            integer auto_increment not null,
  user_id                       integer,
  password                      varchar(255),
  confirmpassword               varchar(255),
  created_on                    datetime(6),
  constraint pk_password primary key (id)
);

create table role_type (
  id                            integer auto_increment not null,
  name                          varchar(255),
  constraint pk_role_type primary key (id)
);

create table status (
  id                            integer auto_increment not null,
  name                          varchar(255),
  constraint pk_status primary key (id)
);

create table transaction (
  id                            integer auto_increment not null,
  user_id                       integer,
  amount                        varchar(255),
  transaction_type_id           integer,
  created_on                    datetime(6),
  description                   varchar(255),
  action_type_id                integer,
  bank_details_id               integer,
  constraint pk_transaction primary key (id)
);

create table transaction_action (
  id                            integer auto_increment not null,
  name                          varchar(255),
  constraint pk_transaction_action primary key (id)
);

create table transaction_type (
  id                            integer auto_increment not null,
  name                          varchar(255),
  constraint pk_transaction_type primary key (id)
);

create index ix_user_status_id on user (status_id);
alter table user add constraint fk_user_status_id foreign key (status_id) references status (id) on delete restrict on update restrict;

create index ix_user_role_id on user (role_id);
alter table user add constraint fk_user_role_id foreign key (role_id) references user (id) on delete restrict on update restrict;

create index ix_bank_tt_id on bank (tt_id);
alter table bank add constraint fk_bank_tt_id foreign key (tt_id) references transaction_type (id) on delete restrict on update restrict;

create index ix_bank_user_id on bank (user_id);
alter table bank add constraint fk_bank_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;

create index ix_password_user_id on password (user_id);
alter table password add constraint fk_password_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;

create index ix_transaction_user_id on transaction (user_id);
alter table transaction add constraint fk_transaction_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;

create index ix_transaction_transaction_type_id on transaction (transaction_type_id);
alter table transaction add constraint fk_transaction_transaction_type_id foreign key (transaction_type_id) references transaction_type (id) on delete restrict on update restrict;

create index ix_transaction_action_type_id on transaction (action_type_id);
alter table transaction add constraint fk_transaction_action_type_id foreign key (action_type_id) references transaction_action (id) on delete restrict on update restrict;

create index ix_transaction_bank_details_id on transaction (bank_details_id);
alter table transaction add constraint fk_transaction_bank_details_id foreign key (bank_details_id) references bank (id) on delete restrict on update restrict;


# --- !Downs

alter table user drop foreign key fk_user_status_id;
drop index ix_user_status_id on user;

alter table user drop foreign key fk_user_role_id;
drop index ix_user_role_id on user;

alter table bank drop foreign key fk_bank_tt_id;
drop index ix_bank_tt_id on bank;

alter table bank drop foreign key fk_bank_user_id;
drop index ix_bank_user_id on bank;

alter table password drop foreign key fk_password_user_id;
drop index ix_password_user_id on password;

alter table transaction drop foreign key fk_transaction_user_id;
drop index ix_transaction_user_id on transaction;

alter table transaction drop foreign key fk_transaction_transaction_type_id;
drop index ix_transaction_transaction_type_id on transaction;

alter table transaction drop foreign key fk_transaction_action_type_id;
drop index ix_transaction_action_type_id on transaction;

alter table transaction drop foreign key fk_transaction_bank_details_id;
drop index ix_transaction_bank_details_id on transaction;

drop table if exists user;

drop table if exists bank;

drop table if exists password;

drop table if exists role_type;

drop table if exists status;

drop table if exists transaction;

drop table if exists transaction_action;

drop table if exists transaction_type;

