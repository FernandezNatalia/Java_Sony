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
INSERT INTO `consultorios` VALUES (1,'A',NULL),(2,'B',NULL),(3,'C',NULL),(4,'D',NULL),(5,'E',NULL);
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
INSERT INTO `especialidades` VALUES (1,'Cirugia Oncologica'),(2,'Endocrinologia'),(3,'Gastroenterologia'),(4,'Geriatria'),(5,'Hepatologia'),(6,'Nefrologia'),(7,'Obstetricia'),(8,'Oftamologia');
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
INSERT INTO `especialidades_practicas` VALUES (1,1),(2,1),(3,1),(4,2),(5,2),(6,2),(7,3),(8,3),(9,3),(10,3);
/*!40000 ALTER TABLE `especialidades_practicas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horarios`
--

DROP TABLE IF EXISTS `horarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `horarios` (
  `dia` int(11) NOT NULL,
  `dni_especialista` int(10) unsigned NOT NULL,
  `turno` int(10) unsigned NOT NULL,
  `hora_desde` time NOT NULL,
  `hora_hasta` time NOT NULL,
  PRIMARY KEY (`dia`,`turno`,`dni_especialista`),
  KEY `fk_horarios_usuarios_idx` (`dni_especialista`),
  CONSTRAINT `fk_horarios_usuarios` FOREIGN KEY (`dni_especialista`) REFERENCES `usuarios` (`dni`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horarios`
--

LOCK TABLES `horarios` WRITE;
/*!40000 ALTER TABLE `horarios` DISABLE KEYS */;
INSERT INTO `horarios` VALUES (1,38900800,1,'08:00:00','09:30:00'),(1,40100300,1,'08:00:00','09:30:00'),(1,37800700,2,'17:00:00','19:00:00'),(1,40100300,2,'16:00:00','18:00:00'),(1,37800700,3,'22:00:00','23:30:00'),(1,40100300,3,'21:30:00','23:00:00'),(2,38900800,1,'08:00:00','10:00:00'),(2,40100300,1,'08:00:00','09:30:00'),(2,37800700,2,'17:00:00','18:30:00'),(2,40100300,2,'17:00:00','19:00:00'),(3,38900800,1,'08:30:00','09:30:00'),(3,40100300,1,'08:30:00','10:00:00'),(4,40100300,1,'08:00:00','10:00:00'),(4,37800700,2,'17:00:00','18:30:00'),(5,40100300,3,'22:00:00','23:00:00');
/*!40000 ALTER TABLE `horarios` ENABLE KEYS */;
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
INSERT INTO `obras_sociales` VALUES ('30-10504876-5','ESENCIAL'),('30-15876543-4','Accord Salud'),('30-20987654-4','Swiss Medical'),('30-21008765-5','OSDE'),('30-21098732-4','LUIS PASTEUR'),('30-23456328-5','Union Personal');
/*!40000 ALTER TABLE `obras_sociales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacientes_planes`
--

DROP TABLE IF EXISTS `pacientes_planes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pacientes_planes` (
  `id_plan` int(10) unsigned NOT NULL,
  `dni_paciente` int(10) unsigned NOT NULL,
  `nro_afiliado` varchar(45) NOT NULL,
  PRIMARY KEY (`id_plan`,`dni_paciente`),
  KEY `fk_pacientes_planes_usuarios_idx` (`dni_paciente`),
  CONSTRAINT `fk_pacientes_planes_planes` FOREIGN KEY (`id_plan`) REFERENCES `planes` (`id_plan`),
  CONSTRAINT `fk_pacientes_planes_usuarios` FOREIGN KEY (`dni_paciente`) REFERENCES `usuarios` (`dni`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacientes_planes`
--

LOCK TABLES `pacientes_planes` WRITE;
/*!40000 ALTER TABLE `pacientes_planes` DISABLE KEYS */;
INSERT INTO `pacientes_planes` VALUES (1,34556766,'101'),(1,37677877,'2089'),(2,38900788,'5908'),(2,40200700,'9776'),(3,40900398,'90001');
/*!40000 ALTER TABLE `pacientes_planes` ENABLE KEYS */;
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
INSERT INTO `planes` VALUES (1,'30-10504876-5','Premium'),(2,'30-10504876-5','Clasico'),(3,'30-10504876-5','Global'),(5,'30-15876543-4','Acord310'),(6,'30-15876543-4','Acord210'),(7,'30-20987654-4','SMG01'),(8,'30-20987654-4','SMG02'),(9,'30-20987654-4','SMG10'),(10,'30-20987654-4','SMG20'),(11,'30-21008765-5','BM200'),(12,'30-21008765-5','BM300'),(13,'30-21098732-4','Clasico'),(14,'30-21098732-4','Global'),(15,'30-21098732-4','Smart'),(16,'30-23456328-5','Clasico'),(17,'30-23456328-5','Premium');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practicas`
--

LOCK TABLES `practicas` WRITE;
/*!40000 ALTER TABLE `practicas` DISABLE KEYS */;
INSERT INTO `practicas` VALUES (1,'Tratamiento integral del acúfeno'),(2,'Tratamiento integral del equilibrio'),(3,'Rehabilitación del Lenguaje'),(4,'Rehabilitación de trastornos cognitivos'),(5,'Trastornos de deglución'),(6,'Doppler cardiaco'),(7,'Doppler Abdominal'),(8,'Electrofisologia'),(9,'Radiologia'),(10,'Dermatoscopia');
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
  `fecha_desde` date DEFAULT NULL,
  `precio` double unsigned NOT NULL,
  PRIMARY KEY (`id_plan`,`cod_practica`),
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
INSERT INTO `practicas_planes` VALUES (1,1,NULL,100),(1,2,NULL,100),(1,3,NULL,120),(1,4,NULL,130),(1,5,NULL,150),(2,1,NULL,90),(2,2,NULL,80),(2,3,NULL,85),(2,4,NULL,90),(2,5,NULL,95),(3,1,NULL,60),(3,2,NULL,50),(3,3,NULL,55),(3,4,NULL,40),(3,5,NULL,35),(5,1,NULL,200),(5,2,NULL,210),(5,6,NULL,100),(5,7,NULL,150),(6,1,NULL,80),(6,2,NULL,60),(6,6,NULL,15),(6,7,NULL,90);
/*!40000 ALTER TABLE `practicas_planes` ENABLE KEYS */;
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
  `fecha` date NOT NULL,
  `hora` time DEFAULT NULL,
  `duracion` int(10) unsigned DEFAULT NULL,
  `estado` int(10) unsigned NOT NULL,
  `dni_especialista` int(10) unsigned NOT NULL,
  `id_consultorio` int(10) unsigned DEFAULT NULL,
  `dni_paciente` int(10) unsigned DEFAULT NULL,
  `id_plan` int(10) unsigned DEFAULT NULL,
  `observacion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_turno`),
  KEY `fk_turnos_especialista_idx` (`dni_especialista`),
  KEY `fk_turnos_consultorios_idx` (`id_consultorio`),
  KEY `fk_turnos_pacientes_planes_idx` (`dni_paciente`,`id_plan`),
  KEY `fk_turnos_planes_idx` (`id_plan`),
  CONSTRAINT `fk_turnos_consultorios` FOREIGN KEY (`id_consultorio`) REFERENCES `consultorios` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_turnos_especialistas` FOREIGN KEY (`dni_especialista`) REFERENCES `usuarios` (`dni`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_turnos_pacientes` FOREIGN KEY (`dni_paciente`) REFERENCES `usuarios` (`dni`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_turnos_planes` FOREIGN KEY (`id_plan`) REFERENCES `planes` (`id_plan`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turnos`
--

LOCK TABLES `turnos` WRITE;
/*!40000 ALTER TABLE `turnos` DISABLE KEYS */;
INSERT INTO `turnos` VALUES (61,'2023-03-20 13:15:00','2019-09-30','15:50:00',NULL,2,40100300,1,40200700,2,NULL),(62,'2019-09-11 20:15:00','2019-09-11','15:20:00',NULL,2,40100300,2,40200700,2,NULL),(63,'2018-05-09 00:00:15','2018-05-09','00:00:15',NULL,3,40100300,1,40200700,2,'Se ha limpiado toda la zona'),(64,'2018-09-09 00:00:15','2018-09-09','00:00:15',NULL,3,40100300,2,40200700,2,'Control aparentemente bien'),(65,'2023-03-20 09:15:00','2019-09-29','14:30:00',NULL,2,40100300,3,40200700,2,''),(66,NULL,'2019-09-30',NULL,NULL,1,38900800,1,NULL,NULL,NULL),(67,'2018-04-10 00:00:15','2018-04-10','00:00:15',NULL,3,40100300,3,40200700,2,'No ha presentado ningun sintoma.'),(68,NULL,'2019-09-11',NULL,NULL,1,40100300,5,NULL,NULL,NULL),(69,NULL,'2019-09-11',NULL,NULL,1,40100300,4,NULL,NULL,NULL),(70,NULL,'2019-09-11',NULL,NULL,1,38900800,1,NULL,NULL,NULL),(71,'2019-10-05 14:30:00','2019-10-05','14:30:00',NULL,2,40100300,3,40900398,3,NULL),(72,'2019-10-05 18:46:00','2019-09-30','18:46:00',NULL,2,40100300,3,38900788,2,NULL),(73,'2019-10-05 12:00:00','2019-09-30','12:00:00',NULL,2,40100300,3,37677877,1,NULL),(74,'2121-10-05 21:21:00','2121-09-30','21:21:00',NULL,4,40100300,3,NULL,NULL,NULL),(75,'2000-10-05 12:12:00','2000-12-12','12:12:00',NULL,4,40100300,3,NULL,NULL,NULL);
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
INSERT INTO `turnos_practicas` VALUES (63,1),(64,1),(67,1),(63,2),(64,2),(63,3);
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
  PRIMARY KEY (`dni`),
  KEY `fk_usuarios_especialidades_idx` (`cod_especialidad`),
  CONSTRAINT `fk_usuarios_especialidades` FOREIGN KEY (`cod_especialidad`) REFERENCES `especialidades` (`cod_especialidad`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (16755877,'Matias','Gonzalez','1945-11-04',2,'1234','matiasmatias@hotmail.es',3),(19800654,'Roman','Rodriguez','1965-11-12',2,'1234','roro@hotmail.com',3),(29800700,'Raul','Juares','1976-10-01',2,'1234','raurau@live.es',2),(30030031,'Adminis','Trador','1900-01-01',3,'1234','admin@hotmail.com',NULL),(30800100,'Pedro','Gomez','1996-10-10',2,'1234','pedrogomez@outlook.com',2),(34556766,'Guillermina','Lopez','1995-04-20',1,'1234','guille90@hotmail.com',NULL),(37677877,'Carla','Opera','1997-01-08',1,'1234','carla@hotmail.com',NULL),(37800700,'Luca','Especial','1987-06-05',2,'1234','lucaluca@hotmail.com',1),(38900788,'Maria','Olmedo','1998-03-02',1,'1234','mariamaria@hotmail.com',NULL),(38900800,'Martin','Torrente','1990-03-09',2,'1234','martin9@hotmail.es',1),(40100300,'Juan','Fogliato','1800-07-09',2,'1234','juanjuan@hotmail.com',1),(40200700,'Natalia','Fernandez','1900-08-27',1,'1234','natalia@hotmail.com',NULL),(40900398,'Lucia','Hurman','1987-05-05',1,'1234','lulucia@hotmail.com',NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-07 18:37:02
