
webix.ready(function(){
	//webix.html.addCss($$("agegTitre").$view, "red");
	if (webix.CustomScroll)
		webix.CustomScroll.init();
	webix.ui({
		view:"scrollview",
		id:"scrollview",
		scroll:"y",
		height:"80%",
		width:"80%",body:{
			rows:[{
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
					scroll: "x",
					select:true,
					type: {
						height: 200,
						width:"auto"
					},
					template:" <div class='webix_strong'>#title#</div>  Prix: #prix# $, rank: #rank# <a href = pageArticles.html> <img src= #image2# class = image_article alt='images articles'></a>  >",
					data:[
						{ id:1, title:"Tuque", image2:"https://drive.google.com/uc?export=view&id=1hKaET_4XQ8-nXZq96YEAHFx-cPPLx6sO",prix:1994, votes:678790, rating:9.2, rank:1},
						{ id:2, title:"Bas",  image2:"https://drive.google.com/uc?export=view&id=1GVDq4TWYwS35es9k7IdcN4s76PV1JRE3",prix:1972, votes:511495, rating:9.2, rank:2},
						{ id:3, title:"Chapeau", image2:"https://drive.google.com/uc?export=view&id=1GtqJ-uWW_aq8nIL6lYnLnG91PNQcwOxL", prix:1974, votes:319352, rating:9.0, rank:3},
						{ id:4, title:"Hoodie",  image2:"https://drive.google.com/uc?export=view&id=1JLcyOWfbtUFUMPBedtEcO9nw0wAn87A1", prix:1966, votes:213030, rating:8.9, rank:4}
					]

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