var app = angular.module("PlantaAPP", ['nvd3ChartDirectives', 'n3-line-chart', "ngResource", "ngRoute", 'angular-loading-bar']);
var BaseTableController = function ($scope, $filter) {
    $scope.current = 0;
    $scope.total = 0;
    $scope.rowsPage = 10;
    $scope.totalRows = 0;
    $scope.pages = [];
    $scope.next = function () {
        if ($scope.current !== 0)
            $scope.current--;
    };
    $scope.previous = function () {
        if (($scope.total - 1) !== 0)
            $scope.current++;
    };
    $scope.setPage = function (page) {
        $scope.current = page;
    };
    $scope.$watch("search", function () {
        $scope.current = 0;
        $scope.filtered = $filter("filter")($scope.items, $scope.search);
        $scope.total = Math.ceil($scope.filtered.length / $scope.rowsPage);
        $scope.totalRows = $scope.filtered.length;
        $scope.pages = [];
        for (var page = 0; page < $scope.total; page++)
            $scope.pages.push(page);
    }, true);
    $scope.result = function () {
        return $scope.filtered.slice($scope.current * $scope.rowsPage, ($scope.current + 1) * $scope.rowsPage);
    };
    $scope.Init = function () {
        $scope.total = Math.ceil($scope.items.length / $scope.rowsPage);
        $scope.totalRows = $scope.items.length;
        $scope.filtered = $scope.items;
        for (var page = 0; page < $scope.total; page++)
            $scope.pages.push(page);
    };
};
app.controller("PruebasCtrl", ["$scope", "PlantaServices", "$filter",
    function ($scope, PlantaServices, $filter) {
        BaseTableController.call(this, $scope, $filter);
        $scope.items = PlantaServices.Ensambles.query($scope.Init);
    }]);
app.controller("PruebaCtrl", ["$scope", "PlantaServices", "$routeParams",
    function ($scope, PlantaServices, $routeParams) {
        if (!$routeParams.PruebaID) {
        }
        else
            $scope.prueba = PlantaServices.Ensambles.get({id: $routeParams.PruebaID});
    }]);
