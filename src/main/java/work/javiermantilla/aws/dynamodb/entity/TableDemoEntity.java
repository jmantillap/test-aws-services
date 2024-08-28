package work.javiermantilla.aws.dynamodb.entity;

import java.io.Serializable;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;



/**
 * Clase Entity de la tabla de dynamoDB
 * @author javier.mantilla SP 6. 
 * @since Ago/2023
 */
@Data
@AllArgsConstructor
@Builder
public class TableDemoEntity implements Serializable {

	private static final long serialVersionUID = 7143320504040335055L;
	
	@DynamoDBHashKey(attributeName = "UserName")	
    private String userName;	
	@DynamoDBRangeKey(attributeName = "Email")
    private String email;
	@DynamoDBTyped(DynamoDBAttributeType.BOOL)
    @DynamoDBAttribute(attributeName = "Blocked")    
    private boolean blocked;
	@DynamoDBAttribute(attributeName = "NumberAttempts")
	private int numberAttempts;
    @DynamoDBAttribute(attributeName = "Password")    
    private String password;
    @DynamoDBAttribute(attributeName = "PasswordExpires")
    private String passwordExpires;    
    
    
    
    public TableDemoEntity() {}
    
    
	public TableDemoEntity(String userName) {
		super();
		this.userName = userName;
	}
		
	public TableDemoEntity(String userName, String email) {
		super();
		this.userName = userName;
		this.email = email;
	}

	

	public TableDemoEntity(String userName, String email, boolean blocked,String password, String passwordExpires) {
		super();
		this.userName = userName;
		this.email = email;
		this.blocked = blocked;		
		this.password = password;
		this.passwordExpires = passwordExpires;
	}


	@Override
	public String toString() {
		return "TableDemoEntity [userName=" + userName + ", email=" + email + ", blocked=" + blocked + ", numberAttempts=" + numberAttempts + ", password=" + password
				+ ", passwordExpires=" + passwordExpires + "]";
	}
	
	

}
