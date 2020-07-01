package pacheco.pamela.projetofilme.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HelloWorld {

    @RequestMapping("/")
    public ResponseEntity<String> index(){
        return new ResponseEntity<String>("Olá!", new HttpHeaders(), HttpStatus.CREATED);
    }
}
