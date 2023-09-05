package work.javiermantilla.util;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class MEntryLambda extends MEntry {	
	private String arnLamda;
	
	public MEntryLambda(String arnLamda, String accessKey, String secretKey, String region ) {
		super(accessKey, secretKey, region);
		this.arnLamda = arnLamda;
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MEntryLambda other = (MEntryLambda) obj;
		return Objects.equals(arnLamda, other.arnLamda);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(arnLamda);
		return result;
	}

	@Override
	public String toString() {
		return "MEntryLambda [arnLamda=" + arnLamda + ", getAccessKey()=" + getAccessKey() + ", getSecretKey()=" + getSecretKey() + ", getRegion()=" + getRegion() + "]";
	}
	
	
	
}
