/*
Navicat MySQL Data Transfer

Source Server         : 本机环境-mysql
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2016-03-18 18:11:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(20) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`c_id`),
  KEY `fk_teacher_id` (`teacher_id`),
  CONSTRAINT `fk_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('1', 'class_a', '1');
INSERT INTO `class` VALUES ('2', 'class_b', '2');

-- ----------------------------
-- Table structure for oa_button_event
-- ----------------------------
DROP TABLE IF EXISTS `oa_button_event`;
CREATE TABLE `oa_button_event` (
  `BE_ID` varchar(64) NOT NULL COMMENT '按钮事件ID',
  `BE_TYPE_ID` varchar(64) DEFAULT NULL COMMENT '按钮事件类型ID',
  `BE_NAME` varchar(255) DEFAULT NULL COMMENT '按钮事件名称',
  `BE_METHOD` varchar(255) DEFAULT NULL COMMENT '按钮事件方法名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_button_event
-- ----------------------------

-- ----------------------------
-- Table structure for oa_button_event_set
-- ----------------------------
DROP TABLE IF EXISTS `oa_button_event_set`;
CREATE TABLE `oa_button_event_set` (
  `BE_TYPE_ID` varchar(64) NOT NULL COMMENT '事件类型ID',
  `BE_TYPE_NAME` varchar(255) DEFAULT NULL COMMENT '事件类型名称',
  `BE_TYPE_REMARK` varchar(255) DEFAULT '1' COMMENT '事件类型备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_button_event_set
-- ----------------------------

-- ----------------------------
-- Table structure for oa_node_button_event_bind
-- ----------------------------
DROP TABLE IF EXISTS `oa_node_button_event_bind`;
CREATE TABLE `oa_node_button_event_bind` (
  `BE_BIND_ID` varchar(64) NOT NULL COMMENT '按钮事件绑定的ID',
  `NB_BIND_ID` varchar(64) DEFAULT NULL COMMENT '节点按钮绑定ID',
  `BE_ID` varchar(64) DEFAULT NULL COMMENT '按钮事件ID',
  `BE_BIND_SORT_NO` int(11) DEFAULT NULL COMMENT '按钮事件绑定顺序编号',
  `BE_BIND_RESULT_TYPE` int(11) DEFAULT NULL COMMENT '按钮事件绑定的后置条件类型',
  `BE_BIND_TYPE` int(11) DEFAULT NULL COMMENT '按钮事件绑定类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_node_button_event_bind
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT,
  `s_name` varchar(20) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'student_A', '1');
INSERT INTO `student` VALUES ('2', 'student_B', '1');
INSERT INTO `student` VALUES ('3', 'student_C', '1');
INSERT INTO `student` VALUES ('4', 'student_D', '2');
INSERT INTO `student` VALUES ('5', 'student_E', '2');
INSERT INTO `student` VALUES ('6', 'student_F', '2');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', 'teacher1');
INSERT INTO `teacher` VALUES ('2', 'teacher2');
