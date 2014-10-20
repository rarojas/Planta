var ControlPlantaCtrl = function($scope,PlantaService){
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
        restrict:'EA',
        templateUrl: '/templates/directives/table.html'
    };
});
app.filter('minutos', function () {
    return function (input) {
        input = input || 0;
        var seg = input % 60;
        var min = Math.floor(input / 60);
        var out = min.toString() + ":" + seg.toString();
        return out;
    };
});