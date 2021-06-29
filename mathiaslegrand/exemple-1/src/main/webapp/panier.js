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
                            { "view": "label", "height": 0, "borderless": 0 },
                            {
                                "height": 48,
                                "rows": [
                                    { "label": "Click me", "view": "button", "height": 0 },
                                    { "label": "Click me", "view": "button", "height": 0 }
                                ]
                            },
                            { "label": "Click me", "view": "button", "height": 48 }
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
})