import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Postagem } from '../model/Postagem';

@Injectable({
  providedIn: 'root'
})
export class PostagemService {

  constructor(private http: HttpClient) { }

  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  getAllPostagens(): Observable<Postagem[]>{
    return this.http.get<Postagem[]>('https://blogpessoal29.herokuapp.com/api/v1/postagem/todas', this.token)
  }

  getByIdPostagem(idPostagem: number): Observable<Postagem>{
    return this.http.get<Postagem>(`https://blogpessoal29.herokuapp.com/api/v1/postagem/${idPostagem}`, this.token)
  }

  postPostagem(postagem: Postagem): Observable<Postagem>{
    return this.http.post<Postagem>('https://blogpessoal29.herokuapp.com/api/v1/postagem/salvar', postagem, this.token)
  }

  putPostagem(postagem: Postagem): Observable<Postagem>{
    return this.http.put<Postagem>('https://blogpessoal29.herokuapp.com/api/v1/postagem/atualizar', postagem, this.token)
  }

  deletePostagem(idPostagem:number) {
    return this.http.delete(`https://blogpessoal29.herokuapp.com/api/v1/postagem/deletar/${idPostagem}`, this.token)
  }

}
