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

var Commande = {

    "columns": [
        { "id": "id_commande","header":[{ text: "#" }], width: 50, "sort": "int" },
        { "id": "prixtotal","header":[{ text: "PrixTotal" }, { content: "textFilter" }], "fillspace": true, sort: "int" },
        { "id": "cip", "header": "Cip", "sort": "string" },
        { "id": "id_etat_commande", "header": "id_etat_commande", "sort": "int" },
        { "id": "date", "header": "Date", "sort": "date" }
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

