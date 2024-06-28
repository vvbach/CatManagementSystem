create table if not exists main.user(
    user_id bigserial not null primary key,
    username varchar(50) not null,
    password varchar(50) not null,
    role varchar(10) not null,
    owner_id bigint not null references main.owner("owner_id")
);