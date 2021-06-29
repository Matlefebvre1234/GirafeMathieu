
var Inventaire_data = [

    {produit:{idproduit:1,nomitem:"HELLO",quantite:5,prix:"34"},quantite:1}
];

fetchGetInventaire();

async function fetchGetInventaire() {
    let response = await fetch("http://localhost:8080/exemple-1/api/produit/inventaire")
    data = await response.json();


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

    console.log(Inventaire_data[1].nomitem,);
    $$("inventaireTable").parse(Inventaire_data);
    console.log(Inventaire_data);
}

