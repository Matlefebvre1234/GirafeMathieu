
var ajouterAdmin = {
    "cols": [
        {
            "view": "text",
            "placeholder": "Type here...",
            "label": "Cip",
            name: "cip",
            id:"cip",
            "labelWidth": 100
        },
        {
            "label": "Ajouter Admin",
            "view": "button",
            "height": 38,
            click: function (){

                const xhr = new XMLHttpRequest();
                xhr.open('POST', 'http://localhost:8080/exemple-1/api/insert_admin');
                var data = {cip:$$("cip").getValue()}
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
                var datatexte = ('cip='+data.cip)
                xhr.send(datatexte);
                xhr.onload = () =>{
                    console.log(xhr.response);
                    if(xhr.response == "true")
                    {
                        alert("Admin ajouté");
                    }
                    else {
                        alert(" erreur de connetion");
                    }
                };
            }
        }
    ]


}

var Inventaire = {

    cols: [
        {
           "columns": [
                { "id": "idproduit", "header": [{ text: "#" }], autowidth:true, "sort": "int" },
                { "id": "nomitem", "header": [{ text: "Nom" }, { content: "textFilter" }], "fillspace": true, sort: "string" },
                { "id": "quantite", "header": [{ text: "Quantite" }], sort: "string", autowidth:true },
              //  { "id": "ModifTime", "header": [{ text: "Temps" }], "sort": "string", autowidth:true },
                { "id": "prix", "header": [{ text: "Prix Unitaire" }], "sort": "string", autowidth:true },
                { "id": "Action", "header": [{ text: "Action" }], template: "{common.trashIcon} {common.editIcon}", autowidth:true }
            ],
            view: "datatable",
            id: "inventaireTable",
            data: Inventaire_data,
            scroll: false,
            type: {
                trashIcon: "<span class='webix-icon wxi-trash'></span>",
                editIcon: "<span class='webix-icon wxi-pencil'></span>"
            }         
        }
    ]
}
var Inventaire_buttons = {
    view: "toolbar", height: 100, padding:25, elements : [
        {view: "button", label: "Supprimer Article", type: "icon", icon:"wxi-trash", width: 200, align: "right", click: function(){
                webix.confirm({
                    title:"Title",
                    id: "confirmMessage",
                    ok:"Yes", cancel:"No",
                    text:"Lorem ipsum dolor sit amet, consectetur adipisicing elit"
                })
                    .then(function(){
                        webix.confirm({
                            title:"Warning!",
                            type:"confirm-warning",
                            text:"You are about to agree. Are you sure?"
                        });
                    })
                    .fail(function(){
                        webix.confirm({
                            title:"Error!",
                            type:"confirm-error",
                            text:"You have no right to cancel this action"
                        });
                    });}},
        {view: "button", label: "Ajouter Article",type: "icon", icon:"wxi-plus",width: 200, click: function(){$$("window_form").show()}},
        {view: "button", label: "Refresh Information",type: "icon", icon:"wxi-sync", width: 200, align: "left", click: fetchGetInventaire}
    ]
}

var Precommande_buttons = {
    view: "toolbar", height: 100, padding:25, elements : [
        {view: "button", label: "Supprimer Precommande", type: "icon", icon:"wxi-trash", width: 200, align: "right"},
        {view: "button", label: "Ajouter Precommande",type: "icon", icon:"wxi-plus",width: 200},
        {view: "button", label: "Refresh Information",type: "icon", icon:"wxi-sync", width: 200, align: "left"}
    ]
}
var Commande = {

    "columns": [
        { "id": "id_commande","header":[{ text: "#" },{ content: "textFilter" }], "autowidth": true, "sort": "int" },
        { "id": "prix_total","header":[{ text: "PrixTotal" }],autowidth: true, sort: "int" },
        { "id": "cip", "header": "Cip", "sort": "string" },
        { "id": "id_etat_commande", "header": "id_etat_commande", "sort": "int",width: 150 },
        { "id": "date", "header": "Date", "sort": "date",fillspace: true }
    ],
    "view": "datatable",
    id: "commandeTable",
    data: Commande_data,
}

