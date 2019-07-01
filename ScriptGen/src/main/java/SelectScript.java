import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class SelectScript {


	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		
		try {
			File fileName1 = new File("*******/ScriptGen/src/main/resources/keys.properties");
			String prefLang = "zh_CN";
			
			FileInputStream fileInput1 = new FileInputStream(fileName1);
			Properties properties = new Properties();
			properties.load(fileInput1);
			fileInput1.close();
			String value = null;
			Enumeration enuKeys1 = properties.keys();
			
			File file = new File("*******/src/main/resources/genprop/locale_" +prefLang+ ".txt");
			
			//get Connection to merlind 
			 DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection ( "*******", "*******","*******");
			Statement stm = con.createStatement();
			
			List keysExists = new ArrayList<String>();
			List keysNotExists = new ArrayList<String>();
			
			
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			
			//FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
			bw.write("SET DEFINE OFF;");
			
			while (enuKeys1.hasMoreElements()) {
				String key = (String) enuKeys1.nextElement();
				
				if(key!=null ){

					//value = properties.getProperty(key);
					
					String strQuery = "select key ,translation from translation_locale where key = " +" '"+ key.trim() + "' and lang_cd = '" +prefLang + "'" ;
					//System.out.println(strQuery);
					String valuekey = null;
					ResultSet rs = stm.executeQuery(strQuery);
					bw.newLine();
					if (rs.next()) {
						//System.out.println("DB value exists " +rs.getString(1) +" for the property file Key:" +key);
						//System.out.println("Key is "+rs.getString("key"));
						//System.out.println("Value is "+rs.getString("translation"));
						try{
							valuekey = rs.getString("key")+"                      "+rs.getString("translation");
							bw.write(valuekey);
							}catch (Exception e){
								System.out.println("Exception for the key: "+key);
							}
						keysExists.add(key);
					}else {
						System.out.println("DB value doesn't for the properties Key:" +key);
						try{
						valuekey = key+"                      "+"    ";
						bw.write(valuekey);
						}catch (Exception e){
							System.out.println("Exception for the key: "+key);
						}
						 keysNotExists.add(key);
					}
					
				
				}
			}
			bw.close();
			System.out.println("Count details keysExists " + keysExists.size() + " DoesNotExists " +keysNotExists.size());
		} catch (Exception e) {
        e.printStackTrace();
		}

	}

	



}
