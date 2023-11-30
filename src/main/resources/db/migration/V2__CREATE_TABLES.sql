CREATE TABLE IF NOT EXISTS public.tb_users
(
    id               uuid                                                NOT NULL,
    cpf              character varying(20) COLLATE pg_catalog."default",
    creation_date    timestamp(6) without time zone                      NOT NULL,
    email            character varying(50) COLLATE pg_catalog."default"  NOT NULL,
    full_name        character varying(150) COLLATE pg_catalog."default" NOT NULL,
    image_url        character varying(255) COLLATE pg_catalog."default",
    last_update_date timestamp(6) without time zone                      NOT NULL,
    password         character varying(255) COLLATE pg_catalog."default" NOT NULL,
    phone_number     character varying(20) COLLATE pg_catalog."default",
    user_status      character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_type        character varying(255) COLLATE pg_catalog."default" NOT NULL,
    username         character varying(50) COLLATE pg_catalog."default"  NOT NULL,
    CONSTRAINT tb_users_pkey PRIMARY KEY (id),
    CONSTRAINT uk_username UNIQUE (username),
    CONSTRAINT uk_email UNIQUE (email),
    CONSTRAINT tb_users_user_status_check CHECK (user_status::text = ANY
                                                 (ARRAY ['ACTIVE'::character varying, 'BLOCKED'::character varying]::text[])),
    CONSTRAINT tb_users_user_type_check CHECK (user_type::text = ANY
                                               (ARRAY ['ADMIN'::character varying, 'STUDENT'::character varying, 'INSTRUCTOR'::character varying, 'USER'::character varying]::text[]))
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tb_users
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.tb_roles
(
    role_id   uuid                                               NOT NULL,
    role_name character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tb_roles_pkey PRIMARY KEY (role_id),
    CONSTRAINT uk_name UNIQUE (role_name),
    CONSTRAINT tb_roles_role_name_check CHECK (role_name::text = ANY
                                               (ARRAY ['ROLE_STUDENT'::character varying, 'ROLE_INSTRUCTOR'::character varying, 'ROLE_ADMIN'::character varying, 'ROLE_USER'::character varying]::text[]))
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tb_roles
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.tb_users_roles
(
    user_id uuid NOT NULL,
    role_id uuid NOT NULL,
    CONSTRAINT tb_users_roles_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES public.tb_users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_role FOREIGN KEY (role_id)
        REFERENCES public.tb_roles (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tb_users_roles
    OWNER to postgres;