CREATE SEQUENCE primary_sequence START WITH 1 INCREMENT BY 50;

CREATE TABLE department
(
    id   INT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_department PRIMARY KEY (id)
);

CREATE TABLE employee
(
    start_date         TIMESTAMP NOT NULL,
    end_date           TIMESTAMP,
    employment_type_id INT       NOT NULL,
    person_id          INT       NOT NULL,
    department_id      INT       NOT NULL,
    CONSTRAINT pk_employee PRIMARY KEY (person_id, department_id)
);

CREATE TABLE employment_type
(
    id   INT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_employment_type PRIMARY KEY (id)
);

CREATE TABLE person
(
    id   INT NOT NULL,
    name VARCHAR(255),
    age  INT,
    CONSTRAINT pk_person PRIMARY KEY (id)
);

ALTER TABLE employee
    ADD CONSTRAINT FK_EMPLOYEE_ON_DEPARTMENT FOREIGN KEY (department_id) REFERENCES department (id);

ALTER TABLE employee
    ADD CONSTRAINT FK_EMPLOYEE_ON_EMPLOYMENT_TYPE FOREIGN KEY (employment_type_id) REFERENCES employment_type (id);

ALTER TABLE employee
    ADD CONSTRAINT FK_EMPLOYEE_ON_PERSON FOREIGN KEY (person_id) REFERENCES person (id);