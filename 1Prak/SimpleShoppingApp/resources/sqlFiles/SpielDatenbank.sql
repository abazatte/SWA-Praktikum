create table Ware(Warennummer int primary key,Name varchar(255),Beschreibung varchar(2048),Preis decimal(5,2))
create table Produktinformation(Bezeichnung varchar(255),information blob,Warennummer int primary key,Foreign key (Warennummer) REFERENCES Ware(Warennummer))
create table warenkorb(warenkorbnr int primary key,nutzer varchar(255))
create table warenImKorb(warenkorbnr int , warennr int, Foreign key(warennr) references Ware(warennummer), Foreign key(warenkorbnr) References Warenkorb(warenkorbnr), primary key(warenkorbnr,warennr))
