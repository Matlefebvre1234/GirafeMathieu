var Inventaire = {
    cols: [
        {
            "columns": [
                { "id": "Id", "header": [{ text: "#" }], autowidth:true, "sort": "int" },
                { "id": "Nom", "header": [{ text: "Nom" }, { content: "textFilter" }], "fillspace": true, sort: "string" },
                { "id": "Qte", "header": [{ text: "Quantite" }], sort: "string", autowidth:true },
                { "id": "ModifTime", "header": [{ text: "Temps" }], "sort": "string", autowidth:true },
                { "id": "PrixUnitaire", "header": [{ text: "Prix Unitaire" }], "sort": "string", autowidth:true },
                { "id": "Action", "header": [{ text: "Action" }], template: "{common.trashIcon} {common.editIcon}", autowidth:true }
            ],
            view: "datatable",
            data: Inventaire_data,
            scroll: false,
            type: {
                trashIcon: "<span class='webix-icon wxi-trash'></span>",
                editIcon: "<span class='webix-icon wxi-pencil'></span>"
            }         
        }
    ]
}   

var Precommande = {

    "columns": [
        { "id": "Id", "header": [{ text: "#" }], width: 50, "sort": "int" },
        { "id": "Nom", "header": [{ text: "Nom" }, { content: "textFilter" }], "fillspace": true, sort: "string" },
        { "id": "QtePrec", "header": "Quantite", "sort": "string" },
        { "id": "DebutPrec", "header": "Debut", "sort": "string" },
        { "id": "FinPrec", "header": "Fin", "sort": "string" }
    ],
    "view": "datatable"
}

var Commande = {

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