CREATE OR ALTER PROCEDURE buatAlamatKirim
	@id_pembeli INT,
	@nama_lokasi NVARCHAR(50),
	@alamat NVARCHAR(255)
AS
BEGIN SET NOCOUNT ON;
	BEGIN TRANSACTION
		BEGIN TRY
			INSERT INTO alamat_kirim(id_pembeli, nama_lokasi, alamat) 
			VALUES (@id_pembeli, @nama_lokasi, @alamat)
	
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