CREATE SEQUENCE fonesequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE fonesequence
  OWNER TO postgres;
----------------------------------
  CREATE TABLE telefone
(
  id bigint NOT NULL DEFAULT nextval('fonesequence'::regclass),
  numero character varying(500),
  tipo character varying(500),
  usuario bigint NOT NULL,
  CONSTRAINT fone_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE telefone
  OWNER TO postgres;