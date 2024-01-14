
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <%--  bootstrap配置 --%>
    <meta charset="utf-8">
    <%--     Sign in   --%>
    <%--    <meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
    <%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <%--   轮播     --%>
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <!-- Bootstrap core CSS -->
    <%--    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">--%>
    <!--轮播-->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>
        /* Make the image fully responsive */
        .myCaroudel {
            width: 40%;
            height: 66%;
            float: left;
            margin-top: 3%;
            margin-bottom: 2%;
            margin-left: 8%;
            margin-right: 4%;
        }
        .carousel-inner img{
            width: 100%;
            height: 100%;
        }
    </style>

    <link rel="StyleSheet" href="css/jpetstore.css" type="text/css"
          media="screen" />
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/dialog.css" type="text/css">
    <link rel="stylesheet" href="css/searchProduct.css" type="text/css" media="screen">

    <meta name="generator"
          content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
    <title>MyPetStore</title>
    <meta content="text/html; charset=windows-1252"
          http-equiv="Content-Type" />
    <meta http-equiv="Cache-Control" content="max-age=0" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="Pragma" content="no-cache" />
</head>

<body>

<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="main" style="width: 100% ;height: 100%"><img src="images/logo-topbar.png" /></a>
        </div>
    </div>

    <div id="SearchP">
        <div id="SearchContent">
            <form action="searchProducts" method="post">
                <img src="images/search.png" aria-hidden="true" id="searchLogo"></img>
                <input type="text" class="input-box header-searchLine" id="keyword" name="keyword" size="14" />
                <div class="auto hidden" id="auto">
                    <div class="auto_out">1</div>
                    <div class="auto_out">2</div>
                </div>

                <input type="submit" name="searchProducts" id="header-searchBtn" value="Search" />
                <script type="text/javascript" src="js/searchProduct.js"></script>
            </form>
        </div>
    </div>

    <div id="Menu">
        <div id="MenuContent">
            <div class="myCart">
                <a href="viewCart">MYCART&nbsp;&nbsp;<img align="middle" name="img_cart" src="images/cart.gif" /></a>
            </div>
            <%--            <a href="viewCart"><img align="middle" name="img_cart" src="images/cart.gif" /></a>--%>
            <%--            <img align="middle"  src="images/separator.gif" />--%>

            <c:if test="${sessionScope.isSignIn == false}">
                <a href="newAccountForm">Sign up</a>
                <img align="middle" src="images/separator.gif" />
                <a href="loginForm?path=${pageContext.request.servletPath}">Sign in</a>
                <img align="middle" src="images/separator.gif" />
            </c:if>

            <c:if test="${sessionScope.isSignIn == true}">
                <a href="signOut">Sign Out</a>
                <img align="middle" src="images/separator.gif" />
                <a href="viewMyAccount">My Account</a>
                <img align="middle" src="images/separator.gif" />
            </c:if>

            <a href="/help.html">?</a>
        </div>

        <%--    <div id="Menu">--%>
        <%--        <div id="MenuContent">--%>
        <%--            <div class="myCart">--%>
        <%--            MYCART&nbsp;&nbsp;<a href="viewCart"><img align="middle" name="img_cart" src="images/cart.gif" /></a>--%>
        <%--            </div>--%>

        <%--&lt;%&ndash;            <img align="middle"  src="images/separator.gif" />&ndash;%&gt;--%>
        <%--            <div class="dropdown myAccount">--%>
        <%--                <button class="dropbtn">My Account</button>--%>
        <%--                <div class="dropdown-content">--%>
        <%--                    <c:if test="${sessionScope.isSignIn == false}">--%>
        <%--                        <a href="newAccountForm">Sign up</a>--%>
        <%--&lt;%&ndash;                        <img align="middle" src="images/separator.gif" />&ndash;%&gt;--%>
        <%--                        <a href="loginForm">Sign in</a>--%>
        <%--&lt;%&ndash;                        <img align="middle" src="images/separator.gif" />&ndash;%&gt;--%>
        <%--                    </c:if>--%>

        <%--                    <c:if test="${sessionScope.isSignIn == true}">--%>
        <%--                        <a href="signOut">Sign Out</a>--%>
        <%--&lt;%&ndash;                        <img align="middle" src="images/separator.gif" />&ndash;%&gt;--%>
        <%--                        <a href="viewMyAccount">My Account</a>--%>
        <%--&lt;%&ndash;                        <img align="middle" src="images/separator.gif" />&ndash;%&gt;--%>
        <%--                    </c:if>--%>
        <%--                </div>--%>
        <%--            </div>--%>

        <%--             <a href="/help.html">?</a>--%>
        <%--        </div>--%>
        <%--    </div>--%>

        <%--    <div id="QuickLinks">--%>
        <%--        <a href="viewCategory?categoryId=FISH"><img src="/images/sm_fish.gif" /></a>--%>
        <%--        <img src="/images/separator.gif" />--%>
        <%--        <a href="viewCategory?categoryId=DOGS"><img src="/images/sm_dogs.gif" /></a>--%>
        <%--        <img src="/images/separator.gif" />--%>
        <%--        <a href="viewCategory?categoryId=REPTILES"><img src="/images/sm_reptiles.gif" /></a>--%>
        <%--        <img src="/images/separator.gif" />--%>
        <%--        <a href="viewCategory?categoryId=CATS"><img src="/images/sm_cats.gif" /></a>--%>
        <%--        <img src="/images/separator.gif" />--%>
        <%--        <a href="viewCategory?categoryId=BIRDS"><img src="/images/sm_birds.gif" /></a>--%>
        <%--    </div>--%>

    </div>