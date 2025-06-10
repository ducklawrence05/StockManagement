USE StockManagement;
GO

CREATE TABLE tblSamples (							-- đổi tên table
    id INT IDENTITY(1,1) PRIMARY KEY,				-- key tự tăng
    string VARCHAR(50) NOT NULL,					-- varchar
    number INT NOT NULL CHECK (number > 0),			-- int >= 0
    numberF FLOAT NOT NULL CHECK (numberF > 0),		-- float >= 0
	numberD DECIMAL(10, 2) NOT NULL CHECK (numberD > 0), -- ko dùng float thì dùng decimal, nào cũng được

	-- FK
    fkId int NOT NULL,							-- Id của table foreign tới,
													-- nhớ đổi lại type để trùng với type của id bảng tham chiếu tới
    CONSTRAINT FK_Samples_AnotherTable			-- đặt tên tham chiếu, ko cần chính xác
		FOREIGN KEY (fkId) REFERENCES AnotherTable(anotherTableId)
);
GO

-- Insert tự ghi theo trong đề nha =)))))