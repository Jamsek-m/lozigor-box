DROP FUNCTION IF EXISTS `ima_pravice`;
DELIMITER $$
CREATE FUNCTION `ima_pravice`(upb INTEGER, item INTEGER, tip_dovoljenja INTEGER) RETURNS tinyint(1)
  BEGIN
    declare st_ustreznih_dovoljenj INT default 0;
    select count(*) into st_ustreznih_dovoljenj from (select d.id from dovoljenje d
      inner join skupina s
        on s.id = d.skupina_id
    where s.id in (
      select cs.skupina_id from clanstvo_skupin cs
        inner join uporabnik u
          on u.id = cs.upb_id
        inner join skupina s
          on s.id = cs.skupina_id
      where cs.upb_id = upb
    )) upb
      CROSS JOIN (select i.dovoljenje_id, d.tip from item_dovoljenja i
        inner join dovoljenje d on d.id = i.dovoljenje_id
      where i.item_id = item) item
    WHERE id = dovoljenje_id AND tip = tip_dovoljenja;
    IF st_ustreznih_dovoljenj > 0 THEN
      RETURN true;
    END IF;
    RETURN false;
  END$$
DELIMITER ;