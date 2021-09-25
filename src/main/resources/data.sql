INSERT INTO address (city, country, street_and_number, zip_code) VALUES
('Olhão', 'Portugal', 'R Dr Malafaia Bl3 1dto', '8700');

INSERT INTO user (name, password, username) VALUES
('Andre', 'password', 'admin');

INSERT INTO user (name, password, username, date_of_birth, mailing_address_id) VALUES
('Guida', 'password', 'accountholder', '1978-05-19', '1');

INSERT INTO role (id, role_name, user_id) VALUES
('1', 'ADMIN', '1'),
('2', 'ACCOUNTHOLDER', '2');


INSERT INTO `timebankdatabase`.`address`
(`address_id`,
`city`,
`country`,
`street_and_number`,
`zip_code`)
VALUES
(1, 'Olhao', 'Portugal', 'R Dr Antonio Malafaia Bl3', 8700),
(2, 'Faro', 'Portugal', 'R Antonio Bivar', 8000),
(3, 'Springfield', 'USA', 'Simpsons Drv', 1234),
(1, 'Huelva', 'Spain', 'Calle Cerveza', 5678);






