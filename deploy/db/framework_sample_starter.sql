/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 8.0.12 : Database - framework_sample_starter
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`framework_sample_starter` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `framework_sample_starter`;

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL COMMENT '唯一标识',
  `account` varchar(32) NOT NULL COMMENT '登录账号',
  `pwd` varchar(32) NOT NULL COMMENT '密码（MD5）',
  `truename` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(32) DEFAULT NULL COMMENT '电子邮箱',
  `id_card` varchar(32) DEFAULT NULL COMMENT '身份证号',
  `wechat` varchar(64) DEFAULT NULL COMMENT '微信号',
  `openid` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '微信OpengID',
  `defualt_pwd` varchar(32) DEFAULT NULL COMMENT '默认密码',
  `pwd_changed_dt` datetime DEFAULT NULL COMMENT '密码修改时间',
  `user_status` char(1) DEFAULT '0' COMMENT '用户状态(0-有效、1失效)',
  `sync_status` varchar(20) DEFAULT NULL COMMENT '数据同步状态(提交:submitted;已确认:approved)',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `expired_dt` datetime DEFAULT NULL COMMENT '用户失效时间(到期后无法使用)',
  `is_deleted` char(1) DEFAULT '0' COMMENT '是否逻辑删除标识（0-未删除，1-已删除）',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人ID',
  `created_dt` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人ID',
  `updated_dt` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `customer_id` varchar(32) DEFAULT NULL COMMENT '客户ID',
  `app_id` varchar(32) DEFAULT NULL COMMENT '应用ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基础信息表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
