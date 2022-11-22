INSERT INTO account (account_number, account_type, currency, balance)
VALUES ('88888888', 0, 'HKD', 1000000);
INSERT INTO account (account_number, account_type, currency, balance)
VALUES ('12345678', 0, 'HKD', 1000000);

INSERT INTO account_transaction (from_account_id, to_account_id, transaction_type, transaction_status, currency, amount,
                                 transaction_time)
VALUES (NULL, 1, 0, 0, 'HKD', 1000000, CURRENT_TIMESTAMP());
INSERT INTO account_transaction (from_account_id, to_account_id, transaction_type, transaction_status, currency, amount,
                                 transaction_time)
VALUES (NULL, 2, 0, 0, 'HKD', 1000000, CURRENT_TIMESTAMP());
