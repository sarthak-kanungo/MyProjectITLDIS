import { Component, OnInit } from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-login-images',
  templateUrl: './login-images.component.html',
  styleUrls: ['./login-images.component.scss'],
  providers: [NgbCarouselConfig]
})
export class LoginImagesComponent implements OnInit {
  carouselArray = ['./assets/carousel-img1.png', './assets/carousel-img2.png', './assets/carousel-img3.png', './assets/carousel-img4.png', './assets/carousel-img5.png'];
  imgPath = './assets/dms-banner-temp-image.png'
  imgWd = 100;
  imgHt = 450;
  constructor(config: NgbCarouselConfig) {
    config.interval = 3000;
    config.showNavigationArrows = false;
    config.showNavigationIndicators = true;
    config.pauseOnHover = false;
  }

  ngOnInit() {
  }

}
