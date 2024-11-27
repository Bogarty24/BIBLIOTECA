-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: biblioteca
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `id_trabajador` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `departamento` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_trabajador`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (1,'Juan','Pérez','123456789','juan.perez@example.com','Recursos Humanos'),(2,'María','Gómez','987654321','maria.gomez@example.com','Finanzas'),(3,'Carlos','Ruiz','456123789','carlos.ruiz@example.com','Marketing');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libros`
--

DROP TABLE IF EXISTS `libros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libros` (
  `id_libro` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `autor` varchar(255) NOT NULL,
  `isbn` varchar(20) NOT NULL,
  `genero` varchar(50) DEFAULT NULL,
  `editorial` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_libro`),
  UNIQUE KEY `isbn` (`isbn`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros`
--

LOCK TABLES `libros` WRITE;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` VALUES (1,'Don Quijote de la Manchaa','Miguel de Cervantes','978-3-16-148410-0','Novela','--Seleccione una editorial --','Madrid'),(2,'Cien a?os de soledad','Gabriel Garc?a M?rquez','978-84-376-0494-7','Realismo M?gico','Editorial Sudamericana','Bogot?'),(3,'1984','George Orwell','978-0-452-28423-4','Distop?a','Penguin Books','Londres'),(4,'El se?or de los anillos','J.R.R. Tolkien','978-0-261-10236-2','Fantas?a','HarperCollins','Londres'),(5,'Orgullo y prejuicio','Jane Austen','978-1-5011-7683-6','Rom?ntica','Penguin Classics','Londres'),(6,'La sombra del viento','Carlos Ruiz Zaf?n','978-0-14-303490-2','Misterio','Editorial Planeta','Barcelona'),(7,'Fahrenheit 451','Ray Bradbury','978-0-7432-4722-1','Ciencia Ficci?n','Simon & Schuster','Los ?ngeles'),(8,'El Alquimista','Paulo Coelho','978-0-06-112241-5','Ficci?n','HarperCollins','R?o de Janeiro'),(9,'a','a','2','1',NULL,NULL),(10,'a','a','1','a',NULL,NULL);
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos_multas`
--

DROP TABLE IF EXISTS `pagos_multas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagos_multas` (
  `id_recibo` int(11) NOT NULL AUTO_INCREMENT,
  `id_socio` int(11) DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT 0.00,
  `estado` enum('Pendiente','Pagado') DEFAULT 'Pendiente',
  `fecha_pago` date DEFAULT NULL,
  `monto_adicional` decimal(10,2) DEFAULT 0.00,
  PRIMARY KEY (`id_recibo`),
  KEY `id_socio` (`id_socio`),
  CONSTRAINT `pagos_multas_ibfk_1` FOREIGN KEY (`id_socio`) REFERENCES `socios` (`id_socio`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos_multas`
--

LOCK TABLES `pagos_multas` WRITE;
/*!40000 ALTER TABLE `pagos_multas` DISABLE KEYS */;
INSERT INTO `pagos_multas` VALUES (2,2,30.00,'Pendiente',NULL,5.00),(3,3,100.00,'Pagado','2024-11-20',10.00),(4,4,25.00,'Pendiente',NULL,2.50),(5,5,60.00,'Pagado','2024-11-18',7.00),(6,4,0.00,'Pendiente',NULL,93.00),(7,14,0.00,'Pendiente',NULL,93.00);
/*!40000 ALTER TABLE `pagos_multas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamos`
--

DROP TABLE IF EXISTS `prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamos` (
  `id_prestamo` int(11) NOT NULL AUTO_INCREMENT,
  `id_libro` int(11) DEFAULT NULL,
  `id_socio` int(11) DEFAULT NULL,
  `fecha_prestamo` date NOT NULL,
  `fecha_devolucion` date DEFAULT NULL,
  PRIMARY KEY (`id_prestamo`),
  KEY `id_socio` (`id_socio`),
  KEY `id_libro` (`id_libro`),
  CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id_libro`),
  CONSTRAINT `prestamos_ibfk_2` FOREIGN KEY (`id_socio`) REFERENCES `socios` (`id_socio`),
  CONSTRAINT `prestamos_ibfk_3` FOREIGN KEY (`id_socio`) REFERENCES `socios` (`id_socio`),
  CONSTRAINT `prestamos_ibfk_4` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id_libro`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamos`
--

LOCK TABLES `prestamos` WRITE;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
INSERT INTO `prestamos` VALUES (17,4,4,'2023-10-27','2024-11-27'),(19,5,5,'2024-04-04','2024-11-27'),(20,6,13,'2024-04-04','2024-11-27'),(21,4,4,'2024-04-04','2024-06-04'),(22,10,14,'2024-04-04','2024-06-04');
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socios`
--

DROP TABLE IF EXISTS `socios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `socios` (
  `id_socio` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_socio`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socios`
--

LOCK TABLES `socios` WRITE;
/*!40000 ALTER TABLE `socios` DISABLE KEYS */;
INSERT INTO `socios` VALUES (1,'Juan Pérez','Calle Principal 123','5551234567','juan.perez@example.com'),(2,'María López','Avenida Secundaria 456','5557654322','maria.lopez@example.com'),(3,'Carlos Gómez','Paseo de las Flores 789','5559876543','carlos.gomez@example.com'),(4,'Ana Torres','Calle Nueva 111','5551112233','ana.torres@example.com'),(5,'Luis Díaz','Avenida del Sol 222','5552223344','luis.diaz@example.com'),(13,'Juan Pérez','Calle Falsa 123','123456789','juan@example.com'),(14,'a','a','a','a');
/*!40000 ALTER TABLE `socios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(50) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `tipo_usuario` enum('Administrador','Usuario') NOT NULL,
  `email` varchar(100) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin1','2c16289512dc84060eff07cfec46da67549c80cbc3c5b40dcab8a8fa78fbde14','Administrador','admin1@biblioteca.com','111222333','2024-10-31 23:00:00'),(2,'user1','4284bafb3a419dff9c5d49d80b46f7418b5edcfbf14ed0141babed37c5e9f25b','Usuario','user1@biblioteca.com','444555666','2024-11-01 23:00:00'),(3,'jewel','647e485f817f9e9d59260b2461ff1a3bd0c89865a85dee70218594e6865d727b','Administrador','a','1','2024-11-26 23:18:19'),(4,'a','b80de1ab9d0903823cc10fd5b2ba57616b339a69313b4d3e23363dbea6579cd6','Administrador','a','1','2024-11-26 23:20:34'),(5,'b','b80de1ab9d0903823cc10fd5b2ba57616b339a69313b4d3e23363dbea6579cd6','Administrador','a','1','2024-11-27 00:09:30'),(6,'jewel','da3811154d59c4267077ddd8bb768fa9b06399c486e1fc00485116b57c9872f5','Administrador','a','1','2024-11-27 00:32:07'),(7,'c','1','Administrador','a','1','2024-11-27 00:57:09'),(8,'d','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','Administrador','d','1','2024-11-27 04:44:20');
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

-- Dump completed on 2024-11-27  5:59:06
