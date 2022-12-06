CREATE OR ALTER PROCEDURE buatMenuRestaurant
	@id_restaurant INT,
	@nama NVARCHAR(50),
	@harga FLOAT,
	@deskripsi NVARCHAR(255) = NULL,
	@ketersediaan INT = 0
AS
BEGIN SET NOCOUNT ON;
	BEGIN TRANSACTION
		BEGIN TRY

			INSERT INTO menu_restaurant(id_restaurant, nama, harga, deskripsi, ketersediaan) 
			VALUES (@id_restaurant, @nama, @harga, @deskripsi, @ketersediaan)
	
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