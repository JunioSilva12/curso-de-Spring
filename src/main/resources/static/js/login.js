// Call the dataTables jQuery plugin
$(document).ready(function() {

    //on ready
      });
      
      async function iniciarSession(){
    
       
        
          
          const  userEmail= document.querySelector("#emailLog").value;
          const userPassword = document.querySelector("#passwordLog").value;
      newUser={}     
      newUser.email=userEmail;
      newUser.password=userPassword;
        
    
      console.log(newUser);
          const req = await fetch('api/login', {//igual que la de get solo cambia es el metodo
              method: 'POST',
              headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
              },
              body: JSON.stringify(newUser)
            });
            const res = await req.text();
            console.log(res);
            if (res != "FAIL") {
                localStorage.token=res;
                localStorage.email=newUser.email;
                window.location.href="users.html"
                console.log("se inicion session");
            }else{
                alert("credenciales invalidas");
            }




        }
        

