create table persons(
    person_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE CHECK(email LIKE '%_@_%._%')
);


-- create table orders(
--     order_id int AUTO_INCREMENT PRIMARY KEY,
--     order_amount decimal(10,2) NOT NULL,
--     person_id int,
--     -- FOREIGN KEY (person_id) REFERENCES persons(person_id)
--     constraint fk_person
--         Foreign Key (person_id) REFERENCES persons(person_id),
--     constraint chk_amount
--         check (order_amount > 0)

-- )

ALTER table persons RENAME TO person;


INSERT INTO person(name,email) values('Stephenie', 'bacotell@email.com');

SELECT * from person;

UPDATE person set email='tacobell1@email.com' where person_id=1;

DELETE from person where person_id =1;

drop table person;



