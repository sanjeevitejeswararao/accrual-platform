CREATE TABLE deals (

                       id BIGINT NOT NULL AUTO_INCREMENT,

                       external_reference VARCHAR(64) NOT NULL,

                       principal_amount DECIMAL(19,4) NOT NULL,

                       currency VARCHAR(3) NOT NULL,

                       annual_rate DECIMAL(12,8) NOT NULL,

                       start_date DATE NOT NULL,

                       maturity_date DATE NOT NULL,

                       day_count_basis INT NOT NULL,

                       deal_status VARCHAR(20) NOT NULL,

                       version BIGINT NOT NULL DEFAULT 0,

                       created_at TIMESTAMP(6) NOT NULL,

                       updated_at TIMESTAMP(6) NOT NULL,

                       PRIMARY KEY (id),

                       CONSTRAINT uk_deal_external_reference
                           UNIQUE (external_reference),

                       INDEX idx_deal_status (deal_status),

                       INDEX idx_deal_start_date (start_date)
);