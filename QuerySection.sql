USE QLThueNhaTro_java

-- DROP a database is in use
USE MASTER
ALTER DATABASE QLThueNhaTro_java SET SINGLE_USER WITH ROLLBACK IMMEDIATE;


-- SELECT Section

SELECT * FROM Admins
SELECT * FROM Chutro
SELECT * FROM NguoiThueTro
SELECT * FROM TTPhongtro
SELECT * FROM HoaDon
SELECT * FROM CTHoadon


Insert Into TTPhongtro
set Sodienhientai = 1 , Sonuochientai = 1
where idPhong = 1 & 2

UPDATE TTPhongtro
SET Sodienhientai = 1, Sonuochientai = 1
WHERE IDPhong IN (1, 2);


-- INSERT Section
-- Initialize Admins
    INSERT INTO Users (Username, Password, Role, is_active)
VALUES
    ('admin_1', 'pass123', 'admin', 1),
    ('admin_2', 'pass123', 'admin', 1);

INSERT INTO Admins (FullName, Username, Password, IsRoot, UserID)
VALUES
    ('Admin One', 'admin_1', 'pass123', 1, 1),
    ('Admin Two', 'admin_2', 'pass123', 0, 2);

-- UPDATE Section
UPDATE Users SET is_active = 1 WHERE UserID = 2

-- ALTER Section
ALTER TABLE TTPhongtro ADD

-- DROP Section
    DROP TABLE Admins
DROP TABLE Users
DROP TABLE TTPhongtro



SELECT 
                TTPhongtro.IDPhong, 
                TTPhongtro.TenPhong, 
                ISNULL(NguoiThueTro.Hoten, 'Không có') AS TenNguoiThue, 
                TTPhongtro.GiaPhong, 
                TTPhongtro.Giadien, 
                TTPhongtro.Gianuoc, 
                ISNULL(TTPhongtro.Giarac, 0) AS Giarac ,
                TTPhongtro.Sodienhientai,
                TTPhongtro.Sonuochientai
            FROM TTPhongtro
            LEFT JOIN NguoiThueTro ON TTPhongtro.IDNguoiThue = NguoiThueTro.IDNguoiThue
            WHERE TTPhongtro.IDPhong = 3