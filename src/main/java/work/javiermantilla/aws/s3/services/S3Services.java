package work.javiermantilla.aws.s3.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import work.javiermantilla.util.MEntryS3;

public class S3Services {

	private static final Logger LOGGER = LogManager.getLogger(S3Services.class);

	private AmazonS3 s3Client;
	private MEntryS3 entry;

	public S3Services(MEntryS3 mEntryS3) {

		this.entry = mEntryS3;
		AWSCredentials credentials = new BasicAWSCredentials(entry.getAccessKey(), entry.getSecretKey());
		this.s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(entry.getRegion()).build();

	}

	public void listBucketObjects() {
		// https://github.com/awsdocs/aws-doc-sdk-examples/blob/main/java/example_code/s3/src/main/java/aws/example/s3/ListObjects.java
		ListObjectsV2Result result = this.s3Client.listObjectsV2(entry.getNameBucket());
		List<S3ObjectSummary> objects = result.getObjectSummaries();
		for (S3ObjectSummary os : objects) {
			LOGGER.info("* {}", os.getKey());
		}
	}

	public void uploadObject(String nombreArchivoKey, File file) {
		// https://github.com/awsdocs/aws-doc-sdk-examples/blob/main/java/example_code/s3/src/main/java/aws/example/s3/UploadObject.java
		PutObjectRequest request = new PutObjectRequest(entry.getNameBucket(), nombreArchivoKey, file);
		this.s3Client.putObject(request);
		LOGGER.info("El archivo se subio al S3 : {}", nombreArchivoKey);

	}

	public void downloadObject(String nombreArchivoKey, File fileDestino) throws IOException {
		// https://github.com/awsdocs/aws-doc-sdk-examples/blob/main/javav2/example_code/s3/src/main/java/com/example/s3/GetObjectData.java
		GetObjectRequest objectRequest = new GetObjectRequest(entry.getNameBucket(),nombreArchivoKey);

		S3Object object = this.s3Client.getObject(objectRequest);
		
		InputStream reader = new BufferedInputStream(object.getObjectContent());
		OutputStream writer = new BufferedOutputStream(new FileOutputStream(fileDestino));
		int read = -1;

		while ( ( read = reader.read() ) != -1 ) {
		    writer.write(read);
		}
		writer.flush();
		writer.close();
		reader.close();
		LOGGER.info("El archivo se descargo a : {}", fileDestino.getAbsolutePath());

	}
	
	public void downloadObject1(String nombreArchivoKey, File fileDestino) throws IOException {
		// https://github.com/awsdocs/aws-doc-sdk-examples/blob/main/javav2/example_code/s3/src/main/java/com/example/s3/GetObjectData.java
		GetObjectRequest objectRequest = new GetObjectRequest(entry.getNameBucket(),nombreArchivoKey);

		ObjectMetadata object = this.s3Client.getObject(objectRequest,fileDestino);
		//boolean success = fileDestino.exists() && fileDestino.canRead();		
		LOGGER.info("El archivo se descargo a downloadObject1 : {}", fileDestino.getAbsolutePath());

	}

}
