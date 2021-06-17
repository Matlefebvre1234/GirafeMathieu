
webix.ready(function(){
	if (webix.CustomScroll)
		webix.CustomScroll.init();
	webix.ui({
		"rows": [
			{
				"view": "form",
				"margin": 40,
				"rows": [
					{
						"margin": 20,
						"cols": [
							{
								"margin": 10,
								"rows": [
									{
										"view": "template",
										"type": "section",
										"template": "Menu utilisateur"
									},
									{
										"label": "M'ajouter comme client",
										"view": "button",
										"height": 38,
										click: function (){


											console.log("adawdawdawd")

											const xhr = new XMLHttpRequest();
											xhr.open('GET','http://localhost:8080/exemple-1/api/insert_etudiant');
											xhr.send();
											xhr.onload = () => {
												console.log(xhr.response);
											};
										}
									},
									{
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
													};
												}
											}
										]
									}
								]
							},
							{
								"margin": 10,
								"rows": [
									{
										"view": "template",
										"type": "section",
										"template": "Menu produit"
									},
									{
										"label": "Nom",
										"view": "text",
										name: "nom",
										id:"nom",
										"height": 38
									},
									{
										"label": "Description",
										"view": "text",
										name: "description",
										id:"description",
										"height": 38
									},
									{
										"label": "Prix",
										"view": "text",
										name: "prix",
										id:"prix",
										"height": 38
									},
									{
										"label": "Taille",
										"view": "text",
										name: "taille",
										id:"taille",
										"height": 38
									},
									{
										"label": "Couleur",
										"view": "text",
										name: "couleur",
										id:"couleur",
										"height": 38
									},
									{
										"label": "Visibilité",
										"view": "text",
										name: "visibilite",
										id:"visibilite",
										"height": 38
									},
									{
										"label": "État",
										"view": "text",
										name: "etat",
										id:"etat",
										"height": 38
									},
									{
										"label": "Url photo",
										"view": "text",
										name: "url",
										id:"url",
										"height": 38
									},
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
														url:$$("url").getValue()}
											xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
											var datatexte = ('nom='+data.nom + '&description='+data.description + '&prix='+data.prix + '&taille='+data.taille + '&couleur='+data.couleur + '&visibilite='+data.visibilite + '&etat='+data.etat+ '&url='+data.url);
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
		]
	});
})

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