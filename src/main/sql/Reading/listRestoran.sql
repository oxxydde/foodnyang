CREATE OR ALTER PROCEDURE listRestoran
	@nama NVARCHAR(50) = '%%',
	@tipe_restaurant NVARCHAR(30) = '%%',
	@status NVARCHAR(10) = 'Aktif'
AS
	BEGIN TRY
			SELECT * FROM restaurant 
			WHERE nama = @nama	OR tipe_restaurant = @tipe_restaurant OR status = @status
	END TRY
	BEGIN CATCH
		PRINT 'Kode error = '+ CAST(@@ERROR AS NVARCHAR(255))
		PRINT ERROR_MESSAGE()
	END CATCH


EXEC buatRestaurant 2, 'a', 8, 'Cafe', 'Aktif', 'a'
EXEC listRestoran 

SELECT * FROM mitra