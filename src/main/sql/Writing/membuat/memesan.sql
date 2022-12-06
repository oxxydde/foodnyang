CREATE OR ALTER PROCEDURE memesanItem
	@id_mr INT,
	@id_pesanan INT,
	@jumlah_item INT
AS
BEGIN SET NOCOUNT ON;
	BEGIN TRANSACTION
		BEGIN TRY

			DECLARE @subtotal FLOAT
			SET @subtotal = @jumlah_item * (SELECT harga FROM menu_restaurant WHERE id_mr = @id_mr)
			
			INSERT INTO memesan(id_mr, id_pesanan, subtotal_harga, jumlah_item) 
			VALUES (@id_mr, @id_pesanan, @subtotal, @jumlah_item)
	
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