app.controller("IncidenciasCtrl",
        ["$scope", "PlantaServices", "$routeParams"
                    , function ($scope, PlantaServices) {
                        $scope.incidencias = PlantaServices.Incidencias.query();
                        $scope.Delete = function (incidencia) {
                            PlantaServices.Incidencias.delete({id: incidencia.id}, function () {
                                $scope.incidencias.pop(incidencia);
                            }, function () {
                            });
                        };
                    }
        ]);

app.controller("IncidenciasSaveCtrl",
        ["$scope", "PlantaServices", "$routeParams", "$location"
                    , function ($scope, PlantaServices, $routeParams, $location) {
                        $scope.incidencia = new PlantaServices.Incidencias();
                        if ($routeParams.IncidenciaID !== undefined)
                            $scope.incidencia.$get({id: $routeParams.IncidenciaID});
                        $scope.Save = function () {
                            $scope.incidencia.$save(function () {
                                if (!$routeParams.IncidenciaID)
                                    $location.path("/Incidencias/Edit/" + $scope.incidencia.id);
                            }, function () {

                            });
                        };
                        $scope.Update = function () {
                            $scope.incidencia.$save(function () {
                            }, function () {
                            });
                        };
                        $scope.Delete = function () {
                            $scope.incidencia.$delete(function () {
                                $location.path("/Incidencias");
                            }, function () {

                            });
                        };
                    }
        ]);
app.controller("MotoresCtrl",
        ["$scope", "PlantaServices", "$routeParams"
                    , function ($scope, PlantaServices) {
                        $scope.motores = PlantaServices.Motores.query();
                        $scope.Delete = function (motor) {
                            PlantaServices.Motores.delete({modelo: motor.modelo}, function () {
                                $scope.incidencias.pop(motor);
                            }, function () {
                            });
                        };
                    }
        ]);

app.controller("MotoresSaveCtrl",
        ["$scope", "PlantaServices", "$routeParams", "$location"
                    , function ($scope, PlantaServices, $routeParams, $location) {
                        $scope.motor = new PlantaServices.Motores();
                        if ($routeParams.modelo !== undefined)
                            $scope.motor.$get({modelo: $routeParams.modelo});
                        $scope.Save = function () {
                            $scope.motor.$save(function () {
                                if (!$routeParams.modelo)
                                    $location.path("/Motores/Edit/" + $scope.motor.id);
                            }, function () {

                            });
                        };
                        $scope.Update = function () {
                            $scope.motor.$update(function () {
                            }, function () {
                            });
                        };
                        $scope.Delete = function () {
                            $scope.motor.$delete(function () {
                                $location.path("/Motores");
                            }, function () {

                            });
                        };
                    }
        ]);
app.controller("UsuariosCtrl",
        ["$scope", "PlantaServices", "$routeParams"
                    , function ($scope, PlantaServices) {
                        $scope.usuarios = PlantaServices.Usuarios.query();
                        $scope.Delete = function (usuario) {
                            PlantaServices.Usuarios.delete({id: usuario.id}, function () {
                                $scope.usuario.pop(usuario);
                            }, function () {
                            });
                        };
                    }
        ]);

app.controller("UsuariosSaveCtrl",
        ["$scope", "PlantaServices", "$routeParams", "$location"
                    , function ($scope, PlantaServices, $routeParams, $location) {
                        $scope.usuario = new PlantaServices.Usuarios();
                        if ($routeParams.id !== undefined)
                            $scope.usuario.$get({id: $routeParams.id});
                        $scope.Save = function () {
                            $scope.usuario.$save(function () {
                                if (!$routeParams.id)
                                    $location.path("/Usuarios/Edit/" + $scope.usuario.id);
                            }, function () {

                            });
                        };
                        $scope.Update = function () {
                            $scope.usuario.$update(function () {
                            }, function () {
                            });
                        };
                        $scope.Delete = function () {
                            $scope.usuario.$delete(function () {
                                $location.path("/Usuarios");
                            }, function () {

                            });
                        };
                    }
        ]);