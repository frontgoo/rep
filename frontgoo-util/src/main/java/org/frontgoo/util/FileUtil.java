package org.frontgoo.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.helper.StringUtil;

/**
 * file handles class
 * @author frontgoo
 *
 */
public class FileUtil {
	
	/**
	 * 指定路径生成文件并写入内容
	 * 
	 * @param path
	 * @param fileName
	 * @param content
	 */
	public static void createFileInPathAndWrite(String path,String fileName,String content){
		  File file = new File(path);
	  		if (!file.exists()) {
					file.mkdirs();
				}
	  		File file2 = new File(path+"\\"+fileName);
	  		FileWriter fileWriter = null;
			try {
				if (!file2.exists()) {
		  			file2.createNewFile();
					}
				fileWriter = new FileWriter(file2);
				fileWriter.write(content);
				fileWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if (null!=fileWriter) {
					try {
						fileWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	}
	
	private final static Log log = LogFactory.getLog(FileUtil.class);
	private final static byte[] UTF_8_BOM ={(byte) 0xEF,(byte) 0xBB,(byte) 0xBF};
    /**
     * 删除指定文件或目录
     * 
     * @param file
     * 		需要被删除的文件
     * @return
     * 		0成功,非0失败
     */
    public static int deleteFile(File file) {
    	if(file == null) {
    		return -1;
    	}
		if(file.exists()) {
			if(file.isFile()) {
				file.delete();
			}
			if(file.isDirectory()) {
				File[] subFiles = file.listFiles();
				if(subFiles != null) {
					for(int i=0;i<subFiles.length;i++) {
						deleteFile(subFiles[i]);
					}
				}
				file.delete();
			}
			return 0;
		}else {
			return -1;
		}
	}
    
    /**
     * 文件拷贝
     * @param src
     * @param tar
     */
    public static void fileCopy(File srcFile, File tarFile) throws Exception{
    	FileInputStream is = null;
    	FileOutputStream os = null;
    	try {
    		is = new FileInputStream(srcFile);
    		os = new FileOutputStream(tarFile);
    		// write the file to the file specified
    		int bytesRead = 0;
    		byte[] buffer = new byte[8192];
    		while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
    			os.write(buffer, 0, bytesRead);
    		}
    	} catch (FileNotFoundException fnfe) {
    		throw fnfe;
    	} catch (IOException ioe) {
    		throw ioe;
    	} finally {
    		try {
    			if (is != null)
    				is.close();
    		} catch (Exception e) {
    			throw e;
    		}
    		try {
    			if (os != null)
    				os.close();
    		} catch (Exception e) {
    			throw e;
    		}
    	}
    }
    
    /**
     * 将字符串写入到文件中，
     * @param filePath      文件路径+文件名
     * @param fileContent   文件内容
     * @param encoding      字符串编码格式 默认系统编码
     * @throws Exception
     */
	public static void writeFileByString(String filePath, String fileContent,
			String encoding) {
		FileUtil.writeFileByString(filePath, fileContent, encoding, false);
	}

	
	/**
	 * 将字符串写入到文件中，
	 * 
	 * @param filePath
	 *            文件路径+文件名
	 * @param fileContent
	 *            文件内容
	 * @param encoding
	 *            字符串编码格式 默认系统编码
	 * @param append
	 *            如果为true表示将fileContent中的内容添加到文件file末尾处
	 * @throws Exception
	 * 
	 * @author duguocheng
	 */
	public static void writeFileByString(String filePath, String fileContent,
			String encoding, boolean append) {
		PrintWriter out = null;
		try {
			if (filePath == null || fileContent == null
					|| fileContent.length() <= 0) {
				log.error("into writeFileByString [filePath=" + filePath
						+ ",filePath=" + fileContent + "] is null return!!!");
				return;
			}

			if (append) {
				File tempFile = new File(filePath);
				if (!tempFile.exists()) {
					tempFile.getParentFile().mkdirs();
					tempFile.createNewFile();
				}
			} else
				createNewFile(new File(filePath));

			if (encoding == null || encoding.trim().length() <= 0) {
				out = new PrintWriter(new FileWriter(filePath));
			} else {
				out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(filePath,
								append), encoding)), true);
			}
			out.print(fileContent);
			out.close();
		} catch (Exception e) {
			log.error("writeFileByString Exception:" + e, e);
		} finally {
			if (out != null)
				out.close();
		}
	}
	
	public static String loadAFileToString(File f){     
	    InputStream is = null;   
	    String ret = null;   
	    try {   
	        is = new BufferedInputStream( new FileInputStream(f) );   
	        long contentLength = f.length();   
	        ByteArrayOutputStream outstream = new ByteArrayOutputStream( contentLength > 0 ? (int) contentLength : 1024);   
	        byte[] buffer = new byte[4096];   
	        int len;   
	        while ((len = is.read(buffer)) > 0) {   
	            outstream.write(buffer, 0, len);   
	        }               
	        outstream.close();   
	        ret = new String(outstream.toByteArray(), "utf-8"); 
	    }catch(IOException e){
	    	ret="";
	    } finally {   
	        if(is!=null) {try{is.close();} catch(Exception e){} }   
	    }   
	    return ret;           
	}  
	
	public static String loadAFileToString2(File f) {
		InputStream is = null;
		String ret = "";
		if (f.exists()) {
			BufferedReader br = null;
			try {
				String line;
				is = new FileInputStream(f);
				InputStreamReader read = new InputStreamReader(is, "utf-8");
				br = new BufferedReader(read);
				while ((line = br.readLine()) != null) {
					ret += line + "\r\n";
				}
			} catch (Exception e) {
				ret = "";
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (Exception e) {
					}
				}
			}
		}
		return ret;
	}
	/**
	 * 创建文件，如果存在，删除后，新建
	 * @param f
	 * @throws IOException
	 */
	public static void createNewFile(File f)throws IOException{
		if(f.exists()){
			f.delete();
		}
		f.getParentFile().mkdirs();
		f.createNewFile();
	}	
	
	public static boolean checkFileExists(String fileName) {
		File file = new File(fileName);
		return file.exists();
	}
	
	
	public static void checkDirExists(String dirPath) {
		File dirFile = new File(dirPath);
		if (!dirFile.isDirectory()) {
			dirFile.mkdirs();
		}
	}
	
	
	/**
	 * 取得文件大小单位M
	 * @param f
	 * @throws IOException
	 */
	public static double getFileSize(File f)throws IOException{
		FileInputStream fis =null;
		try {
			 fis = new FileInputStream(f);
			 BigDecimal b = new BigDecimal(Integer.toString(fis.available()));
			 BigDecimal s = new BigDecimal("1048576");
			 return b.divide(s).doubleValue();
		}catch(Exception e) {
			
		}finally{
			if(fis != null)
				fis.close();
		}
		return 0;
	}
	
	/**
	 * 判断文件是否为UTF-8格式
	 * @param f
	 * @return
	 */
	public static boolean isUTF8(File f){
		boolean isUtf8 =false;
		try {
			FileInputStream fis = new FileInputStream(f);			
			byte[] bom = new byte[3];
			fis.read(bom);
			fis.close();
			if(null != bom && bom.length>2
					&&bom[0]==UTF_8_BOM[0]
					&&bom[1]==UTF_8_BOM[1]
					&&bom[2]==UTF_8_BOM[2]){
				isUtf8= true;				
			}
		} catch (Exception e) {			
			return false;
		}	
		return isUtf8;
	}
	
    /**
	 * 把一个文件A追加到另一个文件B中，
	 * @param sorceFile 文件A
	 * @param despFile  文件B
	 */
	public static void writeFileByFileWithUtf(File sorceFile, File despFile) {
		long sourceLength = sorceFile.length();
		if (sourceLength == 0) {
			return;
		}

		InputStream is = null;
		FileOutputStream fos = null;

		byte[] bytes = new byte[1024 * 4];
		
		int numRead = 0;
		try {
			double fileSize = FileUtil.getFileSize(despFile);
			
			is = new FileInputStream(sorceFile);
			fos = new FileOutputStream(despFile, true);
			
			if (fileSize == 0) {
				byte[] bom = new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
				fos.write(bom);
			}

			while ((numRead = is.read(bytes, 0, 1024 * 4)) >= 0) {
				if (numRead < 1024 * 4) {
					String str = new String(bytes);
					String pattern = "\\x00+$";
					Pattern p = Pattern.compile(pattern);  
					Matcher m = p.matcher(str);
					str = m.replaceAll("");
					bytes = str.getBytes();
				}
				
				fos.write(bytes, 0, bytes.length);
				bytes = new byte[1024 * 4];
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 将字符串content的内容追加到文件filePath的最后，如果文件不存在，先创建新文件
	 * 追加文件：在构造FileOutputStream时，把第二个参数设为true
	 * 编码：UTF-8
	 * 
	 * @param filePath	文件路径+文件名
	 * @param content	内容
	 */
	public static boolean writeFileAppend(String filePath, String content) {
		boolean res = true;
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePath, true), "UTF-8"));
			out.write(content);
		} catch (Exception e) {
			log.error("", e);
			res = false;
		} 
		try {
			out.close();
		} catch (IOException e) {
			log.error("", e);
		}
		return res;
	}
	
	/**
	 * 判断参数是不是文件头标识
	 * @param content
	 * @return
	 */
	public static boolean isFileHead(String content){
		byte[] b = content.trim().getBytes();
		if(b.length!=3) return false;
		if(b[0]!=-17) return false;
		if(b[1]!=-69) return false;
		if(b[2]!=-65) return false;
		return true;
	}
	

	/**
	 * 把一个文件A追加到另一个文件B中，
	 * 
	 * @param sorceFile
	 *            文件A
	 * @param despFile
	 *            文件B
	 * @throws IOException
	 */
	public static void appendTextToFile(String fileName, String text)
			throws IOException {
		if (text.length() == 0) {
			return;
		}
		File despFile = new File(fileName);
		if (!checkFileExists(fileName)) {
			File f = new File(fileName);
			createNewFile(f);
		}
		InputStream is = null;
		FileOutputStream fos = null;

		byte[] bytes = new byte[1024 * 4];

		int numRead = 0;
		try {
			double fileSize = FileUtil.getFileSize(despFile);

			is = new ByteArrayInputStream(text.getBytes());
			fos = new FileOutputStream(despFile, true);

			if (fileSize == 0) {
				byte[] bom = new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
				fos.write(bom);
			}

			while ((numRead = is.read(bytes, 0, 1024 * 4)) >= 0) {
				if (numRead < 1024 * 4) {
					String str = new String(bytes);
					String pattern = "\\x00+$";
					Pattern p = Pattern.compile(pattern);
					Matcher m = p.matcher(str);
					str = m.replaceAll("");
					bytes = str.getBytes();
				}

				fos.write(bytes, 0, bytes.length);
				bytes = new byte[1024 * 4];
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	
	public static String loadFileToStr(File f) throws FileNotFoundException {
		InputStream is = null;
		String ret = "";
		if (f.exists()) {
			BufferedReader br = null;
			try {
				String line;
				is = new FileInputStream(f);
				InputStreamReader read = new InputStreamReader(is, "utf-8");
				br = new BufferedReader(read);
				while ((line = br.readLine()) != null) {
					ret += line + "\r\n";
					log.debug(ret);
				}
			} catch (Exception e) {
				ret = "";
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (Exception e) {
					}
				}
			}
		}
		return ret;
	}

	/**
	 * 将字符串写入到文件中，
	 * 
	 * @param filePath
	 *            文件路径+文件名
	 * @param fileContent
	 *            文件内容
	 * @param encoding
	 *            字符串编码格式 默认系统编码
	 * @param append
	 *            如果为true表示将fileContent中的内容添加到文件file末尾处
	 * @throws Exception
	 * 
	 * @author duguocheng
	 * @throws Exception 
	 */
	public static void writeFileToString(String filePath, String fileContent,String encoding, boolean append) throws Exception {
		PrintWriter out = null;
		try {
			if (filePath == null || fileContent == null
					|| fileContent.length() <= 0) {
				log.error("into writeFileByString [filePath=" + filePath
						+ ",filePath=" + fileContent + "] is null return!!!");
				return;
			}

			File tempFile = new File(filePath);
			if (!tempFile.exists()) {
				tempFile.getParentFile().mkdirs();
				tempFile.createNewFile();
			}

			if (encoding == null || encoding.trim().length() <= 0) {
				out = new PrintWriter(new FileWriter(filePath));
			} else {
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath,append), encoding)), true);
			}
			out.print(fileContent);
			out.close();
		} catch (Exception e) {
			log.error("writeFileByString Exception:" + e, e);
			throw e;
		} finally {
			if (out != null)
				out.close();
		}
	}
//	public static String getFileType(String name){
//		String type = "";
//		if(StringUtil.isNullStr(name) == false 
//				&& StringUtil.null2Str(name).endsWith(".") == false
//				&& StringUtil.null2Str(name).lastIndexOf(".") > 0){
//			type = name.substring(name.lastIndexOf(".") + 1);
//		}
//		return type;
//	}
}
