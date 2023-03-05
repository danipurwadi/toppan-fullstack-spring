-- Tests on insertion
INSERT INTO books (id, name, created_at, updated_at) VALUES (3, 'test', '20120618 10:34:09 AM', '20120618 10:34:09 AM');
INSERT INTO books (id, name, created_at, updated_at) VALUES (4, 'test', '20120618', '20120618 10:34:09 AM');
select exists(select 1 from books where id = 3 and name = 'test');
select exists(select 1 from books where id = 4 and name = 'test');

-- Tests on delete
DELETE FROM books WHERE id = 3;
select not exists(select 1 from books where id = 3 and name = 'test');

-- Tests on update
UPDATE books SET name = 'new_test' WHERE id = 4;
select exists(select 1 from books where name = 'new_test');

-- Test on
TRUNCATE books CASCADE;
