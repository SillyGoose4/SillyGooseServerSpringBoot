-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: 39.108.107.28    Database: SillyGoose
-- ------------------------------------------------------
-- Server version	5.7.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `talbum`
--

DROP TABLE IF EXISTS `talbum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `talbum` (
  `userId` int(11) NOT NULL COMMENT '用户id，外键',
  `picId` int(11) NOT NULL COMMENT '照片id,外键',
  KEY `userId` (`userId`),
  KEY `picId` (`picId`),
  CONSTRAINT `talbum_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `tuser` (`userId`),
  CONSTRAINT `talbum_ibfk_2` FOREIGN KEY (`picId`) REFERENCES `tpic` (`picId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `talbum`
--

LOCK TABLES `talbum` WRITE;
/*!40000 ALTER TABLE `talbum` DISABLE KEYS */;
/*!40000 ALTER TABLE `talbum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tcollecttime`
--

DROP TABLE IF EXISTS `tcollecttime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tcollecttime` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id，外键',
  `gooseSunT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Sun上次收集时间',
  `gooseWindT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Wind上次收集时间',
  `gooseRainT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Rain上次收集时间',
  `gooseCloudT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Cloud上次收集时间',
  `gooseDevilT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Devil上次收集时间',
  `gooseStarT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Star上次收集时间',
  KEY `userId` (`userId`),
  CONSTRAINT `tcollecttime_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `tuser` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tcollecttime`
--

LOCK TABLES `tcollecttime` WRITE;
/*!40000 ALTER TABLE `tcollecttime` DISABLE KEYS */;
INSERT INTO `tcollecttime` VALUES (9,'2018-06-19 16:18:31','2018-06-19 16:18:26','2018-06-19 21:52:35','2018-06-19 16:18:30','2018-06-19 21:47:02','2018-06-19 16:18:29'),(10,'2018-06-19 20:22:03','2018-06-19 15:28:14','2018-06-19 20:36:53','2018-06-19 20:22:00','2018-06-19 20:36:55','2018-06-19 20:22:06');
/*!40000 ALTER TABLE `tcollecttime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tgoose`
--

DROP TABLE IF EXISTS `tgoose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tgoose` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id，外键',
  `gooseENY` int(11) NOT NULL DEFAULT '0' COMMENT '鹅民币',
  `gooseSun` int(11) NOT NULL DEFAULT '0' COMMENT '已收集阳光',
  `gooseRain` int(11) NOT NULL DEFAULT '0' COMMENT '已收集雨',
  `gooseWind` int(11) NOT NULL DEFAULT '0' COMMENT '已收集风',
  `gooseCloud` int(11) NOT NULL DEFAULT '0' COMMENT '已收集云',
  `gooseDevil` int(11) NOT NULL DEFAULT '0' COMMENT '已收集恶',
  `gooseStar` int(11) NOT NULL DEFAULT '0' COMMENT '已收集星星',
  `gooseMap` int(11) DEFAULT NULL,
  KEY `userId` (`userId`),
  CONSTRAINT `tgoose_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `tuser` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tgoose`
--

LOCK TABLES `tgoose` WRITE;
/*!40000 ALTER TABLE `tgoose` DISABLE KEYS */;
INSERT INTO `tgoose` VALUES (9,600,0,0,0,0,0,0,NULL),(10,2400,0,0,0,0,0,0,NULL);
/*!40000 ALTER TABLE `tgoose` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tpic`
--

DROP TABLE IF EXISTS `tpic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tpic` (
  `picId` int(11) NOT NULL AUTO_INCREMENT COMMENT '照片id',
  `picBelong` varchar(20) NOT NULL COMMENT '照片归属地',
  `picLevel` char(2) NOT NULL DEFAULT 'E' COMMENT '照片层级',
  `picUrl` char(140) NOT NULL COMMENT '照片url',
  `picDescribe` varchar(140) DEFAULT '(无)' COMMENT '照片描述',
  PRIMARY KEY (`picId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tpic`
--

LOCK TABLES `tpic` WRITE;
/*!40000 ALTER TABLE `tpic` DISABLE KEYS */;
INSERT INTO `tpic` VALUES (1,'华南','A','E:SillyGoosefoodf_0010.png','(无)');
/*!40000 ALTER TABLE `tpic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tuser`
--

DROP TABLE IF EXISTS `tuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tuser` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id，自增',
  `userName` varchar(20) NOT NULL COMMENT '用户姓名',
  `userPhone` char(11) NOT NULL COMMENT '用户手机号',
  `userPasswd` char(32) NOT NULL COMMENT '用户密码，md5加密',
  `userStatus` int(11) NOT NULL DEFAULT '0' COMMENT '0:未登录\n1:登录',
  `lastSignIn` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userId`,`userPhone`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tuser`
--

LOCK TABLES `tuser` WRITE;
/*!40000 ALTER TABLE `tuser` DISABLE KEYS */;
INSERT INTO `tuser` VALUES (9,'tenacity','18051072519','wjr.981125',1,'2018-06-19 21:46:15'),(10,'??','15651631693','110120zxy',1,'2018-06-19 20:36:46');
/*!40000 ALTER TABLE `tuser` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-20 13:14:25
