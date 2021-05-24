create database QUANLYNHATRE
go
use QUANLYNHATRE
go
create function func_nextmadh(@lastmadh varchar(20), @prefix varchar(3), @size int)
returns varchar(20)
as
begin
if(@lastmadh='')
	set @lastmadh= @prefix + REPLICATE(0,@size - len(@prefix))
	declare @num_nextmadh int , @nextmadh varchar(20) 
	set @lastmadh= LTRIM(RTRIM(@lastmadh))
	set @num_nextmadh= REPLACE(@lastmadh, @prefix, '') +1
	set @size= @size - LEN(@prefix)
	set @nextmadh = @prefix + REPLICATE(0,@size - LEN(@prefix))
	set @nextmadh= @prefix + RIGHT(REPLICATE(0,@size) + CONVERT(varchar(max),@num_nextmadh),@size)
	return @nextmadh
end
go
create table ChucVu
(
	maChucVu varchar(20) not null constraint pk_ChucVu primary key(maChucVu),
	tenChucVu nvarchar(50)
)
go
create trigger tr_nextmachucvu on chucvu
for insert
as
begin
declare @lastmadh varchar(20)
set @lastmadh= (select top 1 maChucVu from chucvu order by maChucVu desc) 
update chucvu set maChucVu = dbo.func_nextmadh(@lastmadh,'CV_',7) where maChucVu=''
end
go
insert into ChucVu values('',N'Giáo viên')
go
create table BacLuong
(
	maBacLuong varchar(20) not null constraint pk_BacLuong primary key(maBacLuong),
	heSoBac int not null,
	mucLuongCanBan money
)
go
create trigger tr_nextmabacluong on bacluong
for insert
as
begin
declare @lastmadh varchar(20)
set @lastmadh= (select top 1 maBacLuong from bacluong order by maBacLuong desc) 
update bacluong set maBacLuong = dbo.func_nextmadh(@lastmadh,'BL_',7) where maBacLuong=''
end
insert into bacluong values('',3,30000000)
go
create table NhanVien
(
	maNhanVien varchar(20) not null constraint pk_NhanVien primary key(maNhanVien),
	tenNhanVien nvarchar(50) not null,
	ngaySinh date not null,
	gioiTinh bit not null,
	cmnd varchar(11) not null unique,
	ngayVaoLam date not null,
	diaChi nvarchar(250) not null,
	soDienThoai varchar(10) unique,
	trangThai bit not null,
	ghiChu nvarchar(100) null,
	maBacLuong varchar(20) not null,
	maChucVu varchar(20) not null
)
go


alter table NhanVien
	add constraint fk_NhanVien_maBacLuong
		foreign key(maBacLuong)
		references BacLuong(maBacLuong)
		on delete cascade
		on update cascade
go
alter table NhanVien
	add constraint fk_NhanVien_maChucVu
		foreign key(maChucVu)
		references ChucVu(maChucVu)
		on delete cascade
		on update cascade
go
create trigger tr_nextmanhanvien on nhanvien
for insert
as
begin
declare @lastmadh varchar(20)
set @lastmadh= (select top 1 maNhanVien from nhanvien order by maNhanVien desc) 
update nhanvien set maNhanVien = dbo.func_nextmadh(@lastmadh,'NV_',7) where maNhanVien=''
end
go
create table GiaoVien
(
	maGiaoVien varchar(20) not null constraint pk_GiaoVien primary key(maGiaoVien), 
	tenGiaoVien nvarchar(50) not null,
	ngaySinh date not null,
	cmnd varchar(15) not null unique,
	ngayVaoLam date not null,
	tranThai bit not null,
	diaChi nvarchar(250) not null,
	soDienThoai varchar(11) not null unique,
	ghiChu nvarchar(100) null, 
	maBacLuong varchar(20) not null
)
go
alter table GiaoVien
	add constraint fk_GiaoVien_maBacLuong
		foreign key(maBacLuong)
		references BacLuong(maBacLuong)
		on delete cascade
		on update cascade
