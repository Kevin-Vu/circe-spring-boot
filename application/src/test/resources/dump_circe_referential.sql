--
-- postgrestestQL database dump
--

-- Dumped from database version 12.5 (Ubuntu 12.5-1.pgdg18.04+1)
-- Dumped by pg_dump version 13.1 (Ubuntu 13.1-1.pgdg18.04+1)

--
-- Name: authority_sequence; Type: SEQUENCE; Schema: public; Owner: postgrestest
--
DROP SCHEMA PUBLIC CASCADE;
CREATE SCHEMA public;

do
$$
begin
  if not exists (select * from pg_user where usename = 'postgrestest') then
     CREATE USER postgrestest SUPERUSER;
  end if;
end
$$
;

CREATE SEQUENCE public.authority_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.authority_sequence OWNER TO postgrestest;

--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: postgrestest
--

CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE public.databasechangelog OWNER TO postgrestest;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: postgrestest
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO postgrestest;

--
-- Name: designation_catalog_sequence; Type: SEQUENCE; Schema: public; Owner: postgrestest
--

CREATE SEQUENCE public.designation_catalog_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.designation_catalog_sequence OWNER TO postgrestest;

--
-- Name: product_type_sequence; Type: SEQUENCE; Schema: public; Owner: postgrestest
--

CREATE SEQUENCE public.product_type_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_type_sequence OWNER TO postgrestest;

--
-- Name: r_authority; Type: TABLE; Schema: public; Owner: postgrestest
--

CREATE TABLE public.r_authority (
    ath_id integer DEFAULT nextval('public.authority_sequence'::regclass) NOT NULL,
    ath_name character varying(40),
    ath_created_by character varying(60),
    ath_created_date timestamp without time zone DEFAULT now(),
    ath_last_modified_by character varying(60),
    ath_last_modified_date timestamp without time zone DEFAULT now()
);


ALTER TABLE public.r_authority OWNER TO postgrestest;

--
-- Name: r_designation_catalog; Type: TABLE; Schema: public; Owner: postgrestest
--

CREATE TABLE public.r_designation_catalog (
    dec_id integer DEFAULT nextval('public.designation_catalog_sequence'::regclass) NOT NULL,
    dec_name character varying(60),
    dec_price_no_taxes double precision,
    dec_ptr_pdt_id integer NOT NULL,
    dec_created_by character varying(60),
    dec_created_date timestamp without time zone DEFAULT now(),
    dec_last_modified_by character varying(60),
    dec_last_modified_date timestamp without time zone DEFAULT now()
);


ALTER TABLE public.r_designation_catalog OWNER TO postgrestest;

--
-- Name: r_join_authority_right; Type: TABLE; Schema: public; Owner: postgrestest
--

CREATE TABLE public.r_join_authority_right (
    jar_ptr_auth_id integer NOT NULL,
    jar_ptr_right_id integer NOT NULL
);


ALTER TABLE public.r_join_authority_right OWNER TO postgrestest;

--
-- Name: r_product_type; Type: TABLE; Schema: public; Owner: postgrestest
--

CREATE TABLE public.r_product_type (
    pdt_id integer DEFAULT nextval('public.product_type_sequence'::regclass) NOT NULL,
    pdt_product_type character varying(40),
    pdt_amount double precision,
    pdt_expiration_date timestamp without time zone,
    pdt_created_by character varying(60),
    pdt_created_date timestamp without time zone DEFAULT now(),
    pdt_last_modified_by character varying(60),
    pdt_last_modified_date timestamp without time zone DEFAULT now()
);


ALTER TABLE public.r_product_type OWNER TO postgrestest;

--
-- Name: right_sequence; Type: SEQUENCE; Schema: public; Owner: postgrestest
--

CREATE SEQUENCE public.right_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.right_sequence OWNER TO postgrestest;

--
-- Name: r_right; Type: TABLE; Schema: public; Owner: postgrestest
--

CREATE TABLE public.r_right (
    rgt_id integer DEFAULT nextval('public.right_sequence'::regclass) NOT NULL,
    rgt_name character varying(40),
    rgt_created_by character varying(60),
    rgt_created_date timestamp without time zone DEFAULT now(),
    rgt_last_modified_by character varying(60),
    rgt_last_modified_date timestamp without time zone DEFAULT now()
);


