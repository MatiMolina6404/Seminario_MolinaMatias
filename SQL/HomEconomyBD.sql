-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: homeconomydb
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ahorros`
--

DROP TABLE IF EXISTS `ahorros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ahorros` (
  `idAhorro` int NOT NULL AUTO_INCREMENT,
  `concepto` varchar(45) NOT NULL,
  `cantidad` int NOT NULL,
  `fecha` date NOT NULL,
  `idUsuarioAho` int NOT NULL,
  PRIMARY KEY (`idAhorro`),
  KEY `idUsuarioAho_idx` (`idUsuarioAho`),
  CONSTRAINT `idUsuarioAho` FOREIGN KEY (`idUsuarioAho`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `idCategoria` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `idUsuarioCat` int NOT NULL,
  PRIMARY KEY (`idCategoria`),
  KEY `idUsuarioCat_idx` (`idUsuarioCat`),
  CONSTRAINT `idUsuarioCat` FOREIGN KEY (`idUsuarioCat`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contactarequipo`
--

DROP TABLE IF EXISTS `contactarequipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contactarequipo` (
  `idMensaje` int NOT NULL AUTO_INCREMENT,
  `idUsuarioM` int NOT NULL,
  `mensaje` varchar(45) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`idMensaje`),
  KEY `idUsuarioM_idx` (`idUsuarioM`),
  CONSTRAINT `idUsuarioM` FOREIGN KEY (`idUsuarioM`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `familia`
--

DROP TABLE IF EXISTS `familia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `familia` (
  `idFamilia` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Relacion` varchar(45) DEFAULT NULL,
  `idUsuario` int NOT NULL,
  PRIMARY KEY (`idFamilia`),
  KEY `idUsuario_idx` (`idUsuario`),
  CONSTRAINT `idUsuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gastos`
--

DROP TABLE IF EXISTS `gastos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gastos` (
  `idUsuarioG` int NOT NULL,
  `idGasto` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `cantidad` int NOT NULL,
  `idCategoriaG` int DEFAULT NULL,
  `comentario` varchar(45) DEFAULT NULL,
  `idFamiliarG` int DEFAULT NULL,
  `comprobante` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idGasto`),
  KEY `idFamiliar_idx` (`idFamiliarG`),
  KEY `idCategoria_idx` (`idCategoriaG`),
  KEY `idUsuario_idx` (`idUsuarioG`),
  CONSTRAINT `idCategoriaG` FOREIGN KEY (`idCategoriaG`) REFERENCES `categorias` (`idCategoria`),
  CONSTRAINT `idUsuarioG` FOREIGN KEY (`idUsuarioG`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `informes`
--

DROP TABLE IF EXISTS `informes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `informes` (
  `idInforme` int NOT NULL,
  `tipoInforme` varchar(45) NOT NULL,
  `fechaInicial` date NOT NULL,
  `fechaFinal` date NOT NULL,
  `idCategoria` int DEFAULT NULL,
  `idFamilia` int DEFAULT NULL,
  PRIMARY KEY (`idInforme`),
  KEY `idCategoriaInf_idx` (`idCategoria`),
  KEY `idFamiliar` (`idFamilia`),
  CONSTRAINT `idCategoria` FOREIGN KEY (`idCategoria`) REFERENCES `categorias` (`idCategoria`),
  CONSTRAINT `idFamiliar` FOREIGN KEY (`idFamilia`) REFERENCES `familia` (`idFamilia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ingresos`
--

DROP TABLE IF EXISTS `ingresos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingresos` (
  `idUsuarioI` int NOT NULL,
  `idIngreso` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `cantidad` int NOT NULL,
  `idCategoriaI` int DEFAULT NULL,
  `comentario` varchar(45) DEFAULT NULL,
  `idFamiliarI` int DEFAULT NULL,
  `comprobante` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idIngreso`),
  KEY `idUsuarioI_idx` (`idUsuarioI`),
  KEY `idFamiliar_idx` (`idFamiliarI`),
  KEY `idCategoria_idx` (`idCategoriaI`),
  CONSTRAINT `idCategoriaI` FOREIGN KEY (`idCategoriaI`) REFERENCES `categorias` (`idCategoria`),
  CONSTRAINT `idUsuarioI` FOREIGN KEY (`idUsuarioI`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `movimientos`
--

DROP TABLE IF EXISTS `movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimientos` (
  `idMovimiento` int NOT NULL,
  `idIngreso` int DEFAULT NULL,
  `idGasto` int DEFAULT NULL,
  `cantidad` int NOT NULL,
  `fecha` date NOT NULL,
  `cantidadAnterior` int NOT NULL,
  `cantidadPosterior` int NOT NULL,
  PRIMARY KEY (`idMovimiento`),
  KEY `idIngreso_idx` (`idIngreso`),
  KEY `idGasto_idx` (`idGasto`),
  CONSTRAINT `idGasto` FOREIGN KEY (`idGasto`) REFERENCES `gastos` (`idGasto`),
  CONSTRAINT `idIngreso` FOREIGN KEY (`idIngreso`) REFERENCES `ingresos` (`idIngreso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `movimientosahorros`
--

DROP TABLE IF EXISTS `movimientosahorros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimientosahorros` (
  `idMovimiento` int NOT NULL AUTO_INCREMENT,
  `idUsuarioMov` int NOT NULL,
  `idAhorro` int NOT NULL,
  `tipoMovimiento` varchar(45) NOT NULL,
  `cantidad` int NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`idMovimiento`),
  KEY `idUsuarioMov_idx` (`idUsuarioMov`),
  KEY `idAhorro_idx` (`idAhorro`),
  CONSTRAINT `idAhorro` FOREIGN KEY (`idAhorro`) REFERENCES `ahorros` (`idAhorro`),
  CONSTRAINT `idUsuarioMov` FOREIGN KEY (`idUsuarioMov`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `contraseña` varchar(45) NOT NULL,
  `dineroInicial` int NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-14 16:38:12
