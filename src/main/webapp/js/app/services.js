var services = ["PlantaServices",
    ["$resource", function ($resource) {
            var urlApiBase = "/api/";
            var api = {};
            api.Plantas = $resource(urlApiBase + "Planta/:noSerie", {}, {
                On: {method: 'get', url: urlApiBase + "Planta/On/:id"},
                Off: {method: 'get', url: urlApiBase + "Planta/Off/:id"}
            });
            api.Carriles = $resource(urlApiBase + "Carriles/:id", {}, {});
            api.Motores = $resource(urlApiBase + "Motores/:modelo", {}, {
                update: {method: 'PUT'},
                get: {method: 'GET', url: urlApiBase + "Motores/Get"}
            });
            api.Ensambles = $resource(urlApiBase + "Ensambles/:id", {}, {});
            api.Pruebascontrol = $resource(urlApiBase + "Pruebacontrol/:id", {}, {});
            api.Pruebas = $resource(urlApiBase + "Pruebas/:id", {}, {
                GetValues: {url: urlApiBase + 'Planta/GetValues/:id/:seg/:ite/:equipo'},
                Valores: {url: urlApiBase + "Planta/Valores/:id"},
                update: {method: 'PUT'},
                Lecturas: {url: urlApiBase + "Pruebas/Lecturas/:id", isArray: true},
                AutorizaE: {method: 'POST', url: urlApiBase + "Pruebas/AutorizarE/:id"},
                RechazaE: {method: 'POST', url: urlApiBase + "Pruebas/RechazarE/:id"},
                AutorizaS: {method: 'POST', url: urlApiBase + "Pruebas/AutorizarS/:id"},
                RechazaS: {method: 'POST', url: urlApiBase + "Pruebas/RechazarS/:id"},
                Control: {method: 'POST', url: urlApiBase + "Pruebacontrol"}
            });
            api.Incidencias = $resource(urlApiBase + "Incidencias/:id", {}, {
                update: {method: 'PUT'}
            });
            api.Usuarios = $resource(urlApiBase + "Usuarios/:id", {}, {
                update: {method: 'PUT'}
            });
            return api;
        }]];
app.factory(services[0], services[1]);
