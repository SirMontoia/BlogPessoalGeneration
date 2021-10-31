import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { AlertasComponent } from '../alertas/alertas.component';
import { Postagem } from '../model/Postagem';
import { Tema } from '../model/Tema';
import { User } from '../model/User';
import { AlertasService } from '../service/alertas.service';
import { AuthService } from '../service/auth.service';
import { PostagemService } from '../service/postagem.service';
import { TemaService } from '../service/tema.service';


@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  postagem: Postagem = new Postagem
  listaTemas : Tema[]
  id: number
  tema: Tema = new Tema
  listaPostagens: Postagem[]
  tituloPost: string 
  nomeTema: string
  
  user: User = new User
  idUser = environment.id

  key = 'data'
  reverse = true



  constructor(

    private router: Router,
    private postagemService: PostagemService,
    private temaService: TemaService,
    public authService: AuthService,
    private alertas: AlertasService

  ) { }

  ngOnInit() {
 
    window.scroll(0,0)

    if(environment.token == ''){
      alert ('Sua sessão expirou, faça o login novamente')
      this.router.navigate(['/entrar'])
    
    
    }
 
    this.getAllTemas()
    this.getAllPostagens()
  }

  getAllTemas(){
    this.temaService.getAllTema().subscribe((resp: Tema[])=>{
      this.listaTemas = resp 
    })
  }

  findById(){
    this.temaService.getByIdTema(this.id).subscribe((resp: Tema)=>{
      this.tema = resp 
    })
  }


  getAllPostagens(){
    this.postagemService.getAllPostagens().subscribe((resp: Postagem[])=>{
      this.listaPostagens = resp 
    })
  }


  findByIdUser(){
    this.authService.getByIdUser(this.idUser).subscribe((resp: User)=>{
      this.user = resp
    })
  }



  
  publicar(){

    this.tema.idTema = this.id
    this.postagem.temaRelacionado = this.tema

    this.user.idUsuario = this.idUser
    this.postagem.criador = this.user

    this.postagemService.postPostagem(this.postagem).subscribe((resp: Postagem)=>{
      this.postagem = resp 
      this.alertas.showAlertSuccess('Postagem realizada com sucesso!')
      this.postagem = new Postagem
      this.getAllPostagens()
    })

  }


  findByTituloPostagem(){

      if(this.tituloPost == ''){
        this.getAllPostagens()
      }else{
        
      this.postagemService.getByTituloPostagem(this.tituloPost).subscribe((resp:Postagem[])=>{ 
        this.listaPostagens = resp
      })
    }
      }


      findByNomeTema(){

        if (this.nomeTema == ''){
          this.getAllTemas
        }else{
          this.temaService.getByTema(this.nomeTema).subscribe((resp:Tema[])=>{
            this.listaTemas = resp
          })
        }


      }



}
