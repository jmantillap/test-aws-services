package work.javiermantilla.aws.sqs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import work.javiermantilla.aws.sqs.service.SQSServicePub;
import work.javiermantilla.util.MEntrySQS;

public class MainSQS {

	private static final Logger LOGGER = LogManager.getLogger(MainSQS.class);

	public static void main(String[] args) {

		LOGGER.info("Inicial el servicio de SQS");
		String urlCola = "";
		String accessKey = "";
		String secretKey = "";
		String region = "us-east-1";
		MEntrySQS mEntrySQS = new MEntrySQS(urlCola, accessKey, secretKey, region);
		String mensaje = "{\"typeDocument\":\"1\",\"documentNumber\":\"37896334\",\"password\":\"Q29tZXJjaW8yMDIzKio=\",\"idPlataforma\":226}";
		SQSServicePub servicePub = new SQSServicePub();
		servicePub.enviarmensajeColaSQS(mensaje, mEntrySQS);
		LOGGER.info("FIN TEST SQS");

	}

}
