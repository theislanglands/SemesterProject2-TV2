
CREATE TABLE production_type(
    id serial PRIMARY KEY,
    type VARCHAR(100)
);

CREATE TABLE production_name(
    id serial PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE language(
    id serial PRIMARY KEY,
    language VARCHAR(100)
);

CREATE TABLE production_company (
    id serial PRIMARY KEY,
    name VARCHAR(100),
    address VARCHAR(100),
    phone INTEGER,
    email VARCHAR(100),
    country VARCHAR(100)
);

CREATE TABLE genre(
    id serial PRIMARY KEY,
    genre VARCHAR(100)
);

CREATE TABLE credit_name (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    address VARCHAR(100),
    phone INTEGER,
    email VARCHAR(100)
);

CREATE TABLE credit_type(
    id SERIAL PRIMARY KEY,
    type VARCHAR(100)
);

CREATE TABLE production (
    id SERIAL PRIMARY KEY,
    season INTEGER,
    episode INTEGER,
    release_date TIMESTAMP NOT NULL,
    length INTEGER,
    subtitle BOOLEAN,
    sign_language BOOLEAN,
    active BOOLEAN,
    validated BOOLEAN,
    production_company_production_id VARCHAR(100),
    
    production_company_id INTEGER NOT NULL REFERENCES production_company(id),
    production_type_id INTEGER NOT NULL REFERENCES production_type(id),
    language_id INTEGER NOT NULL REFERENCES language(id),
    production_name INTEGER NOT NULL REFERENCES production_name(id)
);

CREATE TABLE genres_production_associaton(
    production_id INTEGER NOT NULL REFERENCES production(id),
    genre_id INTEGER NOT NULL REFERENCES genre(id)
);

CREATE TABLE credit (
    id SERIAL PRIMARY KEY,
    role VARCHAR(100),
    validated BOOLEAN,
    production_id INTEGER NOT NULL REFERENCES production(id)
);

CREATE TABLE credit_name_credit_type_associaton (
    credit_name_id INTEGER NOT NULL REFERENCES credit_name(id),
    credit_type_id INTEGER NOT NULL REFERENCES credit_type(id),
    credit_id INTEGER NOT NULL REFERENCES credit(id)
);