
class ConversationForum {
   constructor() {
       this.photo = document.querySelector("#photo");
       this.forum = document.querySelector("#forum");
       this.message = document.querySelector("#message");
       
       
       this.name = new URL(document.URL).searchParams.get("name");
       this.loadImage(this.name);
       
       this.message.onchange = event => {
          fetch('api/messages/add?name=' + this.name,
            {
             method: 'POST', 
             body : JSON.stringify(new Message('Mikael',event.target.value)),
             headers: {'Content-Type' : 'application/json; charset=UTF-8'}
            })
           .then(response => response.json())
           .then(message => {
               this.message.value = "";
            });
       };
       
       this.worker = new Worker("worker.js");
       this.worker.postMessage({"name" : this.name});
       
       this.worker.onmessage = event => {
           this.forum.innerHTML = '';
           let ul = document.createElement('ul');
           event.data.map(message => {
              let li = document.createElement('li');
              li.innerHTML = `${message.user} - ${message.text}`;
              ul.appendChild(li);
           });
           this.forum.appendChild(ul);
           this.forum.scrollTop = this.forum.scrollHeight;
       }
       
   } 
   
   loadImage(name) {
       let img = document.createElement('img');
       img.src = 'api/store/' + name + '?width=200';
       this.photo.appendChild(img);
   }
   
}

class Message {
    constructor(user, text) {
        this.user = user;
        this.text = text;
        this.timestamp = null;
    }
}

let forum = new PhotoForum();


