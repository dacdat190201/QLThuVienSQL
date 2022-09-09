﻿create database QL_BANSACH
go
create table KhachHang
(
	maKH char(10) not null,
	tenKH nvarchar(50),
	sdt char(11),
	email nvarchar(100),
	diaChi nvarchar(100),
	tinhTrang nvarchar(100) default N'Thành viên thường',
	constraint PK_KhachHang primary key (maKH)
)
go
create table NhanVien
(
	taiKhoan char(50),
	matKhau char(50),
	maNV char(10) not null,
	tenNV nvarchar(70),
	ngaySinh date,
	gioiTinh nvarchar(5),
	sdtNV char(11),
	emailNV nvarchar(100),
	diaChi nvarchar(100),
	anhNV Image, --Insert ảnh cho nhân viên
	constraint PK_NhanVien primary key (maNV)
)
go
create table ThongTinSach
(
	maSach char(20) not null,
	tenSach nvarchar(100),
	tacGia nvarchar(100),
	nhaXB nvarchar(100),
	namXB int,
	theLoai nvarchar(100),
	giaBan float default 0,
	constraint PK_ThongTinSach primary key (maSach)
)
go
create table TonKho
(
	maSach char(20) not null,
	soLuongTon int,
	constraint PK_TonKho primary key (maSach),
	constraint FK_TonKho_ThongTinSach foreign key (maSach) references ThongTinSach(maSach) on delete no action,
)
go
create table DonBanHang
(
	maDonBan char(10) not null,
	maNV char(10),
	maKH char(10),
	ngayBan date,
	soLuongBan int,
	tongTien int,
	constraint PK_DonBanHang primary key (maDonBan),
	constraint FK_DonBanHang_KhachHang foreign key (maKH) references KhachHang(maKH) on delete no action,
	constraint FK_DonBanHang_NhanVien foreign key (maNV) references NhanVien(maNV) on delete no action,
)
go
create table CT_DonBanHang
(
	maDonBan char(10) not null,
	maSach char(20) not null,
	soLuongBan int,
	giaBan float,
	thanhTien float,
	constraint PK_CT_DonBanHang primary key (maDonBan, maSach),
	constraint  FK_CT_DonBanHang_DonBanHang foreign key (maDonBan) references DonBanHang(maDonBan) on delete no action,
	constraint  FK_CT_DonBanHang_ThongTinSach foreign key (maSach) references ThongTinSach(maSach) on delete no action,
)
go
create table NhaCungCap
(
	maNCC char(10) not null,
	tenNCC nvarchar(100),
	sdt char(11),
	email nvarchar(100),
	diaChi nvarchar(100),
	constraint PK_NhaCungCap primary key (maNCC)
)
go
create table DonNhapHang
(
	maDonNhap char(10) not null,
	maNV char(10),
	maNCC char(10),
	ngayNhap date,
	soLuong int,
	tongTienHD float,
	constraint PK_DonNhapHang primary key (maDonNhap),
	constraint FK_DonNhapHang_NhanVien foreign key (maNV) references NhanVien(maNV) on delete no action,
	constraint FK_DonNhapHang_NhaCungCap foreign key (maNCC) references NhaCungCap(maNCC) on delete no action,
)
go
create table CT_DonNhapHang
(
	maDonNhap char(10) not null,
	maSach char(20),
	soLuongNhap int,
	giaNhap float,
	constraint PK_CT_DonNhapHang primary key (maDonNhap, maSach),
	constraint FK_CT_DonNhapHang_ThongTinSach foreign key (maSach) references ThongTinSach(maSach) on delete no action, 
)
go
--=====================================================================================================================================NHẬP LIỆU
insert into KhachHang
values
('KH001', N'Pha Tu Da', '0321456987', 'phatuda@gmail.com', N'140 Lê Trọng Tấn, Tây Thạnh', N'Thành viên Vip'),
('KH002', N'L Th Tha Bi', '0326598741', 'lththabi@gmail.com', N'142 Lê Trọng Tấn, Tây Thạnh', N'Thành viên Vip'),
('KH003', N'Pha Tha Bi', '0235687419', 'tudathabi@gmail.com', N'144 Lê Trọng Tấn, Tây Thạnh', N'Thành viên thường'),
('KH004', N'Phe tu de', '0235698741', 'phetude@gmail.com', N'140 Lê Trọng Tấn, Tây Thạnh', N'Thành viên thường'),
('KH005', N'Nguyễn Bá Cây', '0956321478', 'bacay123@gmail.com', N'140 Lê Trọng Tấn, Tây Thạnh', N'Thành viên Vip')
go
set dateformat DMY
insert into NhanVien	
values 
('tuandat', '321321', 'NV001', N'Phạm Tuấn Đạt', '16/07/2001', N'Nam', '0908946327', 'dat160701@gmail.com', N'Bình Tân, TP.HCM', null),
('dacdat', '321321', 'NV002', N'Nguyễn Đặc Đạt', '03/03/2001', N'Nữ', '0707370556', 'thabi2831@gmail.com', N'Bình Tân, TP.HCM', null),
('aily', '321321', 'NV003', N'Bùi Thị Ái Ly', '05/03/2001', N'Nữ', '0707370556', 'thabi2831@gmail.com', N'Bình Tân, TP.HCM', null)
go
select *from NhanVien
insert into ThongTinSach
values
('S010', N'Sách tự nhiên', N'Pha Tu Da', N'Nhà Xuất Bản Gia đình', 2018, N'Sách nghiên cứu', 25000),
('S011',N'Toán a2',N'Đinh Xuân Ngoc',N'Nhà Xuất Bản Giáo Dục',2018,N'Sách Giáo Khoa',23000),
('S013',N'Hạt giống tội lỗi',N'Nguyễn Văn Vỵ',N'Kim Đồng',1999,N'Khoa Học Tự Nhiên',21000),
('S014',N'Tom and Jerry',N'Trần Văn Tuấn',N'Nhà xuất bản Trẻ',1980,N'Truyện Tranh Thiếu Nhi',28000),
('S015',N'Truyện Doremon',N'Hồ Thị Anh',N'Nhà xuất bản Trẻ',1990,N'Truyện Tranh Thiếu Nhi',27000),
('S012',N'Tâm lý học ',N'Đoàn Thị Hoa',N'Nhà xuất bản lao động',2001,N'Tâm Lý',23500),
('S016',N'Ngữ Pháp Tiếng Anh',N'Nguyễn Anh Đào',N'Từ điển Bách KHoa',2008,N'Sách Giáo Khoa',24500),
('S017',N'Bắt trẻ đồng xanh',N'NGuyễn Hoàng Nam',N' Khoa học và kỹ thuật',2013,N'Giáo Khoa',20000),
('S018',N'Tội ác trừ trị',N'Trần Văn Tuấn',N'Nhà xuất bản Tư pháp',2002,N'Chính Trị-Pháp Luật',31000),
('S019', N'Hồi Xuân', N'Nguyễn Tiến Huy', N'Nhà xuất bản Trẻ', 2019, N'Sách Tiểu Thuyết', 25000)	
go
insert into TonKho
values
('S010',56),
('S011',35),
('S012',21),
('S013',70),
('S014',70),
('S015',10),
('S016',150),
('S017',150),
('S018',50)
go
set dateformat DMY
insert into DonBanHang
values
('HD001','NV001','KH001', GETDATE(), 100, 200000),
('HD002','NV001','KH002','14/04/2021', 100, 1920000),
('HD003','NV002','KH003','15/05/2021', 100, 400000)
go
insert into CT_DonBanHang
values
('HD001','S015',2,100000,200000),
('HD002','S011',1,200000,200000),
('HD002','S012',2,300000,600000),
('HD003','S013',2,560000,1120000),
('HD001','S019',1,400000,400000)
go
insert into NhaCungCap
values
('NCC01', N'Nhà cung cấp Bình Dương', '0123684273', 'binhduong@gmail.com', N'Dĩ An, Thủ Dầu 1, Bình Dương'),
('NCC02',N'Nhà cung cấp TP.HCM', '0654321987', 'tphcm@gmail.com', N'Quận 11, TPHCM'),
('NCC03',N'Nhà cung cấp Bình Định','0232391209', 'binhdinh@gmail.com', N'Hòa Hải, Bình Định'),
('NCC04',N'Nhà cung cấp Hà Nội', '0823481910', 'hanoi@gmail.com', N'Quận 8, Hà Nội'),
('NCC05',N'Nhà cung cấp Ninh Thuận', '0123569874', 'ninhthuan@gmail.com', N'Nguyễn Thái Tông,Ninh Thuận'),
('NCC06',N'Nhà cung cấp Tây Ninh ', '0234731209', 'tayninh@gmail.com', N'11 Võ Thị Sáu,Tây Ninh'),
('NCC07',N'Nhà cung cấp Đồng Nai', '0523641789', 'dongnai@gmail.com', N'1123 Hoàng Hoa Thám ,Đồng Nai')
go
set dateformat DMY
insert into DonNhapHang
values
('NH10', 'NV001', 'NCC05', '17/10/2020', 150, 69000),
('NH11', 'NV001', 'NCC06', '11/01/2021', 20, 77000),
('NH12', 'NV002', 'NCC05', '26/09/2018', 70, 54000),
('NH13', 'NV001', 'NCC04', '27/08/2019', 122, 102000),
('NH15', 'NV002', 'NCC02', '15/04/2020', 50, 56000),
('NH17', 'NV001', 'NCC05', '13/12/2021', 150,12000),
('NH16', 'NV002', 'NCC07', '01/06/2021', 50, 34000)
go
insert into CT_DonNhapHang
values
('NH10', 'S014', 150, 14000),
('NH11', 'S012', 20, 11000),
('NH12', 'S010', 70, 55000),
('NH16', 'S011', 150, 15000),
('NH15', 'S013', 50, 10000),
('NH13', 'S015', 10, 21000),
('NH17', 'S016', 50, 30000),
('NH13', 'S017', 112, 75000)

