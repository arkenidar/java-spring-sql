CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `age` int NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

INSERT INTO `java_spring_sql`.`customers` (`name`, `age`, `created_date`) VALUES ('user_name_1', '24', now());
INSERT INTO `java_spring_sql`.`customers` (`name`, `age`, `created_date`) VALUES ('user_name_2', '45', now());

SELECT * FROM java_spring_sql.customers;