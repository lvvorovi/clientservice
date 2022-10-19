use clientdb;

DROP TABLE IF EXISTS client_workplace;
DROP TABLE IF EXISTS client_address;
DROP TABLE IF EXISTS document;
DROP TABLE IF EXISTS client;

CREATE TABLE client (
client_id bigint(20) NOT NULL AUTO_INCREMENT,
first_name varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
last_name varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
date_of_birth datetime NOT NULL,
passport_number varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
personal_number varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
phone_number varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
monthly_expenses decimal(25,2),
occupation varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
date_created datetime DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (client_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE client_workplace (
client_workplace_id bigint(20) NOT NULL AUTO_INCREMENT,
client_id bigint(20) NOT NULL,
company_name varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
position varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
monthly_income decimal(25,2) NOT NULL,
company_phone_number varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
PRIMARY KEY (client_workplace_id),
FOREIGN KEY (client_id)
REFERENCES client(client_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE client_address (
client_address_id bigint(20) NOT NULL AUTO_INCREMENT,
client_id bigint(20) NOT NULL,
country varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
region varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
city varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
street varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
house_number varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
apartment_number varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
postal_code varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
PRIMARY KEY (client_address_id),
FOREIGN KEY (client_id)
REFERENCES client(client_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


