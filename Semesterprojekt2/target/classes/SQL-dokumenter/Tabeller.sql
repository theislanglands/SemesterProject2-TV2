
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
    email VA0)RCHAR(10
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
    production_reference VARCHAR(100),
    
    production_company_id INTEGER NOT NULL REFERENCES production_company(id),
    production_type_id INTEGER NOT NULL REFERENCES production_type(id),
    language_id INTEGER NOT NULL REFERENCES language(id),
    production_name_id INTEGER NOT NULL REFERENCES production_name(id)
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

INSERT INTO credit_type (type) VALUES ('Billedkunstner');
INSERT INTO credit_type (type) VALUES ('Billed og Lydredigering');
INSERT INTO credit_type (type) VALUES ('Casting');
INSERT INTO credit_type (type) VALUES ('Colourgrading');
INSERT INTO credit_type (type) VALUES ('Dirigenter');
INSERT INTO credit_type (type) VALUES ('Drone');
INSERT INTO credit_type (type) VALUES ('Dukkefører');
INSERT INTO credit_type (type) VALUES ('Dukkeskaber');
INSERT INTO credit_type (type) VALUES ('Fortæller');
INSERT INTO credit_type (type) VALUES ('Fotograf');
INSERT INTO credit_type (type) VALUES ('Forlæg');
INSERT INTO credit_type (type) VALUES ('Grafisk Designer');
INSERT INTO credit_type (type) VALUES ('Indtaler');
INSERT INTO credit_type (type) VALUES ('Kapelmester');
INSERT INTO credit_type (type) VALUES ('Klipper');
INSERT INTO credit_type (type) VALUES ('Programkoncept');
INSERT INTO credit_type (type) VALUES ('Konsulent');
INSERT INTO credit_type (type) VALUES ('Kor');
INSERT INTO credit_type (type) VALUES ('Koreografi');
INSERT INTO credit_type (type) VALUES ('Lyd');
INSERT INTO credit_type (type) VALUES ('Tonemester');
INSERT INTO credit_type (type) VALUES ('Ledredigering');
INSERT INTO credit_type (type) VALUES ('Lys');
INSERT INTO credit_type (type) VALUES ('Medvirkende');
INSERT INTO credit_type (type) VALUES ('Musiker');
INSERT INTO credit_type (type) VALUES ('Musikalsk Arrangement');
INSERT INTO credit_type (type) VALUES ('Orkester');
INSERT INTO credit_type (type) VALUES ('Band');
INSERT INTO credit_type (type) VALUES ('Oversætter');
INSERT INTO credit_type (type) VALUES ('Producent');
INSERT INTO credit_type (type) VALUES ('Producer');
INSERT INTO credit_type (type) VALUES ('Produktionskoordinator');
INSERT INTO credit_type (type) VALUES ('Produktionsleder');
INSERT INTO credit_type (type) VALUES ('Programansvarlig');
INSERT INTO credit_type (type) VALUES ('Redaktion');
INSERT INTO credit_type (type) VALUES ('Tilrettelæggelse')
INSERT INTO credit_type (type) VALUES ('Redaktør');
INSERT INTO credit_type (type) VALUES ('Rekvisitør');
INSERT INTO credit_type (type) VALUES ('Scenograf');
INSERT INTO credit_type (type) VALUES ('Scripter');
INSERT INTO credit_type (type) VALUES ('Producerassistent');
INSERT INTO credit_type (type) VALUES ('SpecialEffects');
INSERT INTO credit_type (type) VALUES ('Sponsor');
INSERT INTO credit_type (type) VALUES ('Tegnefilm');
INSERT INTO credit_type (type) VALUES ('Animation');
INSERT INTO credit_type (type) VALUES ('Tekster');
INSERT INTO credit_type (type) VALUES ('Tekst og musik');
INSERT INTO credit_type (type) VALUES ('Uhonoreret ekstraordinær indsats');

INSERT INTO genre (genre) VALUES ('Drama');
INSERT INTO genre (genre) VALUES ('Komedie');
INSERT INTO genre (genre) VALUES ('Animation');
INSERT INTO genre (genre) VALUES ('Thriller');
INSERT INTO genre (genre) VALUES ('Action');
INSERT INTO genre (genre) VALUES ('Fantasy');
INSERT INTO genre (genre) VALUES ('Gyser');
INSERT INTO genre (genre) VALUES ('Romantisk');
INSERT INTO genre (genre) VALUES ('Western');
INSERT INTO genre (genre) VALUES ('Sci-fi');
INSERT INTO genre (genre) VALUES ('Krig');
INSERT INTO genre (genre) VALUES ('Biografi');
INSERT INTO genre (genre) VALUES ('Dokumentar');
INSERT INTO genre (genre) VALUES ('Reality');
INSERT INTO genre (genre) VALUES ('Andet');

INSERT INTO language (language) VALUES ('Dansk');
INSERT INTO language (language) VALUES ('Svensk');
INSERT INTO language (language) VALUES ('Norsk');
INSERT INTO language (language) VALUES ('Engelsk');
INSERT INTO language (language) VALUES ('Tysk');
INSERT INTO language (language) VALUES ('Spansk');
INSERT INTO language (language) VALUES ('Italiensk');
INSERT INTO language (language) VALUES ('Andet');

INSERT INTO production_type (type) VALUES ('Film');
INSERT INTO production_type (type) VALUES ('Serie');


INSERT INTO production_company (name, address, phone, email, country) VALUES ('SF Film Production ApS', 'filmbyen 1, 5000 Oense', 62856381, 'sffilm@badhotellet.dk', 'Denmark');

INSERT INTO production_name (name) VALUES ('Badehotellet');
INSERT INTO production (season, episode, release_date, length, subtitle, sign_language, active, validated, production_reference, production_company_id, production_type_id, language_id, production_name_id) VALUES (1, 2, '1978-06-23 00:00:00.000000', 43, true, false, true, true, 'SF102', 1, 2, 1, 1);

-- tilføjer kolonne til production tabel
ALTER TABLE production ADD COLUMN production_bio VARCHAR(2000);
UPDATE production SET production_bio='badehotellet er en helt fantastisk serie om et badehotel' WHERE production.id = 1;


