

webix.ready( function () {

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
        console.log(intermediaire);
        intermediaire = JSON.parse(intermediaire);
        var titre = intermediaire.nomitem;
        var description = intermediaire.description;
        var prix = intermediaire.prix;
        var photos = intermediaire["arrayPhoto"];
        console.log(photos);

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
                                                            value: "Taille",
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
                                                            value: "0",
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
                                            inputWidth:100,
                                            click: function (){
                                                const xhr = new XMLHttpRequest();
                                                xhr.open('POST', 'http://localhost:8080/exemple-1/api/commande/commander_item');
                                                var data = {id:queryString, quantite:$$("quantiteProduit").getValue()}
                                                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
                                                var datatexte = ('id='+data.id + '&quantite='+data.quantite)
                                                xhr.send(datatexte);
                                                xhr.onload = () =>{
                                                    console.log(xhr.response);
                                                };
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