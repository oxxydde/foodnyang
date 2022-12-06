-- peran user
INSERT INTO peran_user (peran_id, nama_peran)
VALUES (1, 'Pembeli');

INSERT INTO peran_user (peran_id, nama_peran)
VALUES (2, 'Driver');

INSERT INTO peran_user (peran_id, nama_peran)
VALUES (3, 'Mitra');

INSERT INTO peran_user (peran_id, nama_peran)
VALUES (4, 'Pegawai');

-- user_info
SET IDENTITY_INSERT user_info ON;
INSERT INTO user_info (id, nama, jenis_kelamin, nomor_telpon, tempat_lahir, tanggal_lahir, email, alamat, status)
VALUES (15,'Aphrodite Hampton','Wanita','228-3371-4330','Culiacán', convert(DATE, '1998-12-24 00:00:00', 23),'lorem.donec@hotmail.edu', 'Ap #101-3722 Magna. Ave','Aktif'), -- Password 111222333
(17,'Fletcher Silva','Wanita','448-1690-7559','Nagaon', convert(date, '1992-05-28 00:00:00', 23),'in.consequat.enim@yahoo.edu', 'Ap #120-8064 Hendrerit Avenue','Aktif'),	-- Password 123454321
(1,'Aiko Miles','Wanita','117-1634-1662','Kherson', convert(date, '1997-04-06 00:00:00', 23),'justo.nec.ante@aol.net', '"Ap #204-584 Convallis Av','Aktif'), -- Password Admin
(12,'Wayne Lawson','Pria','742-8618-7834','Chełm', convert(date, '1989-06-07 00:00:00', 23),'magna.nec@icloud.com', 'P.O. Box 676, 9563 Ante. St.','Aktif'), -- Password 000000
(5,'Fredericka Langley','Pria','407-6029-5660','Thabazimbi',convert(date, '1989-01-30 00:00:00', 23), 'Thabazimbi","P.O. Box 796, 8646 Risus. Av.','fermentum.metus.aenean@yahoo.edu','Aktif'); -- Password password
SET IDENTITY_INSERT user_info OFF;

-- login
INSERT INTO login(email, user_id, password)
VALUES ('lorem.donec@hotmail.edu', 15, HASHBYTES('SHA2_512', '111222333')),
('in.consequat.enim@yahoo.edu', 17, HASHBYTES('SHA2_512', '123454321')),
('justo.nec.ante@aol.net', 1, HASHBYTES('SHA2_512', 'Admin')),
('magna.nec@icloud.com', 12, HASHBYTES('SHA2_512', '000000')),
('fermentum.metus.aenean@yahoo.edu', 5, HASHBYTES('SHA2_512', 'password'))

-- pegawai
INSERT INTO pegawai(id_pegawai, tanggal_perekrutan, pekerjaan, manajer, departemen, gaji)
VALUES 
(15, '2020-10-8', 'AdministratorDB', NULL, 'IT', 8000000),
(12, '2019-10-8', 'Software Engineer', NULL, 'IT', 9800000)

-- pembeli
INSERT INTO pembeli(id_pembeli, membership_type)
VALUES 
(1, 'Sultan'), (5, 'Premium'), (12, 'Normal'), (15, 'Normal'), (17, 'Premium')

-- driver
INSERT INTO driver(id_driver, tanggal_bergabung)
VALUES (17, '2018-01-30'), (15, '2021-03-24')

-- Mitra
INSERT INTO mitra(id_mitra, tanggal_bergabung)
VALUES(1, '2022-01-04'), (5, '2021-07-26'), (12, '2018-05-25') 

-- berperan
INSERT INTO berperan(user_id, peran_id)
VALUES 
(1, 1), (1, 3),				-- USER ID 1 (PEMBELI, MITRA)
(5, 1), (5, 3),				-- USER ID 5 (PEMBELI, MITRA)
(12, 1), (12, 3), (12, 4),	-- USER ID 12 (PEMBELI, MITRA, PEGAWAI)
(15, 1), (15, 2), (15, 4),	-- USER ID 15 (PEMBELI, DRIVER, PEGAWAI)
(17, 1), (17, 2)			-- USER ID 17 (PEMBELI, DRIVER)

-- transport
INSERT INTO transport(id_driver, nomor_polisi, tipe_kendaraan, warna_kendaraan)
VALUES
(15, 'D 14 RE', 'Roda 2', 'Cokelat'), (17, 'L 38 AY', 'Roda 2', 'Silver')

-- alamat_kirim
SET IDENTITY_INSERT alamat_kirim ON;
INSERT INTO alamat_kirim(id_alamat, id_pembeli, nama_lokasi, alamat, zipcode, latitude, longitude)
VALUES 
(2001, 1, 'Istana Ku', 'Mojolangu, Lowokwaru, Malang City, East Java 65142', 65412, -7.930237561509358, 112.61781635090222),
(2002, 5, 'Kampus Filkom', '2JW7+GVF, Jl. Veteran, Ketawanggede, Kec. Lowokwaru, Kota Malang, Jawa Timur 65145', 65145, -7.953578294035755, 112.61464772729722),
(2003, 12, 'Perpustakaan UB', 'Gedung JPC, Jl. Veteran, Ketawanggede, Kec. Lowokwaru, Kota Malang, Jawa Timur 65145', 65145, -7.953127963888025, 112.61327430025274),
(2004, 15, 'Syariah Residence', 'Jl. Bend. Sempor No.8, Sumbersari, Kec. Lowokwaru, Kota Malang, Jawa Timur 65145', 65145, -7.959672004419853, 112.61263381153837),
(2005, 17, 'Masjid Sabilillah', 'Jl. A. Yani No.15, Blimbing, Kec. Blimbing, Kota Malang, Jawa Timur 65126', 65126, -7.9406784897408675, 112.64139514724842),
(2006, 1, 'Gazebo Perpus UMM', 'Jl. Raya Tlogomas No.246, Babatan, Tegalgondo, Kec. Lowokwaru, Kota Malang, Jawa Timur 65144', 65144, -7.9209893192851295, 112.59706746746095),
(2007, 12, 'Perumahan Villa Tlogomas', 'Jl. Joyo Agung III, Tlogomas, Lowokwaru, Malang City, East Java 65144', 65144, -7.937708221024554, 112.59275704126162) 
SET IDENTITY_INSERT alamat_kirim OFF;

