-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.5.37 - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              8.3.0.4770
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura do banco de dados para shopcar
DROP DATABASE IF EXISTS `shopcar`;
CREATE DATABASE IF NOT EXISTS `shopcar` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `shopcar`;


-- Copiando estrutura para tabela shopcar.cliente
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `clienteId` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`clienteId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela shopcar.cliente: ~0 rows (aproximadamente)
DELETE FROM `cliente`;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;


-- Copiando estrutura para tabela shopcar.cor
DROP TABLE IF EXISTS `cor`;
CREATE TABLE IF NOT EXISTS `cor` (
  `corId` int(11) NOT NULL AUTO_INCREMENT,
  `cor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`corId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela shopcar.cor: ~3 rows (aproximadamente)
DELETE FROM `cor`;
/*!40000 ALTER TABLE `cor` DISABLE KEYS */;
INSERT INTO `cor` (`corId`, `cor`) VALUES
	(1, 'Vermelho'),
	(6, 'Azul');
/*!40000 ALTER TABLE `cor` ENABLE KEYS */;


-- Copiando estrutura para tabela shopcar.itempedido
DROP TABLE IF EXISTS `itempedido`;
CREATE TABLE IF NOT EXISTS `itempedido` (
  `itemPedidoId` bigint(20) NOT NULL AUTO_INCREMENT,
  `precoItem` decimal(19,2) DEFAULT NULL,
  `pedidoId` bigint(20) DEFAULT NULL,
  `veiculoPlaca` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`itemPedidoId`),
  KEY `FK_lkbhstrg6hbkpsorxyr7wxm36` (`pedidoId`),
  KEY `FK_ggi1ll941a1kv78omph60y6k9` (`veiculoPlaca`),
  CONSTRAINT `FK_ggi1ll941a1kv78omph60y6k9` FOREIGN KEY (`veiculoPlaca`) REFERENCES `veiculo` (`placa`),
  CONSTRAINT `FK_lkbhstrg6hbkpsorxyr7wxm36` FOREIGN KEY (`pedidoId`) REFERENCES `pedido` (`pedidoId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela shopcar.itempedido: ~0 rows (aproximadamente)
DELETE FROM `itempedido`;
/*!40000 ALTER TABLE `itempedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `itempedido` ENABLE KEYS */;


