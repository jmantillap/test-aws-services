package work.javiermantilla.util;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class MEntryDynamoDB extends MEntry {
	private String nameTable;
	private String urlDynamoDB;
	
	public MEntryDynamoDB(String nameTable, String urlDynamoDB,String accessKey, String secretKey, String region ) {
		super(accessKey, secretKey, region);
		this.nameTable = nameTable;
		this.urlDynamoDB = urlDynamoDB;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nameTable, urlDynamoDB);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MEntryDynamoDB other = (MEntryDynamoDB) obj;
		return Objects.equals(nameTable, other.nameTable) && Objects.equals(urlDynamoDB, other.urlDynamoDB);
	}

	@Override
	public String toString() {
		return "MEntryDynamoDB [nameTable=" + nameTable + ", urlDynamoDB=" + urlDynamoDB + ", getAccessKey()=" + getAccessKey() + ", getSecretKey()=" + getSecretKey()
				+ ", getRegion()=" + getRegion() + "]";
	}
	
	
}
