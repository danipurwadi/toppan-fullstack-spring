-- Tests on insertion
INSERT INTO authors (id, name, created_at, updated_at) VALUES (3, 'test', '20120618 10:34:09 AM', '20120618 10:34:09 AM');
INSERT INTO authors (id, name, created_at, updated_at) VALUES (4, 'test', '20120618', '20120618 10:34:09 AM');
select exists(select 1 from authors where id = 3 and name = 'test');
select exists(select 1 from authors where id = 4 and name = 'test');

-- Tests on delete
DELETE FROM authors WHERE id = 3;
select not exists(select 1 from authors where id = 3 and name = 'test');

-- Tests on update
UPDATE authors SET name = 'new_test' WHERE id = 4;
select exists(select 1 from authors where name = 'new_test');

-- Test on
TRUNCATE authors CASCADE;
