CREATE OR ALTER PROCEDURE buatKategori
	@id_restaurant INT,
	@nama_kategori NVARCHAR(20)
AS
BEGIN SET NOCOUNT ON;
	BEGIN TRANSACTION
		BEGIN TRY

			-- UNTUK CEK APAKAH ADA KATEGORI YANG MIRIP
			DECLARE @similiar_id INT
			SELECT TOP 1 @similiar_id = id_kategori FROM kategori_restaurant  
			where nama LIKE '%' + @nama_kategori + '%' 
				AND ABS(Len(nama) - Len(@nama_kategori)) < 2
			Order by ABS(Len(nama) - Len(@nama_kategori)), nama

			-- INSERT DATA KATEGORI
			IF (@similiar_id is null)
				BEGIN
					INSERT INTO kategori_restaurant(nama) 
					VALUES (@nama_kategori)

					-- UNTUK MENGAMBIL PRIMARY KEY TERBARU YANG BARU DIINPUTKAN DALAM SCOPE TRANSACTION INI
					DECLARE @pdId int
                    SET @pdId = SCOPE_IDENTITY()

					-- INSERT KEDALAM TABEL TERGOLONG
					INSERT INTO tergolong(id_restaurant, id_kategori)
					VALUES(@id_restaurant, @pdId)
				END
			ELSE 
				BEGIN
					-- INSERT KEDALAM TABEL TERGOLONG
					IF @similiar_id NOT IN (SELECT id_kategori FROM tergolong WHERE id_restaurant = @id_restaurant)
						BEGIN
							INSERT INTO tergolong(id_restaurant, id_kategori)
							VALUES(@id_restaurant, @similiar_id)
						END
				END
	
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
