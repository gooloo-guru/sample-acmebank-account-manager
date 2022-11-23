CREATE TABLE account
(
    account_id     BIGINT         NOT NULL AUTO_INCREMENT,
    account_number CHAR(8)        NOT NULL,
    account_type   INT            NOT NULL,
    currency       CHAR(3)        NOT NULL,
    balance        NUMERIC(15, 2) NOT NULL DEFAULT 0.00,
    CONSTRAINT pk_account_id PRIMARY KEY (account_id)
);

CREATE UNIQUE INDEX idx_account_account_number ON account (account_number);

CREATE TABLE account_transaction
(
    account_transaction_id BIGINT                   NOT NULL AUTO_INCREMENT,
    from_account_id        BIGINT NULL,
    to_account_id          BIGINT NULL,
    transaction_number     UUID                     NOT NULL,
    transaction_type       INT                      NOT NULL,
    transaction_status     INT                      NOT NULL,
    currency               CHAR(3)                  NOT NULL,
    amount                 NUMERIC(15, 2)           NOT NULL DEFAULT 0.00,
    transaction_time       TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT pk_account_transaction_id PRIMARY KEY (account_transaction_id),
    CONSTRAINT fk_from_account_id FOREIGN KEY (from_account_id) REFERENCES account (account_id),
    CONSTRAINT fk_to_account_id FOREIGN KEY (to_account_id) REFERENCES account (account_id)
);

CREATE INDEX idx_account_transaction_from_account_id ON account_transaction (from_account_id);
CREATE INDEX idx_account_transaction_to_account_id ON account_transaction (to_account_id);

