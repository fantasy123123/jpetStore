<%--
  Created by IntelliJ IDEA.
  User: 17817
  Date: 2019/10/15
  Time: 00:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../common/IncludeTop.jsp"%>
</div>
<br>
<div class="wrap">
    <ul class="list">
        <li class="item active"></li>
        <li class="item"></li>
        <li class="item"></li>
        <li class="item"></li>
        <li class="item"></li>
    </ul>
    <ul class="pointList">
        <li class="point active" data-index = 0></li>
        <li class="point" data-index = 1></li>
        <li class="point" data-index = 2></li>
        <li class="point" data-index = 3></li>
        <li class="point" data-index = 4></li>
    </ul>
    <button class="btn" id="leftBtn"> < </button>
    <button class="btn" id="rightBtn"> > </button>
</div>

<h1 align="center"><br></h1>

<div id="crewphoto">
    <div class="img_div">
        <a href="viewCategory?categoryId=DOGS">
            <img class="who"  src="images/dog.png" />
            <div class="mask">
                <h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DOGS</h1>
            </div>
        </a>
    </div>
    <div class="img_div">
        <a href="viewCategory?categoryId=CATS">
            <img class="who" src="images/cat.png" />
            <div class="mask">
                <h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CATS</h1>
            </div>
        </a>
    </div>
    <div class="img_div">
        <a href="viewCategory?categoryId=BIRDS">
            <img class="who" src="images/bird.png" />
            <div class="mask">
                <h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;BIRDS</h1>
            </div>
        </a>
    </div>

    <div>
        <h1><br></h1>
    </div>

    <div>
        <h1><br></h1>
    </div>

    <div>
        <h1><br></h1>
    </div>
    <div class="img_div">
        <a href="viewCategory?categoryId=REPTILES">
            <img class="who" src="images/Reptiles.png" />
            <div class="mask">
                <h1>&nbsp;&nbsp;REPTILES</h1>
            </div>
        </a>
    </div>
    <div class="img_div">

        <img class="who" src="images/maskLogo.png" />
        <%--<div class="mask">--%>
        <%--<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FISH</h1>--%>
        <%--</div>--%>

    </div>
    <div class="img_div">
        <a href="viewCategory?categoryId=FISH">
            <img class="who" src="images/fish.png" />
            <div class="mask">
                <h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FISH</h1>
            </div>
        </a>
    </div>
</div>
<div id="Main">


    <%--<div id="Mainimages">--%>
    <%--<div id="MainimagesContent">--%>
    <%--<map name="estoremap">--%>
    <%--<area alt="Birds" coords="179,118,405,300" href="viewCategory?categoryId=BIRDS" shape="rect" />--%>
    <%--<area alt="Fish" coords="805,395,1031,577" href="viewCategory?categoryId=FISH" shape="rect" />--%>
    <%--<area alt="Dogs" coords="60,250,130,320" href="viewCategory?categoryId=DOGS" shape="rect" />--%>
    <%--<area alt="Reptiles" coords="492,395,719,577" href="viewCategory?categoryId=REPTILES" shape="rect" />--%>
    <%--<area alt="Cats" coords="805,118,1031,300" href="viewCategory?categoryId=CATS" shape="rect" />--%>
    <%--<area alt="Birds" coords="179,395,407,577" href="viewCategory?categoryId=BIRDS" shape="rect" />--%>
    <%--</map>--%>
    <%--<img  src="/images/picture_main.png" align="middle" usemap="#estoremap"  height="600" width="1210" />--%>
    <%--</div>--%>
    <%--</div>--%>

    <div id="Separator">&nbsp;</div>
</div>

<div id="Welcome" >
    <div id="WelcomeContent">
        <br><br><br>Pets are friends of human beings. Please treat them kindly.
    </div>
</div>

<script type="text/javascript" src="js/main.js"></script>

<%@include file="../common/IncludeBottom.jsp"%>

