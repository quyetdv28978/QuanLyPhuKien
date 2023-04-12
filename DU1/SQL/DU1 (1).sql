CREATE DATABASE DU1_NHOM1
USE DU1_NHOM1
drop database DU1_NHOM1

CREATE TABLE NHANVIEN(
idNhanVien						UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
maNhanVien					VARCHAR				(20) UNIQUE,
taiKhoan							VARCHAR				(50),
matKhau							VARCHAR				(50),
tenNhanVien					NVARCHAR			(30) DEFAULT NULL,
cmnd									VARCHAR				(12)DEFAULT NULL,
gioiTinh							NVARCHAR			(10) DEFAULT NULL,
ngaySinh							DATE						NULL,
diaChi								NVARCHAR			(100) DEFAULT NULL,
sdt										VARCHAR				(30) DEFAULT NULL,
email									VARCHAR				(50) DEFAULT NULL,
anh										NVARCHAR			(50) NULL,
ngayTao							DATE,
idChucVu							UNIQUEIDENTIFIER FOREIGN KEY REFERENCES CHUCVU(idChucVu),
trangThai							INT,
)


CREATE TABLE CHUCVU(
idChucVu							UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
maChucVu						VARCHAR(20) UNIQUE,
tenChucVu						NVARCHAR(50) DEFAULT NULL,
ngayTao							DATE,
trangThai							INT
)

CREATE TABLE KHACHHANG(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
ma VARCHAR(20) UNIQUE,
ten NVARCHAR(30),
ngaySinh DATE DEFAULT NULL,
sdt VARCHAR(30) DEFAULT NULL,
diaChi NVARCHAR(100) DEFAULT NULL,
ngayTao date,
gioitinh nvarchar(50),
trangThai int,
)



CREATE TABLE GIOHANG(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
ma VARCHAR(20) UNIQUE,
ngayTao DATE  NULL,
tenKH NVARCHAR(50) DEFAULT NULL,
tinhTrang INT DEFAULT 0,
idkh uniqueidentifier foreign key references khachhang(id)
)



CREATE TABLE CHITIETGIOHANG(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
soLuong int,
ngayTao date,
trangThai int,
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
ngayBD datetime, 
ngayKT datetime,
ngayTao date,
trangThai int,
giagiam float
)


CREATE TABLE CHITIETKHUYENMAI(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
ma varchar(20) UNIQUE,
trangThai int,
mota nvarchar(50),
idSP UNIQUEIDENTIFIER foreign key references sanpham(id),
idKM UNIQUEIDENTIFIER foreign key references khuyenmai(id)
)

CREATE TABLE HOADON (
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
ngayTao Datetime,
ngayTT datetime,
tinhTrang int,
hinhThucThanhToan nvarchar(50),
tongtien float,
idKH UNIQUEIDENTIFIER foreign key references khachhang(id),
idNV UNIQUEIDENTIFIER foreign key references nhanvien(idnhanvien)
)

CREATE TABLE CHITIETHOADON(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
soLuong int,
donGia float,
giaGiam float,
ngayTao date,
trangThai int,
idHD UNIQUEIDENTIFIER foreign key references hoadon(id),
idSP UNIQUEIDENTIFIER foreign key references sanpham(id)
)

Create Table DanhMuc(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
ma varchar(20) UNIQUE,
dongSP nvarchar(50),
ngayTao date,
trangThai int
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
--ma varchar(20) UNIQUE,
tenChatLieu nvarchar(50),
ngayTao date,
trangThai int
)

select * from sanpham
select * from KhachHang
drop database DU1_NHOM1
alter table CHITIETKHUYENMAI drop column ngayHetHan 
alter table CHITIETHOADON drop column khuyenMai 
select * from ChatLieu

select * from sanpham
select * from khuyenmai
select * from CHITIETKHUYENMAI
insert into chitietkhuyenmai(id, ma, idSP, idkm) values (newid(), 'quyet2', '0DF054F2-03F4-41F2-92B6-605534BC1E9D','DFC6F063-EA8B-4749-9F94-099B411D14A2')

select * from hoadon 
select  * from chitiethoadon
select * from giohang
select * from CHITIETGIOHANG

delete from chitiethoadon
delete from chitietgiohang
delete from hoadon
delete from giohang

insert into nhanvien (id, ma, ten) values (newid(), '1', 'quyet'),(newid(), '2', 'quyet2'),(newid(), '3', 'quyet3')
insert into KHACHHANG(id, ma, ten, ngaySinh, sdt, diaChi) values (NEWID(), 'ph4', N'hà', '01/12/2022', '0387765432',N'hà nội'),(NEWID(), 'ph5', N'hà là sao?', '01/12/2022', '0387765432',N'hà nội')
---insert into SANPHAM values (NEWID(),'h2',N'vòng cổ đính đá',N'trắng',N'Trung Quốc','2022/01/01',0,100,10000,20000,10,N'h',N'vàng')