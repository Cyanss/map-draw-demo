/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.7.18-log : Database - frame
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`frame` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `frame`;

/*Table structure for table `change_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_name` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名称',
  `user_sex` tinyint(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户性别',
  `user_age` int(10) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户年龄',
  `user_phone` varchar(13) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户手机号',
  `user_email` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户邮箱',
  `user_password` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户密码',
  `user_auth` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户权限',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  key `idx_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `graph_info`;

CREATE TABLE `graph_info` (
  `graph_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图形编号',
  `user_id` int(11) NOT NULL COLLATE utf8mb4_unicode_ci COMMENT '用户编号',
  `graph_name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图形名称',
  `graph_type` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图形类型',
  `graph_color` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图形颜色',
  `graph_content` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图形内容描述',
  `graph_data` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图形数据',
#   `graph_data` blob NOT NULL COMMENT '图形数据',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`graph_id`),
  key `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

