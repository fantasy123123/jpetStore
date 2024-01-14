var xhr;
var msg = document.getElementById('isExistInfo1');
var Username = document.getElementById("username");
var Password1 = document.getElementById("password");
var Password2 = document.getElementById("repeatedPassword");
function usernameIsExist() {
    var username = document.getElementById('username').value;
    if (username!=="")
    {
        sendRequest("usernameIsExist?username=" + username);
    }
}
function sendRequest(url) {
    xhr = new XMLHttpRequest();
    xhr.open("GET", url, true);
    xhr.onreadystatechange = processResponse;
    xhr.send(null);
}
function processResponse() {
    if (xhr.readyState == 4) {
        if (xhr.status == 200) {
            var responseInfo = xhr.responseText;
            if(responseInfo == 'Exist'){
                msg.classList.add('okmsg');
                msg.innerText = "The user name is not available";
            }
            else if(responseInfo == 'Not Exist'){
                msg.classList.add('errormsg');
                msg.innerText = "";
            }
        }
    }
}

function checkUserName() {
    var username = Username.value;
    if(username === ""){
        var msg = document.getElementById('isExistInfo1');
        msg.classList.add('errormsg');
        msg.innerHTML = "The username cannot be blank.";
    }
    else if(username.length<=2||username.length>=11){
        var msg = document.getElementById('isExistInfo1');
        msg.classList.add('errormsg');
        msg.innerHTML = "The username words must between 3 and 10 ";
    }
}


function checkPassword(){
    var password1 = Password1.value;
    var password2 = Password2.value;
    if(password1!==password2 && password2!=="" &&password1!=="")
    {
        var msg1 = document.getElementById('isExistInfo2');
        msg1.classList.add('errormsg');
        msg1.innerHTML = "The two passwords are inconsistent.";
    }
    else if(password1===password2 && password2!=="" &&password1!=="")
    {
        if(password1.length<=4 ){
            var msg1 = document.getElementById('isExistInfo2');
            msg1.classList.add('cautionmsg');
            msg1.innerHTML = "Password Strength: Low";
        }
        else if(password1.length>=4){
            var msg1 = document.getElementById('isExistInfo2');
            msg1.classList.add('cautionmsg');
            msg1.innerHTML = "Password Strength: High";
        }
    }
}

window.onload=function(){
    Password1.addEventListener('focus',checkUserName,false);
    Password2.addEventListener('focus',checkUserName,false);
}

