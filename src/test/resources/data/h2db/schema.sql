
DROP IF EXISTS `prendas`;

CREATE TABLE `prendas` (
  `prd_id` bigint NOT NULL AUTO_INCREMENT,
  `prd_description` varchar(255) DEFAULT NULL,
  `prd_precio_base` decimal(19,2) DEFAULT NULL,
  `prd_tipo_prenda` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`prd_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
