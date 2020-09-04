-- Table: public.documents

-- DROP TABLE public.documents;

CREATE TABLE public.documents
(
    id integer NOT NULL DEFAULT nextval('documents_id_seq'::regclass),
    filename character varying(255) COLLATE pg_catalog."default",
    file bytea,
    CONSTRAINT documents_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.documents
    OWNER to postgres;