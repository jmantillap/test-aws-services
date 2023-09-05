package work.javiermantilla.aws.sqs.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;
import work.javiermantilla.exception.TechnicalException;
import work.javiermantilla.util.MEntrySQS;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

public class SQSServicePub {

	private static final Logger LOGGER = LogManager.getLogger(SQSServicePub.class);

	public boolean enviarmensajeColaSQS(String mensaje, MEntrySQS queque) throws TechnicalException {
		try {
			LOGGER.info("Inicio de enviarmensajeColaSQS, mensaje : {}, cola : {}  ", mensaje, queque.getUrlCola());
			this.writeMessage(mensaje, queque);
			LOGGER.info("Fin de enviarmensajeColaSQS, mensaje : {}, cola: {}", mensaje, queque.getUrlCola());
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error con el mensaje {}; err: {}", mensaje, e.getMessage());
			throw new TechnicalException(e.getMessage(), e);
		}
		return true;
	}

	private String writeMessage(String message, MEntrySQS queque) throws TechnicalException {
		String messageId;
		try {
			AwsBasicCredentials credentials = AwsBasicCredentials.create(queque.getAccessKey(), queque.getSecretKey());
			SqsClient sqsClient = SqsClient.builder().region(Region.of(queque.getRegion())).credentialsProvider(StaticCredentialsProvider.create(credentials)).build();
			SendMessageRequest sendMessageRequest = SendMessageRequest.builder().queueUrl(queque.getUrlCola()).messageBody(message).build();
			SendMessageResponse sendMessageResponse = sqsClient.sendMessage(sendMessageRequest);
			messageId = sendMessageResponse.messageId();
			LOGGER.info("Mensaje: {}, enviado a la cola : {}, Identificador del mensaje: {}  ", message, queque.getUrlCola(), messageId);
		} catch (Exception e) {
			LOGGER.error("Error al enviar mensaje: {}, a la cola: {}, detalle:{}", message, queque.getUrlCola(), e);
			throw new TechnicalException("Problemas Al escribir el mensaje : " + e.getMessage(), e);
		}
		return messageId;

	}
}
