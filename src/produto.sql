-- Table: public.produto

-- DROP TABLE public.produto;

CREATE TABLE public.produto
(
  nome character varying,
  qtde bigint,
  valor double precision
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.produto
  OWNER TO postgres;

  -----------------------------
CREATE SEQUENCE serialprod
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 82
	CACHE 1;
ALTER TABLE serialuser
	OWNER TO postgres;
	-------------------------
ALTER TABLE produto ADD COLUMN id bigint;
ALTER TABLE produto ALTER COLUMN id SET NOT NULL;
ALTER TABLE produto ALTER COLUMN id SET DEFAULT nextval('serialprod'::regclass);