INSERT INTO menu_entries(name, parent, type, file_id) VALUES ('#ROOT', 0, 'DIR', null);

INSERT INTO roles(code, name) VALUES ('USER', 'User');
INSERT INTO roles(code, name) VALUES ('ADMIN', 'Admin');

INSERT INTO users(active, password, username) VALUES (true, '$2a$10$R2pbIeJoiWJS61FfOuk7te9mKJi5YHYZk1Ln3uHGDKcWx.BV7SbUO', 'test');
INSERT INTO users(active, password, username) VALUES (true, '$2a$10$R2pbIeJoiWJS61FfOuk7te9mKJi5YHYZk1Ln3uHGDKcWx.BV7SbUO', 'test_user');

INSERT INTO user_roles(user_id, role_id) VALUES (1, 1), (1, 2);
INSERT INTO user_roles(user_id, role_id) VALUES (2, 1);