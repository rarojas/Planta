var services = ["PlantaServices",
    ["$resource", function ($resource) {
            var urlApiBase = "/api/";
            var api = {};
            api.Plantas = $resource(urlApiBase + "Planta/:noSerie", {}, {
                On: {method: 'POST', url: urlApiBase + "Planta/On/:id", },
                Off: {method: 'POST', url: urlApiBase + "Planta/Off/:id"}
            });
            api.Carriles = $resource(urlApiBase + "Carriles/:id", {}, {});
            api.Motores = $resource(urlApiBase + "Motores/:modelo", {}, {
                update: {method: 'PUT'},
                get: {method: 'GET', url: urlApiBase + "Motores/Get"}
            });
            api.Ensambles = $resource(urlApiBase + "Ensambles/:id", {}, {});
            api.Pruebas = $resource(urlApiBase + "Pruebas/:id", {}, {
                GetValues: {url: urlApiBase + 'Planta/GetValues/:id'},
                Valores: {url: urlApiBase + "Planta/Valores/:id"},
                update: {method: 'PUT'},
                Lecturas: {url: urlApiBase + "Pruebas/Lecturas/:id", isArray: true}
            });
            api.Incidencias = $resource(urlApiBase + "Incidencias/:id", {}, {
                update: {method: 'PUT'}
            });
            return api;
        }]];
app.factory(services[0], services[1]);
