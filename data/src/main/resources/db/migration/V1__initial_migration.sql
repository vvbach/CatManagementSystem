create table if not exists main.owner(
    owner_id bigserial not null primary key,
    owner_name varchar(50) not null,
    date_of_birth date not null
);

create table if not exists main.cat(
    cat_id bigserial not null primary key,
    cat_name varchar(50) not null,
    date_of_birth date not null,
    breed varchar(20),
    color varchar(20),
    owner_id bigint references main.owner("owner_id")
);

create table if not exists main.cat_friend(
    cat_id bigint not null references main.cat("cat_id"),
    friend_id bigint not null references main.cat("cat_id"),
    primary key (cat_id, friend_id)
)