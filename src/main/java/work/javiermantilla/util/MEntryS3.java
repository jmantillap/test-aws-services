package work.javiermantilla.util;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MEntryS3 extends MEntry {
	private String nameBucket;

	public MEntryS3(String nameBucket,String accessKey, String secretKey, String region ) {
		super(accessKey, secretKey, region);
		this.nameBucket = nameBucket;		
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MEntryS3 other = (MEntryS3) obj;
		return Objects.equals(nameBucket, other.nameBucket);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nameBucket);
		return result;
	}


	@Override
	public String toString() {
		return "MEntrySQS [nameBucket=" + nameBucket + ", getAccessKey()=" + getAccessKey() + ", getSecretKey()=" + getSecretKey() + ", getRegion()=" + getRegion() + "]";
	}

	
	
	
}
