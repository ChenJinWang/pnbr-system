/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : db_petdiet

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 27/05/2022 21:31:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `consignee` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人',
  `consignee_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人手机号',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `district` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区',
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `num` bigint(0) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_address
-- ----------------------------
INSERT INTO `tb_address` VALUES (8, 5, '陈进旺', '18269111830', '广西', '南宁', '邕宁区', '高博', 1);
INSERT INTO `tb_address` VALUES (9, 4, '赵六', '18997718156', '广西', '南宁', '邕宁', '高博', 1);
INSERT INTO `tb_address` VALUES (10, 19, '我是开发', '15994602675', '广西', '南宁', '邕宁', '高博', 1);
INSERT INTO `tb_address` VALUES (14, 2, '李四', '18269111830', '上海', '市辖区', '黄浦区', '李四家', 1);
INSERT INTO `tb_address` VALUES (15, 3, '王五', '16976618156', '湖南', '长沙', '岳麓区', '山旮旯1号', 1);
INSERT INTO `tb_address` VALUES (16, 1, '张三', '18269111831', '广东', '广州', '天河', '公寓1号', 1);
INSERT INTO `tb_address` VALUES (17, 40, '1', '18269111838', '1', '1', '1', '1', 1);

-- ----------------------------
-- Table structure for tb_background_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_background_user`;
CREATE TABLE `tb_background_user`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'admin' COMMENT '角色',
  `avatar_image` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png' COMMENT '头像',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `key_num` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密钥',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role`(`role`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_background_user
-- ----------------------------
INSERT INTO `tb_background_user` VALUES (1, 'super_admin', '123456', '18269111831', 'superadmin', '/profile/upload/2022/05/07/header03.jpeg', '2022-03-05 15:51:21', 'cjw');
INSERT INTO `tb_background_user` VALUES (3, 'general_admin', '123456', '18269111832', 'admin', '/profile/upload/2022/05/07/header01.jpeg', '2022-03-04 03:07:49', 'cjw');
INSERT INTO `tb_background_user` VALUES (4, 'sj_zhaoliu', '123456', '18269111834', 'sjuser', '/profile/upload/2022/05/07/header04.jpeg', '2022-04-09 12:48:16', 'cjw');
INSERT INTO `tb_background_user` VALUES (7, 'general_admin', '123456', '18269111833', 'admin', '/profile/upload/2022/05/07/header02.jpeg', '2022-03-04 03:07:49', 'cjw');
INSERT INTO `tb_background_user` VALUES (37, 'sj_jkml', '123456', '18269111836', 'sjuser', '/profile/upload/2022/05/07/header.jpg', '2022-04-09 14:51:17', 'cjw');
INSERT INTO `tb_background_user` VALUES (46, '123', '123456', '18269111839', 'admin', '/profile/upload/2021/03/10/dbced90a-9594-4aa7-b228-05a482c26937.png', '2022-05-08 05:29:34', 'cjw');

-- ----------------------------
-- Table structure for tb_cart
-- ----------------------------
DROP TABLE IF EXISTS `tb_cart`;
CREATE TABLE `tb_cart`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `seller_id` bigint(0) NULL DEFAULT NULL COMMENT '卖家id',
  `background_agent_id` bigint(0) NULL DEFAULT 0 COMMENT '后台代理用户id',
  `sku_id` bigint(0) NULL DEFAULT NULL COMMENT '商品id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品标题',
  `image` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `price` decimal(18, 2) NULL DEFAULT NULL COMMENT '价格 单位元',
  `num` int(0) NULL DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_cart
-- ----------------------------
INSERT INTO `tb_cart` VALUES (23, 27, 4, 0, 45, '日用粮测试01', '/profile/upload/2022/03/31/23043b72-114e-45b9-995e-244f93183d9d.gif', 100.00, 1);
INSERT INTO `tb_cart` VALUES (25, 2, 37, 0, 55, '测试4.9/1', '/profile/upload/2022/04/09/28408366-64a4-4c33-b285-2f44f034b11b.jpg', 100.00, 1);
INSERT INTO `tb_cart` VALUES (26, 4, 37, 0, 55, '测试4.9/1', '/profile/upload/2022/04/09/28408366-64a4-4c33-b285-2f44f034b11b.jpg', 100.00, 1);

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES (1, '日用粮');
INSERT INTO `tb_category` VALUES (2, '保健品');
INSERT INTO `tb_category` VALUES (3, '处方粮');
INSERT INTO `tb_category` VALUES (4, '零食');
INSERT INTO `tb_category` VALUES (16, '123');

-- ----------------------------
-- Table structure for tb_com_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `tb_com_evaluation`;
CREATE TABLE `tb_com_evaluation`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `content` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价内容',
  `pet_id` bigint(0) NULL DEFAULT NULL COMMENT '评价的商品id',
  `parent_id` bigint(0) NULL DEFAULT 0 COMMENT '父id',
  `to_user_id` bigint(0) NULL DEFAULT 0 COMMENT '被评论人Id',
  `to_user_type` int(0) NULL DEFAULT 0 COMMENT '被评论人类型(0:C端，1：B端）',
  `creator_id` bigint(0) NOT NULL COMMENT '评论人',
  `creator_type` int(0) NULL DEFAULT 0 COMMENT '创建者类型(0:C端，1：B端）',
  `created_time` datetime(0) NULL DEFAULT NULL,
  `updated_time` datetime(0) NULL DEFAULT NULL,
  `is_deleted` tinyint(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 125 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评价/评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_com_evaluation
-- ----------------------------
INSERT INTO `tb_com_evaluation` VALUES (87, '宠物喜欢吃', 49, 0, 0, 0, 1, 0, '2022-04-06 11:27:51', '2022-04-06 11:27:51', 0);
INSERT INTO `tb_com_evaluation` VALUES (89, '我也买了，家里宠物喜欢吃', 50, 0, 0, 0, 2, 0, '2022-04-06 14:31:09', '2022-04-06 14:31:09', 0);
INSERT INTO `tb_com_evaluation` VALUES (90, '这个很好，我也给宠物买过', 53, 0, 0, 0, 2, 0, '2022-04-06 14:36:52', '2022-04-06 14:36:52', 0);
INSERT INTO `tb_com_evaluation` VALUES (91, '是的，我也给宠物买过，非常好，下次还买！', 53, 90, 2, 0, 3, 0, '2022-04-06 14:43:47', '2022-04-06 14:43:47', 0);
INSERT INTO `tb_com_evaluation` VALUES (92, '很棒！ ', 48, 0, 0, 0, 3, 0, '2022-04-06 14:53:50', '2022-04-06 14:53:50', 0);
INSERT INTO `tb_com_evaluation` VALUES (93, '我也买过，很棒', 49, 0, 0, 0, 1, 0, '2022-04-06 14:56:06', '2022-04-06 14:56:06', 0);
INSERT INTO `tb_com_evaluation` VALUES (94, '我家狗子也喜欢吃，吃了一个星期，现在长得很壮实', 49, 87, 1, 0, 1, 0, '2022-04-06 14:56:44', '2022-04-06 14:56:44', 0);
INSERT INTO `tb_com_evaluation` VALUES (95, '是呀，我家宠物也是', 50, 89, 2, 0, 1, 0, '2022-04-06 14:57:34', '2022-04-06 14:57:34', 0);
INSERT INTO `tb_com_evaluation` VALUES (101, '真的吗？', 49, 87, 1, 0, 2, 0, '2022-04-07 09:43:08', '2022-04-07 09:43:08', 0);
INSERT INTO `tb_com_evaluation` VALUES (105, '哦哦', 48, 0, 0, 0, 3, 0, '2022-04-07 10:06:29', '2022-04-07 10:06:29', 0);
INSERT INTO `tb_com_evaluation` VALUES (106, '为什么', 50, 89, 2, 0, 3, 0, '2022-04-07 10:08:35', '2022-04-07 10:08:35', 0);
INSERT INTO `tb_com_evaluation` VALUES (108, '你好', 49, 93, 1, 0, 2, 0, '2022-04-07 10:17:37', '2022-04-07 10:17:37', 0);
INSERT INTO `tb_com_evaluation` VALUES (109, '啊啊', 49, 93, 1, 0, 2, 0, '2022-04-07 10:18:45', '2022-04-07 10:18:45', 0);
INSERT INTO `tb_com_evaluation` VALUES (111, '你好', 49, 87, 1, 0, 3, 0, '2022-04-07 10:27:27', '2022-04-07 10:27:27', 0);
INSERT INTO `tb_com_evaluation` VALUES (112, 'aaa', 49, 87, 1, 0, 2, 0, '2022-04-07 10:39:59', '2022-04-07 10:39:59', 0);
INSERT INTO `tb_com_evaluation` VALUES (114, '测试', 49, 0, 0, 0, 2, 0, '2022-04-07 10:50:23', '2022-04-07 10:50:23', 0);
INSERT INTO `tb_com_evaluation` VALUES (115, '你好', 49, 114, 2, 0, 3, 0, '2022-04-07 10:51:42', '2022-04-07 10:51:42', 0);
INSERT INTO `tb_com_evaluation` VALUES (116, '今天又买了1000份', 50, 89, 2, 0, 2, 0, '2022-04-07 10:54:44', '2022-04-07 10:54:44', 0);
INSERT INTO `tb_com_evaluation` VALUES (117, '宠物很喜欢\n', 50, 89, 2, 0, 3, 0, '2022-04-07 10:55:40', '2022-04-07 10:55:40', 0);
INSERT INTO `tb_com_evaluation` VALUES (118, '啊啊', 48, 92, 3, 0, 4, 1, '2022-04-11 11:42:47', '2022-04-11 11:42:47', 0);
INSERT INTO `tb_com_evaluation` VALUES (119, '1', 54, 0, 0, 0, 1, 0, '2022-05-05 13:38:21', '2022-05-05 13:38:21', 0);
INSERT INTO `tb_com_evaluation` VALUES (120, '1', 54, 0, 0, 0, 37, 1, '2022-05-05 13:38:32', '2022-05-05 13:38:32', 0);
INSERT INTO `tb_com_evaluation` VALUES (121, '1', 55, 0, 0, 0, 1, 0, '2022-05-06 08:55:29', '2022-05-06 08:55:29', 0);
INSERT INTO `tb_com_evaluation` VALUES (122, '1', 57, 0, 0, 0, 1, 0, '2022-05-07 06:47:46', '2022-05-07 06:47:46', 0);
INSERT INTO `tb_com_evaluation` VALUES (123, '1', 57, 122, 1, 0, 37, 1, '2022-05-07 06:47:59', '2022-05-07 06:47:59', 0);
INSERT INTO `tb_com_evaluation` VALUES (124, '1', 58, 0, 0, 0, 1, 0, '2022-05-08 00:43:56', '2022-05-08 00:43:56', 0);
INSERT INTO `tb_com_evaluation` VALUES (125, '1', 60, 0, 0, 0, 1, 0, '2022-05-08 05:35:28', '2022-05-08 05:35:28', 0);

-- ----------------------------
-- Table structure for tb_community
-- ----------------------------
DROP TABLE IF EXISTS `tb_community`;
CREATE TABLE `tb_community`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cid` bigint(0) NOT NULL COMMENT '分类id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `images` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面',
  `images_list` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片列表',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `brief` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `type` int(0) NOT NULL COMMENT '类型',
  `contact_way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `audit_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '审核状态',
  `saleable` tinyint(0) NULL DEFAULT 1 COMMENT '是否上架，0下架，1上架',
  `create_id` bigint(0) NOT NULL COMMENT '发布人',
  `background_agent_id` bigint(0) NULL DEFAULT 0 COMMENT '后台代理人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(0) NULL DEFAULT 0 COMMENT '是否删除',
  `num` bigint(0) NULL DEFAULT 1,
  `favorite_num` bigint(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_id`(`create_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '花' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_community
-- ----------------------------
INSERT INTO `tb_community` VALUES (45, 1, '发现一个秘密...', '/profile/upload/2022/03/31/pet01.jpeg', ',/profile/upload/2022/03/31/pet01.jpeg', '干膨化食品', '我家狗子就是吃了这种商品，身体长得非常好，价格也不贵，推荐你们为自家宠物乖乖买来试试哦！', 0, NULL, '1', 1, 4, 0, '2022-03-31 11:47:17', '2022-03-31 11:47:17', 0, 1, 0);
INSERT INTO `tb_community` VALUES (46, 1, '哇，限量大礼包！', '/profile/upload/2022/03/31/e38727a33bdbffc7ff2445f7f639a444.jpeg', ',/profile/upload/2022/03/31/e38727a33bdbffc7ff2445f7f639a444.jpeg', '半湿食品', '我家狗子就是吃了这种商品，身体长得非常好，价格也不贵，推荐你们为自家宠物乖乖买来试试哦！', 0, NULL, '1', 1, 4, 0, '2022-03-31 11:54:34', '2022-03-31 11:54:34', 0, 1, 0);
INSERT INTO `tb_community` VALUES (47, 2, '推荐这款狗粮！', '/profile/upload/2022/03/31/498f6f9c00f62e1729b1e30293f2e70c.jpeg', ',/profile/upload/2022/03/31/498f6f9c00f62e1729b1e30293f2e70c.jpeg', '干膨化食品', '我家狗子就是吃了这种商品，身体长得非常好，价格也不贵，推荐你们为自家宠物乖乖买来试试哦！', 0, NULL, '1', 1, 4, 0, '2022-03-31 12:00:11', '2022-03-31 12:00:11', 0, 1, 0);
INSERT INTO `tb_community` VALUES (48, 2, '我家狗狗最喜欢的零食！', '/profile/upload/2022/03/31/2be86a2cb6a1661b661edcec97e38bab.jpeg', ',/profile/upload/2022/03/31/2be86a2cb6a1661b661edcec97e38bab.jpeg', '干膨化食品', '我家狗子就是吃了这种商品，身体长得非常好，价格也不贵，推荐你们为自家宠物乖乖买来试试哦！', 0, NULL, '1', 1, 4, 0, '2022-03-31 12:00:30', '2022-03-31 12:00:30', 0, 1, 0);
INSERT INTO `tb_community` VALUES (49, 3, '推荐！良心商家！', '/profile/upload/2022/03/31/pet05.jpeg', ',/profile/upload/2022/03/31/pet05.jpeg', '半湿食品', '我家狗子就是吃了这种商品，身体长得非常好，价格也不贵，推荐你们为自家宠物乖乖买来试试哦！', 0, NULL, '1', 1, 4, 0, '2022-03-31 12:02:41', '2022-03-31 12:02:41', 0, 1, 0);
INSERT INTO `tb_community` VALUES (50, 3, '进来聊聊天吧！', '/profile/upload/2022/03/31/pet06.jpeg', ',/profile/upload/2022/03/31/pet06.jpeg', '半湿食品', '我家狗子就是吃了这种商品，身体长得非常好，价格也不贵，推荐你们为自家宠物乖乖买来试试哦！', 0, NULL, '1', 1, 4, 0, '2022-03-31 12:03:00', '2022-03-31 12:03:00', 0, 1, 0);
INSERT INTO `tb_community` VALUES (51, 4, '猫星人日常生活照', '/profile/upload/2022/03/31/pet03.jpeg', ',/profile/upload/2022/03/31/pet03.jpeg', '软膨化（软干的）食品', '我家狗子就是吃了这种商品，身体长得非常好，价格也不贵，推荐你们为自家宠物乖乖买来试试哦！', 0, NULL, '1', 1, 4, 0, '2022-03-31 12:04:45', '2022-03-31 12:04:45', 0, 1, 0);
INSERT INTO `tb_community` VALUES (52, 4, '好萌，忍不住了!', '/profile/upload/2022/03/31/pet02.jpeg', ',/profile/upload/2022/03/31/pet02.jpeg', '软膨化（软干的）食品', '我家狗子就是吃了这种商品，身体长得非常好，价格也不贵，推荐你们为自家宠物乖乖买来试试哦！', 0, NULL, '1', 1, 4, 0, '2022-03-31 12:04:55', '2022-03-31 12:04:55', 0, 1, 0);
INSERT INTO `tb_community` VALUES (59, 1, 'cs', '/profile/upload/2022/05/08/70e9f019-54d5-42fb-92a6-7c7a0777616a.jpeg', ',/profile/upload/2022/05/08/70e9f019-54d5-42fb-92a6-7c7a0777616a.jpeg', '干膨化食品', 'cs', 0, NULL, '1', 1, 1, 0, '2022-05-08 02:12:54', '2022-05-08 02:12:54', 0, 1, 0);
INSERT INTO `tb_community` VALUES (60, 1, '帖子', '/profile/upload/2022/05/08/7619b6d9-eb4e-44ac-90bc-36fc8b71eefe.jpeg', ',/profile/upload/2022/05/08/7619b6d9-eb4e-44ac-90bc-36fc8b71eefe.jpeg', '干膨化食品', '1', 0, NULL, '1', 1, 1, 0, '2022-05-08 05:35:06', '2022-05-08 05:35:06', 0, 1, 0);

-- ----------------------------
-- Table structure for tb_feedback
-- ----------------------------
DROP TABLE IF EXISTS `tb_feedback`;
CREATE TABLE `tb_feedback`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cid` bigint(0) NOT NULL COMMENT '分类id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `images` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面',
  `images_list` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片列表',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `brief` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `type` int(0) NOT NULL COMMENT '类型',
  `contact_way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `audit_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '审核状态',
  `saleable` tinyint(0) NULL DEFAULT 1 COMMENT '是否上架，0下架，1上架',
  `create_id` bigint(0) NOT NULL COMMENT '发布人',
  `background_agent_id` bigint(0) NULL DEFAULT 0 COMMENT '后台代理人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(0) NULL DEFAULT 0 COMMENT '是否删除',
  `num` bigint(0) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_id`(`create_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '花' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_feedback
-- ----------------------------
INSERT INTO `tb_feedback` VALUES (41, 1, '反馈', '/profile/upload/2022/05/08/41791825-c7e5-48f7-bf07-796d846d3399.jpeg', ',/profile/upload/2022/05/08/41791825-c7e5-48f7-bf07-796d846d3399.jpeg', '干膨化食品', '假货', 0, NULL, '0', 1, 1, 0, '2022-05-08 05:33:38', '2022-05-08 05:33:38', 0, 1);

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `total_pay` decimal(18, 2) NULL DEFAULT NULL COMMENT '总金额 单位分',
  `actual_pay` decimal(18, 2) NULL DEFAULT NULL COMMENT '实付金额 单位分',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `seller_id` bigint(0) NULL DEFAULT NULL COMMENT '卖家id',
  `background_agent_id` bigint(0) NULL DEFAULT 1 COMMENT '后台代理者',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `buyer_nick` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家昵称',
  `receiver` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家全称',
  `receiver_mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人手机',
  `receiver_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区/县 ',
  `receiver_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址，如：xx路xx号',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_deleted` tinyint(0) NULL DEFAULT 0,
  `num` bigint(0) NULL DEFAULT 1,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('1510635915335286784', 100.00, 110.00, '2022-04-03 15:10:43', '2022-04-07 11:55:46', 4, 0, 2, '李四', '李四', '18269111830', '上海', '市辖区', '黄浦区', '李四家', '', '5', 0, 1);
INSERT INTO `tb_order` VALUES ('1512066846684151808', 100.00, 110.00, '2022-04-07 13:56:44', '2022-04-09 13:13:01', 4, 0, 2, '李四', '李四', '18269111830', '上海', '市辖区', '黄浦区', '李四家', '', '4', 0, 1);
INSERT INTO `tb_order` VALUES ('1512422678856855552', 100.00, 110.00, '2022-04-08 13:30:41', '2022-04-08 14:01:32', 4, 0, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '4', 0, 1);
INSERT INTO `tb_order` VALUES ('1512435756625424384', 100.00, 110.00, '2022-04-08 14:22:39', '2022-04-08 14:25:28', 4, 0, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '5', 1, 1);
INSERT INTO `tb_order` VALUES ('1512788162060070912', 100.00, 110.00, '2022-04-09 13:42:59', '2022-04-09 13:42:59', 37, 0, 2, '李四', '李四', '18269111830', '上海', '市辖区', '黄浦区', '李四家', '', '2', 0, 1);
INSERT INTO `tb_order` VALUES ('1513122883952451584', 100.00, 110.00, '2022-04-10 11:53:03', '2022-04-10 11:53:05', 37, 0, 2, '李四', '李四', '18269111830', '上海', '市辖区', '黄浦区', '李四家', '', '2', 0, 1);
INSERT INTO `tb_order` VALUES ('1513482922022146048', 100.00, 110.00, '2022-04-11 11:43:42', '2022-04-11 11:43:44', 37, 0, 4, '赵六宠物粮超市', '赵六', '18997718156', '广西', '南宁', '邕宁', '高博', '', '2', 0, 1);
INSERT INTO `tb_order` VALUES ('1522868504305319936', 200.00, 210.00, '2022-05-07 09:18:40', '2022-05-07 09:25:31', 37, 0, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '5', 0, 1);
INSERT INTO `tb_order` VALUES ('1522870296514641920', 200.00, 210.00, '2022-05-07 09:25:47', '2022-05-07 09:26:17', 37, 1, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '5', 0, 1);
INSERT INTO `tb_order` VALUES ('1522870517906784256', 200.00, 210.00, '2022-05-07 09:26:40', '2022-05-07 09:26:41', 37, 1, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '2', 0, 1);
INSERT INTO `tb_order` VALUES ('1522871625312092160', 799.00, 809.00, '2022-05-07 09:31:04', '2022-05-07 09:36:23', 37, 1, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '4', 0, 1);
INSERT INTO `tb_order` VALUES ('1523173813188018176', 100.00, 110.00, '2022-05-08 05:31:51', '2022-05-08 05:32:04', 37, 0, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '2', 0, 1);
INSERT INTO `tb_order` VALUES ('1523173820364472320', 100.00, 110.00, '2022-05-08 05:31:53', '2022-05-08 05:32:04', 37, 0, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '2', 0, 1);
INSERT INTO `tb_order` VALUES ('1523173820444164096', 100.00, 110.00, '2022-05-08 05:31:53', '2022-05-08 05:32:04', 37, 0, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '2', 0, 1);
INSERT INTO `tb_order` VALUES ('1523173821182361600', 100.00, 110.00, '2022-05-08 05:31:53', '2022-05-08 05:32:04', 37, 0, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '2', 0, 1);
INSERT INTO `tb_order` VALUES ('1523173826286829568', 100.00, 110.00, '2022-05-08 05:31:54', '2022-05-08 05:32:04', 37, 0, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '2', 0, 1);
INSERT INTO `tb_order` VALUES ('1523173831869448192', 100.00, 110.00, '2022-05-08 05:31:55', '2022-05-08 05:32:04', 37, 0, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '2', 0, 1);
INSERT INTO `tb_order` VALUES ('1523173849489719296', 100.00, 110.00, '2022-05-08 05:32:00', '2022-05-08 05:32:04', 37, 0, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '2', 0, 1);
INSERT INTO `tb_order` VALUES ('1523173851314241536', 100.00, 110.00, '2022-05-08 05:32:00', '2022-05-08 05:32:05', 37, 0, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '2', 0, 1);
INSERT INTO `tb_order` VALUES ('1523173853054877696', 100.00, 110.00, '2022-05-08 05:32:00', '2022-05-08 05:32:05', 37, 0, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '2', 0, 1);
INSERT INTO `tb_order` VALUES ('1523173854074093568', 100.00, 110.00, '2022-05-08 05:32:01', '2022-05-08 05:32:40', 37, 0, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '4', 0, 1);
INSERT INTO `tb_order` VALUES ('1523173854074093569', 100.00, 110.00, '2022-05-08 05:32:01', '2022-05-08 05:32:05', 37, 0, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '2', 0, 1);
INSERT INTO `tb_order` VALUES ('1523173854074093570', 100.00, 110.00, '2022-05-08 05:32:01', '2022-05-08 05:32:05', 37, 0, 1, '张三', '张三', '18269111831', '广东', '广州', '天河', '公寓1号', '', '2', 0, 1);

-- ----------------------------
-- Table structure for tb_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_detail`;
CREATE TABLE `tb_order_detail`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '订单详情id ',
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `sku_id` bigint(0) NOT NULL COMMENT 'sku商品id',
  `num` int(0) NOT NULL COMMENT '购买数量',
  `title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品标题',
  `own_spec` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '商品动态属性键值集',
  `price` decimal(18, 2) NOT NULL COMMENT '价格,单位：元',
  `image` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '商品图片',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `key_order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 227 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order_detail
-- ----------------------------
INSERT INTO `tb_order_detail` VALUES (180, '1510635915335286784', 45, 1, '日用粮测试01', '', 100.00, '/profile/upload/2022/03/31/23043b72-114e-45b9-995e-244f93183d9d.gif');
INSERT INTO `tb_order_detail` VALUES (181, '1512066846684151808', 46, 1, '日用粮测试02', '', 100.00, '/profile/upload/2022/03/31/23043b72-114e-45b9-995e-244f93183d9d.gif');
INSERT INTO `tb_order_detail` VALUES (182, '1512422678856855552', 46, 1, '日用粮测试02', '', 100.00, '/profile/upload/2022/03/31/83028299102f8adde77b8b7efab05451.jpeg');
INSERT INTO `tb_order_detail` VALUES (183, '1512435756625424384', 50, 1, '处方粮测试02', '', 100.00, '/profile/upload/2022/03/31/beef3b6c-9d9f-4096-a55b-f3cc58f600a6.gif');
INSERT INTO `tb_order_detail` VALUES (184, '1512788162060070912', 55, 1, '测试4.9/1', '', 100.00, '/profile/upload/2022/04/09/28408366-64a4-4c33-b285-2f44f034b11b.jpg');
INSERT INTO `tb_order_detail` VALUES (185, '1513122883952451584', 55, 1, '测试4.9/1', '', 100.00, '/profile/upload/2022/04/09/28408366-64a4-4c33-b285-2f44f034b11b.jpg');
INSERT INTO `tb_order_detail` VALUES (186, '1513482922022146048', 55, 1, '测试4.9/1', '', 100.00, '/profile/upload/2022/04/09/28408366-64a4-4c33-b285-2f44f034b11b.jpg');
INSERT INTO `tb_order_detail` VALUES (204, '1522868504305319936', 63, 1, 'PUPPY幼犬粮', '', 100.00, '/profile/upload/2022/05/06/bc8eaee555d6561d6a96cb0a173df27b.jpeg');
INSERT INTO `tb_order_detail` VALUES (205, '1522868504305319936', 62, 1, '拉布拉多专用型', '', 100.00, '/profile/upload/2022/05/06/f1f39e2bd7e80d952b93bc55739215ef.jpeg');
INSERT INTO `tb_order_detail` VALUES (206, '1522870296514641920', 63, 1, 'PUPPY幼犬粮', '', 100.00, '/profile/upload/2022/05/06/bc8eaee555d6561d6a96cb0a173df27b.jpeg');
INSERT INTO `tb_order_detail` VALUES (207, '1522870296514641920', 62, 1, '拉布拉多专用型', '', 100.00, '/profile/upload/2022/05/06/f1f39e2bd7e80d952b93bc55739215ef.jpeg');
INSERT INTO `tb_order_detail` VALUES (208, '1522870517906784256', 63, 1, 'PUPPY幼犬粮', '', 100.00, '/profile/upload/2022/05/06/bc8eaee555d6561d6a96cb0a173df27b.jpeg');
INSERT INTO `tb_order_detail` VALUES (209, '1522870517906784256', 62, 1, '拉布拉多专用型', '', 100.00, '/profile/upload/2022/05/06/f1f39e2bd7e80d952b93bc55739215ef.jpeg');
INSERT INTO `tb_order_detail` VALUES (210, '1522871625312092160', 66, 1, '宠物保健饼干', '', 699.00, '/profile/upload/2022/05/07/54a4c963-aab8-4ea0-b15c-0d9ed92ac420.jpeg');
INSERT INTO `tb_order_detail` VALUES (211, '1522871625312092160', 63, 1, 'PUPPY幼犬粮', '', 100.00, '/profile/upload/2022/05/06/bc8eaee555d6561d6a96cb0a173df27b.jpeg');
INSERT INTO `tb_order_detail` VALUES (216, '1523173813188018176', 68, 1, '测试', '', 100.00, '/profile/upload/2022/05/08/f0985336-1643-4973-8c97-bfaa6f3f42dd.jpeg');
INSERT INTO `tb_order_detail` VALUES (217, '1523173826286829568', 68, 1, '测试', '', 100.00, '/profile/upload/2022/05/08/f0985336-1643-4973-8c97-bfaa6f3f42dd.jpeg');
INSERT INTO `tb_order_detail` VALUES (218, '1523173821182361600', 68, 1, '测试', '', 100.00, '/profile/upload/2022/05/08/f0985336-1643-4973-8c97-bfaa6f3f42dd.jpeg');
INSERT INTO `tb_order_detail` VALUES (219, '1523173820444164096', 68, 1, '测试', '', 100.00, '/profile/upload/2022/05/08/f0985336-1643-4973-8c97-bfaa6f3f42dd.jpeg');
INSERT INTO `tb_order_detail` VALUES (220, '1523173820364472320', 68, 1, '测试', '', 100.00, '/profile/upload/2022/05/08/f0985336-1643-4973-8c97-bfaa6f3f42dd.jpeg');
INSERT INTO `tb_order_detail` VALUES (221, '1523173831869448192', 68, 1, '测试', '', 100.00, '/profile/upload/2022/05/08/f0985336-1643-4973-8c97-bfaa6f3f42dd.jpeg');
INSERT INTO `tb_order_detail` VALUES (222, '1523173849489719296', 68, 1, '测试', '', 100.00, '/profile/upload/2022/05/08/f0985336-1643-4973-8c97-bfaa6f3f42dd.jpeg');
INSERT INTO `tb_order_detail` VALUES (223, '1523173851314241536', 68, 1, '测试', '', 100.00, '/profile/upload/2022/05/08/f0985336-1643-4973-8c97-bfaa6f3f42dd.jpeg');
INSERT INTO `tb_order_detail` VALUES (224, '1523173853054877696', 68, 1, '测试', '', 100.00, '/profile/upload/2022/05/08/f0985336-1643-4973-8c97-bfaa6f3f42dd.jpeg');
INSERT INTO `tb_order_detail` VALUES (225, '1523173854074093569', 68, 1, '测试', '', 100.00, '/profile/upload/2022/05/08/f0985336-1643-4973-8c97-bfaa6f3f42dd.jpeg');
INSERT INTO `tb_order_detail` VALUES (226, '1523173854074093570', 68, 1, '测试', '', 100.00, '/profile/upload/2022/05/08/f0985336-1643-4973-8c97-bfaa6f3f42dd.jpeg');
INSERT INTO `tb_order_detail` VALUES (227, '1523173854074093568', 68, 1, '测试', '', 100.00, '/profile/upload/2022/05/08/f0985336-1643-4973-8c97-bfaa6f3f42dd.jpeg');

-- ----------------------------
-- Table structure for tb_order_status
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_status`;
CREATE TABLE `tb_order_status`  (
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态：1、未付款 2、已付款,未发货 3、已发货,未确认 4、交易成功 5、交易关闭',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '订单创建时间',
  `payment_time` datetime(0) NULL DEFAULT NULL COMMENT '付款时间',
  `consign_time` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime(0) NULL DEFAULT NULL COMMENT '交易关闭时间',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单状态表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order_status
-- ----------------------------
INSERT INTO `tb_order_status` VALUES ('1510635915335286784', 5, '2022-04-03 15:10:43', '2022-04-03 15:10:44', NULL, NULL, '2022-04-07 11:55:45');
INSERT INTO `tb_order_status` VALUES ('1512066846684151808', 4, '2022-04-07 13:56:44', '2022-04-07 13:56:44', '2022-04-08 10:28:42', '2022-04-09 13:13:00', NULL);
INSERT INTO `tb_order_status` VALUES ('1512422678856855552', 4, '2022-04-08 13:30:41', '2022-04-08 14:01:07', '2022-04-08 14:01:22', '2022-04-08 14:01:31', NULL);
INSERT INTO `tb_order_status` VALUES ('1512435756625424384', 5, '2022-04-08 14:22:39', '2022-04-08 14:22:39', '2022-04-08 14:23:01', '2022-04-08 14:23:07', '2022-04-08 14:25:28');
INSERT INTO `tb_order_status` VALUES ('1512788162060070912', 2, '2022-04-09 13:42:59', '2022-04-09 13:42:59', NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES ('1513122883952451584', 2, '2022-04-10 11:53:03', '2022-04-10 11:53:04', NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES ('1513482922022146048', 2, '2022-04-11 11:43:42', '2022-04-11 11:43:43', NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES ('1522868504305319936', 5, '2022-05-07 09:18:40', '2022-05-07 09:18:40', NULL, NULL, '2022-05-07 09:25:30');
INSERT INTO `tb_order_status` VALUES ('1522870296514641920', 5, '2022-05-07 09:25:47', '2022-05-07 09:25:47', NULL, NULL, '2022-05-07 09:26:17');
INSERT INTO `tb_order_status` VALUES ('1522870517906784256', 2, '2022-05-07 09:26:40', '2022-05-07 09:26:40', NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES ('1522871625312092160', 4, '2022-05-07 09:31:04', '2022-05-07 09:31:04', '2022-05-07 09:36:07', '2022-05-07 09:36:23', NULL);
INSERT INTO `tb_order_status` VALUES ('1523173813188018176', 2, '2022-05-08 05:31:51', '2022-05-08 05:32:03', NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES ('1523173820364472320', 2, '2022-05-08 05:31:53', '2022-05-08 05:32:03', NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES ('1523173820444164096', 2, '2022-05-08 05:31:53', '2022-05-08 05:32:03', NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES ('1523173821182361600', 2, '2022-05-08 05:31:53', '2022-05-08 05:32:03', NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES ('1523173826286829568', 2, '2022-05-08 05:31:54', '2022-05-08 05:32:03', NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES ('1523173831869448192', 2, '2022-05-08 05:31:55', '2022-05-08 05:32:03', NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES ('1523173849489719296', 2, '2022-05-08 05:32:00', '2022-05-08 05:32:04', NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES ('1523173851314241536', 2, '2022-05-08 05:32:00', '2022-05-08 05:32:04', NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES ('1523173853054877696', 2, '2022-05-08 05:32:00', '2022-05-08 05:32:04', NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES ('1523173854074093568', 4, '2022-05-08 05:32:01', '2022-05-08 05:32:04', '2022-05-08 05:32:33', '2022-05-08 05:32:39', NULL);
INSERT INTO `tb_order_status` VALUES ('1523173854074093569', 2, '2022-05-08 05:32:01', '2022-05-08 05:32:04', NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES ('1523173854074093570', 2, '2022-05-08 05:32:01', '2022-05-08 05:32:04', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_pet
-- ----------------------------
DROP TABLE IF EXISTS `tb_pet`;
CREATE TABLE `tb_pet`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cid` bigint(0) NOT NULL COMMENT '分类id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `images` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面',
  `images_list` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片列表',
  `price` decimal(18, 2) NOT NULL COMMENT '价格 单位元',
  `specification` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规格',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `brief` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `type` int(0) NOT NULL COMMENT '类型',
  `contact_way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `audit_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '审核状态',
  `saleable` tinyint(0) NULL DEFAULT 1 COMMENT '是否上架，0下架，1上架',
  `create_id` bigint(0) NOT NULL COMMENT '发布人',
  `background_agent_id` bigint(0) NULL DEFAULT 0 COMMENT '后台代理人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(0) NULL DEFAULT 0 COMMENT '是否删除',
  `num` bigint(20) UNSIGNED ZEROFILL NULL DEFAULT 00000000000000000001,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_id`(`create_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '花' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_pet
-- ----------------------------
INSERT INTO `tb_pet` VALUES (45, 1, '艾嘉成犬粮', '/profile/upload/2022/03/31/6f2e0534aab8b4cb7d68e114a70b9d07.jpeg', ',/profile/upload/2022/03/31/6f2e0534aab8b4cb7d68e114a70b9d07.jpeg', 100.00, '100g', '干膨化食品', '治疗宠物的掉毛、食欲不振、手术后的修养等问题', 0, NULL, '1', 1, 4, 0, '2022-03-31 11:47:17', '2022-03-31 11:47:17', 0, 00000000000000000001);
INSERT INTO `tb_pet` VALUES (46, 1, '邦德仕生态粮', '/profile/upload/2022/03/31/8659b5ae3a0660cbea56cfb265e616fc.jpeg', ',/profile/upload/2022/03/31/8659b5ae3a0660cbea56cfb265e616fc.jpeg', 100.00, '120g', '半湿食品', '治疗宠物的掉毛、食欲不振、手术后的修养等问题', 0, NULL, '1', 1, 4, 0, '2022-03-31 11:54:34', '2022-03-31 11:54:34', 0, 00000000000000000001);
INSERT INTO `tb_pet` VALUES (47, 2, '比瑞吉幼犬粮', '/profile/upload/2022/03/31/2be86a2cb6a1661b661edcec97e38bab.jpeg', ',/profile/upload/2022/03/31/2be86a2cb6a1661b661edcec97e38bab.jpeg', 100.00, '100g', '干膨化食品', '治疗宠物的掉毛、食欲不振、手术后的修养等问题', 0, NULL, '1', 1, 4, 0, '2022-03-31 12:00:11', '2022-03-31 12:00:11', 0, 00000000000000000001);
INSERT INTO `tb_pet` VALUES (48, 2, '好之味幼犬粮', '/profile/upload/2022/03/31/498f6f9c00f62e1729b1e30293f2e70c.jpeg', ',/profile/upload/2022/03/31/498f6f9c00f62e1729b1e30293f2e70c.jpeg', 100.00, '100g', '干膨化食品', '治疗宠物的掉毛、食欲不振、手术后的修养等问题', 0, NULL, '1', 1, 4, 0, '2022-03-31 12:00:30', '2022-03-31 12:00:30', 0, 00000000000000000001);
INSERT INTO `tb_pet` VALUES (49, 3, '兔子处方粮', '/profile/upload/2022/03/31/cb74e0d3f9912b2d489d223dd4544a39.jpeg', ',/profile/upload/2022/03/31/cb74e0d3f9912b2d489d223dd4544a39.jpeg', 100.00, '100g', '半湿食品', '治疗宠物的掉毛、食欲不振、手术后的修养等问题', 0, NULL, '1', 1, 4, 0, '2022-03-31 12:02:41', '2022-03-31 12:02:41', 0, 00000000000000000001);
INSERT INTO `tb_pet` VALUES (50, 3, '张旭幼犬滋养粮', '/profile/upload/2022/03/31/cd86a46970cada888d016f2d34692891.jpeg', ',/profile/upload/2022/03/31/cd86a46970cada888d016f2d34692891.jpeg', 100.00, '100g', '半湿食品', '治疗宠物的掉毛、食欲不振、手术后的修养等问题', 0, NULL, '1', 1, 4, 0, '2022-03-31 12:03:00', '2022-03-31 12:03:00', 0, 00000000000000000001);
INSERT INTO `tb_pet` VALUES (51, 4, '仓鼠零食大礼包', '/profile/upload/2022/03/31/881ae2cdbb8cafb15993a1b52acd0b98.jpeg', ',/profile/upload/2022/03/31/881ae2cdbb8cafb15993a1b52acd0b98.jpeg', 100.00, '100g', '软膨化（软干的）食品', '治疗宠物的掉毛、食欲不振、手术后的修养等问题', 0, NULL, '1', 1, 4, 0, '2022-03-31 12:04:45', '2022-03-31 12:04:45', 0, 00000000000000000001);
INSERT INTO `tb_pet` VALUES (52, 4, '诺贝幼犬零食', '/profile/upload/2022/03/31/25473b861b4e84da4d484fb053cbf578.jpeg', ',/profile/upload/2022/03/31/25473b861b4e84da4d484fb053cbf578.jpeg', 100.00, '100g', '软膨化（软干的）食品', '治疗宠物的掉毛、食欲不振、手术后的修养等问题', 0, NULL, '1', 1, 4, 0, '2022-03-31 12:04:55', '2022-03-31 12:04:55', 0, 00000000000000000001);
INSERT INTO `tb_pet` VALUES (62, 1, '拉布拉多专用型', '/profile/upload/2022/05/06/f1f39e2bd7e80d952b93bc55739215ef.jpeg', ',/profile/upload/2022/05/06/f1f39e2bd7e80d952b93bc55739215ef.jpeg', 100.00, '100g', '干膨化食品', 'cs', 0, NULL, '1', 1, 37, 0, '2022-05-06 04:15:14', '2022-05-06 04:15:14', 0, 00000000000000000001);
INSERT INTO `tb_pet` VALUES (63, 1, 'PUPPY幼犬粮', '/profile/upload/2022/05/06/bc8eaee555d6561d6a96cb0a173df27b.jpeg', ',/profile/upload/2022/05/06/bc8eaee555d6561d6a96cb0a173df27b.jpeg', 100.00, '100g', '干膨化食品', 'cs', 0, NULL, '1', 1, 37, 0, '2022-05-06 07:38:04', '2022-05-06 07:38:04', 0, 00000000000000000001);
INSERT INTO `tb_pet` VALUES (66, 2, '宠物保健饼干', '/profile/upload/2022/05/07/54a4c963-aab8-4ea0-b15c-0d9ed92ac420.jpeg', ',/profile/upload/2022/05/07/54a4c963-aab8-4ea0-b15c-0d9ed92ac420.jpeg', 699.00, '100g', '干膨化食品', '保证宠物健康', 0, NULL, '1', 1, 37, 0, '2022-05-07 09:29:36', '2022-05-07 09:29:36', 0, 00000000000000000001);
INSERT INTO `tb_pet` VALUES (68, 1, '测试', '/profile/upload/2022/05/08/f0985336-1643-4973-8c97-bfaa6f3f42dd.jpeg', ',/profile/upload/2022/05/08/f0985336-1643-4973-8c97-bfaa6f3f42dd.jpeg', 100.00, '100g', '干膨化食品', '测试', 0, NULL, '1', 0, 37, 0, '2022-05-08 05:30:55', '2022-05-08 05:30:55', 0, 00000000000000000001);

-- ----------------------------
-- Table structure for tb_pet_favorite
-- ----------------------------
DROP TABLE IF EXISTS `tb_pet_favorite`;
CREATE TABLE `tb_pet_favorite`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `favorite_id` bigint(0) NULL DEFAULT NULL COMMENT '商品id',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `num` bigint(0) NULL DEFAULT 1 COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收藏' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_pet_favorite
-- ----------------------------
INSERT INTO `tb_pet_favorite` VALUES (53, 2, 47, '2022-04-09 16:44:34', 1);
INSERT INTO `tb_pet_favorite` VALUES (54, 2, 46, '2022-04-09 16:44:35', 1);
INSERT INTO `tb_pet_favorite` VALUES (114, 1, 52, NULL, 1);
INSERT INTO `tb_pet_favorite` VALUES (115, 1, 62, NULL, 1);
INSERT INTO `tb_pet_favorite` VALUES (116, 1, 64, NULL, 1);

-- ----------------------------
-- Table structure for tb_prod_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `tb_prod_evaluation`;
CREATE TABLE `tb_prod_evaluation`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `content` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价内容',
  `pet_id` bigint(0) NULL DEFAULT NULL COMMENT '评价的商品id',
  `parent_id` bigint(0) NULL DEFAULT 0 COMMENT '父id',
  `to_user_id` bigint(0) NULL DEFAULT 0 COMMENT '被评论人Id',
  `to_user_type` int(0) NULL DEFAULT 0 COMMENT '被评论人类型(0:C端，1：B端）',
  `creator_id` bigint(0) NOT NULL COMMENT '评论人',
  `creator_type` int(0) NULL DEFAULT 0 COMMENT '创建者类型(0:C端，1：B端）',
  `created_time` datetime(0) NULL DEFAULT NULL,
  `updated_time` datetime(0) NULL DEFAULT NULL,
  `is_deleted` tinyint(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 124 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评价/评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_prod_evaluation
-- ----------------------------
INSERT INTO `tb_prod_evaluation` VALUES (87, '宠物喜欢吃', 49, 0, 0, 0, 1, 0, '2022-04-06 11:27:51', '2022-04-06 11:27:51', 0);
INSERT INTO `tb_prod_evaluation` VALUES (89, '我也买了，家里宠物喜欢吃', 50, 0, 0, 0, 2, 0, '2022-04-06 14:31:09', '2022-04-06 14:31:09', 0);
INSERT INTO `tb_prod_evaluation` VALUES (90, '这个很好，我也给宠物买过', 53, 0, 0, 0, 2, 0, '2022-04-06 14:36:52', '2022-04-06 14:36:52', 0);
INSERT INTO `tb_prod_evaluation` VALUES (91, '是的，我也给宠物买过，非常好，下次还买！', 53, 90, 2, 0, 3, 0, '2022-04-06 14:43:47', '2022-04-06 14:43:47', 0);
INSERT INTO `tb_prod_evaluation` VALUES (92, '很棒！ ', 48, 0, 0, 0, 3, 0, '2022-04-06 14:53:50', '2022-04-06 14:53:50', 0);
INSERT INTO `tb_prod_evaluation` VALUES (93, '我也买过，很棒', 49, 0, 0, 0, 1, 0, '2022-04-06 14:56:06', '2022-04-06 14:56:06', 0);
INSERT INTO `tb_prod_evaluation` VALUES (94, '我家狗子也喜欢吃，吃了一个星期，现在长得很壮实', 49, 87, 1, 0, 1, 0, '2022-04-06 14:56:44', '2022-04-06 14:56:44', 0);
INSERT INTO `tb_prod_evaluation` VALUES (95, '是呀，我家宠物也是', 50, 89, 2, 0, 1, 0, '2022-04-06 14:57:34', '2022-04-06 14:57:34', 0);
INSERT INTO `tb_prod_evaluation` VALUES (101, '真的吗？', 49, 87, 1, 0, 2, 0, '2022-04-07 09:43:08', '2022-04-07 09:43:08', 0);
INSERT INTO `tb_prod_evaluation` VALUES (102, '1', 49, 87, 1, 0, 2, 0, '2022-04-07 09:46:53', '2022-04-07 09:46:53', 0);
INSERT INTO `tb_prod_evaluation` VALUES (103, '1', 49, 87, 1, 0, 2, 0, '2022-04-07 09:47:12', '2022-04-07 09:47:12', 0);
INSERT INTO `tb_prod_evaluation` VALUES (104, '2', 49, 87, 1, 0, 2, 0, '2022-04-07 09:49:27', '2022-04-07 09:49:27', 0);
INSERT INTO `tb_prod_evaluation` VALUES (105, '哦哦', 48, 0, 0, 0, 3, 0, '2022-04-07 10:06:29', '2022-04-07 10:06:29', 0);
INSERT INTO `tb_prod_evaluation` VALUES (106, '为什么', 50, 89, 2, 0, 3, 0, '2022-04-07 10:08:35', '2022-04-07 10:08:35', 0);
INSERT INTO `tb_prod_evaluation` VALUES (107, '1', 49, 87, 1, 0, 2, 0, '2022-04-07 10:17:21', '2022-04-07 10:17:21', 0);
INSERT INTO `tb_prod_evaluation` VALUES (108, '你好', 49, 93, 1, 0, 2, 0, '2022-04-07 10:17:37', '2022-04-07 10:17:37', 0);
INSERT INTO `tb_prod_evaluation` VALUES (109, '啊啊', 49, 93, 1, 0, 2, 0, '2022-04-07 10:18:45', '2022-04-07 10:18:45', 0);
INSERT INTO `tb_prod_evaluation` VALUES (110, '1', 49, 93, 1, 0, 2, 0, '2022-04-07 10:22:31', '2022-04-07 10:22:31', 0);
INSERT INTO `tb_prod_evaluation` VALUES (111, '你好', 49, 87, 1, 0, 3, 0, '2022-04-07 10:27:27', '2022-04-07 10:27:27', 0);
INSERT INTO `tb_prod_evaluation` VALUES (112, 'aaa', 49, 87, 1, 0, 2, 0, '2022-04-07 10:39:59', '2022-04-07 10:39:59', 0);
INSERT INTO `tb_prod_evaluation` VALUES (113, '啊VS方式', 49, 93, 1, 0, 2, 0, '2022-04-07 10:43:29', '2022-04-07 10:43:29', 0);
INSERT INTO `tb_prod_evaluation` VALUES (114, '测试', 49, 0, 0, 0, 2, 0, '2022-04-07 10:50:23', '2022-04-07 10:50:23', 0);
INSERT INTO `tb_prod_evaluation` VALUES (115, '你好', 49, 114, 2, 0, 3, 0, '2022-04-07 10:51:42', '2022-04-07 10:51:42', 0);
INSERT INTO `tb_prod_evaluation` VALUES (116, '今天又买了1000份', 50, 89, 2, 0, 2, 0, '2022-04-07 10:54:44', '2022-04-07 10:54:44', 0);
INSERT INTO `tb_prod_evaluation` VALUES (117, '宠物很喜欢\n', 50, 89, 2, 0, 3, 0, '2022-04-07 10:55:40', '2022-04-07 10:55:40', 0);
INSERT INTO `tb_prod_evaluation` VALUES (118, '非常好', 56, 0, 0, 0, 1, 0, '2022-05-04 08:13:11', '2022-05-04 08:13:11', 0);
INSERT INTO `tb_prod_evaluation` VALUES (119, '谢谢', 56, 118, 1, 0, 37, 1, '2022-05-04 08:14:09', '2022-05-04 08:14:09', 0);
INSERT INTO `tb_prod_evaluation` VALUES (120, '非常好', 58, 0, 0, 0, 1, 0, '2022-05-04 08:40:45', '2022-05-04 08:40:45', 0);
INSERT INTO `tb_prod_evaluation` VALUES (121, '谢谢', 58, 120, 1, 0, 37, 1, '2022-05-04 08:41:03', '2022-05-04 08:41:03', 0);
INSERT INTO `tb_prod_evaluation` VALUES (122, '非常好吃', 59, 0, 0, 0, 1, 0, '2022-05-04 11:34:20', '2022-05-04 11:34:20', 0);
INSERT INTO `tb_prod_evaluation` VALUES (123, '谢谢评论', 59, 122, 1, 0, 37, 1, '2022-05-04 11:34:35', '2022-05-04 11:34:35', 0);
INSERT INTO `tb_prod_evaluation` VALUES (124, '1', 52, 0, 0, 0, 1, 0, '2022-05-06 04:12:56', '2022-05-06 04:12:56', 0);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'ybuser' COMMENT '角色',
  `avatar_image` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/profile/upload/2022/03/04/782e5b08-916e-4126-ae75-4f95c542048b.jpg' COMMENT '头像',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `num` bigint(0) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, '张三', '一般用户', '123456', '18269111831', 'ybuser', '/profile/upload/2022/05/07/header04.jpeg', '2022-03-05 15:51:21', 1);
INSERT INTO `tb_user` VALUES (2, '李四', '一般用户', '123456', '18269111832', 'ybuser', '/profile/upload/2022/05/07/header03.jpeg', '2022-03-05 16:06:24', 1);
INSERT INTO `tb_user` VALUES (3, '王五', '一般用户', '123456', '18269111833', 'ybuser', '/profile/upload/2022/05/07/header02.jpeg', '2022-03-05 18:34:11', 1);
INSERT INTO `tb_user` VALUES (4, '赵六宠物粮超市', '商家用户', '123456', '18269111834', 'sjuser', '/profile/upload/2022/05/07/header.jpeg', '2022-03-05 13:43:18', 1);
INSERT INTO `tb_user` VALUES (27, '系统客服', '系统客服', '123456', '18269111835', 'ybadmin', '/profile/upload/2022/05/07/header01.jpeg', '2022-03-15 15:46:05', 1);
INSERT INTO `tb_user` VALUES (37, '健康猫粮', '商家用户', '123456', '18269111836', 'sjuser', '/profile/upload/2022/05/07/header.jpg', '2022-04-09 10:15:16', 1);

SET FOREIGN_KEY_CHECKS = 1;
