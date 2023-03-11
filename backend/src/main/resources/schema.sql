CREATE TABLE IF NOT EXISTS authors
(
    id SERIAL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "updatedAt" timestamp with time zone NOT NULL,
    CONSTRAINT authors_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS books
(
    id SERIAL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "updatedAt" timestamp with time zone NOT NULL,
    CONSTRAINT books_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS people
(
    id SERIAL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "updatedAt" timestamp with time zone NOT NULL,
    country_id bigint,
    CONSTRAINT people_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS book_rents
(
    person_id bigint NOT NULL,
    book_id bigint NOT NULL,
    "createdAt" time with time zone NOT NULL,
    "updatedAt" time with time zone NOT NULL
);

CREATE TABLE IF NOT EXISTS author_books
(
    "createdAt" timestamp with time zone NOT NULL,
    "updatedAt" timestamp with time zone NOT NULL,
    author_id integer NOT NULL,
    book_id integer NOT NULL,
    CONSTRAINT author_books_pkey PRIMARY KEY (author_id, book_id),
    CONSTRAINT author_books_author_id_fkey FOREIGN KEY (author_id)
        REFERENCES public.authors (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT author_books_book_id_fkey FOREIGN KEY (book_id)
        REFERENCES public.books (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);