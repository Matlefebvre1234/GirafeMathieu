

webix.ready( function () {

    var queryString = decodeURIComponent(window.location.search);
    queryString = queryString.substring(1);
    var intermediaire;
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
                                                        options: [
                                                            {"id": 1, "value": "XS"},
                                                            {"id": 2, "value": "S"},
                                                            {"id": 3, "value": "M"}],
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
                                                        options: [
                                                            {"id": 1, "value": "1"},
                                                            {"id": 2, "value": "2"},
                                                            {"id": 3, "value": "3"}],
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
<<<<<<< HEAD
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
=======
                                        inputWidth:100
>>>>>>> LESAUVEUR
                                    }
                                ]
                            }
                        ]
                    }


                ]

            }
        })
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