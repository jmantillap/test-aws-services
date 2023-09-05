package work.javiermantilla.aws.dynamodb.service;

import work.javiermantilla.aws.dynamodb.entity.TableDemoEntity;
import work.javiermantilla.aws.dynamodb.repository.TableDemoRepository;

public class DynamoDbServices {
	
	private TableDemoRepository tableDemoRepository;

	public DynamoDbServices(TableDemoRepository tableDemoRepository) {
		super();
		this.tableDemoRepository = tableDemoRepository;
	}	
	
	public void saveOrUpdateTable(TableDemoEntity tableDemo) {
		this.tableDemoRepository.saveOrUpdateTable(tableDemo);
	}
	
	
	
}
