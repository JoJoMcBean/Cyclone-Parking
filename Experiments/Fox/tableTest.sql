USE test;

CREATE TABLE IF NOT EXISTS owners (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  address VARCHAR(255),
  city VARCHAR(80),
  telephone VARCHAR(20),
  INDEX(last_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS city (
	city VARCHAR(20) PRIMARY KEY,
	state VARCHAR(20)
    ) engine = InnoDB;

INSERT IGNORE INTO owners VALUES (NULL, '110 W. Liberty St.', 'George', 'Franklin', '6085551023');
INSERT IGNORE INTO owners VALUES (NULL, '638 Cardinal Ave.', 'Betty', 'Davis', '6085551749');
INSERT IGNORE INTO owners VALUES (NULL, '2693 Commerce St.', 'Eduardo', 'Rodriquez', '6085558763');
