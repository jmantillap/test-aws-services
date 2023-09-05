package work.javiermantilla.util;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MEntrySQS extends MEntry {
	private String urlCola;

	public MEntrySQS(String urlCola,String accessKey, String secretKey, String region ) {
		super(accessKey, secretKey, region);
		this.urlCola = urlCola;		
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MEntrySQS other = (MEntrySQS) obj;
		return Objects.equals(urlCola, other.urlCola);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(urlCola);
		return result;
	}
	
	
	
}
