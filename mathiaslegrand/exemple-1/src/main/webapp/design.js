
webix.ready(function(){
	//webix.html.addCss($$("agegTitre").$view, "red");
	webix.ui({
		view:"scrollview",
		id:"scrollview",
		enable: true,
		scroll:"y",
		body:{
			rows:[
				{
				view: "label",
				id: "agegTitre",
				height: 200,
				label : "AGEG UDES",
				css: "titre",
				align: "center"
				//label: "Bas De texte"
				},
				{
					view: "label",
					id:"En vedette",
					label : "En vedette",
					css: "titre2"
					//label: "Bas De texte"
				},
				{
					view:"carousel",
					css:"webix_dark",
					id:"carousel",
					scrollSpeed: "500ms",
					width:500,
					height:500,
					cols:[
						{ css: "images", template:img, data:{src:"https://drive.google.com/uc?export=view&id=1hKaET_4XQ8-nXZq96YEAHFx-cPPLx6sO"} },
						{ css: "images", template:img, data:{src:"https://drive.google.com/uc?export=view&id=1GVDq4TWYwS35es9k7IdcN4s76PV1JRE3"} },
						{ css: "images", template:img, data:{src:"https://drive.google.com/uc?export=view&id=1GtqJ-uWW_aq8nIL6lYnLnG91PNQcwOxL"} },
						{css: "images", template:img, data:{src:"https://drive.google.com/uc?export=view&id=1juGpKhUP184DaYzEferp7eOPAXzC3qWn"} },
						{ css: "images",  template:img, data:{src:"https://drive.google.com/uc?export=view&id=1JLcyOWfbtUFUMPBedtEcO9nw0wAn87A1"} }
					]},
				{
					view: "label",
					id:"Promotion",
					height: 100,
					label : "Promotion",
					css: "titre2"
					//label: "Bas De texte"
				},
				{
					view:"dataview",
					id:"dataview1",
					height:400,
					xCount:3,
					scroll: "y",
					select:true,
					url : "http://localhost:8080/exemple-1/api/produit/listeproduitsdistinct",
					type: {
						height: 200,
						width:"auto"
					},
					template:"<div class='webix_strong'>#nomitem#</div>  Prix: #prix# $ <img src= #arrayPhoto[0]# class = image_article alt='images articles'>",
				},
				{
					view: "label",
					id: "Suivez-nous",

					label : "Suivez-nous!!!",
					//label: "Bas De texte"
				},{
					cols:[
						{
							view: "label",

							id: "Facebook",

							label : "Facebook",
							//label: "Bas De texte"
						},
						{
							view:'icon',icon:"fas fa-dungeon",align:"left"
						}
					]
				},
				{cols:[
						{
							view: "label",
							id: "Twitter",

							label : "Twitter",
							//label: "Bas De texte"
						},{
							view: "label",
							id: "Suivez-nous",

							label : "Suivez-nous!!!",
							//label: "Bas De texte"
						}
					]
				}
			]
		}
	});
	$$("dataview1").attachEvent("onAfterSelect", function(id){
		var value1= $$("dataview1").getItem(id);
		var value = value1["idproduit"];
		var queryString = "?" + value;
		window.location.href = "pageArticles.html" + queryString;
	}
	);
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