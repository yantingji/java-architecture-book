CREATE TABLE public.person
(
    id integer,
    name character varying(64) COLLATE pg_catalog."default"
)

insert into person values(1,'itedu1');
insert into person values(2,'itedu2');
commit;