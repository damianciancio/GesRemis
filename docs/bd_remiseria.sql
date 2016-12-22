DROP SCHEMA IF EXISTS ges_remis;
CREATE SCHEMA `ges_remis` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
use ges_remis;
CREATE TABLE `marcas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `modelos` (
  `id_marca` int(11) NOT NULL,
  `descripcion` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_marca`,`descripcion`),
  CONSTRAINT `fk_id_marca` FOREIGN KEY (`id_marca`) REFERENCES `marcas` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `remises` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patente` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `fecha_incorporacion` date NOT NULL,
  `fecha_baja` date DEFAULT NULL,
  `anio_modelo` int(11) NOT NULL,
  `id_marca` int(11) NOT NULL,
  `desc_marca` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_marca_idx` (`id_marca`),
  KEY `fk_modelo_idx` (`desc_marca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `remises_choferes` (
  `legajo` int(11) NOT NULL,
  `id_remis` int(11) NOT NULL,
  `fecha_desde` date NOT NULL,
  PRIMARY KEY (`legajo`,`fecha_desde`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `viajes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `legajo_chofer` int(11) NOT NULL,
  `fec_desde_chof_rem` date NOT NULL,
  `fecha_hora_inicio` datetime NOT NULL,
  `fecha_hora_fin` datetime DEFAULT NULL,
  `direccion_origen` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `direccion_destino` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `saldo` decimal(9,2) DEFAULT NULL,
  `nom_ap_cliente` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fecha_hora_solicitada` datetime DEFAULT NULL,
  `fecha_hora_solicitud` datetime DEFAULT NULL,
  `legajo_recepcionista` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
