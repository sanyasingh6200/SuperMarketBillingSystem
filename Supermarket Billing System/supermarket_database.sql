CREATE DATABASE IF NOT EXISTS supermarket;

USE supermarket;

create table customerdata(bill_no integer(10),customer_name varchar(20),time varchar(10),date1 varchar(10),amount_pay integer(10));

create table cashierdata(cashier_id integer(5),name varchar(20),password varchar(8),email_id varchar(20),phone_no integer(10));

create table addnew(product_id integer(5),product_name varchar(20),price integer(5),quantity integer(5));

create table cashierlogindata(s_no integer(5),cashier_id integer(5),name varchar(20),login_time varchar(10),logout_time varchar(10),date1 varchar(10));