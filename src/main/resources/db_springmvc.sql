/*
Navicat MySQL Data Transfer

Source Server         : localhost_mysql_zwb_10.173.230.176
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : db_springmvc

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2016-05-09 17:15:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `trueName` varchar(20) DEFAULT NULL,
  `roleName` varchar(20) DEFAULT NULL,
  `deptId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_user` (`deptId`),
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`deptId`) REFERENCES `t_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'java1234', '123456', '锋哥', '管理员', '1');
INSERT INTO `t_user` VALUES ('2', 'jack', '123', '杰克', '使用者', '2');
INSERT INTO `t_user` VALUES ('3', 'marry', '123', '玛丽', '维修者', '3');
INSERT INTO `t_user` VALUES ('4', 'json2', '12322', '姐僧2', '维修者', '3');
