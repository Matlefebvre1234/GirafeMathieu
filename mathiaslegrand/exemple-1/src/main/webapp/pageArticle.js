webix.ready(function() {

    //webix.html.addCss($$("agegTitre").$view, "red");
    if (webix.CustomScroll)
        webix.CustomScroll.init();
    webix.ui({
        view: "scrollview",
        id: "scrollview",
        scroll: "y",
        height: "80%",
        width: "80%", body: {
            rows:[


                {
                    view: "label",
                    id: "nomProduit",
                    height: 200,
                    label: "Nom du produit",
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
                                    filespace:true,
                                    scrollSpeed: "500ms",
                                    autoWidth: true,
                                    height: 500,
                                    borderless: true,
                                    align: "left",
                                    cols: [
                                        {
                                            css: "images",
                                            template: img,
                                            data: {src: "https://drive.google.com/uc?export=view&id=1hKaET_4XQ8-nXZq96YEAHFx-cPPLx6sO"}
                                        },
                                        {
                                            css: "images",
                                            template: img,
                                            data: {src: "https://drive.google.com/uc?export=view&id=1GVDq4TWYwS35es9k7IdcN4s76PV1JRE3"}
                                        },
                                        {
                                            css: "images",
                                            template: img,
                                            data: {src: "https://drive.google.com/uc?export=view&id=1GtqJ-uWW_aq8nIL6lYnLnG91PNQcwOxL"}
                                        },
                                        {
                                            css: "images",
                                            template: img,
                                            data: {src: "https://drive.google.com/uc?export=view&id=1juGpKhUP184DaYzEferp7eOPAXzC3qWn"}
                                        },
                                        {
                                            css: "images",
                                            template: img,
                                            data: {src: "https://drive.google.com/uc?export=view&id=1JLcyOWfbtUFUMPBedtEcO9nw0wAn87A1"}
                                        }
                                    ]
                                }
                            ]
                        },
                        {
                            rows: [
                                {
                                    view: "textarea",
                                    label:"Description",
                                    labelPosition: "top",
                                    id: "txtdescription",
                                    readonly:true,
                                    height: 180,
                                },
                                {
                                    cols: [
                                        {
                                            cols: [
                                                {
                                                    view: "richselect",
                                                    label:"Taille",
                                                    id: "choixtaille",
                                                    value:"Taille",
                                                    options:[
                                                        { "id":1, "value":"XS"},
                                                        { "id":2, "value":"S"},
                                                        { "id":3, "value":"M"}],
                                                    //TODO Connecter avec la base de donnees
                                                    height: 50,
                                                    width: 150,
                                                }
                                            ]
                                        },
                                        {
                                            cols:[
                                                {
                                                    view: "richselect",
                                                    label:"Quantite",
                                                    id: "quantiteProduit",
                                                    options:[
                                                        { "id":1, "value":"1"},
                                                        { "id":2, "value":"2"},
                                                        { "id":3, "value":"3"}],
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
                                    value: 19.99,
                                    readonly:true,
                                    height: 50,
                                    width: 300
                                },
                                {
                                    view: "textarea",
                                    label: "Informations",
                                    id: "information",
                                    labelPosition: "top",
                                    borderless: true,
                                    readonly:true,
                                    height: 200,
                                    width: 300
                                },

                            ]
                        }
                    ]
                }


            ]

        }
})
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

const sendData = (cipVal) => {
    sendHttpRequest('POST', 'http://localhost:8080/exemple-1/api/insert_admin', {
        cip: cipVal
    }).then(responseData => {
        console.log(responseData);
    });
};