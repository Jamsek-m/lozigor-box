INSERT INTO menu_entries(name, parent, type, file_id) VALUES ('#ROOT', 0, 'DIR', null);

INSERT INTO roles(code, name) VALUES ('USER', 'User');
INSERT INTO roles(code, name) VALUES ('ADMIN', 'Admin');

INSERT INTO users(active, password, username) VALUES (true, '$2a$10$R2pbIeJoiWJS61FfOuk7te9mKJi5YHYZk1Ln3uHGDKcWx.BV7SbUO', 'test');
INSERT INTO users(active, password, username) VALUES (true, '$2a$10$R2pbIeJoiWJS61FfOuk7te9mKJi5YHYZk1Ln3uHGDKcWx.BV7SbUO', 'test_user');

INSERT INTO user_roles(user_id, role_id) VALUES (1, 1), (1, 2);
INSERT INTO user_roles(user_id, role_id) VALUES (2, 1);

INSERT INTO menu_entries(name, parent, type, file_id) VALUES ('Mapa 1', 1, 'DIR', null);
INSERT INTO menu_entries(name, parent, type, file_id) VALUES ('Mapa 2', 1, 'DIR', null);
INSERT INTO menu_entries(name, parent, type, file_id) VALUES ('Mapa 3', 1, 'DIR', null);
INSERT INTO menu_entries(name, parent, type, file_id) VALUES ('Mapa 3.1', 5, 'DIR', null);
INSERT INTO menu_entries(name, parent, type, file_id) VALUES ('Mapa 3.2', 5, 'DIR', null);
INSERT INTO menu_entries(name, parent, type, file_id) VALUES ('Mapa 3.2.1', 6, 'DIR', null);

INSERT INTO files(ext, filename, mime_type, size, updated_at, uploaded_at) VALUES ('.png', 'slika.png', 'application/octet-stream', 40000000, NOW(), NOW());
INSERT INTO files(ext, filename, mime_type, size, updated_at, uploaded_at) VALUES ('.png', 'slika.png', 'application/octet-stream', 4000, NOW(), NOW());
INSERT INTO files(ext, filename, mime_type, size, updated_at, uploaded_at) VALUES ('.png', 'slika.png', 'application/octet-stream', 4000, NOW(), NOW());
INSERT INTO files(ext, filename, mime_type, size, updated_at, uploaded_at) VALUES ('.png', 'slika.png', 'application/octet-stream', 4000, NOW(), NOW());
INSERT INTO files(ext, filename, mime_type, size, updated_at, uploaded_at) VALUES ('.png', 'slika.png', 'application/octet-stream', 4000, NOW(), NOW());

INSERT INTO menu_entries(name, parent, type, file_id) VALUES ('File 1', 1, 'FILE', 1);
INSERT INTO menu_entries(name, parent, type, file_id) VALUES ('File 2', 1, 'FILE', 2);
INSERT INTO menu_entries(name, parent, type, file_id) VALUES ('File 3', 3, 'FILE', 3);
INSERT INTO menu_entries(name, parent, type, file_id) VALUES ('File 4', 4, 'FILE', 4);
INSERT INTO menu_entries(name, parent, type, file_id) VALUES ('File 5', 7, 'FILE', 5);
