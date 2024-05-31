INSERT INTO company (name, address, created_by, updated_by)
VALUES ('Company A', '123 Street, City', 'system', 'system');
INSERT INTO company (name, address, created_by, updated_by)
VALUES ('Company B', '456 Avenue, City', 'system', 'system');

INSERT INTO client (name, email, phone, company_id, created_by, updated_by)
VALUES ('Client 1', 'client1@example.com', '123456789', 1, 'system', 'system');
INSERT INTO client (name, email, phone, company_id, created_by, updated_by)
VALUES ('Client 2', 'client2@example.com', '123456780', 1, 'system', 'system');
INSERT INTO client (name, email, phone, company_id, created_by, updated_by)
VALUES ('Client 3', 'client3@example.com', '123456788', 2, 'system', 'system');


INSERT INTO sys_user (username, password, role, created_by, updated_by)
VALUES ('user1', 'user1', 'SUPPER_USER', 'system', 'system');
INSERT INTO sys_user (username, password, role, created_by, updated_by)
VALUES ('user2', 'user2', 'MANAGER', 'system', 'system');
INSERT INTO sys_user (username, password, role, created_by, updated_by)
VALUES ('user3', 'user3', 'OPERATOR', 'system', 'system');