-- restaurant
SET IDENTITY_INSERT restaurant ON;
INSERT INTO restaurant(id_restaurant, id_mitra, nama, jam_operasional, tipe_restaurant, status, rating, alamat, longitude, latitude)
VALUES
(3001, 1, 'Warung Jai Lalapan', 12, 'Warung', 'Aktif', 4, 'Jl. Sigura - Gura No.5, Sumbersari, Kec. Lowokwaru, Kota Malang, Jawa Timur 65149', -7.9583965873784335, 112.60744315652363),
(3002, 5, 'Tachibay', 20, 'Warung', 'Aktif', 4, 'Jl. Simp.Gajayana No.72, Dinoyo, Kec. Lowokwaru, Kota Malang, Jawa Timur 65144', -7.947277629188254, 112.60572852738584),
(3003, 12, 'Roketto Coffee Suhat', 12, 'Cafe', 'Aktif', 4, 'Jl. Soekarno - Hatta No.D-511, Mojolangu, Kec. Lowokwaru, Kota Malang, Jawa Timur 65141', -7.9405953028553435, 112.62303234907506),
(3004, 1, 'Nasi Goreng Sayang', 10, 'Casual Style Dining', 'Aktif', 4,'2JR5+V4R, Jl. Sigura - Gura, Sumbersari, Kec. Lowokwaru, Kota Malang, Jawa Timur 65149', -7.957663745351537, 112.60772449093407),
(3005, 12, 'Dapoer Cobek', 24, 'Fast Casual Dining', 'Aktif', 4, 'Jl.Bendungan Sigura gura No.26-A.B.C, Sumbersari, Kota Malang, Jawa Timur 65145', -7.957868267050879, 112.60880834916073)
SET IDENTITY_INSERT restaurant OFF;

-- menu restaurant
SET IDENTITY_INSERT menu_restaurant ON;
INSERT INTO menu_restaurant(id_mr, id_restaurant, nama, harga, deskripsi, ketersediaan)
VALUES
(4001, 3001, 'Lalapan Ayam Bakar Jumbo KS Original', 25000, 'Nasi + Ayam Bakar Jumbo Khas Dapur KS + Sayur Lalapan + Sambal', 10),
(4002, 3002, 'Ayam Crispy + Nasi', 17500, 'Ayam goreng dibalur tepung crispy crunchy yang kayaa akan rasa & bergizi', 20),
(4003, 3003, 'Roku', 24000, 'Iced milk, brown sugar with chocolate pudding - non coffe', 30),
(4004, 3004, 'Nasi Goreng Kulit Crispy', 15000, Null, 20),
(4005, 3005, 'Paket Ayam Kentucky + Tahu Sambal Bawang', 28800, 'Nasi, Ayam Kentucky, tahu, Lalapan, dan Sambal Bawang', 40)
SET IDENTITY_INSERT menu_restaurant OFF;

-- pesanan
SET IDENTITY_INSERT pesanan ON;
INSERT INTO pesanan(id_pesanan, id_pembeli, id_driver, id_restaurant, id_alamat_kirim, status, waktu_diterima, waktu_selsai, total_item, total_harga)
VALUES
(1001,  1, 15, 3003, 2001, 'Diterima', CURRENT_TIMESTAMP, NULL, 2, 48000),
(1002, 12, 17, 3005, 2007, 'Selsai', DateADD(mi, -30, CURRENT_TIMESTAMP), CURRENT_TIMESTAMP, 1, 28800)
SET IDENTITY_INSERT pesanan OFF;

-- memesan
INSERT INTO memesan(id_mr, id_pesanan, jumlah_item, subtotal_harga)
VALUES (4003, 1001, 2, 48000), (4005, 1002, 1, 28800)

-- kategori_restaurant
SET IDENTITY_INSERT kategori_restaurant ON;
INSERT INTO kategori_restaurant(id_kategori, nama)
VALUES (5003, 'Lalapan'), (5004, 'Beverages'), (5005, 'Chicken & duck'), (5006, 'Nasi')
SET IDENTITY_INSERT kategori_restaurant OFF;

-- tergolong
INSERT INTO tergolong(id_restaurant, id_kategori)
VALUES 
(3001, 5003), (3001, 5005),					-- Warung Jai Lalapan
(3002, 5003), (3002, 5005),					-- Tachibay
(3003, 5004),								-- Roketto Coffe Suhat
(3004, 5005), (3004, 5006),					-- Nasi Goreng Sayang
(3005, 5003), (3005, 5005), (3005, 5006)	-- Dapoer Cobek