var Precommande = {

    "columns": [
        { "id": "NoCommande", "header": [{ text: "#" }], width: 50, "sort": "int" },
        { "id": "Cip", "header": [{ text: "Cip" }, { content: "textFilter" }], "fillspace": true, sort: "string" },
        { "id": "QtePrec", "header": "Quantitee", "sort": "string" },
        { "id": "Temps", "header": "temps", "sort": "string" },
        { "id": "NomArticle", "header": "Nom", "sort": "string" },
        { "id": "QteArticle", "header": "Quantite", "sort": "string" },
    ],
    "view": "datatable"
}

var log = {

    "columns": [
        { "id": "Id", "header": [{ text: "#" }], width: 50, "sort": "int" },
        { "id": "Action", "header": [{ text: "Action" }, { content: "textFilter" }], "fillspace": true, sort: "string" },
        { "id": "Old", "header": "Vieille", "sort": "string" },
        { "id": "New", "header": "Nouvelle", "sort": "string" },
        { "id": "Temps", "header": "Temps", "sort": "string" }
    ],
    "view": "datatable"
}
webix.ready(function() {
    webix.ui({
        view: "window",
        id: "window_form",
        move: true,
        head: {
            view:"toolbar", cols:[
                { width:4 },
                { view:"label", label: "Ajouter Article" },
                { view:"button", label: "Fermer", autowidth: true, align: 'right', click:function(){ $$('window_form').hide(); }}
            ]
        },
        left: 400, top: 150,

        body: {
            view: "form",elements:[
                {"label": "Nom", "view": "text", name: "nom", id:"nom", "height": 38, required: true, placeholder:"Taper ici le nom de l'article"},
                {"label": "Description", "view": "text", name: "description", id:"description", "height": 38, required: true, placeholder:"Taper ici la description de l'article"},
                {"label": "Prix", "view": "text", name: "prix", id:"prix", "height": 38, required: true, placeholder:"Taper ici le prix de l'article"},
                {"label": "Taille", "view": "text", name: "taille", id:"taille", "height": 38, required: true, placeholder:"Taper ici la taille de l'article"},
                {"label": "Couleur", "view": "text", name: "couleur", id:"couleur", "height": 38, required: true, placeholder:"Taper ici la couleur de l'article"},
                {"label": "Visibilité", "view": "text", name: "visibilite", id:"visibilite", "height": 38, required: true, placeholder:"Taper ici la visibilite de l'article"},
                {"label": "État", "view": "text", name: "etat", id:"etat", "height": 38, required: true, placeholder:"Taper ici l'etat de l'article"},
                {"label": "Url photo", "view": "text", name: "url", id:"url", "height": 38, required: true, placeholder:"Taper ici l'url de l'article"},
                {"label": "Quantite", "view": "text", name: "quantite", id:"quantite", "height": 38, required: true, placeholder:"Taper ici la quantite de l'article"},
                {
                    "label": "Ajouter produit",
                    "view": "button",
                    "height": 38,
                    click: function (){

                        const xhr = new XMLHttpRequest();
                        xhr.open('POST', 'http://localhost:8080/exemple-1/api/insert_produit');
                        var data = {nom:$$("nom").getValue(),
                            description:$$("description").getValue(),
                            prix:$$("prix").getValue(),
                            taille:$$("taille").getValue(),
                            couleur:$$("couleur").getValue(),
                            visibilite:$$("visibilite").getValue(),
                            etat:$$("etat").getValue(),
                            url:$$("url").getValue(),
                            quantite:$$("quantite").getValue()}
                        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
                        var datatexte = ('nom='+data.nom + '&description='+data.description + '&prix='+data.prix + '&taille='+data.taille + '&couleur='+data.couleur + '&visibilite='+data.visibilite + '&etat='+data.etat+ '&url='+data.url + '&quantite='+data.quantite);
                        xhr.send(datatexte);
                        xhr.onload = () =>{
                            console.log(xhr.response);
                        };

                    }
                }
            ],
            autoheight: true,
            autowidth:true
        }
    })
});

