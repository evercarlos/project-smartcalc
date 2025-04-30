-- Conectar a PostgreSQL
--psql -U postgres

-- Create database if it does not exist
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'dbsmartcalculator') THEN
        CREATE DATABASE dbsmartcalculator;
    END IF;
END
$$;

-- Conectarse a la base de datos recién creada
\c dbsmartcalculator

-- Crear las tablas y permisos necesarios
CREATE TABLE public.call_history
(
    id serial PRIMARY KEY,
    register_date timestamp(0) with time zone,
    endpoint character varying(100),
    parameter_json character varying(100),
    response numeric(18,2),
    error text
);

-- Conceder permisos
GRANT TEMPORARY, CONNECT ON DATABASE dbsmartcalculator TO PUBLIC;
GRANT ALL ON DATABASE dbsmartcalculator TO postgres;
