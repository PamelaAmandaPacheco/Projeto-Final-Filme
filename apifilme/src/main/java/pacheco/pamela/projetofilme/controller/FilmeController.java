package pacheco.pamela.projetofilme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pacheco.pamela.projetofilme.model.FilmeEntity;
import pacheco.pamela.projetofilme.repository.FilmeRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/filmes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FilmeController {

    @Autowired
    private FilmeRepository filmeRepository;

    @GetMapping
    public ResponseEntity<List<FilmeEntity>> findAll() {
        return new ResponseEntity<List<FilmeEntity>>(
                (List<FilmeEntity>)this.filmeRepository.findAll(),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FilmeEntity> findById(@PathVariable("id") long id ) {
        if (this.filmeRepository.findById(id).isPresent()) {
            return new ResponseEntity<FilmeEntity>(
                    this.filmeRepository.findById(id).get(),
                    new HttpHeaders(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<FilmeEntity>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<FilmeEntity> save(@Valid @RequestBody FilmeEntity filmeEntity) {
        return new ResponseEntity<FilmeEntity>(
                this.filmeRepository.save(filmeEntity),
                new HttpHeaders(),
                HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<FilmeEntity> update(@RequestBody FilmeEntity filmeEntity) throws Exception  {
        if(!this.filmeRepository.existsById(filmeEntity.getId())) {
            throw new Exception("Código não encontrado ou inexistente");
        }
        return new ResponseEntity<FilmeEntity>(
                this.filmeRepository.save(filmeEntity),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<FilmeEntity> delete(@PathVariable("id") long id) {
        this.filmeRepository.deleteById(id);
        return new ResponseEntity<FilmeEntity>(new HttpHeaders(), HttpStatus.OK);
    }

}
