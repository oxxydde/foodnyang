-- peran user
INSERT INTO peran_user (peran_id, nama_peran)
VALUES (1, 'Pembeli');

INSERT INTO peran_user (peran_id, nama_peran)
VALUES (2, 'Driver');

INSERT INTO peran_user (peran_id, nama_peran)
VALUES (3, 'Mitra');

INSERT INTO peran_user (peran_id, nama_peran)
VALUES (4, 'Pegawai');

-- kategori-restaurant


-- USER INFO
EXEC daftarUser @nama = 'B', @nomor_telpon = 'h', @email = 'g', @password = 'heada';
EXEC daftarPegawai @ID = 2, @pekerjaan = 'a', @departemen = 'g', @gaji = 123;
EXEC daftarPembeli @id = 2;
EXEC daftarMitra @id = 2;
EXEC daftarDriver @id = 2, @tipe_kendaraan = 'sd', @nomor_polisi = '1234', @warna_kendaraan = 'biru';
SELECT * FROM user_info
SELECT * FROM login
SELECT * FROM pembeli
SELECT * FROM pegawai
SELECT * FROM mitra
SELECT * FROM driver
SELECT

PRINT dbo.loginCheck('c', 'hello word')
SELECT * FROM pegawai

