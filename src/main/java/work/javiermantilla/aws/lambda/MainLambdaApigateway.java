package work.javiermantilla.aws.lambda;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;

import work.javiermantilla.aws.lambda.service.InvoqueLambdaService;
import work.javiermantilla.util.ConstantsTest;
import work.javiermantilla.util.MEntryLambda;
import work.javiermantilla.util.MapperUtil;
import work.javiermantilla.util.PropertiesTestUtil;

public class MainLambdaApigateway {

	private static final Logger LOGGER = LogManager.getLogger(MainLambdaApigateway.class);

	public static void main(String[] args) {

		InvoqueLambdaService invoqueLambdaService = new InvoqueLambdaService();
		LOGGER.info("Inicial el servicio de Lambda");
		String arnLambda=PropertiesTestUtil.getProperty(ConstantsTest.LAMBDA_ARN);
		String accessKey = PropertiesTestUtil.getProperty(ConstantsTest.VAR_ACCESSKEY);
		String secretKey = PropertiesTestUtil.getProperty(ConstantsTest.VAR_SECRETKEY);
		String region = PropertiesTestUtil.getProperty(ConstantsTest.VAR_REGION);		
		MEntryLambda entry = new MEntryLambda(arnLambda,accessKey,secretKey,region);
		String mensaje = "{\r\n"
				+ "	\"typeDocument\": \"1\",\r\n"
				+ "	\"documentNumber\": \"13544171\",\r\n"
				+ "	\"password\": \"Q29tZXJjaW8yMDIzKg==\",\r\n"
				+ "	\"idPlataforma\": 226\r\n"
				+ "}";
		APIGatewayV2HTTPEvent event = new APIGatewayV2HTTPEvent();
		event.setBody(mensaje);
		LOGGER.info("Entry aws: {}",entry);
		String retorno = invoqueLambdaService.invokeLambdaAws(MapperUtil.getStringJsonFromDTO(event), entry);
		LOGGER.info("Respuesta Servicio {}", retorno);
	}

}
