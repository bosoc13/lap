package com.lap.webadmin.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lap.common.entity.Books;
import com.lap.webadmin.dto.BookDto;
import com.lap.webadmin.repository.BooksRepository;

@Service
public class BooksService {
	@Autowired
    private BooksRepository booksRepository;
	
	public void saveBook(Books book) {
		booksRepository.save(book);
	}
	
	public boolean isExist(BookDto bookDto) {
		List<Books> books = booksRepository.findByNameAndCategoryIdAndAuthorIdAndPublishingYear(
				bookDto.getName(), 
				bookDto.getCategoryId(), 
				bookDto.getAuthorId(), 
				bookDto.getPublishingYear());
		return !CollectionUtils.isEmpty(books);
	}
	
	public void importFile(MultipartFile file) {
	    try (InputStream inputstream = file.getInputStream()) {
	    	Workbook workbook = new XSSFWorkbook(inputstream);
	    	Sheet sheet = workbook.getSheetAt(0);
	    	Iterator<Row> rows = sheet.iterator();
	    	List<Books> listBook = new ArrayList<>();
	    	int rowNumber = 0;
	    	Row currentRow;
	    	Books book;
	    	int cellIndex;
	    	while (rows.hasNext()) {
	    		currentRow = rows.next();
	    		// skip header
	            if (rowNumber == 0) {
	              rowNumber++;
	              continue;
	            }

	            cellIndex=0;
	            book = new Books();
                book.setName(currentRow.getCell(cellIndex++).getStringCellValue());
                book.setCategoryId((long) currentRow.getCell(cellIndex++).getNumericCellValue());
                book.setAuthorId((long) currentRow.getCell(cellIndex++).getNumericCellValue());
                book.setPublishingYear(String.valueOf((int)currentRow.getCell(cellIndex++).getNumericCellValue()));
                
                listBook.add(book);
                
                // save 1000 record to DB
                if(listBook.size()==1000) {
                	booksRepository.saveAll(listBook);
                	listBook.clear();
                }
	    	}
	    	
	    	if(listBook.size() > 0) {
	    		booksRepository.saveAll(listBook);
	    	}
	      } catch (IOException e) {
	        throw new RuntimeException("fail to store excel data: " + e.getMessage());
	      }
	    }
	}

	

