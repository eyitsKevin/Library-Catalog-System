import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { AppComponent } from "./app.component";
import { RegisterComponent } from "./registration/register.component";
import { HomePageComponent } from "./homepage/homepage.component";
import { RouterModule } from '@angular/router';
import { HeaderComponent } from "./header/header.component";
import { FooterComponent } from "./footer/footer.component";
import { TestComponent } from "./test/test.component";
import { MatFormFieldModule, MatButtonModule, MatInputModule, MatToolbarModule, MatMenuModule, MatDialogModule,
MatCardModule} from "@angular/material";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { routing } from './app.routing';
import { LoginComponent } from './login/login.component';
import { ConfirmationComponent } from "./registration/confirmation.component";
import { PasswordService } from "./_services/PasswordService";
import { RegistrationErrorComponent } from "./registration/registration_error.component";
import { UserService } from "./_services/user.service";

@NgModule({
   declarations: [
      AppComponent,
      HeaderComponent,
      FooterComponent,
      TestComponent,
      HomePageComponent,
      RegisterComponent,
      LoginComponent,
      ConfirmationComponent,
      RegistrationErrorComponent
   ],
   imports: [
      BrowserModule,
      BrowserAnimationsModule,
      MatFormFieldModule,
      MatInputModule,
      MatButtonModule,
      MatToolbarModule,
      MatMenuModule,
      MatDialogModule,
      MatCardModule,
      HttpClientModule,
      FormsModule,
      RouterModule.forRoot([]),
      routing,
      ReactiveFormsModule
   ],
   entryComponents: [
     LoginComponent,
     ConfirmationComponent,
     RegistrationErrorComponent
   ],
   providers: [UserService, PasswordService, UserService],
   bootstrap: [AppComponent]
})
export class AppModule { }