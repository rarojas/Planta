var ControlPlantaCtrl = function ($scope, PlantaService) {
    $scope.CarrilId = {};

};

app.directive('controlplanta', function () {
    return {
        templateUrl: '/templates/directives/ControlPlanta.html'
    };
});
app.directive('incidenciasctrl', function () {
    return {
        templateUrl: '/templates/directives/Incidencias.html'
    };
});
app.directive('tableCtrl', function () {
    return {
        replace: true,
        restrict: 'EA',
        templateUrl: '/templates/directives/table.html'
    };
});
app.directive('datosprueba', function () {
    return {
        replace: true,
        restrict: 'EA',
        templateUrl: '/templates/directives/DatosPrueba.html'
    };
});
app.directive('tableCtrlCarga', function () {
    return {
        replace: true,
        restrict: 'EA',
        templateUrl: '/templates/directives/tablecarga.html'
    };
});
app.directive('tableCtrlSubita', function () {
    return {
        replace: true,
        restrict: 'EA',
        templateUrl: '/templates/directives/tablesubita.html'
    };
});
app.filter('minutos', function () {
    return function (input) {
        input = input || 0;
        var seg = input % 60;
        var min = Math.floor(input / 60);
        var out = min.toString() + ":" + (seg < 10 ? "0" : "") + seg.toString();
        return out;
    };
});
app.directive('check', function () {
    return {
        replace: true,
        restrict: 'EA',
        scope: {
            value: "=value"
        },
        //templateUrl: '/templates/directives/tablesubita.html'
        template: '<div><span class="glyphicon glyphicon-ok success" ng-show="value === true"></span><span class="glyphicon glyphicon-remove bg-danger" ng-show="value === false"></span></div>'
    };
});
app.directive('estatusPrueba', function () {
    return {
        replace: true,
        restrict: 'EA',
        scope: {
            value: "=value"
        },
        controller: ["$scope", function ($scope) {
                $scope.msg = "Desconocido";
                switch ($scope.value) {
                    case 0:
                        $scope.msg = "Creada";
                        break;
                    case 1:
                        $scope.msg = "En curso";
                        break;
                    case 2:
                        $scope.msg = "Finalizada";
                        break;
                    case 3:
                        $scope.msg = "Rechazada Ejecutor";
                        break;
                    case 4:
                        $scope.msg = "Autorizada Ejecutor";
                        break;
                    case 5:
                        $scope.msg = "Rechazada Supervisor";
                        break;
                    case 6:
                        $scope.msg = "Autorizada Supervisor";
                        break;
                }
            }],
        template: '<div><label >{{msg}}</label></div>'
    };
});
app.directive('listPruebas', function () {
    return {
        replace: true,
        restrict: 'EA',
        scope: {
            prueba: "=prueba",
            url: "@url",
            urlview: "@urlview",
            tipo: "@tipo"
        },
        templateUrl: '/templates/directives/listPruebas.html'
                //template: '<div><span class="glyphicon glyphicon-ok success" ng-show="value === true"></span><span class="glyphicon glyphicon-remove bg-danger" ng-show="value === false"></span></div>'
    };
});