package work.javiermantilla.aws.s3;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import work.javiermantilla.aws.s3.services.S3Services;
import work.javiermantilla.util.MEntryS3;

public class MainS3 {

	private static String nameBucket = "";
	private static String accessKey = "";
	private static String secretKey = "";
	private static String region = "us-east-1";
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
		entry = new MEntryS3(nameBucket, accessKey, secretKey, region);
	}

}
