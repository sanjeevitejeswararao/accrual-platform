CREATE TABLE deal_audit (

                            id BIGINT NOT NULL AUTO_INCREMENT,

                            deal_id BIGINT NOT NULL,

                            event_type VARCHAR(50) NOT NULL,

                            old_status VARCHAR(20),

                            new_status VARCHAR(20),

                            details VARCHAR(1000),

                            created_at TIMESTAMP(6) NOT NULL,

                            PRIMARY KEY (id),

                            INDEX idx_audit_deal_id (deal_id),

                            CONSTRAINT fk_audit_deal
                                FOREIGN KEY (deal_id)
                                    REFERENCES deals(id)
);