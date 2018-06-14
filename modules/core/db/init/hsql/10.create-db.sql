-- begin CEUW_CUSTOMER
create table CEUW_CUSTOMER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    SALUTATION varchar(50),
    NAME varchar(255) not null,
    FIRST_NAME varchar(255) not null,
    BIRTHDAY date,
    EMAIL varchar(255) not null,
    PHONE varchar(255),
    --
    primary key (ID)
)^
-- end CEUW_CUSTOMER
-- begin CEUW_ADDRESS
create table CEUW_ADDRESS (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    STREET varchar(255) not null,
    POSTAL_CODE varchar(255) not null,
    CITY varchar(255) not null,
    COUNTRY varchar(50) not null,
    CUSTOMER_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end CEUW_ADDRESS
-- begin CEUW_ORDER
create table CEUW_ORDER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ORDER_DATE date not null,
    CUSTOMER_ID varchar(36) not null,
    DELIVERY_ADDRESS_ID varchar(36) not null,
    INVOICE_ADDRESS_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end CEUW_ORDER
-- begin CEUW_ORDER_LINE
create table CEUW_ORDER_LINE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    POSITION_ integer not null,
    PRODUCT_ID varchar(36) not null,
    AMOUNT integer not null,
    AMOUNT_UNIT varchar(50) not null,
    ORDER_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end CEUW_ORDER_LINE
-- begin CEUW_PRODUCT
create table CEUW_PRODUCT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    CODE varchar(255) not null,
    TAX integer not null,
    --
    primary key (ID)
)^
-- end CEUW_PRODUCT
-- begin CEUW_PRODUCT_PRICE
create table CEUW_PRODUCT_PRICE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PRODUCT_ID varchar(36) not null,
    VALUE_ decimal(19, 2) not null,
    UNIT varchar(50) not null,
    --
    primary key (ID)
)^
-- end CEUW_PRODUCT_PRICE
