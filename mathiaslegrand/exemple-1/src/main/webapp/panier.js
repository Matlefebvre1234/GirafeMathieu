
webix.ready(function(){

    var produit;
    const xhr = new XMLHttpRequest();
    xhr.open('POST', '"http://localhost:8080/exemple-1/api/ItemPanier/getQuantite"');
    var data = {cip: queryString}
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
    var datatexte = ('cip=' + data.cip)
    xhr.send(datatexte);


    webix.ui({
    "cols": [
    {
        "rows": [
            { "label": "Menu Panier", "view": "label", "height": 75, "borderless": 0 },
            { "label": "Votre Panier", "view": "label", "height": 47, "borderless": 0 },
            {
                "rows": [
                    { "height":100,
                        "cols": [
                            {
                                css: "images",
                                template: img,
                                data: {src: "https://drive.google.com/uc?export=view&id=1hKaET_4XQ8-nXZq96YEAHFx-cPPLx6sO"}
                            },
                            { "label": "Description:",  "view": "label","height": 0, "borderless": 0 },
                            { "label": "Taille::",  "view": "label","height": 0, "borderless": 0 },
                            { "label": "Prix Unitaire:", "view": "label","height": 0, "borderless": 0 },
                            { "label": quantite,"view": "label", "height": 0, "borderless": 0 , align : "center"},
                            //"text", id:'field_a', attributes:{ maxlength:10 }}
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
    function getQt(){

        //This sends a get request to executeInit.jsp
        //
        //$.get('C:\\Users\\Telep\\Documents\\S3\\Project\\GirafeMathieu\\mathiaslegrand\\exemple-1\\src\\main\\java\\ca\\usherbrooke\\gegi\\server\\business');

    }

    function img(obj){
        return '<img src="'+obj.src+'" alt="centered image" class = "carousel_image"/>'
    }


    let quantite;
    async function Qt(){

        let response = await fetch("http://localhost:8080/exemple-1/api/ItemPanier/getQuantite")
         quantite = await response.json();

        }
})