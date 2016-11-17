create database sbox;

use sbox;


create table appuser(
	app_user_id int not null primary key auto_increment,
    email varchar(100) not null unique,
    password varchar(100) not null,
    role_id int not null,
    first_name varchar(80) not null,
    last_name varchar(80) not null,
    phone_nr varchar(15) not null,
    created_at timestamp not null,
    account_id int not null,
    last_action timestamp not null,
    image_path varchar(80) not null,
	foreign key(role_id) references role(role_id),
	foreign key(account_id) references account(account_id)
);



create table account(
	account_id int not null primary key auto_increment,
    account_type_id int not null,
    state_id int not null,
    start_date timestamp not null,
    end_date timestamp,
    foreign key(state_id) references state(state_id),
    foreign key(account_type_id) references account_type(account_type_id)
);


create table account_type(
	account_type_id int not null primary key auto_increment,
    description varchar(50) not null
);



create table role(
	role_id int not null primary key auto_increment,
	role_name varchar(50) not null
);



create table state(
	state_id int not null primary key auto_increment,
	description varchar(50) not null
);




create table request(
	request_id int not null primary key auto_increment,
    app_user_id int not null,
    request_date timestamp not null,
    desired_date date not null,
    desired_hour time not null,
    provider_id int not null,
	provider_service_id int not null,
    total_cost double not null,
    request_state_id int not null,
    foreign key(app_user_id) references appuser(app_user_id) on delete cascade,
    foreign key(provider_id) references appuser(app_user_id),
	foreign key(provider_service_id) references provider_service(provider_service_id),
    foreign key(request_state_id) references request_state(request_state_id)
);



create table request_state(
	request_state_id int not null primary key auto_increment,
    description varchar(50) not null
);



create table address(
	address_id int not null primary key auto_increment,
    app_user_id int not null,
    description varchar(50) not null,
    county varchar(50) not null,
    city varchar(50) not null,
    foreign key(app_user_id) references appuser(app_user_id) on delete cascade
);




create table provider_category_list(
	provider_category_list_id int not null primary key auto_increment,
    provider_id int not null,
    category_id int not null,
	list_name varchar(50) not null,
    foreign key(provider_id) references appuser(app_user_id) on delete cascade,
    foreign key(category_id) references category(category_id)
);


create table provider_service(
	provider_service_id int not null primary key auto_increment,
    provider_category_list_id int not null,
    price double not null,
    service_id int not null,
    available bool,
    foreign key(provider_category_list_id) references provider_category_list(provider_category_list_id) on delete cascade,
	foreign key(service_id) references service(service_id)
);



create table category(
	category_id int not null primary key auto_increment,
    category_name varchar(80) not null,
    icon_id int not null,
    foreign key(icon_id) references icon(icon_id)
);



create table service(
	service_id int not null primary key auto_increment,
    category_id int not null,
    service_name varchar(80) not null,
    foreign key(category_id) references category(category_id) on delete cascade
);




# ICON if useFramework = true then use content else use path, 5
create table icon(
	icon_id int not null primary key auto_increment,
    content varchar(100) not null,
    path varchar(100) not null,
    use_framework int
);


