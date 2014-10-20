var app = angular.module("PlantaAPP", ['nvd3ChartDirectives', 'n3-line-chart', "ngResource", "ngRoute", 'angular-loading-bar']);
app.controller("PruebasCtrl", ["$scope", "PlantaServices",
    function ($scope, PlantaServices) {
        $scope.pruebas = PlantaServices.Ensambles.query();
    }]);
app.controller("PruebaCtrl", ["$scope", "PlantaServices", "$routeParams",
    function ($scope, PlantaServices, $routeParams) {
        if (!$routeParams.PruebaID) {
        }
        else
            $scope.prueba = PlantaServices.Ensambles.get({id: $routeParams.PruebaID});
    }]);
var BaseController = function ($scope, $http, $interval, $routeParams, PlantaServices, $timeout) {
    $scope.OptionsControl = {
        Locked: true
    };
    $scope.incidencias = PlantaServices.Incidencias.query();
    $scope.ensamble = PlantaServices.Ensambles.get({id: $routeParams.EnsambleID}, function () {
        $scope.valores = PlantaServices.Pruebas.Valores({id: $scope.ensamble.planta.noSerie});
    });
    $scope.Enable = function () {
        $scope.OptionsControl.Locked = false;
    };
    $scope.Disable = function () {
        $scope.OptionsControl.Locked = true;
    };
    $scope.Added = [];
    $scope.now = [];
    $scope.Poller = {};
    $scope.RefreshTime = 5;
    $scope.RefreshTimes = [1, 5, 30, 60];
    $scope.Estatus = {
        Waiting: {text: "En espera", value: 0, color: 'green'},
        Running: {text: "Corriendo...", value: 1, color: 'blue'},
        Error: {text: "Error", value: 2, color: 'red'}
    };
    $scope.Save = function () {
        $scope.prueba.ensamble = $scope.ensamble;
        $scope.prueba.$update();
    };
    $scope.Estado = $scope.Estatus.Waiting;
    $scope.AccumulateTime = 0;
    $scope.intervalTime = 1;
    $scope.lastMinute = [];
    $scope.Accumulate = [];
    $scope.options = {
        series: [
            {
                y: "val_0",
                label: "L1-N",
                color: "#ff0000",
                type: "line",
                thickness: "1px"
            }, {
                y: "val_1",
                label: "L2-N",
                color: "#64c900",
                type: "line",
                thickness: "1px"
            }, {
                y: "val_2",
                label: "L3-N",
                color: "#428bca",
                type: "line",
                thickness: "1px"
            },
            {
                y: "I1",
                label: "I1",
                axis: "y2",
                color: "#ff0000",
                type: "line",
                thickness: "1px"
            }, {
                y: "I2",
                label: "I2",
                axis: "y2",
                color: "#64c900",
                type: "line",
                thickness: "1px"
            }, {
                y: "I3",
                label: "I3",
                axis: "y2",
                color: "#428bca",
                type: "line",
                thickness: "1px"
            }
        ],
        axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}, y2: {type: "linear"}},
        lineMode: "linear",
        tension: 0.7,
        tooltip: {mode: 'axes', interpolate: false}
    };
    $scope.optionsTemp = {};
    angular.copy($scope.options, $scope.optionsTemp);
    $scope.optionsTemp.series = [
        {
            y: "Temp",
            label: "Temp",
            color: "#ff0000",
            type: "line",
            thickness: "1px"
        }, {
            y: "HZ",
            label: "HZ",
            axis: "y2",
            color: "#64c900",
            type: "line",
            thickness: "1px"
        }
    ];

    $scope.Stop = function () {
        $scope.Estado = $scope.Estatus.Waiting;
        $interval.cancel($scope.Poller);
        $interval.cancel($scope.timer);
        $scope.prueba.dtFin = new Date();
        $scope.prueba.estatus = 1;
        $scope.prueba.ensamble = $scope.ensamble;
        $scope.prueba.$update();
    };

    $scope.ParoPlanta = function () {
        PlantaServices.Plantas.Off({id: $scope.ensamble.id});
    };

    $scope.ArranquePlanta = function () {
        PlantaServices.Plantas.On({id: $scope.ensamble.id});
    };

    $scope.Start = function () {
        noty({
            text: "¿Confirma el encendido de la planta?", modal: true,
            buttons: [
                {addClass: 'btn btn-primary', text: 'Ok', model: true,
                    onClick: function ($noty) {
                        $noty.close();
                        $scope.notyInit = noty({text: 'Comenzando prueba en  ...', type: 'success', modal: true});
                        $scope.notyInit.count = 5;
                        $scope.intervalNoty = $interval(function () {
                            $scope.notyInit.setText("Comenzando prueba en ... " + $scope.notyInit.count);
                            $scope.notyInit.count--;
                        }, 1000);
                        $timeout(function () {
                            $scope.notyInit.close();
                            $interval.cancel($scope.intervalNoty);
                            $scope.Process();
//                            }, function () {
//                                noty({text: 'Error al prender la planta', type: "error"});
//                                $scope.Estado = $scope.Estatus.Waiting;
//                                $interval.cancel($scope.Poller);
//                                $interval.cancel($scope.timer);
//                            });
                        }, 5000);
                    }
                },
                {addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
                        $noty.close();
                        noty({text: 'Cancelado', type: 'error'});
                    }
                }
            ]
        });
    };

};