var BaseController = function ($scope, $http, $interval, $routeParams, PlantaServices, $timeout, $location) {
    $scope.OptionsControl = {
        Locked: true
    };
    $scope.GetColor = function (max, min, read) {


        var esperado = d3.mean([min, max]);
        var result = Math.abs(esperado - read);
        result /= (esperado - min);
        result > 1 ? 1 : result;
        var color = d3.scale.linear().domain([0, 0.5, 1]).range(["green", "yellow", "red"]);
        return color(result);
    };
    $scope.Init = function () {
        $scope.incidencias = PlantaServices.Incidencias.query();
        $scope.ensamble = PlantaServices.Ensambles.get({id: $routeParams.EnsambleID}, function () {
            $scope.valores = PlantaServices.Pruebas.Valores({id: $scope.ensamble.planta.noSerie}, function () {
                $scope.PruebaCarga = [$scope.valores, $scope.valores, $scope.valores, $scope.valores];
                var i = 0;
                for (i = 0; i < 2; i++) {
                    var max = ($scope.valores.Max.I1 * 0.25 * (i + 1));
                    var min = ($scope.valores.Min.I1 * 0.25 * (i + 1));
                    console.log(max);
                    $scope.PruebaCarga[i].Max.I2 = max;
                    $scope.PruebaCarga[i].Max.I1 = max;
                    $scope.PruebaCarga[i].Max.I3 = max;
                    $scope.PruebaCarga[i].Min.I2 = min;
                    $scope.PruebaCarga[i].Min.I1 = min;
                    $scope.PruebaCarga[i].Min.I3 = min;
                }
                $scope.PruebaCargaIndex = function () {
                    return $scope.Iteraciones.current < 2 ? $scope.Iteraciones.current : 3;
                };
            });
        });
    };
    $scope.Init();
    $scope.Enable = function () {
        $scope.OptionsControl.Locked = false;
    };
    $scope.Disable = function () {
        $scope.OptionsControl.Locked = true;
    };
    $scope.Added = [];
    $scope.now = [];
    $scope.Poller = {};
    $scope.RefreshTime = 1;
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
                y: "L1N",
                label: "L1-N",
                color: "#ff0000",
                type: "line",
                thickness: "1px"
            }, {
                y: "L2N",
                label: "L2-N",
                color: "#64c900",
                type: "line",
                thickness: "1px"
            }, {
                y: "L3N",
                label: "L3-N",
                color: "#428bca",
                type: "line",
                thickness: "1px"},
            {
                y: "I1",
                label: "I1",
                axis: "y2",
                color: "#4C2D73",
                type: "line",
                thickness: "1px"
            }, {
                y: "I2",
                label: "I2",
                axis: "y2",
                color: "#FF7C00", type: "line",
                thickness: "1px"
            }, {
                y: "I3",
                label: "I3", axis: "y2",
                color: "#FFFF00",
                type: "line", thickness: "1px"
            }],
        axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}, y2: {type: "linear"}},
        lineMode: "bundle",
        tension: 0.7,
        tooltip: {mode: "scrubber"},
        drawLegend: true,
        drawDots: true,
        columnsHGap: 5
    };
    $scope.optionsTemp = {};
    angular.copy($scope.options, $scope.optionsTemp);
    $scope.optionsTemp.series = [
        {
            y: "Temp",
            label: "Temp",
            color: "#ff0000",
            type: "line", thickness: "1px"
        }, {
            y: "HZ",
            label: "HZ",
            axis: "y2",
            color: "#64c900",
            type: "line",
            thickness: "1px"
        }
    ];
    $scope.CanAprove = true;
    $scope.Stop = function () {
        $scope.ParoPlanta();
        $scope.CanAprove = false;
        $scope.Estado = $scope.Estatus.Waiting;
        $interval.cancel($scope.Poller);
        $interval.cancel($scope.timer);
        $scope.prueba.dtFin = new Date();
        $scope.prueba.estatus = 2;
        $scope.prueba.ensamble = {id: $scope.ensamble.id},
        $scope.prueba.$update();
    };
    $scope.ParoPlanta = function () {
        PlantaServices.Plantas.Off({id: $scope.ensamble.id}, {id: $scope.ensamble.id}, function () {
            noty({text: "Apagado de planta exitoso¡¡¡", type: "success"});
        }, function () {
            noty({text: "Error al apagar la planta la planta¡¡¡", type: "error"});
        });
    };
    $scope.ArranquePlanta = function () {
        PlantaServices.Plantas.On({id: $scope.ensamble.id}, {id: $scope.ensamble.id}, function () {
            noty({text: "Encendido de planta exitoso¡¡¡", type: "success"});
        }, function () {
            noty({text: "Error al encender la planta¡¡¡", type: "error"});
        });
    };
    $scope.Autoriza = function () {
        PlantaServices.Pruebas.AutorizaE({id: $scope.prueba.id}, {},
                function () {
                    $scope.prueba.estatus = 4;
                    noty({
                        text: "Aprobación con exito¡¡¡¡¡¡", type: "success"
                    });
                    $location.path("/Pruebas/" + $scope.ensamble.id);
                }, function () {
            noty({
                text: "Ocurrio un error al realizar la operacion¡¡¡", type: "error"
            });
        });
    };
    $scope.Rechaza = function () {
        PlantaServices.Pruebas.RechazaE({id: $scope.prueba.id}, {},
                function () {
                    $scope.prueba.estatus = 3;
                    noty({
                        text: "Rechazo con exito¡¡¡¡¡¡", type: "success"
                    });
                    $location.path("/Pruebas/" + $scope.ensamble.id);
                }, function () {
            noty({
                text: "Ocurrio un error al realizar la operacion¡¡¡", type: "error"
            });
        });
    };
    $scope.AutorizaS = function () {
        PlantaServices.Pruebas.AutorizaS({id: $scope.prueba.id}, {},
                function () {
                    $scope.prueba.estatus = 6;
                    noty({
                        text: "Aprobación con exito¡¡¡¡¡¡", type: "success"
                    });
                    $location.path("/Pruebas/" + $scope.ensamble.id);
                }, function () {
            noty({
                text: "Ocurrio un error al realizar la operacion¡¡¡", type: "error"
            });
        });
    };
    $scope.RechazaS = function () {
        PlantaServices.Pruebas.RechazaS({id: $scope.prueba.id}, {},
                function () {
                    $scope.prueba.estatus = 5;
                    noty({
                        text: "Rechazo con exito¡¡¡¡¡¡", type: "success"
                    });
                    $location.path("/Pruebas/" + $scope.ensamble.id);
                }, function () {
            noty({
                text: "Ocurrio un error al realizar la operacion¡¡¡", type: "error"
            });
        });
    };
    $scope.NowToLastMinute = function () {
        return {
            x: $scope.AccumulateTime,
            L1N: $scope.now.L1N,
            L2N: $scope.now.L2N,
            L3N: $scope.now.L3N,
            I1: $scope.now.I1,
            I2: $scope.now.I2,
            I3: $scope.now.I3, HZ: $scope.now.HZ,
            Temp: $scope.now.Temp,
            RPM: $scope.now.RPM,
            MaxV: $scope.valores.Max.L1N,
            MinV: $scope.valores.Min.L1N,
            //bateria: $scope.now.bateria
        };
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
                        }, 5000);
                    }
                },
                {addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
                        $noty.close();
                        noty({text: 'Cancelado', type: 'error'});
                    }
                }]
        });
    };
};
app.controller("PruebaSinCargaCtrl", [
    "$scope", "$http", "$interval", "$routeParams", "PlantaServices", "$location",
    function ($scope, $http, $interval, $routeParams, PlantaServices, $location) {
        BaseController.call(this, $scope, $http, $interval, $routeParams, PlantaServices, $location);
        $scope.prueba = PlantaServices.Pruebas.get({id: $routeParams.PruebaID});
        $scope.options = {
            series: [
                {
                    y: "L1N",
                    label: "L1-N",
                    color: "#ff0000",
                    type: "line",
                    thickness: "1px"
                }, {
                    y: "L2N",
                    label: "L2-N",
                    color: "#64c900",
                    type: "line",
                    thickness: "1px"
                }, {
                    y: "L3N",
                    label: "L3-N",
                    color: "#428bca",
                    type: "line",
                    thickness: "1px"
                }
            ],
            axes: {x: {type: "linear", key: "x"}, y: {type: "linear"}, y2: {type: "linear"}},
            lineMode: "bundle",
            tension: 0.7,
            tooltip: {mode: "scrubber"},
            drawLegend: true
        };
        $scope.data = PlantaServices.Pruebas.Lecturas({id: $routeParams.PruebaID});
        $scope.minute = 1;
        $scope.$watch("minute", function () {
            var index = $scope.minute * 60;
            var i = 0;
            $scope.lastMinute = [];
            if ($scope.data.length >= index + 60)
                for (i = 0; i < 60; i++) {
                    $scope.lastMinute.push($scope.data[i + index]);
                }
        });
    }]);
