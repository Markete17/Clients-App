import { Component, OnInit } from '@angular/core';
import { RegionService } from '../../regions/region.service';
import { Region } from '../../regions/region';
import { FormGroup, FormControl } from '@angular/forms';
import { ClientService } from '../client.service';
import { Client } from '../client';

@Component({
  selector: 'app-clients-filter',
  templateUrl: './clients-filter.component.html',
  styleUrls: ['./clients-filter.component.css']
})
export class ClientsFilterComponent implements OnInit {

  regions: Region[]

  clientsForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl(''),
    regionId: new FormControl(''),
    createAt: new FormControl(''),
  });

  clients: Client[] = []

  constructor(private regionService:RegionService, private clientService:ClientService) { }

  ngOnInit(): void {
    this.loadRegions()
  }

  loadRegions(){
    this.regionService.getRegions().subscribe(
      regions => this.regions = regions
    )
  }

  onSubmit() {

    let params = new Map<string,any>()

    for (let key in this.clientsForm.value){
      let value = this.clientsForm.value[key]
      if(value!=undefined && value!=''){
        params.set(key,value)
      }
    }

    this.clientService.getClientsFilter(0,params).subscribe(
      clients => this.clients = clients
    )

  }

  reset() {
    this.clients = []
  }

  compareRegion(o1: Region, o2: Region) {
    return o1.id === o2.id;
}
}