----------------------------------------------------------------------------
-----------THỦ TỤC LẤY THÔNG TIN SÁCH VÀ SỐ LƯỢNG TỒN KHO CỦA SÁCH ĐỎ
go
create proc SL_ThongTinCTSach
as
	begin
		select	ThongTinSach.maSach, tenSach, tacGia, nhaXB, namXB, theLoai, soLuongTon, giaBan
		from ThongTinsach, TonKho
		where ThongTinSach.maSach = TonKho.maSach
	end
go
exec SL_ThongTinCTSach

-----------TÌM KIẾM THÔNG TIN KHÁCH HÀNG BẰNG SỐ ĐIỆN THOẠI
go
create proc TimKiemKH_SDT @sdt char(11)
as 
	begin
		select *
		from KhachHang
		where sdt = @sdt
	end
go
exec TimKiemKH_SDT '0123596874'

-----------TÌM KIẾM THÔNG TIN NHÂN VIÊN BẰNG MÃ NHÂN VIÊN
go
create proc TimKiemnv_Manv @mnv char(10)
as 
	begin
		select *
		from NhanVien
		where maNV = @mnv
	end
go
exec TimKiemnv_Manv 'NV001'

-----------HIỂN THỊ ĐƠN NHẬP HÀNG
go
create proc show_HDNS
as
	begin
		select maDonNhap, tenNCC, maNV, ngayNhap, soLuong, tongTienHD
		from DonNhapHang, NhaCungCap
		where DonNhapHang.maNCC = NhaCungCap.maNCC
	end
