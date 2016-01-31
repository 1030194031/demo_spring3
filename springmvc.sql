/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 60011
Source Host           : localhost:3306
Source Database       : springmvc

Target Server Type    : MYSQL
Target Server Version : 60011
File Encoding         : 65001

Date: 2015-11-03 09:33:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `fileupload`
-- ----------------------------
DROP TABLE IF EXISTS `fileupload`;
CREATE TABLE `fileupload` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `create_time` datetime DEFAULT NULL COMMENT '上传时间',
  `url` varchar(255) DEFAULT NULL COMMENT '路径',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='上传文件列表';

-- ----------------------------
-- Records of fileupload
-- ----------------------------
INSERT INTO `fileupload` VALUES ('18', '工作周报', '2015-05-15 18:15:09', '/static/uploadfile/file/ar1YoINTLE.xls');
INSERT INTO `fileupload` VALUES ('19', '理臣修改', '2015-05-15 18:15:16', '/static/uploadfile/file/LCpMgicdl4.txt');
INSERT INTO `fileupload` VALUES ('20', '', '2015-05-15 18:21:24', '/static/uploadfile/file/ZcW9Ldeyvc.editorconfig');
INSERT INTO `fileupload` VALUES ('21', 't014069ad99d857979c', '2015-05-16 15:20:32', '/static/uploadfile/file/Wc5jjSHvSo.jpg');
INSERT INTO `fileupload` VALUES ('22', '2', '2015-05-19 16:21:04', '/static/uploadfile/file/kMP2pLcvTT.jpg');
INSERT INTO `fileupload` VALUES ('23', '2.', '2015-05-19 16:23:56', '/static/uploadfile/file/GG147SX06x.jpg');
INSERT INTO `fileupload` VALUES ('24', '1', '2015-05-19 16:24:14', '/static/uploadfile/file/toGbiI4AfH.jpg');
INSERT INTO `fileupload` VALUES ('25', '20111207150829-916088691', '2015-05-19 16:24:23', '/static/uploadfile/file/F7WSgPfDBQ.jpg');

-- ----------------------------
-- Table structure for `subject`
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '专业名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='专业';

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('14', '123', '0');
INSERT INTO `subject` VALUES ('21', '新建专业', '14');
INSERT INTO `subject` VALUES ('22', '新建专业', '0');
INSERT INTO `subject` VALUES ('23', '新建专业', '0');
INSERT INTO `subject` VALUES ('24', '新建专业', '0');
INSERT INTO `subject` VALUES ('25', '新建专业', '0');
INSERT INTO `subject` VALUES ('26', '新建专业', '0');

-- ----------------------------
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text COMMENT '内容',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='模板文件';

-- ----------------------------
-- Records of template
-- ----------------------------
INSERT INTO `template` VALUES ('1', '		         				         				         				         				         				         				         				         				         				         				         				         				         				         				         				         				         		<!DOCTYPE html>\r\n<html>\r\n<head>\r\n	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n	<title></title>\r\n	<meta name=\"author\" content=\"\"/>\r\n	<meta name=\"keywords\" content=\"\" />\r\n	<meta name=\"description\" content=\"\" />\r\n	<link rel=\"stylesheet\" type=\"text/css\" href=\"${staticServer}/static/edu/css/lc_official_line/style.css\">\r\n	<script type=\"text/javascript\" src=\"${staticServer}/static/common/jquery-1.11.1.min.js\"></script>\r\n	<script type=\"text/javascript\" src=\"${staticServer}/static/common/webutils.js\"></script>\r\n	<script type=\"text/javascript\" src=\"${staticServer}/static/line/js/line-course.js\"></script>\r\n	<!--[if lt IE 9]><script src=\"${staticServer}/static/common/html5.js\" type=\"text/javascript\"></script><![endif]-->\r\n\r\n</head>\r\n<body>\r\n	你好123$!{ctx}\r\n        用户昵称：\r\n        #set($userList=$userService.getUserList(null))\r\n        #foreach($c in $userList)\r\n         <b>  $!{c.nickname}</b>,      \r\n       #end\r\n</body>\r\n</html>');

-- ----------------------------
-- Table structure for `user_user`
-- ----------------------------
DROP TABLE IF EXISTS `user_user`;
CREATE TABLE `user_user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `pic` varchar(255) DEFAULT '' COMMENT '头像',
  `content` text COMMENT '用户介绍',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- ----------------------------
-- Records of user_user
-- ----------------------------
INSERT INTO `user_user` VALUES ('1', '123345', '/static/uploadfile/img\\20111207150829-916088691.jpg', null);
INSERT INTO `user_user` VALUES ('2', '345', '/static/uploadfile\\1.jpg', null);
INSERT INTO `user_user` VALUES ('3', '暗示', '/static/uploadfile/img\\u=4239590577,3778890584&fm=21&gp=0.jpg', null);
INSERT INTO `user_user` VALUES ('4', '123', '/static/uploadfile/img\\u=4239590577,3778890584&fm=21&gp=0.jpg', null);
INSERT INTO `user_user` VALUES ('5', '哈哈', '/static/uploadfile/img\\u=4239590577,3778890584&fm=21&gp=0.jpg', null);
INSERT INTO `user_user` VALUES ('6', '123', '/static/uploadfile/img\\u=4239590577,3778890584&fm=21&gp=0.jpg', null);
INSERT INTO `user_user` VALUES ('7', '123123131', '', '<span style=\"background-color:#E53333;\">123</span>');
INSERT INTO `user_user` VALUES ('8', 'fsadfa', '', '<img src=\"http://127.0.0.1:8081/static/kindeditor/plugins/emoticons/images/1.gif\" border=\"0\" alt=\"\" />');
INSERT INTO `user_user` VALUES ('9', '123123', '', '<img src=\"0\" alt=\"\" />');
INSERT INTO `user_user` VALUES ('10', '123312啦啦啦', '', '12312313<img src=\"http://127.0.0.1:8081/static/uploadfile/img/FP7bMrES93.jpg\" alt=\"\" /><span style=\"background-color:#64451D;\">afasdfsadfsafa</span>');
