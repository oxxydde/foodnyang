CREATE OR ALTER PROCEDURE buatKategori
	@id_restaurant INT,
	@nama_kategori INT
AS
BEGIN SET NOCOUNT ON;
	BEGIN TRANSACTION
		BEGIN TRY

			DECLARE select , ABS(Len(searchColumn) - Len(@MyString))
			from kategori_restaurant where data LIKE '%' + @MyString + '%'
			Order by Similarity, searchColumn

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