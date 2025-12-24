-- Insert Roles
INSERT INTO roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (3, 'ROLE_MANAGER');

-- Insert Users (password is 'password123' encoded with BCrypt)
INSERT INTO users (id, username, email, password, created_at, updated_at) 
VALUES (1, 'admin', 'admin@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iwy8pL5O', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO users (id, username, email, password, created_at, updated_at) 
VALUES (2, 'user', 'user@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iwy8pL5O', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Assign Roles
INSERT INTO user_roles (user_id, role_id) VALUES (1, 2); -- admin has ROLE_ADMIN
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1); -- user has ROLE_USER

