import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";

@Component({
  selector: "app-authencation-password",
  templateUrl: "./authencation-password.component.html",
  styleUrls: ["./authencation-password.component.css"],
})
export class AuthencationPasswordComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit() {}
}
