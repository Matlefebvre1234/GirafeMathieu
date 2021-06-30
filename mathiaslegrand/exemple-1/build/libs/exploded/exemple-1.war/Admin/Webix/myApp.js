webix.ready(function() {
    if (webix.CustomScroll)
        webix.CustomScroll.init();
    webix.ui({
        rows: [
            {
                view: "toolbar", css: "webix_dark", elements: [
                    {view: "button", type: "icon", icon: "wxi-angle-double-down", width: 30, align: "left",click: function(){
                            $$("$sidebar1").toggle();}},
                    {view: "label", label: "Menu", align: "left"},
                    { view: "button", type: "icon", icon: "wxi-user", width: 40, align: "right" },  
                    {view: "button", type: "icon", icon: "mdi mdi-email", width: 40, align: "right"}
                ]
            },
                {
                    cols: [
                        {
                            view: "sidebar", css: "webix_dark", data: main_sidebar_data, on: {
                                onAfterSelect: function (id) {
                                    var view = $$(id);
                                    if (view)
                                        view.show();
                                }
                            }
                        },
                            {
                            view: "multiview",
                                cells: [
                                    { id: "Inventaire", "rows": [Inventaire_buttons,Inventaire] },
                                    { id: "Precommande", "rows": [Precommande] },
                                    { id: "Commande", "rows": [Commande] },
                                    { id: "Log", "rows": [log] }
                                ]
                            }
                      
                    ]
                }
        ]

    });
});

const sendHttpRequest = (method, url, data) => {
    const promise = new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.open(method, url);

        xhr.responseType = 'json';

        if(data) {
            resolve(xhr.response);
        };

        xhr.send(JSON.stringify(data));
    });
    return promise;
}

const getData = () => {
    sendHttpRequest('GET','http://localhost:8080/exemple-1/api/isAdmin'.then(responseData => {
        console.log(reponseData);
    }));
};

const PageAccueil = () => {
    window.location.replace("http://www.w3schools.com");
}