app.controller("PruebaSinCargaCtrl", [
    "$scope", "$http", "$interval", "$routeParams", "PlantaServices",
    function ($scope, $http, $interval, $routeParams, PlantaServices) {
        BaseController.call(this, $scope, $http, $interval, $routeParams, PlantaServices);
        $scope.prueba = PlantaServices.Pruebas.get({id: $routeParams.PruebaID});
        $scope.lastMinute = PlantaServices.Pruebas.Lecturas({id: $routeParams.PruebaID});
    }]);
app.controller("PruebaSinCargaController", [
    "$scope", "$http", "$interval", "$routeParams", "PlantaServices", "$timeout",
    function ($scope, $http, $interval, $routeParams, PlantaServices, $timeout) {
        BaseController.call(this, $scope, $http, $interval, $routeParams, PlantaServices, $timeout);
        $scope.Iteraciones = {current: 0,
            Iteracciones: [{No: 1, Time: 10 * 60, current: 0}]};

        $scope.Process = function () {
            $scope.Estado = $scope.Estatus.Running;
            $scope.data = [];
            $scope.Iteraciones.current = 0;
            $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current = 0;
            $scope.prueba = new PlantaServices.Pruebas({
                id: 0,
                ensamble: $scope.ensamble,
                tipo: 0,
                estatus: 0,
                dtInicio: new Date(),
                dtFin: new Date(),
                comentario: null,
                incidencias: null
            });
            $scope.prueba.$save(function () {
                noty({text: "Comenzando Prueba de carga "});
                $scope.timer = $interval(function () {
                    $scope.prueba.dtFin = new Date();
                    if ($scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current >= $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].Time) {
                        $scope.Iteraciones.current++;
                        var current = $scope.now;
                        current.index = $scope.Iteraciones.current;
                        $scope.Accumulate.push(current);
                        if ($scope.Iteraciones.Iteracciones.length === $scope.Iteraciones.current) {
                            noty({text: "Terminando prueba¡¡¡", type: 'success'});
                            return $scope.Stop();
                        }
                        else {
                            $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current = 0;
                            noty({text: "Comenzando Iteracion " + $scope.Iteraciones.current + "¡¡¡", type: 'success'});
                        }
                    }
                    if ($scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].alert)
                        if ($scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].alert.time
                                === $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current) {
                            noty({text: $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].alert.msg,
                                type: "error"});
                        }
                    $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current++;
                    $scope.AccumulateTime++;
                }, 1000);
                $scope.Poller = $interval(function () {
                    PlantaServices.Pruebas.GetValues({id: $scope.prueba.id}
                    , function (response) {
                        $scope.now = response;
                        $scope.now.time = $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current;
                        $scope.lastMinute.push({
                            x: $scope.AccumulateTime,
                            val_0: $scope.now.L1N,
                            val_1: $scope.now.L2N,
                            val_2: $scope.now.L3N,
                            I1: $scope.now.I1,
                            I2: $scope.now.I2,
                            I3: $scope.now.I3,
                            HZ: $scope.now.HZ,
                            Temp: $scope.now.Temp
                        });
                        if ($scope.lastMinute.length > (30 / $scope.RefreshTime)) {
                            $scope.lastMinute.shift();
                        }
                        $scope.data.push({x: $scope.AccumulateTime, val_0: $scope.now.L1N, val_1: $scope.now.L2N, val_2: $scope.now.L3N});
                    }, function () {

                    });
                }, $scope.RefreshTime * 1000);
            }, function () {
                $scope.Estado = $scope.Estatus.Waiting;
            });
        };
    }]);
