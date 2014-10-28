var Routes = ['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
                when('/NuevaPrueba', {
                    templateUrl: '/templates/Ensamble/Nueva.html',
                    controller: 'EnsambleController'
                }).
                when('/Pruebas', {
                    templateUrl: '/templates/Ensamble/Pruebas.html',
                    controller: 'PruebasCtrl'
                }).
                when('/Pruebas/:PruebaID', {
                    templateUrl: '/templates/Ensamble/Prueba.html',
                    controller: 'PruebaCtrl'
                }).
                when('/PruebaSinCarga/:EnsambleID', {
                    templateUrl: '/templates/PruebaSinCarga.html',
                    controller: 'PruebaSinCargaController'
                }).
                when('/PruebaSinCargaView/:EnsambleID/:PruebaID', {
                    templateUrl: '/templates/PruebaSinCargaView.html',
                    controller: 'PruebaSinCargaCtrl'
                }).
                when('/PruebaConCargaView/:EnsambleID/:PruebaID', {
                    templateUrl: '/templates/PruebaConCargaView.html',
                    controller: 'PruebaSinCargaCtrl'
                }).
                when('/PruebaConCarga/:EnsambleID', {
                    templateUrl: '/templates/PruebaConCarga.html',
                    controller: 'PruebaConCargaController'
                }).
                when('/PruebaConCargaSubita/:EnsambleID', {
                    templateUrl: '/templates/PruebaConCargaSubita.html',
                    controller: 'PruebaConCargaSubitaCtrl'
                }).
                when('/PruebaConCargaSubitaView/:EnsambleID/:PruebaID', {
                    templateUrl: '/templates/PruebaSinCargaView.html',
                    controller: 'PruebaSinCargaCtrl'
                }).
                when('/Motores', {
                    templateUrl: '/templates/Motores/Index.html',
                    controller: 'MotoresCtrl'
                }).
                when('/Motores/Edit', {
                    templateUrl: '/templates/Motores/Edit.html',
                    controller: 'MotoresSaveCtrl'
                }).
                when('/Motores/Create', {
                    templateUrl: '/templates/Motores/Create.html',
                    controller: 'MotoresSaveCtrl'
                }).
                when('/PruebaControl/:EnsambleID', {
                    templateUrl: '/templates/PruebaControl.html',
                    controller: 'PruebaControlCtrl'
                }).
                when('/PruebaControlView/:EnsambleID/:PruebaID', {
                    templateUrl: '/templates/PruebaControlView.html',
                    controller: 'PruebaControlViewCtrl'
                }).
                when('/Usuarios', {
                    templateUrl: '/templates/Usuarios/Index.html',
                    controller: 'UsuariosCtrl'
                }).
                when('/Usuarios/Edit/:id', {
                    templateUrl: '/templates/Usuarios/Edit.html',
                    controller: 'UsuariosSaveCtrl'
                }).
                when('/Usuarios/Create', {
                    templateUrl: '/templates/Usuarios/Create.html',
                    controller: 'UsuariosSaveCtrl'
                }).
                otherwise({
                    redirectTo: '/'
                });
    }];
app.config(Routes);