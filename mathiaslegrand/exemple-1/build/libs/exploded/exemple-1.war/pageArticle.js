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
            cols: [
                {
                    view: "sidemenu",
                    id: "menu",
                    width: 200,
                    position: "top",
                    state: function (state) {
                        state.top = $$("toolbar").$height;
                    },
                    css: "my_menu",
                    body: {
                        borderless: true,
                        margin: 5,
                        cols: [
                            {

                                rows: [
                                    {
                                        view: "list",
                                        scroll: false,
                                        layout: "x",

                                        template: "<span class='webix_icon mdi mdi-#icon#'></span> #value#",
                                        data: [
                                            {id: 1, value: "Customers", icon: "account"},
                                            {id: 2, value: "Products", icon: "cube"},
                                            {id: 3, value: "Reports", icon: "chart-bar"},
                                            {id: 4, value: "Archives", icon: "database"},
                                            {id: 5, value: "Settings", icon: "cogs"}
                                        ],
                                        select: true,
                                        type: {
                                            width: "auto",
                                            height: 40
                                        }
                                    },
                                    {
                                        view: "label",
                                        id: "nomProduit",
                                        height: 200,
                                        label: "Legging",
                                        css: "titre",
                                        align: "left"
                                        //label: "Bas De texte"
                                    },
                                    {
                                        view: "carousel",
                                        css: "webix_dark",
                                        id: "carousel",
                                        scrollSpeed: "500ms",
                                        width: "auto",
                                        height: 500,
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
                                    },

                                ],
                            },
                            {
                                rows: [
                                    {
                                        view: "label",
                                        id: "description",
                                        height: 100,
                                        label: "Description",
                                        css: "titre",
                                        align: "left"
                                        //label: "Bas De texte"
                                    },
                                    {
                                        view: "label",
                                        id: "taille",
                                        height: 100,
                                        label: "Taille",
                                        css: "titre",
                                        align: "left"
                                        //label: "Bas De texte"
                                    },
                                    {
                                        view: "label",
                                        id: "prix",
                                        height: 100,
                                        label: "Prix",
                                        css: "titre",
                                        align: "left"
                                        //label: "Bas De texte"
                                    },
                                    {
                                        view: "label",
                                        id: "inventaire",
                                        height: 100,
                                        label: "Inventaire",
                                        css: "titre",
                                        align: "left"
                                        //label: "Bas De texte"
                                    }


                                ]

                            }]
                    },
                }]
        }
    })
}
)












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