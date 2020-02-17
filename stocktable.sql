create table mavenfxdatabase.userStock(
	stockId int not null auto_increment unique primary key,
    idUsers int,
    Gas int,
    Gold int,
    Microsoft int,
    Sony int,
    foreign key(idUsers) references users(idUsers) 
)