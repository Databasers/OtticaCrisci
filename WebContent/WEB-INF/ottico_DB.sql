CREATE DATABASE  IF NOT EXISTS `ottico` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ottico`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ottico
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `certificato`
--

DROP TABLE IF EXISTS `certificato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `certificato` (
  `CodiceFiscale` varchar(16) NOT NULL,
  `Url` varchar(100) DEFAULT NULL,
  `Valido` tinyint(1) NOT NULL,
  PRIMARY KEY (`CodiceFiscale`),
  UNIQUE KEY `Url_UNIQUE` (`Url`),
  CONSTRAINT `CodiceFiscale` FOREIGN KEY (`CodiceFiscale`) REFERENCES `cliente` (`CodiceFiscale`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificato`
--

LOCK TABLES `certificato` WRITE;
/*!40000 ALTER TABLE `certificato` DISABLE KEYS */;
INSERT INTO `certificato` VALUES ('CRSLGU97P06F924H','C:AiOLog.txt',0);
/*!40000 ALTER TABLE `certificato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `CodiceFiscale` varchar(16) NOT NULL,
  `Nome` varchar(20) NOT NULL,
  `Cognome` varchar(20) NOT NULL,
  `Password` varchar(45) NOT NULL,
  PRIMARY KEY (`CodiceFiscale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('CCCGZN93L08F839T','Graziano','Ciccarelli',''),('CRSLGU97P06F924H','Luigi','Crisci',''),('FRNSST80R50C129B','Francesca','Esposito',''),('GVNDRT78S14F839Z','Giovanni','De Martino',''),('LGUCSC97P06F924V','Luigi','Crisci',''),('LRANTR94S69B963J','Laura','Notaro',''),('LRIDDA97E54F839T','Ilaria','Addeo',''),('PLADCN80E11A089R','Paolo','De Canio',''),('RMMNTN96T21F839N','Antonio','Auriemma',''),('SLVFNC81A41A509P','Silvia','Francescani','');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornitore`
--

DROP TABLE IF EXISTS `fornitore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornitore` (
  `PartitaIVA` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `Nome` varchar(20) NOT NULL,
  PRIMARY KEY (`PartitaIVA`)
) ENGINE=InnoDB AUTO_INCREMENT=456790 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornitore`
--

LOCK TABLES `fornitore` WRITE;
/*!40000 ALTER TABLE `fornitore` DISABLE KEYS */;
INSERT INTO `fornitore` VALUES (123456,'FornitoreOttici'),(234567,'LaLenteDiCasa'),(345678,'UnFrameAlGiorno'),(456789,'LenteMaNonTroppo');
/*!40000 ALTER TABLE `fornitore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frame`
--

DROP TABLE IF EXISTS `frame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `frame` (
  `IDFrame` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `Modello` varchar(15) NOT NULL,
  `Colore` varchar(15) NOT NULL,
  `Peso` int(15) unsigned NOT NULL,
  `Materiale` varchar(15) NOT NULL,
  `Prezzo` int(15) unsigned NOT NULL,
  `PartitaIVA` int(15) unsigned NOT NULL,
  `Marchio` varchar(45) NOT NULL,
  PRIMARY KEY (`IDFrame`),
  KEY `PartitaIVA` (`PartitaIVA`),
  CONSTRAINT `frame_ibfk_1` FOREIGN KEY (`PartitaIVA`) REFERENCES `fornitore` (`PartitaIVA`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frame`
--

LOCK TABLES `frame` WRITE;
/*!40000 ALTER TABLE `frame` DISABLE KEYS */;
INSERT INTO `frame` VALUES (1,'tondo','nero',10,'plastica',100,123456,''),(2,'rettangolare','grigio',15,'plastica',75,345678,''),(3,'occhi di gatto','blu scuro',12,'plastica',50,123456,''),(4,'tondo','blu',10,'plastica',100,345678,''),(5,'tondo','nero',8,'metallo',150,123456,''),(6,'tondo piccolo','nero',10,'metallo',150,345678,''),(7,'rettangolare','nero',10,'metallo',125,123456,''),(8,'tondo','nero',10,'metallo',125,345678,''),(9,'rettangolare','nero',8,'metallo',150,123456,''),(10,'tondo','nero',8,'metallo',125,123456,''),(11,'occhi di gatto','blu',15,'acciaio',200,123456,''),(12,'occhi di gatto','grigio',11,'acciaio',100,345678,''),(13,'rettangolare','grigio',15,'acciaio',75,123456,''),(14,'rettangolare','nero',12,'plastica',50,345678,''),(15,'occhi di gatto','blu',10,'plastica',50,123456,''),(16,'tondo piccolo','nero',10,'plastica',50,123456,''),(17,'tondo','blu',8,'plastica',50,123456,''),(18,'NNJK','DSD',1,'NJNKNKJ',30,123456,'');
/*!40000 ALTER TABLE `frame` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lavorazione_deposito`
--

DROP TABLE IF EXISTS `lavorazione_deposito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lavorazione_deposito` (
  `CodiceLavorazione` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `CodiceAddetto` int(15) NOT NULL,
  `DataIngresso` date NOT NULL,
  `DataUscita` date DEFAULT NULL,
  `PosizioneOcchiale` varchar(10) NOT NULL,
  `Occhiale_nuovo.IDOcchiale` int(15) unsigned DEFAULT NULL,
  `Occhiale_rotto.IDOcchiale` int(15) unsigned DEFAULT NULL,
  `IDFrame` int(15) unsigned DEFAULT NULL,
  PRIMARY KEY (`CodiceLavorazione`),
  KEY `Occhiale_nuovo.IDOcchiale` (`Occhiale_nuovo.IDOcchiale`),
  KEY `Occhiale_rotto.IDOcchiale` (`Occhiale_rotto.IDOcchiale`),
  KEY `IDFrame` (`IDFrame`),
  CONSTRAINT `lavorazione_deposito_ibfk_1` FOREIGN KEY (`Occhiale_nuovo.IDOcchiale`) REFERENCES `occhiale_nuovo` (`IDOcchiale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lavorazione_deposito_ibfk_2` FOREIGN KEY (`Occhiale_rotto.IDOcchiale`) REFERENCES `occhiale_rotto` (`IDOcchiale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lavorazione_deposito_ibfk_3` FOREIGN KEY (`IDFrame`) REFERENCES `frame` (`IDFrame`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lavorazione_deposito`
--

LOCK TABLES `lavorazione_deposito` WRITE;
/*!40000 ALTER TABLE `lavorazione_deposito` DISABLE KEYS */;
INSERT INTO `lavorazione_deposito` VALUES (1,5,'2017-12-05','2017-12-15','A1',1,NULL,NULL),(2,5,'2017-04-01','2017-04-11','B3',2,NULL,NULL),(3,4,'2017-12-30','2018-01-08','Z1',NULL,1,NULL),(4,2,'2017-11-25','2017-11-30','Z2',NULL,2,NULL),(5,4,'2017-09-05','2017-10-05','C11',NULL,NULL,16),(6,13,'2017-03-16','2017-05-16','B12',NULL,NULL,17),(9,2,'2017-09-30',NULL,'Z3',NULL,4,NULL),(11,12,'2017-06-03',NULL,'A8',6,NULL,NULL),(12,12,'2017-05-05',NULL,'C6',7,NULL,NULL),(13,5,'2017-09-01','2017-09-08','C14',3,NULL,NULL),(14,5,'2017-10-08','2017-10-15','B5',4,NULL,NULL),(15,12,'2017-11-09','2017-11-11','C6',5,NULL,NULL),(16,1,'2018-01-11',NULL,'X',8,NULL,NULL),(17,1,'2018-01-11',NULL,'X',NULL,NULL,13),(18,1,'2018-01-11',NULL,'X',NULL,NULL,14);
/*!40000 ALTER TABLE `lavorazione_deposito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lavorazione_laboratorio`
--

DROP TABLE IF EXISTS `lavorazione_laboratorio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lavorazione_laboratorio` (
  `CodiceLavorazione` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `CodiceAddetto` int(15) unsigned NOT NULL,
  `TipoLavorazione` varchar(15) NOT NULL,
  `DataInizio` date NOT NULL,
  `DataFine` date DEFAULT NULL,
  `Occhiale_nuovo.IDOcchiale` int(15) unsigned DEFAULT NULL,
  `Occhiale_rotto.IDOcchiale` int(15) unsigned DEFAULT NULL,
  PRIMARY KEY (`CodiceLavorazione`),
  KEY `Occhiale_nuovo.IDOcchiale` (`Occhiale_nuovo.IDOcchiale`),
  KEY `Occhiale_rotto.IDOcchiale` (`Occhiale_rotto.IDOcchiale`),
  CONSTRAINT `lavorazione_laboratorio_ibfk_1` FOREIGN KEY (`Occhiale_nuovo.IDOcchiale`) REFERENCES `occhiale_nuovo` (`IDOcchiale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lavorazione_laboratorio_ibfk_2` FOREIGN KEY (`Occhiale_rotto.IDOcchiale`) REFERENCES `occhiale_rotto` (`IDOcchiale`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lavorazione_laboratorio`
--

LOCK TABLES `lavorazione_laboratorio` WRITE;
/*!40000 ALTER TABLE `lavorazione_laboratorio` DISABLE KEYS */;
INSERT INTO `lavorazione_laboratorio` VALUES (1,11,'riparazione','2017-11-05',NULL,NULL,3),(2,7,'riparazione','2017-09-09','2017-09-30',NULL,4),(3,5,'riparazione','2017-12-01','2017-12-30',NULL,1),(4,4,'riparazione','2017-11-05','2017-11-25',NULL,2),(5,1,'riparazione','2017-08-10',NULL,NULL,5),(6,11,'montaggio','2017-11-29','2017-12-05',1,NULL),(7,11,'montaggio','2017-03-20','2017-04-01',2,NULL),(8,1,'montaggio','2017-03-29','2018-03-29',8,NULL),(9,4,'montaggio','2017-12-30',NULL,9,NULL),(10,5,'montaggio','2017-11-09',NULL,10,NULL),(11,6,'montaggio','2017-05-20','2017-06-03',6,NULL),(12,7,'montaggio','2017-04-20','2017-05-05',7,NULL),(13,11,'montaggio','2017-08-20','2017-09-01',3,NULL),(14,11,'montaggio','2017-09-30','2017-10-08',4,NULL),(15,10,'montaggio','2017-10-31','2017-11-09',5,NULL),(16,1,'montaggio','2018-03-29',NULL,11,NULL);
/*!40000 ALTER TABLE `lavorazione_laboratorio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lente`
--

DROP TABLE IF EXISTS `lente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lente` (
  `IDLente` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `Diottria` int(10) NOT NULL,
  `Materiale` varchar(15) NOT NULL,
  `Peso` int(10) unsigned NOT NULL,
  `Prezzo` int(10) unsigned NOT NULL,
  `TipoLente` varchar(100) NOT NULL,
  `PartitaIva` int(15) unsigned NOT NULL,
  PRIMARY KEY (`IDLente`),
  KEY `PartitaIva` (`PartitaIva`),
  CONSTRAINT `lente_ibfk_1` FOREIGN KEY (`PartitaIva`) REFERENCES `fornitore` (`PartitaIVA`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lente`
--

LOCK TABLES `lente` WRITE;
/*!40000 ALTER TABLE `lente` DISABLE KEYS */;
INSERT INTO `lente` VALUES (1,4,'Minerale',5,70,'antiriflesso, antigraffio',456789),(2,3,'Organico',7,50,'antigraffio',234567),(3,2,'Organico',5,50,'antiriflesso',234567),(4,1,'Trivex',5,10,'antiriflesso, antigraffio, transition',234567),(5,1,'Minerale',8,50,'antigraffio',456789),(6,1,'Policarbonato',7,100,'antiriflesso, antigraffio, transition',456789),(7,2,'Policarbonato',5,60,'antigraffio, transition',234567),(8,2,'Trivex',7,70,'antiriflesso, transition',234567),(9,3,'Organico',7,50,'antiriflesso',456789),(10,3,'Trivex',8,100,'antiriflesso, antigraffio, transition',234567),(11,5,'HH',1,100,'HH',123456);
/*!40000 ALTER TABLE `lente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `occhiale_nuovo`
--

DROP TABLE IF EXISTS `occhiale_nuovo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `occhiale_nuovo` (
  `IDOcchiale` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `Prezzo` int(15) unsigned NOT NULL,
  `DataRitiro` date DEFAULT NULL,
  `IDLente` int(15) unsigned NOT NULL,
  `IDFrame` int(15) unsigned NOT NULL,
  `CodiceFiscale` varchar(16) NOT NULL,
  PRIMARY KEY (`IDOcchiale`),
  UNIQUE KEY `IDLente` (`IDLente`),
  UNIQUE KEY `IDFrame` (`IDFrame`),
  KEY `CodiceFiscale` (`CodiceFiscale`),
  CONSTRAINT `occhiale_nuovo_ibfk_1` FOREIGN KEY (`CodiceFiscale`) REFERENCES `cliente` (`CodiceFiscale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `occhiale_nuovo_ibfk_2` FOREIGN KEY (`IDFrame`) REFERENCES `frame` (`IDFrame`) ON UPDATE CASCADE,
  CONSTRAINT `occhiale_nuovo_ibfk_3` FOREIGN KEY (`IDLente`) REFERENCES `lente` (`IDLente`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `occhiale_nuovo`
--

LOCK TABLES `occhiale_nuovo` WRITE;
/*!40000 ALTER TABLE `occhiale_nuovo` DISABLE KEYS */;
INSERT INTO `occhiale_nuovo` VALUES (1,500,'2017-12-15',1,1,'CCCGZN93L08F839T'),(2,500,'2017-04-11',2,2,'CCCGZN93L08F839T'),(3,500,'2017-09-08',3,3,'CCCGZN93L08F839T'),(4,500,'2017-10-15',4,4,'CCCGZN93L08F839T'),(5,350,'2017-11-11',5,5,'LRIDDA97E54F839T'),(6,300,NULL,6,6,'LRIDDA97E54F839T'),(7,400,NULL,7,7,'RMMNTN96T21F839N'),(8,200,NULL,8,8,'RMMNTN96T21F839N'),(9,350,NULL,9,9,'LRANTR94S69B963J'),(10,500,NULL,10,10,'LRANTR94S69B963J'),(11,169,NULL,11,18,'CRSLGU97P06F924H');
/*!40000 ALTER TABLE `occhiale_nuovo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `occhiale_rotto`
--

DROP TABLE IF EXISTS `occhiale_rotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `occhiale_rotto` (
  `IDOcchiale` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `Prezzo` int(15) unsigned NOT NULL,
  `DataRitiro` date DEFAULT NULL,
  `DataConsegna` date NOT NULL,
  `EntitàDanno` varchar(150) NOT NULL,
  `CodiceFiscale` varchar(16) NOT NULL,
  PRIMARY KEY (`IDOcchiale`),
  KEY `CodiceFiscale` (`CodiceFiscale`),
  CONSTRAINT `occhiale_rotto_ibfk_1` FOREIGN KEY (`CodiceFiscale`) REFERENCES `cliente` (`CodiceFiscale`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `occhiale_rotto`
--

LOCK TABLES `occhiale_rotto` WRITE;
/*!40000 ALTER TABLE `occhiale_rotto` DISABLE KEYS */;
INSERT INTO `occhiale_rotto` VALUES (1,250,'2018-01-08','2017-12-01','grave danno alla lente','GVNDRT78S14F839Z'),(2,100,'2017-11-30','2017-11-05','lieve danno alla lente','PLADCN80E11A089R'),(3,70,NULL,'2017-11-05','lieve danno al frame','FRNSST80R50C129B'),(4,70,NULL,'2017-09-09','lieve danno al frame','SLVFNC81A41A509P'),(5,100,NULL,'2017-08-10','lieve danno alla lente','LGUCSC97P06F924V');
/*!40000 ALTER TABLE `occhiale_rotto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-20 11:22:55