app.controller("PruebaSinCargaController", [
    "$scope", "$http", "$interval", "$routeParams", "PlantaServices", "$timeout", "$location",
    function ($scope, $http, $interval, $routeParams, PlantaServices, $timeout, $location) {
        BaseController.call(this, $scope, $http, $interval, $routeParams, PlantaServices, $timeout, $location);
        $scope.Iteraciones = {current: 0,
            Iteracciones: [{No: 1, Time: 10 * 60, current: 0}]};
        $scope.Process = function () {
            $scope.Estado = $scope.Estatus.Running;
            $scope.data = [];
            $scope.Iteraciones.current = 0;
            $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current = 0;
            $scope.prueba = new PlantaServices.Pruebas({
                ensamble: {id: $scope.ensamble.id},
                tipo: 0,
                estatus: 0,
                dtInicio: new Date(), dtFin: new Date(),
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
                    PlantaServices.Pruebas.GetValues({id: $scope.prueba.id, seg: $scope.AccumulateTime, ite: $scope.Iteraciones.current, equipo: $scope.ensamble.carriles.equipo}
                    , function (response) {
                        $scope.now = response;
                        $scope.now.time = $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current;
                        $scope.lastMinute.push($scope.NowToLastMinute());
                        if ($scope.lastMinute.length > (30 / $scope.RefreshTime)) {
                            $scope.lastMinute.shift();
                        }
                        $scope.data.push({x: $scope.AccumulateTime, L1N: $scope.now.L1N, L2N: $scope.now.L2N, L3N: $scope.now.L3N});
                    }, function () {

                    });
                }, $scope.RefreshTime * 1000);
            }, function () {
                $scope.Estado = $scope.Estatus.Waiting;
            });
        };
    }]);
