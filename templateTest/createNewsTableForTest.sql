USE [StockManagement]
GO

/****** Object:  Table [dbo].[News]    Script Date: 10/06/2025 1:29:42 CH ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[News](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](50) NOT NULL,
	[content] [varchar](50) NOT NULL,
	[ticker] [char](6) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[News]  WITH CHECK ADD  CONSTRAINT [FK_News_Stocks] FOREIGN KEY([ticker])
REFERENCES [dbo].[tblStocks] ([ticker])
GO

ALTER TABLE [dbo].[News] CHECK CONSTRAINT [FK_News_Stocks]
GO


