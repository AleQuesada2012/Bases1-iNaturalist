create table trolo(id number, name varchar2(20));
insert into ADMIN.TROLO(ID, NAME) VALUES (1, 'troleador');
select * from trolo;
commit;

SELECT* FROM Taxonomia;

CREATE TABLE Taxonomia(
                          id_taxonomy NUMBER GENERATED BY DEFAULT AS IDENTITY,
                          Name VARCHAR2(100),
                          id_ancestor NUMBER,
                          PRIMARY KEY(id_taxonomy)
);
/*
     Reino: Plantae
    Filo: Angiospermas (Magnoliophyta)
    Clase: Eudicotiledóneas (Magnoliopsida)
    Orden: Asterales
    Familia: Asteráceas (Compuestas)
    Género: Helianthus
    Especie: Helianthus annuus
 */
SELECT Name, SYS_CONNECT_BY_PATH(Name,'/') "Path" FROM TAXONOMIA
START WITH id_ancestor = 0 CONNECT BY PRIOR ID_TAXONOMY = id_ancestor;

/*
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('ANIMALIA',0);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('ARTHROPODA',1);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('INSECTA',2);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('SCORPIONES',3);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('SCORPIONIDAE',4);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('ANDROCTONUS',5);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('AUSTRALIS',6);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('ESCORPION COLA GRUESA',7);

INSERT INTO Taxonomia(Name, id_ancestor) VALUES('MOLLUSCA',1);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('GASTROPODA',9);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('HELICIDAE',10);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('HELIX',11);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('HELIX',12);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('CARACOL DE JARDIN',13);

INSERT INTO Taxonomia(Name, id_ancestor) VALUES('Plantae', 0);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('Angiospermas', 15);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('Eudicotiledóneas', 16);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('Asterales', 17);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('Helianthus', 18);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('Helianthus annuus', 19);
INSERT INTO Taxonomia(Name, id_ancestor) VALUES('Girasol', 20);
*/

