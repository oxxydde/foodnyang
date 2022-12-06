CREATE OR ALTER FUNCTION loginRole
(
	@EMAIL nvarchar(150),
	@PASSWORD nvarchar(255)
)
RETURNS INT
AS
BEGIN
	DECLARE @status AS nvarchar(7)
	DECLARE @id INT
	SET @status = dbo.loginCheck(@EMAIL, @PASSWORD)

	IF @status = 'Valid'
	BEGIN
		select @id = (SELECT user_id FROM [login] WHERE email = @EMAIL)
	END
	RETURN @id
END
	