go
create trigger tr_nextmagiaovien on giaovien
for insert
as
begin
declare @lastmadh varchar(20)
set @lastmadh= (select top 1 maGiaoVien from giaovien order by maGiaoVien desc) 
update giaovien set maGiaoVien = dbo.func_nextmadh(@lastmadh,'GV_',7) where maGiaoVien=''
end
--insert into giaovien values('',N'Hiếu','10-11-2001','191973288','10/11/2001',1,'huế','0202020',N'khjh','BL_0001')
go
create table NamHoc
(
	maNamHoc varchar(20) not null constraint pk_NamHoc primary key(maNamHoc),
	tenNamHoc nvarchar(20) not null	
)
go
create trigger tr_nextmanamhoc on Namhoc
for insert
as
begin
declare @lastmadh varchar(20)
set @lastmadh= (select top 1 maNamHoc from Namhoc order by maNamHoc desc) 
update Namhoc set maNamHoc = dbo.func_nextmadh(@lastmadh,'NH_',7) where maNamHoc=''
end
go
create table Lop
(
	maLop varchar(20) not null constraint pk_Lop primary key(maLop),
	tenLop nvarchar(20) not null,
	siSo int not null
)
go
create trigger tr_nextmalop on Lop
for insert
as
begin
declare @lastmadh varchar(20)
set @lastmadh= (select top 1 maLop from Lop order by maLop desc) 
update Lop set maLop = dbo.func_nextmadh(@lastmadh,'LOP_',7) where maLop=''
end
go
create table PhuHuynh
(
	maPhuHuynh varchar(20) not null constraint pk_PhuHuynh primary key (maPhuHuynh),
	tenCha nvarchar(50) null,
	tenMe nvarchar(50) null,
	ngheNghiepCha nvarchar(50) null,
	ngheNghiepMe nvarchar(50) null,
	soDienThoaiLienHe varchar(10) not null unique,
	diaChi nvarchar(50) not null
)
go

create trigger tr_nextmaphuhuynh on phuhuynh
for insert
as
begin
declare @lastmadh varchar(20)
set @lastmadh= (select top 1 maPhuHuynh from phuhuynh order by maPhuHuynh desc) 
update phuhuynh set maPhuHuynh = dbo.func_nextmadh(@lastmadh,'PH_',7) where maPhuHuynh=''
end
go
create table Tre
(
	maTre varchar(20) not null constraint pk_Tre primary key (maTre),
	hoTen nvarchar(50) not null,
	ngaySinh date not null,
	gioiTinh bit not null,
	maPhuHuynh varchar(20) not null,
)
go
alter table Tre
	add constraint fk_Tre_maPhuHuynh
		foreign key(maPhuHuynh)
		references PhuHuynh(maPhuHuynh)
		on delete cascade
		on update cascade
go
create trigger tr_nextmatre on tre
for insert
as
begin
declare @lastmadh varchar(20)
set @lastmadh= (select top 1 matre from tre order by matre desc) 
update tre set matre = dbo.func_nextmadh(@lastmadh,'TRE_',7) where matre=''
end
go
create table TreMuaVeAn
(
	maSoVe varchar(20) not null constraint pk_TreMuaVeAn primary key (maSoVe),
	ngayMua date not null,
	soVeMua int not null,
	donGia money not null,
	maTre varchar(20) not null,
	maNhanVien varchar(20) not null
)
go
alter table TreMuaVeAn
	add constraint fk_TreMuaVeAn_maTre
		foreign key(maTre)
		references Tre(maTre)
		on delete cascade
		on update cascade
go
alter table TreMuaVeAn
	add constraint fk_TreMuaVeAn_maNhanVien
		foreign key(maNhanVien)
		references NhanVien(maNhanVien)
		on delete cascade
		on update cascade
