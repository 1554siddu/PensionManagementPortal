import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PensionDetail } from 'src/app/models/pension-detail';
import { PensionerInput } from 'src/app/models/pensioner-input';
import { AuthService } from 'src/app/services/auth.service';
import { ProcessPensionService } from 'src/app/services/process-pension.service';

@Component({
  selector: 'app-pension-details',
  templateUrl: './pension-details.component.html',
  styleUrls: ['./pension-details.component.css']
})

export class PensionDetailsComponent implements OnInit {

  constructor(
    private pservice: ProcessPensionService,
    private authservice: AuthService,
    private router: Router,
  ) { }

  msg: string = ''
  name: String=''
  dateOfBirth: Date= new Date()
  pan:String=''
  pensionType: String=''
  pensionAmount:number =123
  bankServiceCharge:number=123
  entable: boolean= false
  color: String = ''
  fieldErrors: string[] = []
  types = ["Self", "Family"]

  ngOnInit(): void {
  }

  handleReset() {
    this.msg = ""
    this.name=""
    this.dateOfBirth
    this.pan=''
    this.pensionType=''
     this.pensionAmount
    this.bankServiceCharge
    this.entable=false
    this.fieldErrors = []
  }
  enabletable(){
    this.entable=true

  }

  pensionDetails = new PensionDetail("", new Date, "", "", 0.0,0.0)
  pensionerInput = new PensionerInput("")

  handlePensionerInput() {
    console.log(this.pensionerInput);

    this.pservice.getPensionDetails(this.pensionerInput)
      .subscribe(
        data => {
          this.color = "text-success"
          this.pensionDetails = data
          this.msg = "pension details\n" +"name:"+ this.pensionDetails.name+"\nDOB:"+this.pensionDetails.dateOfBirth+"\nPAN:"+this.pensionDetails.pan+"\nPension type:"+this.pensionDetails.pensionType+"\nPension amount"+this.pensionDetails.pensionAmount+"\nPensionCharge"+this.pensionDetails.bankServiceCharge
          this.name=this.pensionDetails.name
          this.dateOfBirth=this.pensionDetails.dateOfBirth
          this.pan=this.pensionDetails.pan
          this.pensionType=this.pensionDetails.pensionType
          this.pensionAmount=this.pensionDetails.pensionAmount
          this.bankServiceCharge=this.pensionDetails.bankServiceCharge
        },
        error => {
          try {
            // get the errors thrown by the server
            this.fieldErrors = error.error.fieldErrors;
            console.log(this.fieldErrors);
            if (this.fieldErrors.length == 1) {
              this.logoutIfTokenExpired(this.fieldErrors[0])
            }
          } catch (e) {
            // feign error if field error can't be parsed ...
            this.msg = "Service is down, please try again later..."
            this.color = "text-danger"
            console.log(this.msg);
          }
        }
      );
  }

  logoutIfTokenExpired(error: String) {
    if (error.includes("expired")) {
      alert("Your session has been expired... Logging out!");
      this.authservice.logout();
    }
  }
}
