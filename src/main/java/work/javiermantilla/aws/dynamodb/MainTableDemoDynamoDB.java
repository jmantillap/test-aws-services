package work.javiermantilla.aws.dynamodb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import work.javiermantilla.aws.dynamodb.config.DynamoDBManager;
import work.javiermantilla.aws.dynamodb.entity.TableDemoEntity;
import work.javiermantilla.aws.dynamodb.repository.TableDemoRepository;
import work.javiermantilla.aws.dynamodb.service.DynamoDbServices;
import work.javiermantilla.util.ConstantsTest;
import work.javiermantilla.util.MEntryDynamoDB;
import work.javiermantilla.util.PropertiesTestUtil;

public class MainTableDemoDynamoDB {

	private static final Logger LOGGER = LogManager.getLogger(MainTableDemoDynamoDB.class);

	public static void main(String[] args) {

		LOGGER.info("Inicial el proceso para la insercion en table demo");

		String urlDynamoDb = PropertiesTestUtil.getProperty(ConstantsTest.DYNAMO_URL);
		String accessKey = PropertiesTestUtil.getProperty(ConstantsTest.VAR_ACCESSKEY);
		String secretKey = PropertiesTestUtil.getProperty(ConstantsTest.VAR_SECRETKEY);
		String region = PropertiesTestUtil.getProperty(ConstantsTest.VAR_REGION);
		String nameTable = PropertiesTestUtil.getProperty(ConstantsTest.DYNAMO_TABLE);
		MEntryDynamoDB entry = new MEntryDynamoDB(nameTable, urlDynamoDb,accessKey, secretKey, region);
		DynamoDBMapper mapper = DynamoDBManager.mapper(entry);
		DynamoDbServices dynamoDbServices = new DynamoDbServices(TableDemoRepository.instance(mapper));
		LOGGER.info("Entry aws: {}", entry);
		
		dynamoDbServices.listUsers();
		
//		TableDemoEntity tableDemoEntity1 = TableDemoEntity.builder().userName("113544000")
//				.email("jmantillap@hotmail.com").numberAttempts(0).passwordExpires("2023-10-01T00:00:00").blocked(false)
//				.build();
//
//		dynamoDbServices.saveOrUpdateTable(tableDemoEntity1);

		TableDemoEntity tableDemoEntity2 = TableDemoEntity.builder().userName("137896334")
				.email("eliana.bravovesga@gmail.com")
				.numberAttempts(0)
				.passwordExpires("2023-10-06T00:00:00")
				.blocked(false).build();

		dynamoDbServices.saveOrUpdateTable(tableDemoEntity2);

//		TableDemoEntity tableDemoEntity3 = TableDemoEntity.builder().userName("13822225")
//				.email("jmantillap@hotmail.com")
//				.numberAttempts(0)
//				.passwordExpires("2023-10-01T00:00:00").blocked(false)
//				.build();
//
//		dynamoDbServices.saveOrUpdateTable(tableDemoEntity3);
//		
//		TableDemoEntity tableDemoEntity4 = TableDemoEntity.builder().userName("128131633")
//				.email("ing.javiermantilla@gmail.com").numberAttempts(0).passwordExpires("2023-10-14T00:00:00").blocked(false)
//				.build();
//		dynamoDbServices.saveOrUpdateTable(tableDemoEntity4);
//		
//		TableDemoEntity tableDemoEntity5 = TableDemoEntity.builder().userName("113544171")
//				.email("jmantillap@gmail.com").numberAttempts(0).passwordExpires("2023-10-01T00:00:00").blocked(false)
//				.build();
//
//		dynamoDbServices.saveOrUpdateTable(tableDemoEntity5);
		
		/*
		TableDemoEntity tableDemoEntityEliminar = TableDemoEntity.builder().userName("128131633")
				.email("ing.javiermantilla@gmail.com").numberAttempts(0).passwordExpires("2023-10-14T00:00:00").blocked(false)
				.build();
		dynamoDbServices.deleteRow(tableDemoEntity5);
		*/
		
		dynamoDbServices.listUsers();

	}
}
