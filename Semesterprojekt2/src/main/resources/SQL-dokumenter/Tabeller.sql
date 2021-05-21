DROP TABLE IF EXISTS credit_name_credit_type_association;
DROP TABLE IF EXISTS production_images;
DROP TABLE IF EXISTS credit;
DROP TABLE IF EXISTS genres_production_association;
DROP TABLE IF EXISTS production;
DROP TABLE IF EXISTS credit_type;
DROP TABLE IF EXISTS credit_name;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS production_company;
DROP TABLE IF EXISTS language;
DROP TABLE IF EXISTS production_name;
DROP TABLE IF EXISTS production_type;


CREATE TABLE production_type(
    id serial PRIMARY KEY,
    type VARCHAR(100) UNIQUE
);

CREATE TABLE production_name(
    id serial PRIMARY KEY,
    name VARCHAR(100) UNIQUE
);

CREATE TABLE language(
     id serial PRIMARY KEY,
     language VARCHAR(100) UNIQUE
);

CREATE TABLE production_company (
    id serial PRIMARY KEY,
    name VARCHAR(100) UNIQUE,
    address VARCHAR(100),
    phone INTEGER,
    email VARCHAR(100),
    country VARCHAR(100)
);

CREATE TABLE genre(
    id serial PRIMARY KEY,
    genre VARCHAR(100) UNIQUE
);

CREATE TABLE credit_name (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    address VARCHAR(100),
    phone INTEGER,
    email VARCHAR(100),
    date_of_birth DATE,
    country VARCHAR(100),
    bio VARCHAR(2000),
    imageurl VARCHAR(500),
    UNIQUE (first_name, last_name)
);

CREATE TABLE credit_type(
    id SERIAL PRIMARY KEY,
    type VARCHAR(100) UNIQUE
);

CREATE TABLE production (
    id SERIAL PRIMARY KEY,
    season INTEGER,
    episode INTEGER,
    release_date TIMESTAMP,
    length INTEGER,
    subtitle BOOLEAN,
    sign_language BOOLEAN,
    active BOOLEAN,
    validated BOOLEAN,
    production_reference VARCHAR(100),
    production_bio VARCHAR(2000),
    imageurl VARCHAR(500),

    production_company_id INTEGER NOT NULL REFERENCES production_company(id),
    production_type_id INTEGER NOT NULL REFERENCES production_type(id),
    language_id INTEGER NOT NULL REFERENCES language(id),
    production_name_id INTEGER NOT NULL REFERENCES production_name(id),

    UNIQUE (season, episode, production_name_id)
);

CREATE TABLE genres_production_association(
    production_id INTEGER NOT NULL REFERENCES production(id) ON DELETE CASCADE,
    genre_id INTEGER NOT NULL REFERENCES genre(id),
    UNIQUE (production_id, genre_id)
);

CREATE TABLE credit (
    id SERIAL PRIMARY KEY,
    role VARCHAR(100),
    validated BOOLEAN,
    production_id INTEGER NOT NULL REFERENCES production(id) ON DELETE CASCADE,
    UNIQUE (role, production_id)
);

CREATE TABLE production_images (
   image_text VARCHAR(100),
   image BYTEA,
   production_id INTEGER NOT NULL REFERENCES production(id) ON DELETE CASCADE
);

