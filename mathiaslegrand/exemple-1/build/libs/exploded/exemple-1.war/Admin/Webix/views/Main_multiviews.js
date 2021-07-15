
var ajouterAdmin = {
    cols: [
            {"view": "text", "placeholder": "Type here...", "label": "Cip", name: "cip", id:"cipRajouter", "labelWidth": 100, width: 400},
            {"label": "Ajouter Admin", "view": "button", "height": 38, width: 200, align: "right",
                click: function (){
                    const xhr = new XMLHttpRequest();
                    xhr.open('POST', 'http://localhost:8080/exemple-1/api/insert_admin');
                    var data = {cip:$$("cipRajouter").getValue()}
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

var retirerAdmin = {
    cols: [
                {"view": "text", "placeholder": "Type here...", "label": "Cip", name: "cip", id:"cipRetirer", "labelWidth": 100, width: 400},
                {"label": "Retirer Admin", "view": "button", "height": 38, width: 200, align: "right",
                    click: function (){
                        const xhr = new XMLHttpRequest();
                        xhr.open('POST', 'http://localhost:8080/exemple-1/api/remove_admin');
                        var data = {cip:$$("cipRetirer").getValue()}
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

var refresh_admin = {
    cols: [
        {view: "button", label: "Refresh admin", id: "refreshAdmin",type: "icon", icon:"wxi-sync", width: 200, align: "left", click: fetchGetAdmin}
    ]
}

var datatableAdmin = {
    cols: [
            {
                "colums": [
                    {"id": "cipAdmin", "header": [{text: "Cip"}, {content: "textFilter"}], autowidth: true},
                    {"id": "Prenom", "header": [{text: "Prenom"}, {content: "textFilter"}], "fillspace": true},
                    {"id": "nomFamille", "header": [{text: "NomFamille"}, {content: "textFilter"}], autowidth: true},
                    {"id": "courriel", "header": [{text: "courriel"}, {content: "textFilter"}], autowidth: true}
                ],
                view: "datatable",
                id: "adminTable",
                data: Admin_data,
                scroll: false,
                select: true
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
                { "id": "prix", "header": [{ text: "Prix Unitaire" }], "sort": "string", autowidth:true },
                { id:"trash", header:"Supprimer", template:"{common.trashIcon()}"},
               { id:"", template:"<input class='viewbtn' type='button' value='View'>", css:"padding_less", width:100 },
               { id:"", template:"<input class='modifbtn' type='button' value='Modif'>", css:"padding_less", width:100 }
            ],
            view: "datatable",
            id: "inventaireTable",
            data: Inventaire_data,
            scroll: false,
            select:true,
            onClick:{
                "wxi-trash":function(event, id, node){
                    var record = $$("inventaireTable").getItem(id);
                    console.log(record.idproduit);
                    const xhr = new XMLHttpRequest();
                    xhr.open('POST', 'http://localhost:8080/exemple-1/api/remove_produit');
                    var data = record.idproduit;
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
                    var datatexte = ('idproduit='+data)
                    xhr.send(datatexte);
                }
            }
        }
    ]
}
var Inventaire_buttons = {
    view: "toolbar", height: 100, padding:25, elements : [
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

var Commande_buttons = {
    view: "toolbar", height: 100, padding:25, elements : [
        {view: "button", label: "Refresh Information",type: "icon", icon:"wxi-sync", width: 200, align: "left", click: fetchGetCommande}
    ]
}
var Commande = {

    "columns": [
        { "id": "id_commande","header":[{ text: "#" },{ content: "textFilter" }], "autowidth": true, "sort": "int" },
        { "id": "prix_total","header":[{ text: "PrixTotal" }],autowidth: true, sort: "int" },
        { "id": "cip", "header": "Cip", "sort": "string" },
        { "id": "id_etat_commande", "header": "id_etat_commande", "sort": "int",width: 150 },
        { "id": "date", "header": "Date", "sort": "date",fillspace: true },
        { id:"trash", header:"", template:"{common.trashIcon()}"},
        { id:"", template:"<input class='viewbtn' type='button' value='View'>", css:"padding_less", width:100 }
    ],
    "view": "datatable",
    id: "commandeTable",
    data: Commande_data,
    onClick:{
        "wxi-trash":function(event, id, node){
            var record = $$("commandeTable").getItem(id);
            console.log(record.id_commande);
            const xhr = new XMLHttpRequest();
            //xhr.open('POST', 'http://localhost:8080/exemple-1/api/????);
            var data = record.id_commande;
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
            var datatexte = ('idproduit='+data)
            xhr.send(datatexte);
        }
    }
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
                { view:"label", label: "Modifier Article" },
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
var record = [
    {nomitem:"1",description:"1",prix:"1",taille:"1",couleur:"1",visibilite:"1",etat:"1"}
];

webix.ready(function() {
    webix.ui({
        view: "window",
        id: "window_modif",
        move: true,
        head: {
            view:"toolbar", cols:[
                { width:4 },
                { view:"label", label: "Modifier" },
                { view:"button", label: "Fermer", autowidth: true, align: 'right', click:function(){ $$('window_modif').hide(); }}
            ]
        },
        left: 400, top: 150,
        body: {
            view: "form",elements:[
                {"label": "Nom", "view": "text",type: "text", name: "nom", id:"nomModif", "height": 38},
                {"label": "Description", "view": "text", name: "description", id:"descriptionModif", "height": 38},
                {"label": "Prix", "view": "text", name: "prix", id:"prixModif", "height": 38},
                {"label": "Taille", "view": "text", name: "taille", id:"tailleModif", "height": 38},
                {"label": "Couleur", "view": "text", name: "couleur", id:"couleurModif", "height": 38},
                {"label": "Visibilité", "view": "text", name: "visibilite", id:"visibiliteModif", "height": 38},
                {"label": "État", "view": "text", name: "etat", id:"etatModif", "height": 38},
                {"label": "Quantite", "view": "text", name: "quantite", id:"quantiteModif", "height": 38},
                {
                    "label": "Modifier article",
                    "view": "button",
                    "height": 38,
                    id:"formModif",
                    click: function (id){
                        const xhr = new XMLHttpRequest();
                        xhr.open('POST', 'http://localhost:8080/exemple-1/api/produit/modifierProduit');
                        var data = {
                            idproduit:$$("id"),
                            nom:$$("nomModif").getValue(),
                            description:$$("descriptionModif").getValue(),
                            prix:$$("prixModif").getValue(),
                            taille:$$("tailleModif").getValue(),
                            couleur:$$("couleurModif").getValue(),
                            visibilite:$$("visibiliteModif").getValue(),
                            etat:$$("etatModif").getValue(),
                            quantite:$$("quantiteModif").getValue()}
                        console.log(data);
                        data.idproduit = getId();
                        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
                        var datatexte = ('id='+data.idproduit + '&nom='+data.nom + '&description='+data.description + '&prix='+data.prix + '&taille='+data.taille + '&couleur='+data.couleur + '&visibilite='+data.visibilite + '&etat='+data.etat + '&quantite='+data.quantite);
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

webix.ready(function() {
    webix.ui({
        view: "window",
        id: "window_command",
        move: true,
        head: {
            view:"toolbar", cols:[
                { width:4 },
                { view:"label", label: "Modifier" },
                { view:"button", label: "Fermer", autowidth: true, align: 'right', click:function(){ $$('window_command').hide(); }}
            ]
        },
        left: 400, top: 150,
        body: {
            "columns": [
                { "id": "idproduit", "header": [{ text: "#" }], autowidth:true, "sort": "int" },
                { "id": "quantite", "header": [{ text: "Quantite" }], autowidth:true, sort: "int" },
                { "id": "prixtotal", "header": [{ text: "Prix Total" }], sort: "int", autowidth:true }
            ],
                    view: "datatable",
                    id: "dataviewCommand",
                    data: Command_datatable,
                    autoheight: true,
                    autowidth:true,
                    css: "webix_dark"
        }
    })
});

var ModifId;

const modifId = (id) => {
    ModifId = id;
    console.log(ModifId);
}

const getId = () =>{
    console.log(ModifId);
    return ModifId;

}


const modifData = (id) =>{
    const xhr = new XMLHttpRequest();
    var value1= $$("inventaireTable").getItem(id);
    xhr.open('POST', 'http://localhost:8080/exemple-1/api/produit/getProduit');
    var data = value1.idproduit;
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    console.log(data);
    var datatexte = ('idProduit='+data)
    xhr.send(datatexte);

    xhr.onload = () =>{
        record =  JSON.parse(xhr.response);
        console.log(record);
    };
    $$("nomModif").setValue(record.nomitem);
    $$("descriptionModif").setValue(record.description);
    $$("prixModif").setValue(record.prix);
    $$("tailleModif").setValue(record.taille);
    $$("couleurModif").setValue(record.couleur);

    $$("window_modif").show();
}

