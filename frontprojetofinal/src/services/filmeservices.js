import { http } from './api'

export default {

  findAll: () => {
    return http.get('filmes');
  },

  findById: (narrativa) => {
    return http.get(`filmes/${narrativa.id}`, { data: narrativa });
  },

  save: (narrativa) => {
    return http.post('filmes', narrativa);
  },

  delete: (narrativa) => {
    return http.delete(`filmes/${narrativa.id}`, { data: narrativa });
  },

  update: (narrativa) => {
    return http.put(`filmes`, narrativa);
  }
}
