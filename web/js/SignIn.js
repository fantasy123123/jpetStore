var xhr;
var elUsername = document.getElementById("username");
var elPassword = document.getElementById("password");
var msg = document.getElementById('isExistInfo');

var html = "<div class='slide-box' id='TheWholeBlock'>" +
    "<div class='slide-img-block'>" +
    "   <div class='slide-loading'></div>" +
    "   <div class='slide-img-border'>" +
    "       <div class='scroll-background slide-top'></div>" +
    "       <div class='slide-img-div'>" +
    "           <div class='slide-img-nopadding'>" +
    "               <img class='slide-img' id='slideImg' src='' />" +
    "               <div class='slide-block' id='slideBlock'></div>" +
    "               <div class='slide-box-shadow' id='cutBlock'></div>" +
    "           </div>" +
    "           <div class='scroll-background  slide-img-hint-info' id='slideHintInfo'>" +
    "               <div class='slide-img-hint'>" +
    "                   <div class='scroll-background slide-icon' id='slideIcon'></div>" +
    "                   <div class='slide-text'><span class='slide-text-type' id='slideType'></span>" +
    "                       <span class='slide-text-content' id='slideContent'></span>" +
    "                   </div>" +
    "               </div>" +
    "           </div>" +
    "       </div>" +
    "<div class='scroll-background slide-bottom'>" +
    "<div class='scroll-background slide-bottom-refresh' id='refreshBtn' title='更换图片'></div>" +
    "<div class='slide-bottom-no-logo'></div>" +
    "</div>" +
    "</div>" +
    "</div>" +
    "<div class='scroll-background scroll-bar'>" +
    "<div class='scroll-background slide-btn' id='slideBtn'></div>" +
    "<div class='slide-title' id='slideHint'> <-按住滑块，拖动完成上面拼图</div>" +
    "</div>" +
    "</div>" +
    "<script type=\"text/javascript\" src=\"/js/slider/style.js\"></script>" +
    " <script type=\"text/javascript\">" +
    "  var dataList = [\"0\",\"1\"];" +
    "  var options = {" +
    "   dataList: dataList," +
    "   success:function(){" +
    "    console.log(\"show\");" +
    "   }," +
    "   fail: function(){" +
    "    console.log(\"fail\");" +
    "   }" +
    "  };" +
    "  SliderBar(\"slideBar\", options);" +
    " </script>";
msg.style.left = elUsername.offsetLeft;

function usernameIsExist() {
    var username = elUsername.value;
     xhr = new XMLHttpRequest();
     xhr.onreadystatechange = process;
     xhr.open("GET", "usernameIsExist?username=" + username, true);
     xhr.send(null);
}

elUsername.addEventListener('blur',usernameIsExist,false);
function process() {
    if(xhr.readyState == 4){
        if(xhr.status == 200) {
            var responseInfo = xhr.responseText;
            if (responseInfo == 'Exist') {
                msg.classList.add('okmsg');
                msg.innerText = "";
            } else if (responseInfo == 'Not Exist') {
                msg.classList.add('errormsg');
                msg.innerText = 'User name does not exist';
            } else if (responseInfo == 'NULL') {
                msg.classList.add('nullmsg');
                msg.innerText = 'Username can not be blank';
            }
        }
    }
}
document.getElementById("loginForm").onsubmit = verify;

function verify(){
    var d = dialog({
        title: 'Verification',
        content: html,
        fixed: false,
        drag: true
    });
    d.show();
    return false;
}
