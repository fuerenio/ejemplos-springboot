package com.gonzasilve.springboot.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;

public class CoreUtil {
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  
	
	public static boolean isValidEmail(String email){
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);  
		Matcher matcher = pattern.matcher(email);  
		return matcher.matches();  
	} 
	
	
	public static int getIdBySet(Set<?> collection){
		return collection.size() + 1;
	}
	
	public static boolean saveByteArrayToFile(byte[] file, String pathFile){
		try {
			FileOutputStream stream = new FileOutputStream(pathFile);
			stream.write(file);
			stream.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean existsFile( String filePath ){
		try{
			File f = new File( filePath );
			if( f.exists() )
				return true;
		}catch( Exception e ){
		}
		
		return false;
	}
	
	public static boolean renameFile(String filePath, String newName) {
		try {
			
			File oldFile = new File(filePath);
			File newFile = new File(newName );
			org.apache.commons.io.FileUtils.copyFile(oldFile, newFile);
			deleteFile(filePath);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean createFile(String filePath) {
		try {
			File file = new File(filePath);
			return file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean createDirectory(String directoryPath) {
		File file = new File(directoryPath);
		return file.mkdirs();
	}
	
	
	public static boolean deleteFile( String filePath ){
		try {
			File file = new File(filePath);
			return file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static String getFileName( String filePath ){
		return FilenameUtils.getBaseName(filePath);
	}
	
	public static String getFileExtension( String filePath ){
		return FilenameUtils.getExtension(filePath);
	}
	
	public static String getFileFullPath( String filePath ){
		return FilenameUtils.getFullPath(filePath);
	}
	
	public static String getFilePath( String filePath ){
		return FilenameUtils.getPath(filePath);
	}
	
	public static Date fromStringToDate(String fecha, SimpleDateFormat formatoDelTexto) {
        Date fechaEnviar = null;
        try {
            fechaEnviar = formatoDelTexto.parse(fecha);
            return fechaEnviar;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

	
	public static BigDecimal stringToBigDecimal(String number) throws ParseException{
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');
		
		String pattern = "#,##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);
		
		return (BigDecimal) decimalFormat.parse(number);
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public static Map sortHashMapByValues(Map unsortMap, final int orderController) {
		 
		List list = new LinkedList(unsortMap.entrySet());
		
		// sort list based on comparator
		Collections.sort(list, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				int response =  ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
				return (response != 0)? (response * orderController) : response;
			}
		});
 
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
 
	@SuppressWarnings("rawtypes")
	public static void printMap(Map<String, String> map){
		for (Map.Entry entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() 
                                   + " Value : " + entry.getValue());
		}
	}
	/**
	 * Metodo para convertir un hexadecimal a un String
	 * @param b arreglo de bytes que representa el hexadecimal
	 * @return
	 */
	public static String toHexString(byte[] b) {
		String result = "";
		for (int i=0; i < b.length; i++) {
			result += Integer.toString( ( b[i] & 0xFF ) + 0x100, 16).substring( 1 );
		}
		return result;
	}
	
	/**
	 * Metodo para convertir un Hexadecimal a un arreglo de Bytes
	 * @param s String que representa el hexadecimal
	 * @return
	 */
	public static byte[] hexToByteArray(String s) {
		if(s == null) {
			s = "";
		}
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		for(int i = 0; i < s.length() - 1; i += 2) {
			String data = s.substring(i, i + 2);
			bout.write(Integer.parseInt(data, 16));
		}
		return bout.toByteArray();
	}
	
	
	public static boolean checkMail(String mail){
		String exp="([_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}))";
		return Pattern.matches(exp, mail);
	}
	
	public static boolean saveByteArrayToFile(byte[] file, String pathFile, String nameFile){
		try {
			FileOutputStream stream = new FileOutputStream(pathFile+nameFile);
			stream.write(file);
			stream.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	

    public static byte[] fileTobyte(File file) {
        InputStream is;
        byte[] bytes = null;
        try {
            is = new FileInputStream(file);

            // Get the size of the file
            long length = file.length();

            if (length > Integer.MAX_VALUE) {
                // File is too large
            }

            // Create the byte array to hold the data
            bytes = new byte[(int) length];

            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }

            // Ensure all the bytes have been read in
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }

            // Close the input stream and return bytes
            is.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bytes;
    }
    
    public static String hexToAscii(String hex){
    	StringBuilder output = new StringBuilder();
	    for (int i = 0; i < hex.length(); i+=2) {
	        String str = hex.substring(i, i+2);
	        output.append((char)Integer.parseInt(str, 16));
	    }
	    return output.toString();
    }
    
    /**
     * M&eacute;todo para establecer el formato de moneda a un valor de tipo Double
     * @param cantidad a convertir
     * @return Cadena con formato de moneda
     */
    public static String setCurrencyFormat(Double cantidad){
    	NumberFormat df = NumberFormat.getCurrencyInstance();
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setCurrencySymbol("$");
		dfs.setGroupingSeparator(',');
		dfs.setMonetaryDecimalSeparator('.');
		((DecimalFormat) df).setDecimalFormatSymbols(dfs);
		return df.format(cantidad);
    }
    
    
	public static String readFile(String pathFile){
    	Scanner scanner;
    	StringBuilder builder = new StringBuilder();
    	try {
			scanner = new Scanner(new File(pathFile));
			while (scanner.hasNext()){
				String line = scanner.nextLine();
				builder.append(line);
				builder.append('\n');
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("File Legal Terms" +builder.toString());
    	return builder.toString();
    }
    
    /**
     * Lee un archivo de texto desde un InputStream y lo devuelve como String con codificacion UTF-8
     * @param stream
     * @return String {@link java.lang.String}
     */
    @SuppressWarnings("resource")
	public static String readTxtFromResources(InputStream stream){
    	Scanner scanner;
    	StringBuilder builder = new StringBuilder();

    	try {
			scanner = new Scanner(stream, "ISO-8859-1");
			while (scanner.hasNext()){
				String line = scanner.nextLine();
				builder.append(line);
				builder.append('\n');
			}
			scanner.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return builder.toString();
   
    }
    
    
	/**
	 * Recive una cadena en UTF8 y la convierte a Latin-1
	 * 
	 * @param cadena Cadena en utf-8
	 * @return Cadena en Latin-1
	 * 
	 * @throws UnsupportedEncodingException Si el encoding no esta soportado
	 */
	public static String fromUtf8ToLatin(String cadena) throws UnsupportedEncodingException{
		return new String( cadena.getBytes("ISO-8859-1"), "UTF-8" );
	} 
    
    /**
     * Metodo para generar el nombre de los archivos FTP
     */
    public static String createNameFileFtp(String nameFile){
    	
    	String ftpFileName = getFileName(nameFile)+"."+getFileExtension(nameFile);
    	return ftpFileName;
    }


	public static String createBasePath(String ftpPath, int idUsuario, String traId) {
		System.out.println("Id usuario "+idUsuario);
		String ftpFileName = ftpPath+idUsuario+"/"+traId;
		return ftpFileName;
	}


	public static String createNameFileXLS(int idUser) {
		return idUser+".xls";
	}


	public static String formatCurrency(double merchantBalance) {

		DecimalFormat myFormatter = new DecimalFormat("$###,###.00");
		String output = myFormatter.format(merchantBalance);
		System.out.println(merchantBalance + "  " + "  " + output);
		return output;
	}
	
	/**
	 * Redondeo a dos decimales
	 * @param numero
	 * @return
	 */
	public static double redondear(double numero)
	{
	       return Math.rint(numero*100)/100;
	}
}
