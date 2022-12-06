CREATE OR ALTER PROCEDURE buatRestaurant
	@id_mitra INT,
	@nama NVARCHAR(50),
	@jam_operasional TINYINT,
	@tipe_restaurant NVARCHAR(30),
	@status NVARCHAR(10),
	@alamat NVARCHAR(255)
AS
BEGIN SET NOCOUNT ON;
	BEGIN TRANSACTION
		BEGIN TRY

			INSERT INTO restaurant(id_mitra, nama, jam_operasional, tipe_restaurant, status, alamat) 
			VALUES (@id_mitra, @nama, @jam_operasional, @tipe_restaurant, @status, @alamat)
	
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