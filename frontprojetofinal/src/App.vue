<template>
  <div id="app">

    <nav>
      <!-- <div class="nav-wrapper blue darken-1"> -->
        <!-- <a class="brand-logo center">Cadrastro de filmes</a> -->
        <a href="pamela.html" class="brand-logo center" style="font-size: 20px">Informações do criador do site</a>
    </nav>
    <nav>
      <div class="nav-wrapper blue darken-1">
        <a class="brand-logo center" style="font-size: 55px">Cadastre seu filme favorito</a>
      </div>
    </nav>

    <img style="float: right" alt="Corra" src="./assets/corra.jpg" width="400" height="200">
    <img alt="Toy story" src="./assets/story.jpg" width="400" height="200">
    <img style="float: left" alt="Vingadores" src="./assets/vingadores.jpg" width="400" height="200">



    <form @submit.prevent="saveAndUpdateNarrativa" style="margin: 15px">
      <div>
        <input type="text" v-model="narrativa.nome" placeholder="Digite o nome do filme">
      </div>
      <div>
        <input type="text" v-model="narrativa.duracao" placeholder="Digite a duração do filme">
      </div>
      <div>
        <input type="text" v-model="narrativa.classificacao" placeholder="Digite a classificação indicativa do filme">
      </div>
      <div>
        <input type="text" v-model="narrativa.categoria" placeholder="Digite a categoria do filme">
      </div>
      <div>
        <input type="text" v-model="narrativa.nacionalidade" placeholder="Digite a nacionalidade do filme">
      </div>
      <div>
        <input type="text" v-model="narrativa.anoLancamento" placeholder="Digite o ano de estréia do filme">
      </div>
      <button class="waves-effect waves-light btn-small" style="background-color: #E91E63">Cadastrar</button>
    </form>
      <button v-show="narrativa.id" @click="cancelUpdate" class="waves-effect waves-light btn-small" style="background-color: #E91E63">Cancelar</button>

    <p>Quantidade de filmes: {{filmes.length}}</p>

    <form @submit.prevent="findById" style="margin: 15px">
      <div>
        <input type="text" v-model="narrativa.id" placeholder="Digite o código do filme que deseja buscar">
      </div>
      <button class="waves-effect waves-light btn-small" style="background-color: #E91E63">Procurar</button>
    </form>

    <div v-show="findNarrativa.id">
      <h1>Filme encontrado!</h1>
      <p>
        Código: {{findNarrativa.id}} <br>
        Nome do filme: {{findNarrativa.nome}} <br>
        Duração: {{findNarrativa.duracao}} <br>
        Classificação Indicativa: {{findNarrativa.classificacao}} <br>
        Categoria: {{findNarrativa.categoria}} <br>
        Nacionalidade: {{findNarrativa.nacionalidade}} <br>
        Ano do lançamento: {{findNarrativa.anoLancamento}} <br>
      </p>
    </div>

    <table v-show="filmes.length > 0" style="margin: 15px">
      <thead>
        <tr>
          <th>Código</th>
          <th>Nome do Filme</th>
          <th>Duração</th>
          <th>Classificação indicativa</th>
          <th>Categoria</th>
          <th>Nacionalidade</th>
          <th>Ano de lançamento</th>
          <th>Excluir</th>
          <th>Alterar</th>
        </tr>
      </thead>
      <tfoot>
        <th>Código</th>
        <th>Nome do Filme</th>
        <th>Duração</th>
        <th>Classificação indicativa</th>
        <th>Categoria</th>
        <th>Nacionalidade</th>
        <th>Ano de lançamento</th>
        <th>Excluir</th>
        <th>Alterar</th>
      </tfoot>
      <tbody>
        <tr v-for="narrativa in filmes" :key="narrativa.id">
          <td>{{narrativa.id}}</td>
          <td>{{narrativa.nome}}</td>
          <td>{{narrativa.duracao}}</td>
          <td>{{narrativa.classificacao}}</td>
          <td>{{narrativa.categoria}}</td>
          <td>{{narrativa.nacionalidade}}</td>
          <td>{{narrativa.anoLancamento}}</td>
          <td>
            <button type="button" @click="deleteNarrativa(narrativa)" class="waves-effect waves-light btn-small" style="background-color: #E91E63">Excluir</button>
          </td>
          <td>
            <button type="button" @click="updateNarrativa(narrativa)" class="waves-effect waves-light btn-small" style="background-color: #E91E63">Alterar</button>
          </td>
        </tr>
      </tbody>
    </table>

    <img style="float: right" alt="Branquelas" src="./assets/branquelas.jpg" width="400" height="200">
    <img alt="Iluminado" src="./assets/iluminado.jpg" width="400" height="200">
    <img style="float: left" alt="Titanic" src="./assets/titanic.jpg" width="400" height="200">

  </div>
</template>

<script>
import FilmeServices from './services/filmeservices'

export default {
  data() {
    return {
      narrativa: {
        id: '',
        nome: '',
        duracao:'',
        classificacao: '',
        categoria: '',
        nacionalidade: '',
        anoLancamento: ''
      },
      findNarrativa: {
        id: '',
        nome: '',
        duracao:'',
        classificacao: '',
        categoria: '',
        nacionalidade: '',
        anoLancamento: ''
      },
      filmes: []
    }
  },
  name: 'App',
  components: {
  },
  mounted() {
    this.listFilmes();
  },
  methods: {
    listFilmes() {
      FilmeServices.findAll().then(response => {
        this.filmes = response.data;
      });
    },
    saveAndUpdateNarrativa() {
      if(!this.narrativa.id) {
        FilmeServices.save(this.narrativa).then(response => {
          this.listFilmes();
          this.narrativa = {};
          alert("Filme cadastrado com sucesso!");
          return response;
        });
      } else {
        FilmeServices.update(this.narrativa).then(response => {
          this.listFilmes();
          this.narrativa = {};
          alert("Filme atualizado com sucesso!");
          return response;
      });
    }
  },
    deleteNarrativa(narrativa) {
      if(confirm("Deseja excluir o filme?")) {
        FilmeServices.delete(narrativa).then(response => {
        this.listFilmes();
        alert("Filme excluído com sucesso!");
        return response;
        });
      }
    },
    findById() {
      FilmeServices.findById(this.narrativa).then(response => {
        this.findNarrativa = response.data;
      });
    },
    updateNarrativa(narrativa) {
      this.narrativa = narrativa;
    },
    cancelUpdate() {
      this.narrativa = {};
      alert("Alteração cancelada!");
    }
  }
}
</script>

<style>
img {
  margin: 15px;
}
button {
  margin: 5px;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* margin-top: 60px; */
  /* background-color: #FCE4EC; */
  background-color: #82B1FF;
}
</style>
