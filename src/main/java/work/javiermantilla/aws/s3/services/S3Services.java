package work.javiermantilla.aws.s3.services;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import work.javiermantilla.util.MEntryS3;


public class S3Services {
	
	private static final Logger LOGGER = LogManager.getLogger(S3Services.class);
	
	private AmazonS3 s3Client;
	private MEntryS3 entry;

	public S3Services(MEntryS3 mEntryS3) {

		this.entry = mEntryS3;
		AWSCredentials credentials = new BasicAWSCredentials(entry.getAccessKey(), entry.getSecretKey());
		this.s3Client = AmazonS3ClientBuilder.standard()
							.withCredentials(new AWSStaticCredentialsProvider(credentials))
							.withRegion(entry.getRegion())
							.build();

	}	
	
	public void listBucketObjects() {
		//https://github.com/awsdocs/aws-doc-sdk-examples/blob/main/java/example_code/s3/src/main/java/aws/example/s3/ListObjects.java
		ListObjectsV2Result result = this.s3Client.listObjectsV2(entry.getNameBucket());
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        for (S3ObjectSummary os : objects) {
        	LOGGER.info("* {}" ,os.getKey());
        }        
	}
	
	public void uploadObject(String nombreArchivoKey, File file) {
		//https://github.com/awsdocs/aws-doc-sdk-examples/blob/main/java/example_code/s3/src/main/java/aws/example/s3/UploadObject.java
		PutObjectRequest request = new PutObjectRequest(entry.getNameBucket(),nombreArchivoKey,file);		
		this.s3Client.putObject(request);
		LOGGER.info("El archivo se subio al S3 : {}" ,nombreArchivoKey);
		
	}
	
	
	
}
