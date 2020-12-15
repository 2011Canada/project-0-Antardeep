drop schema if exists bank cascade;
create schema bank;
set schema 'bank';


create table "user" (
	"user_id" text primary key,
	"password" int not null,
	"type" text not null
);

create table "employee" (
	"employee_id" serial primary key,
	"first_name" text,
	"last_name" text,
	"phone" text,
	"email" text,
	"address" text,
	"user_ref" text unique references "user" ("user_id")
);


create table "customer" (
	"customer_id" serial primary key,
	"first_name" text,
	"last_name" text,
	"phone" text,
	"email" text,
	"address" text,
	"user_ref" text unique references "user" ("user_id")
);

create table "accounts" (
	"account_no" serial primary key,
	"account_type" text not null,
	"account_balance" numeric(10,2) check("account_balance" >= 0),
	"account_status" text not null,
	"cus_ref" int references "customer" ("customer_id")
 );

create table "transfers" (
	"transfer_id" serial primary key,
	"transfer_amount" numeric(10,2) check("transfer_amount" > 0),
	"status" text not null, 
	"from_account" int references "accounts" ("account_no"),
	"to_account" int references "accounts" ("account_no")
);


			       
insert into "user" ("user_id", "password", "type")
  			values( 'em',  1, 'employee');
select * from "user";

select * from "employee";

select * from "customer";

delete from "user" where "user_id" = 'em';

select * from "user" where "user_id" = 'em' and "password" = '1';
select * from "employee" where "user_ref" = 'em';


insert into "accounts" ("account_type", "account_balance", "account_status", "cus_ref") values ('saving', 12.44, 'pending', 1);

select * from "accounts";

delete from "accounts" where "account_no" = 18;

update "accounts" set "account_balance" = "account_balance"-10 WHERE "account_no" = 20 when "account_balance" - 10 > -1;

select "account_balance" from "accounts" where "account_no" = 20;



select * from "transfers";

select * from "transfers" as t inner join "accounts" as a on t.to_account = a.account_no where cus_ref = 1 and t.status = 'Posted';


select * from "accounts" inner join "customer" on "cus_ref" = "customer_id" where "account_no" = 3;


delete from "transfers" where "transfer_id" = 3;





