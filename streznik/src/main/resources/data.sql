-- vloge, testni uporabnik
INSERT INTO vloga(naziv, sifra) VALUES ('Uporabnik', 'USER'), ('Moderator', 'MOD'), ('Administrator', 'ADMIN');
INSERT INTO uporabnik(email, geslo, upb_ime)
  VALUES ('miha@mail.com', '$2a$10$FJtZ0OUHnKcrgqMdmn4.xu/29IWkUfaU9mtY1w7wApKJ5D4DRC2ha', 'miha'),
    ('test@mail.com', '$2a$10$FJtZ0OUHnKcrgqMdmn4.xu/29IWkUfaU9mtY1w7wApKJ5D4DRC2ha', 'test');
INSERT INTO uporabniske_vloge(upb_id, vloga_id) VALUES (1, 1), (1, 3),(2, 1);

-- korenski nivo menija
INSERT INTO menu_item(ime, parent, tip, file_id)
  VALUES ('ROOT_DIR', 0, 'DIR', null);

-- testne datoteke
INSERT INTO datoteka(content_type, ext, ime, lokacija, uploaded, velikost)
  VALUES('text/plain', 'txt', 'file.1.1', 'file.1.1.txt', '2018-02-21 15:51:11', 0)
    ,('text/plain', 'txt', 'file.2.1', 'file.2.1.txt', '2018-02-21 15:51:11', 0)
    ,('text/plain', 'txt', 'file.2.2', 'file.2.2.txt', '2018-02-21 15:51:11', 0)
    ,('text/plain', 'txt', 'file.3.1.1', 'file.3.1.1.txt', '2018-02-21 15:51:11', 0)
    ,('text/plain', 'txt', 'file.3.1', 'file.3.1.txt', '2018-02-21 15:51:11', 0)
    ,('image/jpeg', 'jpg', 'slika', 'slika.jpg', '2018-02-23 22:30:47', 91449);

-- testni meni
INSERT INTO menu_item(ime,  parent, tip, file_id)
  VALUES ('mapa 1', 1, 'DIR', null)
    , ('mapa 2', 1, 'DIR', null)
    , ('mapa 3', 1, 'DIR', null)
    , ('mapa 3.1', 4, 'DIR', null);

INSERT INTO menu_item(ime, parent, tip, file_id)
    VALUES (null, 2, 'FILE', 1)
      , (null, 3, 'FILE', 2)
      , (null, 3, 'FILE', 3)
      , (null, 5, 'FILE', 4)
      , (null, 4, 'FILE', 5)
      , (null, 1, 'FILE', 6);

-- testna dovoljenja
INSERT INTO dovoljenje_tip(naziv, sifra)
  VALUES ('Nalaganje', 'UPLOAD')
      , ('Prenos', 'DOWNLOAD')
      , ('Urejanje', 'EDIT');

INSERT INTO skupina(naziv, sifra)
  VALUES ('Javno', 'PUBLIC')
        ,('Uporabnik_1', 'USER_1')
        ,('Uporabnik_2', 'USER_2')
        ,('Uporabniki', 'USERS');

INSERT INTO clanstvo_skupin(skupina_id, upb_id)
    VALUES (1, 1), (2, 1), (1, 2), (3, 2);

INSERT INTO dovoljenje(tip, skupina_id) VALUES
   (2, 1)
  ,(1, 2)
  ,(2, 2)
  ,(3, 2)
  ,(1, 3);

INSERT INTO item_dovoljenja(item_id, dovoljenje_id) VALUES
   (1, 1)
  ,(2, 2)
  ,(3, 1)
  ,(4, 1)
  ,(5, 1)
  ,(5, 2)
  ,(6, 2)
  ,(7, 3)
  ,(8, 3)
  ,(9, 3)
  ,(10, 1);