go

-----------HIỂN THỊ THEO ĐIỀU KIỆN SỐ LƯỢNG GIẢM DẦN
go
create proc show_HD_Soluong_Giam
as
	begin
		select maDonNhap, tenNCC, maNV, ngayNhap, soLuong, tongTienHD
		from DonNhapHang, NhaCungCap
		where DonNhapHang.maNCC = NhaCungCap.maNCC
		order by soLuong DESC
	end
go
exec show_HD_Soluong_Giam

-----------HIỂN THỊ THEO ĐIỀU KIỆN TỔNG TIỀN GIẢM DẦN
go
create proc show_HD_TongTien_Giam
as
	begin
		select maDonNhap, tenNCC, maNV, ngayNhap, soLuong, tongTienHD
		from DonNhapHang, NhaCungCap
		where DonNhapHang.maNCC = NhaCungCap.maNCC
		order by tongTienHD DESC
	end
go
exec show_HD_TongTien_Giam

-----------HIỂN THỊ THEO ĐIỀU KIỆN TÊN NHÀ CUNG CẤP
go
create proc show_HD_TenNCC @tenNCC nvarchar(100), @maNCC char(10)
as
	begin
		select maDonNhap, tenNCC, maNV, ngayNhap, soLuong, tongTienHD
		from DonNhapHang, NhaCungCap
		where DonNhapHang.maNCC = NhaCungCap.maNCC
		and (tenNCC = @tenNCC or NhaCungCap.maNCC = @maNCC) 
	end
