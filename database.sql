create schema if not exists `smart_campus`;

use `smart_campus`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sc_admin
-- ----------------------------
DROP TABLE IF EXISTS `sc_admin`;
CREATE TABLE `sc_admin`
(
    `id`        char(20)                                                NOT NULL,
    `name`      varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci  not null,
    `gender`    tinyint(1)                                              not null,
    `password`  char(32) CHARACTER SET utf8 COLLATE utf8_general_ci     not null,
    `email`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    `address`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `avatar`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `create_by` varchar(30)                                             not null,
    `create_at` bigint                                                  not null default 0,
    `update_by` varchar(30)                                                      default '',
    `update_at` bigint,
    `archived`  tinyint                                                 not null default 0,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB

  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sc_klasse
-- ----------------------------
DROP TABLE IF EXISTS `sc_klasse`;
CREATE TABLE `sc_klasse`
(
    `id`           char(20)                                                NOT NULL,
    `name`         varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  not null,
    `number`       int(3)                                                  not null,
    `introduction` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `headmaster`   varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    `email`        varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    `telephone`    varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    `grade_name`   varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    `create_by`    varchar(30)                                             not null,
    `create_at`    bigint                                                  not null default 0,
    `update_by`    varchar(30)                                                      default '',
    `update_at`    bigint,
    `archived`     tinyint                                                 not null default 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sc_grade
-- ----------------------------
DROP TABLE IF EXISTS `sc_grade`;
CREATE TABLE `sc_grade`
(
    `id`           char(20)                                                NOT NULL,
    `name`         varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
    `manager`      varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    `email`        varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    `telephone`    varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    `introduction` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `create_by`    varchar(30)                                             not null,
    `create_at`    bigint                                                  not null default 0,
    `update_by`    varchar(30)                                                      default '',
    `update_at`    bigint,
    `archived`     tinyint                                                 not null default 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sc_student
-- ----------------------------
DROP TABLE IF EXISTS `sc_student`;
CREATE TABLE `sc_student`
(
    `id`             char(20)                                                NOT NULL,
    `student_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  not null,
    `name`           varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  not null,
    `gender`         tinyint(1)                                              not null,
    `password`       char(32) CHARACTER SET utf8 COLLATE utf8_general_ci     not null,
    `email`          varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    `telephone`      varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    `address`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `introduction`   varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `avatar`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `klasse_name`    varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    `create_by`      varchar(30)                                             not null,
    `create_at`      bigint                                                  not null default 0,
    `update_by`      varchar(30)                                                      default '',
    `update_at`      bigint,
    `archived`       tinyint                                                 not null default 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sc_teacher
-- ----------------------------
DROP TABLE IF EXISTS `sc_teacher`;
CREATE TABLE `sc_teacher`
(
    `id`             char(20)                                                NOT NULL,
    `teacher_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  not null,
    `name`           varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  not null,
    `gender`         tinyint(1)                                              not null,
    `password`       char(32) CHARACTER SET utf8 COLLATE utf8_general_ci     not null,
    `email`          varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    `telephone`      varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    `address`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `avatar`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `klasse_name`    varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL,
    `create_by`      varchar(30)                                             not null,
    `create_at`      bigint                                                  not null default 0,
    `update_by`      varchar(30)                                                      default '',
    `update_at`      bigint,
    `archived`       tinyint                                                 not null default 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
