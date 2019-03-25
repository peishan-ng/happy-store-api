CREATE TABLE `brand` (
  `brand_code` varchar(45) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `logo_image` varchar(255) DEFAULT NULL,
  `banner_image` varchar(45) DEFAULT NULL,
  `language_code` enum('en_US','zh_CN') DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`brand_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8