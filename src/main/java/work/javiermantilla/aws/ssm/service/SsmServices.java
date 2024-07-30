package work.javiermantilla.aws.ssm.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;
import work.javiermantilla.exception.TechnicalException;
import work.javiermantilla.util.MEntryS3;

public class SsmServices {
	private static final Logger LOGGER = LogManager.getLogger(SsmServices.class);

	private MEntryS3 entry;
	private SsmClient ssmClient;

	public SsmServices(MEntryS3 entry) {
		AwsBasicCredentials awsCreds = AwsBasicCredentials.create(entry.getAccessKey(), entry.getSecretKey());

		// credenciales de rol IAM de servicios
//		ssmClient = SsmClient.builder().credentialsProvider(EnvironmentVariableCredentialsProvider.create())
//				.region(Region.US_EAST_1).build();

		// credenciales programaticas
		ssmClient = SsmClient.builder().credentialsProvider(StaticCredentialsProvider.create(awsCreds))
				.region(Region.US_EAST_1).build();
	}

	public String getParameterStore(String name) {
		try {
			LOGGER.info("Nombre ParameterStore a consultar : {}", name);
			GetParameterRequest parameterRequest = GetParameterRequest.builder().name(name).withDecryption(false)
					.build();
			GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);
			return parameterResponse.parameter().value();
		} catch (TechnicalException | SdkClientException e) {
			LOGGER.error("Error getParameterStore name: {}; decryp:{}, error:{}", name, e.getMessage(), e);
			throw new TechnicalException(e.getMessage(), e);
		}
	}
	
	public String getParameterStoreSecurityString(String name) {
		try {
			LOGGER.info("Nombre ParameterStore a consultar : {}", name);
			GetParameterRequest parameterRequest = GetParameterRequest.builder().name(name).withDecryption(true)
					.build();
			GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);
			return parameterResponse.parameter().value();
		} catch (TechnicalException | SdkClientException e) {
			LOGGER.error("Error getParameterStore name: {}; decryp:{}", name, e.getMessage(), e);
			throw new TechnicalException(e.getMessage(), e);
		}
	}
	

}
