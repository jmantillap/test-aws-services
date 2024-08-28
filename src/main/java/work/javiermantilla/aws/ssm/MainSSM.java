package work.javiermantilla.aws.ssm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import work.javiermantilla.aws.ssm.service.SsmServices;
import work.javiermantilla.util.ConstantsTest;
import work.javiermantilla.util.MEntryS3;
import work.javiermantilla.util.PropertiesTestUtil;

public class MainSSM {

	private static SsmServices ssmServices;
	private static MEntryS3 entry;
	private static final Logger LOGGER = LogManager.getLogger(MainSSM.class);
	
	public static void main(String[] args) {
		loadEntry();
		ssmServices= new SsmServices(entry);
		LOGGER.info("Inicial el servicio ssm para obtener un parametro String sin cifrar");
		LOGGER.info("Valor /parametro/valor : {}",ssmServices.getParameterStore("/parametro/valor"));
		
		LOGGER.info("Inicial el servicio ssm para obtener un parametro de tipo SecurityString ");
		LOGGER.info("Valor /parametro/pass : {}",ssmServices.getParameterStoreSecurityString("/parametro/pass"));
		
	}

	private static void loadEntry() {

		String accessKey = PropertiesTestUtil.getProperty(ConstantsTest.VAR_ACCESSKEY);
		String secretKey = PropertiesTestUtil.getProperty(ConstantsTest.VAR_SECRETKEY);
		String region = PropertiesTestUtil.getProperty(ConstantsTest.VAR_REGION);
		String nameBucket = PropertiesTestUtil.getProperty(ConstantsTest.S3_BUCKET);
		entry = new MEntryS3(nameBucket, accessKey, secretKey, region);
	}
	

}
