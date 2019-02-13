package genericLibrary;

import java.io.File;

public class CommonUtil {

	public static String getTestExecutionReportFolderPath()
	{
		return System.getProperty("user.dir")+"\\TestExecutionReport";
	}
	
	public static String getTestExecutionReportFilePath()
	{
		return System.getProperty("user.dir")+"\\TestExecutionReport\\Raptor.html";
	}
	public static String getScreenShotFolderPath()
	{
		return System.getProperty("user.dir")+"\\Screenshots";
	}
	
	public static void clearFolder(String folderPath)
	{
		for(File file: new File(folderPath).listFiles()) 
		    if (!file.isDirectory()) 
		    {
		    	file.delete();
		    	AppLogger.log.info("Failed Screenshots are deleted");
		    }
		        
		
	}
	public static String getExcelFilePath()
	{
		return System.getProperty("user.dir")+"/src/test/resources/Test-Cases.xlsx";
	}
	
	public static void createLogFolder()
	{
		String path = "C:\\AutomationLogs";
		try
		{
			if(!(new File(path).exists()))
			{
				new File(path).mkdir();
			}
		}
		catch(Exception EX)
		{
			AppLogger.log.info("Exception occured during createLogFolder due to ==>"+ EX);
		}
	}
	
}