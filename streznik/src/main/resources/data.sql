INSERT INTO vloga(naziv, sifra) VALUES ('Uporabnik', 'USER'), ('Moderator', 'MOD'), ('Administrator', 'ADMIN');

INSERT INTO uporabnik(email, geslo, upb_ime)
  VALUES ('test@mail.com', '$2a$10$FJtZ0OUHnKcrgqMdmn4.xu/29IWkUfaU9mtY1w7wApKJ5D4DRC2ha', 'miha');

INSERT INTO uporabniske_vloge(upb_id, vloga_id) VALUES (1, 1), (1, 3);
