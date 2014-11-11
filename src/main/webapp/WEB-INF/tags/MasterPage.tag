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

        <script src="<c:url value="/js/libs/jquery/jquery.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/js/libs/d3/d3.v3.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/js/libs/nvd3/nv.d3.js"/>" type="text/javascript"></script>         
        <!--        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.1/angular.min.js  " type="text/javascript"></script>                         -->
        <script src="<c:url value="/js/libs/angular.js/angular.min.js"/>"></script>    
        <script src="<c:url value="/js/libs/angular.js/i18n/angular-locale_es-mx.js"/>"></script> 
        <script src="<c:url value="/js/libs/angular-directives-d3/angularjs-nvd3-directives.min.js"/>"></script>    
        <script src="<c:url value="/js/libs/n3-linecharts/line-chart.min.js"/>"></script>    
        <script src="<c:url value="/js/libs/angular.js/angular-resource.min.js"/>"></script>    
        <script src="<c:url value="/js/libs/angular.js/angular-cookies.min.js"/>"></script>    
        <script src="<c:url value="/js/libs/angular.js/angular-animate.min.js"/>"></script>    
        <script src="<c:url value="/js/libs/angular.js/angular-route.min.js"/>"></script>    
        <script src="<c:url value="/js/libs/angular.js/angular-touch.min.js"/>"></script>    
        <script src="<c:url value="/js/app/app.js"/>"></script>            
        <script src="<c:url value="/js/app/services.js"/>"></script>  
        <script src="<c:url value="/js/app/routes.js"/>"></script>  
        <script src="<c:url value="/js/app/directives.js"/>"></script>     

        <script src="<c:url value="/js/libs/moment.js/moment.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/moment.js/langs.min.js"/>"></script> 
        <script src="<c:url value="/js/angular-moment/angular-moment.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/angular-loading-bar/loading-bar.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/angular-slider/angular-slider.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/twitter-bootstrap/js/bootstrap.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/angular-strap/angular-strap.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/angular-strap/angular-strap.tpl.min.js"/>"></script> 

        <script src="<c:url value="/js/libs/angular-ui-bootstrap/angular-ui-bootstrap.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/angular-ui-bootstrap/angular-ui-bootstrap-tpls.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/jquery-noty/jquery.noty.packaged.min.js"/>"></script>     
        <script src="<c:url value="/js/libs/angular-gantt/angular-gantt.js"/>"></script>     
        <script src="<c:url value="/js/libs/angular-gantt/angular-gantt-plugins.js"/>"></script>     
        <script src="<c:url value="/js/libs/underscore.js/underscore.js"/>"></script>  
        
        <link href="<c:url value="/js/libs/twitter-bootstrap/css/bootstrap-theme.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/js/libs/twitter-bootstrap/css/bootstrap.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/js/libs/angular-loading-bar/loading-bar.css"/>" rel="stylesheet" type="text/css"/>   
        <link href="<c:url value="/js/libs/angular-gantt/gantt.css"/>" rel="stylesheet" type="text/css"/>   
        <link href="<c:url value="/css/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css"/>   
        <link href="<c:url value="/css/sb-admin.css"/>" rel="stylesheet" type="text/css"/>   
        <link href="<c:url value="/css/typehead.css"/>" rel="stylesheet" type="text/css"/>   
        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
            $(function () {
                $("#menu-toggle").click(function (e) {
                    e.preventDefault();
                    $("#wrapper").toggleClass("toggled");
                    $(".side-nav").toggleClass("toggled");
                });
            });

        </script>
        <jsp:invoke fragment="app"/>        
    </head> 

    <body ng-app="PlantaAPP">               
        <div id="wrapper" class="toggled">

            <!-- Navigation -->
            <nav class="navbar navbar-default  navbar-fixed-top" role="navigation">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button"  class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href id="menu-toggle">
                        <img style="width:150px" src="<c:url value="/img/EV.png"/>" class="img-responsive hidden-xs" alt=""/>
                    </a>                    
                </div>
                <!-- Top Menu Items -->
                <ul class="nav navbar-right top-nav">
                    <!--                    <li class="dropdown">
                         <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
                         <ul class="dropdown-menu message-dropdown">
                             <li class="message-preview">
                                 <a href="#">
                                     <div class="media">
                                         <span class="pull-left">
                                             <img class="media-object" src="http://placehold.it/50x50" alt="">
                                         </span>
                                         <div class="media-body">
                                             <h5 class="media-heading"><strong>John Smith</strong>
                                             </h5>
                                             <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                             <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                         </div>
                                     </div>
                                 </a>
                             </li>
                             <li class="message-preview">
                                 <a href="#">
                                     <div class="media">
                                         <span class="pull-left">
                                             <img class="media-object" src="http://placehold.it/50x50" alt="">
                                         </span>
                                         <div class="media-body">
                                             <h5 class="media-heading"><strong>John Smith</strong>
                                             </h5>
                                             <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                             <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                         </div>
                                     </div>
                                 </a>
                             </li>
                             <li class="message-preview">
                                 <a href="#">
                                     <div class="media">
                                         <span class="pull-left">
                                             <img class="media-object" src="http://placehold.it/50x50" alt="">
                                         </span>
                                         <div class="media-body">
                                             <h5 class="media-heading"><strong>John Smith</strong>
                                             </h5>
                                             <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                             <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                         </div>
                                     </div>
                                 </a>
                             </li>
                             <li class="message-footer">
                                 <a href="#">Read All New Messages</a>
                             </li>
                         </ul>
                     </li>
                     <li class="dropdown">
                         <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b class="caret"></b></a>
                         <ul class="dropdown-menu alert-dropdown">
                             <li>
                                 <a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
                             </li>
                             <li>
                                 <a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
                             </li>
                             <li>
                                 <a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
                             </li>
                             <li>
                                 <a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
                             </li>
                             <li>
                                 <a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
                             </li>
                             <li>
                                 <a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
                             </li>
                             <li class="divider"></li>
                             <li>
                                 <a href="#">View All</a>
                             </li>
                         </ul>
                     </li>-->
                    <li class="dropdown">
                        <a href class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>
                            Bienvenid@ {{user.username}} <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#"><i class="fa fa-fw fa-user"></i> Cuenta</a>
                            </li>
                            <!--                            <li>
                                                            <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                                                        </li>
                                                        <li>
                                                            <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                                                        </li>-->
                            <li class="divider"></li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-power-off"></i> Cerrar Sesión</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href style="padding:0px">
                            <img src="<c:url value="/img/LogoSelmec.png"/>" class="img-responsive" style="width:150px" alt=""/>
                        </a>
                    </li>
                </ul>
                <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
                <div bs-navbar class="collapse navbar-collapse navbar-ex1-collapse" >
                    <ul class="nav navbar-nav side-nav toggled">                        
                        <li data-match-route="/NuevaPrueba">
                            <a href="#/NuevaPrueba">                                
                                Nueva
                            </a>
                        </li>
                        <li data-match-route="/Pruebas">
                            <a href="#/Pruebas">Pruebas</a>
                        </li>
                        <li data-match-route="/ProgramacionPruebasEnsamble">
                            <a href="#/ProgramacionPruebasEnsamble">Programación de Pruebas de Ensamble </a>
                        </li>
                        <li data-match-route="/ProgramacionPruebasArranque">
                            <a href="#/ProgramacionPruebasArranque">Programación de Pruebas de Arranques</a>                            
                        </li>
                        <li data-match-route="/NuevoArranque">
                            <a href="#/NuevoArranque">Nuevo Arranque</a>
                        </li>
                        <li data-match-route="/PruebasArranque">
                            <a href="#/PruebasArranque">Pruebas Arranque</a>
                        </li>
                        <li data-match-route="/Clientes">
                            <a href="#/Clientes">Clientes</a>
                        </li>
                        <li data-match-route="/Motores">
                            <a href="#/Motores" class="dropdown-toggle">Motores</a>                                        
                        </li>
                        <li data-match-route="/Incidencias">
                            <a href="#/Incidencias" class="dropdown-toggle">Incidencias</a>                                        
                        </li>
                        <li data-match-route="/Usuarios">
                            <a href="#/Usuarios" class="dropdown-toggle">Usuarios</a>                                        
                        </li> 
                        <li data-match-route="/Ubicaciones">
                            <a href="#/Ubicaciones" class="dropdown-toggle">Ubicaciones</a>                                        
                        </li> 
                        <li data-match-route="/Carriles">
                            <a href="#/Carriles" class="dropdown-toggle">Carriles</a>                                        
                        </li> 
                        <li data-match-route="/Kits">
                            <a href="#/Kits" class="dropdown-toggle">Kits</a>                                        
                        </li>                                
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </nav>

            <div id="page-wrapper">

                <!-- Page Content -->
                <div id="page-content-wrapper">
                    <!--                    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
                                            <div class="container-fluid">
                                                 Brand and toggle get grouped for better mobile display 
                                                <div class="navbar-header">
                                                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                                        <span class="sr-only">Toggle navigation</span>
                                                        <span class="icon-bar"></span>
                                                        <span class="icon-bar"></span>
                                                        <span class="icon-bar"></span>
                                                    </button>
                                                    <a class="navbar-brand" href="#">
                                                        <img style="width:180px" src="<c:url value="/img/EV.png"/>" class="img-responsive hidden-xs" alt=""/>
                                                    </a>
                                                </div>
                    
                                                 Collect the nav links, forms, and other content for toggling 
                                                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                                                                <ul class="nav navbar-nav">
                                                                                    <li>
                                                                                        <a href="#/NuevaPrueba" ng-show="hasRole('')">Nueva</a>
                                                                                    </li>
                                                                                    <li>
                                                                                        <a href="#/Pruebas">Pruebas</a>
                                                                                    </li>
                                                                                    <li>                            
                                                                                        <a href="#/ProgramacionPruebasEnsamble">Programación de Pruebas de Ensamble </a>
                                                                                    </li>
                                                                                    <li>
                                                                                        <a href="#/ProgramacionPruebasArranque">Programación de dPruebas de Arranques</a>                            
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
                </a>
            </li>                        <li>
                <a href="#" style="padding:0px">
                    <img src="<c:url value="/img/LogoSelmec.png"/>" class="img-responsive" width="150" alt=""/>
                </a>
            </li>                        
            <li>                 
                </li>
            </ul>
        </div> /.navbar-collapse 
    </div> /.container-fluid 
</nav>-->
                    <div class="container-fluid"> 
                        <div class='col-md-12'>
                            <jsp:doBody/>
                        </div>
                    </div>   
                </div>
            </div>
        </div>
    </body>  
</html>