
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
            var listeProduits = [];
            var listeTailles = []

             for(var i = 0; i < item.length; i++){

                 if(item!=null){
                     var produit = item[i].produit;
                     var quantite = item[i].quantite;
                     var idProduit = produit.idproduit;
                     var taille = produit.taille;
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
                 listeProduits.push(idProduit);
                 listeTailles.push(taille);

                 arrayProduit.push({
                     "height": 100,
                     id: "rangee" + i,
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
                         },
                         {"label": "Retirer l'article",
                             "view": "button",
                             "height": 48,
                             id: "r" + i,
                         click: function retirerArticle(id) {
                             var bonID = id.substring(1);
                             console.log(bonID);
                             console.log(arrayProduit);
                             arrayProduit.splice(bonID, 1);
                             console.log(arrayProduit);
                            $$("layout").removeView("rangee" + bonID);
                            total -= quantites[bonID] *  listePrix[bonID];
                            $$("TOT").setValue(total + "$");

                             const xhr3 = new XMLHttpRequest();
                             xhr3.open('POST', 'http://localhost:8080/exemple-1/api/Panier/retirerItemPanier');
                             var data = {id: listeProduits[bonID]};
                             xhr3.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                             var datatexte = ('id=' + data.id);
                             xhr3.send(datatexte);

                         }}
                     ]});
             }


    webix.ui({

        "cols": [
            {
                "rows": [
                    {"label": "Menu Panier", "view": "label", "height": 75, "borderless": 0},
                    {"label": "Votre Panier", "view": "label", "height": 47, "borderless": 0},

                    {   id : "layout",
                        "rows": arrayProduit},{
                        "height": 63,
                        "cols": [
                            {"label": "Total:", "view": "label", "height": 0, "css": "a"},
                            {"label": total + "$","view": "label", "height": 0, "borderless": 0, id: "TOT"}
                        ]
                    },
                    {
                        view:"button",
                        id:"my_button",
                        value:"Commander",
                        css:"webix_primary",
                        inputWidth:200,
                        click: function (){


                                for(var j = 0; j < listeProduits.length; j++){
                                    var compteur = 0;
                                    var resultat
                                    var data = {id:listeProduits[j], quantite:quantites[j], taille:listeTailles[j]}
                                    console.log(data.quantite);

                                    const xhr4 = new XMLHttpRequest();
                                    xhr4.open('POST', 'http://localhost:8080/exemple-1/api/commande/commander_item');

                                    console.log(data.taille)
                                    console.log(data.quantite)
                                    xhr4.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
                                    var datatexte = ('id='+data.id + '&quantite='+data.quantite + '&taille='+data.taille)
                                    xhr4.send(datatexte);
                                    xhr4.onload = () =>{
                                        resultat = xhr4.response
                                        console.log(xhr4.response);

                                        resultat = JSON.parse(resultat)

                                        var reste = resultat[0] + quantites[compteur];
                                        console.log(listeProduits.length);
                                        console.log(quantites[compteur]);
                                        if(resultat[0] >= 0){
                                            arrayProduit.splice(compteur, 1);
                                            console.log(arrayProduit);
                                            $$("layout").removeView("rangee" + compteur);
                                            total -= quantites[compteur] *  listePrix[compteur];
                                            $$("TOT").setValue(total + "$");

                                            const xhr5 = new XMLHttpRequest();
                                            xhr5.open('POST', 'http://localhost:8080/exemple-1/api/Panier/retirerItemPanier');
                                            var data = {id: listeProduits[compteur]};
                                            xhr5.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                                            var datatexte = ('id=' + data.id);
                                            xhr5.send(datatexte);
                                            compteur++;
                                            webix.alert({
                                                titre: "Commande",
                                                text: "La commande a ete enregistree!"

                                            });
                                        }

                                        else{
                                            webix.alert({
                                                titre: "Commande",
                                                text: "Trop grande quantité, il ne reste que " + reste + " items"
                                            });
                                        }};
                                }
                        }
                    }
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


})
