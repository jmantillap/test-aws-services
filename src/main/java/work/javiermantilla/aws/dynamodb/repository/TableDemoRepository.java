package work.javiermantilla.aws.dynamodb.repository;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import work.javiermantilla.aws.dynamodb.entity.TableDemoEntity;
import work.javiermantilla.exception.ErrorCode;
import work.javiermantilla.exception.TechnicalException;

/**
 * Clase Con los servicios necesarios para almacenar y consultar
 * 
 * @author javier.mantilla SP 6.
 * @since Ago/2023
 */
public class TableDemoRepository {

	private static final Logger LOGGER = LogManager.getLogger(TableDemoRepository.class);

	private final DynamoDBMapper mapper;

	private static TableDemoRepository instance;

	private TableDemoRepository(DynamoDBMapper mapper) {
		this.mapper = mapper;
	}

	public static TableDemoRepository instance(DynamoDBMapper mapper) {
		if (instance == null) {
			instance = new TableDemoRepository(mapper);
		}
		return instance;
	}

	public List<TableDemoEntity> findTableDemoByUserName(String username) throws TechnicalException {
		DynamoDBQueryExpression<TableDemoEntity> customerQuery = new DynamoDBQueryExpression<>();
		try {
			LOGGER.info("Se consulta cliente por username: {}", username);
			TableDemoEntity customerKey = new TableDemoEntity(username);
			customerQuery.setHashKeyValues(customerKey);
			return mapper.query(TableDemoEntity.class, customerQuery);
		} catch (Exception e) {
			LOGGER.error("Ocurrió error al consultar por username : {} | {}", username, e);
			throw new TechnicalException(ErrorCode.TCH_ERROR_DATABASE.getMessage() + ": " + username, e);
		}
	}

	public Optional<TableDemoEntity> findTableByUserNameAndEmail(String userName, String email) throws TechnicalException {
		try {
			TableDemoEntity tableDemo = mapper.load(TableDemoEntity.class, userName, email);
			return Optional.ofNullable(tableDemo);
		} catch (Exception e) {
			LOGGER.error("Ocurrió error al consultar por username y email : {} | {}", userName, e);
			throw new TechnicalException(ErrorCode.TCH_ERROR_DATABASE.getMessage() + ": " + userName, e);
		}
	}

	public void saveOrUpdateTable(TableDemoEntity tableDemo) throws TechnicalException {
		try {
			LOGGER.info("Se va a guardar o insertar el tableDemo: {} ", tableDemo);
			mapper.save(tableDemo);
		} catch (Exception e) {
			LOGGER.error("Ocurrió error al guardar o insertar el sicoMauc: {} | {}", tableDemo, e);
			throw new TechnicalException(ErrorCode.TCH_ERROR_DATABASE.getMessage() + ": " + tableDemo.getEmail(), e);
		}
	}

	public void deleteTableRow(String userName, String email) throws TechnicalException {
		LOGGER.info("Se va a eliminar username: {}, email: {} ", userName,email);
		Optional<TableDemoEntity> oTableDemo = findTableByUserNameAndEmail(userName, email);
		if (oTableDemo.isPresent()) {
			mapper.delete(oTableDemo.get());
			LOGGER.info("Se elimino username: {}, email: {} ", userName,email);
		} else {
			LOGGER.error("Could not delete event, no such team and date combination : {} | {}", userName,email);			
			throw new IllegalArgumentException("Delete failed for nonexistent event");
		}
	}
	
	
	public List<TableDemoEntity> findAllUsers(){		
		return mapper.scan(TableDemoEntity.class, new DynamoDBScanExpression());
	}

	/**
	 * Para pruebas unitarias
	 */
	public static void resetInstance() {
		instance = null;
	}
}
