import { Component, OnInit } from '@angular/core';
import { RegionService } from '../../regions/region.service';
import { Region } from '../../regions/region';
import { FormGroup, FormControl } from '@angular/forms';
import { ClientService } from '../client.service';
import { Client } from '../client';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';

@Component({
  selector: 'app-clients-filter',
  templateUrl: './clients-filter.component.html',
  styleUrls: ['./clients-filter.component.css']
})
export class ClientsFilterComponent implements OnInit {

  regions: Region[]

  paginator:any
  url:string = '/clients/filter/page'

  clientsForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl(''),
    regionId: new FormControl(''),
    createAt: new FormControl(''),
  });

  clients: Client[] = []

  parameters: Map<string,any>
  page: number

  constructor(private regionService:RegionService, private clientService:ClientService,private activatedRoute:ActivatedRoute, private router:Router) { }

  ngOnInit(): void {
    this.loadRegions()
    this.loadClients()
  }

  loadRegions(){
    this.regionService.getRegions().subscribe(
      regions => this.regions = regions
    )
  }

  loadClients(){
    this.activatedRoute.paramMap.subscribe(
      params => {
        let page = params.get('page')
        if(page!=null){
          this.page = +page
          this.activatedRoute.queryParamMap.subscribe(
            params => {
                let parameters = new Map<string,any>()
                for(let key of params.keys){
                  parameters.set(key,params.get(key))
                }
                console.log(parameters)
                this.clientService.getClientsFilter(this.page,parameters).subscribe(
                  response => {
                    this.clients = response.content as Client []
                    this.paginator = response
                  }
                )
            }
            
          )
        }
        }
    )
  }

  onSubmit() {

      this.parameters = new Map<string,any>()

      for (let key in this.clientsForm.value){
        let value = this.clientsForm.value[key]
        if(value!=undefined && value!=''){
          this.parameters[key] = value
        }
      }
      this.router.navigate(['/clients/filter/page/0'],{queryParams:this.parameters})
  }

  reset() {
    this.clients = []
    this.paginator = null
    this.page = null
    this.router.navigate(['/clients/filter/'])
  }

  compareRegion(o1: Region, o2: Region) {
    return o1.id === o2.id;
}
}
