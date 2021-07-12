USE [PRJ_Assignment]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 7/12/2021 3:33:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [name] [nvarchar](50) NOT NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[comment]    Script Date: 7/12/2021 3:33:26 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[comment](
    [username] [nvarchar](255) NOT NULL,
    [image_url] [text] NULL,
    [content] [text] NULL,
    [product_id] [int] NULL,
    [created_at] [datetime] NULL
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Order_Detail]    Script Date: 7/12/2021 3:33:26 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Order_Detail](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [product_id] [int] NOT NULL,
    [username] [nvarchar](255) NULL,
    [price] [float] NOT NULL,
    [quantity] [int] NULL,
    [order_date] [datetime] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Order_Details]    Script Date: 7/12/2021 3:33:26 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Order_Details](
    [product_id] [int] NOT NULL,
    [order_id] [int] NOT NULL,
    [quantity] [int] NOT NULL,
    [price] [float] NULL,
     CONSTRAINT [PK_Order_Details] PRIMARY KEY CLUSTERED
    (
    [product_id] ASC,
[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Orders]    Script Date: 7/12/2021 3:33:26 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Orders](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [customer_id] [int] NOT NULL,
    [order_date] [datetime] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Product]    Script Date: 7/12/2021 3:33:26 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Product](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [name] [nvarchar](255) NOT NULL,
    [category_id] [int] NOT NULL,
    [price] [float] NOT NULL,
    [quantity] [int] NOT NULL,
    [seller_id] [int] NULL,
    [description] [text] NULL,
    [create_date] [datetime] NULL,
    [modify_date] [datetime] NULL,
    [image_url] [text] NULL,
    CONSTRAINT [PK__Product__3213E83FBED3EFAD] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Ranking]    Script Date: 7/12/2021 3:33:26 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Ranking](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [name] [nvarchar](50) NOT NULL,
    [discount_percent] [float] NULL,
    [total_spent] [float] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Role]    Script Date: 7/12/2021 3:33:26 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Role](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [name] [nvarchar](50) NOT NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[User_Account]    Script Date: 7/12/2021 3:33:26 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[User_Account](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [full_name] [nvarchar](50) NOT NULL,
    [username] [varchar](50) NOT NULL,
    [password] [varchar](50) NOT NULL,
    [address] [nvarchar](255) NULL,
    [phone] [nvarchar](20) NULL,
    [role_id] [int] NOT NULL,
    [rank_id] [int] NOT NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
    SET IDENTITY_INSERT [dbo].[Category] ON

    INSERT [dbo].[Category] ([id], [name]) VALUES (1, N'Food')
    INSERT [dbo].[Category] ([id], [name]) VALUES (2, N'Technology')
    INSERT [dbo].[Category] ([id], [name]) VALUES (3, N'Cosmetic')
    SET IDENTITY_INSERT [dbo].[Category] OFF
    GO
    INSERT [dbo].[comment] ([username], [image_url], [content], [product_id], [created_at]) VALUES (N'doanhthuc', N'https://this-is-image-url', N'Good Product!!!', 2, CAST(N'2021-07-12T15:15:27.317' AS DateTime))
    INSERT [dbo].[comment] ([username], [image_url], [content], [product_id], [created_at]) VALUES (N'doanhthuc', NULL, N'Nice Product!!!', 2, CAST(N'2021-07-12T15:24:01.907' AS DateTime))
    GO
    SET IDENTITY_INSERT [dbo].[Order_Detail] ON

    INSERT [dbo].[Order_Detail] ([id], [product_id], [username], [price], [quantity], [order_date]) VALUES (3, 1, N'doanhthuc', 100, 2, CAST(N'2021-07-11T21:50:56.460' AS DateTime))
    INSERT [dbo].[Order_Detail] ([id], [product_id], [username], [price], [quantity], [order_date]) VALUES (4, 2, N'doanhthuc', 150, 2, CAST(N'2021-07-11T21:50:57.037' AS DateTime))
    SET IDENTITY_INSERT [dbo].[Order_Detail] OFF
    GO
    INSERT [dbo].[Order_Details] ([product_id], [order_id], [quantity], [price]) VALUES (1, 1, 10, 1234456)
    INSERT [dbo].[Order_Details] ([product_id], [order_id], [quantity], [price]) VALUES (2, 2, 1, 110)
    INSERT [dbo].[Order_Details] ([product_id], [order_id], [quantity], [price]) VALUES (2, 3, 1, 110)
    INSERT [dbo].[Order_Details] ([product_id], [order_id], [quantity], [price]) VALUES (2, 4, 1, 110)
    INSERT [dbo].[Order_Details] ([product_id], [order_id], [quantity], [price]) VALUES (2, 5, 1, 110)
    INSERT [dbo].[Order_Details] ([product_id], [order_id], [quantity], [price]) VALUES (2, 6, 1, 110)
    INSERT [dbo].[Order_Details] ([product_id], [order_id], [quantity], [price]) VALUES (2, 7, 1, 110)
    INSERT [dbo].[Order_Details] ([product_id], [order_id], [quantity], [price]) VALUES (2, 8, 1, 110)
    INSERT [dbo].[Order_Details] ([product_id], [order_id], [quantity], [price]) VALUES (2, 9, 1, 110)
    INSERT [dbo].[Order_Details] ([product_id], [order_id], [quantity], [price]) VALUES (2, 11, 1, 110)
    INSERT [dbo].[Order_Details] ([product_id], [order_id], [quantity], [price]) VALUES (3, 2, 3, 10)
    GO
    SET IDENTITY_INSERT [dbo].[Orders] ON

    INSERT [dbo].[Orders] ([id], [customer_id], [order_date]) VALUES (1, 2, CAST(N'2021-06-19T00:00:00.000' AS DateTime))
    INSERT [dbo].[Orders] ([id], [customer_id], [order_date]) VALUES (2, 2, CAST(N'2021-06-23T17:05:57.960' AS DateTime))
    INSERT [dbo].[Orders] ([id], [customer_id], [order_date]) VALUES (3, 2, CAST(N'2021-06-23T20:28:08.660' AS DateTime))
    INSERT [dbo].[Orders] ([id], [customer_id], [order_date]) VALUES (4, 2, CAST(N'2021-06-23T20:32:36.037' AS DateTime))
    INSERT [dbo].[Orders] ([id], [customer_id], [order_date]) VALUES (5, 2, CAST(N'2021-06-25T10:47:53.607' AS DateTime))
    INSERT [dbo].[Orders] ([id], [customer_id], [order_date]) VALUES (6, 2, CAST(N'2021-06-27T16:59:12.917' AS DateTime))
    INSERT [dbo].[Orders] ([id], [customer_id], [order_date]) VALUES (7, 2, CAST(N'2021-07-03T20:05:31.443' AS DateTime))
    INSERT [dbo].[Orders] ([id], [customer_id], [order_date]) VALUES (8, 2, CAST(N'2021-07-03T21:08:56.790' AS DateTime))
    INSERT [dbo].[Orders] ([id], [customer_id], [order_date]) VALUES (9, 2, CAST(N'2021-07-03T21:09:45.630' AS DateTime))
    INSERT [dbo].[Orders] ([id], [customer_id], [order_date]) VALUES (10, 2, CAST(N'2021-07-03T21:15:18.443' AS DateTime))
    INSERT [dbo].[Orders] ([id], [customer_id], [order_date]) VALUES (11, 2, CAST(N'2021-07-03T21:15:42.630' AS DateTime))
    SET IDENTITY_INSERT [dbo].[Orders] OFF
    GO
    SET IDENTITY_INSERT [dbo].[Product] ON

    INSERT [dbo].[Product] ([id], [name], [category_id], [price], [quantity], [seller_id], [description], [create_date], [modify_date], [image_url]) VALUES (1, N'Laptop Dell', 2, 700, 25, 3, N'Laptop for Dell', CAST(N'2021-06-19T00:00:00.000' AS DateTime), CAST(N'2021-06-19T00:00:00.000' AS DateTime), N'link')
    INSERT [dbo].[Product] ([id], [name], [category_id], [price], [quantity], [seller_id], [description], [create_date], [modify_date], [image_url]) VALUES (2, N'Samsung galaxy note 21', 1, 110, 50, 3, N'New Galaxy note generation 2021', NULL, NULL, N'link')
    INSERT [dbo].[Product] ([id], [name], [category_id], [price], [quantity], [seller_id], [description], [create_date], [modify_date], [image_url]) VALUES (3, N'Chocolate M2M', 1, 10, 90, 3, N'Best chocolate ever in the world', CAST(N'2021-06-23T11:33:01.803' AS DateTime), NULL, N'link')
    INSERT [dbo].[Product] ([id], [name], [category_id], [price], [quantity], [seller_id], [description], [create_date], [modify_date], [image_url]) VALUES (5, N'La roche Posay B5', 3, 20, 30, 3, N'Acid serum with vitamin B5', CAST(N'2021-06-23T11:41:39.303' AS DateTime), NULL, N'link')
    INSERT [dbo].[Product] ([id], [name], [category_id], [price], [quantity], [seller_id], [description], [create_date], [modify_date], [image_url]) VALUES (6, N'La roche Posay B512', 3, 20, 30, 3, N'Acid serum with vitamin B5', CAST(N'2021-06-23T11:47:08.050' AS DateTime), NULL, N'link')
    INSERT [dbo].[Product] ([id], [name], [category_id], [price], [quantity], [seller_id], [description], [create_date], [modify_date], [image_url]) VALUES (7, N'La roche Posay B513', 3, 25, 30, 3, N'Acid serum with vitamin B5', CAST(N'2021-06-23T14:38:59.010' AS DateTime), NULL, N'link')
    INSERT [dbo].[Product] ([id], [name], [category_id], [price], [quantity], [seller_id], [description], [create_date], [modify_date], [image_url]) VALUES (8, N'La roche Posay B5113', 3, 25, 30, 3, N'Acid serum with vitamin B5', CAST(N'2021-06-23T14:46:49.067' AS DateTime), NULL, NULL)
    SET IDENTITY_INSERT [dbo].[Product] OFF
    GO
    SET IDENTITY_INSERT [dbo].[Ranking] ON

    INSERT [dbo].[Ranking] ([id], [name], [discount_percent], [total_spent]) VALUES (1, N'Bronze', 0, 0)
    INSERT [dbo].[Ranking] ([id], [name], [discount_percent], [total_spent]) VALUES (2, N'Silver', 0.05, 500)
    INSERT [dbo].[Ranking] ([id], [name], [discount_percent], [total_spent]) VALUES (3, N'Gold', 0.1, 1000)
    SET IDENTITY_INSERT [dbo].[Ranking] OFF
    GO
    SET IDENTITY_INSERT [dbo].[Role] ON

    INSERT [dbo].[Role] ([id], [name]) VALUES (1, N'Admin')
    INSERT [dbo].[Role] ([id], [name]) VALUES (2, N'Customer')
    INSERT [dbo].[Role] ([id], [name]) VALUES (3, N'Seller')
    SET IDENTITY_INSERT [dbo].[Role] OFF
    GO
    SET IDENTITY_INSERT [dbo].[User_Account] ON

    INSERT [dbo].[User_Account] ([id], [full_name], [username], [password], [address], [phone], [role_id], [rank_id]) VALUES (1, N'Le Phuoc Cuong', N'cuongle', N'123', N'202 FPTU Dom', N'123456789', 1, 1)
    INSERT [dbo].[User_Account] ([id], [full_name], [username], [password], [address], [phone], [role_id], [rank_id]) VALUES (2, N'Doanh Thuc', N'doanhthuc', N'123', N'123 NHS Street', N'123456789', 2, 2)
    INSERT [dbo].[User_Account] ([id], [full_name], [username], [password], [address], [phone], [role_id], [rank_id]) VALUES (3, N'Nguyen Huu Phuoc', N'phuocnguyen', N'123', N'123 Hoa Hai Street', N'123456789', 3, 3)
    INSERT [dbo].[User_Account] ([id], [full_name], [username], [password], [address], [phone], [role_id], [rank_id]) VALUES (4, N'Nguyen Van Tien Dung', N'tiendungAI', N'tiensiML', N'vien AI Nha Trang', N'0339198650', 2, 1)
    SET IDENTITY_INSERT [dbo].[User_Account] OFF
    GO
ALTER TABLE [dbo].[Order_Details]  WITH CHECK ADD  CONSTRAINT [FK_Order] FOREIGN KEY([order_id])
    REFERENCES [dbo].[Orders] ([id])
    GO
ALTER TABLE [dbo].[Order_Details] CHECK CONSTRAINT [FK_Order]
    GO
ALTER TABLE [dbo].[Order_Details]  WITH CHECK ADD  CONSTRAINT [FK_Product] FOREIGN KEY([product_id])
    REFERENCES [dbo].[Product] ([id])
    GO
ALTER TABLE [dbo].[Order_Details] CHECK CONSTRAINT [FK_Product]
    GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_User_Account2] FOREIGN KEY([customer_id])
    REFERENCES [dbo].[User_Account] ([id])
    GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_User_Account2]
    GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Category] FOREIGN KEY([category_id])
    REFERENCES [dbo].[Category] ([id])
    GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Category]
    GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_User_Account] FOREIGN KEY([seller_id])
    REFERENCES [dbo].[User_Account] ([id])
    GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_User_Account]
    GO
ALTER TABLE [dbo].[User_Account]  WITH CHECK ADD  CONSTRAINT [FK_Ranking] FOREIGN KEY([rank_id])
    REFERENCES [dbo].[Ranking] ([id])
    GO
ALTER TABLE [dbo].[User_Account] CHECK CONSTRAINT [FK_Ranking]
    GO
ALTER TABLE [dbo].[User_Account]  WITH CHECK ADD  CONSTRAINT [FK_Role] FOREIGN KEY([role_id])
    REFERENCES [dbo].[Role] ([id])
    GO
ALTER TABLE [dbo].[User_Account] CHECK CONSTRAINT [FK_Role]
    GO
