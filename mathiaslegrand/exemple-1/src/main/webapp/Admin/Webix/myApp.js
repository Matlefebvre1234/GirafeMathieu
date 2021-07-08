

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
                    { view: "button", type: "icon", icon: "wxi-user", width: 40, align: "right"},
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
                                    { id: "Admin", padding: 30,"rows": [ajouterAdmin, retirerAdmin, datatableAdmin] },
                                    { id: "Inventaire", "rows": [Inventaire_buttons,Inventaire] },
                                    { id: "Precommande", "rows": [Precommande_buttons, Precommande] },
                                    { id: "Commande", "rows": [Commande_buttons,Commande] },
                                    { id: "Log", "rows": [log] }
                                ]
                            }
                      
                    ]
                }
        ]

    });
    $$("inventaireTable").on_click.viewbtn=function(e, id, trg){
        var value1= $$("inventaireTable").getItem(id);
        var value = value1["idproduit"];
        var queryString = "?" + value;
        window.location.href = "pageArticles.html" + queryString;
    };
    $$("inventaireTable").on_click.modifbtn=function(e, id, trg){
        var value1= $$("inventaireTable").getItem(id);
        modifId(value1.idproduit);
        $$("window_modif").show();
    };
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

