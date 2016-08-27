var app = angular.module('AngularJSClientApp', []);


// CUSTOM DIRECTIVE FOR PASSWORD MATCH
app.directive('passwordMatch', [function(){
	return{
		restrict: 'A',
		scope: true,
		require: 'ngModel',
		link: function(scope, elem, attrs, control){
			var checker = function(){
				// get value of first password
				var p1 = scope.$eval(attrs.ngModel);

				// get the value of the second passord
				var p2 = scope.$eval(attrs.passwordMatch);
				return p1 == p2;
			};
			scope.$watch(checker, function(n){
				control.$setValidity("match", n);
			});
		}
	};
}]);



// CONTROLLER
app.controller('SignupController', function($scope, $location){
 	
    var cities = new Array();
    cities[0] = "Alba Iulia,Sebes,Aiud,Cugir,Blaj,Ocna Mures,Zlatna,Campeni,Teius,Abrud,Baia de Aries";
    cities[1] = "Arad,Pecica,Santana,Lipova,Ineu,Chisineu Cris,Nadlac,Curtici,Pancota,Sebis";
    cities[2] = "Pitesti,Mioveni,Campulung,Curtea de Arges,Stefanesti,Costesti,Topoloveni";
    cities[3] = "Bacau,Onesti,Moinesti,Comanesti,Buhusi,Darmanesti,Targu Ocna,Slanic-Moldova";
    cities[4] = "Oradea,Salonta,Marghita,Sacueni,Beius,Valea lui Mihai,Alesd,Stei,Vascau,Nucet";
    cities[5] = "Bistrita,Beclean,Sangeorz-Bai,Nasaud";
    cities[6] = "Botosani,Dorohoi,Darabani,Flamanzi,Saveni,Stefanesti,Bucecea";
    cities[7] = "Braila,Ianca,Insuratei,Faurei";
    cities[8] = "Brasov,Fagaras,Sacele,Zarnesti,Codlea,Rasnov,Victoria,Rupea,Ghimbav,Predeal";
    cities[9] = "Buzau,Ramnicu Sarat,Nehoiu,Patarlagele,Pogoanele";
    cities[10] = "Calarasi,Oltenita,Budesti,Fundulea,Lehliu-Gara";
    cities[11] = "Resita,Caransebes,Bocsa,Moldova Noua,Oravita,Otelu Rosu,Anina,Baile Herculane";
    cities[12] = "Cluj-Napoca,Turda,Dej,Campia Turzii,Gherla,Huedin";
    cities[13] = "Constanta,Medgidia,Mangalia,Navodari,Cernavoda,Ovidiu,Murfatlar,Harsova,Eforie,Techirghiol,Baneasa,Negru Voda";
    cities[14] = "Sfantu Gheorghe,Targu Secuiesc,Covasna,Baraolt,Intorsura Buzaului";
    cities[15] = "Targoviste,Moreni,Pucioasa,Gaesti,Titu,Fieni,Racari";
    cities[16] = "Craiova,Bailesti,Calafat,Filiasi,Dabuleni,Segarcea,Bechet";
    cities[17] = "Galati,Tecuci,Targu Bujor,Beresti";
    cities[18] = "Giurgiu,Bolintin-Vale,Mihailesti";
    cities[19] = "Targu Jiu,Motru,Rovinari,Bumbesti-Jiu,Targu Carbunesti,Turceni,Tismana,Novaci,Ticleni";
    cities[20] = "Miercurea-Ciuc,Odorheiu Secuiesc,Gheorgheni,Toplita,Cristuru Secuiesc,Vlahita,Balan,Borsec,Baile Tusnad";
    cities[21] = "Deva, Hunedoara,Petrosani,Vulcan,Lupeni,Petrila,Orastie,Brad,Simeria,Calan,Hateg,Uricani,Geoagiu,Aninoasa";
    cities[22] = "Slobozia,Fetesti,Urziceni,Tandarei,Amara,Fierbinti-Targ,Cazanesti";
    cities[23] = "Iasi,Pascani,Harlau,Targu Frumos,Podu Iloaiei";
    cities[24] = "Bucuresti,Voluntari,Pantelimon,Buftea,Popesti-Leordeni,Bragadiru,Chitila,Otopeni,Magurele";
    cities[25] = "Baia Mare,Sighetu Marmatiei,Borsa,Baia-Sprie,Viseu de Sus,Targu-Lapus,Seini,Somcuta Mare,Ulmeni,Tautii Magheraus,Cavnic,Salistea de Sus,Dragomiresti";
    cities[26] = "Drobeta-Turnu Severin,Strehaia,Orsova,Baia de Arama,Vanju Mare";
    cities[27] = "Targu-Mures,Reghin,Sighisoara,Tarnaveni,Ludus,Sovata,Iernut,Sarmasu,Ungheni,Miercurea Nirajului,Sangeorgiu-de-Padure";
    cities[28] = "Piatra Neamt,Roman,Targu-Neamt,Roznov,Bicaz";
    cities[29] = "Slatina,Caracal,Bals,Corabia,Scornicesti,Draganesti-Olt,Piatra Olt,Potcoava";
    cities[30] = "Ploiesti,Campina,Baicoi,Breaza,Mizil,Comarnic,Valenii de Munte,Boldesti-Scaeni,Urlati,Sinaia,Busteni,Plopeni,Slanic,Azuga";
    cities[31] = "Zalau,Simleu Silvaniei,Jibou,Cehu Silvaniei";
    cities[32] = "Satu Mare,Carei,Negresti-Oas,Tasnad,Livada,Ardud";
    cities[33] = "Sibiu, Medias,Cisnadie,Avrig,Agnita,Dumbraveni,Talmaciu,Copsa Mica,Saliste,Miercurea Sibiului,Ocna Sibiului";
    cities[34] = "Suceava,Falticeni,Radauti,Campulung Moldovenesc,Vatra Dornei,Vicovu de Sus,Gura Humorului,Dolhasca,Liteni,Salcea,Siret,Cajvana,Frasin,Brosteni,Milisauti,Solca";
    cities[35] = "Alexandria,Rosiorii de Vede,Turnu Magurele,Zimnicea,Videle";
    cities[36] = " Timisoara,Lugoj,Sannicolau Mare,Jimbolia,Recas,Faget,Buzias,Deta,Gataia,Ciacova";
    cities[37] = "Tulcea,Babadag,Macin,Isaccea,Sulina";
    cities[38] = "Vaslui,Barlad,Husi,Negresti,Murgeni";
    cities[49] = "Ramnicu Valcea,Dragasani,Babeni,Calimanesti,Horezu,Brezoi,Balcesti,Berbesti,Baile Olanesti,Ocnele Mari,Baile Govora";
    cities[40] = "Focsani,Adjud,Marasesti,Odobesti,Panciu";

    $scope.city_list   = [{value: "Alba Iulia"},
    					  {value: "Sebes"},
    					  {value: "Aiud"},
    					  {value: "Cugir"},
    					  {value: "Blaj"},
    					  {value: "Ocna Mures"},
    					  {value: "Zlatna"},
    					  {value: "Campeni"},
    					  {value: "Teius"},
    					  {value: "Abrud"},
    					  {value: "Baia de Aries"}
    					  ];

	$scope.county_list = [{value:"Alba", 		index:'0'}, 
					   {value:"Arad", 			index:'1'}, 
					   {value:"Arges", 			index:'2'}, 
					   {value:"Bacau", 			index:'3'},	  
					   {value:"Bihor", 			index:'4'},
					   {value:"Bistrita-Nasaud",index:'5'},
					   {value:"Botosani", 		index:'6'},
					   {value:"Braila", 		index:'7'},
					   {value:"Brasov", 		index:'8'},
					   {value:"Buzau", 			index:'9'},
					   {value:"Calarasi", 		index:'10'},
					   {value:"Caras-Severin", 	index:'11'},
					   {value:"Cluj",     		index:'12'},
					   {value:"Constanta", 		index:'13'},
					   {value:"Covasna", 		index:'14'},
					   {value:"Dimbovita", 		index:'15'},
					   {value:"Dolj", 			index:'16'},
					   {value:"Galati", 		index:'17'},
					   {value:"Giurgiu",  		index:'18'},
					   {value:"Gorj",  			index:'19'},
					   {value:"Harghita", 		index:'20'},
					   {value:"Hunedoara", 		index:'21'},
					   {value:"Ialomita", 		index:'22'},
					   {value:"Iasi",  			index:'23'},
					   {value:"Ilfov",     		index:'24'},
					   {value:"Maramures",  	index:'25'},
					   {value:"Mehedinti", 		index:'26'},
					   {value:"Mures",     		index:'27'},
					   {value:"Neamt",  		index:'28'},
					   {value:"Olt",  			index:'29'},
					   {value:"Prahova",  		index:'30'},
					   {value:"Salaj",  		index:'31'},
					   {value:"Satu Mare",  	index:'32'},
					   {value:"Sibiu",   		index:'33'}, 
					   {value:"Suceava",  		index:'34'},
					   {value:"Teleorman",  	index:'35'},
					   {value:"Timis",    		index:'36'},
					   {value:"Tulcea",  		index:'37'},
					   {value:"Vaslui",  		index:'38'},
					   {value:"Vilcea",  		index:'39'},
					   {value:"Vrancea", 		index:'40'}
					   ];

	$scope.selected_county = $scope.county_list[0];
	$scope.selected_city   = $scope.city_list[0];

	$scope.populateCitiesDropdown = function(){
		var countyIndex = $scope.selected_county.index;		// get the index of selected county
		var city_arr = cities[countyIndex].split(",");		// array of cities
		$scope.city_list   = [];							// reinitialize the city list

		for (var i = 0; i < city_arr.length; i++) {
            var newCity = {
            	value: ''+city_arr[i]+''
            };
            $scope.city_list.push(newCity);					// add new city to the list
        }
        $scope.selected_city   = $scope.city_list[0];
	}

	

	/***** SUBMIT *****/
	$scope.submit = function(){
		var email 	 = $scope.email;
		var password = $scope.password;
		var phone    = $scope.phone;
		var address	 = $scope.address;
		var county	 = $scope.selected_county.value;
		var city     = $scope.selected_city.value; 

		console.log("Email " + email + "| Password " + password + 
					"| Phone " + phone + "| Address " + address +
					"| County " + county + "| City " + city  );
			
	};


});