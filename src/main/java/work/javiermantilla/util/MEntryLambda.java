package work.javiermantilla.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MEntryLambda {
	private String accesskey;
	private String secretkey;
	private String region;
	private String arnLamda;
}
