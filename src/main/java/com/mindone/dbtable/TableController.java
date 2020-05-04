package com.mindone.dbtable;

import java.awt.Font;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mindone.dbtable.TableRepository;


@Controller
public class TableController {

	@Autowired
	TableRepository tableRepository;
	
	@RequestMapping("/tablelayout")
	public String tablelayout(ModelMap model,HttpServletResponse response) throws Exception {
		return "tablelayout";
	}
	
	@RequestMapping("/exceltablelayout")
	public void exceltablelayout(ModelMap model,HttpServletResponse response) throws Exception {
		XSSFWorkbook workbook = null;
		String fileName = "tablelayout.xlsx";
		
		try {
			
			List<Map<String,Object>> columnList = tableRepository.tablelayout();
			System.out.println("size : " + columnList.size());
			// 엑셀 생성
			workbook = makeExcelWorkbook(columnList);
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			
			workbook.write(response.getOutputStream());	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public XSSFWorkbook makeExcelWorkbook(List<Map<String,Object>> list) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("테이블");
        String bfTableNm = ""; 
        int row = 0;
       
        if (list.size() == 0) return null;
                
        XSSFFont Black11 = workbook.createFont();
        Black11.setFontName("맑은 고딕"); //글씨체
        Black11.setFontHeight((short)(9*20)); //사이즈

		//셀 스타일 및 폰트 설정
		CellStyle cellStyleHeader = workbook.createCellStyle();
		//폰트 설정
		cellStyleHeader.setFont(Black11);
		
		cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
		cellStyleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyleHeader.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		cellStyleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		//테두리 선 (우,좌,위,아래)
		cellStyleHeader.setBorderRight(BorderStyle.THIN);
		cellStyleHeader.setBorderLeft(BorderStyle.THIN);
		cellStyleHeader.setBorderTop(BorderStyle.THIN);
		cellStyleHeader.setBorderBottom(BorderStyle.THIN);
		
        //셀 스타일 및 폰트 설정
        CellStyle cellStyleLeft = workbook.createCellStyle();
        //폰트 설정
        cellStyleLeft.setFont(Black11);
 
        cellStyleLeft.setAlignment(HorizontalAlignment.LEFT);
		cellStyleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyleLeft.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		//cellStyleLeft.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		//테두리 선 (우,좌,위,아래)
		cellStyleLeft.setBorderRight(BorderStyle.THIN);
		cellStyleLeft.setBorderLeft(BorderStyle.THIN);
		cellStyleLeft.setBorderTop(BorderStyle.THIN);
		cellStyleLeft.setBorderBottom(BorderStyle.THIN);

		//셀 스타일 및 폰트 설정
		CellStyle cellStyleCenter = workbook.createCellStyle();
		//폰트 설정
		cellStyleCenter.setFont(Black11);
		
		cellStyleCenter.setAlignment(HorizontalAlignment.CENTER);
		cellStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyleCenter.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		//cellStyleCenter.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		//테두리 선 (우,좌,위,아래)
		cellStyleCenter.setBorderRight(BorderStyle.THIN);
		cellStyleCenter.setBorderLeft(BorderStyle.THIN);
		cellStyleCenter.setBorderTop(BorderStyle.THIN);
		cellStyleCenter.setBorderBottom(BorderStyle.THIN);
		
        for(int i=0; i<list.size(); i++) {
            Map<String,Object> map = list.get(i);
            String tableNm = map.get("TABLE_NAME").toString();
            
            if (!tableNm.equals(bfTableNm)) {
            	// 공백줄
            	Row spaceRow = sheet.createRow(row);  
            	row++;
            	
		        // 테이블명 행
		        Row tableNmRow = sheet.createRow(row);
		        
		        Cell tableNmCell = tableNmRow.createCell(0);
		        tableNmCell.setCellValue("테이블명");
		        tableNmCell.setCellStyle(cellStyleHeader);
		        tableNmCell = tableNmRow.createCell(1);
		        tableNmCell.setCellValue(tableNm);
		        tableNmCell.setCellStyle(cellStyleLeft);
		        tableNmCell = tableNmRow.createCell(2);
		        tableNmCell.setCellValue("");
		        tableNmCell.setCellStyle(cellStyleLeft);
		        tableNmCell = tableNmRow.createCell(3);
		        tableNmCell.setCellValue("");
		        tableNmCell.setCellStyle(cellStyleLeft);
		        tableNmCell = tableNmRow.createCell(4);
		        tableNmCell.setCellValue("");
		        tableNmCell.setCellStyle(cellStyleLeft);
		        tableNmCell = tableNmRow.createCell(5);
		        tableNmCell.setCellValue("");
		        tableNmCell.setCellStyle(cellStyleLeft);
		        //셀 병합
		        sheet.addMergedRegion(new CellRangeAddress(row,row,1,5)); //열시작, 열종료, 행시작, 행종료 (자바배열과 같이 0부터 시작)
		        row++;
		        
		        // 테이블설명 행
		        Row tableCommentRow = sheet.createRow(row);
		        
		        Cell tableCommentCell = tableCommentRow.createCell(0);
		        tableCommentCell.setCellValue("설명");
		        tableCommentCell.setCellStyle(cellStyleHeader);
		        tableCommentCell = tableCommentRow.createCell(1);
		        tableCommentCell.setCellValue((String)map.get("T_COMMENT"));
		        tableCommentCell.setCellStyle(cellStyleLeft);
		        tableCommentCell = tableCommentRow.createCell(2);
		        tableCommentCell.setCellValue((String)map.get("T_COMMENT"));
		        tableCommentCell.setCellStyle(cellStyleLeft);
		        tableCommentCell = tableCommentRow.createCell(3);
		        tableCommentCell.setCellValue((String)map.get("T_COMMENT"));
		        tableCommentCell.setCellStyle(cellStyleLeft);
		        tableCommentCell = tableCommentRow.createCell(4);
		        tableCommentCell.setCellValue((String)map.get("T_COMMENT"));
		        tableCommentCell.setCellStyle(cellStyleLeft);
		        tableCommentCell = tableCommentRow.createCell(5);
		        tableCommentCell.setCellValue((String)map.get("T_COMMENT"));
		        tableCommentCell.setCellStyle(cellStyleLeft);
		        tableCommentCell = tableCommentRow.createCell(1);
		        tableCommentCell.setCellValue((String)map.get("T_COMMENT"));
		        tableCommentCell.setCellStyle(cellStyleLeft);
		        //셀 병합
		        sheet.addMergedRegion(new CellRangeAddress(row,row,1,5)); 
		        row++;
		        
		        // 헤더 행
		        Row headerRow = sheet.createRow(row);
		        
		        Cell headerCell = headerRow.createCell(0);
		        headerCell.setCellValue("컬럼명");
		        headerCell.setCellStyle(cellStyleHeader);
		        headerCell = headerRow.createCell(1);
		        headerCell.setCellValue("설명");
		        headerCell.setCellStyle(cellStyleHeader);
		        headerCell = headerRow.createCell(2);
		        headerCell.setCellValue("데이터타입");
		        headerCell.setCellStyle(cellStyleHeader);
		        headerCell = headerRow.createCell(3);
		        headerCell.setCellValue("NULL");
		        headerCell.setCellStyle(cellStyleHeader);
		        headerCell = headerRow.createCell(4);
		        headerCell.setCellValue("Key");
		        headerCell.setCellStyle(cellStyleHeader);
		        headerCell = headerRow.createCell(5);
		        headerCell.setCellValue("Default");
		        headerCell.setCellStyle(cellStyleHeader);
		        row++;

		        bfTableNm = tableNm;
            }
            
      	    // 과일표 내용 행 및 셀 생성
	        Row bodyRow = null;
	        Cell bodyCell = null;
            
            // 행 생성
            bodyRow = sheet.createRow(row);
            
            bodyCell = bodyRow.createCell(0);
            bodyCell.setCellValue(map.get("COLUMN_NAME").toString());
            bodyCell.setCellStyle(cellStyleLeft);
            bodyCell = bodyRow.createCell(1);
            bodyCell.setCellValue((String)map.get("C_COMMENT"));
            bodyCell.setCellStyle(cellStyleLeft);
            bodyCell = bodyRow.createCell(2);
            bodyCell.setCellValue(map.get("DATATYPE").toString());
            bodyCell.setCellStyle(cellStyleCenter);
            bodyCell = bodyRow.createCell(3);
            bodyCell.setCellValue(map.get("NULLABLE").toString());
            bodyCell.setCellStyle(cellStyleCenter);
            bodyCell = bodyRow.createCell(4);
            bodyCell.setCellValue((String)map.get("IS_PK"));
            bodyCell.setCellStyle(cellStyleCenter);
            bodyCell = bodyRow.createCell(5);
            bodyCell.setCellValue((String)map.get("DATA_DEFAULT"));
            bodyCell.setCellStyle(cellStyleCenter);
	        row++;
        }
        
        for (int j = 0; j <  row; j++){
        	sheet.autoSizeColumn(j);
        	sheet.setColumnWidth(j, (sheet.getColumnWidth(j)) + 1000);
        	Row thisRow = sheet.getRow(j);
        	thisRow.setHeight((short)264); //1000 이 행높이 50
        }
        
        return workbook;
    }
    
}
