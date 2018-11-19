-- For the users database
CREATE TABLE users (username TEXT NOT NULL PRIMARY KEY, password TEXT NOT NULL, enabled BOOLEAN NOT NULL);

CREATE TABLE authorities (username TEXT NOT NULL PRIMARY KEY REFERENCES users(username), authority TEXT NOT NULL);

-- For the employee kpi table
CREATE TABLE employee_metrics(employee_code SERIAL NOT NULL PRIMARY KEY, kpi1 DECIMAL NOT NULL, kpi2 DECIMAL NOT NULL, month INTEGER NOT NULL, year INTEGER NOT NULL, kpi3 DECIMAL NOT NULL, kpi4 DECIMAL NOT NULL, kpi_tot DECIMAL NOT NULL, name text, is_draft BOOLEAN);

-- For the rule table
CREATE TABLE rule_files (name TEXT NOT NULL PRIMARY KEY, location TEXT NOT NULL, month INTEGER NOT NULL, year INTEGER NOT NULL, rules TEXT NOT NULL, csv TEXT NOT NULL);

INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$AXQmICgFYVflZicfkG2Qj.QKaq7HAS3Uvn7Ua9f5rv3tv5Kc0om2K', true);

INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER') ON CONFLICT (username) DO NOTHING;

INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN') ON CONFLICT (username) DO NOTHING;
