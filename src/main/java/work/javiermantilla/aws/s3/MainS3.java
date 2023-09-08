package work.javiermantilla.aws.s3;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import work.javiermantilla.aws.s3.services.S3Services;
import work.javiermantilla.util.ConstantsTest;
import work.javiermantilla.util.MEntryS3;
import work.javiermantilla.util.PropertiesTestUtil;

public class MainS3 {

	
	private static S3Services s3Services;
	private static MEntryS3 entry;

	private static final Logger LOGGER = LogManager.getLogger(MainS3.class);

	public static void main(String[] args) {
		loadEntry();
		s3Services = new S3Services(entry);
		LOGGER.info("Inicial el servicio de S3 Listar los objetos del bucket");
		s3Services.listBucketObjects();
		LOGGER.info("Creacion de archivo");
		subirArchivo();

	}

	public static void subirArchivo() {

		String url="C:\\Users\\javier.mantilla\\Proyecto-CCB\\ccb_fuentes_sico2\\ccb_login_tables.yaml";
		File file = new File(url);
		s3Services.uploadObject("ccb_login_tables.yaml", file);

	}

	private static void loadEntry() {
		
		String accessKey = PropertiesTestUtil.getProperty(ConstantsTest.VAR_ACCESSKEY);
		String secretKey = PropertiesTestUtil.getProperty(ConstantsTest.VAR_SECRETKEY);
		String region = PropertiesTestUtil.getProperty(ConstantsTest.VAR_REGION);
		String nameBucket = PropertiesTestUtil.getProperty(ConstantsTest.S3_BUCKET);
		
		entry = new MEntryS3(nameBucket, accessKey, secretKey, region);
	}

}
