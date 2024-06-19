create user siatwiki@localhost identified by 'siatwiki';
create database siatwiki;
grant all privileges on siatwiki.* to siatwiki@localhost with grant option;
commit;