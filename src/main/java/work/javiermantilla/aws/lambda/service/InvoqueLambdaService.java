package work.javiermantilla.aws.lambda.service;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClient;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;

import work.javiermantilla.exception.TechnicalException;
import work.javiermantilla.util.MEntryLambda;

public class InvoqueLambdaService implements Serializable {

	private static final long serialVersionUID = -9009206876822320744L;

	private static final Logger LOGGER = LogManager.getLogger(InvoqueLambdaService.class);

	public String invokeLambdaAws(String mensaje, MEntryLambda entry) throws TechnicalException {
		LOGGER.info("payload: {}", mensaje);
		AWSLambda client = AWSLambdaClient.builder()
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials(entry.getAccessKey(), entry.getSecretKey())))
				.withRegion(entry.getRegion()).build();
		InvokeRequest request = new InvokeRequest();		
		request.withFunctionName(entry.getArnLamda()).withPayload(mensaje);
		InvokeResult invoke = client.invoke(request);
		LOGGER.info("Resultado de la invocacion: {}", invoke);
		String converted = new String(invoke.getPayload().array(), StandardCharsets.UTF_8);
		LOGGER.info("Resultado JSON convertida: {}", converted);
		return converted;
	}

}
