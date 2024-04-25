CREATE TABLE Kingdom(id_kingdom NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, kingdom_name VARCHAR2(100));

CREATE TABLE Phylum(id_phylum NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY , phylum_name VARCHAR2(100),
    kingdom_id NUMBER,
    FOREIGN KEY(kingdom_id) REFERENCES Kingdom
);

CREATE TABLE GPS_Coordinates(id_coordinates NUMBER PRIMARY KEY, latidude VARCHAR2(50), longitude VARCHAR2(50));


CREATE TABLE Country(id_country NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                     country_name VARCHAR2(150));

CREATE TABLE Use_License(id_license NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         license_type VARCHAR2(150));

CREATE TABLE PERSON(id_person NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                    first_name VARCHAR2(150),
                    last_name1 VARCHAR2(150),
                    last_name2 VARCHAR2(150),
                    email VARCHAR2(150));

CREATE TABLE USUARIO(id_usuario NUMBER GENERATED ALWAYS  AS IDENTITY PRIMARY KEY,
                     fk_person_id NUMBER, FOREIGN KEY (fk_person_id) REFERENCES PERSON,
                     fk_id_country NUMBER, FOREIGN KEY (fk_id_country) REFERENCES COUNTRY,
                     direccion VARCHAR2(200));


/* TODO: arreglar tablas. Aplicar cambios del físico.
CREATE TABLE Identification(id_identification NUMBER GENERATED AS IDENTITY PRIMARY KEY, fk_id_observation NUMBER, FOREIGN KEY (fk_id_observation) REFERENCES Observation(fk_id_observation),
                            fk_user_identifier NUMBER, FOREIGN KEY fk_id_user REFRENCES User(id_user),
                            fk_id_taxon NUMBER, FOREIGN KEY  fk_id_taxon REFERENCES Taxon(id_taxon), date ???);

CREATE TABLE Observation(observation_id NUMBER PRIMARY KEY, fk_id_taxon NUMBER, FOREIGN KEY (fk_id_taxon) REFERENCES Taxon(id_taxon),
                         fk_id_observer NUMBER, FOREIGN KEY fk_id_observer REFERENCES ???, fk_id_coordinates NUMBER, FOREIGN KEY (fk_id_coordinates) REFERENCES GPS_COORDINATES(id_coordinates),
                         DATE ???, comment VARCHAR2(1000));

CREATE TABLE Image(id_image NUMBER PRIMARY KEY, fk_photographer, FOREIGN KEY (fk_photographer) REFERNCES ???,
                   fk_present_taxon ????, FOREIGN KEY(fk_present_taxon)  REFERENCES Taxon(??), DATE ???, fk_id_coordinates NUMBER, FOREIGN KEY  (fk_id_coordinates) REFERENCES GPS_COORDINATES(id_coordinates)
    ,fk_license_id NUMBER, FOREIGN KEY (fk_license_id) REFERENCES Use_license(id_license), IMAGE_URL VARCHAR2(1000));
*/
select * from COUNTRY;
select * from USE_LICENSE;