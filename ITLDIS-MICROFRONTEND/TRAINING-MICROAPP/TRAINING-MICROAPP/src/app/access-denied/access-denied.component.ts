import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Location } from '@angular/common';
@Component({
  selector: 'app-access-denied',
  templateUrl: './access-denied.component.html',
  styleUrls: ['./access-denied.component.css']
})
export class AccessDeniedComponent implements OnInit {

  constructor(
    private router:Router,
    private location:Location,
    private activiteRoute:ActivatedRoute,
  ) { }

  ngOnInit() {
  }


  goToDashBoad(){
      this.location.back();
  // let url=  this.router.navigate(['../../'], { relativeTo: this.activiteRoute });
  // console.log(url,'urls')
    // this.router.navigateByUrl('/dashboard')
  }

}
