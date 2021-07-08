
webix.ready(function () {

    const xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/exemple-1/api/getUtilisateur');
    xhr.send();
    xhr.onload = () =>{
        var cip = xhr.response;

        cip = JSON.parse(cip);
        cip = cip.cip;

        console.log(cip);

        const xhr2 = new XMLHttpRequest();
        xhr2.open('POST', 'http://localhost:8080/exemple-1/api/Panier/getPanier');
        var data = {cip: cip};
        xhr2.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        var datatexte = ('cip=' + data.cip);
        xhr2.send(datatexte);

        xhr2.onload =  () => {
             var panier = xhr2.response
             console.log(panier);
             panier = JSON.parse(panier);
             var item = panier.items[0];
             console.log(item);
             var produit = item.produit;
             var quantite = item.quantite;
             var nomItem = produit.nomitem;
             var taille = produit.taille;
             var prix = produit.prix;
             var photo = produit["arrayPhoto"];


    webix.ui({
        "cols": [
            {
                "rows": [
                    {"label": "Menu Panier", "view": "label", "height": 75, "borderless": 0},
                    {"label": "Votre Panier", "view": "label", "height": 47, "borderless": 0},
                    {
                        "rows": [
                            {
                                "height": 100,
                                "cols": [
                                    {
                                        css: "images",
                                        template: img,
                                        data: {src: photo[0]}
                                    },
                                    {"label": "Description:                                                                                            " + nomItem, "view": "label", "height": 0, "borderless": 0},
                                    {"label": "Taille: " + taille, "view": "label", "height": 0, "borderless": 0},
                                    {"label": "Prix Unitaire: " + prix, "view": "label", "height": 0, "borderless": 0},
                                    {
                                        "label": "Quantité: " + quantite,
                                        "view": "label",
                                        "height": 0,
                                        "borderless": 0,
                                        align: "center"
                                    },
                                    //"text", id:'field_a', attributes:{ maxlength:10 }}
                                    {
                                        "height": 48,
                                        "rows": [
                                            {"label": "+", "view": "button", "height": 0},
                                            {"label": "-", "view": "button", "height": 0}
                                        ]
                                    },
                                    {"label": "Retirer l'article", "view": "button", "height": 48}
                                ]
                            },
                            {
                                "height": 63,
                                "cols": [
                                    {"label": "Total:", "view": "label", "height": 0, "css": "a"},
                                    {"view": "label", "height": 0, "borderless": 0}
                                ]
                            },
                            {
                                "view": "template",
                                "template": "You can place any widget here..",
                                "role": "placeholder",
                                "height": 308
                            }
                        ]
                    }
                ]
            }
        ],
        "css": "text-align"
    });

        }
    }
    function getQt() {

        //This sends a get request to executeInit.jsp
        //
        //$.get('C:\\Users\\Telep\\Documents\\S3\\Project\\GirafeMathieu\\mathiaslegrand\\exemple-1\\src\\main\\java\\ca\\usherbrooke\\gegi\\server\\business');

    }

    function img(obj) {
        return '<img src="' + obj.src + '" alt="centered image" class = "carousel_image"/>'
    }

})