go
exec show_HD_TenNCC 'Nhà cung cấp Hà Nội', 'NCC05'

-----------HIỂN THỊ THEO ĐIỀU KIỆN THEO NGÀY NHẬP
go
create proc show_HD_NgayNhap @ngayNhap date
as
	begin
		set dateformat DMY
		select maDonNhap, tenNCC, maNV, ngayNhap, soLuong, tongTienHD
		from DonNhapHang, NhaCungCap
		where DonNhapHang.maNCC = NhaCungCap.maNCC
		and ngayNhap = @ngayNhap
	end
go
exec show_HD_NgayNhap '15/04/2020'

-----------HIỂN THỊ THEO ĐIỀU KIỆN THEO GIÁ NHẬP
go
create proc show_HD_GiaNhap @maDonNhap char(10)
as
	begin
		select *
		from CT_DonNhapHang
		where maDonNhap = @maDonNhap
		order by giaNhap DESC
	end
go
exec show_HD_GiaNhap 'NH13'

-----------HIỂN THỊ THEO ĐIỀU KIỆN THEO SỐ LƯỢNG NHẬP
go
create proc show_HD_SLNhap @maDonNhap char(10)
as
	begin
		select *
		from CT_DonNhapHang
		where  maDonNhap = @maDonNhap
		order by soLuongNhap DESC
	end
go
exec show_HD_SLNhap 'NH13'

-----------HIỂN THỊ THEO ĐIỀU KIỆN THEO TÊN SÁCH _ MÃ SÁCH NHẬP
go
create proc show_HD_BanSach_Ma_SDT @sdt char(11),  @maKH char(10)
as
	begin
		select maDonBan, maNV, DonBanHang.maKH, ngayBan, soLuongBan, tongTien
		from DonBanHang, KhachHang
		where DonBanHang.maKH = KhachHang.maKH
		and (sdt = @sdt or DonBanHang.maKH = @maKH)
	end
go
exec show_HD_BanSach_Ma_SDT 'KH001', 'KH001'

----------HIỂN THỊ HÓA ĐƠN BÁN SÁCH THEO TỔNG TIỀN GIẢM DẦN
go
create proc show_HD_BanSach_TT
as
	begin
		select maDonBan, maNV, maKH, ngayBan, soLuongBan, tongTien
		from DonBanHang
		order by tongTien DESC
	end
exec show_HD_BanSach_TT

-----------HIỂN THỊ HÓA ĐƠN BÁN SÁCH THEO SỐ LƯỢNG GIẢM DẦN
go
create proc show_HD_BanSach
as
	begin
		select maDonBan, maNV, maKH, ngayBan, soLuongBan, tongTien
		from DonBanHang
		order by soLuongBan DESC
	end
exec show_HD_BanSach

