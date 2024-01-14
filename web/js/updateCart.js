var xhr;
function updateCart(){
    var quantityElements = document.getElementsByClassName('quantity');
    var i;
    var quantity = "";
    for(i=0;i<quantityElements.length;i++){

            quantity += quantityElements[i].value + ",";

    }
    //quantityElements[0].value = quantity;

    sendRequest("updateCartServlet?quantity="+ quantity);

}
function sendRequest(url) {
    xhr = new XMLHttpRequest();
    xhr.open("GET", url, true);
    xhr.onreadystatechange = processResponse;
    xhr.send(null);
}
function processResponse() {
    if(xhr.readyState==4){
        if(xhr.status==200){
            var resp = xhr.responseText;
            var array = resp.split(",");
            var quantityEle = document.getElementsByClassName('quantity');
            var total = document.getElementsByClassName('total');
            var subtotal = document.getElementById('subtotal');

            for(var i=0;i<quantityEle.length;i++) {
                if(array[3*i] < 1){
                    quantityEle[i].parentNode.removeChild(quantityEle[i]);
                }

                quantityEle[i].value = array[3*i];
                total[i].innerHTML = array[3*i+1];
            }
            subtotal.innerText = array[2];
        }
    }
}

