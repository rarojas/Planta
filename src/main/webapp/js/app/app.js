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
var BaseController = function ($scope, $http, $interval, $routeParams, PlantaServices, $timeout, $location) {
    $scope.OptionsControl = {
        Locked: true
    };


    $scope.log = [];

    $scope.GetColor = function (max, min, read) {

        if (read === undefined || read === null)
            return "white";
        console.log(read);

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
                thickness: "1px"
            },
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
                color: "#FF7C00",
                type: "line",
                thickness: "1px"
            }, {
                y: "I3",
                label: "I3",
                axis: "y2",
                color: "#FFFF00",
                type: "line",
                thickness: "1px"
            }
        ],
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

    $scope.CanAprove = true;

    $scope.Stop = function () {
        $scope.ParoPlanta();
        $scope.CanAprove = false;
        $scope.Estado = $scope.Estatus.Waiting;
        $interval.cancel($scope.Poller);
        $interval.cancel($scope.timer);
        $scope.prueba.dtFin = new Date();
        $scope.prueba.estatus = 2;
        $scope.prueba.ensamble = $scope.ensamble;
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

        if ($scope.valores.Max.L1N > $scope.now.L1N && $scope.valores.Min.L1N < $scope.now.L1N) {
            $scope.log.push($scope.now.L1N);
        }

        if ($scope.valores.Max.L1N > $scope.now.L2N && $scope.valores.Min.L1N < $scope.now.L2N) {
            $scope.log.push($scope.now.L2N);
        }
        if ($scope.valores.Max.L1N > $scope.now.L3N && $scope.valores.Min.L1N < $scope.now.L3N) {
            $scope.log.push($scope.now.L3N);
        }
        if ($scope.valores.Max.I1 > $scope.now.I1 && $scope.valores.Min.I1 < $scope.now.I1) {
            $scope.log.push($scope.now.I1);
        }
        if ($scope.valores.Max.I1 > $scope.now.I && $scope.valores.Min.I1 < $scope.now.I2) {
            $scope.log.push($scope.now.I2);
        }
        if ($scope.valores.Max.I1 > $scope.now.I3 && $scope.valores.Min.I1 < $scope.now.I3) {
            $scope.log.push($scope.now.I3);
        }

        if ($scope.valores.Max.HZ > $scope.now.HZ && $scope.valores.Min.HZ < $scope.now.HZ) {
            $scope.log.push($scope.now.HZ);
        }

        if ($scope.valores.Max.Temp > $scope.now.HZ && $scope.valores.Min.Temp < $scope.now.HZ) {
            $scope.log.push($scope.now.Temp);
        }

        if ($scope.valores.Max.Temp > $scope.now.HZ && $scope.valores.Min.Temp < $scope.now.HZ) {
            $scope.log.push($scope.now.Temp);
        }
        return {
            x: $scope.AccumulateTime,
            L1N: $scope.now.L1N,
            L2N: $scope.now.L2N,
            L3N: $scope.now.L3N,
            I1: $scope.now.I1,
            I2: $scope.now.I2,
            I3: $scope.now.I3,
            HZ: $scope.now.HZ,
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
                }
            ]
        });
    };
};


app.controller("PruebaControlCtrl", [
    "$scope", "$http", "$interval", "$routeParams", "PlantaServices", "$timeout", "$location",
    function ($scope, $http, $interval, $routeParams, PlantaServices, $timeout, $location) {
        BaseController.call(this, $scope, $http, $interval, $routeParams, PlantaServices, $timeout, $location);
        $scope.prueba = new PlantaServices.Instalacion({
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



