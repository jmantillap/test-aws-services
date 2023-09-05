package work.javiermantilla.aws.dynamodb.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;

import work.javiermantilla.util.MEntryDynamoDB;

/**
 * Clase encargada de manejar la conexión a dynamodb de AWS
 * 
 * @author javier.mantilla SP 6.
 * @since Ago/2023
 */
public class DynamoDBManager {

	private static DynamoDBManager instance;
	private DynamoDBMapper mapper;

	private AmazonDynamoDB amazonDynamoDBConfig;

	private DynamoDBManager(MEntryDynamoDB entry) {

		this.amazonDynamoDBConfig = AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(
						new AwsClientBuilder.EndpointConfiguration(entry.getUrlDynamoDB(), entry.getRegion()))
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials(entry.getAccessKey(), entry.getSecretKey())))
				.build();

		TableNameOverride config = new DynamoDBMapperConfig.TableNameOverride(entry.getNameTable());

		DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
				.withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.CLOBBER)
				.withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT).withTableNameOverride(config)
				.withPaginationLoadingStrategy(DynamoDBMapperConfig.PaginationLoadingStrategy.EAGER_LOADING).build();

		mapper = new DynamoDBMapper(amazonDynamoDBConfig, mapperConfig);

	}

	private static DynamoDBManager instance(MEntryDynamoDB entry) {

		if (instance == null) {
			instance = new DynamoDBManager(entry);
		}

		return instance;
	}

	public static DynamoDBMapper mapper(MEntryDynamoDB entry) {
		DynamoDBManager manager = DynamoDBManager.instance(entry);
		return manager.mapper;
	}
}
