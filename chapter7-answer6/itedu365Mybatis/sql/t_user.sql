CREATE TABLE public.t_user
(
    id character varying(64) COLLATE pg_catalog."default" NOT NULL,
    username character varying(64) COLLATE pg_catalog."default",
    CONSTRAINT t_user_pkey PRIMARY KEY (id)
)

insert into t_user values('1','itedu1');
insert into t_user values('2','itedu2');
commit;