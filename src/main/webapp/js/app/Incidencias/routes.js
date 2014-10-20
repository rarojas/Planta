var Routes = ['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
                when('/', {
                    templateUrl: '/templates/Incidencias/Index.html',
                    controller: 'IncidenciasCtrl'
                }).
                when('/Edit/:IncidenciaID', {
                    templateUrl: '/templates/Incidencias/Edit.html',
                    controller: 'IncidenciasSaveCtrl'
                }).
                when('/Create', {
                    templateUrl: '/templates/Incidencias/Edit.html',
                    controller: 'IncidenciasSaveCtrl'
                }).
                otherwise({
                    redirectTo: '/'
                });
    }];
app.config(Routes);