-----------BÁO CÁO THEO NGÀY
go
create proc BCngay @ngay int, @thang int, @nam int
as
	begin
		select CT_DonBanHang.maDonBan, CT_DonBanHang.maSach, CT_DonBanHang.soLuongBan, giaNhap, giaBan
		from DonBanHang ,CT_DonBanHang, CT_DonNhapHang
		where DonBanHang.maDonBan = CT_DonBanHang.maDonBan and CT_DonBanHang.maSach = CT_DonNhapHang.maSach
		and DAY(ngayBan) = @ngay and MONTH(ngayBan) = @thang and YEAR(ngayBan) = @nam
		order by maDonBan ASC
	end
go
exec BCngay 14, 4, 2021

-----------BÁO CÁO THEO THANG
go
create proc BCTHANG @thang int, @nam int
as
	begin
		select CT_DonBanHang.maDonBan, CT_DonBanHang.maSach, CT_DonBanHang.soLuongBan, giaNhap, giaBan
		from DonBanHang ,CT_DonBanHang, CT_DonNhapHang
		where DonBanHang.maDonBan = CT_DonBanHang.maDonBan and CT_DonBanHang.maSach = CT_DonNhapHang.maSach
		and MONTH(ngayBan) = @thang and YEAR(ngayBan) = @nam
		order by maDonBan ASC
	end
go
exec BCTHANG 4, 2021

-----------BÁO CÁO THEO QUÝ 1
go
create proc BCQuy1  @nam int
as
	begin
		select CT_DonBanHang.maDonBan, CT_DonBanHang.maSach, CT_DonBanHang.soLuongBan, giaNhap, giaBan
		from DonBanHang ,CT_DonBanHang, CT_DonNhapHang
		where DonBanHang.maDonBan = CT_DonBanHang.maDonBan and CT_DonBanHang.maSach = CT_DonNhapHang.maSach
		and (MONTH(ngayBan) between 1 and 6) and YEAR(ngayBan) = @nam
		order by maDonBan ASC
	end
go
exec BCQuy1 2021

-----------BÁO CÁO THEO QUÝ 2
go
create proc BCQuy2  @nam int
as
	begin
		select CT_DonBanHang.maDonBan, CT_DonBanHang.maSach, CT_DonBanHang.soLuongBan, giaNhap, giaBan
		from DonBanHang ,CT_DonBanHang, CT_DonNhapHang
		where DonBanHang.maDonBan = CT_DonBanHang.maDonBan and CT_DonBanHang.maSach = CT_DonNhapHang.maSach
		and (MONTH(ngayBan) between 7 and 12) and YEAR(ngayBan) = @nam
		order by maDonBan ASC
	end
go
exec BCQuy2 2021

-----------Lấy giá nhập sách ngày gần nhất
go
create proc GiaNhap_GanNhat @mas char(20)
as
	begin
		select giaNhap, MAX((ngayNhap)) AS N'ĐƠN HÀNG GẦN NHẤT'
		from CT_DonNhapHang, DonNhapHang 
		where CT_DonNhapHang.maDonNhap = DonNhapHang.maDonNhap AND maSach= @mas
		GROUP BY giaNhap
		HAVING MAX((ngayNhap)) >= ALL (SELECT MAX((ngayNhap))  from CT_DonNhapHang, DonNhapHang
								 where CT_DonNhapHang.maDonNhap = DonNhapHang.maDonNhap AND maSach= @mas)
	end
go
exec GiaNhap_GanNhat 'S017'
select ngayNhap, giaNhap from CT_DonNhapHang, DonNhapHang where CT_DonNhapHang.maDonNhap = DonNhapHang.maDonNhap and maSach='S017'

select *from NhanVien
select *from KhachHang
select *from NhaCungCap
select *from DonBanHang
select *from CT_DonBanHang
select *from DonNhapHang
select *from CT_DonNhapHang
select *from ThongTinSach
select *from TonKho

drop table CT_DonBanHang
drop table DonBanHang
drop table CT_DonNhapHang
drop table DonNhapHang
drop table NhaCungCap
drop table TonKho
drop table ThongTinSach
drop table KhachHang
drop table NhanVien
