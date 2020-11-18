DROP INDEX IF EXISTS region_index_code;
DROP INDEX IF EXISTS region_index_code_name;
DROP INDEX IF EXISTS region_index_name;
DROP TABLE IF EXISTS regions;
DROP SEQUENCE IF EXISTS GLOBAL_SEQ;

CREATE SEQUENCE GLOBAL_SEQ  START WITH 10000 increment by 1;
create table regions
(
    id            integer   default GLOBAL_SEQ.nextval PRIMARY KEY,
    name          varchar(255) not null,
    code          varchar(20)      not null

);
create unique index region_index_code_name on regions(name,code);
create unique index region_index_name on regions(name);
create unique index region_index_code on regions(code);