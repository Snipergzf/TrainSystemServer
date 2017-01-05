-- MySQL dump 10.13  Distrib 5.7.15, for Win64 (x86_64)
--
-- Host: localhost    Database: trainsystem
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
-- Table structure for table `data`
--

DROP TABLE IF EXISTS `data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data` (
  `ipaddress` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `MC_deviceId` varchar(2) NOT NULL,
  `MC_groupRate` varchar(10) NOT NULL COMMENT '群路速率',
  `MC_groupInterface` varchar(20) NOT NULL COMMENT '群路接口',
  `MC_groupClock` varchar(20) NOT NULL COMMENT '群路时钟',
  `MC_videoRate` varchar(20) NOT NULL COMMENT '视频速率',
  `MC_videoSendClock` varchar(20) NOT NULL COMMENT '视频发钟',
  `MC_videoReceivedClock` varchar(20) NOT NULL COMMENT '视频收钟',
  `VC_interfaceType` varchar(20) NOT NULL COMMENT '线路接口选择',
  `VC_clock` varchar(20) NOT NULL COMMENT '时钟选择',
  `VC_rate` varchar(20) NOT NULL COMMENT '线路速率设置',
  `VC_codeFormat` varchar(20) NOT NULL COMMENT '编码格式',
  `VC_imageFormat` varchar(20) NOT NULL COMMENT '图像格式',
  `VC_frameRateValues` varchar(20) NOT NULL COMMENT '帧率',
  `VC_audioParameValues` varchar(20) NOT NULL COMMENT '音频参数',
  `VC_synData` varchar(20) NOT NULL COMMENT '同步数据设置',
  `MD_modemSendDataRate` varchar(20) NOT NULL COMMENT '调制 发数据速率',
  `MD_modemScrambleType` varchar(20) NOT NULL COMMENT '调制 扰码方式',
  `MD_modemDifferEncode` varchar(20) NOT NULL COMMENT '调制 差分编码',
  `MD_modemRSCode` varchar(20) NOT NULL COMMENT '调制 RS编码',
  `MD_modemConvoluCode` varchar(20) NOT NULL COMMENT '调制 卷积编码',
  `MD_modemType` varchar(20) NOT NULL COMMENT '调制 方式',
  `MD_modemCarrierOutput` varchar(20) NOT NULL COMMENT '载波输出',
  `MD_modemSendCarrierFrequence` varchar(20) NOT NULL COMMENT '调制 发载波频率',
  `MD_deModemReceiveDataRate` varchar(20) NOT NULL COMMENT '解调 收数据速率',
  `MD_deModemDescrambleType` varchar(20) NOT NULL COMMENT '解调 解扰方式',
  `MD_deModemDifferEncode` varchar(20) NOT NULL COMMENT '解调 差分译码',
  `MD_deModemRSDecode` varchar(20) NOT NULL COMMENT '解调 RS译码',
  `MD_deModemConvoluDecode` varchar(20) NOT NULL COMMENT '解调 卷积译码',
  `MD_deModemType` varchar(20) NOT NULL COMMENT '解调 方式',
  `MD_deModemReceiveCarrierFrequence` varchar(20) NOT NULL COMMENT '收载波频率',
  `MD_frameType` varchar(20) NOT NULL COMMENT '成帧 类型',
  `MD_frameParam` varchar(20) NOT NULL COMMENT '载波输出',
  `MD_frameSClockPhase` varchar(20) NOT NULL COMMENT '成帧 发时钟相位',
  `MD_frameSServiceInterface` varchar(20) NOT NULL COMMENT '成帧 发勤务接口',
  `MD_frameSDataClock` varchar(20) NOT NULL COMMENT '成帧 发数据时钟',
  `MD_deframeType` varchar(20) NOT NULL COMMENT '解帧 类型',
  `MD_deframeSClockPhase` varchar(20) NOT NULL COMMENT '解帧 发时钟相',
  `MD_deframeRServiceInterface` varchar(20) NOT NULL COMMENT '解帧 收勤务接口',
  `MD_interfaceType` varchar(20) NOT NULL COMMENT '接口 类型',
  `MD_interfaceCodeType` varchar(20) NOT NULL COMMENT '接口 码型',
  UNIQUE KEY `ipaddress` (`ipaddress`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `ipaddress` varchar(20) NOT NULL,
  `currentlogin` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `online` tinyint(2) NOT NULL DEFAULT '1' COMMENT '默认在线状态为1，表示在线',
  `connectstatus` tinyint(2) NOT NULL DEFAULT '0' COMMENT '默认连接状态为0，表示不连接',
  `connectwith` varchar(20) DEFAULT '无',
  `operationcount` int(11) NOT NULL DEFAULT '0' COMMENT '操作数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ipaddress` (`ipaddress`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-05 19:20:36
