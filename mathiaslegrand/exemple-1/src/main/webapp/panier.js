
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

            var item = panier.items;
            var arrayProduit = [];
            var total = 0;
            var  quantites= [] ;
            var listePrix = [];

             for(var i = 0; i < item.length; i++){

                 if(item!=null){
                     var produit = item[i].produit;
                     var quantite = item[i].quantite;
                     var nomItem = produit.nomitem;
                     var taille = produit.taille;
                     var prix = produit.prix;
                     //console.log(total)
                     total = total + item[i].quantite * produit.prix;
                     var photo = produit["arrayPhoto"];
                 }
                 console.log(photo[0]);
                 quantites.push(quantite);
                 listePrix.push(prix);


                 arrayProduit.push({
                     "height": 100,
                     "cols": [
                         {
                             css: "images",
                             template: img,
                             data: {src: photo[0]}
                         },
                         {"label": "Produit: " + nomItem, "view": "label", "height": 0, "borderless": 0},
                         {"label": "Taille: " + taille, "view": "label", "height": 0, "borderless": 0},
                         {"label": "Prix Unitaire: " + listePrix[i], "view": "label", "height": 0, "borderless": 0},
                         {"label": "Quantité: " + quantites[i], "view": "label", "height": 0, "borderless": 0, id:"quantite" + i},
                         {
                             "height": 48,
                             "rows": [
                                 {"label": "+", "view": "button", "height": 0, id :"p" + i,
                                     click: function plus(id)
                                         {

                                             var bonID = id.substring(1);
                                             quantites[bonID] += 1;
                                             var qte  = "quantite" + bonID;
                                             console.log(quantites[bonID]);
                                             $$(qte).setValue("Quantite" + ": " + quantites[bonID]);

                                             total = total + listePrix[bonID];

                                             $$("TOT").setValue(total+"$");
                                         }
                                 },
                                 {"label": "-", "view": "button", "height": 0, id:"m"+i,
                                     click: function moins(id) {
                                         {
                                             var bonID = id.substring(1);
                                             quantites[bonID] -= 1;

                                             var vieuxTotal = total;
                                             total -= listePrix[bonID];

                                             if(quantites[bonID]  < 0){

                                                 quantites[bonID]=0;

                                                 total = vieuxTotal;
                                             }
                                             var qte  = "quantite" + bonID;
                                             $$(qte).setValue("Quantite" + ": " + quantites[bonID]);
                                             $$("TOT").setValue(total + "$");

                                     }
                                 }}

                             ]
                         }]});
             }


    webix.ui({

        "cols": [
            {
                "rows": [
                    {"label": "Menu Panier", "view": "label", "height": 75, "borderless": 0},
                    {"label": "Votre Panier", "view": "label", "height": 47, "borderless": 0},

                    {"rows": arrayProduit},{
                        "height": 63,
                        "cols": [
                            {"label": "Total:", "view": "label", "height": 0, "css": "a"},
                            {"label": total + "$","view": "label", "height": 0, "borderless": 0, id: "TOT"}
                        ]
                    },
        ],
        "css": "text-align"
    }]});

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