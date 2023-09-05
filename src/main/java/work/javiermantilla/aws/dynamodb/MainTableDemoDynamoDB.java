package work.javiermantilla.aws.dynamodb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import work.javiermantilla.aws.dynamodb.config.DynamoDBManager;
import work.javiermantilla.aws.dynamodb.entity.TableDemoEntity;
import work.javiermantilla.aws.dynamodb.repository.TableDemoRepository;
import work.javiermantilla.aws.dynamodb.service.DynamoDbServices;
import work.javiermantilla.util.MEntryDynamoDB;

public class MainTableDemoDynamoDB {

	private static final Logger LOGGER = LogManager.getLogger(MainTableDemoDynamoDB.class);

	public static void main(String[] args) {

		LOGGER.info("Inicial el proceso para la insercion en table demo");

		String urlDynamoDb = "dynamodb.us-east-1.amazonaws.com";
		String accessKey = "";
		String secretKey = "";
		String region = "us-east-1";
		String nameTable = "CCB_SICO_MAUC_CUSTOMER";
		MEntryDynamoDB entry = new MEntryDynamoDB(accessKey, secretKey, region, nameTable, urlDynamoDb);
		DynamoDBMapper mapper = DynamoDBManager.mapper(entry);
		DynamoDbServices dynamoDbServices = new DynamoDbServices(TableDemoRepository.instance(mapper));
		LOGGER.info("Entry aws: {}", entry);
		TableDemoEntity tableDemoEntity1 = TableDemoEntity.builder().userName("113544000")
				.email("jmantillap@hotmail.com").numberAttempts(0).passwordExpires("2023-10-01T00:00:00").blocked(false)
				.build();

		dynamoDbServices.saveOrUpdateTable(tableDemoEntity1);

		TableDemoEntity tableDemoEntity2 = TableDemoEntity.builder().userName("137896334")
				.email("eliana.bravovesga@gmail.com").numberAttempts(0).passwordExpires("2099-08-30T00:00:00")
				.blocked(false).build();

		dynamoDbServices.saveOrUpdateTable(tableDemoEntity2);

		TableDemoEntity tableDemoEntity3 = TableDemoEntity.builder().userName("113544000")
				.email("jmantillap@hotmail.com").numberAttempts(0).passwordExpires("2023-10-01T00:00:00").blocked(false)
				.build();

		dynamoDbServices.saveOrUpdateTable(tableDemoEntity3);

	}
}
