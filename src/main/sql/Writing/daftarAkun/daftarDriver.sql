CREATE OR ALTER PROCEDURE daftarDriver
	@id INT,
	@tipe_kendaraan NVARCHAR(10),
	@nomor_polisi NVARCHAR(10),
	@warna_kendaraan NVARCHAR(10)
AS
BEGIN SET NOCOUNT ON;
	BEGIN TRANSACTION
		BEGIN TRY
			-- adding to driver table
			INSERT INTO driver 
			VALUES (@id, convert(varchar, getdate(), 23))

			-- adding vehicle
			INSERT INTO transport
			VALUES (@id, @nomor_polisi, @tipe_kendaraan, @warna_kendaraan)

			INSERT INTO berperan (user_id, peran_id)
			VALUES (@id, 2)
	
		IF @@TRANCOUNT > 0
		BEGIN
			COMMIT TRANSACTION;
		END
	END TRY
	BEGIN CATCH
		PRINT 'Kode error = '+ CAST(@@ERROR AS NVARCHAR(255))
		PRINT ERROR_MESSAGE()
		IF @@TRANCOUNT > 0
		BEGIN
			ROLLBACK TRANSACTION;
		END
	END CATCH
END