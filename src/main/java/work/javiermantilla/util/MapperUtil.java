package work.javiermantilla.util;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import work.javiermantilla.exception.ErrorCode;
import work.javiermantilla.exception.TechnicalException;


/**
 * Clase para parsear los objetos a json o a object
 * @author javier.mantilla SP 6. 
 * @since Ago/2023
 */
public class MapperUtil implements Serializable {

	private static final long serialVersionUID = -2531587665281099197L;

	private static final Logger LOGGER = LogManager.getLogger(MapperUtil.class);
	private static ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);	

	
	public static <T> T parseMessageToDto(String bodyContent, Class<T> outPutClass) throws TechnicalException {
		try {
			return objectMapper.readValue(bodyContent, outPutClass);
		} catch (Exception e) {
			LOGGER.error("Ocurri� un error al parsear: {} | {}", bodyContent, e);
			throw new TechnicalException(ErrorCode.TCH_EXCEPTION_OBJECT_MAPPING.getMessage() + ": " + bodyContent, e);
		}
	}

	public static <T> String getStringJsonFromDTO(T dto) throws TechnicalException {
		try {
			return objectMapper.writeValueAsString(dto);
		} catch (Exception e) {
			LOGGER.error("Ocurri� error al parsear: {} | {}", dto, e);
			throw new TechnicalException(ErrorCode.TCH_EXCEPTION_OBJECT_MAPPING.getMessage() + ": " + dto, e);
		}
	}
}
