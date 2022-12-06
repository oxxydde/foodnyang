CREATE OR ALTER FUNCTION [dbo].[loginCheck]
(
	@EMAIL nvarchar(150),
	@PASSWORD nvarchar(255)
)
RETURNS nvarchar(7)
AS
BEGIN
	DECLARE @result nvarchar(7)
	Select
		@result  =( CASE when [password] =  HASHBYTES('SHA2_512', @PASSWORD) THEN 'Valid'
			ELSE 'Invalid'
		END )
		from login
		where  [email] =  @EMAIL
 
	RETURN @result
	END
GO