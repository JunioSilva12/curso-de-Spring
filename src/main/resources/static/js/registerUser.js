// Call the dataTables jQuery plugin
$(document).ready(function() {

//on ready
  });
  
  async function registerUser(){

    if(!confirm("¿Desea registrar el usuario?"))
    { return;}

      const userName = document.querySelector("#nameReg").value;
      const userLastname = document.querySelector("#lastNameReg").value;
      const userNumber_phone = document.querySelector("#numberPhoneReg").value;
      const  userEmail= document.querySelector("#emailReg").value;
      const userPassword = document.querySelector("#passwordReg").value;
      const repetPassword = document.querySelector("#repeatpassword").value;
      if(repetPassword ==userPassword)
      {
        

      newUser={}
      newUser.name=userName;
      newUser.lastname=userLastname;
      newUser.email=userEmail;
      newUser.number_phone=userNumber_phone;
      newUser.password=userPassword;


      const req = await fetch('api/users', {//igual que la de get solo cambia es el metodo
          method: 'POST',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(newUser)
        });
       // const users = await req.json();
          //console.log(users);
          console.log(req);

          if (req.ok) {
            alert("registrado con éxito");
            window.location.pathname="login.html";
          }
       
        
    }else{
        alert("las contraseñas no coinciden")
    }
    }
    // si el status es 200 pasa esto href="login.html"
