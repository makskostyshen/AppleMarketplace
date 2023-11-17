INSERT INTO good (id, name, price) VALUES ('good-id-1', 'iPhone 10 Max', 1000);
INSERT INTO good (id, name, price) VALUES ('good-id-2', 'iPhone 15 Max', 1200);

INSERT INTO good_stock (id, quantity, good_id) VALUES ('good-stock-id-1', 130, 'good-id-1');
INSERT INTO good_stock (id, quantity, good_id) VALUES ('good-stock-id-2', 5, 'good-id-2');

INSERT INTO user_account (id, email, password) VALUES ('client-account-id-1', 'client@gmail.com', 'pass');
INSERT INTO user_account (id, email, password) VALUES ('manager-account-id-1', 'manager@gmail.com', 'pass');

INSERT INTO user_client_profile (id, account_id) VALUES ('client-profile-id-1', 'client-account-id-1');
INSERT INTO user_client_profile (id, account_id) VALUES ('manager-profile-id-1', 'manager-account-id-1');

INSERT INTO "order" (id, bill, created_on, status, client_id)
VALUES ('order-id-1', 10000, '2023-11-17T00:00:00Z', 'UNPAID', 'client-profile-id-1');

INSERT INTO good_purchase (id, count, good_id, order_id) VALUES ('good-purchase-id-1', 4, 'good-id-1', 'order-id-1');