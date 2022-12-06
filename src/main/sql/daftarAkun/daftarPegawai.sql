CREATE OR ALTER PROCEDURE daftarPegawai
	@id INT,
	@pekerjaan NVARCHAR(20),
	@manajer INT = NULL,
	@departemen NVARCHAR(20),
	@gaji INT
AS
BEGIN SET NOCOUNT ON;
	BEGIN TRANSACTION
		BEGIN TRY

			INSERT INTO pegawai(id_pegawai, tanggal_perekrutan, pekerjaan, manajer, departemen, gaji) 
			VALUES (@id, convert(varchar, getdate(), 23), @pekerjaan, @manajer, @departemen, @gaji)

			INSERT INTO berperan (user_id, peran_id)
			VALUES (@id, 4)
	
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