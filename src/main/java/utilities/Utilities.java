package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

public class Utilities {
	
	public static String read_propert_value(String prop_name) {
		String prop_value="";
		try {
			Properties prop=new Properties();
			FileInputStream inputstream=new FileInputStream("./src/test/resources/config.properties");
			prop.load(inputstream);
			prop_value=prop.getProperty(prop_name);
			
		}
	   catch(Exception e) {
		   e.printStackTrace();
	   }
		return prop_value;
	}

	
	@DataProvider(name="testdata")
	public Object[][] read_excel_data(Method m) throws EncryptedDocumentException, IOException {
		String Excel_SheetName=m.getName();
		File f= new File("./src/test/resources/TestData.xlsx");
		FileInputStream fis=new FileInputStream(f);
		Workbook wb=WorkbookFactory.create(fis);
		
		Sheet sheetname=wb.getSheet(Excel_SheetName);
		int total_rows=sheetname.getLastRowNum();
		Row rowCells=sheetname.getRow(0);
		int total_cols=rowCells.getLastCellNum();
		
		DataFormatter format= new DataFormatter();
		String testData[][] =new String[total_rows][total_cols];
		
		for(int i=1;i<=total_rows;i++) {
			for(int j=0;j<total_cols;j++) {
				testData[i-1][j]=format.formatCellValue(sheetname.getRow(i).getCell(j));
			}
		}
		
		return testData;
		
	}

	public static String changeWindowToChild(WebDriver driver) {
		String winHandleBefore="" ;
		try {
		winHandleBefore = driver.getWindowHandle();

		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return winHandleBefore;
	}
	public void changeWindowToParent(String winHandleBefore,WebDriver driver) {
		driver.switchTo().window(winHandleBefore);
	}
	
	public static List<String> getListOfElementText(List<WebElement> elements) {
		List<String> Mylist = new ArrayList<>();
		try {
		Iterator<WebElement> itr = elements.iterator();
		while(itr.hasNext()) {
			Mylist.add(itr.next().getText());
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return Mylist;
	}
	
	public static int convertStringToInt(String stringToConvert) {
		if(stringToConvert.contains(".00")) {
			stringToConvert = stringToConvert.substring(1);
		}
		String MyString=stringToConvert.replace(".00", "").replace("[^a-zA-Z0-9]", "").replace(",", "".replace("?", ""));
		
		return Integer.parseInt(MyString);
		
	}
	

}
