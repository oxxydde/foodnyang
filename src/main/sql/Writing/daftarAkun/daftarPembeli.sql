CREATE OR ALTER PROCEDURE daftarPembeli
	@id INT,
	@membership_type NVARCHAR(20) = 'Normal'
AS
BEGIN SET NOCOUNT ON;
	BEGIN TRANSACTION
		BEGIN TRY

			INSERT INTO pembeli(id_pembeli, membership_type) 
			VALUES (@id, @membership_type)

			INSERT INTO berperan (user_id, peran_id)
			VALUES (@id, 1)
	
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