package work.javiermantilla.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MEntry {
	private String accessKey;
	private String secretKey;
	private String region;
	
	
}
