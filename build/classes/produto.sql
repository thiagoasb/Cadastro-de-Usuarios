 CREATE SEQUENCE produtosequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE produtosequence
  OWNER TO postgres;
  
------------------------------------
  CREATE TABLE produto
(
  id bigint NOT NULL DEFAULT nextval('produtosequence'::regclass),
  nome character varying(500),
  quantidade bigint,
  valor numeric(10,4),
  CONSTRAINT produto_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE produto
  OWNER TO postgres;
  
