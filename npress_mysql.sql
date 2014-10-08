/*
MySQL - 5.5.30 : Database - blog
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `blog`;

/*Table structure for table `t_article` */

DROP TABLE IF EXISTS `t_article`;

CREATE TABLE `t_article` (
  `C_ID` bigint(20) NOT NULL,
  `C_USER_ID` bigint(20) DEFAULT NULL,
  `C_TITLE` varchar(200) DEFAULT NULL,
  `C_PIC` varchar(500) DEFAULT NULL,
  `C_CONTENT` text,
  `C_DESC` text,
  `C_DAY` varchar(10) DEFAULT NULL,
  `C_TIME` varchar(19) DEFAULT NULL,
  `C_KEYWORDS` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_article_cat` */

DROP TABLE IF EXISTS `t_article_cat`;

CREATE TABLE `t_article_cat` (
  `C_ID` bigint(20) NOT NULL,
  `C_ARTICLE_ID` bigint(20) DEFAULT NULL,
  `C_CAT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_cat` */

DROP TABLE IF EXISTS `t_cat`;

CREATE TABLE `t_cat` (
  `C_ID` bigint(20) NOT NULL,
  `C_NAME` varchar(100) DEFAULT NULL,
  `C_TYPE` int(11) DEFAULT NULL,
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_config` */

DROP TABLE IF EXISTS `t_config`;

CREATE TABLE `t_config` (
  `C_KEY` varchar(60) NOT NULL,
  `C_VALUE` varchar(500) DEFAULT NULL,
  `C_NAME` varchar(100) DEFAULT NULL,
  `C_DESC` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`C_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_link` */

DROP TABLE IF EXISTS `t_link`;

CREATE TABLE `t_link` (
  `C_ID` bigint(20) NOT NULL,
  `C_NAME` varchar(100) DEFAULT NULL,
  `C_URL` varchar(300) DEFAULT NULL,
  `C_INDEX` int(11) DEFAULT '0',
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_module` */

DROP TABLE IF EXISTS `t_module`;

CREATE TABLE `t_module` (
  `C_ID` bigint(20) NOT NULL,
  `C_NAME` varchar(60) DEFAULT NULL,
  `C_TYPE` int(11) DEFAULT NULL,
  `C_URL` varchar(300) DEFAULT NULL,
  `C_CONTENT` text,
  `C_IS_HOME` int(11) DEFAULT '0',
  `C_INDEX` int(11) DEFAULT NULL,
  `C_POS` int(11) DEFAULT NULL,
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `C_ID` bigint(20) NOT NULL,
  `C_ACCOUNT` varchar(16) DEFAULT NULL,
  `C_NAME` varchar(60) DEFAULT NULL,
  `C_PASSWORD` varchar(32) DEFAULT NULL,
  `C_IS_ADMIN` int(11) DEFAULT '0',
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
