use clientdb;

INSERT INTO client (first_name, last_name, date_of_birth, passport_number, personal_number, phone_number, monthly_expenses, occupation, date_created)
VALUES ('Arturs', 'Liepa', '2000-03-21', 'LV2849385', '210300-38475', '20558493', '300', 'student', '2019-09-13');
INSERT INTO client (first_name, last_name, date_of_birth, passport_number, personal_number, phone_number, monthly_expenses, occupation, date_created)
VALUES ('Janis', 'Kalnins', '1955-02-22', 'LV2869385', '220255-49586', '20345678', '200', 'retired', '2019-09-18');
INSERT INTO client (first_name, last_name, date_of_birth, passport_number, personal_number, phone_number, monthly_expenses, occupation, date_created)
VALUES ('Evita', 'Skujina', '2000-07-05', 'LV2849353', '050700-38576', '20356789', '350', 'student', '2020-03-18');
INSERT INTO client (first_name, last_name, date_of_birth, passport_number, personal_number, phone_number, monthly_expenses, occupation, date_created)
VALUES ('Kristaps', 'Saulitis', '1992-12-03', 'LV9719385', '021392-04857', '20745688', '300', 'employed', '2021-09-24');
INSERT INTO client (first_name, last_name, date_of_birth, passport_number, personal_number, phone_number, monthly_expenses, occupation, date_created)
VALUES ('Andrejs', 'Upitis', '1995-04-15', 'LV9475528', '150495-28475', '20653468', '450', 'employed', '2021-09-28');
INSERT INTO client (first_name, last_name, date_of_birth, passport_number, personal_number, phone_number, monthly_expenses, occupation, date_created)
VALUES ('Anna', 'Krastina', '1996-01-29', 'LV1374628', '290196-69485', '20935346', '500', 'unemployed', '2020-09-26');
INSERT INTO client (first_name, last_name, date_of_birth, passport_number, personal_number, phone_number, monthly_expenses, occupation, date_created)
VALUES ('Raimonds', 'Makonis', '1991-05-02', 'LV3956257', '020591-94758', '20845679', '700', 'employed', '2020-09-27');
INSERT INTO client (first_name, last_name, date_of_birth, passport_number, personal_number, phone_number, monthly_expenses, occupation, date_created)
VALUES ('Inese', 'Lapsa', '2000-01-02', 'LV3047265', '020100-29485', '20374564', '230', 'student', '2020-09-27');
INSERT INTO client (first_name, last_name, date_of_birth, passport_number, personal_number, phone_number, monthly_expenses, occupation, date_created)
VALUES ('Andrejs', 'Zakis', '1984-04-21', 'LV4736407', '210484-94857', '20084673', '620', 'self-employed', '2022-09-24');
INSERT INTO client (first_name, last_name, date_of_birth, passport_number, personal_number, phone_number, monthly_expenses, occupation, date_created)
VALUES ('Linda', 'Ozola', '1979-08-13', 'LV0375947', '130879-29485', '20457657', '180', 'unemployed', '2021-08-05');
INSERT INTO client (first_name, last_name, date_of_birth, passport_number, personal_number, phone_number, monthly_expenses, occupation, date_created)
VALUES ('Main', 'Bank Account', '2018-10-03', 'n/a', 'n/a', 'n/a', '100000000000.00', 'employed', '2018-10-03');

INSERT INTO client_address (client_id, country, city, street, house_number, apartment_number, postal_code)
VALUES ('1', 'Latvia', 'Riga', 'Dzirnavu iela', '12A', '4', 'LV-1050');
INSERT INTO client_address (client_id, country, city, street, house_number, postal_code)
VALUES ('2', 'Latvia','Jurmala', 'Tallinas iela', '15', 'LV-2049');
INSERT INTO client_address (client_id, country, city, street, house_number, apartment_number, postal_code)
VALUES ('3', 'Latvia', 'Valmiera', 'Dzelzavas iela', '12', '3', 'LV-1049');
INSERT INTO client_address (client_id, country, city, street, house_number, postal_code)
VALUES ('4', 'Latvia', 'Riga', 'Elizabetes iela', '123', 'LV-2340');
INSERT INTO client_address (client_id, country, city, street, house_number, apartment_number, postal_code)
VALUES ('5', 'Latvia', 'Rezekne', 'Skolas iela', '56', '8', 'LV-1048');
INSERT INTO client_address (client_id, country, region, city, street, house_number, apartment_number, postal_code)
VALUES ('6', 'Latvia', 'Siguldas novads', 'Sigulda', 'Slokas iela', '78B', '34', 'LV-1123');
INSERT INTO client_address (client_id, country, city, street, house_number, postal_code)
VALUES ('7', 'Latvia', 'Ventspils', 'Krasta iela', '23', 'LV-0486');
INSERT INTO client_address (client_id, country, city, street, house_number, apartment_number, postal_code)
VALUES ('8', 'Latvia', 'Ogre', 'Kokles iela', '2', '2', 'LV-6293');
INSERT INTO client_address (client_id, country, region, city, street, house_number, apartment_number, postal_code)
VALUES ('9', 'Latvia', 'Jelgavas novads', 'Jelgava', 'Dzirciema iela', '90', '6', 'LV-9468');
INSERT INTO client_address (client_id, country, city, street, house_number, postal_code)
VALUES ('10', 'Latvia', 'Jurmala', 'Burtu iela', '132', 'LV-2048');

INSERT INTO client_workplace (client_id, company_name, position, monthly_income, company_phone_number)
VALUES ('4', 'Rimi', 'Janitor', '550', '20394855');
INSERT INTO client_workplace (client_id, company_name, position, monthly_income, company_phone_number)
VALUES ('4', 'Maxima', 'Janitor', '200', '20374958');
INSERT INTO client_workplace (client_id, company_name, position, monthly_income, company_phone_number)
VALUES ('5', 'Lidl', 'Manager', '850', '20948579');
INSERT INTO client_workplace (client_id, company_name, position, monthly_income, company_phone_number)
VALUES ('7', 'Balticom', 'System Administrator', '1500', '20867497');
INSERT INTO client_workplace (client_id, position, monthly_income)
VALUES ('9', 'Blacksmith', '1200');