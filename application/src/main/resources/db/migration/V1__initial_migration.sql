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