app.controller("PruebaConCargaController", [
    "$scope", "$http", "$interval", "$routeParams", "PlantaServices", "$timeout", "$filter",
    function ($scope, $http, $interval, $routeParams, PlantaServices, $timeout, $filter) {
        BaseController.call(this, $scope, $http, $interval, $routeParams, PlantaServices, $timeout);
        $scope.Accumulate = [];
        $scope.Iteraciones = {current: 0,
            Iteracciones: [
                {No: 1, Time: 1 * 10, current: 0, alert: {msg: 'Aumentar la carga a 50%¡¡¡', time: 5}},
                {No: 2, Time: 1 * 10, current: 0, alert: {msg: 'Aumentar la carga a 75%¡¡¡', time: 5}},
                {No: 3, Time: 1 * 10, current: 0, alert: {msg: 'Aumentar la carga a 100%¡¡¡', time: 5}},
                {No: 4, Time: 1 * 10, current: 0},
                {No: 5, Time: 1 * 10, current: 0},
                {No: 6, Time: 1 * 10, current: 0},
                {No: 7, Time: 1 * 10, current: 0},
                {No: 8, Time: 1 * 10, current: 0},
                {No: 9, Time: 1 * 10, current: 0}
            ]};
        $scope.Process = function () {
            $scope.Estado = $scope.Estatus.Running;
            $scope.data = [];
            $scope.Iteraciones.current = 0;
            $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current = 0;
            $scope.prueba = new PlantaServices.Pruebas({
                id: 0,
                ensamble: $scope.ensamble,
                tipo: 1,
                estatus: 0,
                dtInicio: new Date(),
                dtFin: new Date()
            });
            $scope.prueba.$save(function () {
                noty({text: "Comenzando Prueba de carga "});
                $scope.timer = $interval(function () {
                    if ($scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current >= $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].Time) {
                        $scope.Iteraciones.current++;
                        var current = $scope.now;
                        current.index = $scope.Iteraciones.current;
                        $scope.Accumulate.push(current);
                        if ($scope.Iteraciones.Iteracciones.length === $scope.Iteraciones.current) {
                            noty({text: "Terminando prueba¡¡¡", type: 'success'});
                            return $scope.Stop();
                        }
                        else {
                            $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current = 0;
                            noty({text: "Comenzando Iteracion " + $scope.Iteraciones.current + "¡¡¡", type: 'success'});
                        }
                    }
                    if ($scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].alert)
                        if ($scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].alert.time
                                === $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current) {
                            noty({text: $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].alert.msg,
                                type: "error"});
                        }
                    $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current++;
                    $scope.AccumulateTime++;
                }, 1000);
                $scope.Poller = $interval(function () {
                    PlantaServices.Pruebas.GetValues({id: $scope.prueba.id}
                    , function (response) {
                        $scope.now = response;
                        $scope.now.time = $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current;
                        $scope.lastMinute.push({x: $scope.AccumulateTime, val_0: $scope.now.L1N, val_1: $scope.now.L2N, val_2: $scope.now.L3N});
                        if ($scope.lastMinute.length > (30 / $scope.RefreshTime)) {
                            $scope.lastMinute.shift();
                        }
                        $scope.data.push({x: $scope.AccumulateTime, val_0: $scope.now.L1N, val_1: $scope.now.L2N, val_2: $scope.now.L3N});
                    }, function () {

                    });
                }, $scope.RefreshTime * 1000);
            }, function () {
                $scope.Estado = $scope.Estatus.Waiting;
            });
        };
    }]);
