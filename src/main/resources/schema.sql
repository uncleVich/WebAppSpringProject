create sequence public.t_person_sequence start with 1;

create cached table public.t_person(
id bigint not null,
first_name varchar(255),
last_name varchar(255)
);

alter table public.t_person add constraint public.constraint_person_pk primary key(id);