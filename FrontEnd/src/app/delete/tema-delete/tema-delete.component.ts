import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Tema } from './../../model/Tema';
import { TemaService } from './../../service/tema.service'

@Component({
  selector: 'app-tema-delete',
  templateUrl: './tema-delete.component.html',
  styleUrls: ['./tema-delete.component.css']
})
export class TemaDeleteComponent implements OnInit {

  tema: Tema = new Tema
  idTema: number


  constructor(
    private temaService: TemaService,
    private router: Router,
    private route: ActivatedRoute

  ) { }

  ngOnInit() {

    if(environment.token == ''){
      this.router.navigate(['/entrar'])
    }

    this.idTema = this.route.snapshot.params['id']
    this.findByIdTema( this.idTema)

  }


  findByIdTema(idTema:number){
    this.temaService.getByIdTema(idTema).subscribe((resp: Tema)=>{
      this.tema = resp
    })
  }

apagar(){
  this.temaService.deleteTema( this.idTema).subscribe(()=>{
    alert ('Tema apagado com sucesso!')
    this.router.navigate(['/tema'])
  })
}


}