app.controller("PruebaConCargaController", [
    "$scope", "$http", "$interval", "$routeParams", "PlantaServices", "$timeout", "$location",
    function ($scope, $http, $interval, $routeParams, PlantaServices, $timeout, $location) {
        BaseController.call(this, $scope, $http, $interval, $routeParams, PlantaServices, $timeout, $location);
        $scope.Accumulate = [];
        $scope.Iteraciones = {current: 0,
            Iteracciones: [
                {No: 1, Time: 5 * 60, current: 0, alert: {msg: 'Aumentar la carga a 50%¡¡¡', time: 30}}, {No: 2, Time: 5 * 60, current: 0, alert: {msg: 'Aumentar la carga a 75%¡¡¡', time: 30}},
                {No: 3, Time: 5 * 60, current: 0, alert: {msg: 'Aumentar la carga a 100%¡¡¡', time: 30}},
                {No: 4, Time: 10 * 60, current: 0},
                {No: 5, Time: 10 * 60, current: 0},
                {No: 6, Time: 10 * 60, current: 0},
                {No: 7, Time: 10 * 60, current: 0},
                {No: 8, Time: 10 * 60, current: 0},
                {No: 9, Time: 10 * 60, current: 0}
            ]};
        $scope.Process = function () {
            $scope.Estado = $scope.Estatus.Running;
            $scope.data = [];
            $scope.Iteraciones.current = 0;
            $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current = 0;
            $scope.prueba = new PlantaServices.Pruebas({
                ensamble: $scope.ensamble,
                tipo: 1,
                estatus: 0,
                dtInicio: new Date(), dtFin: new Date()
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
                    PlantaServices.Pruebas.GetValues({id: $scope.prueba.id, seg: $scope.AccumulateTime, ite: $scope.Iteraciones.current, equipo: $scope.ensamble.carriles.equipo}
                    , function (response) {
                        $scope.now = response;
                        $scope.now.time = $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current;
                        $scope.lastMinute.push($scope.NowToLastMinute());
                        if ($scope.lastMinute.length > (30 / $scope.RefreshTime)) {
                            $scope.lastMinute.shift();
                        }
                        $scope.data.push({x: $scope.AccumulateTime, L1N: $scope.now.L1N, L2N: $scope.now.L2N, L3N: $scope.now.L3N});
                    }, function () {

                    });
                }, $scope.RefreshTime * 1000);
            }, function () {
                $scope.Estado = $scope.Estatus.Waiting;
            });
        };
    }]);
