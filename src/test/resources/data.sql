
INSERT INTO user
(id, name, password, username)
VALUES
(1, 'Andre', '$2a$10$6EppIKnQboNTfBW3IYwwE.hN/AVp5BXAKT0mOtYknwS82p8uxc.s6', 'admin'),
(2, 'Homer Simpson', '$2a$10$6EppIKnQboNTfBW3IYwwE.hN/AVp5BXAKT0mOtYknwS82p8uxc.s6', 'homer'),
(3, 'Peter Griffin', '$2a$10$6EppIKnQboNTfBW3IYwwE.hN/AVp5BXAKT0mOtYknwS82p8uxc.s6', 'peter'),
(4, 'Banco Santander', '$2a$10$6EppIKnQboNTfBW3IYwwE.hN/AVp5BXAKT0mOtYknwS82p8uxc.s6', 'santander'),
(5, 'Revolut', '$2a$10$6EppIKnQboNTfBW3IYwwE.hN/AVp5BXAKT0mOtYknwS82p8uxc.s6', 'revolut'),
(6, 'Rick Sanchez', '$2a$10$6EppIKnQboNTfBW3IYwwE.hN/AVp5BXAKT0mOtYknwS82p8uxc.s6', 'picklerick'),
(7, 'Stan Smith', '$2a$10$6EppIKnQboNTfBW3IYwwE.hN/AVp5BXAKT0mOtYknwS82p8uxc.s6', 'stan');





INSERT INTO role
(id, role_name, user_id)
VALUES
(1, 'ADMIN', 1),
(2, 'ACCOUNTHOLDER', 2),
(3, 'ACCOUNTHOLDER', 3),
(4, 'THIRDPARTY', 4),
(5, 'THIRDPARTY', 5),
(6, 'ACCOUNTHOLDER', 6),
(7, 'ACCOUNTHOLDER', 7);



INSERT INTO address
(`address_id`, `city`, `country`, `street_and_number`, `zip_code`)
VALUES
(1, 'Springfield', 'USA', '742 Evergreen Terrace', 1234),
(2, 'Quahog', 'USA', '31 Spooner Street', 5678),
(3, 'Seattle', 'USA', 'The Smith Residence', 9123),
(4, 'Langley Falls', 'USA', '1024 Cherry Street', 4567);



INSERT INTO account_holder
(`date_of_birth`, `id`, `primary_address_id`)
VALUES
('1968-05-01', 2, 1),
('1962-12-01', 3, 2),
('1968-05-01', 6, 3),
('1968-05-01', 7, 4);



INSERT INTO admin
(`id`)
VALUES
(1);



INSERT INTO third_party
(`hashed_key_third_party`, `id`)
VALUES
('$2a$10$6EppIKnQboNTfBW3IYwwE.hN/AVp5BXAKT0mOtYknwS82p8uxc.s6', 4),
('$2a$10$6EppIKnQboNTfBW3IYwwE.hN/AVp5BXAKT0mOtYknwS82p8uxc.s6', 5);


--With primaryOwner only
INSERT INTO account
(`account_number`,`balance`,`currency`,`creation_date`,`minimum_balance`,`penalty_fee`,`secret_key`,`account_status`,
`primary_owner`)
VALUES
(1, 800, 'EUR', '2010-01-01', 250, 40, '$2a$10$6EppIKnQboNTfBW3IYwwE.hN/AVp5BXAKT0mOtYknwS82p8uxc.s6',
'ACTIVE', 2),
(2, 1580, 'EUR', '2012-05-20', 250, 40, '$2a$10$6EppIKnQboNTfBW3IYwwE.hN/AVp5BXAKT0mOtYknwS82p8uxc.s6',
'ACTIVE', 2),
(3, 2470, 'EUR', '2013-05-20', 250, 40, '$2a$10$6EppIKnQboNTfBW3IYwwE.hN/AVp5BXAKT0mOtYknwS82p8uxc.s6',
'ACTIVE', 3),
(4, 6000, 'EUR', '2016-05-20', 250, 40, '$2a$10$6EppIKnQboNTfBW3IYwwE.hN/AVp5BXAKT0mOtYknwS82p8uxc.s6',
'ACTIVE', 6),
(5, 500, 'EUR', '2017-05-20', 250, 40, '$2a$10$6EppIKnQboNTfBW3IYwwE.hN/AVp5BXAKT0mOtYknwS82p8uxc.s6',
'ACTIVE', 7);

--With secondaryOwner also
INSERT INTO account
(`account_number`,`balance`,`currency`,`creation_date`,`minimum_balance`,`penalty_fee`,`secret_key`,`account_status`,
`primary_owner`,`secondary_owner`)
VALUES
(6, 1320, 'EUR', '2015-01-01', 250, 40, '$2a$10$6EppIKnQboNTfBW3IYwwE.hN/AVp5BXAKT0mOtYknwS82p8uxc.s6',
'ACTIVE', 3, 2);



















