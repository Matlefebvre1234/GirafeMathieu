
var Inventaire_data = [

    {produit:{idproduit:1,nomitem:"HELLO",quantite:5,prix:"34"},quantite:1}
];
var Commande_data = [

    {id_commande:1,prix_total:"10",cip:"xxxx",id_etat_commande:"1",date:"0000-00-00",listeItem: [{
            id_item_commander:"1",
            quantite: "1",
            prixtotal:"20",
            idproduit:1,
            id_etat_commade:1,
            produit:{
                nomitem:"nom",
                taille: "M",
                couleur:"rouge",
            }
        }]}
];

fetchGetCommande();
fetchGetInventaire();



async function fetchGetCommande() {
    let response = await fetch("http://localhost:8080/exemple-1/api/commande/commande")
    let data = await response.json();

    $$("commandeTable").clearAll()
    Commande_data = [];
    console.log(data);
    for (let i = 0; i < data.length; i++) {

        let currentListeItem = [];
        for (let j = 0; j < data[i].listeItem.length; j++) {
            currentListeItem.push({
                id_item_commander: data[i].listeItem[j].id_item_commander,
                quantite: data[i].listeItem[j].quantite,
                prixtotal: data[i].listeItem[j].prixtotal,
                idproduit: data[i].listeItem[j].idproduit,
                id_etat_commande: data[i].listeItem[j].id_etat_commade,
                produit: {
                    nomitem: data[i].listeItem[j].produit.nomitem,
                    taille: data[i].listeItem[j].produit.nomitem,
                    couleur: data[i].listeItem[j].produit.couleur,
                }
            })

        }

        Commande_data.push({
            id_commande: data[i].idCommande,
            prix_total: data[i].prixTotal,
            cip: data[i].cip,
            id_etat_commande: data[i].idEtatCommande,
            date: data[i].date,
            listeItem: currentListeItem
        });
    }
   // console.log(Commande_data);
    $$("commandeTable").parse(Commande_data);
    //console.log(Commande_data);
}
async function fetchGetInventaire() {
    let response = await fetch("http://localhost:8080/exemple-1/api/produit/inventaire")
    let data = await response.json();


    $$("inventaireTable").clearAll()
    Inventaire_data = [];
    for (let i =0;i<data.length;i++)
    {
        Inventaire_data.push({
            idproduit: data[i].produit.idproduit,
            nomitem: data[i].produit.nomitem,
            quantite:data[i].quantite,
            prix:data[i].produit.prix
        });
    }


    $$("inventaireTable").parse(Inventaire_data);
    //console.log(Inventaire_data[1].nomitem,);
    //console.log(Inventaire_data);
}

var Admin_data = [
    {
        cipAdmin : "test",
        nomFamille : "test",
        prenom : "test",
        courriel : "test@courriel.com"
    }
]

async function fetchGetAdmin() {
    let response = await fetch("http://localhost:8080/exemple-1/api/produit/isAdmin")
    let data = await response.json();
/* Probleme avec la commande pour get les admin
    - a regler si possible
    -isAdmin est vraiment pas clair

    console.log(data);

    $$("adminTable").clearall();
    Admin_data = [];
    for(let i =0; i<data.length;i++)
    {
        Admin_data.push({
            cip: data[i].cip,
            nom: data[i].nom,
            prenom: data[i].prenom,
            courriel: data[i].courriel
        })
    }

 */
}

var Command_datatable = [
]

const modifDataCommand = (id) =>{
    $$("dataviewCommand").clearAll();
    var record = $$("commandeTable").getItem(id);
    Command_datatable = record.listeItem;
    console.log("truc a charle");
    console.log(Command_datatable);
    $$("dataviewCommand").parse(Command_datatable);

    $$("window_command").show();
};
