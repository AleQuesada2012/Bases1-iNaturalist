
--------------- ahora sí me volví un poco loca

Create table TAXON(
    ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    taxon_name VARCHAR2(300), --- aqui almaceno por ejemplo si es del Kingdom Animalia
    TAXON_ANCESTOR_ID INT, --- PUEDE SER NULO PARA LOS KINGDOMS
    top_herarquy_name VARCHAR2(300) NOT NULL, -- aqui almaceno si es kingdom, phylum, etc
    common_species_name VARCHAR2(400)
);

------------- inserts:
---- 1: kingdom
INSERT INTO TAXON(name, top_herarquy_name) VALUES ('Animalia', 'Kingdom');
INSERT INTO TAXON(name, top_herarquy_name) VALUES ('Plantae', 'Kingdom' );
INSERT INTO TAXON(name, top_herarquy_name) VALUES ('Fungi', 'Kingdom');
INSERT INTO TAXON(name, top_herarquy_name) VALUES ('Protist', 'Kingdom');
INSERT INTO TAXON(name, top_herarquy_name) VALUES ('Archaea/Archaebacteria', 'Kingdom');
INSERT INTO TAXON(name, top_herarquy_name) VALUES ('Bacteria/Eubacteria', 'Kingdom');

select * from KINGDOM;

----- 2: phylum

------------phylum-------------------------------------------------------------------------------------------
------------plantae-phylum-----------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Anthocerotophyta', 2, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Bryophyta', 2, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Charophyta', 2, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Chlorophyta', 2, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Cycadophyta', 2, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Ginkgophyta', 2, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Glaucophyta', 2, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Gnetophyta', 2, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Lycopodiophyta (Lycophyta)', 2, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Magnoliophyta (Anthophyta)', 2, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Marchantiophyta (Hepatophyta)', 2, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Pinophyta (Coniferophyta)', 2, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Polypodiophyta (Monilophyta)', 2, 'Phylum');

------------animalia-phylum-------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Porifera', 1, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Coelenterata (Cnidaria)', 1, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Platyhelminthes', 1, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Nematoda', 1, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Annelida', 1, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Arthropoda', 1, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Mollusca', 1, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Echinodermata', 1, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Hemichordata', 1, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Chordata', 1, 'Phylum');

------------fungi-phylum-------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Ascomycota',3, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Basidiomycota',3, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Blastocladiomycota',3, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Chytridiomycota',3, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Glomeromycota',3, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Microsporidia',3, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Neocallimastigomycota',3, 'Phylum');

------------protist-phylum-------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Euglenozoa', 4, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Alveolata', 4, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Stramenopiles (Heterokonta)', 4, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Rhizaria', 4, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Amoebozoa', 4, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Chromalveolata', 4, 'Phylum');

------------archea-phylum-----------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Euryarchaeota', 5, 'Phylum');

------------bacteria-phylum---------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Firmicutes', 6, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Proteobacteria', 6, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Actinobacteria', 6, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Bacteroidetes', 6, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Cyanobacteria', 6, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Spirochetes', 6, 'Phylum');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Chlamydiae', 6, 'Phylum');

select * from taxon;


------------class----------------------------------------------------------------------------------
------------chordata-------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Aves', 29, 'Class');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Fish', 29, 'Class');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Reptilia', 29, 'Class');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Mammalia', 29, 'Class');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Amphibia', 29, 'Class');

------------Phylum-mollusca------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Monoplacophora', 26, 'Class');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Polyplacophora', 26, 'Class');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Bivalvia', 26, 'Class');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Gastropoda', 26, 'Class');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Cephalopoda', 26, 'Class');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Scaphopoda', 26, 'Class');
------------Phylum-Ascomycota----------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Dothideomycetes', 30, 'Class');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Eurotiomycetes', 30, 'Class');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Leotiomycetes', 30, 'Class');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Pezizomycetes', 30, 'Class');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Sordariomycetes', 30, 'Class');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Saccharomycetes', 30, 'Class');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Taphrinomycetes', 30, 'Class');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Laboulbeniomycetes', 30, 'Class');

select * from taxon;

------------orden-----------------------------------------------------------------------------------
------------aves------------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Passeriformes', 51, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Anseriformes', 51, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Galliformes', 51, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Columbiformes', 51, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Accipitriformes', 51, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Strigiformes', 51, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Falconiformes', 51, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Charadriiformes', 51, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Psittaciformes', 51, 'Order');

------------fish------------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Salmoniformes', 52, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Perciformes', 52, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Siluriformes', 52, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Cypriniformes', 52, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Gadiformes', 52, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Scorpaeniformes', 52, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Pleuronectiformes', 52, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Tetraodontiformes', 52, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Anguilliformes', 52, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Clupeiformes', 52, 'Order');
------------reptilia---------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Squamata', 53, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Testudines', 53, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Crocodilia', 53, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Rhynchocephalia', 53, 'Order');
-------------Mammalia--------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Rodentia', 54, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Chiroptera', 54, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Carnivora', 54, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Primates', 54, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Artiodactyla', 54, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Cetacea', 54, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Perissodactyla', 54, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Lagomorpha', 54, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Soricomorpha', 54, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Proboscidea', 54, 'Order');
------------Amphibia----------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Anura', 55, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Caudata', 55, 'Order');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Gymnophiona',55, 'Order');



select * from taxon;





