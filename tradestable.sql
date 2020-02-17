create table mavenfxdatabase.trades(
	tradeId int not null auto_increment unique primary key,
    company varchar(40) not null, 
    ordertime datetime not null,
    buyOrSell varchar(10) not null,
    idUsers int not null,
    price int not null,
    totalPrice int,
    amount int not null,
    tradeStatus varchar(20) not null,
	foreign key(idUsers) references users(idUsers)
);