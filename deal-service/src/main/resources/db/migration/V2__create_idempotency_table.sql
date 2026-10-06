CREATE TABLE idempotency_records (

                                     id BIGINT NOT NULL AUTO_INCREMENT,

                                     idempotency_key VARCHAR(128) NOT NULL,

                                     request_hash VARCHAR(64) NOT NULL,

                                     status VARCHAR(20) NOT NULL,

                                     deal_id BIGINT NULL,

                                     created_at TIMESTAMP(6) NOT NULL,

                                     updated_at TIMESTAMP(6) NOT NULL,

                                     PRIMARY KEY (id),

                                     CONSTRAINT uk_idempotency_key
                                         UNIQUE (idempotency_key),

                                     CONSTRAINT fk_idempotency_deal
                                         FOREIGN KEY (deal_id)
                                             REFERENCES deals(id)
);