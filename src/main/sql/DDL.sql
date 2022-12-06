--USE FoodNyang GO;

CREATE TABLE user_info(
    id INT PRIMARY KEY IDENTITY (0,1),
    nama NVARCHAR(50) NOT NULL,
    jenis_kelamin NVARCHAR(8),
    tempat_lahir NVARCHAR(30),
    tanggal_lahir DATE,
    nomor_telpon NVARCHAR(15) UNIQUE NOT NULL,
    alamat NVARCHAR(255),
	email NVARCHAR(150) UNIQUE NOT NULL,
    status NVARCHAR(10) NOT NULL DEFAULT 'Aktif'
);

CREATE TABLE peran_user(
	peran_id INT PRIMARY KEY,
	nama_peran NVARCHAR(7) NOT NULL
);

CREATE TABLE berperan(
	user_id INT FOREIGN KEY REFERENCES user_info(id),
	peran_id INT FOREIGN KEY REFERENCES peran_user(peran_id)
);

CREATE TABLE login(
	email NVARCHAR(150) PRIMARY KEY,
    user_id INT NOT NULL FOREIGN KEY REFERENCES user_info(id),
    password NVARCHAR(150) NOT NULL
);

CREATE TABLE pegawai(
    id_pegawai INT PRIMARY KEY FOREIGN KEY REFERENCES user_info(id),
    tanggal_perekrutan DATE NOT NULL,
    pekerjaan NVARCHAR(50) NOT NULL,
    manajer INT,
    departemen NVARCHAR(20) NOT NULL,
    gaji INT NOT NULL
);

CREATE TABLE driver(
    id_driver INT PRIMARY KEY FOREIGN KEY REFERENCES user_info(id),
    tanggal_bergabung DATE NOT NULL
);

CREATE TABLE pembeli(
    id_pembeli INT PRIMARY KEY FOREIGN KEY REFERENCES user_info(id),
    membership_type NVARCHAR(20) DEFAULT 'Normal'
);

CREATE TABLE transport(
    id_driver INT PRIMARY KEY FOREIGN KEY REFERENCES driver(id_driver) ON DELETE CASCADE,
    nomor_polisi NVARCHAR(10) NOT NULL,
    tipe_kendaraan NVARCHAR(10) NOT NULL,
    warna_kendaraan NVARCHAR(10)
);

CREATE TABLE mitra(
    id_mitra INT PRIMARY KEY FOREIGN KEY REFERENCES user_info(id),
    tanggal_bergabung DATE NOT NULL
);

CREATE TABLE alamat_kirim(
    id_alamat INT PRIMARY KEY IDENTITY(2000, 1),
    id_pembeli INT NOT NULL FOREIGN KEY REFERENCES pembeli(id_pembeli),
    nama_lokasi NVARCHAR(50) NOT NULL,
    alamat NVARCHAR(255) NOT NULL,
    zipcode INT,
    latitude DECIMAL(12, 9), 
    longitude DECIMAL(12, 9),
);

CREATE TABLE restaurant(
    id_restaurant INT PRIMARY KEY IDENTITY(3000,1),
    id_mitra INT NOT NULL FOREIGN KEY REFERENCES mitra(id_mitra),
    nama NVARCHAR(50) NOT NULL,
    jam_operasional TINYINT NOT NULL,
    tipe_restaurant NVARCHAR(30) NOT NULL,
    status NVARCHAR(10) NOT NULL,
    rating TINYINT CHECK(rating BETWEEN 0 AND 5) DEFAULT 0,
    alamat NVARCHAR(255) NOT NULL,
    latitude DECIMAL(12, 9), 
    longitude DECIMAL(12, 9),
);

CREATE TABLE pesanan(
    id_pesanan INT PRIMARY KEY IDENTITY(1000, 1),
    id_driver INT,
    id_pembeli INT,
    id_restaurant INT,
    id_alamat_kirim INT,
    status NVARCHAR(10) NOT NULL DEFAULT 'Diterima',
    waktu_diterima DATETIME2,
    waktu_selsai DATETIME2,
    total_item INT DEFAULT 0,
    total_harga FLOAT DEFAULT 0,

	FOREIGN KEY (id_driver) REFERENCES driver(id_driver),
	FOREIGN KEY (id_pembeli) REFERENCES pembeli(id_pembeli),
	FOREIGN KEY (id_restaurant) REFERENCES restaurant(id_restaurant),
	FOREIGN KEY (id_alamat_kirim) REFERENCES alamat_kirim(id_alamat)
);

CREATE TABLE menu_restaurant(
    id_mr INT PRIMARY KEY IDENTITY(4000,1),
    id_restaurant INT NOT NULL FOREIGN KEY REFERENCES restaurant(id_restaurant),
    nama NVARCHAR(50) NOT NULL,
    harga FLOAT NOT NULL,
    ketersediaan INT DEFAULT 0,
    deskripsi NVARCHAR(255),
	gambarMakanan NVARCHAR(2083)
);

CREATE TABLE kategori_restaurant(
    id_kategori INT PRIMARY KEY IDENTITY(5000,1),
    nama NVARCHAR(20) NOT NULL
);

CREATE TABLE memesan(
    id_mr INT FOREIGN KEY REFERENCES menu_restaurant(id_mr),
    id_pesanan INT FOREIGN KEY REFERENCES pesanan(id_pesanan),
    subtotal_harga FLOAT DEFAULT 0,
    jumlah_item INT DEFAULT 0
);

CREATE TABLE tergolong(
    id_restaurant INT FOREIGN KEY REFERENCES restaurant(id_restaurant),
    id_kategori INT FOREIGN KEY REFERENCES kategori_restaurant(id_kategori)
);
