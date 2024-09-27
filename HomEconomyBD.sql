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
  `idAhorro` int NOT NULL,
  `cantidadAhorrada` int NOT NULL,
  `ultimoMovimiento` date NOT NULL,
  PRIMARY KEY (`idAhorro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `idCategoria` int NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contactarequipo`
--

DROP TABLE IF EXISTS `contactarequipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contactarequipo` (
  `idMensaje` int NOT NULL,
  `idUsuarioM` int NOT NULL,
  `mensaje` varchar(45) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`idMensaje`),
  KEY `idUsuarioM_idx` (`idUsuarioM`),
  CONSTRAINT `idUsuarioM` FOREIGN KEY (`idUsuarioM`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `familia`
--

DROP TABLE IF EXISTS `familia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `familia` (
  `idFamilia` int NOT NULL,
  `idUsuario` int NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Relacion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idFamilia`),
  KEY `idUsuario_idx` (`idUsuario`),
  CONSTRAINT `idUsuarioF` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gastos`
--

DROP TABLE IF EXISTS `gastos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gastos` (
  `idGasto` int NOT NULL,
  `idUsuarioG` int NOT NULL,
  `idFamiliarG` int DEFAULT NULL,
  `idCategoriaG` int DEFAULT NULL,
  `cantidad` int NOT NULL,
  `fecha` date NOT NULL,
  `comentario` varchar(45) DEFAULT NULL,
  `comprobante` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idGasto`),
  KEY `idFamiliar_idx` (`idFamiliarG`),
  KEY `idCategoria_idx` (`idCategoriaG`),
  KEY `idUsuario_idx` (`idUsuarioG`),
  CONSTRAINT `idCategoriaG` FOREIGN KEY (`idCategoriaG`) REFERENCES `categorias` (`idCategoria`),
  CONSTRAINT `idFamiliarG` FOREIGN KEY (`idFamiliarG`) REFERENCES `familia` (`idFamilia`),
  CONSTRAINT `idUsuarioG` FOREIGN KEY (`idUsuarioG`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `idFamiliar` int DEFAULT NULL,
  PRIMARY KEY (`idInforme`),
  KEY `idFamiliaInf_idx` (`idFamiliar`),
  KEY `idCategoriaInf_idx` (`idCategoria`),
  CONSTRAINT `idCategoriaInf` FOREIGN KEY (`idCategoria`) REFERENCES `categorias` (`idCategoria`),
  CONSTRAINT `idFamiliaInf` FOREIGN KEY (`idFamiliar`) REFERENCES `familia` (`idFamilia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ingresos`
--

DROP TABLE IF EXISTS `ingresos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingresos` (
  `idIngreso` int NOT NULL,
  `idUsuarioI` int NOT NULL,
  `idFamiliarI` int DEFAULT NULL,
  `idCategoriaI` int DEFAULT NULL,
  `cantidad` int NOT NULL,
  `fecha` date NOT NULL,
  `comentario` varchar(45) DEFAULT NULL,
  `comprobante` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idIngreso`),
  KEY `idUsuarioI_idx` (`idUsuarioI`),
  KEY `idFamiliar_idx` (`idFamiliarI`),
  KEY `idCategoria_idx` (`idCategoriaI`),
  CONSTRAINT `idCategoriaI` FOREIGN KEY (`idCategoriaI`) REFERENCES `categorias` (`idCategoria`),
  CONSTRAINT `idFamiliarI` FOREIGN KEY (`idFamiliarI`) REFERENCES `familia` (`idFamilia`),
  CONSTRAINT `idUsuarioI` FOREIGN KEY (`idUsuarioI`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `idMovimiento` int NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `contraseña` varchar(45) NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-27 19:42:08
