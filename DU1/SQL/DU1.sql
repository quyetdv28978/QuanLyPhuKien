CREATE DATABASE DU1_NHOM1
USE DU1_NHOM1

CREATE TABLE NHANVIEN(
id uniqueidentifier primary key default newID(),
ma VARCHAR(20) UNIQUE,
ten NVARCHAR(30) DEFAULT NULL,
gioiTinh NVARCHAR(10) DEFAULT NULL,
ngaySinh DATE DEFAULT NULL,
diaChi NVARCHAR(100) DEFAULT NULL,
sdt VARCHAR(30) DEFAULT NULL,
anh NVARCHAR(50) NULL,
iduser uniqueidentifier foreign key references user_pass(id),
idCV UNIQUEIDENTIFIER foreign key references chucvu(id),
)

CREATE TABLE user_pass(
id uniqueidentifier primary key default newID(),
tentk varchar(50),
mk varchar(50)
)

CREATE TABLE CHUCVU(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
ma VARCHAR(20) UNIQUE,
ten NVARCHAR(50) DEFAULT NULL
)

CREATE TABLE KHACHHANG(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
ma VARCHAR(20) UNIQUE,
ten NVARCHAR(30),
ngaySinh DATE DEFAULT NULL,
sdt VARCHAR(30) DEFAULT NULL,
diaChi NVARCHAR(100) DEFAULT NULL,
)

CREATE TABLE GIOHANG(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
ma VARCHAR(20) UNIQUE,
ngayTao DATE  NULL,
tenKH NVARCHAR(50) DEFAULT NULL,
tinhTrang INT DEFAULT 0,
idNV UNIQUEIDENTIFIER foreign key references NHANVIEN(id)
)

CREATE TABLE CHITIETGIOHANG(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
soLuong int,
donGia float,
idSP UNIQUEIDENTIFIER foreign key references sanpham(id),
idGH UNIQUEIDENTIFIER foreign key references giohang(id)
)

CREATE TABLE SANPHAM(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
ma varchar(20) UNIQUE,
tenSanPham nvarchar(50),
mauSac nvarchar(50),
nhaSanXuat nvarchar(50),
ngayTao date,
trangThai int,
soLuong int,
giaNhap float,
giaBan float,
trongLuong float,
QL nvarchar(50),
moTa nvarchar(50),
iddm UNIQUEIDENTIFIER foreign key references danhmuc(id),
idcl UNIQUEIDENTIFIER foreign key references chatlieu(id)
)

CREATE TABLE KHUYENMAI (
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
ma varchar(20) UNIQUE, 
tenKM NVARCHAR(50),
ngayBD date, 
ngayKT date,
ngaytao date,
giaGiam float
)

CREATE TABLE CHITIETKHUYENMAI(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
ma varchar(20) UNIQUE,
ngayHetHan date,
idSP UNIQUEIDENTIFIER foreign key references sanpham(id),
idKM UNIQUEIDENTIFIER foreign key references khuyenmai(id)
)

alter table chitietkhuyenmai drop column ngayHetHan

CREATE TABLE HOADON (
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
ngayTao Date,
ngayTT date,
tinhTrang int,
hinhThucThanhToan nvarchar(50),
idKH UNIQUEIDENTIFIER foreign key references khachhang(id),
idNV UNIQUEIDENTIFIER foreign key references nhanvien(id)
)

CREATE TABLE CHITIETHOADON(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
soLuong int,
khuyenMai nvarchar(50),
donGia float,
giaGiam float,
idHD UNIQUEIDENTIFIER foreign key references hoadon(id),
idSP UNIQUEIDENTIFIER foreign key references sanpham(id)
)

Create Table DanhMuc(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
--ma varchar(20) UNIQUE,
dongSP nvarchar(50),
)
Create Table DoiTra(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
ngayTao date,
lyDo nvarchar(50),
trangThai int,
idKH UNIQUEIDENTIFIER foreign key references khachhang(id),
idHD UNIQUEIDENTIFIER foreign key references hoadon(id)
)


Create Table ChatLieu
(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
ma varchar(20) UNIQUE,
tenChatLieu nvarchar(50),
)


select * from sanpham
select * from KhachHang
drop database DU1_NHOM1
select * from ChatLieu
insert into chucvu(ma, ten) values (1, 'Nhân viên'), (2, 'Quản lý')
insert into KHACHHANG values (NEWID(), 'ph4', N'hà', '01/12/2022', '0387765432',N'hà nội')
---insert into SANPHAM values (NEWID(),'h2',N'vòng cổ đính đá',N'trắng',N'Trung Quốc','2022/01/01',0,100,10000,20000,10,N'h',N'vàng')