ALTER TABLE public.r_right OWNER TO postgrestest;

--
-- Name: user_sequence; Type: SEQUENCE; Schema: public; Owner: postgrestest
--

CREATE SEQUENCE public.user_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_sequence OWNER TO postgrestest;

--
-- Name: r_user; Type: TABLE; Schema: public; Owner: postgrestest
--

CREATE TABLE public.r_user (
    usr_id integer DEFAULT nextval('public.user_sequence'::regclass) NOT NULL,
    usr_firstname character varying(60),
    usr_lastname character varying(60),
    usr_user_code character varying(60),
    usr_email character varying(60),
    usr_password character varying(60),
    usr_lang_code character varying(20),
    usr_ptr_ath_id integer NOT NULL,
    usr_pwd_expiration_date timestamp without time zone,
    usr_pwd_access_start timestamp without time zone,
    usr_pwd_access_end timestamp without time zone,
    usr_pwd_last_change_date timestamp without time zone,
    usr_created_by character varying(60),
    usr_created_date timestamp without time zone DEFAULT now(),
    usr_last_modified_by character varying(60),
    usr_last_modified_date timestamp without time zone DEFAULT now()
);


ALTER TABLE public.r_user OWNER TO postgrestest;

--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: postgrestest
--

INSERT INTO public.databasechangelog VALUES ('p001_01_add_authority_sequence', 'kvu', 'classpath:/db/changelog/referential/patches/db.referential.changelog-patch-001.xml', '2021-01-02 01:55:05.346188', 1, 'EXECUTED', '8:3af831f51eac6e0b9130851b1b7b97b3', 'createSequence sequenceName=authority_sequence', '', NULL, '3.6.3', NULL, NULL, '9548905211');
INSERT INTO public.databasechangelog VALUES ('p001_01_creation_authority', 'kvu', 'classpath:/db/changelog/referential/patches/db.referential.changelog-patch-001.xml', '2021-01-02 01:55:05.429917', 2, 'EXECUTED', '8:3b2fce6c957af24c1fd3389279b77371', 'createTable tableName=r_authority', '', NULL, '3.6.3', NULL, NULL, '9548905211');
INSERT INTO public.databasechangelog VALUES ('p001_02_add_right_sequence', 'kvu', 'classpath:/db/changelog/referential/patches/db.referential.changelog-patch-001.xml', '2021-01-02 01:55:05.472937', 3, 'EXECUTED', '8:5d3ad913de26107fc268a68abdc763cd', 'createSequence sequenceName=right_sequence', '', NULL, '3.6.3', NULL, NULL, '9548905211');
INSERT INTO public.databasechangelog VALUES ('p001_02_creation_right', 'kvu', 'classpath:/db/changelog/referential/patches/db.referential.changelog-patch-001.xml', '2021-01-02 01:55:05.513655', 4, 'EXECUTED', '8:43ae2dd730c04c737efb7a3d49ce90f7', 'createTable tableName=r_right', '', NULL, '3.6.3', NULL, NULL, '9548905211');
INSERT INTO public.databasechangelog VALUES ('p001_03_creation_join_auth_right', 'kvu', 'classpath:/db/changelog/referential/patches/db.referential.changelog-patch-001.xml', '2021-01-02 01:55:05.548873', 5, 'EXECUTED', '8:34f7c26de60c7e1cd59f45d8a7b35863', 'createTable tableName=r_join_authority_right', '', NULL, '3.6.3', NULL, NULL, '9548905211');
INSERT INTO public.databasechangelog VALUES ('p001_04_add_user_sequence', 'kvu', 'classpath:/db/changelog/referential/patches/db.referential.changelog-patch-001.xml', '2021-01-02 01:55:05.576657', 6, 'EXECUTED', '8:05dcd87c02dce418ef9b5ce216239337', 'createSequence sequenceName=user_sequence', '', NULL, '3.6.3', NULL, NULL, '9548905211');
INSERT INTO public.databasechangelog VALUES ('p001_04_creation_user', 'kvu', 'classpath:/db/changelog/referential/patches/db.referential.changelog-patch-001.xml', '2021-01-02 01:55:05.610488', 7, 'EXECUTED', '8:e858acaee9a8d6fbd39c5d22d8b38b9e', 'createTable tableName=r_user', '', NULL, '3.6.3', NULL, NULL, '9548905211');
INSERT INTO public.databasechangelog VALUES ('p001_05_add_product_type_sequence', 'kvu', 'classpath:/db/changelog/referential/patches/db.referential.changelog-patch-001.xml', '2021-01-02 01:55:05.643377', 8, 'EXECUTED', '8:207b61006679a80c503af6913046bf1a', 'createSequence sequenceName=product_type_sequence', '', NULL, '3.6.3', NULL, NULL, '9548905211');
INSERT INTO public.databasechangelog VALUES ('p001_05_creation_product_type', 'kvu', 'classpath:/db/changelog/referential/patches/db.referential.changelog-patch-001.xml', '2021-01-02 01:55:05.709654', 9, 'EXECUTED', '8:a2446737916ee459bde8ec0899a23e64', 'createTable tableName=r_product_type', '', NULL, '3.6.3', NULL, NULL, '9548905211');
INSERT INTO public.databasechangelog VALUES ('p001_05_add_designation_catalog_sequence', 'kvu', 'classpath:/db/changelog/referential/patches/db.referential.changelog-patch-001.xml', '2021-01-02 01:55:05.730397', 10, 'EXECUTED', '8:25b7f005d388208df480e8306a53cbe8', 'createSequence sequenceName=designation_catalog_sequence', '', NULL, '3.6.3', NULL, NULL, '9548905211');
INSERT INTO public.databasechangelog VALUES ('p001_06_creation_designation_catalog', 'kvu', 'classpath:/db/changelog/referential/patches/db.referential.changelog-patch-001.xml', '2021-01-02 01:55:05.756621', 11, 'EXECUTED', '8:ef9b4bc6487e1cefb29faec4fd7cd26e', 'createTable tableName=r_designation_catalog', '', NULL, '3.6.3', NULL, NULL, '9548905211');
INSERT INTO public.databasechangelog VALUES ('p001_07_insert_admin_auth', 'kvu', 'classpath:/db/changelog/referential/patches/db.referential.changelog-patch-001.xml', '2021-01-02 16:17:20.607767', 12, 'EXECUTED', '8:bfa1ef1461ae5e02ecb7aae177a1e57b', 'insert tableName=r_authority', '', NULL, '3.6.3', NULL, NULL, '9600640505');
INSERT INTO public.databasechangelog VALUES ('p001_09_join_right_auth_admin', 'kvu', 'classpath:/db/changelog/referential/patches/db.referential.changelog-patch-001.xml', '2021-01-02 16:19:44.316545', 14, 'EXECUTED', '8:04b141d77261b172ae72cd492399339c', 'sql', '', NULL, '3.6.3', NULL, NULL, '9600784220');
INSERT INTO public.databasechangelog VALUES ('p001_08_insert_right_auth', 'kvu', 'classpath:/db/changelog/referential/patches/db.referential.changelog-patch-001.xml', '2021-01-02 16:17:20.625827', 13, 'EXECUTED', '8:722f17f104f36b6c505b709cbfd6279a', 'insert tableName=r_right', '', NULL, '3.6.3', NULL, NULL, '9600640505');
INSERT INTO public.databasechangelog VALUES ('p001_10_unique_email_code', 'kvu', 'classpath:/db/changelog/referential/patches/db.referential.changelog-patch-001.xml', '2021-01-03 01:24:36.002583', 15, 'EXECUTED', '8:39afaf636233ec30cc6f4729ec9b0319', 'addUniqueConstraint constraintName=unique_email_code, tableName=r_user', '', NULL, '3.6.3', NULL, NULL, '9633475839');


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: postgrestest
--

