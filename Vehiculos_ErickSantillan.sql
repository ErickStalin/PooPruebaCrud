create database vehiculo;
use vehiculo;
create table datosVehiculo(placa varchar(10) not null primary key,
tipo varchar(20) not null, color varchar(20) not null,  modelo varchar(20) not null);
insert into datosVehiculo(placa, tipo , color ,modelo)
values( "321345","Hibrido","Amarrillo","Tesla");
select * from datosVehiculo;

