
webix.ready(function(){
	if (webix.CustomScroll)
		webix.CustomScroll.init();
	webix.ui({
	"rows": [
		{
			"autoheight": false,
			"view": "form",
			"rows": [
				{
					"view": "text",
					"label": "Nom",
					"name": "name"
				},
				{
					"view": "text",
					"label": "Prenom",
					"prenom": "prenom"
				},
				{
				
					"view": "text",
					"label": "CIP",
					"cip": "cip"
				},
				{
					"view": "button",
					"css": "webix_primary",
					"label": "Save"
				},
				{
					
					"columns": [
						{
							"id": "title",
							"header": "Title",
							"fillspace": true,
							"sort": "string"
						},
						{
							"id": "year",
							"header": "Year",
							"sort": "string"
						},
						{
							"id": "votes",
							"header": "Votes",
							"sort": "string"
						},
						{
							"id": "rating",
							"header": "Rating",
							"sort": "string"
						},
						{
							"id": "rank",
							"header": "Rank",
							"sort": "string"
						},
						{
							"id": "category",
							"header": "Category",
							"sort": "string"
						}
					],
					"view": "datatable",
					"height": 79
				},
				{
					"autoheight": false,
					"view": "form",
					"rows": [
						{
							"view": "text",
							"label": "User name",
							"name": "name"
						},
						{
							"view": "text",
							"label": "Email",
							"name": "email"
						},
						{
							"view": "button",
							"css": "webix_primary",
							"label": "Save",
							"height": 106,
							click: function (){

								//get des infos

								/*const xhr = new XMLHttpRequest();
								xhr.open('GET','http://localhost:8080/exemple-1/api/test/get');
								xhr.send();
								xhr.onload = () => {
									console.log(xhr.response);*/
								//};

								//send info

								const xhr = new XMLHttpRequest();
								xhr.open('POST','http://localhost:8080/exemple-1/api/test/post');
								let data = {cip:'lefm1509',nom:'Mathieu'};
								xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded')
								let datatexte = "cip="+data.cip+"&nom="+data.nom;
								console.log(datatexte);
								xhr.send(datatexte);
								xhr.onload = () => {
									console.log(xhr.response);
								};

							}

						},
						{
							"url": "demo->60b90882fd710000187ea330",
							"columns": [
								{
									"id": "title",
									"header": "Data table des clients",
									"fillspace": true,
									"sort": "string",
									"hidden": false
								},
								{
									"id": "year",
									"sort": "string",
									"fillspace": false,
									"hidden": false
								},
								{
									"id": "votes",
									"header": "Votes",
									"sort": "string"
								},
								{
									"id": "rating",
									"header": "Rating",
									"sort": "string"
								},
								{
									"id": "rank",
									"header": "Rank",
									"sort": "string"
								},
								{
									"id": "category",
									"header": "Category",
									"sort": "string"
								}
							],
							"view": "datatable"
						}
					]
				}
			]
		}
	]
});
});

