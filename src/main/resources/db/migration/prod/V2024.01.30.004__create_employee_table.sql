CREATE TABLE employee
(
    start_date         TIMESTAMP NOT NULL,
    end_date           TIMESTAMP,
    employment_type_id INT       NOT NULL,
    person_id          INT       NOT NULL,
    department_id      INT       NOT NULL,
    CONSTRAINT pk_employee PRIMARY KEY (person_id, department_id)
);