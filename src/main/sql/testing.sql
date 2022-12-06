
-- testing purposes
EXEC daftarUser @nama = 'g', @nomor_telpon = 'hd', @email = 'ts', @password = 'heasdasda';
EXEC daftarPegawai @ID = 4, @pekerjaan = 'a', @departemen = 'g', @gaji = 123;
EXEC daftarPembeli @id = 4;
EXEC daftarMitra @id = 4;
EXEC daftarDriver @id = 4, @tipe_kendaraan = 'sd', @nomor_polisi = '12a34', @warna_kendaraan = 'biru';
EXEC buatRestaurant 4, 'domas', 5, 'fast food', 'aktif', 'lol'
SELECT * FROM user_info
SELECT * FROM login
SELECT * FROM pembeli
SELECT * FROM pegawai
SELECT * FROM mitra
SELECT * FROM driver
SELECT

PRINT dbo.loginCheck('ts', 'heasdasda')
SELECT * FROM pegawai

