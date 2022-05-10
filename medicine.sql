create table Users
(
    id       int auto_increment
        primary key,
    name     varchar(50) charset utf8  null,
    email    varchar(50) charset utf8  null,
    password varchar(200) charset utf8 null,
    sex      tinyint(1) default 0      null,
    birthday date                      null,
    sdt      varchar(15) charset utf8  null,
    isAdmin  int                       null,
    status   int                       null
)
    charset = utf8mb4;

create table formDrug
(
    id   int auto_increment
        primary key,
    name varchar(50) charset utf8 not null
);

create table shop
(
    id      int auto_increment
        primary key,
    name    varchar(50) charset utf8  null,
    idChuCH int                       null,
    address varchar(100) charset utf8 null,
    status  int                       null,
    constraint shop_Users_id_fk
        foreign key (idChuCH) references Users (id)
)
    charset = utf8mb4;

create table bill
(
    id     int auto_increment
        primary key,
    idShop int null,
    idUser int null,
    status int null,
    constraint bill_Users_id_fk
        foreign key (idUser) references Users (id),
    constraint bill_shop_id_fk
        foreign key (idShop) references shop (id)
)
    charset = utf8mb4;

create table plan
(
    id        int auto_increment
        primary key,
    name      varchar(50) charset utf8  null,
    idCuaHang int                       null,
    content   varchar(200) charset utf8 null,
    status    int                       null,
    constraint plan_shop_id_fk
        foreign key (idCuaHang) references shop (id)
)
    charset = utf8mb4;

create table typeDrug
(
    id   int auto_increment
        primary key,
    name varchar(100) charset utf8 not null
);

create table drug
(
    id      int auto_increment
        primary key,
    name    varchar(50) charset utf8 null,
    price   double                   null,
    term    int                      null,
    status  int                      null,
    type_id int                      null,
    form_id int                      null,
    constraint FKkejp72b6ler1qqarkx09g9af6
        foreign key (form_id) references formDrug (id),
    constraint FKkjiptvdxfgtlck06hq938axdo
        foreign key (type_id) references typeDrug (id)
)
    charset = utf8mb4;

create table detailBill
(
    id       int auto_increment
        primary key,
    idBill   int null,
    idDrug   int null,
    quantity int null,
    constraint detailBill_bill_id_fk
        foreign key (idBill) references bill (id),
    constraint detailBill_drug_id_fk
        foreign key (idDrug) references drug (id)
)
    charset = utf8mb4;

create table detailPlan
(
    id       int auto_increment
        primary key,
    idPlan   int null,
    idDrug   int null,
    quantity int null,
    constraint detailPlan_drug_id_fk
        foreign key (idDrug) references drug (id),
    constraint detailPlan_plan_id_fk
        foreign key (idPlan) references plan (id)
)
    charset = utf8mb4;

create table warehouse
(
    id     int auto_increment
        primary key,
    idShop int null,
    status int null,
    constraint warehouse_shop_id_fk
        foreign key (idShop) references shop (id)
)
    charset = utf8mb4;

create table detailedWarehouse
(
    id          int auto_increment
        primary key,
    idDrug      int  null,
    quantity    int  null,
    DateAdded   date null,
    DateEnd     date null,
    idWarehouse int  null,
    constraint detailedWarehouse_drug_id_fk
        foreign key (idDrug) references drug (id),
    constraint detailedWarehouse_warehouse_id_fk_2
        foreign key (idWarehouse) references warehouse (id)
)
    charset = utf8mb4;


