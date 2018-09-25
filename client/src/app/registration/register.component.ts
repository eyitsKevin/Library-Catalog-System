import { Component, OnInit } from "@angular/core";
import {FormControl, Validators, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"]
})
export class RegisterComponent implements OnInit {
  email = new FormControl('', [Validators.required, Validators.email]);

  ngOnInit() {}

  register() {

  }

  getEmailErrorMessage() {
    return this.email.hasError('required') ? 'You must enter a value' :
        this.email.hasError('email') ? 'Not a valid email' :
            '';
  }

}