app.controller("PruebaConCargaSubitaCtrl", [
    "$scope", "$http", "$interval", "$routeParams", "PlantaServices", "$timeout", "$location",
    function ($scope, $http, $interval, $routeParams, PlantaServices, $timeout, $location) {
        BaseController.call(this, $scope, $http, $interval, $routeParams, PlantaServices, $timeout, $location);
        $scope.Iteraciones = {current: 0,
            Iteracciones: [
                {No: 1, Time: 1 * 60, current: 0},
                {No: 2, Time: 1 * 60, current: 0},
                {No: 3, Time: 1 * 60, current: 0}
            ]};
        $scope.data = {voltaje: [], hz: []};
        $scope.Process = function () {
            $scope.Estado = $scope.Estatus.Running;
            $scope.data = {voltaje: [], hz: []};
            $scope.Iteraciones.current = 0;
            $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current = 0;
            $scope.prueba = new PlantaServices.Pruebas({
                ensamble: $scope.ensamble,
                tipo: 2,
                estatus: 0,
                dtInicio: new Date(),
                dtFin: new Date()
            });
            $scope.prueba.$save(function () {
                noty({text: "Comenzando Prueba de Carga Subita"});
                $scope.timer = $interval(function () {
                    if ($scope.now.L1N >= $scope.valores.Min.L1L2 && $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].active) {
                        var current = $scope.now;
                        current.index = $scope.Iteraciones.current;
                        $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current;
                        current.voltaje = {max: 0, min: 0};
                        current.hz = {max: 0, min: 0};
                        current.voltaje.max = _.max($scope.data.voltaje);
                        current.voltaje.min = _.min($scope.data.voltaje);
                        current.hz.max = _.max($scope.data.hz);
                        current.hz.min = _.min($scope.data.hz);
                        $scope.Accumulate.push(current);
                        $scope.Iteraciones.current++;
                        if ($scope.Iteraciones.Iteracciones.length === $scope.Iteraciones.current) {
                            noty({text: "Terminando prueba¡¡¡", type: 'success'});
                            return $scope.Stop();
                        }
                        else {
                            $scope.data = {voltaje: [], hz: []};
                            $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current = 0;
                            noty({text: "Comenzando Iteracion " + $scope.Iteraciones.current + "¡¡¡", type: 'success'});
                        }
                    }
                    if (!$scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].active)
                        if ($scope.now.L1N < $scope.valores.Min.L1L2)
                            $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].active = true;
                    if ($scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].active) {
                        $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current++;
                        $scope.data.voltaje.push($scope.now.L1N);
                        $scope.data.hz.push($scope.now.HZ);
                    }
                    $scope.AccumulateTime++;
                }, 1000);
                $scope.Poller = $interval(function () {
                    PlantaServices.Pruebas.GetValues({id: $scope.prueba.id, seg: $scope.AccumulateTime, ite: $scope.Iteraciones.current, equipo: $scope.ensamble.carriles.equipo}
                    , function (response) {
                        $scope.now = response;
                        $scope.now.time = $scope.Iteraciones.Iteracciones[$scope.Iteraciones.current].current;
                        $scope.lastMinute.push($scope.NowToLastMinute());
                        if ($scope.lastMinute.length > (30 / $scope.RefreshTime)) {
                            $scope.lastMinute.shift();
                        }

                    }, function () {
                    });
                }, 1000);
            }, function () {
                $scope.Estado = $scope.Estatus.Waiting;
            }
            );
        }
        ;
    }]);
app.controller("PruebaControlCtrl", [
    "$scope", "$http", "$interval", "$routeParams", "PlantaServices", "$timeout", "$location",
    function ($scope, $http, $interval, $routeParams, PlantaServices, $timeout, $location) {
        BaseController.call(this, $scope, $http, $interval, $routeParams, PlantaServices, $timeout, $location);
        $scope.prueba = new PlantaServices.Pruebascontrol({
            ensamble: $scope.ensamble,
            tipo: 3,
            estatus: 0,
            dtInicio: new Date(),
            dtFin: new Date()
        });
        $scope.GuardarPrueba = function () {
            $scope.prueba.estatus = 2;
            if ($scope.prueba.id)
                $scope.prueba.$update(function () {
                }, function () {
                });
            else
                $scope.prueba.$save(function () {
                }, function () {
                });
        };
    }]);
app.controller("PruebaControlViewCtrl", [
    "$scope", "$http", "$interval", "$routeParams", "PlantaServices", "$timeout", "$location",
    function ($scope, $http, $interval, $routeParams, PlantaServices, $timeout, $location) {
        BaseController.call(this, $scope, $http, $interval, $routeParams, PlantaServices, $timeout, $location);
        $scope.pruebacontrol = PlantaServices.Pruebascontrol.get({id: $routeParams.PruebaID});
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

app.controller("NuevoArranqueCtrl",
        ["$scope", "PlantaServices", "$filter", "$location",
            function ($scope, PlantaServices, $filter, $location) {
                $scope.motores = PlantaServices.Plantas.query();
                $scope.submit = function () {
                    var prueba = new PlantaServices.EnsambleArranque({
                        folio: "14PR000001",
                        dtCreacion: new Date(),
                        planta: $scope.planta,
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

                };
            }]);
     