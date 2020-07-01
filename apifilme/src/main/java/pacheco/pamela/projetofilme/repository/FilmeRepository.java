package pacheco.pamela.projetofilme.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pacheco.pamela.projetofilme.model.FilmeEntity;

@Repository
public interface FilmeRepository extends CrudRepository<FilmeEntity, Long> {
}
