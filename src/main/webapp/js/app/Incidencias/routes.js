var Routes = ['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
                when('/Incidencias', {
                    templateUrl: '/templates/Incidencias/Index.html',
                    controller: 'IncidenciasCtrl'
                }).
                when('/Incidencias/Edit/:IncidenciaID', {
                    templateUrl: '/templates/Incidencias/Edit.html',
                    controller: 'IncidenciasSaveCtrl'
                }).
                when('/Incidencias/Create', {
                    templateUrl: '/templates/Incidencias/Edit.html',
                    controller: 'IncidenciasSaveCtrl'
                }).
                otherwise({
                    redirectTo: '/Pruebas'
                });
    }];
app.config(Routes);