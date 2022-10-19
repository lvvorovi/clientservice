use clientdb;

drop table IF EXISTS client_workplace;
drop table IF EXISTS client_address;
drop table IF EXISTS document;
drop table IF EXISTS client;

create TABLE client (
client_id bigint(20) NOT NULL AUTO_INCREMENT,
first_name varchar(45) NOT NULL,
last_name varchar(45) NOT NULL,
date_of_birth datetime NOT NULL,
passport_number varchar(45) NOT NULL,
personal_number varchar(45) NOT NULL,
phone_number varchar(45) NOT NULL,
monthly_expenses decimal(25,2),
occupation varchar(45) NOT NULL,
date_created datetime DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (client_id)
);

create TABLE client_workplace (
client_workplace_id bigint(20) NOT NULL AUTO_INCREMENT,
client_id bigint(20) NOT NULL,
company_name varchar(45) ,
position varchar(45) NOT NULL,
monthly_income decimal(25,2) NOT NULL,
company_phone_number varchar(45) ,
PRIMARY KEY (client_workplace_id),
FOREIGN KEY (client_id) REFERENCES client(client_id)
);

create TABLE client_address (
client_address_id bigint(20) NOT NULL AUTO_INCREMENT,
client_id bigint(20) NOT NULL,
country varchar(45) NOT NULL,
region varchar(45) ,
city varchar(45) NOT NULL,
street varchar(45) NOT NULL,
house_number varchar(45) NOT NULL,
apartment_number varchar(45) ,
postal_code varchar(45) NOT NULL,
PRIMARY KEY (client_address_id),
FOREIGN KEY (client_id)
REFERENCES client(client_id)
);


