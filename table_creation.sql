## For the users database
CREATE TABLE users (username TEXT NOT NULL PRIMARY KEY, password TEXT NOT NULL, enabled BOOLEAN NOT NULL);

CREATE TABLE authorities (username TEXT NOT NULL PRIMARY KEY REFERENCES users(username), authority TEXT NOT NULL);

## For the employee kpi table
CREATE TABLE employee_metrics(employee_code SERIAL NOT NULL PRIMARY KEY, kpi1 DECIMAL NOT NULL, kpi2 DECIMAL NOT NULL, month INTEGER NOT NULL, year INTEGER NOT NULL, kpi3 DECIMAL NOT NULL, kpi4 DECIMAL NOT NULL, kpi_tot DECIMAL NOT NULL, text NAME);