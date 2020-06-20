var mymap, radFloat, popup, Popup;

function initMap(rad, list) {

  radFloat = Number(rad) * 1000;

  mymap = L.map('mapid',{
    minZoom: 0,
    maxZoom: 20,
    closePopupOnClick: false,
    scrollWheelZoom: false
  }).locate({setView: true, maxZoom: 20});

  L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    id: 'mapbox/streets-v11',
    tileSize: 512,
    minZoom: 0, 
    maxZoom: 20,
    zoomOffset: -1,
    accessToken: key_leaflet
  }).addTo(mymap);

  function onLocationFound(e) {
    L.marker(e.latlng).addTo(mymap);
    L.circle(e.latlng, radFloat).addTo(mymap);
  }
  mymap.on('locationfound', onLocationFound);
  console.log(mymap.getZoom()); 
  showStations(list);
}

function showStations(list) {

  for (var i = 0; i < list[0].length; i++) 
  {
    var gs = list[0][i];
    var lat = gs.lat;
    var lon = gs.lng;
    var contentString = "<h6><b>" + gs.name + "</b></h6>"
      + "<p>"+ gs.street + " " + gs.houseNumber + " <br>" + gs.postCode + " "+ gs.place +"</p>"
      + "<p><b>aktuelle Preise:</b><br>"
      + "Diesel: " + gs.diesel + "€ <br>"
      + "E5: " + gs.e5 + "€ <br>"
      + "E10: " + gs.e10 + "€ </p>";
    var favList = Cookies.get("cfavourites");
    if (favList == undefined) 
    {
      favList = new String("");
    }
    if (!favList.includes(gs.id)) 
    {

      contentString += "<button id=\"favourite" + gs.id + "\" class=\"btn btn-primary btn-sm\" onclick=\"toggleFav(this.id) \"><i data-toggle=\"tooltip\" data-placement=\"right\" title=\"zu Favoriten hinzufügen\" class=\"material-icons align-middle\"> favorite </i></button >"
    }
    else 
    {
      contentString += "<button id=\"\'favourite" + gs.id + "\" class=\"btn btn-primary btn-sm\" onclick=\"toggleFav(this.id) \"><i data-toggle=\"tooltip\" data-placement=\"right\" title=\"von Favoriten entfernen\" class= \"material-icons align-middle\" > clear </i ></button >"
    }

    var popup = L.popup({
      closeOnClick: false,
      autoClose: false,
      closeButton: false,
    })
      .setLatLng([lat, lon])
      .setContent(contentString)
      .addTo(mymap);
  }
}
