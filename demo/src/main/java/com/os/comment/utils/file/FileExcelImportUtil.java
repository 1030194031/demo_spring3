package com.os.comment.utils.file;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;

/**
 * 导入excel处理类
 * @author Administrator
 * 2015-5-14
 */
public class FileExcelImportUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileExcelImportUtil.class);
	// 从excel表中导出的数据处理
	public static String importExcel(MultipartFile myFile){
		String msg="";
		try {
			// datalist拼装List<String[]> datalist,
			HSSFWorkbook wookbook = new HSSFWorkbook(myFile.getInputStream());
			HSSFSheet sheet = wookbook.getSheet("Sheet1");
		
			int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
			for (int i = 1; i <= rows+1; i++) {
				// 读取左上端单元格
				HSSFRow row = sheet.getRow(i);
				// 行不为空
				if (row != null) {
					// **读取cell**
					String nickname=getCellValue(row.getCell((short) 0));//用户昵称
					
					// 获取到Excel文件中的所有的列
//					int maxcell = row.getLastCellNum();
					if (nickname==null || nickname.equals("")) {
						msg+="第"+rows+"行昵称错误<br/>";
						continue;
					}else{
						msg+=nickname+",";
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("FileExcelImportUtil.importExcel---error",e);
		}
		return msg;
	}
	/**
	 * 获得Hsscell内容
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(HSSFCell cell) {
		String value = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_FORMULA:
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				DecimalFormat df = new DecimalFormat("0");    
				value = df.format(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_STRING:
				value = cell.getStringCellValue().trim();
				break;
			default:
				value = "";
				break;
			}
		}
		return value.trim();
	}
}
    
    
  

