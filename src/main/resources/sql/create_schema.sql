-- Drop table good
DROP TABLE IF EXISTS good CASCADE;

-- Drop table good_purchase
DROP TABLE IF EXISTS good_purchase CASCADE;

-- Drop table good_stock
DROP TABLE IF EXISTS good_stock CASCADE;

-- Drop table "order"
DROP TABLE IF EXISTS "order" CASCADE;

-- Drop table user_account
DROP TABLE IF EXISTS user_account CASCADE;

-- Drop table user_client_profile
DROP TABLE IF EXISTS user_client_profile CASCADE;

-- Drop table user_manager_profile
DROP TABLE IF EXISTS user_manager_profile CASCADE;

-- Create table good
CREATE TABLE good (
                      id VARCHAR(255) NOT NULL,
                      name VARCHAR(255),
                      price DECIMAL(19,2),
                      PRIMARY KEY (id)
);

-- Create table good_purchase
CREATE TABLE good_purchase (
                               id VARCHAR(255) NOT NULL,
                               count INTEGER NOT NULL,
                               good_id VARCHAR(255) NOT NULL,
                               order_id VARCHAR(255) NOT NULL,
                               PRIMARY KEY (id)
);

-- Create table good_stock
CREATE TABLE good_stock (
                            id VARCHAR(255) NOT NULL,
                            quantity BIGINT NOT NULL,
                            good_id VARCHAR(255) NOT NULL,
                            PRIMARY KEY (id)
);

-- Create table "order"
CREATE TABLE "order" (
                         id VARCHAR(255) NOT NULL,
                         bill DECIMAL(19,2) NOT NULL,
                         created_on TIMESTAMP NOT NULL,
                         payed_on TIMESTAMP,
                         status VARCHAR(255) NOT NULL,
                         client_id VARCHAR(255) NOT NULL,
                         PRIMARY KEY (id)
);

-- Create table user_account
CREATE TABLE user_account (
                              id VARCHAR(255) NOT NULL,
                              email VARCHAR(255) UNIQUE NOT NULL,
                              password VARCHAR(255) NOT NULL,
                              PRIMARY KEY (id)
);

-- Create table user_client_profile
CREATE TABLE user_client_profile (
                                     id VARCHAR(255) NOT NULL,
                                     account_id VARCHAR(255) UNIQUE NOT NULL,
                                     PRIMARY KEY (id)
);

-- Create table user_manager_profile
CREATE TABLE user_manager_profile (
                                      id VARCHAR(255) NOT NULL,
                                      account_id VARCHAR(255) UNIQUE NOT NULL,
                                      PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS shedlock (
                                        name VARCHAR(64),
                                        lock_until TIMESTAMP(3) NULL,
                                        locked_at TIMESTAMP(3) NULL,
                                        locked_by VARCHAR(255),
                                        PRIMARY KEY (name)
);

-- Add foreign key constraint to good_purchase for good_id
ALTER TABLE good_purchase
    ADD CONSTRAINT FKbc1vbrlyy83c22n090x58o01e
        FOREIGN KEY (good_id)
            REFERENCES good;

-- Add foreign key constraint to good_purchase for order_id
ALTER TABLE good_purchase
    ADD CONSTRAINT FK8g0ta4g0f0uummfxrt4gt8f0r
        FOREIGN KEY (order_id)
            REFERENCES "order";

-- Add foreign key constraint to good_stock for good_id
ALTER TABLE good_stock
    ADD CONSTRAINT FKtcyv4ny8b02rrrblyh2p8f898
        FOREIGN KEY (good_id)
            REFERENCES good;

-- Add foreign key constraint to "order" for client_id
ALTER TABLE "order"
    ADD CONSTRAINT FKcxudgmpsv2s3dnh9whhqh1fck
        FOREIGN KEY (client_id)
            REFERENCES user_client_profile;

-- Add foreign key constraint to user_client_profile for account_id
ALTER TABLE user_client_profile
    ADD CONSTRAINT FK3qjcnv4fv3222r3bvx3qyalni
        FOREIGN KEY (account_id)
            REFERENCES user_account;

-- Add foreign key constraint to user_manager_profile for account_id
ALTER TABLE user_manager_profile
    ADD CONSTRAINT FK8evyaa70lvwl02xgqqdyqcngf
        FOREIGN KEY (account_id)
            REFERENCES user_account;