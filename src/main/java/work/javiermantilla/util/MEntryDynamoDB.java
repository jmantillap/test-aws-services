package work.javiermantilla.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MEntryDynamoDB {
	private String accessKey;
	private String secretKey;
	private String region;
	private String nameTable;
	private String urlDynamoDB;
}
