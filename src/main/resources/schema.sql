DROP TABLE IF EXISTS bvmessages;

CREATE TABLE bvmessages (
    id          varchar(40) NOT NULL,
    message     varchar(40) NOT NULL,
    created		timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	modified	timestamp
);