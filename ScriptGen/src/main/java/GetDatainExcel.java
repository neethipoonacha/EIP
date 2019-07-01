import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetDatainExcel {

	public static void main(String[] args) {
		String prefLang = "fr_BE";
		//List keyList =readFromExcel();
		//Map<String, Object[]> datatoWrite = getData(keyList,prefLang);
		//writeToExcel(datatoWrite,prefLang);

		String key = null;
		Map<String, Object[]> writeData = new HashMap<String, Object[]>();
		for(int i =1;i<=94;i++) {
			key= readFromExcel(i);
			writeData = getData(key,prefLang,writeData);
			
		}
		writeToExcel(writeData,prefLang);
	}
	
	private static Map<String, Object[]> getData(String key,String prefLang,Map<String, Object[]> writeData) {

		//Map<String, Object[]> writeData = new HashMap<String, Object[]>();
		try {
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection ( "*******", "*******","*******");
			Statement stm = con.createStatement();

			List keysExists = new ArrayList<String>();
			List keysNotExists = new ArrayList<String>();

			Map<String, Object[]> data = new TreeMap<String, Object[]>();

			String strQuery1 = "SELECT a.key as g_key ,b.translation as english, a.translation as german \n" + 
					"from translation_locale a\n" + 
					"join translation_locale b on a.key = b.key \n" + 
					"where (b.key in ( " ;
			String strQuery2 = "'))\n" + 
					"and b.lang_cd='en_US'\n" + 
					"and a.lang_cd = '"+prefLang+"'";
			StringBuffer keyString = new StringBuffer();

			keyString.append("'"+key+"");

			String finalQuery = strQuery1+keyString+strQuery2;
			System.out.println("finalQuery is "+finalQuery);

			ResultSet rs;
			rs = stm.executeQuery(finalQuery);
			//writeData.put("KEY", new Object[] {"KEY", "ENGLISH", prefLang});
			if(rs.next()) {
				writeData.put(rs.getString("g_key"), new Object[] {rs.getString("g_key"), rs.getString("english"), rs.getString("german")});
			}else {
				String strQueryEng = "SELECT a.key as g_key ,a.translation as english\n" + 
						"FROM translation_locale a\n" + 
						"WHERE KEY = '"+key+"'\n" + 
								" and lang_Cd = 'en_US'";
				ResultSet rs1;
				rs1 = stm.executeQuery(strQueryEng);
				if(rs1.next()) {
				writeData.put(key, new Object[] {rs1.getString("g_key"), rs1.getString("english"), "  "});
				}
			}

			
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		return writeData;


	}

	private static Map<String, Object[]> getData(List keys,String prefLang) {
		Map<String, Object[]> writeData = new HashMap<String, Object[]>();
		try {
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection ( "jdbc:oracle:thin:@evek.corp.apple.com:1799:merlin8t", "mypage_user","allowintwomerlintwotapple");
			Statement stm = con.createStatement();

			List keysExists = new ArrayList<String>();
			List keysNotExists = new ArrayList<String>();

			Map<String, Object[]> data = new TreeMap<String, Object[]>();

			String strQuery1 = "SELECT a.key as g_key ,b.translation as english, a.translation as german \n" + 
					"from translation_locale a\n" + 
					"join translation_locale b on a.key = b.key \n" + 
					"where (b.key in ( " ;
			String strQuery2 = "'))\n" + 
					"and b.lang_cd='en_US'\n" + 
					"and a.lang_cd = '"+prefLang+"'";
			StringBuffer keyString = new StringBuffer();

			for(int i=0;i<keys.size();i++) {
				if(i==keys.size()-1) {
					keyString.append("'"+keys.get(i)+"");
				}else {
					keyString.append("'"+keys.get(i)+"',");
				}
			}

			String finalQuery = strQuery1+keyString+strQuery2;
			System.out.println("finalQuery is "+finalQuery);

			ResultSet rs;
			rs = stm.executeQuery(finalQuery);
			//writeData.put("KEY", new Object[] {"KEY", "ENGLISH", prefLang});
			while(rs.next()) {
				writeData.put(rs.getString("g_key"), new Object[] {rs.getString("g_key"), rs.getString("english"), rs.getString("german")});
			}

			for(Object[] mp : writeData.values()) {
				for(int i =0;i<mp.length;i++) {
					System.out.println(mp[i]);
				}
			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		return writeData;
	}

	private static List readFromExcel() {
		List keyList = new ArrayList();
		try
		{
			FileInputStream file = new FileInputStream(new File("******/ALL_Locales/en_US.xlsx"));

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFCell enuKeys1;
			for(int i =13;i<=22;i++) {
				//System.out.println("At 9th index"+workbook.getSheetAt(0).getRow(i).getCell(0)); 
				enuKeys1 = workbook.getSheetAt(0).getRow(i).getCell(0);
				keyList.add(enuKeys1.getStringCellValue());
			}
			file.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return keyList;
	}
	
	private static String readFromExcel(Integer num) {
		String keyList = new String();
		XSSFCell enuKeys1 = null;
		try
		{
			FileInputStream file = new FileInputStream(new File("*******/ALL_Locales/en_US.xlsx"));

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);


			//System.out.println("At 9th index"+workbook.getSheetAt(0).getRow(i).getCell(0)); 
			enuKeys1 = workbook.getSheetAt(0).getRow(num).getCell(1);
			//keyList.add(enuKeys1.getStringCellValue());
			file.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return enuKeys1.getStringCellValue();
	}

	private static void writeToExcel(Map<String, Object[]> datatoWrite,String prefLang) {

		//Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		//Create a blank sheet
		XSSFSheet sheet = workbook.createSheet(prefLang);

		//This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		//data.put("1", new Object[] {"KEY", "ENGLISH", prefLang});
		for (Map.Entry<String,Object[]> entry : datatoWrite.entrySet()) {
			data.put(entry.getKey(),entry.getValue());
		}

		//Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset)
		{
			Row row = sheet.createRow(rownum++);
			Object [] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr)
			{
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Integer)
					cell.setCellValue((Integer)obj);
			}
		}
		try
		{
			//Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("******/ALL_Locales/"+prefLang+".xlsx"));
			workbook.write(out);
			out.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
         

