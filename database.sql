create schema if not exists `smart_campus`;

use `smart_campus`;

SET NAMES utf8mb4;
SET
    FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sc_admin
-- ----------------------------
DROP TABLE IF EXISTS `sc_admin`;
create table sc_admin
(
    id        char(20)               not null
        primary key,
    username  varchar(30)            not null,
    name      varchar(30)            not null,
    gender    tinyint(1)             not null,
    password  char(32)               not null,
    email     varchar(255)           null,
    telephone varchar(20)            null,
    address   varchar(255)           null,
    avatar    varchar(255)           null,
    create_by varchar(30)            not null,
    create_at bigint      default 0  not null,
    update_by varchar(30) default '' null,
    update_at bigint                 null,
    archived  tinyint     default 0  not null,
    constraint sc_admin_name_uindex
        unique (name)
);



-- ----------------------------
-- Table structure for sc_klasse
-- ----------------------------
DROP TABLE IF EXISTS `sc_klasse`;
create table sc_klasse
(
    id           char(20)               not null
        primary key,
    name         varchar(30)            not null,
    number       int(3)                 not null,
    introduction varchar(200)           null,
    headmaster   char(20)               not null,
    grade_id     char(20)               null,
    create_by    varchar(30)            not null,
    create_at    bigint      default 0  not null,
    update_by    varchar(30) default '' null,
    update_at    bigint                 null,
    archived     tinyint     default 0  not null
);



-- ----------------------------
-- Table structure for sc_grade
-- ----------------------------
DROP TABLE IF EXISTS `sc_grade`;
create table sc_grade
(
    id           char(20)               not null
        primary key,
    name         varchar(30)            not null,
    manager      char(20)               null,
    introduction varchar(200)           null,
    create_by    varchar(30)            not null,
    create_at    bigint      default 0  not null,
    update_by    varchar(30) default '' null,
    update_at    bigint                 null,
    archived     tinyint     default 0  not null
);



-- ----------------------------
-- Table structure for sc_student
-- ----------------------------
DROP TABLE IF EXISTS `sc_student`;
create table sc_student
(
    id             char(20)               not null
        primary key,
    student_number varchar(20)            not null,
    username       varchar(30)            null,
    name           varchar(30)            not null,
    gender         tinyint(1)             not null,
    password       char(32)               not null,
    email          varchar(50)            null,
    telephone      varchar(20)            null,
    address        varchar(255)           null,
    introduction   varchar(200)           null,
    avatar         varchar(255)           null,
    klasse_id      char(20)               null,
    create_by      varchar(30)            not null,
    create_at      bigint      default 0  not null,
    update_by      varchar(30) default '' null,
    update_at      bigint                 null,
    archived       tinyint     default 0  not null
);



-- ----------------------------
-- Table structure for sc_teacher
-- ----------------------------
DROP TABLE IF EXISTS `sc_teacher`;
create table sc_teacher
(
    id             char(20)               not null
        primary key,
    teacher_number varchar(20)            not null,
    username       varchar(30)            not null,
    name           varchar(30)            not null,
    gender         tinyint(1)             not null,
    password       char(32)               not null,
    email          varchar(50)            null,
    telephone      varchar(20)            null,
    address        varchar(255)           null,
    avatar         varchar(255)           null,
    create_by      varchar(30)            not null,
    create_at      bigint      default 0  not null,
    update_by      varchar(30) default '' null,
    update_at      bigint                 null,
    archived       tinyint     default 0  not null
);



SET
    FOREIGN_KEY_CHECKS = 1;
