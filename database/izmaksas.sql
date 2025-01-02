CREATE TABLE `izmaksas` (
	`izmaksas_id` INT NOT NULL,
	`user_id` INT NOT NULL,
	`sum` DOUBLE NULL DEFAULT NULL,
	`date` TEXT NULL,
	`category` TEXT NULL,
	PRIMARY KEY (`izmaksas_id`),
	CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
);