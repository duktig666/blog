/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.29 : Database - blog_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog_system` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `blog_system`;

/*Table structure for table `apply_link` */

DROP TABLE IF EXISTS `apply_link`;

CREATE TABLE `apply_link` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '申请友情链接id',
  `title` varchar(500) NOT NULL COMMENT '网站标题',
  `website` varchar(1024) NOT NULL COMMENT '申请的网站链接地址',
  `apply_reason` varchar(500) DEFAULT NULL COMMENT '申请理由(选填)',
  `email` varchar(300) NOT NULL COMMENT '申请人邮箱',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '申请状态（0为申请中，1为申请成功，2为申请失败）',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（相当于申请时间）',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='申请友情链接表';

/*Table structure for table `blog` */

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '博客id\n',
  `type_id` bigint(20) unsigned NOT NULL COMMENT '博客所属类型id',
  `title` varchar(200) NOT NULL COMMENT '博客标题',
  `summary` varchar(500) DEFAULT NULL COMMENT '博客摘要',
  `content` text NOT NULL COMMENT '博客正文',
  `content_html` text NOT NULL,
  `visit_number` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '浏览量',
  `like_number` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '点赞量',
  `observe_number` int(10) unsigned DEFAULT '0' COMMENT '评论量',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '是否删除（回收站保留字段；0代表的是不删除，1代表的是放入回收站，物理删除直接删除数据）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='博客表';

/*Table structure for table `blog_label` */

DROP TABLE IF EXISTS `blog_label`;

CREATE TABLE `blog_label` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `label` varchar(50) NOT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='博客标签表';

/*Table structure for table `blog_label_middle` */

DROP TABLE IF EXISTS `blog_label_middle`;

CREATE TABLE `blog_label_middle` (
  `blog_id` bigint(20) NOT NULL COMMENT '博客id',
  `label_id` bigint(20) NOT NULL COMMENT '博客所属标签id',
  PRIMARY KEY (`blog_id`,`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客和博客标签中间表（n:n）';

/*Table structure for table `blog_type` */

DROP TABLE IF EXISTS `blog_type`;

CREATE TABLE `blog_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '博客类型id',
  `type` varchar(50) NOT NULL COMMENT '博客类型',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='博客类型表';

/*Table structure for table `blogger` */

DROP TABLE IF EXISTS `blogger`;

CREATE TABLE `blogger` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '博主id',
  `nickname` varchar(50) NOT NULL COMMENT '昵称',
  `age` tinyint(4) unsigned DEFAULT NULL,
  `phone` char(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) NOT NULL COMMENT '博主邮箱',
  `profession` varchar(50) DEFAULT NULL COMMENT '职业',
  `intro` varchar(500) DEFAULT NULL COMMENT '个人简介',
  `picture` varchar(1024) DEFAULT NULL COMMENT '博主头像',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='博主表';

/*Table structure for table `leave_word` */

DROP TABLE IF EXISTS `leave_word`;

CREATE TABLE `leave_word` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '留言id',
  `visitor_id` bigint(20) unsigned NOT NULL COMMENT '游客id',
  `leave_content` varchar(1024) NOT NULL COMMENT '留言内容',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间(留言时间)',
  `reply_content` varchar(1024) DEFAULT NULL COMMENT '博主回复内容',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间（相当于博主回复时间）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='留言信息表';

/*Table structure for table `observe` */

DROP TABLE IF EXISTS `observe`;

CREATE TABLE `observe` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `blog_id` bigint(20) unsigned NOT NULL COMMENT '所属博客id',
  `observer_id` bigint(20) unsigned NOT NULL,
  `observe_content` varchar(1000) NOT NULL COMMENT '评论内容',
  `last_id` bigint(20) DEFAULT NULL COMMENT '上一条评论的id（表示回复关系，null代表一级评论，否则为非一级评论）',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '是否删除',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（相当于评论时间）',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='评论表';

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT '用户id',
  `nickname` varchar(50) NOT NULL COMMENT '昵称',
  `email` varchar(300) DEFAULT NULL COMMENT '邮箱',
  `picture` varchar(1024) DEFAULT NULL COMMENT '头像',
  `identity` tinyint(4) NOT NULL DEFAULT '0' COMMENT '身份',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '回收站',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `account` varchar(50) DEFAULT NULL COMMENT '账户',
  `password` varchar(256) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
