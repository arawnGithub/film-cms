/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : db_film

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2018-01-21 22:14:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_film
-- ----------------------------
DROP TABLE IF EXISTS `t_film`;
CREATE TABLE `t_film` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `hot` int(11) DEFAULT NULL,
  `image_path` varchar(300) DEFAULT NULL,
  `name` varchar(200) NOT NULL,
  `publish_date` datetime DEFAULT NULL,
  `title` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_link
-- ----------------------------
DROP TABLE IF EXISTS `t_link`;
CREATE TABLE `t_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `url` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_web_site
-- ----------------------------
DROP TABLE IF EXISTS `t_web_site`;
CREATE TABLE `t_web_site` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `url` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_web_site_info
-- ----------------------------
DROP TABLE IF EXISTS `t_web_site_info`;
CREATE TABLE `t_web_site_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `info` varchar(500) DEFAULT NULL,
  `publish_date` datetime DEFAULT NULL,
  `url` varchar(300) DEFAULT NULL,
  `film_id` int(11) DEFAULT NULL,
  `web_site_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt5lj22ps8vw9elo8nffpe5fn3` (`film_id`),
  KEY `FK5g05mlvck2bcehob8cpbpqge3` (`web_site_id`),
  CONSTRAINT `FK5g05mlvck2bcehob8cpbpqge3` FOREIGN KEY (`web_site_id`) REFERENCES `t_web_site` (`id`),
  CONSTRAINT `FKt5lj22ps8vw9elo8nffpe5fn3` FOREIGN KEY (`film_id`) REFERENCES `t_film` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
