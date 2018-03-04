package actionmonitor.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {
	
	private static Logger logger = LoggerFactory.getLogger(RestApiController.class);

	private final BuildProperties buildProperties;
	
	@Autowired
	public RestApiController(BuildProperties buildProperties){
		this.buildProperties = buildProperties;
	}
	
	@RequestMapping("/version")
	public ResponseEntity<String> version() {
		logger.info("Request for retreiving version accepted");
        return new ResponseEntity<>(buildProperties.getVersion(), HttpStatus.OK);
    }
	
	@RequestMapping("/status")
	public ResponseEntity<Void> status() {
		logger.info("Request for retreiving status accepted");
		return new ResponseEntity<>(HttpStatus.OK);
    }
}
