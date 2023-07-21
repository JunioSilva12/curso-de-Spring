// Call the dataTables jQuery plugin
$(document).ready(function() {


  loadUsers();
  updateUserInfo ();
  $('#users').DataTable();
});


function updateUserInfo (){
  if (localStorage.email) {
    document.querySelector('#user-mail').textContent=localStorage.email;
   }
}
async function loadUsers (){
    const req = await fetch('api/users', {
        method: 'GET',
        headers: getHeaders()

      });
      const users = await req.json();

      console.log(users);
   const tbody =  document.querySelector('#users tbody');

   const fragmento = document.createDocumentFragment();
   users.forEach(user =>{
   contenido=document.createElement('tr');
  let btnDelete =`<a href="#" onclick="deleteUser(${user.id});"  class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>`

   id=document.createElement('td');
   n=document.createElement('td');
   ln=document.createElement('td');
   e=document.createElement('td');
   np=document.createElement('td');
   ac=document.createElement('td');
   n.textContent =`${user.name}`;
   id.textContent =`${user.id}`;
   ln.textContent =`${user.lastname}`;
   e.textContent =`${user.email}`;
   np.textContent =`${user.number_phone}`;
   ac.innerHTML =btnDelete;

    contenido.appendChild(id);
    contenido.appendChild(n);
    contenido.appendChild(ln);
    contenido.appendChild(e);
    contenido.appendChild(np);
    contenido.appendChild(ac);


   fragmento.appendChild(contenido);
   //console.log(user)


   });
  //console.log(fragmento);
  tbody.appendChild(fragmento);
  //console.log(tbody);
  }

  function getHeaders(){
    let headers
    if (localStorage.token) {        
      headers = {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization':localStorage.token
    }}else{
       headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization':''
      }
    }


return headers
  }


async function deleteUser(id){
console.log("eliminando bro:"+id);
if(confirm("¿Desea elimina usuario?"))
{
  const req = await fetch('api/users/'+id, {
         method: 'DELETE',
         headers:getHeaders()

       });
await location.reload();//PARA RECARGAR LA PAGINA
}else{
console.log("eliminacion cancelada");
}
}