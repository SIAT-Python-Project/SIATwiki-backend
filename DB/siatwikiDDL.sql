-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema siatwiki
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema siatwiki
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `siatwiki` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `siatwiki` ;

-- -----------------------------------------------------
-- Table `siatwiki`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siatwiki`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `create_date` DATETIME(6) NULL DEFAULT NULL,
  `email` VARCHAR(200) NULL DEFAULT NULL,
  `name` VARCHAR(200) NULL DEFAULT NULL,
  `password` VARCHAR(1000) NULL DEFAULT NULL,
  `role` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `siatwiki`.`profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siatwiki`.`profile` (
  `attachment_file_size` BIGINT NULL DEFAULT NULL,
  `profile_id` BIGINT NOT NULL AUTO_INCREMENT,
  `attachment_file_name` VARCHAR(2000) NULL DEFAULT NULL,
  `attachment_original_file_name` VARCHAR(2000) NULL DEFAULT NULL,
  `file_path` VARCHAR(2000) NULL DEFAULT NULL,
  PRIMARY KEY (`profile_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `siatwiki`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siatwiki`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL DEFAULT NULL,
  `create_date` DATETIME(6) NULL DEFAULT NULL,
  `profile_id` BIGINT NULL DEFAULT NULL,
  `update_date` DATETIME(6) NULL DEFAULT NULL,
  `email` VARCHAR(200) NULL DEFAULT NULL,
  `github` VARCHAR(1000) NULL DEFAULT NULL,
  `mbti` VARCHAR(50) NULL DEFAULT NULL,
  `name` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK9jsnji0yg2ii7ee8wrrag4mvs` (`profile_id` ASC) VISIBLE,
  INDEX `FK2is3ph79mqcwtkd724syhtjbi` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK2is3ph79mqcwtkd724syhtjbi`
    FOREIGN KEY (`user_id`)
    REFERENCES `siatwiki`.`user` (`id`),
  CONSTRAINT `FKc3vjwc3fku5f4tshfrpr312no`
    FOREIGN KEY (`profile_id`)
    REFERENCES `siatwiki`.`profile` (`profile_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `siatwiki`.`info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siatwiki`.`info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL DEFAULT NULL,
  `create_date` DATETIME(6) NULL DEFAULT NULL,
  `update_date` DATETIME(6) NULL DEFAULT NULL,
  `content` LONGTEXT NULL DEFAULT NULL,
  `type` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK49ayijmxad7gy7hsedo63iyk` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK49ayijmxad7gy7hsedo63iyk`
    FOREIGN KEY (`user_id`)
    REFERENCES `siatwiki`.`person` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;