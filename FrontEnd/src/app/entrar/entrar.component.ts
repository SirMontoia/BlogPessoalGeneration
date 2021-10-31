
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { UserDTO } from '../model/UserDTO';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-entrar',
  templateUrl: './entrar.component.html',
  styleUrls: ['./entrar.component.css']
})
export class EntrarComponent implements OnInit {

  userDTO: UserDTO = new UserDTO() 
  
  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit(){
  
    window.scroll(0,0)
  
  }


  entrar(){
    this.auth.entrar(this.userDTO).subscribe((resp:UserDTO)=>{
       this.userDTO = resp
        environment.token = this.userDTO.token
        environment.nome = this.userDTO.nome
        environment.id = this.userDTO.idUsuario
        environment.foto = this.userDTO.foto
        environment.tipo = this.userDTO.tipo

        console.log(environment.token)
        console.log(environment.nome)
        console.log(environment.id)
        console.log(environment.foto)
        

       this.router.navigate(['/inicio'])
    } ,erro =>{
      if (erro.status == 400){
        alert('Usuário ou senha estão incorretos')
      }
    })
  }
}