app.controller("PruebaConCargaSubitaCtrl", [
    "$scope", "$http", "$interval", "$routeParams", "PlantaServices",
    function ($scope, $http, $interval, $routeParams, PlantaServices) {
        BaseController.call(this, $scope, $http, $interval, $routeParams, PlantaServices);
        $scope.Iteraciones = {current: 0,
            Iteracciones: [
                {No: 1, Time: 1 * 60, current: 0},
                {No: 2, Time: 1 * 60, current: 0},
                {No: 3, Time: 1 * 60, current: 0}
            ]};
        $scope.Process = function () {
            $scope.Estado = $scope.Estatus.Running;
            $scope.data = [];
            $scope.Iteraciones.current = 0;
            $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current = 0;
            $scope.prueba = new PlantaServices.Pruebas({
                id: 0,
                ensamble: $scope.ensamble,
                tipo: 1,
                estatus: 0,
                dtInicio: new Date(),
                dtFin: new Date()
            });
            $scope.prueba.$save(function () {
                noty({text: "Comenzando Prueba de Carga Subita"});
                $scope.timer = $interval(function () {

                    // $scope.Accumulate.push(current);                   
                    if ($scope.now >= $scope.valores.Min.L1L2) {
                        $scope.Iteraciones.current++;
                        var current = $scope.now;
                        current.index = $scope.Iteraciones.current;
                        $scope.Accumulate.push(current);
                    }
                    $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current++;
                    $scope.AccumulateTime++;
                }, 1000);
                $scope.Poller = $interval(function () {
                    PlantaServices.Pruebas.GetValues({id: $scope.prueba.id}
                    , function (response) {
                        $scope.now = response;
                        $scope.now.time = $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current;
                        $scope.lastMinute.push({x: $scope.AccumulateTime, val_0: $scope.now.L1N, val_1: $scope.now.L2N, val_2: $scope.now.L3N});
                        if ($scope.lastMinute.length > (30 / $scope.RefreshTime)) {
                            $scope.lastMinute.shift();
                        }
                        $scope.data.push({x: $scope.AccumulateTime, val_0: $scope.now.L1N, val_1: $scope.now.L2N, val_2: $scope.now.L3N});
                    }, function () {
                    });
                }, 1000);
            }, function () {
                $scope.Estado = $scope.Estatus.Waiting;
            });
        };
    }]);
app.controller("EnsambleController", ["$scope", "PlantaServices", "$filter", "$location",
    function ($scope, PlantaServices, $filter, $location) {
        $scope.motores = PlantaServices.Motores.query();
        $scope.tipoServicio = [{id: 0, text: "Emergencia"}, {id: 1, text: "Continuo"}];
        $scope.tipoControl = [{id: 0, text: "Intellite"}, {id: 1, text: "IntelliCompac"}];
        $scope.combustible = [{id: 0, text: "Diesel"}, {id: 1, text: "Gasolina"}];
        $scope.carriles = PlantaServices.Carriles.query();
        $scope.SelectedMotor = function () {
            if (!$scope.planta.motores)
                return;
            var motor = $filter("filter")($scope.motores, function (item) {
                return item.modelo === $scope.planta.motores.modelo;
            });
            if (motor.length !== 0)
                return motor[0];
        };
        $scope.planta = {};
        $scope.submit = function () {
            var planta = new PlantaServices.Plantas($scope.planta);
            planta.$save(
                    function () {
                        var prueba = new PlantaServices.Ensambles({
                            folio: "14PR000001",
                            dtCreacion: new Date(),
                            planta: planta,
                            cariles: $scope.ensamble.cariles,
                            altitud: $scope.ensamble.altitud,
                            patin: $scope.ensamble.patin,
                            guardas: $scope.ensamble.guardas,
                            rediador: $scope.ensamble.rediador
                        });
                        prueba.$save(function () {
                            noty({text: "Planta registrada con el folio : " + prueba.folio + "¡¡¡"
                                , type: "success", modal: true});
                            $location.path("/Pruebas/" + prueba.id);

                        }, function () {
                            planta.$delete(function () {
                            }, function () {
                                alert("Ocurrio un error");
                            });
                            alert("Ocurrio un error");
                        });
                    },
                    function () {
                        alert("Ocurrio un error");
                    });
        };
    }]);


//var url = 'http://10.82.10.105:50000/api/Planta/SPControl/';
//$.ajax(url, {
//    contentType: 'application/json',
//    success: function () {
//        alert(":) OK");
//    }, error: function (xhr) {
//        alert('Error! :(  Status = ' + xhr.status + " Message = " + xhr.statusText);
//    }
//});

