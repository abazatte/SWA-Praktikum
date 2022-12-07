INSERT INTO fruitEntity(id, name) VALUES (nextval('hibernate_sequence'), 'Cherry');
INSERT INTO fruitEntity(id, name) VALUES (nextval('hibernate_sequence'), 'Apple');
INSERT INTO fruitEntity(id, name) VALUES (nextval('hibernate_sequence'), 'Banana');

-- force using the same if for entity and repository to facilitate testing
INSERT INTO fruit(id, name) VALUES (1, 'Cherry');
INSERT INTO fruit(id, name) VALUES (2, 'Apple');
INSERT INTO fruit(id, name) VALUES (3, 'Banana');

insert into auftraege(id, beschreibung, eingangsDatum, SchiffURL) values (0,'bsp beschreibung',null,null);
insert into Schiff(id, name, hatAuftrag) values (0,'Kurt kutter',false);
insert into Schiff(id, name, hatAuftrag) values (1,'legiter kahn',false);
insert into Schiff(id, name, hatAuftrag) values (2,'lebendiger bort',false);
insert into Schiff(id, name, hatAuftrag) values (3,'super Schiff',false);
insert into Schiff(id, name, hatAuftrag) values (4,'FS MeerjungTim',false);
