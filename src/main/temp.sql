CREATE TABLE GPS_Coordinates(id_coordinates NUMBER PRIMARY KEY, latidude VARCHAR2(50), longitude VARCHAR2(50));

CREATE TABLE Country(id_country NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                     country_name VARCHAR2(150));

CREATE TABLE Use_License(id_license NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         license_type VARCHAR2(150));

CREATE TABLE PERSON(id_person NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                    fk_id_country NUMBER,
                    first_name VARCHAR2(150),
                    last_name1 VARCHAR2(150),
                    last_name2 VARCHAR2(150),
                    email VARCHAR2(150),
                    FOREIGN KEY (fk_id_country) REFERENCES COUNTRY,
                    direccion VARCHAR2(450));


CREATE TABLE USUARIO(id_usuario NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                     fk_person_id NUMBER NOT NULL,
                     FOREIGN KEY (fk_person_id) REFERENCES PERSON);

INSERT INTO PERSON( FK_ID_COUNTRY, FIRST_NAME, LAST_NAME1, LAST_NAME2, EMAIL, DIRECCION) VALUES (40,'Alejandro', 'Quesada', 'Calderón', 'alejosequesada@gmail.com','125m Oeste del Estadio Municipal Allen Rigionni, Grecia, Alajuela');
INSERT INTO PERSON (FK_ID_COUNTRY, FIRST_NAME, LAST_NAME1, LAST_NAME2, EMAIL, DIRECCION) VALUES (40, 'Bryan', 'Castro', 'Madrigal', 'bryancastromadrigal@gmail.com', 'Giralda Desamparados Alajuela');
INSERT INTO PERSON (FK_ID_COUNTRY, FIRST_NAME, LAST_NAME1, LAST_NAME2, EMAIL, DIRECCION) VALUES (40, 'Annette', 'Morúa', 'Rodriguez', 'morúarodriguez@hotmail.com', 'Agua Clara, Alajuela');
INSERT INTO PERSON (FK_ID_COUNTRY, FIRST_NAME, LAST_NAME1, LAST_NAME2, EMAIL, DIRECCION) VALUES (40, 'Eithan', 'Rivera', 'Rivera', 'eithanrivera@yahoo.com', 'Rio segundo Alajuela');
INSERT INTO PERSON (FK_ID_COUNTRY, FIRST_NAME, LAST_NAME1, LAST_NAME2, EMAIL, DIRECCION) VALUES (40, 'Celeste', 'Fonseca', 'Perez', 'celestefonse@gmail.com', 'San Rafael Alajuela');
INSERT INTO PERSON (FK_ID_COUNTRY, FIRST_NAME, LAST_NAME1, LAST_NAME2, EMAIL, DIRECCION) VALUES (109, 'Ricardo', 'Noverola', 'Sanchez', 'noverolarichar@gmail.com', 'Tabasco'); --mexicano
INSERT INTO PERSON (FK_ID_COUNTRY, FIRST_NAME, LAST_NAME1, LAST_NAME2, EMAIL, DIRECCION) VALUES (186, 'Valeria', 'Ogletree', 'Ocampo', 'Ogletree@gmail.com', 'Winsconsin'); --gringo
INSERT INTO PERSON (FK_ID_COUNTRY, FIRST_NAME, LAST_NAME1, LAST_NAME2, EMAIL, DIRECCION) VALUES (35, 'Germán', 'Garmendia', 'Aranis', 'holasoygerman@gmail.com', 'Copiapó'); -- chileno
INSERT INTO PERSON (FK_ID_COUNTRY, FIRST_NAME, LAST_NAME1, LAST_NAME2, EMAIL, DIRECCION) VALUES (142, 'Nathan', 'Drake', 'Marston', 'Marston@gmail.com', 'Rusia');--Ruso
INSERT INTO PERSON (FK_ID_COUNTRY, FIRST_NAME, LAST_NAME1, LAST_NAME2, EMAIL, DIRECCION) VALUES (40, 'Joshua', 'Solís', 'Fuentes', 'joshuasolisfuentes@gmail.com', 'Urbanización Alicante Desamparados Alajuela');
INSERT INTO PERSON (FK_ID_COUNTRY, FIRST_NAME, LAST_NAME1, LAST_NAME2, EMAIL, DIRECCION) VALUES (81, 'Michele', 'Rech', 'Fernandez', 'zerocalcare@gmail.com', 'Cortona');
INSERT INTO PERSON (FK_ID_COUNTRY, FIRST_NAME, LAST_NAME1, LAST_NAME2, EMAIL, DIRECCION) VALUES (186, 'George', 'Lucas', 'Junior', 'lucasfilm@hotmail.com', 'California');
INSERT INTO PERSON (FK_ID_COUNTRY, FIRST_NAME, LAST_NAME1, LAST_NAME2, EMAIL, DIRECCION) VALUES (186, 'Joel', 'Miller', 'Williams', 'Joelmiller@hotmail.com', 'Jackson, Wyoming'); -- mejor jackson :v en wyoming
INSERT INTO PERSON (FK_ID_COUNTRY, FIRST_NAME, LAST_NAME1, LAST_NAME2, EMAIL, DIRECCION) VALUES (81, 'Ezio', 'Auditore', 'Firenze', 'ezioauditore@gmail.com', 'Venecia'); -- italia
INSERT INTO PERSON (FK_ID_COUNTRY, FIRST_NAME, LAST_NAME1, LAST_NAME2, EMAIL, DIRECCION) VALUES (186, 'George', 'Lucas', 'Junior', 'lucasfilm@hotmail.com', 'California');

INSERT INTO USUARIO (FK_PERSON_ID) VALUES (1);

INSERT INTO USUARIO (FK_PERSON_ID) VALUES (10);
INSERT INTO USUARIO (FK_PERSON_ID) VALUES (15);

INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('ANIMALIA',0);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Chordata',1);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Mammalia',2);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Rodentia',3);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Caviidae',4);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Hydrochoerus ',5);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Hydrochoerus hydrochaeris',6);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Capibara',7);


INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('ANIMALIA',0);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Chordata',1);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Mammalia',2);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Rodentia',3);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Caviidae',4);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Hydrochoerus ',5);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Hydrochoerus hydrochaeris',6);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Capibara',7);


INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('ANIMALIA',0);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Chordata',1);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Aves',2);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Cuculiformes',3);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Cuculidae',4);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Cuculus ',5);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Cuculus canorus',6);
INSERT INTO JOSHUA.Taxonomia(Name, id_mitata) VALUES('Cuco común',7);



-- chile: 35, USA: 186, Italia: 81, Rusia: 142, Mexico: 109
select * from COUNTRY;
select * from USE_LICENSE;
select * from person;
select * from USUARIO;

SELECT 1 FROM PERSON p
                    JOIN USUARIO u ON p.id_person = u.fk_person_id
                    WHERE p.first_name = 'Alejandro'
                    AND p.last_name1 = 'Quesad'
                AND p.last_name2 = 'Calderón'
                    AND p.email = 'alejosequesada@gmail.com';