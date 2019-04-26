function showPassword() {
    let x = document.getElementById("userPassword");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
    let y = document.getElementById("userPasswordCheck");
    if (y.type === "password") {
      y.type = "text";
    } else {
      y.type = "password";
    }
}

function login() {
    // if (document.getElementById("userName").value === "check database" && 
    // document.getElementById("userPassword").value === "check database") {
    //     document.getElementById("output").innerHTML = "Logged In";
    //     //login
    // } else {
    //     document.getElementById("output").innerHTML = "Not a match";
    // }
}

function register() {
    // if (document.getElementById("userName").value === "check database") {
    //     document.getElementById("output").innerHTML = "Username in use";
    // } else if (document.getElementById("userPassword").value !== document.getElementById("userPasswordCheck").value) {
    //     document.getElementById("output").innerHTML = "Passwords do not match";
    // } else {
    //     document.getElementById("output").innerHTML = "Registered";
    //     //login
    // }
}

function create() {
    let URL='http://localhost:8081/api/tasklist/tasks';
    let request = new XMLHttpRequest();
    request.open('POST', URL);
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader("Accept", "application/json");
    request.responseType = 'json';
    request.onload = function () {
        console.log(request.response);
    }
    let desc = document.getElementById("task").value;
    let pri = document.getElementById("priority").value;
    let stat = document.getElementById("status").value;
    let doby = document.getElementById("date").value;
    let body = {
                username: "sam",
                description: desc,
                priority: pri,
                status: stat,
                do_by: doby 
                };
    body = JSON.stringify(body);
    request.send(body);
}

function update() {
    
}

function remove() {
    let URL='http://localhost:8081/api/tasklist/tasks/' + document.getElementById().value;
    let request = new XMLHttpRequest();
    request.open('GET', URL);
    request.responseType = 'json';
    request.onload = function () {
        console.log(request.response);
        tasks = request.response;
        display();
    }
    request.send();
}

let tasks = "";
let URL='http://localhost:8081/api/tasklist/alltasks/sam';
let request = new XMLHttpRequest();
request.open('GET', URL);
request.responseType = 'json';
request.onload = function () {
    console.log(request.response)
    tasks = request.response;
    display();
}
request.send();

let x = 15;

function display() {
    let eleDesc;
    let elePri;
    let eleStat;
    let eleDo;
    let eleBut;
    for (let task of tasks) {
        eleDesc = document.createElement("div");
        eleDesc.setAttribute("id", "grid=item" + x++);
        eleDesc.innerHTML = task.description;
        document.getElementById("prevTasks").append(eleDesc);
        elePri = document.createElement("div");
        elePri.setAttribute("id", "grid=item" + x++);
        elePri.innerHTML = task.priority;
        document.getElementById("prevTasks").append(elePri);
        eleStat = document.createElement("div");
        eleStat.setAttribute("id", "grid=item" + x++);
        eleStat.innerHTML = task.status;
        document.getElementById("prevTasks").append(eleStat);
        eleDo = document.createElement("div");
        eleDo.setAttribute("id", "grid=item" + x++);
        eleDo.innerHTML = task.do_by;
        document.getElementById("prevTasks").append(eleDo);
        eleBut = document.createElement("div");
        eleBut.setAttribute("id", "grid=item" + x++);
        eleBut.innerHTML ='<button id="update" onclick="update();">update</button><button id="delete" onclick="remove();">delete</button>';
        document.getElementById("prevTasks").append(eleBut);
    }
}