INSERT INTO public.databasechangeloglock VALUES (1, false, NULL, NULL);


--
-- Data for Name: r_authority; Type: TABLE DATA; Schema: public; Owner: postgrestest
--

INSERT INTO public.r_authority VALUES (1, 'admin', 'kvu', '2021-01-02 16:17:20.601127', 'admin', '2021-01-02 16:17:20.601127');


--
-- Data for Name: r_designation_catalog; Type: TABLE DATA; Schema: public; Owner: postgrestest
--



--
-- Data for Name: r_join_authority_right; Type: TABLE DATA; Schema: public; Owner: postgrestest
--

INSERT INTO public.r_join_authority_right VALUES (1, 1);


--
-- Data for Name: r_product_type; Type: TABLE DATA; Schema: public; Owner: postgrestest
--



--
-- Data for Name: r_right; Type: TABLE DATA; Schema: public; Owner: postgrestest
--

INSERT INTO public.r_right VALUES (1, 'RIGHT_ADMIN', 'kvu', '2021-01-02 16:17:20.617805', 'admin', '2021-01-02 16:17:20.617805');


--
-- Data for Name: r_user; Type: TABLE DATA; Schema: public; Owner: postgrestest
--

INSERT INTO public.r_user VALUES (1, 'admin', 'admin', 'admin', 'admin@circe.live', '$2a$10$pQ6Jm4iByE/53ps8/QMgae4989C5yCbHshPLYdiqgmF301GLPmIx6', 'FR', 1, '2100-01-01 00:00:00', '2000-01-01 00:00:00', '2100-01-01 00:00:00', '2000-01-01 00:00:00', 'kvu', '2021-01-02 16:24:39.303569', 'kvu', '2021-01-02 16:24:39.303569');


