CREATE TABLE `topic` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,  
  UNIQUE(name)  
);


CREATE TABLE `inquiry` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `description` VARCHAR(1000) NOT NULL,
  `create_date` DATETIME NOT NULL,
  `customer_name` VARCHAR(40) NOT NULL,
  `fk_topic` INTEGER NOT NULL,
  
  CONSTRAINT `fk_topic_to_inquiry` FOREIGN KEY(`fk_topic`) REFERENCES `topic` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);

CREATE TABLE `attribute_of_inquiry` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `value` VARCHAR(20) NOT NULL, 
  `fk_inquiry` INTEGER DEFAULT NULL,
  CONSTRAINT UQ_name_value UNIQUE(`name`, `fk_inquiry`),
  
  CONSTRAINT `fk_inquiry_to_attribute_of_inquiry` FOREIGN KEY(`fk_inquiry`) REFERENCES `inquiry` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);

-- ---
-- Test Data
-- ---
INSERT INTO `topic` (`name`) VALUES
('Connection');
INSERT INTO `topic` (`name`) VALUES
('Payment');
INSERT INTO `topic` (`name`) VALUES
('Additional services');
INSERT INTO `topic` (`name`) VALUES
('Internet');
INSERT INTO `topic` (`name`) VALUES
('SIM');
INSERT INTO `topic` (`name`) VALUES
('Roaming');
INSERT INTO `topic` (`name`) VALUES
('Other');


INSERT INTO `inquiry` (`description`,`create_date`,`customer_name`, `fk_topic`) VALUES
('My phone have no connection', '2020-02-27 09:10:00', 'Holmes', 1);
INSERT INTO `inquiry` (`description`,`create_date`,`customer_name`, `fk_topic`) VALUES
('Roaming not working', '2020-03-12 09:10:00', 'Holmes', 6);
INSERT INTO `inquiry` (`description`,`create_date`,`customer_name`, `fk_topic`) VALUES
('I want to know my puk code for my sim', '2020-03-01 12:12:00', 'Andrey', 5);
INSERT INTO `inquiry` (`description`,`create_date`,`customer_name`, `fk_topic`) VALUES
('Can I delay my payment by a couple of days?', '2020-03-01 12:12:00', 'Dima', 2);
INSERT INTO `inquiry` (`description`,`create_date`,`customer_name`, `fk_topic`) VALUES
('How do I cancel my contract', '2020-03-05 17:52:00', 'Dima', 7);
INSERT INTO `inquiry` (`description`,`create_date`,`customer_name`, `fk_topic`) VALUES
('International roaming in Chile', '2020-03-10 12:34:00', 'Olya', 6);
INSERT INTO `inquiry` (`description`,`create_date`,`customer_name`, `fk_topic`) VALUES
('How do I add voice mail?', '2020-03-11 20:25:56', 'Rita', 3);





INSERT INTO `attribute_of_inquiry` (`name`,`value`, `fk_inquiry`) VALUES
('Address', '221B Baker Street', 1);
INSERT INTO `attribute_of_inquiry` (`name`,`value`, `fk_inquiry`) VALUES
('Phone model', 'Samsung Galaxy A50', 1);
INSERT INTO `attribute_of_inquiry` (`name`,`value`, `fk_inquiry`) VALUES
('Contract number', '123/45-34C', 1);
INSERT INTO `attribute_of_inquiry` (`name`,`value`, `fk_inquiry`) VALUES
('Address', '221B Baker Street', 2);
INSERT INTO `attribute_of_inquiry` (`name`,`value`, `fk_inquiry`) VALUES
('Telephone number', '+375291234567', 2);
INSERT INTO `attribute_of_inquiry` (`name`,`value`, `fk_inquiry`) VALUES
('Contract number', '123/78-09B', 5);

