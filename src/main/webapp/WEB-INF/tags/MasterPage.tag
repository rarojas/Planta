<%@tag description="Master Page" pageEncoding="UTF-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@attribute name="header" fragment="true" %> 
<%@attribute name="titlePage" fragment="true" %> 
<%@attribute name="app" fragment="true" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">       
        <meta http-equiv="X-UA-Compatible" content="IE=9" />
        <meta http-equiv="X-UA-Compatible" content="IE=10" />
        <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
        <title><jsp:invoke fragment="titlePage"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link href="<c:url value="/js/libs/twitter-bootstrap/css/bootstrap-theme.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/js/libs/twitter-bootstrap/css/bootstrap.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/js/libs/angular-loading-bar/loading-bar.min.css"/>" rel="stylesheet" type="text/css"/>   
        <script src="<c:url value="/js/libs/jquery/jquery.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/js/libs/d3/d3.v3.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/js/libs/nvd3/nv.d3.js"/>" type="text/javascript"></script>         
        <script src="<c:url value="/js/libs/angular.js/angular.js"/>"></script>    
        <script src="<c:url value="/js/libs/angular-directives-d3/angularjs-nvd3-directives.min.js"/>"></script>    
        <script src="<c:url value="/js/libs/n3-linecharts/line-chart.min.js"/>"></script>    
        <script src="<c:url value="/js/libs/angular.js/angular-resource.js"/>"></script>    
        <script src="<c:url value="/js/libs/angular.js/angular-route.min.js"/>"></script>    
        <script src="<c:url value="/js/app/app.js"/>"></script>            
        <script src="<c:url value="/js/app/services.js"/>"></script>  
        <script src="<c:url value="/js/app/routes.js"/>"></script>  
        <script src="<c:url value="/js/app/directives.js"/>"></script>     
        <script src="<c:url value="/js/libs/angular-loading-bar/loading-bar.min.js"/>"></script>     

        <script src="<c:url value="/js/libs/angular-strap/angular-strap.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/angular-strap/angular-strap.tpl.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/angular-ui-bootstrap/angular-ui-bootstrap.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/angular-ui-bootstrap/angular-ui-bootstrap-tpls.min.js"/>"></script>     




        <script src="<c:url value="/js/libs/jquery-noty/jquery.noty.packaged.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/underscore.js/underscore.js"/>"></script>  
        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>
        <jsp:invoke fragment="app"/>
        <style>
            body {
                padding-top: 30px; /* Required padding for .navbar-fixed-top. Change if height of navigation changes. */
            }

            .navbar-fixed-top .nav {
                padding: 10px 0;
            }

            .navbar-fixed-top .navbar-brand {
                padding: 0 15px;
            }

            @media(min-width:768px) {
                body {
                    padding-top: 70px; /* Required padding for .navbar-fixed-top. Change if height of navigation changes. */
                }

                .navbar-fixed-top .navbar-brand {
                    padding: 0;
                }
            }
        </style>
    </head> 

    <body ng-app="PlantaAPP">               
        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden" 
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">
                        <img style="width:112px" src="<c:url value="/img/Logos/Logo ok fondos de color RGB-03.png"/>" class="img-responsive" width="100" alt=""/>
                    </a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="#/NuevaPrueba">Nueva</a>
                        </li>
                        <li>
                            <a href="#/Pruebas">Pruebas</a>
                        </li>
                        <li>
                            <a href="#/NuevoArranque">Nuevo Arranque</a>
                        </li>
                        <li>
                            <a href="#/PruebasArranque">Pruebas Arranque</a>
                        </li>
                        <li>
                            <a href="#/Clientes">Clientes</a>
                        </li>
                        <li>
                            <a href="#/Motores" class="dropdown-toggle">Motores</a>                                        
                        </li>
                        <li>
                            <a href="#/Incidencias" class="dropdown-toggle">Incidencias</a>                                        
                        </li>
                        <li>
                            <a href="#/Usuarios" class="dropdown-toggle">Usuarios</a>                                        
                        </li> 
                        <li>
                            <a href="#/Ubicaciones" class="dropdown-toggle">Ubicaciones</a>                                        
                        </li> 
                        <li>
                            <a href="#/Carriles" class="dropdown-toggle">Carriles</a>                                        
                        </li> 
                        <li>
                            <a href="#/Kits" class="dropdown-toggle">Kits</a>                                        
                        </li> 
                    </ul>                                
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#/MisDatos" >
                                <security:authorize access="isAuthenticated()">
                                    Bienvenid@: <security:authentication property="principal.username" /> 
                                </security:authorize>
                            </a>
                        </li>                        <li>
                            <a href="#" style="padding:0px">
                                <img src="<c:url value="/img/Selmec.png"/>" class="img-responsive" width="150" alt=""/>
                            </a>
                        </li>                        
                        <li>
                            <security:authorize access="isAuthenticated()">

                                <a href="javascript:formSubmit()"> Logout</a>
                            </security:authorize>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <div class="container-fluid"> 
            <div class='col-md-12'>
                <jsp:doBody/>
            </div>
        </div>
        <!--    <div id="pagefooter" style=" background-image:url('../../Content/images/lgrey035.jpg');">
              <p id="copyright">**** Copyright 1927, Future Bits When There Be Bits Inc. ***</p>
              jsp:invoke fragment="footer"/
            </div>-->
    </body>  
</html>