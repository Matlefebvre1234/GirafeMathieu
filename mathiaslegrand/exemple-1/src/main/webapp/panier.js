webix.ready(function(){
    //webix.html.addCss($$("agegTitre").$view, "red");
    if (webix.CustomScroll)
        webix.CustomScroll.init();
    webix.ui({
    "cols": [
    {
        "rows": [
            { "label": "Menu Panier", "view": "label", "height": 75, "borderless": 0 },
            { "label": "Votre Panier", "view": "label", "height": 47, "borderless": 0 },
            {
                "rows": [
                    {
                        "cols": [
                            { "view": "label", "height": 0, "borderless": 0 },
                            { "view": "label", "height": 0, "borderless": 0 },
                            { "view": "label", "height": 0, "borderless": 0 },
                            { "label": "Quantité","view": "label", "height": 0, "borderless": 0 , align : "center"},
                            {
                                "height": 48,
                                "rows": [
                                    { "label": "+", "view": "button", "height": 0 },
                                    { "label": "-", "view": "button", "height": 0 }
                                ]
                            },
                            { "label": "Retirer l'article", "view": "button", "height": 48 }
                        ]
                    },
                    {
                        "height": 63,
                        "cols": [
                            { "label": "Total:", "view": "label", "height": 0, "css": "a" },
                            { "view": "label", "height": 0, "borderless": 0 }
                        ]
                    },
                    { "view": "template", "template": "You can place any widget here..", "role": "placeholder", "height": 308 }
                ]
            }
        ]
    }
],
    "css": "text-align"


    });
    fetchisAdmin();

})

async function fetchisAdmin()
{
    let response2 = await fetch("http://localhost:8080/exemple-1/api/isAdmin");
    let data2 = await response2.json();
    if(data2[0].mybool== true)
    {
        let gestionAdmin = document.getElementById("gestionAdmin");
        gestionAdmin.style.display = "block";
    }
    else
    {
        let gestionAdmin = document.getElementById("gestionAdmin");
        gestionAdmin.style.display = "none";
    }
}

