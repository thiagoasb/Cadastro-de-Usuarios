CREATE DATABASE cadastro
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;

CREATE SEQUENCE public.serialuser
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 107
  CACHE 1;
ALTER TABLE public.serialuser
  OWNER TO postgres;

CREATE TABLE public.usuario
(
  login character varying,
  senha character varying,
  id bigint DEFAULT nextval('serialuser'::regclass),
  nome character varying(500)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.usuario
  OWNER TO postgres;

  INSERT INTO usuario(id,login,senha)
  VALUES(1,'thiago','thiago');
  
  ALTER TABLE public.usuario ADD COLUMN nome character varying(500);
  
  ALTER TABLE public.usuario ADD COLUMN email character varying;
  
  ALTER TABLE public.usuario ADD COLUMN fone character varying;

ALTER TABLE usuario ADD COLUMN cep character varying(200);
ALTER TABLE usuario ADD COLUMN rua character varying(200);
ALTER TABLE usuario ADD COLUMN bairro character varying(200);
ALTER TABLE usuario ADD COLUMN cidade character varying(200);
ALTER TABLE usuario ADD COLUMN estado character varying(200);
ALTER TABLE usuario ADD COLUMN ibge character varying(200);

ALTER TABLE public.usuario ADD COLUMN nome character varying(500);
ALTER TABLE usuario ADD COLUMN contenttype text;

ALTER TABLE usuario ADD COLUMN contentTypeCurriculo text;

ALTER TABLE usuario ADD COLUMN curriculoBase64 text;