-- Copiando estrutura para tabela shopcar.marca
DROP TABLE IF EXISTS `marca`;
CREATE TABLE IF NOT EXISTS `marca` (
  `marcaId` int(11) NOT NULL AUTO_INCREMENT,
  `marca` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`marcaId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela shopcar.marca: ~3 rows (aproximadamente)
DELETE FROM `marca`;
/*!40000 ALTER TABLE `marca` DISABLE KEYS */;
INSERT INTO `marca` (`marcaId`, `marca`) VALUES
	(1, 'Ford'),
	(2, 'Fiat'),
	(3, 'Chevrolet'),
	(4, 'Hyundai');
/*!40000 ALTER TABLE `marca` ENABLE KEYS */;


-- Copiando estrutura para tabela shopcar.modelo
DROP TABLE IF EXISTS `modelo`;
CREATE TABLE IF NOT EXISTS `modelo` (
  `modeloId` int(11) NOT NULL AUTO_INCREMENT,
  `modelo` varchar(255) DEFAULT NULL,
  `versao` varchar(255) DEFAULT NULL,
  `marcaid` int(11) DEFAULT NULL,
  PRIMARY KEY (`modeloId`),
  KEY `FK_o2lfr121g3lum0p87ju6ag09n` (`marcaid`),
  CONSTRAINT `FK_o2lfr121g3lum0p87ju6ag09n` FOREIGN KEY (`marcaid`) REFERENCES `marca` (`marcaId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela shopcar.modelo: ~8 rows (aproximadamente)
DELETE FROM `modelo`;
/*!40000 ALTER TABLE `modelo` DISABLE KEYS */;
INSERT INTO `modelo` (`modeloId`, `modelo`, `versao`, `marcaid`) VALUES
	(1, 'Fiesta', 'Hatch Rocam 1.0 (Flex) 2014', 1),
	(2, 'Fiesta', 'Hatch S Plus 1.0 RoCam (Flex) 2014', 1),
	(3, 'Fusion', '2.5 16V iVCT (Flex) (Aut) 2015', 1),
	(4, 'Palio', 'Fire 1.0 (Flex) 2p 2015', 2),
	(5, 'Siena', 'EL 1.0 Fire (Flex) 2015', 2),
	(6, 'Uno', 'Vivace 1.0 (Flex) 2p 2015', 2),
	(7, 'Celta', '1.0 LT (Flex) 2015', 3),
	(13, 'Hahah!', 'w', 4);
/*!40000 ALTER TABLE `modelo` ENABLE KEYS */;


-- Copiando estrutura para tabela shopcar.pedido
DROP TABLE IF EXISTS `pedido`;
CREATE TABLE IF NOT EXISTS `pedido` (
  `pedidoId` bigint(20) NOT NULL AUTO_INCREMENT,
  `clienteId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pedidoId`),
  KEY `FK_tin77mpllampcv93mn161ije6` (`clienteId`),
  CONSTRAINT `FK_tin77mpllampcv93mn161ije6` FOREIGN KEY (`clienteId`) REFERENCES `cliente` (`clienteId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela shopcar.pedido: ~0 rows (aproximadamente)
DELETE FROM `pedido`;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;


-- Copiando estrutura para tabela shopcar.veiculo
DROP TABLE IF EXISTS `veiculo`;
CREATE TABLE IF NOT EXISTS `veiculo` (
  `tipoVeiculo` varchar(31) NOT NULL,
  `placa` varchar(255) NOT NULL,
  `anoFabricacao` int(11) NOT NULL,
  `carroceria` varchar(255) NOT NULL,
  `chassi` varchar(255) NOT NULL,
  `cilindradas` varchar(255) NOT NULL,
  `numeroEixos` int(11) NOT NULL,
  `numeroMarchas` int(11) NOT NULL,
  `potenciaCV` int(11) NOT NULL,
  `quilometragem` int(11) NOT NULL,
  `valorVeiculo` decimal(19,2) NOT NULL,
  `vendido` tinyint(1) NOT NULL,
  `capcMaxCarga` decimal(19,2) DEFAULT NULL,
  `numAssentos` int(11) DEFAULT NULL,
  `numPortas` int(11) DEFAULT NULL,
  `corId` int(11) NOT NULL,
  `marcaId` int(11) NOT NULL,
  `modeloId` int(11) NOT NULL,
  PRIMARY KEY (`placa`),
  KEY `FK_2xtg90ajeth3ax9dv8e6fueti` (`corId`),
  KEY `FK_3yc7ioqsqix21d01g21eirwt5` (`marcaId`),
  KEY `FK_8mlq1r3kp0j66becuuyrmoegp` (`modeloId`),
  CONSTRAINT `FK_8mlq1r3kp0j66becuuyrmoegp` FOREIGN KEY (`modeloId`) REFERENCES `modelo` (`modeloId`),
  CONSTRAINT `FK_2xtg90ajeth3ax9dv8e6fueti` FOREIGN KEY (`corId`) REFERENCES `cor` (`corId`),
  CONSTRAINT `FK_3yc7ioqsqix21d01g21eirwt5` FOREIGN KEY (`marcaId`) REFERENCES `marca` (`marcaId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela shopcar.veiculo: ~6 rows (aproximadamente)
DELETE FROM `veiculo`;
/*!40000 ALTER TABLE `veiculo` DISABLE KEYS */;
INSERT INTO `veiculo` (`tipoVeiculo`, `placa`, `anoFabricacao`, `carroceria`, `chassi`, `cilindradas`, `numeroEixos`, `numeroMarchas`, `potenciaCV`, `quilometragem`, `valorVeiculo`, `vendido`, `capcMaxCarga`, `numAssentos`, `numPortas`, `corId`, `marcaId`, `modeloId`) VALUES
	('Carro', 'AAA-2123', 2000, 'Normal', 'AAAAAAAAAAA111111', '1.5', 4, 7, 200, 0, 53000.00, 1, NULL, 4, 4, 1, 3, 7),
	('Carro', 'ABC-1234', 2000, 'Normal', 'AAAAAAAAAAA111111', '1.5', 4, 7, 200, 0, 53000.00, 0, NULL, 4, 4, 1, 2, 4),
	('Caminhonete', 'ASD-1231', 2000, 'none', 'ADST231WE98111111', '1.5', 5, 8, 300, 0, 83000.00, 0, 5500.00, NULL, NULL, 1, 1, 3),
	('Carro', 'FDS-2201', 2000, 'Normal', 'AAAAAAAAAAA111111', '1.5', 4, 7, 200, 0, 53000.00, 0, NULL, 4, 4, 1, 1, 1),
	('Carro', 'GFT-3212', 2000, 'Normal', 'AAAAAAAAAAA111111', '1.5', 4, 7, 200, 0, 53000.00, 0, NULL, 4, 4, 1, 2, 5),
	('Caminhao', 'QEW-1234', 2000, 'HatchBack', 'ASAQR43D98F129648', '1.5', 3, 4, 300, 0, 40000.00, 0, 60000.00, NULL, NULL, 1, 2, 4),
	('Carro', 'QWE-1234', 2000, 'Normal', 'AAAAAAAAAAA111111', '1.5', 4, 7, 200, 0, 53000.00, 0, NULL, 4, 4, 1, 1, 3),
	('Moto', 'TRE-1242', 2000, 'Sedan', 'AAAAAAAAAAA111111', '1500', 7, 13, 300, 0, 27000.00, 0, NULL, NULL, NULL, 1, 1, 2);
/*!40000 ALTER TABLE `veiculo` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
