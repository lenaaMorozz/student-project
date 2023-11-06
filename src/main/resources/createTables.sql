CREATE TABLE "group"
(
    id        SERIAL PRIMARY KEY,
    group_num INTEGER
);

CREATE TABLE subject
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR
);

CREATE TABLE student
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR,
    last_name  VARCHAR,
    age        INTEGER,
    group_id   INTEGER,
    FOREIGN KEY (group_id) REFERENCES "group" (id)
);

CREATE TABLE grade
(
    id         SERIAL PRIMARY KEY,
    student_id INTEGER,
    subject_id INTEGER,
    grade      INTEGER,
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (subject_id) REFERENCES subject (id)
);