--------------------------------------------------------------------------------------------------------
-------------family-------------------------------------------------------------------------------------
-------------crocordilie--------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Crocodylidae', 91, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Alligatoridae', 91, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Gavialidae', 91, 'Family');
------------carnivora------------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Canidae', 95, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Felidae', 95, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Ursidae', 95, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Mustelidae', 95, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Procyonidae', 95, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Hyaenidae', 95, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Otariidae', 95, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Phocidae', 95, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Odobenidae', 95, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Ailuridae', 95, 'Family');
------------primates-----------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Hominidae', 96, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Cercopithecidae', 96, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Callitrichidae', 96, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Hylobatidae', 96, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Lemuridae', 96, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Lorisidae', 96, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Atelidae', 96, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Tarsiidae', 96, 'Family');
------------Lagomorpha--------------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Ochotonidae ', 100, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Leporidae', 100, 'Family');
------------caudata-------------------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Ambystomatidae', 104, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Plethodontidae', 104, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Salamandridae', 104, 'Family');
------------rodentia-----------------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Muridae', 93, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Cricetidae', 93, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Sciuridae', 93, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Castoridae', 93, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Heteromyidae', 93, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Dipodidae', 93, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Erethizontidae', 93, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Geomyidae', 93, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Myocastoridae', 93, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Octodontidae', 93, 'Family');
-------------Chiroptera-------------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Pteropodidae', 94, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Vespertilionidae', 94, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Rhinolophidae', 94, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Phyllostomidae', 94, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Molossidae', 94, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Emballonuridae', 94, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Megadermatidae', 94, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Nycteridae', 94, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Miniopteridae', 94, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Rhinopomatidae', 94, 'Family');
------------Passeriformes----------------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Acanthisittidae', 70, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Aegithalidae', 70, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Alaudidae', 70, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Bombycillidae', 70, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Certhiidae', 70, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Corvidae', 70, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Estrildidae', 70, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Fringillidae', 70, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Hirundinidae', 70, 'Family');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Muscicapidae', 70, 'Family');


select * from TAXON;



------------genus--------------------------------------------------------------------------------------
--------221--Crocodylidae-family-------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Crocodylus', 106, 'Genus');
--------256--Octodontidae-family------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Aconaemys', 141, 'Genus');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Octodon', 141, 'Genus');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Octomys', 141, 'Genus');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Tympanoctomys', 141, 'Genus');
--------225--Felidae-family-------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Leopardus', 110, 'Genus');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Panthera', 110, 'Genus');
--------224--Canidae-family-------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Canis', 109, 'Genus');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Atelocynus', 109, 'Genus');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Cerdocyon', 109, 'Genus');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Chrysocyon', 109, 'Genus');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Speothos', 109, 'Genus');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Lycalopex ', 109, 'Genus');
--------274--Fringillidae-family-------------------------------------------------------------------------
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Hesperiphona', 159, 'Genus');
INSERT INTO taxon(name, TAXON_ANCESTOR_ID, top_herarquy_name) VALUES ('Viridonia sagittirostris', 159, 'Genus');

select * from taxon;



------------species--------------------------------------------------------------------------------------
--------14--Hesperiphona-genus-------------------------------------------------------------------------
INSERT INTO taxon(name, taxon_ancestor_id, common_species_name, top_herarquy_name) VALUES ('Hesperiphona vespertina', 175, 'Pepitero norteño', 'Species');
INSERT INTO taxon(name, taxon_ancestor_id, common_species_name, top_herarquy_name) VALUES ('Hesperiphona abellei', 175, 'Pepitero encapuchado', 'Species');
--------8--Canis-genus-------------------------------------------------------------------------
INSERT INTO taxon(name, taxon_ancestor_id, common_species_name, top_herarquy_name) VALUES ('Aureus', 169, 'Chacal dorado', 'Species');
INSERT INTO taxon(name, taxon_ancestor_id, common_species_name, top_herarquy_name) VALUES ('Latrans', 169, 'Coyote', 'Species');
--------9--Atelocynus-genus-------------------------------------------------------------------------
INSERT INTO taxon(name, taxon_ancestor_id, common_species_name, top_herarquy_name) VALUES ('Microtis', 170, 'Perro de monte', 'Species');
--------10--Cerdocyon-genus-------------------------------------------------------------------------
INSERT INTO taxon(name, taxon_ancestor_id, common_species_name, top_herarquy_name) VALUES ('Thous', 171, 'Zorro cangrejero', 'Species');
--------2--Aconaemys-genus-------------------------------------------------------------------------
INSERT INTO taxon(name, taxon_ancestor_id, common_species_name, top_herarquy_name) VALUES ('Fuscus', 163, 'Tunduco común', 'Species');
INSERT INTO taxon(name, taxon_ancestor_id, common_species_name, top_herarquy_name) VALUES ('Porteri', 163, 'Rata de los pinares', 'Species');
--------3--Octodon-genus-------------------------------------------------------------------------
INSERT INTO taxon(name, taxon_ancestor_id, common_species_name, top_herarquy_name) VALUES ('Bridgesi', 164, 'Degú de Bridges', 'Species');
INSERT INTO taxon(name, taxon_ancestor_id, common_species_name, top_herarquy_name) VALUES ('Degus', 164, 'Ratón cola de pincel', 'Species');


select * from taxon;

select id, TAXON_ANCESTOR_ID, level,
SYS_CONNECT_BY_PATH(taxon_name, '/') "Path"
from TAXON
connect by prior id = taxon_ancestor_id;



--------- drop tables
drop table SPECIES;
drop table genus;
drop table family;
drop table ORDEN;
drop table class;
drop table phylum;
drop table kingdom;