--
-- Name: authority_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgrestest
--

SELECT pg_catalog.setval('public.authority_sequence', 1, true);


--
-- Name: designation_catalog_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgrestest
--

SELECT pg_catalog.setval('public.designation_catalog_sequence', 1, false);


--
-- Name: product_type_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgrestest
--

SELECT pg_catalog.setval('public.product_type_sequence', 1, false);


--
-- Name: right_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgrestest
--

SELECT pg_catalog.setval('public.right_sequence', 2, true);


--
-- Name: user_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgrestest
--

SELECT pg_catalog.setval('public.user_sequence', 2, false);


--
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- Name: r_authority r_authority_pkey; Type: CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.r_authority
    ADD CONSTRAINT r_authority_pkey PRIMARY KEY (ath_id);


--
-- Name: r_designation_catalog r_designation_catalog_pkey; Type: CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.r_designation_catalog
    ADD CONSTRAINT r_designation_catalog_pkey PRIMARY KEY (dec_id);


--
-- Name: r_product_type r_product_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.r_product_type
    ADD CONSTRAINT r_product_type_pkey PRIMARY KEY (pdt_id);


--
-- Name: r_right r_right_pkey; Type: CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.r_right
    ADD CONSTRAINT r_right_pkey PRIMARY KEY (rgt_id);


--
-- Name: r_user r_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.r_user
    ADD CONSTRAINT r_user_pkey PRIMARY KEY (usr_id);


--
-- Name: r_user unique_email_code; Type: CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.r_user
    ADD CONSTRAINT unique_email_code UNIQUE (usr_email, usr_user_code);


--
-- Name: r_join_authority_right fk_ath_id; Type: FK CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.r_join_authority_right
    ADD CONSTRAINT fk_ath_id FOREIGN KEY (jar_ptr_auth_id) REFERENCES public.r_authority(ath_id);


--
-- Name: r_user fk_ath_id; Type: FK CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.r_user
    ADD CONSTRAINT fk_ath_id FOREIGN KEY (usr_ptr_ath_id) REFERENCES public.r_authority(ath_id);


--
-- Name: r_designation_catalog fk_pdt_id; Type: FK CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.r_designation_catalog
    ADD CONSTRAINT fk_pdt_id FOREIGN KEY (dec_ptr_pdt_id) REFERENCES public.r_product_type(pdt_id);


--
-- Name: r_join_authority_right fk_rgt_id; Type: FK CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.r_join_authority_right
    ADD CONSTRAINT fk_rgt_id FOREIGN KEY (jar_ptr_right_id) REFERENCES public.r_right(rgt_id);


--
-- postgrestestQL database dump complete
--

