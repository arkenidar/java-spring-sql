CREATE TABLE `users_login` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `java_spring_sql`.`users_login` (`user_name`) VALUES ('user_name_1');
INSERT INTO `java_spring_sql`.`users_login` (`user_name`) VALUES ('user_name_2');
INSERT INTO `java_spring_sql`.`users_login` (`user_name`) VALUES ('user_name_3');

SELECT * FROM java_spring_sql.users_login;