go
create trigger tr_nextmavean on TreMuaVeAn
for insert
as
begin
declare @lastmadh varchar(20)
set @lastmadh= (select top 1 maSoVe from TreMuaVeAn order by maSoVe desc) 
update TreMuaVeAn set maSoVe = dbo.func_nextmadh(@lastmadh,'VE_',7) where maSoVe=''
end
go
create table TreDongHocPhi
(
	soBienLaiThuTien varchar(20) not null constraint pk_TreDongHocPhi primary key (soBienLaiThuTien),
	ngayThu date not null,
	tienHocPhi money not null,
	tienDaDong money not null,
	tinhTrang nvarchar(50) not null,
	maTre varchar(20) not null,
	maNhanVien varchar(20) not null,
)
go
alter table TreDongHocPhi
	add constraint fk_TreDongHocPhi_maTre
		foreign key(maTre)
		references Tre(maTre)
		on delete cascade
		on update cascade
go
alter table TreDongHocPhi
	add constraint fk_TreDongHocPhi_maNhanVien
		foreign key(maNhanVien)
		references NhanVien(maNhanVien)
		on delete cascade
		on update cascade
go
create trigger tr_nextmatrehocphi on tredonghocphi
for insert
as
begin
declare @lastmadh varchar(20)
set @lastmadh= (select top 1 soBienLaiThuTien from tredonghocphi order by soBienLaiThuTien desc) 
update tredonghocphi set soBienLaiThuTien = dbo.func_nextmadh(@lastmadh,'BL_',7) where soBienLaiThuTien=''
end
go
create table TreHocLop
(
	maNamHoc varchar(20) not null,
	maLop varchar(20) not null,
	maTre varchar(20) not null,
)
go
alter table TreHocLop
	add constraint fk_TreHocLop_maLop
		foreign key(maLop)
		references Lop(maLop)
		on delete cascade
		on update cascade
go
alter table TreHocLop
	add constraint fk_TreHocLop_maTre
		foreign key(maTre)
		references Tre(maTre)
		on delete cascade
		on update cascade
go
alter table TreHocLop
	add constraint fk_TreHocLop_maNamHoc
		foreign key(maNamHoc)
		references NamHoc(maNamHoc)
		on delete cascade
		on update cascade
go


create table GiaoVienPhuTrachLop
(
	maNamHoc varchar(20) not null,
	maGiaoVien varchar(20) not null,
	maLop varchar(20) not null,
)
go
alter table GiaoVienPhuTrachLop
	add constraint fk_GiaoVienPhuTrachLop_maLop
		foreign key(maLop)
		references Lop(maLop)
		on delete cascade
		on update cascade
go
alter table GiaoVienPhuTrachLop
	add constraint fk_GiaoVienPhuTrachLop_maGiaoVien
		foreign key(maGiaoVien)
		references GiaoVien(maGiaoVien)
		on delete cascade
		on update cascade
go
alter table GiaoVienPhuTrachLop
	add constraint fk_GiaoVienPhuTrachLop_maNamHoc
		foreign key(maNamHoc)
		references NamHoc(maNamHoc)
		on delete cascade
		on update cascade
go
create table QuanLyBuaAn
(
	id_NgayNhap int identity(1,1) not null constraint pk_QuanLyBuaAn primary key (id_NgayNhap),
	ngayNhap date not null,
	ngayBaoAn date not null,
	soXuatAn int not null,
	thucDonSang nvarchar(250) not null,
	thucDonTrua nvarchar(250) not null,
	thucDonChieu nvarchar(250) not null,
	tongChiPhi money,
	maNhanVien varchar(20) not null ,
	maGiaoVien varchar(20) not null,
)
go
alter table QuanLyBuaAn
	add constraint fk_QuanLyBuaAn_maGiaoVien
		foreign key(maGiaoVien)
		references GiaoVien(maGiaoVien)
		on delete cascade
		on update cascade
go
alter table QuanLyBuaAn
	add constraint fk_QuanLyBuaAn_maNhanVien
		foreign key(maNhanVien)
		references NhanVien(maNhanVien)
go

