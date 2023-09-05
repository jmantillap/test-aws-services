package work.javiermantilla.aws.lambda;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;

import work.javiermantilla.aws.lambda.service.InvoqueLambdaService;
import work.javiermantilla.util.MEntryLambda;

public class MainLambdaApigateway {

	private static final Logger LOGGER = LogManager.getLogger(MainLambdaApigateway.class);

	public static void main(String[] args) {

		InvoqueLambdaService invoqueLambdaService = new InvoqueLambdaService();
		LOGGER.info("Inicial el servicio de Lambda");
		String arnLambda="";
		String accessKey="";
		String secretKey="arn:aws:lambda:us-east-1:954025672155:function:dev-sico2-lambda-authenticator";
		String region="us-east-1";		
		MEntryLambda entry = new MEntryLambda(accessKey,secretKey,region, arnLambda);
		String mensaje = "{\"typeDocument\":\"1\",\"documentNumber\":\"37896334\",\"password\":\"Q29tZXJjaW8yMDIzKio=\",\"idPlataforma\":226}";
		APIGatewayV2HTTPEvent event = new APIGatewayV2HTTPEvent();
		event.setBody(mensaje);
		LOGGER.info("Entry aws: {}",entry);
		String retorno = invoqueLambdaService.invokeLambdaAws(mensaje, entry);
		LOGGER.info("Respuesta Servicio {}", retorno);
	}

}
