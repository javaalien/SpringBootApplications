package com.alien.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alien.model.Order;
import com.alien.repository.OrderRepository;

@Service
public class ReportService {

	@Autowired
	private OrderRepository orderRepository;

	public byte[] generateReport() throws IOException {

		List<Order> orders = orderRepository.findAll();

		SXSSFWorkbook workBook = new SXSSFWorkbook();

		Sheet sheet = workBook.createSheet();

		writeHeaderLine(sheet);

		int rowCount = 1;

		CellStyle style = workBook.createCellStyle();

		XSSFFont font = (XSSFFont) workBook.createFont();

		font.setFontHeight(14);

		style.setFont(font);

		for (Order order : orders) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, order.getId(), style);
			createCell(row, columnCount++, order.getName(), style);
			createCell(row, columnCount++, order.getQty(), style);
			createCell(row, columnCount++, order.getPrice(), style);

		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			workBook.write(bos);
		} finally {
			bos.close();
		}
		return bos.toByteArray();

	}

	private void writeHeaderLine(Sheet sheet) {

		Row headerRow = sheet.createRow(0);
		Cell headerCell = headerRow.createCell(0);
		headerCell.setCellValue("ORDER_ID");

		headerCell = headerRow.createCell(1);
		headerCell.setCellValue("ORDER_NAME");

		headerCell = headerRow.createCell(2);
		headerCell.setCellValue("QUANTITY");

		headerCell = headerRow.createCell(3);
		headerCell.setCellValue("PRICE");
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		Cell cell = row.createCell(columnCount);

		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Double) {
			cell.setCellValue((Double) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

}
