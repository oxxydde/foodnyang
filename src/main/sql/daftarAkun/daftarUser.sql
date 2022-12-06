CREATE OR ALTER PROCEDURE daftarUser
	@nama NVARCHAR(50),
	@nomor_telpon NVARCHAR(15),
	@email NVARCHAR(150),
	@password NVARCHAR(150)
AS
BEGIN SET NOCOUNT ON;
	BEGIN TRANSACTION
		BEGIN TRY
			-- adding to driver table
			INSERT INTO user_info(nama, nomor_telpon, email)
			VALUES (@nama, @nomor_telpon, @email);

			DECLARE @pdId int
            SET @pdId = SCOPE_IDENTITY()

			INSERT INTO login(email, user_id, password)
			VALUES (@email, @pdId, HASHBYTES('SHA2_512', @password))
	
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