-- PERSON table

DROP TABLE IF EXISTS person;
CREATE TABLE person (
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(20),
  AGE SMALLINT,
  SEX VARCHAR(2),
  EMAIL VARCHAR(50),
  ADDRESS VARCHAR(200)
) ENGINE=InnoDB CHARACTER SET=utf8;