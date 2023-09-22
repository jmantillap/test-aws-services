package work.javiermantilla.aws.dynamodb.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import work.javiermantilla.aws.dynamodb.entity.TableDemoEntity;
import work.javiermantilla.aws.dynamodb.repository.TableDemoRepository;

public class DynamoDbServices {

	private static final Logger LOGGER = LogManager.getLogger(DynamoDbServices.class);

	private TableDemoRepository tableDemoRepository;

	public DynamoDbServices(TableDemoRepository tableDemoRepository) {
		super();
		this.tableDemoRepository = tableDemoRepository;
	}

	public void saveOrUpdateTable(TableDemoEntity tableDemo) {
		this.tableDemoRepository.saveOrUpdateTable(tableDemo);
	}

	public void deleteRow(TableDemoEntity tableDemo) {
		this.tableDemoRepository.deleteTableRow(tableDemo.getUserName(), tableDemo.getEmail());
	}

	public void listUsers() {
		List<TableDemoEntity> lista = this.tableDemoRepository.findAllUsers();
		LOGGER.info("LISTADO DE USUARIOS EN DYNAMOBD");
		for (TableDemoEntity tableDemoEntity : lista) {
			LOGGER.info("USER: {} ", tableDemoEntity);
		}
	}

}
