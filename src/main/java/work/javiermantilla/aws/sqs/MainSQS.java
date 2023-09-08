package work.javiermantilla.aws.sqs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import work.javiermantilla.aws.sqs.service.SQSServicePub;
import work.javiermantilla.util.ConstantsTest;
import work.javiermantilla.util.MEntrySQS;
import work.javiermantilla.util.PropertiesTestUtil;

public class MainSQS {

	private static final Logger LOGGER = LogManager.getLogger(MainSQS.class);

	public static void main(String[] args) {

		LOGGER.info("Inicial el servicio de SQS");
		String urlCola = PropertiesTestUtil.getProperty(ConstantsTest.SQS_URLCOLA);;
		String accessKey = PropertiesTestUtil.getProperty(ConstantsTest.VAR_ACCESSKEY);
		String secretKey = PropertiesTestUtil.getProperty(ConstantsTest.VAR_SECRETKEY);
		String region = PropertiesTestUtil.getProperty(ConstantsTest.VAR_REGION);
		
		MEntrySQS mEntrySQS = new MEntrySQS(urlCola, accessKey, secretKey, region);
		String mensaje = "{\"typeDocument\":\"1\",\"documentNumber\":\"37896334\",\"password\":\"Q29tZXJjaW8yMDIzKio=\",\"idPlataforma\":226}";
		SQSServicePub servicePub = new SQSServicePub();
		LOGGER.info("Entry aws: {}",mEntrySQS);
		servicePub.enviarmensajeColaSQS(mensaje, mEntrySQS);
		LOGGER.info("FIN TEST SQS");

	}

}
