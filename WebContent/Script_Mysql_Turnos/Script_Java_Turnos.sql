CREATE DATABASE  IF NOT EXISTS `java_turnos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `java_turnos`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: java_turnos
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `consultorios`
--

DROP TABLE IF EXISTS `consultorios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `consultorios` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(60) NOT NULL,
  `direccion` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultorios`
--

LOCK TABLES `consultorios` WRITE;
/*!40000 ALTER TABLE `consultorios` DISABLE KEYS */;
INSERT INTO `consultorios` VALUES (1,'A','Corrientes 3321'),(2,'B','Baigorria 7761'),(3,'C','Martin fierro 1919'),(4,'D','Mendoza 6541'),(5,'E','San martin 9009');
/*!40000 ALTER TABLE `consultorios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidades`
--

DROP TABLE IF EXISTS `especialidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `especialidades` (
  `cod_especialidad` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`cod_especialidad`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidades`
--

LOCK TABLES `especialidades` WRITE;
/*!40000 ALTER TABLE `especialidades` DISABLE KEYS */;
INSERT INTO `especialidades` VALUES (1,'Oftamologia'),(2,'Endocrinologia'),(3,'Cardiología'),(4,'Nefrologia'),(5,'Dermatología');
/*!40000 ALTER TABLE `especialidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidades_practicas`
--

DROP TABLE IF EXISTS `especialidades_practicas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `especialidades_practicas` (
  `cod_practica` int(10) unsigned NOT NULL,
  `cod_especialidad` int(10) unsigned NOT NULL,
  PRIMARY KEY (`cod_practica`,`cod_especialidad`),
  KEY `fk_especialidades_practicas_especialidades_idx` (`cod_especialidad`),
  CONSTRAINT `fk_especialidades_practicas_especialidades` FOREIGN KEY (`cod_especialidad`) REFERENCES `especialidades` (`cod_especialidad`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_especialidades_practicas_practicas` FOREIGN KEY (`cod_practica`) REFERENCES `practicas` (`cod_practica`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidades_practicas`
--

LOCK TABLES `especialidades_practicas` WRITE;
/*!40000 ALTER TABLE `especialidades_practicas` DISABLE KEYS */;
INSERT INTO `especialidades_practicas` VALUES (1,1),(2,1),(3,1),(14,1),(15,1),(16,1),(17,1),(18,1),(4,2),(5,2),(6,2),(19,2),(20,2),(21,2),(22,2),(23,2),(7,3),(8,3),(9,3),(10,3),(24,3),(25,3),(26,3),(27,3),(28,4),(29,4),(30,5),(31,5);
/*!40000 ALTER TABLE `especialidades_practicas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obras_sociales`
--

DROP TABLE IF EXISTS `obras_sociales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `obras_sociales` (
  `cuit` varchar(45) NOT NULL,
  `razon_social` varchar(60) NOT NULL,
  PRIMARY KEY (`cuit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obras_sociales`
--

LOCK TABLES `obras_sociales` WRITE;
/*!40000 ALTER TABLE `obras_sociales` DISABLE KEYS */;
INSERT INTO `obras_sociales` VALUES ('30-10504876-5','ESENCIAL'),('30-15876543-4','Accord Salud'),('30-20987654-4','Swiss Medical'),('30-21008765-5','OSDE'),('30-21098732-4','LUIS PASTEUR');
/*!40000 ALTER TABLE `obras_sociales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planes`
--

DROP TABLE IF EXISTS `planes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `planes` (
  `id_plan` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cuit_os` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id_plan`),
  KEY `fk_planes_obras_sociales_idx` (`cuit_os`),
  CONSTRAINT `fk_planes_obras_sociales` FOREIGN KEY (`cuit_os`) REFERENCES `obras_sociales` (`cuit`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planes`
--

LOCK TABLES `planes` WRITE;
/*!40000 ALTER TABLE `planes` DISABLE KEYS */;
INSERT INTO `planes` VALUES (1,'30-10504876-5','Premium'),(2,'30-10504876-5','Clasico'),(3,'30-10504876-5','Global'),(5,'30-15876543-4','Acord310'),(6,'30-15876543-4','Acord210'),(7,'30-20987654-4','SMG01'),(8,'30-20987654-4','SMG02'),(11,'30-21008765-5','BM200'),(12,'30-21008765-5','BM300'),(13,'30-21098732-4','Clasico'),(14,'30-21098732-4','Global');
/*!40000 ALTER TABLE `planes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `practicas`
--

DROP TABLE IF EXISTS `practicas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `practicas` (
  `cod_practica` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) NOT NULL,
  PRIMARY KEY (`cod_practica`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practicas`
--

LOCK TABLES `practicas` WRITE;
/*!40000 ALTER TABLE `practicas` DISABLE KEYS */;
INSERT INTO `practicas` VALUES (1,'Tratamiento con laseres'),(2,'Laser luz pulsada'),(3,'Desprendimiento de retina'),(4,'Radiocirugía estereotáctica del cerebro'),(5,'Resonancia magnética'),(6,'Derivación biliopancreática con cruce duodenal'),(7,'Doppler Abdominal'),(8,'Electrofisologia'),(9,'Radiologia'),(10,'Dermatoscopia'),(14,'Blefaroplastia parpado superior'),(15,'Crosslinking corneal'),(16,'Colocamiento de  perfluorocarbono'),(17,'Cirugia de glaucoma'),(18,'Cirugia de cataratas'),(19,'Conocimiento del metabolismo y sus alteraciones'),(20,'Tratamiento de la patología RIA'),(21,'Tratamiento de la patología IRMA'),(22,'Tratamiento de la patología ELSA'),(23,'Tratamiento de inmunohistoquímica'),(24,'Hemofiltración'),(25,'Trasplante cardiaco'),(26,'Evaluacion del nódulo sinusal '),(27,'Regresión de la aterosclerosis'),(28,'Observacion de las arterias coronarias'),(29,'Observacion de las arterias cerebrales'),(30,'Diagnóstico molecular de patologías endócrinas'),(31,'Análisis de sangre');
/*!40000 ALTER TABLE `practicas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `practicas_planes`
--

DROP TABLE IF EXISTS `practicas_planes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `practicas_planes` (
  `id_plan` int(10) unsigned NOT NULL,
  `cod_practica` int(10) unsigned NOT NULL,
  `descuento` double unsigned NOT NULL,
  `fechadesde` datetime NOT NULL,
  PRIMARY KEY (`id_plan`,`cod_practica`,`fechadesde`),
  KEY `fk_practicas_planes_practicas_idx` (`cod_practica`),
  CONSTRAINT `fk_practicas_planes_planes` FOREIGN KEY (`id_plan`) REFERENCES `planes` (`id_plan`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_practicas_planes_practicas` FOREIGN KEY (`cod_practica`) REFERENCES `practicas` (`cod_practica`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practicas_planes`
--

LOCK TABLES `practicas_planes` WRITE;
/*!40000 ALTER TABLE `practicas_planes` DISABLE KEYS */;
INSERT INTO `practicas_planes` VALUES (1,1,0.3,'2019-10-13 00:00:00'),(1,1,0.5,'2019-11-13 00:00:00'),(1,1,0.7,'2019-11-16 00:00:00'),(1,2,0.1,'2019-11-13 00:00:00'),(1,3,0.6,'2019-11-13 00:00:00'),(1,4,0.2,'2019-11-13 00:00:00'),(1,5,0.05,'2019-11-13 00:00:00'),(1,10,0.3,'2019-08-10 00:00:00'),(1,14,0.1,'2019-08-10 00:00:00'),(1,15,0.05,'2019-08-10 00:00:00'),(1,16,0.4,'2019-08-10 00:00:00'),(1,17,0.2,'2019-08-10 00:00:00'),(1,18,0.8,'2019-08-10 00:00:00'),(1,19,0.75,'2019-08-10 00:00:00'),(1,20,0.3,'2019-08-10 00:00:00'),(1,21,0.25,'2019-08-10 00:00:00'),(1,22,0.15,'2019-08-10 00:00:00'),(1,23,0.1,'2019-08-10 00:00:00'),(1,24,0.5,'2019-08-10 00:00:00'),(2,1,0.5,'2019-11-13 00:00:00'),(2,2,0.6,'2019-11-13 00:00:00'),(2,3,0.8,'2019-11-13 00:00:00'),(2,4,0.4,'2019-11-13 00:00:00'),(2,5,0.3,'2019-11-13 00:00:00'),(3,1,0.1,'2019-11-13 00:00:00'),(3,2,0.5,'2019-11-13 00:00:00'),(3,3,0.1,'2019-11-13 00:00:00'),(3,4,0.2,'2019-11-13 00:00:00'),(3,5,0.3,'2019-11-13 00:00:00'),(5,1,0.4,'2019-11-13 00:00:00'),(5,2,0.5,'2019-11-13 00:00:00'),(5,6,0.6,'2019-11-13 00:00:00'),(5,7,0.7,'2019-11-13 00:00:00'),(6,1,0.8,'2019-11-13 00:00:00'),(6,2,0.9,'2019-11-13 00:00:00'),(6,6,0.55,'2019-11-13 00:00:00'),(6,7,0.45,'2019-11-13 00:00:00'),(6,14,0.7,'2019-09-08 00:00:00'),(6,15,0.8,'2019-09-08 00:00:00'),(6,16,0.9,'2019-09-08 00:00:00'),(6,17,0.3,'2019-09-08 00:00:00'),(6,18,0.2,'2019-09-08 00:00:00'),(6,19,0.1,'2019-09-08 00:00:00'),(6,20,0.15,'2019-09-08 00:00:00'),(7,25,0.9,'2019-05-10 00:00:00'),(7,26,0.1,'2019-05-10 00:00:00'),(7,27,0.8,'2019-05-10 00:00:00'),(7,28,0.15,'2019-05-10 00:00:00'),(7,29,0.2,'2019-05-10 00:00:00'),(7,30,0.4,'2019-05-10 00:00:00'),(7,31,0.35,'2019-05-10 00:00:00'),(8,1,0.1,'2019-11-13 00:00:00'),(8,2,0.2,'2019-11-13 00:00:00'),(8,3,0.9,'2019-11-13 00:00:00'),(8,4,0.6,'2019-11-13 00:00:00'),(8,5,0.5,'2019-09-08 00:00:00'),(8,6,0.3,'2019-11-13 00:00:00'),(8,7,0.2,'2019-09-08 00:00:00'),(8,8,0.15,'2019-09-08 00:00:00'),(8,9,0.25,'2019-09-08 00:00:00'),(8,14,0.09,'2019-09-08 00:00:00'),(11,1,0.3,'2019-09-08 00:00:00'),(11,2,0.45,'2019-09-08 00:00:00'),(11,3,0.7,'2019-09-08 00:00:00'),(11,7,0.6,'2019-09-08 00:00:00'),(11,8,0.5,'2019-09-08 00:00:00'),(12,14,0.2,'2019-09-08 00:00:00'),(12,15,0.3,'2019-09-08 00:00:00'),(12,16,0.45,'2019-09-08 00:00:00'),(13,7,0.3,'2019-01-05 00:00:00'),(13,8,0.05,'2019-01-05 00:00:00'),(13,9,0.25,'2019-01-05 00:00:00'),(14,4,0.1,'2019-01-05 00:00:00'),(14,5,0.8,'2019-01-05 00:00:00'),(14,6,0.95,'2019-01-05 00:00:00'),(14,7,0.1,'2019-01-05 00:00:00');
/*!40000 ALTER TABLE `practicas_planes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precios_practicas`
--

DROP TABLE IF EXISTS `precios_practicas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `precios_practicas` (
  `cod_practica` int(10) unsigned NOT NULL,
  `fecha_desde` datetime NOT NULL,
  `valor` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_practica`,`fecha_desde`),
  CONSTRAINT `fk_practicas` FOREIGN KEY (`cod_practica`) REFERENCES `practicas` (`cod_practica`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precios_practicas`
--

LOCK TABLES `precios_practicas` WRITE;
/*!40000 ALTER TABLE `precios_practicas` DISABLE KEYS */;
INSERT INTO `precios_practicas` VALUES (1,'2018-01-03 00:00:00','240'),(1,'2019-10-10 00:00:00','280'),(1,'2019-11-13 00:00:00','320'),(2,'2018-07-12 00:00:00','90'),(2,'2019-11-13 00:00:00','140'),(3,'2019-11-13 00:00:00','210'),(4,'2019-11-13 00:00:00','100'),(5,'2019-11-13 00:00:00','120'),(6,'2019-11-13 00:00:00','130'),(7,'2019-11-13 00:00:00','160'),(8,'2019-11-13 00:00:00','290'),(9,'2019-11-13 00:00:00','190'),(10,'2019-11-13 00:00:00','270'),(14,'2019-10-10 00:00:00','130'),(15,'2019-10-10 00:00:00','290'),(16,'2019-10-10 00:00:00','286'),(17,'2019-10-10 00:00:00','215'),(18,'2019-10-10 00:00:00','900'),(19,'2019-10-10 00:00:00','980'),(20,'2019-10-10 00:00:00','110'),(21,'2019-10-10 00:00:00','1900'),(22,'2019-10-10 00:00:00','1240'),(23,'2019-10-10 00:00:00','880'),(24,'2019-11-13 00:00:00','800'),(25,'2019-10-10 00:00:00','780'),(26,'2019-10-10 00:00:00','760'),(27,'2019-11-13 00:00:00','560'),(28,'2019-11-13 00:00:00','650'),(29,'2019-11-13 00:00:00','430'),(30,'2019-11-13 00:00:00','320'),(31,'2019-11-13 00:00:00','570');
/*!40000 ALTER TABLE `precios_practicas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turnos`
--

DROP TABLE IF EXISTS `turnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `turnos` (
  `id_turno` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fecha_hora` datetime DEFAULT NULL,
  `estado` int(10) unsigned NOT NULL,
  `dni_especialista` int(10) unsigned NOT NULL,
  `id_consultorio` int(10) unsigned DEFAULT NULL,
  `dni_paciente` int(10) unsigned DEFAULT NULL,
  `observacion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_turno`),
  KEY `fk_turnos_especialista_idx` (`dni_especialista`),
  KEY `fk_turnos_consultorios_idx` (`id_consultorio`),
  KEY `fk_turnos_pacientes_planes_idx` (`dni_paciente`),
  CONSTRAINT `fk_turnos_consultorios` FOREIGN KEY (`id_consultorio`) REFERENCES `consultorios` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_turnos_especialistas` FOREIGN KEY (`dni_especialista`) REFERENCES `usuarios` (`dni`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_turnos_pacientes` FOREIGN KEY (`dni_paciente`) REFERENCES `usuarios` (`dni`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turnos`
--

LOCK TABLES `turnos` WRITE;
/*!40000 ALTER TABLE `turnos` DISABLE KEYS */;
INSERT INTO `turnos` VALUES (1,'2019-11-14 18:00:00',3,40100300,1,40200700,'Se encuentra totalmente recuperado'),(2,'2019-11-14 18:30:00',3,40100300,2,34556766,'Control aparentemente bien'),(3,'2019-11-14 19:00:00',3,40100300,1,37677877,'Analisis de sangre en condiciones'),(4,'2019-10-01 14:30:00',3,40100300,2,40900398,'Se ha limpiado toda la zona'),(5,'2019-10-05 15:00:00',3,40100300,3,38900788,'No se han encontrado perturbaciones'),(6,'2018-10-10 18:00:00',3,40100300,1,40200700,'Todo correcto'),(7,'2018-12-08 18:30:00',3,40100300,3,40200700,'Necesita otro tipo de medicamento'),(8,'2019-10-30 19:00:00',3,40100300,2,40200700,'No siguio las indicaciones'),(9,'2019-09-14 19:30:00',3,40100300,1,34556766,'Tiene que venir la proxima semana'),(10,'2019-09-16 20:00:00',3,40100300,1,34556766,'Dado de alta'),(11,'2019-09-16 14:00:00',3,40100300,5,37677877,'No tiene que hacer actividad fisica'),(12,'2019-09-16 14:30:00',3,40100300,5,40900398,'No puede comer solidos'),(13,'2019-09-16 15:00:00',3,40100300,5,38900788,'Control correcto'),(14,'2019-12-16 14:30:00',1,40100300,1,NULL,NULL),(15,'2019-12-16 15:00:00',1,40100300,1,NULL,NULL),(16,'2019-12-16 15:30:00',1,40100300,1,NULL,NULL),(17,'2019-12-17 16:00:00',1,40100300,1,NULL,NULL),(18,'2019-12-17 16:30:00',1,40100300,2,NULL,NULL),(19,'2019-12-17 17:00:00',1,40100300,2,NULL,NULL),(20,'2019-12-17 17:30:00',1,40100300,3,NULL,NULL),(21,'2019-12-18 18:00:00',1,40100300,4,NULL,NULL),(22,'2019-12-18 18:30:00',1,40100300,5,NULL,NULL),(23,'2019-12-18 19:00:00',1,40100300,1,NULL,NULL),(24,'2019-12-18 19:30:00',1,38900800,2,NULL,NULL),(25,'2019-12-18 20:00:00',1,38900800,5,NULL,NULL),(26,'2019-12-19 14:00:00',1,40100300,4,NULL,NULL),(27,'2019-12-19 14:30:00',1,40100300,3,NULL,NULL),(28,'2019-12-19 15:00:00',1,40100300,1,NULL,NULL),(29,'2019-12-19 16:30:00',1,38900800,2,NULL,NULL),(30,'2019-12-19 17:00:00',1,38900800,3,NULL,NULL),(31,'2019-12-20 14:00:00',1,40100300,2,NULL,NULL),(32,'2019-12-20 14:30:00',1,40100300,4,NULL,NULL),(33,'2019-12-20 15:00:00',1,38900800,5,NULL,NULL),(34,'2019-12-20 16:30:00',1,38900800,1,NULL,NULL),(35,'2019-12-20 17:00:00',1,38900800,2,NULL,NULL),(36,'2019-12-23 14:00:00',1,40100300,3,NULL,NULL),(37,'2019-12-23 14:40:00',1,40100300,4,NULL,NULL),(38,'2019-12-23 15:30:00',1,38900800,1,NULL,NULL),(39,'2019-12-23 16:00:00',1,38900800,2,NULL,NULL),(40,'2019-12-23 16:45:00',1,40100300,3,NULL,NULL),(41,'2019-12-23 17:45:00',1,40100300,4,NULL,NULL),(42,'2019-12-24 14:00:00',1,38900800,5,NULL,NULL),(43,'2019-12-24 14:30:00',1,38900800,1,NULL,NULL),(44,'2019-12-24 15:00:00',1,40100300,2,NULL,NULL),(45,'2019-12-26 15:30:00',2,40100300,2,40200700,NULL),(46,'2019-12-26 16:00:00',2,40100300,3,38900788,NULL),(47,'2019-12-26 16:30:00',2,40100300,4,40900398,NULL),(48,'2019-12-26 18:00:00',1,38900800,5,NULL,NULL),(49,'2019-12-26 18:30:00',1,38900800,1,NULL,NULL),(50,'2019-12-27 19:00:00',2,40100300,2,40200700,NULL),(51,'2019-12-27 19:30:00',2,40100300,3,38900788,NULL),(52,'2019-12-27 20:00:00',2,40100300,2,34556766,NULL),(53,'2019-12-27 20:45:00',1,38900800,5,NULL,NULL),(54,'2019-12-27 21:30:00',1,38900800,4,NULL,NULL),(55,'2019-12-30 14:00:00',1,40100300,3,NULL,NULL),(56,'2019-12-30 14:30:00',1,40100300,2,NULL,NULL),(57,'2019-12-30 15:50:00',2,38900800,1,34556766,NULL),(58,'2019-12-30 16:50:00',2,38900800,2,40200700,NULL),(59,'2020-01-03 14:30:00',1,40100300,1,34556766,NULL),(60,'2020-01-03 15:00:00',1,40100300,2,40200700,NULL),(61,'2020-01-03 15:30:00',1,38900800,3,NULL,NULL),(62,'2020-01-07 14:00:00',2,38900800,2,38900788,NULL),(63,'2020-01-07 14:30:00',2,40100300,1,37677877,NULL),(64,'2020-01-07 15:00:00',1,40100300,5,NULL,NULL),(65,'2020-01-07 15:30:00',1,40100300,4,NULL,NULL),(66,'2020-01-07 16:00:00',2,40100300,5,37677877,NULL),(67,'2020-01-08 17:00:00',1,38900800,3,NULL,NULL),(68,'2020-01-08 17:30:00',1,40100300,3,NULL,NULL),(69,'2020-01-08 18:00:00',1,40100300,2,NULL,NULL),(70,'2020-01-09 17:00:00',1,40100300,1,NULL,NULL),(71,'2020-01-10 18:00:00',1,40100300,2,NULL,NULL),(72,'2020-01-15 17:30:00',1,38900800,3,NULL,NULL),(73,'2020-01-15 18:00:00',1,40100300,4,NULL,NULL),(74,'2020-01-15 19:00:00',1,40100300,5,NULL,NULL),(75,'2020-01-21 14:00:00',1,40100300,1,NULL,NULL),(76,'2020-01-21 14:30:00',1,40100300,2,NULL,NULL),(77,'2020-01-21 15:00:00',1,40100300,3,NULL,NULL),(78,'2020-01-22 15:00:00',2,40100300,4,34556766,NULL),(79,'2020-01-22 15:30:00',2,40100300,5,34556766,NULL),(80,'2020-01-23 16:00:00',1,40100300,1,NULL,NULL),(81,'2020-01-23 16:30:00',1,40100300,2,NULL,NULL),(82,'2020-01-23 17:00:00',1,40100300,3,NULL,NULL),(83,'2020-01-24 19:00:00',1,40100300,4,NULL,NULL),(84,'2019-12-17 17:30:00',4,40100300,3,40200700,NULL),(85,'2019-12-20 14:00:00',4,40100300,2,40200700,NULL),(86,'2019-12-14 14:00:00',2,40100300,1,40200700,NULL),(87,'2019-12-15 14:00:00',2,40100300,2,34556766,NULL),(88,'2019-12-13 14:30:00',2,40100300,2,34556766,NULL),(89,'2019-12-16 16:00:00',2,40100300,2,40200700,NULL);
/*!40000 ALTER TABLE `turnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turnos_practicas`
--

DROP TABLE IF EXISTS `turnos_practicas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `turnos_practicas` (
  `id_turno` int(10) unsigned NOT NULL,
  `cod_practica` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_turno`,`cod_practica`),
  KEY `fk_turnos_practicas_practicas_idx` (`cod_practica`),
  CONSTRAINT `fk_turnos_practicas_practicas` FOREIGN KEY (`cod_practica`) REFERENCES `practicas` (`cod_practica`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_turnos_practicas_turnos` FOREIGN KEY (`id_turno`) REFERENCES `turnos` (`id_turno`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turnos_practicas`
--

LOCK TABLES `turnos_practicas` WRITE;
/*!40000 ALTER TABLE `turnos_practicas` DISABLE KEYS */;
INSERT INTO `turnos_practicas` VALUES (1,1),(4,1),(5,1),(12,1),(13,1),(1,2),(2,2),(3,2),(4,2),(5,2),(7,2),(10,2),(11,2),(13,2),(1,3),(2,3),(7,3),(9,3),(12,3),(13,3),(88,14),(5,15),(9,16),(11,16),(5,17),(6,17),(11,17),(5,18);
/*!40000 ALTER TABLE `turnos_practicas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuarios` (
  `dni` int(10) unsigned NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `apellido` varchar(60) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `tipo_usuario` int(10) unsigned NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(60) NOT NULL,
  `cod_especialidad` int(10) unsigned DEFAULT NULL,
  `nro_afiliado` varchar(45) DEFAULT NULL,
  `id_plan` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`dni`),
  KEY `fk_usuarios_especialidades_idx` (`cod_especialidad`),
  KEY `fk_usuarios_planes_idx` (`id_plan`),
  CONSTRAINT `fk_usuarios_especialidades` FOREIGN KEY (`cod_especialidad`) REFERENCES `especialidades` (`cod_especialidad`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_usuarios_planes` FOREIGN KEY (`id_plan`) REFERENCES `planes` (`id_plan`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (16755877,'Matias','Gonzalez','1945-11-04',2,'1234','matiasmatias@hotmail.es',3,NULL,NULL),(19800654,'Roman','Rodriguez','1965-11-12',2,'1234','roro@hotmail.com',3,NULL,NULL),(29800700,'Raul','Juares','1976-10-01',2,'1234','raurau@live.es',1,NULL,NULL),(30800100,'Pedro','Gomez','1996-10-10',2,'1234','pedrogomez@outlook.com',2,NULL,NULL),(34556766,'Guillermina','Lopez','1995-04-20',1,'1234','guille90@hotmail.com',NULL,'119981111',1),(37677877,'Carla','Opera','1997-01-08',1,'1234','carla@hotmail.com',NULL,'111122009',1),(38900788,'Maria','Olmedo','1998-03-02',1,'1234','mariamaria@hotmail.com',NULL,'341321222',1),(38900800,'Martin','Torrente','1990-03-09',2,'1234','martin9@hotmail.es',2,NULL,NULL),(40100300,'Juan','Fogliato','1800-07-09',2,'1234','juanjuan@gmail.com',1,NULL,NULL),(40200700,'Natalia','Fernandez','1900-08-27',1,'1234','natalia77@live.com',NULL,'144321955',3),(40900398,'Lucia','Hurman','1987-05-05',1,'1234','lulucia@hotmail.com',NULL,'778666628',3);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'java_turnos'
--
/*!50003 DROP FUNCTION IF EXISTS `getValorActualPlanPractica` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getValorActualPlanPractica`(codPrac int, codPlan int) RETURNS decimal(11,2)
BEGIN
drop temporary table if exists valores_actuales;
create temporary table valores_actuales(
select id_plan, cod_practica, MAX(fechadesde) fecha
from practicas_planes
where fechadesde < current_date()
group by 1,2
);
select descuento into @desc
from practicas_planes pp
inner join valores_actuales va on pp.cod_practica = va.cod_practica 
								and pp.id_plan = va.id_plan
								and fechadesde = fecha
where pp.id_plan = codPlan and pp.cod_practica = codPrac;
RETURN @desc;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `getValorActualPractica` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getValorActualPractica`(cod int) RETURNS decimal(11,2)
BEGIN
drop temporary table if exists valores_actuales_practicas;
create temporary table valores_actuales_practicas(
select cod_practica, MAX(fecha_desde) fecha
from precios_practicas 
where fecha_desde < current_date()
group by 1
);

select valor into @valor
from precios_practicas p
inner join valores_actuales_practicas vap on p.cod_practica = vap.cod_practica
										and p.fecha_desde = vap.fecha
inner join practicas pr on pr.cod_practica = p.cod_practica
where p.cod_practica = cod
;

RETURN @valor;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-14 22:15:19
