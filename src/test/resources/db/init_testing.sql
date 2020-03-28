DROP SCHEMA PUBLIC CASCADE;
CREATE SCHEMA PUBLIC;

-- SPRING SECU
CREATE TABLE authority
(
    id integer PRIMARY KEY NOT NULL,
    name text NOT NULL
);

------------- TAXE
-- Actually it is better to add a join column on the taxe table than a join table but for the purpose of this POC
-- we demonstrate how to perform a join table in JPA
CREATE TABLE product_type
(
    id integer PRIMARY KEY NOT NULL,
    name text NOT NULL
);

CREATE TABLE taxe
(
    id bigint PRIMARY KEY NOT NULL,
    amount float NOT NULL,
    expirity_date timestamp WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE product_type_taxe
(
    fk_id_product_type bigint REFERENCES product_type(id) NOT NULL,
    fk_id_taxe bigint REFERENCES taxe(id) NOT NULL,
    PRIMARY KEY(fk_id_product_type,fk_id_taxe)
);
------------- END TAXE

CREATE TABLE company
(
    id bigint PRIMARY KEY NOT NULL,
    name text NOT NULL,
    address text NOT NULL,
    city text NOT NULL,
    postal_code bigint NOT NULL
);

CREATE TABLE client
(
    id bigint PRIMARY KEY NOT NULL,
    firstname text NOT NULL,
    lastname text NOT NULL,
    client_code text NOT NULL,
    email text NOT NULL,
    password text NOT NULL,
    fk_id_authority integer REFERENCES authority(id),
    fk_id_company integer REFERENCES company(id)
);


CREATE TABLE invoice
(
    id bigint PRIMARY KEY NOT NULL,
    invoice_date timestamp WITHOUT TIME ZONE NOT NULL,
    expirity_date timestamp WITHOUT TIME ZONE NOT NULL,
    total_taxes float NOT NULL,
    total_no_taxes float NOT NULL,
    total_with_taxes float NOT NULL,
    fk_id_client integer REFERENCES client(id)
);

CREATE TABLE designation_catalog
(
    id bigint PRIMARY KEY NOT NULL,
    name text NOT NULL,
    price_no_taxes float NOT NULL,
    fk_id_taxe integer REFERENCES taxe(id)
);


CREATE TABLE designation
(
    id bigint PRIMARY KEY NOT NULL,
    name text NOT NULL,
    taxes float NOT NULL,
    unit_price_no_taxes float NOT NULL,
    quantity integer NOT NULL,
    discount float,
    total_no_taxes float NOT NULL,
    fk_id_invoice integer REFERENCES invoice(id)
);

CREATE SEQUENCE client_sequence START 1;
CREATE SEQUENCE invoice_sequence START 1;
CREATE SEQUENCE designation_catalog_sequence START 1;
CREATE SEQUENCE designation_sequence START 1;
CREATE SEQUENCE company_sequence START 1;
CREATE SEQUENCE taxe_sequence START 1;

-- INSERT DATA

INSERT INTO authority(id, name)
values
(1, 'ADMINISTRATOR'),
(2, 'MANAGER'),
(3, 'USER')
;

INSERT INTO product_type(id, name)
values
(1, 'PHYTOSANITARY'),
(2, 'COMPUTING'),
(3, 'COSMETIC'),
(4, 'METALLURGY')
;

INSERT INTO taxe(id, amount, expirity_date)
values
(1, '19.80', '2021-06-14 10:00:001'),
(2, '20.50', '2021-06-14 10:00:001'),
(3, '8.75', '2022-06-14 10:00:001'),
(4, '26.47', '2022-06-14 10:00:001')
;

ALTER sequence taxe_sequence restart WITH 5;

INSERT INTO product_type_taxe(fk_id_taxe, fk_id_product_type)
values
(1,1),
(2,2),
(3,3),
(4,4)
;

INSERT INTO company(id, name, address, city, postal_code)
values
(1, 'Société Général', '29 BD Haussmann', 'Paris', 75009),
(2, 'Dassault Systèmes', '10 rue Marcel Dassault', 'Vélizy-Villacoublay', 78140),
(3, 'Renault', '13 quai Alphonse Le Gallo', 'Sèvres', 92310)
;

ALTER sequence company_sequence restart WITH 4;

INSERT INTO client(id,firstname,lastname, client_code, email, password, fk_id_authority, fk_id_company)
values
(1, 'Adrienne', 'Delmas', 'CU-AD-SG-585', 'adrienne.delmas@societegeneral.fr', '$2a$10$vJ5alPrGWb2GakzBT/u6keNMhlCMHTbhJzms9JsJQMkrIceSaU6qq', 2, 1),
(2, 'Clémence', 'Guillet', 'CU-CG-SG-223', 'clemence.guillet@dassault.fr', '$2a$10$vJ5alPrGWb2GakzBT/u6keNMhlCMHTbhJzms9JsJQMkrIceSaU6qq', 2,2),
(3, 'Alphonse', 'Lesage', 'CU-AL-RN-640', 'alphonse.lesage@renault.fr' , '$2a$10$vJ5alPrGWb2GakzBT/u6keNMhlCMHTbhJzms9JsJQMkrIceSaU6qq', 2,3),
(4, 'Aurelie', 'Blais', 'CU-AB-SG-333', 'aurelie.blais@societegeneral.fr', '$2a$10$vJ5alPrGWb2GakzBT/u6keNMhlCMHTbhJzms9JsJQMkrIceSaU6qq', 1,1),
(5, 'Martin', 'Cabre', 'CU-MC-SG-455', 'martin.cabre@societegeneral.fr', '$2a$10$vJ5alPrGWb2GakzBT/u6keNMhlCMHTbhJzms9JsJQMkrIceSaU6qq', 3, 1),
(6, 'Xavier', 'Alves', 'CU-XA-RN-064', 'xavier.alves@renault.fr', '$2a$10$vJ5alPrGWb2GakzBT/u6keNMhlCMHTbhJzms9JsJQMkrIceSaU6qq', 1, 3)
;

ALTER sequence client_sequence restart WITH 7;

INSERT INTO invoice(id, invoice_date, expirity_date, total_taxes, total_no_taxes, total_with_taxes, fk_id_client)
values
(1, '2020-03-14 10:00:001', '2020-06-14 10:00:001', '19.80', '100.00', '119.80', 1),
(2, '2020-03-14 10:00:001', '2020-06-14 10:00:001', '19.80', '299.00', '2545.99', 1),
(3, '2020-03-14 10:00:001', '2020-06-14 10:00:001', '19.80', '125.00', '169.42', 1),
(4, '2020-03-14 10:00:001', '2020-06-14 10:00:001', '19.80', '260.00', '645.06', 2),
(5, '2020-03-14 10:00:001', '2020-06-14 10:00:001', '19.80', '50.00', '119.50', 2),
(6, '2020-03-14 10:00:001', '2020-06-14 10:00:001', '19.80', '250.00', '25.89', 3)
;

ALTER sequence invoice_sequence restart WITH 7;

INSERT INTO designation_catalog(id, name, price_no_taxes, fk_id_taxe)
values
(1, 'Designation 1', '19.80',1),
(2, 'Designation 2', '20.00',2),
(3, 'Designation 3', '14.80',3),
(4, 'Designation 4 new', '15.60',4)
;

ALTER sequence designation_catalog_sequence restart WITH 5;


INSERT INTO designation(id, name, taxes, unit_price_no_taxes, quantity, discount, total_no_taxes, fk_id_invoice)
values
(1, 'Designation 1', '19.80', '100.00', 3, '0.00', '300.00', 1),
(2, 'Designation 2', '19.80', '100.00', 1, '50.00', '50.00', 1),
(3, 'Designation 1', '19.80', '100.00', 2, '60.00', '80.00', 2),
(4, 'Designation 3', '19.80', '100.00', 2, '60.00', '80.00', 2),
(5, 'Designation 4 old', '19.80', '50.00', 2, '0.00', '100.00', 2)
;

ALTER sequence designation_sequence restart WITH 6;

