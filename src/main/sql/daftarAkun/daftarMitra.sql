CREATE OR ALTER PROCEDURE daftarMitra
	@id INT
AS
BEGIN SET NOCOUNT ON;
	BEGIN TRANSACTION
		BEGIN TRY

			INSERT INTO mitra(id_mitra, tanggal_bergabung) 
			VALUES (@id, convert(varchar, getdate(), 23))

			INSERT INTO berperan (user_id, peran_id)
			VALUES (@id, 3)
	
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