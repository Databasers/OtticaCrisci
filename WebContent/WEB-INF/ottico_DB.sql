CREATE DATABASE  IF NOT EXISTS `ottico` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ottico`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ottico
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `CodiceFiscale` varchar(16) NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `Cognome` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `DataNascita` date NOT NULL,
  `DataAssunzione` date NOT NULL,
  `Telefono` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`CodiceFiscale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('CRSLGU97P06F924H','Luigi','Crisci','prova','1997-09-06','2015-06-05','3896970085');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

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
  `Validato` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`CodiceFiscale`),
  UNIQUE KEY `Url_UNIQUE` (`Url`),
  CONSTRAINT `CodiceFiscale` FOREIGN KEY (`CodiceFiscale`) REFERENCES `cliente` (`codicefiscale`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificato`
--

LOCK TABLES `certificato` WRITE;
/*!40000 ALTER TABLE `certificato` DISABLE KEYS */;
INSERT INTO `certificato` VALUES ('azz','C:\\Users\\Antonio\\eclipse--EEworkspace-servlet\\OtticaCrisci\\Data\\Certificati\\azz.pdf',0,1),('CRSLGU97P06F924H','C:\\Users\\Antonio\\eclipse--EEworkspace-servlet\\OtticaCrisci\\Data\\Certificati\\CRSLGU97P06F924H.pdf',1,1),('Luigi','C:\\Users\\Antonio\\eclipse--EEworkspace-servlet\\OtticaCrisci\\Data\\Certificati\\Luigi.pdf',0,1),('ssssssssssssssss','C:\\Users\\Antonio\\eclipse--EEworkspace-servlet\\OtticaCrisci\\Data\\Certificati\\ssssssssssssssss.pdf',1,1);
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
  `Gradazione` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`CodiceFiscale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('ASDFGHJKLQWERTYI','Luigi','Ferri','prova',NULL),('azz','azz','azz','prova',NULL),('CCCGZN93L08F839T','Graziano','Ciccarelli','prova',NULL),('CRSGPP78I52W687F','Giuseppe','Crisci','prova',NULL),('CRSLGU97P06F924H','Luigi','Crisci','prova',0),('FRNSST80R50C129B','Francesca','Esposito','prova',NULL),('GVNDRT78S14F839Z','Giovanni','De Martino','prova',NULL),('LGUCSC97P06F924V','Luigi','Crisci','prova',NULL),('LRANTR94S69B963J','Laura','Notaro','prova',NULL),('LRIDDA97E54F839T','Ilaria','Addeo','prova',NULL),('Luigi','Luigi','Luigi','prova',0),('PLADCN80E11A089R','Paolo','De Canio','prova',NULL),('RMMNTN96T21F839N','Antonio','Auriemma','prova',NULL),('SLVFNC81A41A509P','Silvia','Francescani','prova',NULL),('ssssssssssssssss','dino','dino','dino123',0);
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
  `UrlImmagine` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`IDFrame`),
  KEY `PartitaIVA` (`PartitaIVA`),
  CONSTRAINT `frame_ibfk_1` FOREIGN KEY (`PartitaIVA`) REFERENCES `fornitore` (`PartitaIVA`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frame`
--

LOCK TABLES `frame` WRITE;
/*!40000 ALTER TABLE `frame` DISABLE KEYS */;
INSERT INTO `frame` VALUES (1,'tondo','nero',10,'plastica',100,123456,'Oakley','Occhiali_steampunk.jpg'),(2,'rettangolare','nero',15,'plastica',75,345678,'Oakley','id.jpg'),(3,'occhi di gatto','nero',12,'plastica',50,123456,'Oakley','id3.jpg'),(4,'tondo','blu',10,'plastica',100,345678,'Alain Mikli','id4.jpg'),(5,'tondo','nero',8,'metallo',150,123456,'Alain Mikli','id5.jpg'),(6,'tondo piccolo','nero',10,'metallo',150,345678,'Alain Mikli','Occhiali_steampunk.jpg'),(7,'rettangolare','nero',10,'metallo',125,123456,'Alain Mikli','id6.jpg'),(8,'tondo','nero',10,'metallo',125,345678,'Arnette','Occhiali_steampunk.jpg'),(9,'rettangolare','nero',8,'metallo',150,123456,'Arnette','id9.png'),(10,'tondo','nero',8,'metallo',125,123456,'Arnette','id10.png'),(11,'occhi di gatto','blu',15,'acciaio',200,123456,'Brooks Brother','id3.jpg'),(12,'occhi di gatto','grigio',11,'acciaio',100,345678,'Brooks Brother','id3.jpg'),(13,'rettangolare','grigio',15,'acciaio',75,123456,'Burberry','id13.jpg'),(14,'rettangolare','nero',12,'plastica',50,345678,'Burberry','id9.png'),(15,'occhi di gatto','blu',10,'plastica',50,123456,'Bulgari','id15.jpg'),(16,'tondo piccolo','nero',10,'plastica',50,123456,'Bulgari','id16.jpg'),(17,'tondo','blu',8,'plastica',50,123456,'Chanel','id4.jpg'),(19,'occhi di gatto','blu',10,'plastica',50,123456,'Coach','id4.jpg'),(20,'occhi di gatto','blu',10,'plastica',50,123456,'Dkny','id4.jpg'),(21,'occhi di gatto','blu',10,'plastica',50,123456,'Dkny','id4.jpg'),(22,'occhi di gatto','blu',10,'plastica',50,123456,'Bulgari','id4.jpg'),(23,'occhi di gatto','blu',10,'plastica',50,123456,'Dkny','id4.jpg');
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
  `Occhiale_nuovoIDOcchiale` int(15) unsigned DEFAULT NULL,
  `Occhiale_rottoIDOcchiale` int(15) unsigned DEFAULT NULL,
  `IDFrame` int(15) unsigned DEFAULT NULL,
  PRIMARY KEY (`CodiceLavorazione`),
  KEY `Occhiale_nuovo.IDOcchiale` (`Occhiale_nuovoIDOcchiale`),
  KEY `Occhiale_rotto.IDOcchiale` (`Occhiale_rottoIDOcchiale`),
  KEY `IDFrame` (`IDFrame`),
  CONSTRAINT `lavorazione_deposito_ibfk_1` FOREIGN KEY (`Occhiale_nuovoIDOcchiale`) REFERENCES `occhiale_nuovo` (`idocchiale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lavorazione_deposito_ibfk_2` FOREIGN KEY (`Occhiale_rottoIDOcchiale`) REFERENCES `occhiale_rotto` (`IDOcchiale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lavorazione_deposito_ibfk_3` FOREIGN KEY (`IDFrame`) REFERENCES `frame` (`IDFrame`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lavorazione_deposito`
--

LOCK TABLES `lavorazione_deposito` WRITE;
/*!40000 ALTER TABLE `lavorazione_deposito` DISABLE KEYS */;
INSERT INTO `lavorazione_deposito` VALUES (1,5,'2017-12-05','2017-12-15','A1',1,NULL,NULL),(2,5,'2017-04-01','2017-04-11','B3',2,NULL,NULL),(3,4,'2017-12-30','2018-01-08','Z1',NULL,1,NULL),(4,2,'2017-11-25','2017-11-30','Z2',NULL,2,NULL),(5,4,'2017-09-05','2017-10-05','C11',NULL,NULL,16),(6,13,'2017-03-16','2017-05-16','B12',NULL,NULL,17),(9,2,'2017-09-30',NULL,'Z3',NULL,4,NULL),(11,12,'2017-06-03','2018-07-10','A8',6,NULL,NULL),(12,12,'2017-05-05',NULL,'C6',7,NULL,NULL),(13,5,'2017-09-01','2017-09-08','C14',3,NULL,NULL),(14,5,'2017-10-08','2017-10-15','B5',4,NULL,NULL),(15,12,'2017-11-09','2017-11-11','C6',5,NULL,NULL),(16,1,'2018-01-11',NULL,'X',8,NULL,NULL),(17,1,'2018-01-11',NULL,'X',NULL,NULL,13),(18,1,'2018-01-11',NULL,'X',NULL,NULL,14);
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
  `DataIngresso` date NOT NULL,
  `DataUscita` date DEFAULT NULL,
  `Occhiale_nuovoIDOcchiale` int(15) unsigned DEFAULT NULL,
  `Occhiale_rottoIDOcchiale` int(15) unsigned DEFAULT NULL,
  PRIMARY KEY (`CodiceLavorazione`),
  KEY `Occhiale_nuovo.IDOcchiale` (`Occhiale_nuovoIDOcchiale`),
  KEY `Occhiale_rotto.IDOcchiale` (`Occhiale_rottoIDOcchiale`),
  CONSTRAINT `lavorazione_laboratorio_ibfk_1` FOREIGN KEY (`Occhiale_nuovoIDOcchiale`) REFERENCES `occhiale_nuovo` (`idocchiale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lavorazione_laboratorio_ibfk_2` FOREIGN KEY (`Occhiale_rottoIDOcchiale`) REFERENCES `occhiale_rotto` (`IDOcchiale`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lavorazione_laboratorio`
--

LOCK TABLES `lavorazione_laboratorio` WRITE;
/*!40000 ALTER TABLE `lavorazione_laboratorio` DISABLE KEYS */;
INSERT INTO `lavorazione_laboratorio` VALUES (1,11,'riparazione','2017-11-05',NULL,NULL,3),(2,7,'riparazione','2017-09-09','2017-09-30',NULL,4),(3,5,'riparazione','2017-12-01','2017-12-30',NULL,1),(4,4,'riparazione','2017-11-05','2017-11-25',NULL,2),(5,1,'riparazione','2017-08-10',NULL,NULL,5),(6,11,'montaggio','2017-11-29','2017-12-05',1,NULL),(7,11,'montaggio','2017-03-20','2017-04-01',2,NULL),(8,1,'montaggio','2017-03-29','2018-03-29',8,NULL),(9,4,'montaggio','2017-12-30',NULL,9,NULL),(10,5,'montaggio','2017-11-09',NULL,10,NULL),(11,6,'montaggio','2017-05-20','2017-06-03',6,NULL),(12,7,'montaggio','2017-04-20','2017-05-05',7,NULL),(13,11,'montaggio','2017-08-20','2017-09-01',3,NULL),(14,11,'montaggio','2017-09-30','2017-10-08',4,NULL),(15,10,'montaggio','2017-10-31','2017-11-09',5,NULL),(17,3,'montaggio','2018-07-10',NULL,6,NULL),(19,32,'montaggio','2018-08-24',NULL,13,NULL),(20,13,'montaggio','2018-08-24',NULL,14,NULL),(21,12,'montaggio','2018-08-24',NULL,15,NULL),(22,23,'montaggio','2018-08-25',NULL,16,NULL),(23,3,'montaggio','2018-08-25',NULL,17,NULL),(24,19,'montaggio','2018-08-25',NULL,18,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lente`
--

LOCK TABLES `lente` WRITE;
/*!40000 ALTER TABLE `lente` DISABLE KEYS */;
INSERT INTO `lente` VALUES (1,4,'Minerale',5,70,'antiriflesso, antigraffio',456789),(2,3,'Organico',7,50,'antigraffio',234567),(3,2,'Organico',5,50,'antiriflesso',234567),(4,1,'Trivex',5,10,'antiriflesso, antigraffio, transition',234567),(5,1,'Minerale',8,50,'antigraffio',456789),(6,1,'Policarbonato',7,100,'antiriflesso, antigraffio, transition',456789),(7,2,'Policarbonato',5,60,'antigraffio, transition',234567),(8,2,'Trivex',7,70,'antiriflesso, transition',234567),(9,3,'Organico',7,50,'antiriflesso',456789),(10,3,'Trivex',8,100,'antiriflesso, antigraffio, transition',234567),(11,5,'HH',1,100,'HH',123456),(12,7,'vetro',50,560,'D&G',123456),(13,7,'vetro',50,560,'occhi di gatto',123456),(14,7,'vetro',50,560,'occhi di gatto',123456),(15,7,'vetro',50,560,'occhi di gatto',123456),(16,0,'vetro',50,0,'occhi di gatto',123456),(17,0,'vetro',50,0,'occhi di gatto',123456),(18,0,'vetro',50,0,'occhi di gatto',123456);
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
  `DataOrdine` date NOT NULL,
  `Stato` varchar(45) NOT NULL,
  PRIMARY KEY (`IDOcchiale`),
  UNIQUE KEY `IDLente` (`IDLente`),
  UNIQUE KEY `IDFrame` (`IDFrame`),
  KEY `CodiceFiscale` (`CodiceFiscale`),
  CONSTRAINT `occhiale_nuovo_ibfk_1` FOREIGN KEY (`CodiceFiscale`) REFERENCES `cliente` (`CodiceFiscale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `occhiale_nuovo_ibfk_2` FOREIGN KEY (`IDFrame`) REFERENCES `frame` (`IDFrame`) ON UPDATE CASCADE,
  CONSTRAINT `occhiale_nuovo_ibfk_3` FOREIGN KEY (`IDLente`) REFERENCES `lente` (`IDLente`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `occhiale_nuovo`
--

LOCK TABLES `occhiale_nuovo` WRITE;
/*!40000 ALTER TABLE `occhiale_nuovo` DISABLE KEYS */;
INSERT INTO `occhiale_nuovo` VALUES (1,500,'2017-12-15',1,1,'CCCGZN93L08F839T','2018-06-12','Consegnato'),(2,500,'2017-04-11',2,2,'CCCGZN93L08F839T','2018-06-12','Consegnato'),(3,500,'2017-09-08',3,3,'CCCGZN93L08F839T','2018-06-12','Consegnato'),(4,500,'2017-10-15',4,4,'CCCGZN93L08F839T','2018-06-12','Consegnato'),(5,350,'2017-11-11',5,5,'LRIDDA97E54F839T','2018-06-12','Consegnato'),(6,300,NULL,6,6,'LRIDDA97E54F839T','2018-06-12','In Lavorazione'),(7,400,NULL,7,7,'RMMNTN96T21F839N','2018-06-12','In Deposito'),(8,200,NULL,8,8,'RMMNTN96T21F839N','2018-06-12','In Deposito'),(9,350,NULL,9,9,'LRANTR94S69B963J','2018-06-12','In Lavorazione'),(10,500,NULL,10,10,'LRANTR94S69B963J','2018-06-12','In Lavorazione'),(13,915,NULL,13,19,'CRSLGU97P06F924H','2018-08-24','In Lavorazione'),(14,915,NULL,14,20,'CRSLGU97P06F924H','2018-08-24','In Lavorazione'),(15,915,NULL,15,21,'CRSLGU97P06F924H','2018-08-24','In Lavorazione'),(16,75,NULL,16,15,'CRSLGU97P06F924H','2018-08-25','In Lavorazione'),(17,75,NULL,17,22,'CRSLGU97P06F924H','2018-08-25','In Lavorazione'),(18,75,NULL,18,23,'CRSLGU97P06F924H','2018-08-25','In Lavorazione');
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
  `EntitaDanno` varchar(150) NOT NULL,
  `CodiceFiscale` varchar(16) NOT NULL,
  `Stato` varchar(45) NOT NULL,
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
INSERT INTO `occhiale_rotto` VALUES (1,250,'2018-01-08','2017-12-01','grave danno alla lente','GVNDRT78S14F839Z','Consegnato'),(2,100,'2017-11-30','2017-11-05','lieve danno alla lente','PLADCN80E11A089R','Consegnato'),(3,70,NULL,'2017-11-05','lieve danno al frame','FRNSST80R50C129B','In Lavorazione'),(4,70,NULL,'2017-09-09','lieve danno al frame','SLVFNC81A41A509P','In Deposito'),(5,100,NULL,'2017-08-10','lieve danno alla lente','LGUCSC97P06F924V','In Lavorazione');
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

-- Dump completed on 2018-07-25 21:32:42
