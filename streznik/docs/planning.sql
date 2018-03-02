-- skupine z id uporabnika
select * from clanstvo_skupin cs
  inner join uporabnik u
    on u.id = cs.upb_id
  inner join skupina s
    on s.id = cs.skupina_id
where cs.upb_id = 1;

-- dovoljenja z id skupine
select s.id, d.tip, s.sifra from dovoljenje d
  inner join skupina s
    on s.id = d.skupina_id
where s.id = 2;

-- oboje skupi (izbere vsa dovoljenja ki jih imajo skupine katere clan je uporabnik z podanim id-jem
select d.id from dovoljenje d
  inner join skupina s
    on s.id = d.skupina_id
where s.id in (
  select cs.skupina_id from clanstvo_skupin cs
    inner join uporabnik u
      on u.id = cs.upb_id
    inner join skupina s
      on s.id = cs.skupina_id
  where cs.upb_id = 1
);

-- poisci vse elemente iz zahtevane mape
select * from menu_item m where m.parent = 1;

-- poisci vsa zahtevana dovoljenja od izbranega elementa
select i.dovoljenje_id, d.tip from item_dovoljenja i 
inner join dovoljenje d on d.id = i.dovoljenje_id
where i.item_id = 5;

-- vse skupaj
select count(*) as st_ustreznih_dovoljenj from (select d.id from dovoljenje d
  inner join skupina s
    on s.id = d.skupina_id
where s.id in (
  select cs.skupina_id from clanstvo_skupin cs
    inner join uporabnik u
      on u.id = cs.upb_id
    inner join skupina s
      on s.id = cs.skupina_id
  where cs.upb_id =1
)) upb
CROSS JOIN (select i.dovoljenje_id, d.tip from item_dovoljenja i 
inner join dovoljenje d on d.id = i.dovoljenje_id
where i.item_id = 5) item
WHERE id = dovoljenje_id AND tip = 1;


select ima_pravice(2, 10, 2) as ima_pravice;



