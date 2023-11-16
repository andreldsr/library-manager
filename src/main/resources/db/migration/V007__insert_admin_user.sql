INSERT INTO profile (name, description, document) values ('ADMIN', 'Admin', '000');
INSERT INTO "user" (name, login, password, profile_id) values ('ADMIN', 'admin', '$2a$10$NxKWUojiBXvVovsLruuyD.yG3E7P6uBXV5hoopy4Vm8cULSZA8KZG', 1);
INSERT INTO "user_roles" (user_id, roles_id) values (1, 1);