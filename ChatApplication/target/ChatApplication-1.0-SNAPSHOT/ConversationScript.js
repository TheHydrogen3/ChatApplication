
class ConversationForum {
   constructor() {
       this.conversation = document.querySelector("#conversation");
       this.forum = document.querySelector("#forum");
       this.message = document.querySelector("#message");
       
       
       this.name = prompt("Please enter your username", "Username");
       
       this.message.onchange = event => {
          fetch('api/messages/add?name=' + this.name,
            {
             method: 'POST', 
             body : JSON.stringify(new Message(this.name,event.target.value)),
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

}

class Message {
    constructor(user, text) {
        this.user = user;
        this.text = text;
        this.timestamp = new Date();
    }
}

let forum = new ConversationForum();