CREATE TABLE credit_name_credit_type_association (
    credit_name_id INTEGER NOT NULL REFERENCES credit_name(id),
    credit_type_id INTEGER NOT NULL REFERENCES credit_type(id),
    credit_id INTEGER NOT NULL REFERENCES credit(id) ON DELETE CASCADE,
    UNIQUE (credit_name_id, credit_type_id, credit_id)
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
INSERT INTO credit_type (type) VALUES ('Tilrettelæggelse');
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
INSERT INTO production_company (name, address, phone, email, country) VALUES ('Koncern TV- og Filmproduktion', 'adresse 2, Kolding', 65926104, 'koncernTvFilm@yahoo.com', 'Denmark');
INSERT INTO production_company (name, address, phone, email, country) VALUES ('Zentropa', 'Filmbyen 22, 2650 Hvidovre', 36868788, 'Zentropa@film.dk', 'Danmark');

INSERT INTO production_name (name) VALUES ('Badehotellet');
INSERT INTO production_name (name) VALUES ('På røven i Nakskov');
INSERT INTO production_name (name) VALUES ('Druk');

INSERT INTO production (season, episode, release_date, length, subtitle, sign_language, active, validated, production_reference, production_bio, imageurl, production_company_id, production_type_id, language_id, production_name_id) VALUES (1, 2, '1999-01-08 04:05:06', 43, true, false, true, true, 'SF102', 'Handlingen udspiller sig på det lille Andersens Badehotel ved Vesterhavet i det nordligste Jylland i sommeren 1928 og følger livet blandt farverige stamgæster og lokale ansatte. Centralt i fortællingen står den 18-årige Fie, der i seriens første afsnit får ansættelse som stuepige. Hun har kun planlagt at være der sommeren over, men det lille hotel bliver hendes skæbne, mens tiden slår om fra optimisme til krise, og den store verden udenfor har retning mod katastrofen.', 'http://rannok.dk//TVCredits/production_images/badehotellet_s01.jpg', 1, 2, 1, 1);
INSERT INTO production (season, episode, release_date, length, subtitle, sign_language, active, validated, production_reference, production_bio, imageurl, production_company_id, production_type_id, language_id, production_name_id) VALUES (1, 1, '1969-04-20 04:05:06', 50, false, true, false, false, 'Z0101', 'På røven i nakskov - Underholdning for hele familien!', 'http://rannok.dk//TVCredits/production_images/paa_roeven_i_naksskov.jpg', 2, 2, 1, 2);
INSERT INTO production (release_date, length, subtitle, sign_language, active, validated, production_reference, production_bio, imageurl, production_company_id, production_type_id, language_id, production_name_id) VALUES ('2020-09-24 04:05:06', 116, false, false, false, false, 'ZDruk22020', 'Fire gymnasielærere og venner beslutter sig for at teste en teori om, at mennesket er født med en halv promille for lidt. Teorien lyder, at alkohol i blodet åbner sindet for omverdenen og får kreativiteten til at stige. Resultatet er opsigtsvækkende. Både undervisningen og resultaterne løfter sig, og vennerne begynder for alvor at mærke livet igen. I takt med at genstandene ryger indenbords, skrider eksperimentet fremad for nogle og af sporet for andre. Det bliver tydeligt, at alkohol kan skabe store resultater, men også at den slags vovemod kan have konsekvenser. Filmen er et drama om venskab, frihed, kærlighed – og alkohol.', 'http://rannok.dk//TVCredits/production_images/drukplakat.jpeg', 3, 1, 1, 3);


-- indsætter skuespillere i credit_name
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Rosalinde', 'Mynster', 'Poulstrupvej 8', 80997397, 'rosalinde@credits.dk', '1990-09-28', 'Danmark', 'Mynster fik sin filmdebut som 16-årig i Niels Arden Oplevs drama To verdener fra 2008, som byggede på en sand historie om et Jehovas Vidne, som bryder fri fra sin familie og menighed. I 2009 blev filmen det danske Oscar-bidrag i kategorien "Bedste udenlandske film". I 2010 spillede hun over for Thure Lindhardt i komedien Sandheden om mænd, og i 2012 var hun med i En kongelig affære. Hendes helt store folkelige gennembrud kom med hovedrollen som Fie i TV 2s dramaserie Badehotellet i 2014, som hun dog valgte at forlade efter fem sæsoner. I de følgende sæsoner nævnes Fie kun, da hun i seriens univers er flyttet til England og har fået børn. - https://da.wikipedia.org/wiki/Rosalinde_Mynster', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Bodil', 'Jørgensen', 'Vær Bakkevej 50', 74786567, 'bodil@credits.dk', '1961-03-03', 'Danmark', 'Under sine studier i engelsk litteratur på Aarhus Universitet begyndte hun en uddannelse til skuespiller, som hun siden afsluttede fra Statens Teaterskole i 1990. Hun har hovedsageligt slået sine folder på forskellige teatre, bl.a. Jomfru Ane Teatret og siden 1996 på Det Kongelige Teater, men hun har også haft markante roller på film, heriblandt store roller i Lars von Triers Idioterne og Fruen på Hamre. Hun medvirkede i 2005 i Jul I Valhal. - https://da.wikipedia.org/wiki/Bodil_J%C3%B8rgensen', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Morten', 'Hemmingsen', 'Kræn Hansensvej 8', 31136419, 'morten@credits.dk', '1980-12-19', 'Danmark', 'Hemmningsen er uddannet fra Skuespillerskolen ved Odense Teater i 2005. Medvirker i teater, tv og film. Komponerer musik og har udgivet pladen Hjertekur. Han er kæreste med skuespiller Gaia Rosberg. - https://da.wikipedia.org/wiki/Morten_Hemmingsen', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Ena', 'Spottag', 'Jens Otto Krags Plads 24', 21446244, 'ena@credits.dk', '1985-05-28', 'Danmark', 'Ena Spottag er skuespiller og uddannet i 2014 fra Skuespillerskolen ved Aarhus Teater. Hun er datter af skuespillerparret Jens Jørn Spottag og Dea Fog. Ena debuterede i sin første rolle som Martha i tv-serien Badehotellet i 2013, et år før hun blev færdiguddannet. Ena Spottag vandt sæson 12 af Vild med dans i 2015. Hun dansede sammen Thomas Evers Poulsen. - https://da.wikipedia.org/wiki/Ena_Spottag', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Ulla', 'Vejby', 'Bymarken 38', 72789301, 'ulla@credits.dk', '1984-06-15', 'Danmark', 'Ulla er en dansk skuespillerinde. Hun er bedst kendt for sin rolle i TV2s serie Badehotellet, hvor hun spiller stuepigen Edith. Hun gik i folkeskole på Nørre Snede Skole og i gymnasiet på Tørring Amtsgymnasium. Hun er uddannet på Skuespillerskolen ved Aarhus Teater fra 2008-2012 - https://da.wikipedia.org/wiki/Ulla_Vejby', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Merete', 'Damgaard Mærkedahl', 'Sneglevænget 3', 17962672, 'merete@credits.dk', '1981-12-27', 'Danmark', 'Merete Damgaard Mærkedahl er en dansk skuespiller. Hun er uddannet fra Skuespillerskolen ved Aarhus Teater i 2009. Hun medvirker i tv-serien Badehotellet. I 2020 deltog hun i sæson 17 af Vild med dans. Hun dansede med den professionelle danser Thomas Evers Poulsen. Parret vandt sæsonen. - https://da.wikipedia.org/wiki/Merete_M%C3%A6rkedahl', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Lars', 'Ranthe', 'Bredager Mark 39', 22525248, 'lars@credits.dk', '1969-08-26', 'Danmark', 'Ranthe begyndte sin skuespilkarriere i starten af 1980erne i filmen Gummi Tarzan (1981) og tv-serien Crash: Truslen fra det sorte hul (1984), før han i 1995 blev optaget på Odense Teater, hvor han dimitterede fra som skuespiller i 1998. Herefter medvirkede han i film som Se til venstre, der er en svensker (2003), Forbrydelser (2004), Adams æbler (2005) og i DR-dramaserien Sommer (2008). Ranthes filmiske gennembrud kom med rollen som Kjeld Petersen i filmen Dirch (2011), som han modtog en Bodil- og en Robert-pris i kategorierne "Bedste mandlig birolle" og "Årets mandlige birolle". Andre prominente roller inkluderer Jagten (2012), Skytten (2013), Dicte (2013-2016), Kollektivet (2016), Der kommer en dag (2016) og Badehotellet (2013-2021). Ranthe har senere modtaget endnu en Robert-pris i kategorien "Årets mandlige birolle" for sin rolle i tv-serien Kriger (2018), samt endnu en Bodil-nominering for filmen Kærlighed og andre katastrofer (2016), og en Robert-nominering for sin rolle i tv-serien Friheden (2018). Ranthes seneste rolle som Peter i den anmelderroste og Oscar-vindende film Druk (2020) har givet ham endnu en Bodil- og Robert-nominering i kategorierne "Bedste mandlige birolle" og "Årets mandlige birolle" - nomineringer som også er gået til hans med-birolleskuespillere i filmen, Magnus Millang og Thomas Bo Larsen. Ranthe modtog desuden sammen med sine medskuespillere, Mads Mikkelsen, Magnus Millang og Thomas Bo Larsen skuespilsprisen Silver Shell ved San Sebastián International Film Festival i Spanien. - https://da.wikipedia.org/wiki/Lars_Ranthe', 'http://rannok.dk//TVCredits/production_images/lars_ranthe_.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Anne', 'Louise Hassing', 'Studiestrædet 46', 77623013, 'anne@credits.dk', '1967-09-17', 'Danmark', 'Hun filmdebuterede i Nils Malmros Kærlighedens smerte (1992), for hvilken hun vandt både en Robert og en Bodil for bedste kvindelige hovedrolle. Hun fik igen en Bodil i 1999 – denne gang for sin næste filmpræstation i Lars von Triers Idioterne. Hun har desuden medvirket i filmatiseringerne af Jane Aamunds romaner Klinkevals og Juliane samt i tv-serierne Strisser på Samsø og De pokkers forældre. 2004-2007 fik hun stor succes med sin rolle som Ida i DR-dramaserien Krøniken. Den indbragte hende prisen som årets kvindelige tv-skuespiller i både 2004, 2005 og 2006. I 2007 deltog hun tv-serien Vild med dans sammen med Michael Olesen. Parret opnåede en 6. plads. Anne Louise Hassing er buddhist og tilknyttet den buddhistiske retning Soka Gakkai. Hun var gift med musikeren Peter Hellemann, indtil de blev skilt omkring maj 2017. Anne Louise har desuden fremvist sine køkkenfærdigheder i Kanal 5''s 4-stjerners Middag. - https://da.wikipedia.org/wiki/Anne_Louise_Hassing', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Amalie', 'Dollerup', 'Minervavej 36', 70944066, 'amalie@credits.dk', '1986-04-15', 'Danmark', 'Amalie Mathisson Dollerup er en dansk skuespillerinde, der er uddannet fra skuespillerskolen ved Aarhus Teater i 2010. Hun blev kendt fra tv-serien Strisser på Samsø, og har siden medvirket i flere film og tv-serier, heriblandt Badehotellet. Hun har også lagt stemme til flere animationsfilm, medvirket i en række musicals såsom Saturday Night Fever og En Kort En Lang. I sommeren 2018 medvirkede hun i Cirkusrevyen på Bakken, og i 2019 spillede hun hovedrollen som musen i Mød mig på Cassiopeia på Folketeatret. - https://da.wikipedia.org/wiki/Amalie_Dollerup - https://da.wikipedia.org/wiki/Amalie_Dollerup', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Peter', 'Hesse Overgaard', 'Krogerupvej 14', 81914964, 'peter@credits.dk', '1954-05-19', 'Danmark', 'Peter Hesse Overgaard er en dansk skuespiller. Allerede i 1971 rejste han til USA for at prøve lykken som teenage-skuespiller på de skrå brædder, vendte tilbage et par år efter og afsluttede sin skuespilleruddannelse på Team Teatret i Herning. Han har gennem mange år arbejdet som freelance, som bl.a. har givet ham teaterroller på Fiolteatret, Husets Teater, Bådteatret, Det Danske Teater, Det ny Teater og Café Teatret. Han stoppede dog som teaterskuespiller i 1990. Han fandt teatret en hård disciplin og "orkede ikke at spille det samme hver aften i mange uger i træk". - https://da.wikipedia.org/wiki/Peter_Hesse_Overgaard.', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Cecilie', 'Stenspil', 'Anemonevej 49', 90585762, 'cecilie@credits.dk', '1979-10-22', 'Danmark', 'Cecilie Maria Stenspil er en dansk skuespiller uddannet fra Skuespillerskolen ved Odense Teater i 2006. Hun har haft hovedroller i en stor række forestillinger på flere danske teatre, blandt andre det kongelige teater,Århus teater), Odense teater og på Folketeateret og hun har indspillet flere film og tv-serier. Desuden har hun lagt stemme til en enorm række tegnefilmsproduktioner. - https://da.wikipedia.org/wiki/Cecilie_Stenspil', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Jens', 'Jacob Tychsen', 'Skovbrynet 42', 20332722, 'jens@credits.dk', '1975-11-19', 'Danmark', 'Jens Jacob Tychsen er en dansk skuespiller. Han er uddannet fra Skuespillerskolen ved Aarhus Teater i 1998 og har siden medvirket i en række forestillinger på teatre over hele landet. Tychsen har også medvirket som skurk i Rejseholdet og er kendt for sin hovedrolle som den dameglade kongelige skuespiller, Edward Weyse, i tv-serien Badehotellet. Han har endvidere lagt stemme til figurer i en lang række tegnefilm. - https://da.wikipedia.org/wiki/Jens_Jacob_Tychsen', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Bjarne', 'Henriksen', 'Rødkælkevej 32', 32358841, 'bjarne@credits.dk', '1959-01-18', 'Danmark', 'Bjarne Henriksen er en dansk skuespiller. Af teatre har han bl.a. været ansat på Jomfru Ane Teatret og Svalegangen. Han har spillet biroller i en lang række danske film fra sidste halvdel af 1990erne og ind i det nye årtusinde bl.a. De største helte, Festen, Pizza King, Dybt vand, Kinamand og Af banen!. - https://da.wikipedia.org/wiki/Bjarne_Henriksen', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Anette', 'Støvelbæk', 'Enghavevej 51', 32641190, 'anette@credits.dk', '1967-07-27','Danmark', 'Anette Støvelbæk er en dansk skuespiller. Hun fik sit gennembrud med rollen som Olympia i Lone Scherfigs film Italiensk for begyndere. På tv har hun medvirket i TV 2-serien Forsvar, og som Frk. Toft i DR-serien Krøniken. Ydermere spiller hun lægen Astrid Borg i TV-serien Lærkevej. Anette Støvelbæk er endvidere tilknyttet lydbogsforlaget Momo - Skuespillernes Lydbogsværksted, hvor hun har indlæst flere lydbøger. - https://da.wikipedia.org/wiki/Anette_St%C3%B8velb%C3%A6k', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Birthe', 'Neumann', 'Malervej 12', 13206378, 'birthe@credits.dk', '1947-04-30', 'Danmark', 'Birthe Neumann Frederiksen er en dansk skuespillerinde. Hun er uddannet fra Statens Teaterskole i 1972 og har medvirket i flere skuespil på Det Kongelige Teater. Hun har ligeledes medvirket i en række danske film. Hun har siden 1974 været gift med skuespilleren Paul Hüttel. Hun er Ridder af 1. grad af Dannebrogordenen. - https://da.wikipedia.org/wiki/Birthe_Neumann', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Sonja', 'Oppenhagen', 'Sylvestervej 46', 30356155, 'sonja@credits.dk', '1948-12-11', 'Danmark', 'Sonja Gladys Oppenhagen Behrend er en dansk skuespillerinde, der oprindeligt var uddannet balletdanserinde. Allerede i 1964 kunne hun opleves på dansk TV, hvor hun juleaften havde titelpartiet i Hans Brenaas TV- ballet "Den Lille Pige Med Svovlstikkerne" over H.C. Andersens eventyr. Hun afsluttede sin skuespilleruddannelse på Odense Teater i 1971 – men var inden da allerede blevet landskendt for sin rolle fra filmene om Min søsters børn – og har siden optrådt på en lang række teatre over det meste af landet, herunder ABC Teatret, Aveny Teatret, Scala, Folketeatret, Gladsaxe Teater og Rialto Teatret. - https://da.wikipedia.org/wiki/Sonja_Oppenhagen', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Anders', 'Juul', 'Grimsbyvej 46', 54870278, 'anders@credits.dk', '1981-01-14', 'Danmark','Anders Juul er en dansk skuespiller, der er mest kendt for at har spillet med i dansk drama tv-serier som Badehotellet, Lykke og Borgen. - https://da.wikipedia.org/wiki/Anders_Juul', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Sigurd', 'Holmen Le Dous', 'Kirsebærvej 34', 15438710, 'sigurd@credits.dk', '1983-09-27', 'Danmark', 'Sigurd Holmen Le Dous, der er søn af radioværten Lars le Dous, blev uddannet på Skuespillerskolen ved Aarhus Teater i 2009. Han blev rigtig kendt i rollen som den homoseksuelle reklametegner Philip Dupont i tv-serien "Badehotellet". Sigurd Holmen Le Dous blev i 2017 gift på Frederiksberg Rådhus med journalist på Radio 24syv Sidsel Brandt Hasberg. I 2018 gentog de brylluppet ved en kærlighedsfest på stranden i Kulhuse. - https://danskefilm.dk/skuespiller.php?id=13865', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Mads', 'Wille', 'Limfjordsvej 44', 43380785, 'mads@credits.dk', '1971-11-10', 'Danmark', 'Mads Michael Wille er søn af afdelingsleder Gunnar Wille og jordemoder Jette Wille (død 2008). Han er uddannet på Skuespillerskolen ved Århus Teater 1998. Han debuterede i "Shopping and Fucking" på CaféTeatret (1998) og har siden medvirket i en lang række forestillinger på de københavnske scener – bl.a. "Den dobbelte troløshed" og "Lige for lige" på Grønnegårdsteatret, "Kuren" på Husets Teater, "Faust er død" og "Dada" på Kaleidoskop, "Maskarade" på Folketeatret, "Parlamentet" på Das Beckwerk, "Afrikas Stjerne" på Bådteatret, "Anne Sophie Hedvig" og "Fyrværkerimesterens datter" på Det Kgl. Teater. På film og tv han man i de senere år kunnet opleve ham i "Drabet", "Rejseholdet", "Hotellet", "Forsvar" og "Ørnen", og i 2006 fik han sin debut som sceneinstruktør med forestillingen "Demokratiske bestræbelser" på Kaleidoskop. Mads Wille var i 2002 medstifter af teatret PLAN-B, hvor han fortsat udgør den kunstneriske ledelse sammen med Simon K. Boberg. På PLAN-B har han i 2004 medvirket i Dejan Dukovskis "På den 2. side" og det var også her han startede den populære trylle-event Magic Fight Night. Mads Wille blev den 10. juni 2000 gift med skuespillerinden Rie Nørgaard, men blev skilt i 2008 og danner nu par med skuespillerinden Tilde Maja Frederiksen. - https://danskefilm.dk/skuespiller.php?id=7304', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, imageurl) VALUES ('Alberte', 'Blichfeldt', 'Mejlsvej 44', 74723710, 'alberte@credits.dk', '2002-01-09', 'Danmark', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, imageurl) VALUES ('Liva', 'Kristoffersen', 'Frydendalsvej 24', 16819432, 'liva@credits.dk', '2004-10-01', 'Danmark', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Peter', 'Gilsfort', 'Håndværkervej 25', 43285932, 'peter@credits.dk', '1985-07-13', 'Danmark', 'Peter Eric Gilsfort er søn af distriktschef Eric Gilsfort (død 1998) og hustru kontorchef Elisabeth Gilsfort. Han blev uddannet fra Skuespillerskolen ved Aarhus Teater 1980 og debuterede i "Livet er en drøm". Fra 1980-90 var han ansat på Aarhus Teater, hvor han medvirkede i en lang række forestillinger, bl.a. i "Lykke Per". Han blev ansat på Det Kongelige Teater i 1991 og har bl.a. medvirket i "Kjartan og Gudrun", "Richard III", "Kærlighedens kværulanter" og "Fruentimmerskolen". I København har han optrådt på flere teatre bl.a. i Det Ny Teaters opsætning af "Edderkoppekvindens Kys" og Rialto Teatrets "Mikaël". Han har desuden medvirket i TV-serierne "Charlot og Charlotte" og "Madsen og Co.". Han blev den 13. juli 1985 gift med Dorthe Maria Scheutz (4-04-1956) - https://danskefilm.dk/skuespiller.php?id=815', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, imageurl) VALUES ('Lukas', 'Helt', 'Fasanvej 33', 59213472, 'lukas@credits.dk', '2002-02-13', 'Danmark', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Lykke', 'Scheuer', 'Thoustrupvej 22', 97208476, 'lykke@credits.dk', '1972-05-28', 'Danmark', 'Lykke Scheuer er uddannet på Michael Chekov Studio, Århus i 1996. Hun har udfoldet sit talent på Teater Grob, hvor hun har fremstillet karikerede og surrealistiske karakterer. I 1999 spillede hun en krumbøjet olding i "Forstanderinden" og i 2003 medvirkede hun i Thor Bjørn Krebs''s "Europamestrene". -https://danskefilm.dk/skuespiller.php?id=7480', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Jeppe', 'Koudal Frostholm', 'Nordre Kåstrupvej 50', 14839387, 'jeppe@credits.dk', '1991-11-05', 'Danmark', 'Jeppe Koudal Frostholm, rekvisitør, startede som freelance runner på forskellige spillefilm og tv-serier. Siden har han arbejdet som rekvisitør på hovedsageligt tv-serier og diverse spillefilm. Han har blandt andet været rekvisitør assistent på tv-serien "Bedrag" og runner og rekvisitør assistent på tv-serien "Badehotellet". - https://danskefilm.dk/skuespiller.php?id=26943', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Waage', 'Sandø', 'Jedsted Møllevej 26', 77654413, 'waage@credits.dk', '1943-05-08', 'Danmark', 'Waage Sandø (Pedersen) er født på Vesterbro som søn af værtshusejer Jørgen Peder Pedersen og Emmy Asta Pedersen. Han er uddannet på Privatteatrenes elevskole 1964-1966. I årene 1968-70 var han tilknyttet Aarhus Teater og han var leder af Friluftsteatret i København 1968-73 og af Svalegangen i Aarhus 1970-75. Fra 1975 til 1977 var han skuespillet og instruktør på Det Danske Teater, Folketeatret og TV-Teatret og i årene fra 1977 til 1985 direktør for Odense Teater. Derefter startede han som free lance skuespiller i København. Waage Sandø har været vidt omkring inden for teater, revy, radio, tv og film, og var fra 1990 i en lang årrække fast tilknyttet Det Kgl. Teater, hvor han f.eks. huskes for sin rolle i "Sælsomt mellemspil". I 1991 blev han chef for Det Danske Teater. Han har medvirket i film som "Min fynske barndom", "Kun en pige", "At kende sandheden" og "Tid til forandring". På tv har vi set ham bl.a. i serien "Landsbyen", og senest er han blevet kendt fra "Rejseholdet". Senest bliver han husket for rollen som direktør Kaj Holger Nielsen i TV-serien "Krøniken". Waage Sandø var i sit første ægteskab gift med Kirsten, som han levede sammen med i 17 år og var gift med i de 12. I sit andet ægteskab er Waage Sandø gift med skuespillerinden Pia Jondal. - https://danskefilm.dk/skuespiller.php?id=1030', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Robert', 'Hansen', 'Ågårdsvej 17', 45516785, 'robert@credits.dk', '1979-10-05', 'Danmark', 'Robert Hansen er født og opvokset i Hvidovre som søn af Mogens og Liselotte Hansen (død 2013). Han er uddannnet på udenlandske skoler i 1998. Robert Hansen debuterede i filmen "Vildbassen" i 1994 og fik det helt store gennembrud i 1999 som Viktor Knudsen i filmen "Kærlighed ved første hik". Udover filmene om Anja & Viktor har han blandt andet spillet med i Krummerne 3, Dykkerne, Kongekabale og Familien Gregersen. I en årrække blev hans karriere berørt af historier i pressen om alkohol- og kokainmisbrug. I 2006 fortalte han offentligt at han var i behandling for sit misbrug og året efter var Robert Hansen klar til et come-back med den fjerde Anja & Viktor film. Desuden vandt han i 2007 sammen med dansepartneren Marianne Eihilt TV2-konkurrencen "Vild med dans". Han har tidligere været kæreste med skuespillerinden Mira Wanting og dannede siden i fem år par med hf-studerende Pernille Fredskild Thøgersen indtil de skiltes i 2015. Fra september 2020 har han dannet par med Nynne Larsen. - https://danskefilm.dk/skuespiller.php?id=2133', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Benedikte', 'Hansen', 'Provst Petersensvej 35', 45415148, 'benedikte@credits.dk', '1958-09-24', 'Danmark', 'Benedikte Hansen er datter af læge Flemming Hansen (død 2003) og hustru cand.jur. Aase Riegels Borch. Hun blev uddannet på Statens Teaterskole 1978-81. Hun blev samme år tilknyttet Rimfaxe teater og fik derefter gennembrud som Eliza i Amagerscenens opsætning af "My Fair Lady" (1986) og som Hilde Wangel i "Bygmester Solness". Hun optrådte på bl.a. Aveny Teatret og Hvidovre Teater, inden hun i 1990 blev ansat på Det Kongelige Teater. Hun har bl.a. spillet Elektra i "Sorg Klæder Elektra" i 1991, Gudrun i "Kjartan og Gudrun" i 1993, tsarinaen i "Rædsel for natten" i 1997, Lady Macbeth i Macbeth-udgaven på Gladsaxe Teater i 1998, Margit i "Hr. Bengts hustru" i 2000, pastorinde Grå i "Egelykke" i 2000, Cleopatra i "Antonius og Cleopatra" i 2001, hustruen Anne i "Detaljer" i 2002 og hustruen i "Lille Eyolf" i 2006. Desuden har hun spillet Jessica i "Urene hænder" og Mrs. Robinson i "Fagre voksne verden" på Nørrebros Teater. Hun debuterede i filmen "Walter og Carlo - op på fars hat" fra 1985. I 1988 medvirkede hun i TV-serien "To som elsker hinanden", hvor hun spillede forfatteren Stuckenbergs hustru Ingeborg, der forlod ham og flygtede sammen med gartneren til New Zealand, hvor hun begik selvmord. Hun medvirkede i endnu en TV-serie, nemlig "Bryggeren", hvor hun spillede Lauras selskabsdame. Hun er sidst set i filmen "Tøsepiger" fra 1996, hvor hun spiller middagsgæst. Benedikte Hansen blev den 27. juli 1983 gift med forfatter, skuespiller og instruktør Gerz Feigenberg. Ægteskabet blev opløst i 1993. Hun var siden fra 1998 gift i 9 år med chefen for Det Kongelige Teater, cand. jur. Michael Christiansen (05-06-1945). Parret blev separeret i 2007 og i fire år fra 2012-2016 dannede Benedikte Hansen par med skuespilleren Viggo Sommer. - https://danskefilm.dk/skuespiller.php?id=90', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Ole', 'Dupont', 'Daugårdvej 4', 64121838, 'ole@credits.dk', '1948-06-17', 'Danmark', 'Ole Dupont har erfaring fra film, TV og reklamefilm gennem omkring 40 år. Han har haft statistroller og biroller med replikker i nyere danske film og har været stuntkører, stuntman og fight-koordinator. Sideløbende med sit statistarbejde har han beklædt flere ledende stillinger i erhvervslivet bl.a. som administrerende direktør og bestyrelsesformand. - https://danskefilm.dk/skuespiller.php?id=17775', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, imageurl) VALUES ('Martin', 'Greisen', 'Vrouevej 51', 42487591, 'martin@credits.dk', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Stig', 'Hoffmeyer', 'Norgaardsvej 39', 45000251, 'stig@credits.dk', '1940-05-27', 'Danmark','Stig Rode Hoffmeyer, der er søn af gesandt, generalkonsul Hans Poul Hoffmeyer (død 1962) og hustru Vibeke Rode (død 1990), er autodidakt skuespiller. Efter studentereksamen fra Herlufsholm Kostskole i 1958 begyndte han at læse jura på Københavns Universitet. Han fik en cand.jur. eksamen i 1964 inden han indledte sin karriere som skuespiller. Han optrådte på Studenterscenen i årene 1960-1964, hvor han fik sit gennembrud som faderen i "Dværgen der blev væk". Karrieren startede for alvor midt i 1960erne på det lille teater Svalegangen i Århus. Han markerede sig i stykker som "Slutspil", "Stikord" og "Hypdangbok". Han var i årene 1973-78 på Aarhus Teater og siden på flere københavnske teatre, bl.a. Det Kongelige Teater, hvor han bl.a. medvirkede i "Richard III", "Urene hænder" og "Anna Sophie Hedvig", Det Danske Teater, Gladsaxe Teater, Hippodromen, Rialto og Folketeatret. Han var Edvard Munch i Sten Kaaløs "Alfa og Omega" i 1980. Han var van Gogh i "Postbudet fra Arles" og Ludvig Holberg i "Den poetiske raptus" i 1984. - https://danskefilm.dk/skuespiller.php?id=910', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Jesper', 'Riefensthal', 'Østertorv 5', 70993077, 'jesper@credits.dk', '1979-04-01', 'Danmark', 'Jesper Riefensthal er uddannet på Skuespillerskolen ved Odense Teater i 2006. Han modtog Talentprisen ved Årets Reumert 2008 for sin præstation i forestillingen "Solidaritet" på Teater Momentum i Odense, hvor han var en del af ensemblet. Jesper Riefensthal har medvirket i flere forestillinger på Odense Teater bl.a. i "Skatteøen" 2009. Han er fast tilknyttet truppen på Mungo Park Kolding. - https://danskefilm.dk/skuespiller.php?id=10781', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Ole', 'Thestrup', 'Mejsevej 39', 34691438, 'ole@credits.dk', '1948-12-03', 'Danmark', 'Ole Svane Thestrup, der var søn af afdelingsleder Christian Svane Thestrup (død 1996) og Ella Thestrup, født Jensen, tog studentereksamen og blev derefter en fast del af det århusianske musikliv. Senere havde han en ultra-kort karriere som journalistelev i Hobro inden han læste dramaturgi på Aarhus Universitet i tre år og blev uddannet på skuespillerskolen ved Århus Teater i årene 1973-1976. Han fortsatte på Århus Teater indtil 1980 og gjorde sig bl.a. bemærket i flere musicals. I 1980 blev han ansat på Det Kongelige Teater, hvor han han bl.a. fik roller i "Hamlet", "Don Ranudo", "Erasmus Montanus" og "Jeppe på Bjerget". Han forlod midt i 1980erne Det Kongelige Teater for at blive freelance-skuespiller. - https://danskefilm.dk/skuespiller.php?id=767n - https://danskefilm.dk/skuespiller.php?id=767', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, imageurl) VALUES ('Sverrir', 'Gudnason', 'Tjørnevej 35', 17931121, 'sverrir@credits.dk', '1978-09-12', 'Sverige', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Hanne', 'Windfeld', 'Ø. Børstingvej 44', 29632276, 'hanne@credits.dk', '1959-03-04', 'Danmark', 'Hanne Windfeld Lund, der er uddannet på Statens Teaterskole i 1985, har siden 2005 været i ensemblet på Aalborg Teater, hvor hun har haft markante roller såvel indenfor det klassiske som det moderne repertoire. I 2005 medvirkede hun i "Martas tema" og derudover har hun bl.a. spillet Dronning Elisabeth i "Maria Stuart" og politikerhustru i "Mød min hr. mor". Inden årene på Aalborg Teater, som hun iøvrigt også var tilknyttet 1985-87, har hun været ansat i to år på Det Kongelige Teater og desuden på Folketeatret, Betty Nansen Teatret, Gladsaxe Teater, Husets Teater, Odense Teater og Tåstrup Teater. Hanne Windfeld har desuden medvirket som Viola i børne-tv-serien "Bamse og Kylling" og i flere tv-serier bl.a. "Strenge tider", "Strisser på Samsø" og "Krøniken". Ved siden af sit arbejde som skuespiller har hun uddannet sig til terapeut. Fra 2000 til 2002 tog hun en psykoterapi-uddannelse på Vedfeldt Institutter og fra 2002 til 2004 tog hun psykoterapi-uddannelse på Psykoterapeutisk Institut. Hun har modtaget Marguerite Viby''s Legat 1989, Agnes Rehni''s Legat 1993, Lise Wolst''s Legat 2000 og Georg Phillip''s Legat 2007. - https://danskefilm.dk/skuespiller.php?id=355', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Ida', 'Dwinger', 'Tågelundvej 33', 72044566, 'ida@credits.dk', '1957-03-06', 'Danmark', 'Ida Dwinger er uddannet fra Skuespillerskolen ved Odense Teater i 1984. Ida Dwinger har bl.a. haft roller i "En verdensomsejling under bordet" på Café Teatret i 1996 og i flere roller på Det Kongelige Teater, heriblandt "Skybrud", "Egelykke" og "Sparekassen". - https://danskefilm.dk/skuespiller.php?id=3279', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Henrik', 'Birch', 'Vesterkærsvej 7', 16686398, 'henrik@credits.dk', '1956-09-05', 'Danmark', 'Henrik Birch er uddannet på Statens Teaterskole i 1979. Han har i sin karriere arbejdet på de tre landsdelsscener og på mange af de små eksperimenterende teatre. Han har bl.a. spillet Frank i "Jernbyrd" på Mammut Teatret og Hans Jørgen Skovgaard i "Trangen" på Får302. I sit ægteskab med skuespillerinden Mette Munk Plum fik han to børn. Skuespillerparret blev skilt i 1996. - https://danskefilm.dk/skuespiller.php?id=1251', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Mads', 'Mikkelsen', 'Dyrestevej 17, 2900 Hellerup', 25945230, 'MadsMikkelsen@skuespiller.dk', '1965-11-22', 'Danmark', 'Uddannet fra Aarhus Teaters skuespillerskole i 1996, efter at have danset i 8-9 år, blandt andet med ensemblet Mikado og på Martha Grahams sommerskole i New York. Mikkelsen fik sit gennembrud som filmskuespiller i Nicolas Winding Refns debut Pusher (1996), der var udtaget til festivaler i Toronto, Göteborg og Rotterdam. Samarbejdet med Refn fortsatte på Bleeder (1999), Pusher II (2004) og Valhalla Rising (2009), der var udtaget til hovedkonkurrence i Venedig. Han har desuden samarbejdet med Anders Thomas Jensen på de indflydelsesrige film Blinkende lygter (2000), De grønne slagtere (2003), Adams æbler (2005), Mænd og høns (2015) og Retfærdighedens ryttere (2020). Også Susanne Bier har han lavet en række film med: Elsker dig for evigt (2002) var udtaget til Toronto, og Efter brylluppet (2006) blev Oscar-nomineret.', 'http://rannok.dk//TVCredits/production_images/mads_mikkelsen.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Thomas Bo', 'Larsen', 'Kunstner Allé 5, 2900 Hellerup', 29957485, 'ThomasBoLarsen@skuespiller.dk', '1963-11-27', 'Danmark', 'Oprindeligt glarmester. Uddannet på Skuespillerskolen ved Odense Teater 1986-90. Larsens debuterede som filmskuespiller i Søren Faulis kultfilm De skrigende halse (1992). Derefter fulgte et karrierelangt samarbejde med Thomas Vinterberg, der startede med Vinterbergs filmskoleafgangsfilm Sidste omgang (1993) og i 90erne fortsatte med Drengen, der gik baglæns (1995), De største helte (1996) og Festen (1999), der indbragte ham en Robert-pris.', 'http://rannok.dk//TVCredits/production_images/thomas_bo_larsen_.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, bio, imageurl) VALUES ('Magnus', 'Millang', 'Kunstner Allé 33, 2900 Hellerup', 56895241, 'MagnusMillang@skuespiller.dk', '1981-07-20', 'Danmark', 'Magnus Millang fik sit gennembrud i TV 2 Zulus komedieserie Danish Dynamite i 2012. I 2014 vandt han prisen som Årets komiker ved Comedy Galla Awards. Millang spillefilmdebuterede som skuespiller i Kvinden i buret (2013) og har siden medvirket i en række film, blandt andet Kollektivet (2016), Dan-Dream (2017), Kursk (2018) og Druk (2020), der indbragte ham og hans tre medskuespillere skuespilprisen ved San Sebastián Filmfestival.', 'http://rannok.dk//TVCredits/production_images/magnus_millang.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, imageurl) VALUES ('Ib', 'Andersen', 'Nakskovvej 1, 4900 Nakskov', 78456532, 'IbAndersen@Nakskov.dk', '1965-09-17', 'Danmark', 'http://rannok.dk//TVCredits/production_images/ib_fra_nakskov_.jpg');
INSERT INTO credit_name (first_name, last_name, address, phone, email, date_of_birth, country, imageurl) VALUES ('Kenneth', 'Rasmussen', 'Nakskovvej 4, 4900 Nakskov', 59647852, 'KennethsKlarsyn@Clavoyant.dk', '1971-10-05', 'Danmark', 'http://rannok.dk//TVCredits/production_images/kenneth_fra_nakskov.jpg');
INSERT INTO credit_name (first_name, last_name, country, imageurl) VALUES ('Jacob', 'Kusk', 'Danmark', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg'); -- Fotograf
INSERT INTO credit_name (first_name, last_name, country, imageurl) VALUES ('Steen', 'Schapiro', 'Danmark', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, country, imageurl) VALUES ('Janne Bjerg', 'Sørensen', 'Danmark', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, country, imageurl) VALUES ('Karen', 'Søsted', 'Danmark', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, country, imageurl) VALUES ('Bobby', 'Hess', 'Danmark', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, country, imageurl) VALUES ('Halfdan', 'E.', 'Danmark', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, country, imageurl) VALUES ('Jeppe', 'Kaas', 'Danmark', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, country, imageurl) VALUES ('Jette', 'Lehmann', 'Danmark', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO credit_name (first_name, last_name, country, imageurl) VALUES ('Christian', 'Grønvall', 'Danmark', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');
INSERT INTO  credit_name (first_name, last_name, country, imageurl) VALUES ('Jette', 'Termann', 'Danmark', 'http://rannok.dk/TVCredits/credit_images/Unknown_person.jpg');


-- indsætter krediteringer i credit
INSERT INTO credit (role, validated, production_id) VALUES ('Fie', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Molly Andersen', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Morten Enevoldsen', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Martha, kokkepige', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Edith, stuepige', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Otilia, stuepige', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Grosserer Georg Madsen', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Therese Madsen', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Amanda Berggren (f. Madsen)', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Kontorchef Hjalmar Aurland', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Helene Aurland', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Skuespiller Edward Weyse', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Fabrikant Otto Frigh', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Alice Frigh', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Fru (Olga) Fjeldsø', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Lydia Vetterstrøm (Fjeldsøs søster)', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Arkitekt Max Berggren', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Reklametegner Philip Dupont', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Grev Ditmar', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Vera', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Bertha', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Alfred Jensen', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Leslie', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Frk. Malling', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Postbud', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Grev Valdemar', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Verner', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Grevinde Anne-Grethe', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Bargæst', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Poul', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Hr. Gottlieb', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Ingeniør Rønn', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Julius Andersen', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Bremer', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Fru Damgaard', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Fru Skibsreder Holm', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Arne Kokholm', true, 1);
INSERT INTO credit (role, validated, production_id) VALUES ('Ib', false, 2);
INSERT INTO credit (role, validated, production_id) VALUES ('Kenneth-Klarsyn', false, 2);
INSERT INTO credit (role, validated, production_id) VALUES ('Martin', false, 3);
INSERT INTO credit (role, validated, production_id) VALUES ('Tommy', false, 3);
INSERT INTO credit (role, validated, production_id) VALUES ('Nikolaj', false, 3);
INSERT INTO credit (role, validated, production_id) VALUES ('Peter', false, 3);
INSERT INTO credit (validated, production_id) VALUES (false, 1); -- Fotograf
INSERT INTO credit (validated, production_id) VALUES (false, 1); -- Klipper
INSERT INTO credit (validated, production_id) VALUES (false, 1); -- Klipper
INSERT INTO credit (validated, production_id) VALUES (false, 1); -- Klipper
INSERT INTO credit (validated, production_id) VALUES (false, 1); -- Lyd
INSERT INTO credit (validated, production_id) VALUES (false, 1); -- Musikals arrangement
INSERT INTO credit (validated, production_id) VALUES (false, 1); -- Musikals arrangement
INSERT INTO credit (validated, production_id) VALUES (false, 1); -- Scenograf
INSERT INTO credit (validated, production_id) VALUES (false, 1); -- Casting
INSERT INTO credit (validated, production_id) VALUES (false, 1); -- Casting



-- indsætter foreign keys i credit_name_credit_type_association
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (1, 24, 1);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (2, 24, 2);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (3, 24, 3);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (4, 24, 4);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (5, 24, 5);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (6, 24, 6);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (7, 24, 7);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (8, 24, 8);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (9, 24, 9);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (10, 24, 10);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (11, 24, 11);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (12, 24, 12);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (13, 24, 13);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (14, 24, 14);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (15, 24, 15);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (16, 24, 16);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (17, 24, 17);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (18, 24, 18);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (19, 24, 19);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (20, 24, 20);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (21, 24, 21);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (22, 24, 22);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (23, 24, 23);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (24, 24, 24);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (25, 24, 25);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (26, 24, 26);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (27, 24, 27);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (28, 24, 28);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (29, 24, 29);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (30, 24, 30);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (31, 24, 31);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (32, 24, 32);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (33, 24, 33);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (34, 24, 34);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (35, 24, 35);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (36, 24, 36);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (37, 24, 37);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (38, 24, 40);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (39, 24, 41);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (40, 24, 42);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (7, 24, 43);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (41, 24, 38);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (42, 24, 39);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (43, 10, 44);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (44, 15, 45);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (45, 15, 46);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (46, 15, 47);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (47, 20, 48);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (48, 26, 49);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (49, 26, 50);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (50, 39, 51);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (51, 3, 52);
INSERT INTO credit_name_credit_type_association (credit_name_id, credit_type_id, credit_id) VALUES (52, 3, 53);


-- inserting relation between production and genres
INSERT INTO genres_production_association (production_id, genre_id) VALUES (1, 2);
INSERT INTO genres_production_association (production_id, genre_id) VALUES (1, 8);
INSERT INTO genres_production_association (production_id, genre_id) VALUES (2, 13);
INSERT INTO genres_production_association (production_id, genre_id) VALUES (3, 1);