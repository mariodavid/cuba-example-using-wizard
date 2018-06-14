alter table CEUW_CUSTOMER add column SALUTATION varchar(50) ;
alter table CEUW_CUSTOMER add column PHONE varchar(255) ;
update CEUW_CUSTOMER set NAME = '' where NAME is null ;
alter table CEUW_CUSTOMER alter column NAME set not null ;
update CEUW_CUSTOMER set FIRST_NAME = '' where FIRST_NAME is null ;
alter table CEUW_CUSTOMER alter column FIRST_NAME set not null ;
