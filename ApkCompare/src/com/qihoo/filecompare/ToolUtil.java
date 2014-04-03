package com.qihoo.filecompare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.channels.Pipe;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import com.qihoo.filecompare.*;

public class ToolUtil {
//	public static String byCompare = "D:\\Program Files\\Beyond Compare 3";


	public static double getFileSize(File file) throws Exception{//取得文件大小
		   double size=0;
		         if (file.exists()) {
		             FileInputStream fis = null;
		             fis = new FileInputStream(file);
		             size= fis.available();
		         } else {
		           return 0;
		         }
		         size = size/1024.00;
		         size=Double.parseDouble(size+"");
		         BigDecimal b = new BigDecimal(size); 
		         double y1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
		         java.text.DecimalFormat df =new java.text.DecimalFormat("#.00"); 
		         return Double.parseDouble(df.format(y1));
		     }

	
	
	/**
	 * 
	 * @param apk路径
	 * @return 转变后的zip名字
	 * @throws IOException
	 */
	public static String RenameFile(String filename) throws IOException{
		
			File file1 = new File(filename);
	
			System.out.println("原APK名称： " + filename);
			
			String[] sarr = filename.split("\\.");
		
			int i = sarr.length;
		
//			for(int a = 0; a < i; a++){
//				System.out.println(sarr[a]);
//			}
//			
//			System.out.println(sarr[i-1]);
			

			if(sarr[i-1].matches("apk")){
				filename = filename.replace("apk", "zip");
				System.out.println("修改后的apk名称" + filename);
				file1.renameTo(new File(filename));
//				File file2 = new File(filename);
//				return file2;
			}
			
			return filename;
	}
	
	
	/**
	 * 
	 * @param zipfile = 解压前的zip
	 * @param destDir = 解压路径
	 * @throws Exception
	 */
	public static void unZip(String zipfile, String destDir) throws Exception{
//		destDir = destDir.endsWith("\\") ? destDir : destDir + "\\";
		try {

			File zipFile = new File(zipfile);
			InputStream is = new FileInputStream(zipFile);
			ZipInputStream zis = new ZipInputStream(is);
			ZipEntry entry = null;
			System.out.println("开始解压:" + zipFile.getName() + "...");
			while ((entry = zis.getNextEntry()) != null) {
			String zipPath = entry.getName();
			try {
			if (entry.isDirectory()) {
			File zipFolder = new File(destDir + File.separator + zipPath);
			if (!zipFolder.exists()) {
			zipFolder.mkdirs();
			}
			} else {
			File file = new File(destDir + File.separator
			+ zipPath);
			if (!file.exists()) {
			File pathDir = file.getParentFile();
			pathDir.mkdirs();
			file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			int bread;
			while ((bread = zis.read()) != -1) {
			fos.write(bread);
			}
			fos.close();
			}
			System.out.println("成功解压"+ zipfile + ":" + zipPath);
			} catch (Exception e) {
			System.out.println("解压" + zipPath + "失败");
			continue;
			}
			}
			zis.close();
			is.close();
			System.out.println("解压结束");
			} catch (Exception e) {
			e.printStackTrace();
			}

//		byte b[] = new byte[512];
//		int length;     
//		ZipFile zipFile;
//		String encoding="utf-8";
//		try {
//			zipFile = new ZipFile(new File(zipfile));
//			Enumeration<?> enumeration = zipFile.entries();
//			ZipEntry zipEntry = null;
//			while (enumeration.hasMoreElements()) {
//				zipEntry = (ZipEntry) enumeration.nextElement();
//				File loadFile = new File(destDir + zipEntry.getName());
//				if (zipEntry.isDirectory()) {
//					loadFile.mkdirs();
//				} else {
//					if (!loadFile.getParentFile().exists())
//						loadFile.getParentFile().mkdirs();
//					OutputStream outputStream = new FileOutputStream(loadFile);
//					InputStream inputStream = zipFile.getInputStream(zipEntry);
//					while ((length = inputStream.read(b)) > 0){
//						outputStream.write(b,0,length);
//					}		
//				}
//			}
//			
//			return true;
//		} catch (IOException e) {
//			throw new Exception("文件不存在！",e);
//		}
	}
	
	
	public static void deleteExistFile(String oldfolder,String newFolder,String reportpath){
		File Existedreport = new File(reportpath);
		if (Existedreport.isFile() && Existedreport.exists()) {
			Existedreport.delete();
			System.out.println("txt报告已清理--------------");
		}		
		
		delAllPath(oldfolder);
		System.out.println("old文件夹已清理");
		delAllPath(newFolder);
		System.out.println("new文件夹已清理");
		
	} 
	
	private static void delAllPath(String folder) {
		File Folder = new File(folder);
		if (Folder.exists()) {
			if (!Folder.isDirectory()) {
				Folder.delete();
			}
			else{
				String[] tempList = Folder.list();
				for(String f : tempList){
					String fs = folder + "\\" + f; 
					File tf = new File(fs);
					if (!tf.isDirectory()) {
						tf.delete();
					}
					else {
						delAllPath(fs);
					}
				}				
			}
			Folder.delete(); //删除空文件夹
		}
		else {
			System.out.println("文件夹不存在，无需清理。");
		}
		
		
		}
				



	public static void runCommend() throws IOException, InterruptedException{
		
//		try {  
//            ProcessBuilder builder = new ProcessBuilder();  
//  
//            ProcessBuilder newBuilder = builder.command("BCompare.exe", "@D:\\scripttxt.txt");  
//  
//            builder.redirectErrorStream(true);  
//  
//            // 创建进程, 执行发布任务  
//            Process process = newBuilder.start();  
//  
//            InputStream inSTest = process.getInputStream();  
//            InputStreamReader reader = new InputStreamReader(inSTest);  
//            BufferedReader bfReader = new BufferedReader(reader);  
//  
//            String strLine = "";  
//            while ((strLine = bfReader.readLine()) != null) {  
//                System.out.println(strLine);  
//                // log.info(strLine + "\r\n");  
//            }  
//  
//            bfReader.close();  
//            process.waitFor();  
//            process.destroy();  
//   
//  
//        } catch (Exception e) {  
//  
//            e.printStackTrace();  
//  
//        }  
     
		ProcessBuilder pb = new ProcessBuilder("BCompare.exe", "@" + PropertiesManager.getInstance().getProperty("BC_SCRIPT_PATH"));   
        // 让这个进程的工作区空间改为F:\dist   
        // 这样的话,它就会去F:\dist目录下找Test.jar这个文件   
        pb.directory(new File(PropertiesManager.getInstance().getProperty("BC_PATH")));   
        // 得到进程生成器的环境 变量,这个变量我们可以改,   
        // 改了以后也会反应到新起的进程里面去   
        Map<String, String> map = pb.environment();   
        Process p1 = pb.start();   
        
        p1.waitFor();
        p1.destroy();
//         然后就可以对p做自己想做的事情了   
//         自己这个时候就可以退出了   
		
//		String command = "cmd /c start D:\\compare.bat";
//		try {
//			Process child = Runtime.getRuntime().exec(command);
//			InputStream in = child.getInputStream();
//			int c;
//			while ((c = in.read()) != -1) {				
////				System.out.print(c);
//			}
//			in.close();
//			try {
//				child.waitFor();
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("done");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
	}
	
//	public static void main(String[] args) throws Exception {
////		delAllPath("D:\\compare\\new");
//		unZip("D:\\compare\\MobileAV_1.0.0.1108.zip", "D:\\compare\\old");
////		runCommend();
//		System.out.println("成功");
//	}
	
}
