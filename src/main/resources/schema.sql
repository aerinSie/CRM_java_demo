CREATE TABLE company
(
    id         INTEGER AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    address    VARCHAR(255),
    created_by VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(255),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE client
(
    id         INTEGER AUTO_INCREMENT PRIMARY KEY,
    company_id INTEGER,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255),
    phone      VARCHAR(255),
    created_by VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(255),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_company FOREIGN KEY (company_id) REFERENCES company (id)
);


CREATE TABLE sys_user
(
    id         INTEGER AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(255),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);