$(document).ready(function () {
    var counties = new Array("Alba", "Arad", "Arges", "Bacau", "Bihor", "Bistrita-Nasaud", "Botosani", "Braila",
            "Brasov", "Buzau", "Calarasi", "Caras-Severin", "Cluj", "Constanta", "Covasna", "Dimbovita",
            "Dolj", "Galati", "Giurgiu", "Gorj", "Harghita", "Hunedoara", "Ialomita", "Iasi", "Ilfov",
            "Maramures", "Mehedinti", "Mures", "Neamt", "Olt", "Prahova", "Salaj", "Satu Mare", "Sibiu",
            "Suceava", "Teleorman", "Timis", "Tulcea", "Vaslui", "Vilcea", "Vrancea");



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
    cities[12] = "Cluj-Napoca,Turda,Dej,Campia-Turzii,Gherla,Huedin";
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


    var stringHTML = "";
    for (var i = 0; i < counties.length; i++) {
        stringHTML += '<option>' + counties[i] + '</option>';
    }


// populate the dropdown with id = counties-dropdown
    $("#counties-dropdown").html("");
    $("#counties-dropdown").append(stringHTML);
    $("#counties-dropdown").selectmenu().selectmenu("menuWidget").addClass("overflow");

    stringHTML = "";
    var county_arr = cities[0].split(",");
    for (var i = 0; i < county_arr.length; i++) {
        stringHTML += '<option value='+county_arr[i]+' ng-model="county">' + county_arr[i] + '</option>';
    }

// populate the dropdown with id = cities-dropdown
    $("#cities-dropdown").html("");
    $("#cities-dropdown").append(stringHTML);
    $("#cities-dropdown").selectmenu().selectmenu("menuWidget").addClass("overflow");

    $("#counties-dropdown").selectmenu({
        change: function () {

            var county = $("#counties-dropdown option:selected").text();
            var countyId = $("#counties-dropdown option:selected").index();

            $("#cities-dropdown").selectmenu("destroy");
            populateCityListByCountyId(countyId - 1);

        }
    });

    function populateCityListByCountyId(countyId) {
        var stringHTML = "";
        var city_arr = cities[countyId].split(",");
        for (var i = 0; i < city_arr.length; i++) {
            stringHTML += '<option value='+city_arr[i]+'>' + city_arr[i] + '</option>';
        }

        $("#cities-dropdown").html("");
        $("#cities-dropdown").append(stringHTML);
        $("#cities-dropdown").selectmenu().selectmenu("menuWidget").addClass("overflow");
    }
    
    
    


});