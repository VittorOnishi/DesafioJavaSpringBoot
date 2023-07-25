CREATE TABLE clientes (
                          cli_id          INTEGER NOT NULL,
                          cli_nome        VARCHAR(100) NOT NULL,
                          cli_datacad     DATE NOT NULL
);

ALTER TABLE clientes ADD CONSTRAINT cliente_pk PRIMARY KEY ( cli_id );

CREATE TABLE contatos (
                          cnt_id          INTEGER NOT NULL,
                          cnt_tipo        VARCHAR(100) NOT NULL,
                          cnt_texto       VARCHAR(100) NOT NULL,
                          clientes_cli_id INTEGER NOT NULL
);

CREATE UNIQUE INDEX contato__idx ON
    contatos (
              clientes_cli_id
              ASC );

ALTER TABLE contatos ADD CONSTRAINT contato_pk PRIMARY KEY ( cnt_id );

ALTER TABLE contatos
    ADD CONSTRAINT contatos_clientes_fk FOREIGN KEY ( clientes_cli_id )
        REFERENCES clientes ( cli_id );