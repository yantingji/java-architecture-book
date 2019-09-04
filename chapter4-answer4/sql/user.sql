DROP TABLE public.t_user;

CREATE TABLE public.t_user
(
    id character varying(64) COLLATE pg_catalog."default" NOT NULL,
    username character varying(64) COLLATE pg_catalog."default",
    password character varying(64) COLLATE pg_catalog."default",
    block_status character(1) COLLATE pg_catalog."default",
    stop_status character(1) COLLATE pg_catalog."default",
    login_wrong_times integer,
    CONSTRAINT t_user_pkey PRIMARY KEY (id)
);

INSERT INTO public.t_user(
	id, username, password, block_status, stop_status, login_wrong_times)
	VALUES (1, 'yan', 'yan', '0', '0', '0');
commit;