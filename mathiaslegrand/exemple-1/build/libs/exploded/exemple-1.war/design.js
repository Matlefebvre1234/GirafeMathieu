
webix.ready(function(){
	if (webix.CustomScroll)
		webix.CustomScroll.init();
	webix.ui({
		rows:[{
			view:"carousel",
			css:"webix_dark",
			id:"carousel",
			scrollSpeed: "500ms",
			width:500,
			height:500,
			cols:[
				{ css: "image", template:img, data:{src:"https://drive.google.com/uc?export=view&id=1hKaET_4XQ8-nXZq96YEAHFx-cPPLx6sO"} },
				{ css: "image", template:img, data:{src:"https://drive.google.com/uc?export=view&id=1GVDq4TWYwS35es9k7IdcN4s76PV1JRE3"} },
				{ css: "image", template:img, data:{src:"https://drive.google.com/uc?export=view&id=1GtqJ-uWW_aq8nIL6lYnLnG91PNQcwOxL"} },
				{css: "image", template:img, data:{src:"https://drive.google.com/uc?export=view&id=1juGpKhUP184DaYzEferp7eOPAXzC3qWn"} },
				{ css: "image",  template:img, data:{src:"https://drive.google.com/uc?export=view&id=1JLcyOWfbtUFUMPBedtEcO9nw0wAn87A1"} }
			]},
			{
				view:"button",
				type: "image",
				image: { css: "image", template:img, data:{src:"https://drive.google.com/uc?export=view&id=1hKaET_4XQ8-nXZq96YEAHFx-cPPLx6sO"} },
				label: "Image Button"
			}
		]
	});
})
function img(obj){
	return '<img src="'+obj.src+'" alt="centered image" class="content"/>'
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