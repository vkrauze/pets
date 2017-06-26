# Pets
Simple CRUD web application using Ext JS, Spring, MyBatis, PostgreSQL and Maven.

Shows a table with information about pets: their names, species and owners.

Interface is in Russian, and the whole application is Cyrillic-ready. 
It is simple to to translate interface though - you'd only need to edit js and jsp files a little.

Works with a local PostgreSQL server, using username "postgres" and password "1234" (see or change DB properties in mybatis-config.xml).

The application needs database "pets" with table "pet" in schema "public".

You can create the table like this:
```sql
CREATE TABLE pet
(
  id SERIAL NOT NULL,
  name CHARACTER VARYING(100) NOT NULL,
  species CHARACTER VARYING(100) NOT NULL,
  owner CHARACTER VARYING(100) NOT NULL,
  CONSTRAINT pet_pkey PRIMARY KEY (id)
)
```
