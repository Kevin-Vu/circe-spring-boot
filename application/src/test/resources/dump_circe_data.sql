--
-- postgrestestQL database dump
--

-- Dumped from database version 12.5 (Ubuntu 12.5-1.pgdg18.04+1)
-- Dumped by pg_dump version 13.1 (Ubuntu 13.1-1.pgdg18.04+1)

--SET statement_timeout = 0;
--SET lock_timeout = 0;
--SET idle_in_transaction_session_timeout = 0;
--SET client_encoding = 'UTF8';
--SET standard_conforming_strings = on;
--SELECT pg_catalog.set_config('search_path', '', false);
--SET check_function_bodies = false;
--SET xmloption = content;
--SET client_min_messages = warning;
--SET row_security = off;

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
--
-- Name: company_sequence; Type: SEQUENCE; Schema: public; Owner: postgrestest
--

CREATE SEQUENCE public.company_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.company_sequence OWNER TO postgrestest;

--
-- Name: customer_sequence; Type: SEQUENCE; Schema: public; Owner: postgrestest
--

CREATE SEQUENCE public.customer_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_sequence OWNER TO postgrestest;


--
-- Name: d_company; Type: TABLE; Schema: public; Owner: postgrestest
--

CREATE TABLE public.d_company (
    com_id integer DEFAULT nextval('public.company_sequence'::regclass) NOT NULL,
    com_name character varying(100),
    com_address character varying(100),
    com_city character varying(100),
    com_postal_code integer,
    com_created_by character varying(60),
    com_created_date timestamp without time zone DEFAULT now(),
    com_last_modified_by character varying(60),
    com_last_modified_date timestamp without time zone DEFAULT now()
);


ALTER TABLE public.d_company OWNER TO postgrestest;

--
-- Name: d_customer; Type: TABLE; Schema: public; Owner: postgrestest
--

CREATE TABLE public.d_customer (
    cus_id integer DEFAULT nextval('public.customer_sequence'::regclass) NOT NULL,
    cus_firstname character varying(100),
    cus_lastname character varying(100),
    cus_email character varying(100),
    cus_telephone character varying(20),
    cus_address character varying(20),
    cus_postal_code character varying(20),
    cus_city character varying(20),
    cus_ptr_com_id integer,
    cus_created_by character varying(60),
    cus_created_date timestamp without time zone DEFAULT now(),
    cus_last_modified_by character varying(60),
    cus_last_modified_date timestamp without time zone DEFAULT now()
);


ALTER TABLE public.d_customer OWNER TO postgrestest;

--
-- Name: designation_sequence; Type: SEQUENCE; Schema: public; Owner: postgrestest
--

CREATE SEQUENCE public.designation_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.designation_sequence OWNER TO postgrestest;

--
-- Name: d_designation; Type: TABLE; Schema: public; Owner: postgrestest
--

CREATE TABLE public.d_designation (
    dsg_id integer DEFAULT nextval('public.designation_sequence'::regclass) NOT NULL,
    dsg_name character varying(60),
    dsg_taxes double precision,
    dsg_unit_price_no_taxes double precision,
    dsg_quantity integer,
    dsg_discount double precision,
    dsg_total_no_taxes double precision,
    dsg_total_taxes double precision,
    dsg_ptr_ivc_id integer,
    dsg_created_by character varying(60),
    dsg_created_date timestamp without time zone DEFAULT now(),
    dsg_last_modified_by character varying(60),
    dsg_last_modified_date timestamp without time zone DEFAULT now(),
    dsg_product_type character varying(60)
);


ALTER TABLE public.d_designation OWNER TO postgrestest;

--
-- Name: invoice_sequence; Type: SEQUENCE; Schema: public; Owner: postgrestest
--

CREATE SEQUENCE public.invoice_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.invoice_sequence OWNER TO postgrestest;

--
-- Name: d_invoice; Type: TABLE; Schema: public; Owner: postgrestest
--

CREATE TABLE public.d_invoice (
    ivc_id integer DEFAULT nextval('public.invoice_sequence'::regclass) NOT NULL,
    ivc_invoice_date timestamp without time zone,
    ivc_expiration_date timestamp without time zone,
    ivc_total_taxes double precision,
    ivc_total_no_taxes double precision,
    ivc_total_with_taxes double precision,
    ivc_ptr_cst_id integer,
    ivc_created_by character varying(60),
    ivc_created_date timestamp without time zone DEFAULT now(),
    ivc_last_modified_by character varying(60),
    ivc_last_modified_date timestamp without time zone DEFAULT now()
);


ALTER TABLE public.d_invoice OWNER TO postgrestest;

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
-- Name: d_company d_company_pkey; Type: CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.d_company
    ADD CONSTRAINT d_company_pkey PRIMARY KEY (com_id);


--
-- Name: d_customer d_customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.d_customer
    ADD CONSTRAINT d_customer_pkey PRIMARY KEY (cus_id);


--
-- Name: d_designation d_designation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.d_designation
    ADD CONSTRAINT d_designation_pkey PRIMARY KEY (dsg_id);


--
-- Name: d_invoice d_invoice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.d_invoice
    ADD CONSTRAINT d_invoice_pkey PRIMARY KEY (ivc_id);


--
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- Name: d_customer fk_com_id; Type: FK CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.d_customer
    ADD CONSTRAINT fk_com_id FOREIGN KEY (cus_ptr_com_id) REFERENCES public.d_company(com_id);


--
-- Name: d_invoice fk_cus_id; Type: FK CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.d_invoice
    ADD CONSTRAINT fk_cus_id FOREIGN KEY (ivc_ptr_cst_id) REFERENCES public.d_customer(cus_id);


--
-- Name: d_designation fk_ivc_id; Type: FK CONSTRAINT; Schema: public; Owner: postgrestest
--

ALTER TABLE ONLY public.d_designation
    ADD CONSTRAINT fk_ivc_id FOREIGN KEY (dsg_ptr_ivc_id) REFERENCES public.d_invoice(ivc_id);


--
-- postgrestestQL database dump complete
--

