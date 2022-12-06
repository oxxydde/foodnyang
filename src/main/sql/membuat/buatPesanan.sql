CREATE OR ALTER PROCEDURE buatPesanan
	@id_driver INT,
	@id_pembeli INT,
	@id_restaurant INT,
	@id_alamat_kirim INT
AS
BEGIN SET NOCOUNT ON;
	BEGIN TRANSACTION
		BEGIN TRY

			INSERT INTO pesanan(id_driver, id_pembeli, id_restaurant, id_alamat_kiri) 
			VALUES (@id_driver, @id_pembeli, @id_restaurant, @id_alamat_kirim)
	
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