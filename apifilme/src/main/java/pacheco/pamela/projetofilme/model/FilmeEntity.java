package pacheco.pamela.projetofilme.model;


import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Timer;

/**
 * @author Pamela Amanda
 * @version 1.0
 * @since 27/06/2020
 */

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString

@Component

@Entity
@Table(name = "narrativa")
public class FilmeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_filme")
    private long id;

    @Column(name = "nome")
//    @NotBlank(message = "Erro! Digite o nome do filme!")
    private String nome;

    @Column(name = "duracao")
//    @NotBlank(message = "Erro!Digite a duração do filme!")
    private String duracao;

    @Column(name = "classificacao")
//    @NotBlank(message = "Erro!Digite a classificação indicativa do filme!")
    private String classificacao;

    @Column(name = "nacionalidade")
//    @NotBlank(message = "Erro!Digite a nacionalidade do filme!")
    private String nacionalidade;

    @Column(name = "categoria")
//    @NotBlank(message = "Erro!Digite a categoria do filme!")
    private String categoria;

    @Column(name = "ano")
//    @NotBlank(message = "Erro! Digite o ano de lançamento do filme!")
    private int anoLancamento;

}
