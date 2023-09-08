package work.javiermantilla.util;


import java.io.InputStream;
import java.util.Properties;

public class PropertiesTestUtil {
	private static Properties properties = new Properties();
	
	private PropertiesTestUtil() {}
	
	static {
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream in = loader.getResourceAsStream("config.properties");
			properties.load(in);
		} catch (Exception e) {
			e.printStackTrace();
			 throw new IllegalArgumentException("file not found! ");
		}		
	}
	
	public static String getProperty(String key) {		
		return properties.getProperty(key);
	}
	

}
