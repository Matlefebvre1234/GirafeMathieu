

webix.ready( function () {

    console.log("Debut")
    const xhr3 = new XMLHttpRequest();
    xhr3.open('GET', 'http://localhost:8080/exemple-1/api/Panier/ajouterPanier');
    xhr3.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
    xhr3.send(datatexte);
    xhr3.onload = () =>{
    };
    console.log("Fin")

    var queryString = decodeURIComponent(window.location.search);
    queryString = queryString.substring(1);
    var intermediaire;
    var temporaire;
    const xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost:8080/exemple-1/api/produit/getProduit');
    var data = {cip: queryString}
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
    var datatexte = ('cip=' + data.cip)
    xhr.send(datatexte);
    xhr.onload =  () => {
        intermediaire =  xhr.response;
        intermediaire = JSON.parse(intermediaire);
        var titre = intermediaire.nomitem;
        var description = intermediaire.description;
        var prix = intermediaire.prix;
        var photos = intermediaire["arrayPhoto"];

        var viewsArray = [];
        for(var i = 0; i < photos.length; i++){
            viewsArray.push({
                id: i,
                css: "image",
                template:img,
                data: {src:photos[i]}
            });
        }

        console.log(viewsArray);

        const xhr2 = new XMLHttpRequest();
        xhr2.open('POST', 'http://localhost:8080/exemple-1/api/produit/getTaillesProduit');
        var data = {cip: queryString}
        xhr2.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
        var datatexte = ('id=' + data.cip)
        xhr2.send(datatexte);
        xhr2.onload =  () => {
            console.log(xhr2.response);

            temporaire = xhr2.response;
            temporaire = JSON.parse(temporaire);
            var tailles = [];
            for(var i = 0; i < temporaire.length; i++){
                tailles.push({
                    id: i,
                    value: temporaire[i]
                })
            }

            var quantites = [];

            for(var i = 1; i < 100; i++){
                quantites.push({
                    id: i,
                    value: i
                })
            }


            webix.ui({
                view: "scrollview",
                id: "scrollview",
                scroll: "y",
                body: {
                    rows: [
                        {
                            view: "label",
                            id: "nomProduit",
                            name: "nomProduit",
                            height: 200,
                            label: titre,
                            css: "titre",
                            align: "left"
                            //label: "Bas De texte"
                        },
                        {
                            cols: [
                                {
                                    rows: [
                                        {
                                            view: "carousel",
                                            css: "webix_dark",
                                            id: "carousel",
                                            filespace: true,
                                            scrollSpeed: "500ms",
                                            autoWidth: true,
                                            height: 500,
                                            borderless: true,
                                            align: "left",
                                            cols: viewsArray
                                        }
                                    ]
                                },
                                {
                                    rows: [
                                        {
                                            view: "textarea",
                                            label: "Description",
                                            labelPosition: "top",
                                            id: "txtdescription",
                                            readonly: true,
                                            height: 180,
                                            value: description
                                        },
                                        {
                                            cols: [
                                                {
                                                    cols: [
                                                        {
                                                            view: "richselect",
                                                            label: "Taille",
                                                            id: "choixtaille",
                                                            value: "N/A",
                                                            options: tailles,
                                                            //TODO Connecter avec la base de donnees
                                                            height: 50,
                                                            width: 150,
                                                        }
                                                    ]
                                                },
                                                {
                                                    cols: [
                                                        {
                                                            view: "richselect",
                                                            label: "Quantite",
                                                            id: "quantiteProduit",
                                                            value: "N/A",
                                                            options: quantites,
                                                            //TODO Connecter avec la base de donnees et rajouter des options
                                                            height: 50,
                                                            width: 150,
                                                        }
                                                    ]
                                                }
                                            ]
                                        },
                                        {
                                            view: "text",
                                            label: "Prix",
                                            id: "prixunitaire",
                                            value: prix,
                                            readonly: true,
                                            height: 50,
                                            width: 300
                                        },
                                        {
                                            view: "textarea",
                                            label: "Informations",
                                            id: "information",
                                            labelPosition: "top",
                                            borderless: true,
                                            readonly: true,
                                            height: 200,
                                            width: 300
                                        },
                                        {
                                            view:"button",
                                            id:"my_button",
                                            value:"Commander",
                                            css:"webix_primary",
                                            inputWidth:200,
                                            click: function (){
                                                var data = {id:queryString, quantite:$$("quantiteProduit").getText(), taille:$$("choixtaille").getText()}
                                                console.log(data.quantite);
                                                if(data.quantite == "" || data.taille == "")
                                                {
                                                    webix.alert({
                                                        titre: "Panier",
                                                        text: "Il faut choisir une taille et un quantité!"
                                                    });
                                                }

                                                else{
                                                    var resultat
                                                    const xhr = new XMLHttpRequest();
                                                    xhr.open('POST', 'http://localhost:8080/exemple-1/api/commande/commander_item');

                                                    console.log(data.taille)
                                                    console.log(data.quantite)
                                                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
                                                    var datatexte = ('id='+data.id + '&quantite='+data.quantite + '&taille='+data.taille)
                                                    xhr.send(datatexte);
                                                    xhr.onload = () =>{
                                                        console.log(xhr.response);

                                                        resultat = xhr.response
                                                        resultat = JSON.parse(resultat)
                                                        console.log(resultat)
                                                        var reste = +resultat[0] + +data.quantite;

                                                        if(resultat[0] >= 0){
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
                                                        }
                                                    };
                                                }
                                            }
                                        },

                                        {
                                            view:"button",
                                            id:"boutonPanier",
                                            value:"Ajouter au panier",
                                            css:"webix_primary",
                                            inputWidth:200,
                                            autoWidth: true,
                                            click: function (){

                                                var data = {id:queryString, quantite:$$("quantiteProduit").getText(), taille:$$("choixtaille").getText()}

                                                if(data.quantite == "" || data.taille == "")
                                                {
                                                    webix.alert({
                                                        titre: "Panier",
                                                        text: "Il faut choisir une taille et un quantité!"
                                                    });
                                                }

                                                else{
                                                    webix.alert({
                                                        titre: "Panier",
                                                        text: "L'article a été ajouté au panier!"
                                                    });
                                                    const xhr = new XMLHttpRequest();
                                                    xhr.open('POST', 'http://localhost:8080/exemple-1/api/Panier/ajouterItemPanier');

                                                    console.log(data.taille)
                                                    console.log(data.quantite)
                                                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
                                                    var datatexte = ('id='+data.id + '&quantite='+data.quantite + '&taille='+data.taille)
                                                    xhr.send(datatexte);
                                                    xhr.onload = () =>{
                                                        console.log(xhr.response);
                                                    };
                                                }
                                            }
                                        }
                                    ]
                                }
                            ]
                        }


                    ]

                }
            })
        }
    };

    fetchisAdmin();

})



function img(obj){
    return '<img src="'+obj.src+'" alt="centered image" class = "carousel_image"/>'
}

const sendHttpRequest  = (method, url, data) =>{
    const xhr = new XMLHttpRequest();
    xhr.open(method, url);
    xhr.response = 'json';

    if(data){
        xhr.setRequestHeader('Content-type', 'application/json');
    }


    xhr.onload = () =>{
        console.log(JSON.stringify(data));
    };

    xhr.send(JSON.stringify(data));
};

function sendData(cipVal, url){
    sendHttpRequest('POST', url, {
        cip: cipVal
    }).then(responseData => {
        console.log(responseData);
    });
};

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