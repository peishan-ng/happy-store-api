CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `new_arrival` tinyint(1) DEFAULT NULL,
  `sale` tinyint(1) DEFAULT NULL,
  `lvl1_code` varchar(45) DEFAULT NULL,
  `lvl1_alias` varchar(45) DEFAULT NULL,
  `lvl2_code` varchar(45) DEFAULT NULL,
  `lvl2_alias` varchar(45) DEFAULT NULL,
  `lvl3_code` varchar(45) DEFAULT NULL,
  `lvl3_alias` varchar(45) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  KEY `cat_new_arrival_idx` (`new_arrival`),
  KEY `cat_lvl1_code_idx` (`lvl1_code`),
  KEY `cat_lvl2_code_idx` (`lvl2_code`),
  KEY `cat_lvl3_code_idx` (`lvl3_code`),
  KEY `cat_sale_idx` (`sale`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8