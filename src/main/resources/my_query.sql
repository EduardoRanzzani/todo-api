create table todo(
         id bigint primary key auto_increment,
         description varchar(250),
         done Bool default false,
         register_date datetime,
         done_date datetime
);

