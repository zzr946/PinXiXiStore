/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50550
Source Host           : localhost:3306
Source Database       : pinxixi

Target Server Type    : MYSQL
Target Server Version : 50550
File Encoding         : 65001

Date: 2019-09-19 17:53:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `aid` varchar(255) NOT NULL,
  `adminaccount` varchar(255) DEFAULT NULL,
  `adminpassword` varchar(255) DEFAULT NULL,
  `adminname` varchar(255) DEFAULT NULL,
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '17371293389', '123321', '周虎', '你的爱好是什么？', '女人', '417ba883-2af0-4e6f-a173-36c6c988040d.jpg', null, null);

-- ----------------------------
-- Table structure for alertstore
-- ----------------------------
DROP TABLE IF EXISTS `alertstore`;
CREATE TABLE `alertstore` (
  `alertid` varchar(255) NOT NULL,
  `mid` varchar(255) DEFAULT NULL,
  `newname` varchar(255) DEFAULT NULL,
  `newstoreinfo` varchar(255) DEFAULT NULL,
  `newaddress` varchar(255) DEFAULT NULL,
  `checkdr` int(255) DEFAULT NULL,
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`alertid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alertstore
-- ----------------------------

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `gidlist` varchar(255) DEFAULT NULL,
  `goodstotallist` int(255) DEFAULT NULL,
  `goodsprice` double DEFAULT NULL,
  `reserved1` int(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('4df22784-ad7d-4eae-a999-8a7161b2f25c', '9a13948c-2e23-4522-9d6e-bab90f107d62', 'ed0c8cbe-db68-4068-9b33-1bce748367ad', '1', '12', '1', null, null, null, null);
INSERT INTO `cart` VALUES ('5a6eb09b-f1fa-4c33-b884-34ee2b15e6ef', '9a13948c-2e23-4522-9d6e-bab90f107d62', 'cea27ab4-7460-4ae3-b1a3-46405a496b97', '1', '90', '1', null, null, null, null);
INSERT INTO `cart` VALUES ('5b8e4dbc-417c-4d4a-bc2a-13501cc2c895', '9a13948c-2e23-4522-9d6e-bab90f107d62', '9b47cd67-efa7-484a-89a8-bb8aa115b489', '1', '69', '1', null, null, null, null);
INSERT INTO `cart` VALUES ('8e180422-d90f-42fe-9031-afa0cbcea1f7', '9a13948c-2e23-4522-9d6e-bab90f107d62', '41f714b0-3c45-4d86-9d73-48741edfd159', '1', '90', '1', null, null, null, null);
INSERT INTO `cart` VALUES ('f1c37917-cb58-4425-ab6c-533873f189fb', '9a13948c-2e23-4522-9d6e-bab90f107d62', '60274899-75eb-45b8-8470-71f13c1ef8e7', '4', '90', '1', null, null, null, null);

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `gid` varchar(255) DEFAULT NULL,
  `collecttime` varchar(255) DEFAULT NULL,
  `collectdr` int(11) DEFAULT NULL,
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collection
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `cid` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `gid` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `subtime` varchar(255) DEFAULT NULL,
  `commentdr` int(11) DEFAULT NULL,
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('21a8b6a8-45f3-424e-b89a-f9da096f6a01', '9a13948c-2e23-4522-9d6e-bab90f107d62', '燃烧的大芹菜', 'ed0c8cbe-db68-4068-9b33-1bce748367ad', '真好吃', '2019-09-18 16:07:12', '0', null, null, null, null, null);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `gid` varchar(255) NOT NULL,
  `goodsnumber` varchar(255) DEFAULT NULL,
  `goodstotal` int(11) DEFAULT NULL,
  `mid` varchar(255) DEFAULT NULL,
  `goodsname` varchar(255) DEFAULT NULL,
  `goodsprice` double(20,0) DEFAULT NULL,
  `discountprice` double(20,0) DEFAULT NULL,
  `goodssell` int(11) DEFAULT NULL,
  `goodsdr` int(11) DEFAULT NULL,
  `goodsinfo` varchar(255) DEFAULT NULL,
  `goodsdiscount` int(11) DEFAULT NULL,
  `goodsimage` varchar(255) DEFAULT NULL,
  `sid` varchar(255) DEFAULT NULL,
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('0aa1631e-7e8d-48e8-9dea-9474540cd221', '3058573175', '500', '4b9c1ca0-16b6-4e55-91b7-2a03261dc12b', '肉类', '130', '90', '500', '0', '新鲜肉类食品    衣', '1', '0fbf924d-81e2-48d1-b788-665479e62ce6.jpg', '067b8a5d-aacb-4038-a6e1-6b6e991cd9b6', '大袋', null, null, null, null);
INSERT INTO `goods` VALUES ('0b5941e7-e3a2-4678-9278-effc15f8198e', '6846397684', '845', '9143ed2d-545b-4eb2-b494-a273c282600a', '冷库直发新鲜牛排', '60', '60', '456', '0', '食品生鲜 精选肉类', '0', '1247bb97-c28d-42bd-8071-028113d374ba.jpg', 'c60951fd-3967-46eb-b7ee-9428fcb5695f', '300g', null, null, null, null);
INSERT INTO `goods` VALUES ('0e779887-83b5-4358-a61c-7f11d5aa9970', '1866859019', '458', '728d476e-6e63-4fcc-8da0-8bdc5a723d0e', '女士牛皮尖角小皮鞋', '299', '299', '563', '0', '女士皮鞋', '0', '708ecc52-5933-46ee-8439-bc3ccff0dabb.jpg', 'f75e7825-7bc1-401e-b83b-d4c8bace7a97', '35', null, null, null, null);
INSERT INTO `goods` VALUES ('2805a7d0-2019-4242-a799-b1593dad4085', '8973640372', '500', '7f0c1480-acb5-472a-a754-1da5575888a9', '奶粉', '560', '90', '600', '0', '婴儿奶粉 辅食    衣', '1', 'f50da47c-d7e4-4b21-87b5-23bf127d9b5c.jpg', 'f41872a6-d051-4c3c-9950-59a61f8070e5', '999g', null, null, null, null);
INSERT INTO `goods` VALUES ('3bfb2cd4-f361-41d0-ac9e-242697f1af68', '0158839249', '500', 'a6c0081d-6935-4683-a4e9-533185d75774', '抹布', '560', '90', '700', '0', '抹布 清洁    衣', '1', '66726420-d54f-4d43-b9dc-eb024b1c3e7b.jpg', '2a2e0234-ef1f-4461-8c46-75ecbebf88f9', '20cm*20cm', null, null, null, null);
INSERT INTO `goods` VALUES ('41f714b0-3c45-4d86-9d73-48741edfd159', '8340783723', '500', 'a784f942-25e2-4a4e-a68c-900f5ddf9bc5', '进口食品', '670', '90', '800', '0', '进口 休闲食品   衣', '1', 'dc304c5a-3f0c-424d-8718-d08b6762621d.jpg', 'dedc4675-dfda-49dc-aa32-2500e3ccbdc2', '300g', null, null, null, null);
INSERT INTO `goods` VALUES ('4e9c7c89-b0e3-453f-90c8-b74054f7a249', '7171872243', '1525', 'ce60c25b-7a44-4dc6-85fb-9c3370b2eb8d', '果园直发 新鲜脐橙', '60', '60', '458', '0', '水果 食品', '0', 'd65c29a1-f06b-4f47-b62d-f86268d58f3d.jpg', '6bdee63a-9769-4e4b-86ec-de9e6aac06eb', '3Kg', null, null, null, null);
INSERT INTO `goods` VALUES ('60274899-75eb-45b8-8470-71f13c1ef8e7', '9578468285', '500', '031f98b3-0f2b-42b6-84c5-f183e0bfdfe1', '旅行箱', '299', '90', '900', '0', '多功能旅行箱 大容量 居家旅行必备', '1', '73da70cc-ec27-4b66-b4e6-407ba41e39e1.jpg', '183cce64-fb44-4aac-99d4-53f8270a2f3e', '24寸', null, null, null, null);
INSERT INTO `goods` VALUES ('6431bc7f-0122-403f-b7e2-7c9fa5b1099f', '0137654635', '500', '5cf8b001-06fa-4969-8c35-b1f1c200b3fa', '婴儿衣服', '234', '90', '1000', '0', '婴儿衣服', '1', 'b342b8cc-070f-4165-b955-9eb7171d7fa0.jpg', 'f33fd768-5039-438c-a88a-e1328950c8ae', '90号', null, null, null, null);
INSERT INTO `goods` VALUES ('715853e3-5da2-4684-b7a7-6962517c637a', '6910556808', '1000', '8a8efd5f-e624-43fc-87df-6e3d6eadb9b4', '苹果', '5', '5', '0', '0', '新鲜水果', '0', '43dc937b-a365-401e-9180-3b86ad43fa34.jpg', '1877ca10-728b-4e09-bd2f-77c069972969', '大箱', null, null, null, null);
INSERT INTO `goods` VALUES ('72d5c541-759c-43b5-b573-816aadc21ed6', '5356027255', '865', 'c385564d-af02-4d95-82ba-04eba0be1907', '时尚女士连衣裙', '160', '160', '775', '0', '潮流女装 女士衣服', '0', 'd42964a5-b34a-40ff-bd1f-83040ef01f05.jpg.jpg', '8de4b5bd-93a5-45c3-90cd-3c2634a4c506', 'M', null, null, null, null);
INSERT INTO `goods` VALUES ('783cf92a-32fc-4f56-8f7e-0365f2261570', '1539347160', '456', '9143ed2d-545b-4eb2-b494-a273c282600a', '发过进口银鳕鱼', '129', '129', '568', '0', '食品生鲜 精选肉类', '0', '1247bb97-c28d-42bd-8071-028113d374ba.jpg.jpg', 'c60951fd-3967-46eb-b7ee-9428fcb5695f', '1Kg', null, null, null, null);
INSERT INTO `goods` VALUES ('7dc45dff-2e8b-47ff-bdac-1017a2a80d00', '5246050322', '500', '530a2ef7-676e-4691-9a8b-fbb7e308ac69', '女士包包', '444', '90', '100', '0', '女士包包 手提包   衣', '1', '13569365-c606-4f3c-9508-0b57c61cd61a.jpg', '6c81f5b3-4c8d-4c4c-bcef-7d5bb0eb83c5', '6L', null, null, null, null);
INSERT INTO `goods` VALUES ('7e101e30-3baa-4987-af1f-27149b269715', '6237894579', '268', '2b4aa6f8-d070-46d9-ba2b-9d06db7b939b', '女士宽脚牛仔裤', '169', '109', '356', '0', '女士下装 衣服', '0', '8f830e93-5dee-4327-ac00-ec5c97d3c0a9.jpg', 'c0162f29-032c-4487-9dc6-44a167ca54d1', 'M', null, null, null, null);
INSERT INTO `goods` VALUES ('8071dbad-5d9e-4f5b-8fbb-685916016d23', '5170189313', '500', '68aa2f4d-d190-46c9-a1da-36e7beaf8e2e', '男士上衣', '456', '90', '250', '0', '男士上衣，衣服 男士上装    衣', '1', 'cb7d0e0a-890b-4ca8-99ea-3cc1fc58933c.jpg', '87fec9aa-0d91-403f-ad0c-639fb5fe4024', 'XL', null, null, null, null);
INSERT INTO `goods` VALUES ('87cb2b99-0396-4d65-bbdb-a64a010d0516', '8181994161', '256', '2b4aa6f8-d070-46d9-ba2b-9d06db7b939b', '女士休闲套装', '199', '99', '586', '0', '女士下装 衣服', '0', 'e0618769-4252-4593-92c2-bcfdf8f37fbd.jpg', 'c0162f29-032c-4487-9dc6-44a167ca54d1', 'M', null, null, null, null);
INSERT INTO `goods` VALUES ('943315da-bc04-42a8-8621-68faf583bbf5', '4049635897', '562', '68aa2f4d-d190-46c9-a1da-36e7beaf8e2e', '男士POLO衫', '186', '89', '568', '0', '男士上衣 衣服男士上装', '0', '5f01aed4-25a7-4eee-b8ec-dae631c16e1f.jpg', '87fec9aa-0d91-403f-ad0c-639fb5fe4024', 'XL', null, null, null, null);
INSERT INTO `goods` VALUES ('9b47cd67-efa7-484a-89a8-bb8aa115b489', '1608304201', '500', '68aa2f4d-d190-46c9-a1da-36e7beaf8e2e', '花花公子精品男士T恤', '129', '69', '225', '0', '精品男装 男士服装', '0', 'fa27d76a-6cd3-446c-b4cc-1a1ef685935a.jpg', 'dad6d190-8bb6-4f1a-bf30-b6feb2111a7b', 'XL', null, null, null, null);
INSERT INTO `goods` VALUES ('9c6a7cc8-5f2b-4dd0-a74c-4d4f88a7bd93', '3387871309', '500', '2c3cda99-913b-47c3-add0-5ff612422eb5', '体育器材', '876', '90', '450', '0', '体育器材 健身 运动 锻炼 运动衣   衣', '1', '89e34df4-493d-4a30-8870-9f99fed7b0a4.jpg', 'd97a11ca-c2a7-4d62-9113-a752b3494adc', '组合套装', null, null, null, null);
INSERT INTO `goods` VALUES ('9dcef34e-40c1-4185-a86d-d0870be2ac21', '9920091726', '500', '2b4aa6f8-d070-46d9-ba2b-9d06db7b939b', '女士小碎花连衣裙', '199', '99', '568', '0', '潮流女装 女士衣服', '0', '3d5f633a-98f3-44fb-9ccd-2da2a9636984.jpg', '4c76dff3-cd93-44ef-b4d0-126bc5049900', 'L', null, null, null, null);
INSERT INTO `goods` VALUES ('a42b6d73-59c6-4cd9-b3a9-02ab82f5394d', '8131324881', '500', 'a40685f9-7440-4ddc-bd5e-9d6c3b91f55d', '训练服', '345', '90', '300', '0', '运动服装 户外 运动  运动衣    衣', '1', 'e33d70d2-d3b3-4d8f-999e-7e912a8765b6.png', 'd6b7f235-bd7c-43e8-987b-3a71e0f0ecff', 'XL', null, null, null, null);
INSERT INTO `goods` VALUES ('a5aa79a4-7062-43a0-a248-4206f9a9f051', '1127453264', '500', '51878a36-7b1a-4eac-b4cc-d9336e3adc06', '女士上装', '234', '90', '100', '0', '女士下衣，衣服   衣', '1', 'a66f8eca-cad5-46f2-a8c8-36eb76a080f0.jpg', 'bb7d566b-5a1f-4773-8966-f8fabe0f7116', 'L', null, null, null, null);
INSERT INTO `goods` VALUES ('a78da4fd-e18f-4cf7-b034-4378a3bb1fa8', '5246755742', '500', '0cdee65d-0cd4-4da1-b12a-08a7070967c0', '收纳箱', '450', '90', '1000', '0', '家居 箱子 收纳   衣', '1', '4bb57fdd-2b5f-4c43-b553-63404c209e32.jpg', '2b175b20-4d55-41cc-a067-407426eabcf9', '20L', null, null, null, null);
INSERT INTO `goods` VALUES ('adc481e1-2159-4d02-8b1f-354f55af3f2d', '9852790783', '500', '9f92f464-446b-4c3b-8f59-bea2bb1e96b6', '口红', '345', '92', '1001', '0', '女士口红   衣', '1', 'ee8a5d53-b089-4fba-b910-a979fcdc69e8.jpg', '1b1828c8-3330-4143-8335-3a61361848a8', '色号:401', null, null, null, null);
INSERT INTO `goods` VALUES ('ae906f21-2a90-4246-9232-d8b186e9eb7f', '9960140961', '500', 'cdca1eb4-1930-4502-a778-d0409de4d156', '碗', '340', '90', '999', '0', '锅碗瓢盆   衣', '1', 'a2305da9-a018-476c-837e-776b2fc7c53c.png', '769cfa38-f0f6-4e7d-84a5-3d6715fa91bb', '10cm', null, null, null, null);
INSERT INTO `goods` VALUES ('aef98a1c-2f35-4b04-b92f-4b492118d046', '5577540796', '500', 'f6210fc1-1738-48f4-8d46-e24dd0328ac1', '男士下装', '156', '90', '801', '0', '男士下装，裤子 衣服 男士上装', '1', '7f7bff7d-a5f7-4cb2-8f11-836fab32602f.jpg', '71c24457-308d-4e60-8a71-bfbaf544f10a', 'XL', null, null, null, null);
INSERT INTO `goods` VALUES ('b0f17e16-d45f-4b8e-94ee-b64cda8f1070', '7431053917', '500', 'd4d61a79-249b-466c-98cf-a80de3405658', '奶瓶', '340', '90', '701', '0', '婴儿奶瓶', '0', 'a9e6b285-fa0e-457f-80b9-c03863ce6e84.jpg', '1fb9bfc0-447e-42df-8f21-29c38b119252', '500毫升', null, null, null, null);
INSERT INTO `goods` VALUES ('b1729fbf-38cf-4fb7-aaf1-3ac3b67db474', '6012447364', '956', 'b1729fbf-38cf-4fb7-aaf1-3ac3b67db474', '多功能大容量旅行箱', '399', '199', '756', '0', '家居生活 生活 多功能箱包 ', '0', 'bb8cf011-ce75-4759-a293-50313aa62b2b.jpg', '2d7667ac-a17c-4cc2-8660-803974729637', '24寸', null, null, null, null);
INSERT INTO `goods` VALUES ('ca724db4-aaf3-4143-87a1-2e07191cab36', '2921354225', '500', '6255f96d-8d3b-4656-acc8-bf9ba1c8b851', '休闲零食', '340', '90', '503', '0', '休闲零食', '0', '2cbb00ec-4608-4069-82cd-97b79d35ceb1.jpg', 'd92f33a0-0e7d-4213-b003-41d71f1098bf', '60g', null, null, null, null);
INSERT INTO `goods` VALUES ('cd6ee7eb-2450-435b-89f5-323f2c27993b', '8924247598', '500', '7f4f5701-b09f-4f2c-9226-f1b8d7e5d33b', '三年高考五年模拟', '540', '92', '602', '0', '资料书 教科书 图书 学习资料', '0', '17110a4e-8e8c-4a2c-8d57-db85d0b5ffef.jpg', '67427316-d6c4-4b8d-9294-f3bf0ec2cd96', '单册', null, null, null, null);
INSERT INTO `goods` VALUES ('cea27ab4-7460-4ae3-b1a3-46405a496b97', '3936172139', '500', 'b2201add-fa8d-4ae5-87c2-a84d7f907410', '床上四件套', '156', '90', '404', '0', '家纺，床上用品 床单被套', '0', '8a50fde7-d7a8-4ef7-82f3-ef20a89d8970.jpg', '125aef79-712c-471b-870b-63d1f89fdf9c', '四件套', null, null, null, null);
INSERT INTO `goods` VALUES ('cf7f5fd9-89e8-418a-b810-3b563f20d115', '5108796725', '568', '17f186e1-a70c-4a2d-bed6-e309999bef8b', '新鲜无刺三文鱼', '99', '49', '589', '0', '食品生鲜 精选肉类', '0', '1c6d11c8-9abf-41ca-b005-e4dc98f594df.png', 'c60951fd-3967-46eb-b7ee-9428fcb5695f', '1碟', null, null, null, null);
INSERT INTO `goods` VALUES ('d9ccaadc-130f-4090-93fa-3e4e3469a9e7', '6860886548', '500', '5935bdce-dd58-4d88-8940-2963ee0f8ca5', '运动鞋', '357', '90', '305', '0', '户外 运动 运动鞋', '0', '10ef59cf-f33d-4a42-81e5-3e297291b13f.jpg', '12733cc8-dacc-457d-96f1-05bb26869d86', '41码', null, null, null, null);
INSERT INTO `goods` VALUES ('e2be00c2-de7b-4002-b97c-0cd07b37270f', '9423775941', '520', 'f41b8335-6345-41fa-8bde-10dc43454e9c', '泰国金枕榴莲', '158', '99', '569', '0', '水果 食品', '0', '14d39278-b758-417e-89bb-88a5d061dc12.jpg', 'f011625a-a2ba-4913-a629-237f2eb1fe43', '5Kg', null, null, null, null);
INSERT INTO `goods` VALUES ('e4f091fe-5189-423f-a163-3ad50eca0966', '8984440103', '623', '17f186e1-a70c-4a2d-bed6-e309999bef8b', '精选少刺带鱼', '99', '10', '425', '0', '食品生鲜 精选肉类', '0', '0bc7d007-5774-4bbf-ba73-7f23b1066da7.jpg', 'c60951fd-3967-46eb-b7ee-9428fcb5695f', '5条', null, null, null, null);
INSERT INTO `goods` VALUES ('ed0c8cbe-db68-4068-9b33-1bce748367ad', '5092827332', '1000', 'f3cb9401-9006-45d5-ab94-fbb50529a588', '香蕉', '12', '12', '0', '0', '新鲜水果', '1', 'a6a34d2c-2b3f-4e7f-9c23-b190c427bc12.jpg', '5f79f032-1185-48f8-a71e-55784e8acc0b', '大箱', null, null, null, null);
INSERT INTO `goods` VALUES ('f40b8730-9193-4262-8413-27db2435f656', '5731684042', '8645', 'd3f305c0-b93f-4deb-99a4-1cb0817b7c93', '时尚运动跑鞋', '199', '199', '456', '0', '男士运动鞋 鞋服', '0', '4e3ff38c-69d8-4313-a125-6958e919501e.jpg', '0304ccca-9786-404a-96d8-199a2792b88e', '41码', null, null, null, null);
INSERT INTO `goods` VALUES ('fcba3d6a-c6a0-4016-b859-59c6380658fa', '6976328104', '500', '1f9daaee-ee16-40fa-a2b1-42c33ec9ab53', '电饭锅 ', '432', '90', '236', '0', '电饭锅 煮饭', '0', 'e0eb4b1f-11f6-474a-83fd-1eb0c1674365.jpg', 'f5571320-651b-49ef-b5bb-c3308b50771d', '2L', null, null, null, null);
INSERT INTO `goods` VALUES ('fd90a3e7-1b5b-4f7b-9724-e604227f01d3', '2575168230', '956', 'c385564d-af02-4d95-82ba-04eba0be1907', '女士碎花连衣裙', '130', '130', '156', '0', '潮流女装 女士衣服', '0', '5c4ae4b5-50ec-48e4-92e9-a9a78b03144f.jpg', '25955dc1-43c0-4304-a4d3-5a7c76705798', 'M', null, null, null, null);
INSERT INTO `goods` VALUES ('fd91c485-725b-4327-9e9d-888ec14ede03', '8975066373', '500', '75236dcf-862e-4c8a-b635-434880f01e07', '水杯', '260', '90', '136', '0', '家居 水杯 喝水', '0', '23d77c35-5cc3-4b61-838f-f334f0f4ad23.jpg', 'da814df6-3a17-4b31-83fc-539b9ef23eac', '500毫升', null, null, null, null);
INSERT INTO `goods` VALUES ('fdd9f014-0895-42c1-9e74-d5993bd34ed1', '9464231000', '500', '2b4aa6f8-d070-46d9-ba2b-9d06db7b939b', '女士下装', '245', '90', '222', '0', '女士下装 衣服', '0', '72b4ff7f-eee8-42b9-a5dc-7af8e68dcee4.jpg', 'c0162f29-032c-4487-9dc6-44a167ca54d1', 'M', null, null, null, null);

-- ----------------------------
-- Table structure for join
-- ----------------------------
DROP TABLE IF EXISTS `join`;
CREATE TABLE `join` (
  `id` varchar(255) NOT NULL,
  `storeid` varchar(255) DEFAULT NULL,
  `gid` varchar(255) DEFAULT NULL,
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of join
-- ----------------------------

-- ----------------------------
-- Table structure for merchant
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant` (
  `mid` varchar(255) NOT NULL,
  `mphone` varchar(255) DEFAULT NULL,
  `mpassword` varchar(255) DEFAULT NULL,
  `merchantdr` int(255) DEFAULT NULL,
  `sid` varchar(255) DEFAULT NULL,
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of merchant
-- ----------------------------
INSERT INTO `merchant` VALUES ('f3cb9401-9006-45d5-ab94-fbb50529a588', '17343319742', 'E10ADC3949BA59ABBE56E057F20F883E', '2', 'b0659842-13ac-41a8-98cf-2ef978aaa3cf', null, null, null, null, null);

-- ----------------------------
-- Table structure for order1
-- ----------------------------
DROP TABLE IF EXISTS `order1`;
CREATE TABLE `order1` (
  `id` varchar(255) NOT NULL,
  `ordernumber` varchar(255) DEFAULT NULL,
  `gidlist` varchar(255) DEFAULT NULL,
  `goodstotallist` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `uadname` varchar(255) DEFAULT NULL,
  `account` double DEFAULT NULL,
  `uadid` varchar(255) DEFAULT NULL,
  `mid` varchar(255) DEFAULT NULL,
  `paydr` int(11) DEFAULT NULL,
  `orderdr` int(11) DEFAULT NULL,
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order1
-- ----------------------------
INSERT INTO `order1` VALUES ('01247207-6232-4c6b-9d93-ddc98e36df01', '1236931805', '9b47cd67-efa7-484a-89a8-bb8aa115b489', '1', '赵志然', '赵志然', '609', '186311df-7a8e-482a-9875-d15b5fb5dc10', '68aa2f4d-d190-46c9-a1da-36e7beaf8e2e', '0', '1', null, null, null, null, null);
INSERT INTO `order1` VALUES ('206034e7-8130-4142-ab02-ed4d9b26ffa4', '1236931805', 'cea27ab4-7460-4ae3-b1a3-46405a496b97', '1', '赵志然', '赵志然', '609', '186311df-7a8e-482a-9875-d15b5fb5dc10', 'b2201add-fa8d-4ae5-87c2-a84d7f907410', '0', '1', null, null, null, null, null);
INSERT INTO `order1` VALUES ('71664e9c-2a61-47af-a1a0-e8fbd4a16ede', '1236931805', '60274899-75eb-45b8-8470-71f13c1ef8e7', '4', '赵志然', '赵志然', '609', '186311df-7a8e-482a-9875-d15b5fb5dc10', '031f98b3-0f2b-42b6-84c5-f183e0bfdfe1', '0', '1', null, null, null, null, null);
INSERT INTO `order1` VALUES ('f890f306-053c-4eaf-9067-cb0865a3970d', '7261607716', 'ed0c8cbe-db68-4068-9b33-1bce748367ad', '5', '赵志然', '赵志然', '609', '186311df-7a8e-482a-9875-d15b5fb5dc10', 'f3cb9401-9006-45d5-ab94-fbb50529a588', '0', '1', null, null, null, null, null);
INSERT INTO `order1` VALUES ('fa988e90-d332-4de2-8076-0561b96d6b2d', '1236931805', '41f714b0-3c45-4d86-9d73-48741edfd159', '1', '赵志然', '赵志然', '609', '186311df-7a8e-482a-9875-d15b5fb5dc10', 'a784f942-25e2-4a4e-a68c-900f5ddf9bc5', '0', '1', null, null, null, null, null);

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `rid` varchar(255) NOT NULL,
  `cid` varchar(255) DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `formid` varchar(255) DEFAULT NULL,
  `storename` varchar(255) DEFAULT NULL,
  `gid` varchar(255) DEFAULT NULL,
  `mcontent` varchar(255) DEFAULT NULL,
  `subtime` varchar(255) DEFAULT NULL,
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `gid` varchar(255) DEFAULT NULL,
  `goodsname` varchar(255) DEFAULT NULL,
  `mid` varchar(255) DEFAULT NULL,
  `cause` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `reporttime` varchar(255) DEFAULT NULL,
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES ('00aa21a0-2fad-4b8c-917a-7d6fc4fe289d', '9a13948c-2e23-4522-9d6e-bab90f107d62', 'ed0c8cbe-db68-4068-9b33-1bce748367ad', '香蕉', 'f3cb9401-9006-45d5-ab94-fbb50529a588', '有毒', '有毒', '2019-09-18 16:08:38', null, null, null, null, null);
INSERT INTO `report` VALUES ('77688d85-b4e7-41d0-9e90-ffd3d90df714', '9a13948c-2e23-4522-9d6e-bab90f107d62', 'ed0c8cbe-db68-4068-9b33-1bce748367ad', '香蕉', 'f3cb9401-9006-45d5-ab94-fbb50529a588', '毒死人了', '毒死人了', '2019-09-18 16:10:03', null, null, null, null, null);
INSERT INTO `report` VALUES ('eaeaffc4-3796-4fbc-a7df-2b9438c57364', '9a13948c-2e23-4522-9d6e-bab90f107d62', 'ed0c8cbe-db68-4068-9b33-1bce748367ad', '香蕉', 'f3cb9401-9006-45d5-ab94-fbb50529a588', '真的有毒\r\n', '真的有毒', '2019-09-18 16:09:49', null, null, null, null, null);

-- ----------------------------
-- Table structure for return1
-- ----------------------------
DROP TABLE IF EXISTS `return1`;
CREATE TABLE `return1` (
  `id` varchar(255) NOT NULL,
  `ordernumber` varchar(255) DEFAULT NULL,
  `returntype` varchar(255) DEFAULT NULL,
  `returncause` varchar(255) DEFAULT NULL,
  `returndetails` varchar(255) DEFAULT NULL,
  `returndr` int(11) DEFAULT NULL,
  `reserved1` double(255,0) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of return1
-- ----------------------------

-- ----------------------------
-- Table structure for sales
-- ----------------------------
DROP TABLE IF EXISTS `sales`;
CREATE TABLE `sales` (
  `id` varchar(255) NOT NULL,
  `gid` varchar(255) DEFAULT NULL,
  `goodsname` varchar(255) DEFAULT NULL,
  `goodsprice` double DEFAULT NULL,
  `discountprice` double DEFAULT NULL,
  `salesdr` int(11) DEFAULT NULL,
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sales
-- ----------------------------
INSERT INTO `sales` VALUES ('0d1f2f8f-659b-4fa0-a1c6-5e4628e72a5b', 'b0f17e16-d45f-4b8e-94ee-b64cda8f1070', '奶瓶', '340', '90', '0', null, null, null, null, null);
INSERT INTO `sales` VALUES ('241d669b-bfa3-4632-9f87-5c1ff26f9caa', 'f69b7b66-f7f6-4af3-a192-ccb9541909da', '水果', '320', '90', '0', null, null, null, null, null);
INSERT INTO `sales` VALUES ('29922732-f232-412c-b906-4c0f4c0732f5', 'cea27ab4-7460-4ae3-b1a3-46405a496b97', '床上四件套', '156', '90', '0', null, null, null, null, null);
INSERT INTO `sales` VALUES ('39de6a76-887d-4bb3-b38a-15eca56f1670', 'ca724db4-aaf3-4143-87a1-2e07191cab36', '休闲零食', '340', '90', '0', null, null, null, null, null);
INSERT INTO `sales` VALUES ('5762058f-685d-445e-9471-c4f72cb72d8f', 'fdd9f014-0895-42c1-9e74-d5993bd34ed1', '女士下装', '245', '90', '0', null, null, null, null, null);
INSERT INTO `sales` VALUES ('6a48a405-a74e-473a-aa48-9ee9a515a352', 'fd91c485-725b-4327-9e9d-888ec14ede03', '水杯', '260', '90', '0', null, null, null, null, null);
INSERT INTO `sales` VALUES ('6c2a1f6c-b416-4ff7-9978-e140fd853a62', 'cd6ee7eb-2450-435b-89f5-323f2c27993b', '三年高考五年模拟', '540', '92', '0', null, null, null, null, null);
INSERT INTO `sales` VALUES ('833602d6-4d51-42df-aecb-cba67419cfce', 'fcba3d6a-c6a0-4016-b859-59c6380658fa', '电饭锅 ', '432', '90', '0', null, null, null, null, null);
INSERT INTO `sales` VALUES ('d3239026-e05d-43a5-bc1d-408cf133bad1', '943315da-bc04-42a8-8621-68faf583bbf5', '男士POLO衫', '186', '89', '0', null, null, null, null, null);
INSERT INTO `sales` VALUES ('d9ccaadc-130f-4090-93fa-3e4e3469a9e7', 'd9ccaadc-130f-4090-93fa-3e4e3469a9e7', '运动鞋', '357', '90', '0', null, null, null, null, null);

-- ----------------------------
-- Table structure for sort
-- ----------------------------
DROP TABLE IF EXISTS `sort`;
CREATE TABLE `sort` (
  `sid` varchar(255) NOT NULL,
  `sortname` varchar(255) DEFAULT NULL,
  `sortinfo` varchar(255) DEFAULT NULL,
  `merchantid` varchar(255) DEFAULT NULL,
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sort
-- ----------------------------
INSERT INTO `sort` VALUES ('5f79f032-1185-48f8-a71e-55784e8acc0b', '水果', '好吃的水果', 'f3cb9401-9006-45d5-ab94-fbb50529a588', null, null, null, null, null);

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `sid` varchar(255) NOT NULL,
  `storename` varchar(255) DEFAULT NULL,
  `storelogo` varchar(255) DEFAULT NULL,
  `storeinfo` varchar(255) DEFAULT NULL,
  `storeaddress` varchar(255) DEFAULT NULL,
  `storedr` int(255) DEFAULT NULL,
  `aptitude` varchar(255) DEFAULT NULL,
  `subtime` varchar(255) DEFAULT NULL,
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES ('b0659842-13ac-41a8-98cf-2ef978aaa3cf', '唯品会', 'cee6146d-a03d-4920-ae09-2c69eea5ee3a.gif', 'asfsf', '大大大', '1', 'd9abfd04-cc06-480f-ab8c-12c7d7be54fb.jpg', '2019-09-18 15:50:10', null, null, null, null, null);

-- ----------------------------
-- Table structure for uaddress
-- ----------------------------
DROP TABLE IF EXISTS `uaddress`;
CREATE TABLE `uaddress` (
  `uadid` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `detailaddress` varchar(255) DEFAULT NULL,
  `addressdr` int(11) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `uadname` varchar(255) DEFAULT NULL,
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uadid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uaddress
-- ----------------------------
INSERT INTO `uaddress` VALUES ('186311df-7a8e-482a-9875-d15b5fb5dc10', '9a13948c-2e23-4522-9d6e-bab90f107d62', '湖北', '武汉市', '江夏区', '你猜啊。。。', '0', '17607103063', '赵志然', null, null, null, null, null);
INSERT INTO `uaddress` VALUES ('8ef64f06-81c3-412b-9034-eefd98625784', '9a13948c-2e23-4522-9d6e-bab90f107d62', '宁夏', '银川市', '兴庆区', '嘿嘿嘿', '1', '17788889999', '大头', null, null, null, null, null);
INSERT INTO `uaddress` VALUES ('d19fbdf8-df7b-4983-a86d-22114ac8dbcc', '9a13948c-2e23-4522-9d6e-bab90f107d62', '内蒙古', '呼和浩特市', '新城区', '嘻嘻嘻', '2', '17789899898', '小头', null, null, null, null, null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` varchar(255) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `userdr` int(11) DEFAULT NULL,
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('09f5e50d-7fab-4f13-bcad-f8b169b14e6e', '大头', '96E79218965EB72C92A549DD5A330112', '17371293396', '40e01597-2587-4ee2-a163-869797d129e2.jpg', '李大头', '男', '0', null, null, null, null, null);
INSERT INTO `user` VALUES ('8e4c71be-104c-4b80-90bb-29fc0e8f02ff', '小胖砸', '96E79218965EB72C92A549DD5A330112', '15171838290', 'e0be308f-7094-42b6-a9f5-672c8d3ce4c0.jpg', '曹宇珂', '女', '0', null, null, null, null, null);
INSERT INTO `user` VALUES ('97192b3b-96eb-4ec0-a392-893d7b63767e', null, '96E79218965EB72C92A549DD5A330112', '17607110224', null, null, null, '0', null, null, null, null, null);
INSERT INTO `user` VALUES ('9a13948c-2e23-4522-9d6e-bab90f107d62', '燃烧的大白菜', 'E3CEB5881A0A1FDAAD01296D7554868D', '17607103063', '50cb2cd5-1f62-450f-b721-ec14149066dd.jpg', '赵志然', '男', '0', null, null, null, null, null);
INSERT INTO `user` VALUES ('a3adff11-20cb-400c-bef0-9d360787ede2', '大枪', '96E79218965EB72C92A549DD5A330112', '17343319742', '71d4105e-48ee-490a-b828-8a829b05f4ef.jpg', '李达强', '男', '0', null, null, null, null, null);
INSERT INTO `user` VALUES ('fd0966d6-35e7-4d86-8367-1100a4f3398c', 'zh', 'E3CEB5881A0A1FDAAD01296D7554868D', '17371293389', 'd6712c3c-e54e-49d6-8148-663f79467560.jpg', '周虎', '男', '0', null, null, null, null, null);
