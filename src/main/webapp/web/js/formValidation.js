/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function validateFormFields(){
    var firstName = document.getElementById("customerForm:first").value; //
    var lastName = document.getElementById("customerForm:last").value;  //
    var postcode = document.getElementById("customerForm:postcode").value;  //
    var address = document.getElementById("customerForm:address").value;    //
    var password = document.getElementById("customerForm:password").value;
    var medicare = document.getElementById("customerForm:medicareId").value;    //    
    var mobile = document.getElementById("customerForm:phone").value;   //
    var email = document.getElementById("customerForm:email").value;    

    alert(firstName+lastName+postcode+address+ password+medicare+mobile+email);
    var nameRegex = /^[a-zA-Z\-]+$/;

    if(firstName.length <= 15 && firstName.match(nameRegex)){
        return true;
    } else {
        alert("First name conatins only alphabet and upto 15 characters");
        document.customerForm.first.focus();    
        return false;
    }
    
    if(lastName.length <= 15 && lastName.match(nameRegex)){
        return true;
    } else {
        alert("Last name conatins only alphabet and upto 15 characters");
        document.customerForm.last.focus();    
        return false;
    }

    var postCodeRegex = /^\d{4}$/;
    if(postcode.match(postCodeRegex)) {
       return true;
    }
    else {
       alert("Post code contains 4 digits");
       document.customerForm.postcode.focus();    
       return false;
    }

    var addressRegex = /^[a-zA-Z0-9{30}]+$/i;
    if(address.match(addressRegex)) {
       return true;
    }
    else {
       alert("Adress contains aplhanumeric characters of length 30");
       document.customerForm.address.focus();    
       return false;
    }

    var passwordRegex = /^[a-zA-Z0-9]+$/i;
    if(password.match(passwordRegex) && (password.length >= 8 && password.length <= 16 )) {
       return true;
    }
    else {
       alert("Password contains aplhanumeric characters of length ");
       document.customerForm.password.focus();    
       return false;
    }

    var medicareRegex = /^\d{10}$/;
    if(medicare.match(medicareRegex)) {
       return true;
    }
    else {
       alert("Medicare number contains 10 digits");
       document.customerForm.medicareId.focus();    
       return false;
    }

    var mobileNumberRegex = /^\d{10}$/;
    if(mobile.match(mobileNumberRegex)) {
       return true;
    }
    else {
       alert("Phone number contains 10 digits");
       document.customerForm.phone.focus();    
       return false;
    }

    var chrbeforAt = email.substr(0, email.indexOf('@'));
    if (!($.trim(email).length > 127)) {
        if (chrbeforAt.length >= 2) {
            var re = /^(([^<>()[\]{}'^?\\.,!|//#%*-+=&;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;
            //var re = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;
            return re.test(email);
        } 
        else {
            return false;
        }
    }
    else {
        return false;
    }
}


    