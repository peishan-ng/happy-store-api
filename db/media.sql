CREATE TABLE `media` (
  `prdt_media_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `ordering` int(11) DEFAULT '1',
  `media_type` enum('IMAGE','VIDEO') DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`prdt_media_id`),
  KEY `media_filename_idx` (`filename`),
  KEY `media_type_idx` (`media_type`),
  KEY `fk_media_prdt_idx` (`product_id`),
  CONSTRAINT `fk_media_prdt` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8